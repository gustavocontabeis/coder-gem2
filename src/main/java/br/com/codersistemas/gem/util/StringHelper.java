package br.com.codersistemas.gem.util;

import org.apache.commons.lang3.StringUtils;

public class StringHelper {

	public static String toUnderlineCase(String string) {
		return string.replaceAll("([a-z])([A-Z]+)", "$1_$2");
	}

	public static String toCase(String string, char character) {
		return string.replaceAll("([a-z])([A-Z]+)", "$1" + character + "$2").toLowerCase();
	}

	public static String capitalize(String string) {
		return StringUtils.capitalize(string);
	}

	public static String uncapitalize(String string) {
		return StringUtils.uncapitalize(string);
	}

	public static String toHifenCase(String string) {
		return toCase(string, '-');
	}
	
	public static String plural(String name) {
		if(!name.toLowerCase().endsWith("s")) {
			if(name.endsWith("cao")) {
				name = StringUtils.replace(name, "cao", "coes");
			} else if(name.endsWith("r")) {
				name += "res";
				name = name.replace("rres", "res");
			} else {
				name = name + "s";
			}			
		}
		return name;
	}

}
