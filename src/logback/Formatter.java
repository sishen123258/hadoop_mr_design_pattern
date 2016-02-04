package logback;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by Tong on 2016/2/4.
 */
public interface Formatter {
    String format(ILoggingEvent event);
}
