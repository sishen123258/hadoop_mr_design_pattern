package storm.trident;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storm.trident.spout.ITridentSpout;

import java.io.Serializable;

/**
 * Created by Tong on 2016/2/2.
 */
public class DefaultCoordinator implements ITridentSpout.BatchCoordinator<Long>,Serializable {

    private static final Logger LOGGER= LoggerFactory.getLogger(DefaultCoordinator.class);

    @Override
    public Long initializeTransaction(long txid, Long prevMetadata, Long currMetadata) {
        LOGGER.info("initializeTransaction"+txid);
        return null;
    }

    @Override
    public void success(long txid) {
        LOGGER.info("success"+txid);
    }

    @Override
    public boolean isReady(long txid) {
        return true;
    }

    @Override
    public void close() {

    }
}
