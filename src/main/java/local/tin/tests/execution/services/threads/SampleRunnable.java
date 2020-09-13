package local.tin.tests.execution.services.threads;

import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class SampleRunnable implements Runnable {
 
    private static final Logger LOGGER = Logger.getLogger(SampleCallable.class);
    private final long sleepTime;
    private final String taskName;

    public SampleRunnable(String taskName, long sleepTime) {
        this.taskName = taskName;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        LOGGER.info("I'm " + taskName + " of " +  getClass().getSimpleName() + " and will sleep for " + sleepTime + "ms");
        try {
            Thread.sleep(sleepTime);
            LOGGER.info("I'm " + taskName + " of " +  getClass().getSimpleName() + " and I woke up at " + new Date());
        } catch (InterruptedException ex) {
            LOGGER.error("Unexpected InterruptedException at " + taskName + " of " +  getClass().getSimpleName() + " because " + ex.getLocalizedMessage());
        }
    }
}
