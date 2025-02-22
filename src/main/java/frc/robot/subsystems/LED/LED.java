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
    private static LED instance;

    public static LED getInstance() {
        if (instance == null) {
            instance = new LED();
        }
        return instance;
    }

    public LED() {
        led = new AddressableLED(kPort);
        ledBuffer = new AddressableLEDBuffer(kLength);
        led.setLength(kLength);
        led.start();
    }

    public void setPattern(LEDPattern pattern) {
        System.out.println("setting pattern");
        this.currentPattern = pattern;
    }

    @Override
    public void periodic() {
        if (currentPattern == null) return;

        System.out.println("Applying pattern");

        currentPattern.applyTo(ledBuffer);
        led.setData(ledBuffer);
    }
}
