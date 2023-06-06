package adapters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerAdapter {
    public static Logger getLogger(Class context){
        return LogManager.getLogger(context);
    }
    public static void logError(Logger logger, String msg){
        logger.error(msg);
    }
}
