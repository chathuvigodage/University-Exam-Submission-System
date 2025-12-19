import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewSubmissionSystem {
    private final int numberOfStudents;
    private final int poolSize;
    private final SubmissionStats stats = new SubmissionStats();


    public NewSubmissionSystem(int poolSize, int numberOfStudents) {
        this.poolSize = poolSize;
        this.numberOfStudents = numberOfStudents;
    }


    public void processSubmissions() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        CountDownLatch latch = new CountDownLatch(numberOfStudents);


        long startTime = System.nanoTime();


        for (int i = 1; i <= numberOfStudents; i++) {
            final Student student = new Student(i);


            executor.submit(() -> {
                try {
                    long time = student.submitExam();
                    stats.recordSuccess(time);
                    System.out.println("[SUCCESS] " + student.getName() + " in " + time + " ms");
                } catch (Exception e) {
                    stats.recordFailure();
                    System.out.println("[FAILED ] " + student.getName());
                } finally {
                    latch.countDown();
                }
            });
        }


        latch.await();
        long endTime = System.nanoTime();
        executor.shutdown();


        long wallClockMs = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        stats.printStats(wallClockMs);
    }
}
