public class ExamSubmissionSimulator {
    public static void main(String[] args) throws InterruptedException {
        int cpu = Runtime.getRuntime().availableProcessors();
        int poolSize = cpu * 2;
        int students = 5000; // change to 100000 for stress test


        System.out.println("CPU cores : " + cpu);
        System.out.println("Pool size : " + poolSize);
        System.out.println("Students : " + students);


        NewSubmissionSystem system = new NewSubmissionSystem(poolSize, students);
        system.processSubmissions();
    }
}
