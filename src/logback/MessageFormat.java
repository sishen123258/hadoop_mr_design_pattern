package logback;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Created by Tong on 2016/2/4.
 */
public class MessageFormat implements Formatter{
    @Override
    public String format(ILoggingEvent event) {
        return event.getFormattedMessage();
    }
}
