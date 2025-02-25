package frc.robot.subsystems.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.LED.Patterns.RainbowPattern;

public class LED extends SubsystemBase {
    private static final int kPort = 0;
    private static final int kLength = 90;

    private final AddressableLED led;
    private final AddressableLEDBuffer ledBuffer;
    private LEDPattern currentPattern;
    private LEDMode mode;
    private Runnable manualAnimation = null;

    public LED() {
        led = new AddressableLED(kPort);
        ledBuffer = new AddressableLEDBuffer(kLength);
        led.setLength(kLength);
        led.start();

        mode = LEDMode.PATTERN;
    }

    // Use a built-in pattern; disables manual mode
    public void setPattern(LEDPattern pattern) {
        this.currentPattern = pattern;
        this.mode = LEDMode.PATTERN;
        this.manualAnimation = null;
    }

    public void setLEDMode(LEDMode mode) {
        this.mode = mode;
    }

    public LEDMode getMode() {
        return mode;
    }

    public AddressableLEDBuffer getBuffer() {
        return ledBuffer;
    }

    public void setManualAnimation(Runnable animation) {
        this.currentPattern = null;
        this.mode = LEDMode.MANUAL;
        this.manualAnimation = animation;
    }

    @Override
    public void periodic() {
        
        if (mode == LEDMode.MANUAL && manualAnimation != null) {
            System.out.println("Manual mode");
            manualAnimation.run();
        } else if (mode == LEDMode.PATTERN && currentPattern != null) {
            System.out.println("Pattern mode");
            currentPattern.applyTo(ledBuffer);
        }
        led.setData(ledBuffer);
    }

    private static LED instance;
    public static LED getInstance() {
        if (instance == null) {
            instance = new LED();
        }
        return instance;
    }
}
