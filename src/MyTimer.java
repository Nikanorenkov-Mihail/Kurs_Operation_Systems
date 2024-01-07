import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    int seconds;
    public MyTimer(){

    }
    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            int secondsPassed = 0;
            int durationInSeconds = 1;

            @Override
            public void run() {
                if (secondsPassed < durationInSeconds) {
                    System.out.println("Seconds passed: " + secondsPassed);
                    secondsPassed++;
                } else {
                    System.out.println("Timer is finished!");
                    timer.cancel();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
