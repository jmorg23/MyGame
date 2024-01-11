package src.Enviorment.EnviornmentControl;

import src.Accessories.Tick;
import src.Enviorment.EnviornmentControl.Parents.Enviornment;
import src.Enviorment.puzzles.Childs.Door;
import src.Enviorment.puzzles.Parents.Sensor;
import src.Main.ControlPanel;

public class Enviornments {
    private int area = 1;
    private String envioUrl = "res/im/sNight.png";
    private static Enviornment env1;
    private static int fps = 60;
    private static int curEnviornment = 0;

    //time per second
    public double timeSpeed = 100;


    //this will be the game day time
    public static double timeOfDay = 0;

    // target platform speed where default is 50 at 60fps
    private static double targetSpeed = (50 * 60);

    public Enviornments(int f, double targetPlatSpeed) {
        targetSpeed = targetPlatSpeed * 60;
        // fps = f;
        System.out.println("fps: "+fps);
        setE();

        eTick.start();
    }
    public void runTime(boolean b){
        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeOfDay += timeSpeed/3600;
                System.out.println(timeSpeed/3600);
            }
        }).start();
    }
    public void setE() {
 
        if (area == 1) {

            curEnviornment = 1;
            env1 = new Enviornment(envioUrl);
            env1.addRain(true);
            env1.addPlatform(new Platforms(area));
            env1.addSensor(new Sensor("res/im/redB.png", 800, 400));
            env1.addActivationObject(new Door("res/im/Door.png", 900, 400, 2, 2));
            env1.setVis(true);
            addComponentToMain(env1);
            runTime(true);
        }
           

    }
    public void addComponentToMain(Enviornment e){
        ControlPanel.addComp(e);
    }
    public static Tick eTick = new Tick(fps, () -> {
        if (curEnviornment == 1) {
            env1.repaint();
            env1.scroll(targetSpeed / fps);
        }

    });

    public void updateArea(int a) {
        area = a;
    }

}
