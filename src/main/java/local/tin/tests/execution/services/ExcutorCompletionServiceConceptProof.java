package local.tin.tests.execution.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import local.tin.tests.execution.services.threads.SampleCallable;
import org.apache.log4j.Logger;

/**
 *
 * @author benitodarder
 */
public class ExcutorCompletionServiceConceptProof {

    private static final Logger LOGGER = Logger.getLogger(ExcutorCompletionServiceConceptProof.class);
    private static final int THREADPOOL_SIZE = 4;
    private static final int NUMBER_OF_TASKS = 10;
    private static final long MAX_SLEEPING_TIME = 5000;        
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        LOGGER.info("Let's create some callables and run them through ExecutorCompletionService");
        LOGGER.info("Thread pool size: " + THREADPOOL_SIZE + "; number of tasks: " + NUMBER_OF_TASKS + "; max. sleeping time: " + MAX_SLEEPING_TIME);               
        List<SampleCallable> callables = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            long sleepTime = (long) (Math.random() * (MAX_SLEEPING_TIME + 1));
            SampleCallable newCallable = new SampleCallable(String.valueOf(i), sleepTime);
            callables.add(newCallable);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREADPOOL_SIZE);
        CompletionService<String> executorCompletionService= new ExecutorCompletionService<>(executorService);        
        LOGGER.info("Let's submit them to the ExecutorService");
        long t0 = System.currentTimeMillis();
        List<Future<String>> futuresList = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_TASKS; i++) {
            futuresList.add(executorCompletionService.submit(callables.get(i)));
        }
        LOGGER.info("Let's wait for ExecutorService to end.");
        for(Future current : futuresList) {
            LOGGER.info(executorCompletionService.take().get());
        }
        executorService.shutdown();
        LOGGER.info("Execution time: " + (System.currentTimeMillis() - t0));
    }


    
}
