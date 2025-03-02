package frc.robot.subsystems.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
    private static final int kPort1 = 0;
    private static final int kPort2 = 9;
    private static final int kLength = 90;

    private final AddressableLED led1;
    //private final AddressableLED led2;
    
    private final AddressableLEDBuffer ledBuffer;
    private LEDAnimation currentAnimation = null;

    private LED() {
        led1 = new AddressableLED(kPort1);
        led1.setLength(kLength);
        led1.start();
        
        // led2 = new AddressableLED(kPort2);
        // led2.setLength(kLength);
        // led2.start();
        
        ledBuffer = new AddressableLEDBuffer(kLength);
    }

    /**
     * Sets the current animation for both LED strips.
     * This will stop any currently running animation.
     * 
     * @param animation The animation to run
     */
    public void setAnimation(LEDAnimation animation) {
        if (currentAnimation != null) {
            currentAnimation.end();
        }
        
        currentAnimation = animation;
        if (currentAnimation != null) {
            currentAnimation.init(this);
        }
    }

    public AddressableLEDBuffer getBuffer() {
        return ledBuffer;
    }

    public int getLength() {
        return kLength;
    }

    @Override
    public void periodic() {
        if (currentAnimation != null) {
            currentAnimation.execute();
        }
        
        led1.setData(ledBuffer);
        //led2.setData(ledBuffer);
    }

    private static LED instance;
    public static LED getInstance() {
        if (instance == null) {
            instance = new LED();
        }
        return instance;
    }
}