package br.com.codersistemas.gem.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.gem.util.JPAHelper;
import br.com.codersistemas.gem.util.ReflectionHelper;
import br.com.codersistemas.gem.util.StringHelper;
import br.com.codersistemas.libs.utils.ReflectionUtils;
import br.com.codersistemas.libs.utils.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class App {
	
	private Class<?>[] classes;
	private List<Model> models;
	private String appName, defaultPackage, schema, database;
	
	public void addClasses(Class<?>...classes) {
		this.classes = classes;
		List<Attribute>fks=new ArrayList<>();
		for (int i = 0; i < classes.length; i++) {
			
			List<Attribute> attributes = new ArrayList<>();
			Class<?> class1 = this.classes[i];
			
			if(!JPAHelper.isEntity(class1)) {
				continue;
			}
			
			Model model = Model.builder()
					.app(this)
					.label(StringUtil.label(class1.getSimpleName()))
					.attributes(attributes)
					.className(class1.getCanonicalName())
					.tableName(JPAHelper.tableName(class1))
					.name(new Name(class1.getSimpleName()))
					.restURI("/" + StringHelper.toHifenCase(StringHelper.plural(class1.getSimpleName())).toLowerCase())
					.build();
			
			models.add(model);
			Field[] fields = ReflectionHelper.getAllFields(class1);
			for (Field field : fields) {
				Attribute attribute = Attribute.builder()
						.label(StringHelper.capitalize(StringHelper.toCase(field.getName(), ' ')))
						.model(model)
						.name(new Name(field.getName()))
						.required(JPAHelper.isRequired(field))
						.size(JPAHelper.getSize(field))
						.type(ReflectionHelper.getType(field, this))
						.unique(JPAHelper.isUnique(field))
						.pk(JPAHelper.isPk(field))
						.fk(JPAHelper.isFk(field))
						.build();
				attributes.add(attribute);
				
				attribute.setSize(setSize(attribute));
				
				if(attribute.isFk()) {
					fks.add(attribute);
				}
			}
		}
		
		for(Attribute attribute : fks) {
			for(Model model : models) {
				if(model.getClassName().equals(attribute.getType().getClassName())) {
					attribute.setFkModel(model);
				}
			}
		}
		
		for(Model model : models) {
			for (Attribute attribute : model.getAttributes()) {
				
				if(attribute.getType().getSubtype() == null && attribute.isFk()) {
					attribute.getType().setSubtype("FK");
				}
				
				if(attribute.isPk()) {
					attribute.setColumnName("id_" + attribute.getModel().getTableName());
				} else if(attribute.getType().isCollection()){
					attribute.setColumnName(null);
				} else if(attribute.isFk()){
					attribute.setColumnName("id_" + attribute.getName().getUnderlineCase().toLowerCase());
				}else {
					attribute.setColumnName(attribute.getName().getUnderlineCase().toLowerCase());
				}

			}
		}
		
	}
	
	private Integer setSize(Attribute attribute) {
		if("ENUM".equals(attribute.getType().getSubtype()) ) {
			String[] enumValues = attribute.getType().getEnumValues();
			int maxlength = 0;
			for (String string : enumValues) {
				if(string.length() > maxlength) {
					maxlength = string.length(); 
				}
			}
			return maxlength;
		}
		if(!"TXT".equals(attribute.getType().getSubtype()) ) {
			attribute.setSize(null);
		}
		return attribute.getSize();
	}
}
