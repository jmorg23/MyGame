package src.Accessories.Inputs;

/*
 * Tanks Multiplayer
 * game.Input.GamePadKeyboard
 * by: SR & LR
 * build: 5-2-23
 */
import com.studiohartman.jamepad.ControllerAxis;
import com.studiohartman.jamepad.ControllerButton;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerUnpluggedException;

/**
 * <strong> GamePadKeyboard </strong>
 * <p>
 * Has default keybinds
 */
public class GamePadKeyboard extends GameInput {
    // Controller
    ControllerManager controllers;
    // STATIC KEY BINDINGS
    private static ControllerAxis forward = ControllerAxis.valueOf("TRIGGERRIGHT");
    private static ControllerAxis backward = ControllerAxis.valueOf("TRIGGERLEFT");
    private static ControllerAxis LEFTY = ControllerAxis.valueOf("LEFTY");
    private static ControllerAxis leftX = ControllerAxis.valueOf("LEFTX");


    private static ControllerAxis MouseX = ControllerAxis.valueOf("RIGHTX");
    private static ControllerAxis MouseY = ControllerAxis.valueOf("RIGHTY");

    private static ControllerButton start = ControllerButton.valueOf("START");

    private static ControllerButton fire = ControllerButton.valueOf("RIGHTBUMPER");

    // private static ControllerButton fire =
    // ControllerButton.valueOf("RIGHTBUMPER");
    // X
    boolean isInteracting;
    boolean toggleInventory;
    boolean isJumping;
    boolean isPausing;
    float lefty;
    float leftx;
    float mx;
    float my;

    private static ControllerButton jump = ControllerButton.valueOf("A");
    // Triangle
    private static ControllerButton inventory = ControllerButton.valueOf("Y");
    // Circle
    private static ControllerButton handbrake = ControllerButton.valueOf("B");
    // Square
    private static ControllerButton interact = ControllerButton.valueOf("X");
    private static ControllerButton back = ControllerButton.valueOf("BACK");
    // CONTROLLER CONTROL
    private int controllerIndex;
    private int previousControllerCount;
    private int currentControllerCount;
    private int controllersStart;
    private int conts;
    ControllerIndex currController;

    // STATIC CONTROLLER CONTROL
    private static float DEADZONE = 0.5f;

    // CONSTRUCTOR
    public GamePadKeyboard(ControllerManager controllerManager, int controllerIndex) {
        // Controllers
        controllers = controllerManager;
        previousControllerCount = controllers.getNumControllers();
        currentControllerCount = controllers.getNumControllers();
        controllersStart = controllers.getNumControllers();
        // index
        this.controllerIndex = controllerIndex;
    }

    //

