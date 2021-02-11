package com.logger;

import com.logger.utils.LoggerConfigParser;
import com.logger.utils.LoggerRecordHandler;
import com.logger.utils.misc.DateUtils;

/**
 *
 * @author hikingcarrot7
 */
public class Logger {

    private static Logger instance;

    private final LoggerConfigInfo configInfo;
    private final LoggerRecordHandler loggerRecordHandler;

    public static Logger getLogger() {
        if (instance == null)
            instance = new Logger();

        return instance;
    }

    private Logger() {
        this(new LoggerConfigParser());
    }

    private Logger(LoggerConfigParser configParser) {
        configInfo = configParser.parseJsonConfig();
        loggerRecordHandler = new LoggerRecordHandler(configInfo);
    }

    public void log(String msg) {
        if (configInfo.printDate()) {
            logWithCurrentDate(msg);
            return;
        }

        loggerRecordHandler.recordLog(msg);
    }

    private void logWithCurrentDate(String msg) {
        String log = String.format("%s | %s | %s",
                DateUtils.getCurrentDate(),
                DateUtils.getCurrentHour(),
                msg);

        loggerRecordHandler.recordLog(log);
    }

}
