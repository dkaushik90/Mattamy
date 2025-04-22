package utilities;

public class WaitUtils {
	
	public static void addDelay() {
        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
	}
	
}
