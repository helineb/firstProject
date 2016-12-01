/**
 * 
 */
package model.jdbc;

import java.util.Properties;

/**
 * @author bmartin
 * created on 3 févr. 2015
 * @version Jbdc v1.0
 */
public class Settings {
	private static Properties properties;
	static {
		try {
			properties = new Properties();
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		String parametre = properties.getProperty(key,null);
		return parametre;
	}

}