    @Override
    public void updateProperties() {
        // startGamePadTick();

        try {
            previousControllerCount = controllers.getNumControllers();
            // GET CURRENT CONTROLLER
            currController = controllers.getControllerIndex(controllerIndex);
            controllers.update(); // If using ControllerIndex, you should call update() to check if a new
                                  // controller
            currentControllerCount = controllers.getNumControllers();
            if (previousControllerCount > currentControllerCount) {
                // STOP GAME
                System.out.println("Controller Disconnected");
                currController.close();

            } else if (previousControllerCount < currentControllerCount) {
                currController = controllers.getControllerIndex(controllerIndex);
                System.out.println("Controller Connected");
                if (controllers.getNumControllers() == controllersStart) {
                }
            }

            // UPDATE KEY PRESSES & SCALARS
            if (currController != null && currController.isConnected()) { // Check if a controller is connected
                // GET MOTION SCALES
                float forwardVal = currController.getAxisState(forward);
                float backwardVal = currController.getAxisState(backward);
                float turnVal = currController.getAxisState(leftX);
                // MOVE FORWARD
                if (exceedsDeadzone(forwardVal, DEADZONE)) {
                    float forwardScale = deadzoneScalar(forwardVal, DEADZONE);
                    isMovingForward = true;
                } else {
                    isMovingForward = false;
                }
                // MOVE BACKWARD
                if (exceedsDeadzone(backwardVal, DEADZONE)) {
                    float backwardScale = deadzoneScalar(backwardVal, DEADZONE);
                    isMovingBackward = true;
                } else {
                    isMovingBackward = false;
                }
                // TURN
                if (exceedsDeadzone(turnVal, DEADZONE)) {
                    // APPLY SCALE
                    float turnScale = deadzoneScalar(turnVal, DEADZONE);
                    if (turnVal > 0) {
                        // TURN RIGHT
                        isMovingRight = true;
                        isMovingLeft = false;
                    } else if (turnVal < 0) {
                        // TURN LEFT
                        isMovingLeft = true;
                        isMovingRight = false;
                    }
                } else {
                    isMovingRight = false;
                    isMovingLeft = false;
                }

                // HANDBRAKE
                isHandbraking = currController.isButtonPressed(handbrake);

                // FIRE
                isFiring = (currController.isButtonPressed(fire));

                // ACCELERATOR
                isAccelerating = currController.isButtonPressed(start);

                // AIM
                isAiming = currController.isButtonPressed(inventory);

                // USE ITEM
              // a = currController.isButtonPressed(interact);

                // GO BACK
                if (currController.isButtonPressed(back)) {
                    // controllers.quitSDLGamepad(); // ONLY WHEN GAME ENDS
                    // gameP.window.setPanel("titlePanel");
                }
            }
        } catch (ControllerUnpluggedException e) {
        }
    }

    public void checkButtons() {
        try {
            controllers.update();
            isFiring = (currController.isButtonJustPressed(fire));
            isJumping = (currController.isButtonJustPressed(jump));
            isPausing = (currController.isButtonJustPressed(start));
            isInteracting = (currController.isButtonJustPressed(interact));
            toggleInventory = (currController.isButtonJustPressed(inventory));


            leftx = (currController.getAxisState(leftX));
            lefty = (currController.getAxisState(LEFTY));

            mx = (currController.getAxisState(MouseX));
            my = (currController.getAxisState(MouseY));
            conts = 1;

        } catch (ControllerUnpluggedException e) {
            conts = 0;
        }

    }

    public boolean getisFiring() {
        return isFiring;
    }
    public double getMX(){
        return mx;
    }
    public double getMY(){
        return my;
    }
    public boolean isInteracting(){
        return isInteracting;
    }
        public boolean getInv() {
        return toggleInventory;
    }
    public boolean getJump(){
        return isJumping;
    }
    public int getConts(){
        return conts;
    }
    public boolean isPausing(){return isPausing;}
    public double getLX() {
        return leftx;
    }    public double getLY() {
        return lefty;
    }


    /**
     * Scales rawScalar within range of the rawScalar - deadzone. So any rawScalar
     * input less than the deadzone
     * will return 0, and any value above the deadzone will be scaled between the
     * return 0 to 1 value.
     * 
     * @param rawScalar [0..1] scale of value from 0 to 1
     * @param deadzone  [0..1] amount of deadzone to not respond to rawScalar
     */
    public static float deadzoneScalar(float rawScalar, float deadzone) {
        rawScalar = Math.abs(rawScalar);
        float outScalar = 0.0f;
        if (rawScalar >= deadzone) {
            float delta = rawScalar - deadzone;
            float scale = 1 - deadzone;
            outScalar = delta / scale;
        }
        return outScalar;
    }

    public static boolean exceedsDeadzone(float rawScalarMagnitude, float deadzone) {
        return (Math.abs(rawScalarMagnitude) >= deadzone);
    }

    @Override
    public String toString() {
        return "GamePad " + controllerIndex;
    }

    /**
     * Transfers the focus that this panel was originally set to so keys will still
     * work.
     */
    public void transferOwner(ControllerManager controllerManager) {
        this.controllers = controllerManager;
    }
}