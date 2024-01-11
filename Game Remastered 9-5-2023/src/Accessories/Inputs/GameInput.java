package src.Accessories.Inputs;


public abstract class GameInput {
    
    public boolean isMovingForward, isMovingBackward, isMovingLeft, isMovingRight, isFiring, isAiming, isAccelerating, isHandbraking, isUsingItem;
    private float forwardScale = 1, backwardScale = 1, turnScale = 1;

    public abstract void updateProperties();


    public void setAllStates(boolean state) {
        
        this.isMovingForward = state;
        this.isMovingBackward = state;
        this.isMovingLeft = state;
        this.isMovingRight = state;
        this.isFiring = state;
        this.isAiming = state;
        this.isAccelerating = state;
        this.isHandbraking = state;
        this.isUsingItem = state;
    }


    public float getForwardScale() {
        return this.forwardScale;
    }
    public float getBackwardScale() {
        return this.backwardScale;
    }
    public float getTurnScale() {
        return this.turnScale;
    }
 
    
}
