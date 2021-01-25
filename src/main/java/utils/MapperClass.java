package utils;

import java.lang.reflect.Field;
import java.util.Map;

public final class MapperClass {

	private MapperClass() {
	}

	public static <T> T mapObject(Class<T> c, Map<String, String> map) {
		T t = null;

		try {
			t = c.getDeclaredConstructor().newInstance();

			Field[] campi = c.getDeclaredFields();

			for (Field campo : campi) {
				boolean accessible = campo.canAccess(t);
				campo.setAccessible(true);
				String type = campo.getType().getName();
				Object o = genericParse(type, map.get(campo.getName()));
				campo.set(t, o);
				campo.setAccessible(accessible);
			}
		} catch (Exception e) {
			return null;
		}

		return t;
	}

	public static Object genericParse(String type, String toParse){
		try {
			if(toParse==null) return nullOf(type);
			switch(type){
				case "int"     : return Integer		.parseInt		(toParse);
				case "float"   : return Float		.parseFloat		(toParse);
				case "long"    : return Long		.parseLong		(toParse);
				case "short"   : return Short		.parseShort		(toParse);
				case "double"  : return Double		.parseDouble	(toParse);
				case "byte"    : return Byte		.parseByte		(toParse);
				case "char"    : return toParse		.charAt			(0);
				case "boolean" : return Boolean		.parseBoolean	(toParse);
				case "java.lang.String" : return toParse;
			}

			return Class.forName(type).getConstructor(String.class).newInstance(toParse); 
		}
		catch (Exception e ) {
			throw new IllegalArgumentException("You need to create a constructor with String argument");
		}
	}

	public static Object nullOf(String type) {
		switch(type){
			case "int"   : case "float" : case "long" :
			case "short" : case "double": case "byte" :
				return 0;
			case "char" : return (char) 0; 
			case "boolean" : return false; 
		}

		return null;
	}
}
