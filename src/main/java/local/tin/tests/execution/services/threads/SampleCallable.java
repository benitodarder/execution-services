package local.tin.tests.execution.services.threads;

import java.util.concurrent.Callable;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class SampleCallable implements Callable {
 
    private static final Logger LOGGER = Logger.getLogger(SampleCallable.class);
    private final long sleepTime;
    private final String taskName;

    public SampleCallable(String taskName, long sleepTime) {
        this.taskName = taskName;
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        long t0 = System.currentTimeMillis();
        LOGGER.info("\tI'm " + taskName + " of " +  getClass().getSimpleName() + " started at " + t0);
        Thread.sleep(sleepTime);
        LOGGER.info("\tI'm " + taskName + " of " +  getClass().getSimpleName() + " and slept for " + sleepTime + "ms");
        return "I'm " + taskName + " of " +  getClass().getSimpleName() + " started at " + t0 + " and I slept for " + sleepTime + "ms";
    }
}
