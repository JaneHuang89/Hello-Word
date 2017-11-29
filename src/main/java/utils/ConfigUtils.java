package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author YR
 *
 */
public class ConfigUtils {
	
	Log log = new Log(ConfigUtils.class);
	
	public Properties getProperties(String config) throws IOException {
		Properties properties = new Properties();
		log.info("get the config file" + config);
		try {
			properties.load(new FileInputStream(config));
		} catch (Exception e) {
			log.error("can't find config file");
			log.error(e.getMessage());
		}		
		return properties;
	}
	
	public void setProperties(String config,String key,String value) throws IOException {
		Properties properties = new Properties();
		log.info("find the config file" + config);
		try {
			FileOutputStream oFile = new FileOutputStream(config,true);
			properties.setProperty(key, value);
			properties.store(oFile, "new properties");
			oFile.close();
		} catch (Exception e) {
			log.error("can't find config file");
			log.error(e.getMessage());
		}

	}
}
