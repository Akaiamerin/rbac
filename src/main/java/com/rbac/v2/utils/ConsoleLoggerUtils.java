package com.rbac.v2.utils;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ConsoleLoggerUtils {
    private static Logger init() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.setPattern("%msg%n");
        patternLayoutEncoder.start();
        ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setEncoder(patternLayoutEncoder);
        consoleAppender.start();
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger(ConsoleLoggerUtils.class.getName());
        logger.addAppender(consoleAppender);
        logger.setAdditive(false);
        return logger;
    }
    private static final Logger logger = init();
    private ConsoleLoggerUtils() {

    }
    public static void log(String message, Object... objects) {
        logger.info(message, objects);
    }
}