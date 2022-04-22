package br.com.codersistemas.gem.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

public class JPAHelper {

	public static boolean isRequired(Field field) {
		Column column = field.getAnnotation(Column.class);
		if(column != null) {
			return !column.nullable();
		}
		JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
		if(joinColumn != null) {
			return !joinColumn.nullable();
		}
		return false;
	}

	public static int getSize(Field field) {
		Column column = field.getAnnotation(Column.class);
		if(column != null) {
			return column.length();
		}
		return 255;
	}

	public static boolean isUnique(Field field) {
		Column column = field.getAnnotation(Column.class);
		if(column != null) {
			return column.unique();
		}
		JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
		if(joinColumn != null) {
			return joinColumn.unique();
		}
		return false;
	}

	public static boolean isPk(Field field) {
		Id id = field.getAnnotation(Id.class);
		if(id != null) {
			return true;
		}
		EmbeddedId embeddedId = field.getAnnotation(EmbeddedId.class);
		if(embeddedId != null) {
			return true;
		}
		return false;
	}

	public static boolean isFk(Field field) {
		return field.getAnnotation(JoinColumn.class) != null
				|| field.getAnnotation(OneToMany.class) != null
				|| field.getAnnotation(ManyToOne.class) != null;
	}

	public static boolean isEntity(Class<?> class1) {
		return class1.getAnnotation(Entity.class) != null;
	}

	public static String collumnName(Field field) {
		Column column = field.getAnnotation(Column.class);
		if(column != null) {
			return column.name(); 
		} else {
			return StringHelper.toUnderlineCase(field.getName()).toLowerCase();
		}
	}

	public static String tableName(Class<?> classe) {
		Table table = classe.getAnnotation(Table.class);
		if(table != null) {
			return table.name(); 
		}
		Entity entity = classe.getAnnotation(Entity.class);
		if(entity != null) {
			return entity.name(); 
		}
		return StringHelper.toUnderlineCase(classe.getSimpleName()).toLowerCase();
	}

}
