import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class SubmissionStats {
    private final AtomicInteger successful = new AtomicInteger(0);
    private final AtomicInteger failed = new AtomicInteger(0);
    private final LongAdder totalSubmissionTime = new LongAdder();


    public void recordSuccess(long timeMs) {
        successful.incrementAndGet();
        totalSubmissionTime.add(timeMs);
    }


    public void recordFailure() {
        failed.incrementAndGet();
    }


    public void printStats(long wallClockTimeMs) {
        int total = successful.get() + failed.get();
        double successRate = total == 0 ? 0 : (successful.get() * 100.0 / total);


        System.out.println("\n====== FINAL STATISTICS ======");
        System.out.println("Total students processed : " + total);
        System.out.println("Successful submissions : " + successful.get());
        System.out.println("Failed submissions : " + failed.get());
        System.out.println("Total execution time(ms): " + wallClockTimeMs);
        System.out.printf("Success rate : %.2f%%\n", successRate);
        System.out.println("Average submission time : " +
                (successful.get() == 0 ? 0 : totalSubmissionTime.longValue() / successful.get()) + " ms");
    }
}
