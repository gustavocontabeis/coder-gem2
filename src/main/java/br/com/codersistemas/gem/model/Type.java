package br.com.codersistemas.gem.model;

import br.com.codersistemas.gem.util.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Type {
	
	private boolean primitive, collection, isEnum;
	private String className, simpleClassName, subtype;
	private String[] enumValues;
	private Name name;

}
