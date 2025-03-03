package frc.robot.subsystems.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LED extends SubsystemBase {
    private static final int port1 = 0;
    private static final int kLength = 90;

    private final AddressableLED led1;
    
    private final AddressableLEDBuffer ledBuffer;
    private LEDAnimation currentAnimation = null;

    private LED() {
        led1 = new AddressableLED(port1);
        led1.setLength(kLength);
        led1.start();
    
        
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

    public void stopPattern() {
        setAnimation(null);
    }

    private static LED instance;
    public static LED getInstance() {
        if (instance == null) {
            instance = new LED();
        }
        return instance;
    }
}