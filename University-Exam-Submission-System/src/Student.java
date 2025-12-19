import java.util.Random;

public class Student {
    private final int id;
    private final String name;
    private final Random random = new Random();


    public Student(int id) {
        this.id = id;
        this.name = "Student-" + id;
    }


    // Simulate submission
    public long submitExam() throws Exception {
        int processingTime = 10 + random.nextInt(190); // 10–200 ms
        Thread.sleep(processingTime);


// 5% failure rate
        if (random.nextInt(100) < 5) {
            throw new Exception("Submission failed");
        }
        return processingTime;
    }


    public String getName() {
        return name;
    }
}
