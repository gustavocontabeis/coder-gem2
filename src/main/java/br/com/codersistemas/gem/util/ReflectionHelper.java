package br.com.codersistemas.gem.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import br.com.codersistemas.gem.model.App;
import br.com.codersistemas.gem.model.Name;
import br.com.codersistemas.gem.model.Type;
import lombok.Builder;

public class ReflectionHelper {

	private static final String BOOLEAN = "BOOLEAN";
	private static final String DATE = "DATE";
	private static final String TXT = "TXT";
	private static final String NUMBER = "NUMBER";
	private static final String COLLECTION = "COLLECTION";
	private static final String ENUM = "ENUM";

	public static Field[] getAllFields(Class classe) {
		List<Field> list = new ArrayList<Field>();
		do {
			Field[] declaredFields = classe.getDeclaredFields();
			for (Field field : declaredFields) {
				if (!field.getName().equals("serialVersionUID"))
					list.add(field);
			}
			classe = classe.getSuperclass();
		} while (classe != Object.class && classe != null);
		return list.toArray(new Field[list.size()]);
	}

	public static Type getType(Field field, App app) {
		
		boolean isCollection = false;
		Class[] classes = {List.class, ArrayList.class, Set.class, HashSet.class, Map.class, HashMap.class};
		for (Class class1 : classes) {
			if(field.getType() == class1) {
				isCollection = true;
				break;
			}
		}
		isCollection = isCollection || field.getType().isArray();
		
		Type type = Type.builder()
				.collection(isCollection)
				.name(new Name(field.getType().getSimpleName()))
				.className(field.getType().getCanonicalName())
				.simpleClassName(field.getType().getSimpleName())
				.primitive(field.getType().isPrimitive())
				.subtype(subtype(field.getType()))
		.build();
		
		if(field.getType().isEnum()) {
			type.setEnum(true);
			extractEnumConstants(field, type);
		}
		
		return type;
	}

	private static boolean isPk(Field field) {
		return JPAHelper.isPk(field);
	}

	private static String subtype(Class<?> type) {
		switch (type.getCanonicalName()) {
		case "boolean":
			return BOOLEAN; 
		case "java.lang.Boolean":
			return BOOLEAN; 
		case "short":
			return NUMBER; 
		case "int":
			return NUMBER; 
		case "long":
			return NUMBER; 
		case "java.lang.Short":
			return NUMBER; 
		case "java.lang.Integer":
			return NUMBER; 
		case "java.lang.Long":
			return NUMBER; 
		case "java.lang.Float":
			return NUMBER; 
		case "java.lang.Double":
			return NUMBER; 
		case "java.math.BigDecimal":
			return NUMBER; 
		case "java.lang.String":
			return TXT;
		case "java.time.LocalDate":
			return DATE;
		case "java.time.LocalDateTime":
			return DATE;
		case "java.util.Date":
			return DATE;
		case "java.sql.Date":
			return DATE;
		case "java.util.List":
			return COLLECTION;
		case "java.util.Set":
			return COLLECTION;
		case "java.util.Map":
			return COLLECTION;
		}
		if(type.isEnum()) {
			return ENUM;
		}
		return null;
	}

	private static void extractEnumConstants(Field field, Type build) {
		Object[] enumConstants = field.getType().getEnumConstants();
		String[] enumConstantsStr = new String[enumConstants.length];
		for (int i = 0; i < enumConstants.length; i++) {
			enumConstantsStr[i] = enumConstants[i].toString();
		}
		build.setEnumValues(enumConstantsStr);
	}

}
