package src.Main;

public class Tim {
    Thread timerThread;
    double timeElapsed = 0;
    double targetTime;

    public Tim(double amountOfTime) {
        targetTime = amountOfTime;

    }

    public boolean start() {
        if (timeElapsed > 0)
            return false;
        else {
            timeIT();

            return true;
        }

    }

    private void timeIT() {
        timerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                    timeElapsed += 0.1;
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (targetTime - timeElapsed <= 0) {
                    timeElapsed = -1;
                    break;
                }
            }
        });
        timerThread.start();

    }

    public void stop() {
        timeElapsed = 0;
    }

    public double getTimeElapsed() {
        return timeElapsed;
    }

    public boolean timerCompleted() {
        if (timeElapsed == -1)
            return true;
        else
            return false;
    }
}
