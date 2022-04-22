package br.com.codersistemas.gem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Model {
	
	private App app;
	private List<Attribute>attributes;
	private Name name;
	private String label, className, restURI, tableName;
	
	public Attribute getPk() {
		Optional<Attribute> findFirst = attributes.stream().filter(f->f.isPk()).findFirst();
		if(findFirst.isPresent()) {
			return findFirst.get();
		} else {
			return null;
		}
	}
	
	public List<Attribute>getAttributesFk(){
		return attributes.stream().filter(a->a.isFk()&&!a.getType().isCollection()).collect(Collectors.toList());
	}

	public List<Attribute>getAttributesFkUnique(){
		Map<String, Attribute> attributes = new HashMap<>();
		for (Attribute attribute : this.attributes) {
			if(attribute.isFk()&&!attribute.getType().isCollection()) {
				if(!attributes.containsKey(attribute.getType().getClassName())) {
					attributes.put(attribute.getType().getClassName(), attribute);
				}
			}
		}
		return new ArrayList<>(attributes.values());
	}

	public List<Attribute>getAttributesNotCollection(){
		return attributes.stream().filter(a->!a.getType().isCollection()).collect(Collectors.toList());
	}

}
