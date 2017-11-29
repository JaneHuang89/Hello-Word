package utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * @author YR
 *
 */
public class Log {
	private final Class<?> clazz;
	Logger logger;
	
	public Log(Class<?> clazz){
		this.clazz = clazz;
		this.logger = LogManager.getLogger(this.clazz);
	}
	
	public void info(String message){
		logger.info(clazz.getCanonicalName() + " : " + message);
	}
	
	public void debug(String message){
		logger.debug(clazz.getCanonicalName() + " : " + message);
	}
	
	public void error(String message){
		logger.error(clazz.getCanonicalName() + " : " + message);
	}
	
	public void warn(String message){
		logger.warn(clazz.getCanonicalName() + " : " + message);
	}
	
	public void fatal(String message){
		logger.fatal(clazz.getCanonicalName() + " : " + message);
	}
	
}
