package logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.Formatter;

/**
 * Created by root on 2/4/16.
 */
public class KafkaAppender extends AppenderBase<ILoggingEvent>{


    private String topic;
    private String zookeeperHost;
    private Formatter formatter;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

    }

}
