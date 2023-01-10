package br.com.codersistemas.gem.model;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Type {
	
	private boolean primitive, collection, isEnum, generic;
	private String className, simpleClassName, subtype;
	private String[] enumValues;
	private Name name;
	private Type genericType;
	private Field field;

}
