package logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import kafka.producer.Producer;
import kafka.producer.ProducerConfig;

import java.util.Properties;


/**
 * Created by root on 2/4/16.
 */
public class KafkaAppender extends AppenderBase<ILoggingEvent>{


    private String topic;
    private String zookeeperHost;
    private Formatter formatter;
    private Producer<String,String> producer;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getZookeeperHost() {
        return zookeeperHost;
    }

    public void setZookeeperHost(String zookeeperHost) {
        this.zookeeperHost = zookeeperHost;
    }

    public Formatter getFormatter() {
        return formatter;
    }

    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }

    public Producer<String, String> getProducer() {
        return producer;
    }

    public void setProducer(Producer<String, String> producer) {
        this.producer = producer;
    }

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {

    }

    @Override
    public void start() {
        if(formatter==null){
            this.formatter=new MessageFormat();
        }
        super.start();
        Properties properties=new Properties();
        properties.put("zk.connect", this.zookeeperHost);
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config=new ProducerConfig(properties);
        this.producer=new Producer<String, String>(config);

    }

    @Override
    public void stop() {
        super.stop();
        this.producer.close();
    }
}
