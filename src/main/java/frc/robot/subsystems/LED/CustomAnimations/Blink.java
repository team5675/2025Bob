package frc.robot.subsystems.LED.CustomAnimations;

import frc.robot.subsystems.LED.LED;
import frc.robot.subsystems.LED.RGB;
import edu.wpi.first.wpilibj.Timer;

public class Blink {
    private final LED led;
    private final RGB color;
    private final double offTime;
    private final double onTime;
    private double lastUpdateTime;
    private boolean isOn;

    /**
     * Constructs a blinking LED animation with a custom RGB color.
     *
     * @param color The RGB color for the blink effect.
     * @param offTime How long the LEDs stay off before turning on.
     * @param onTime How long the LEDs stay on before turning off.
     */
    public Blink(RGB color, double offTime, double onTime) {
        this.led = LED.getInstance();
        this.color = color;
        this.offTime = offTime;
        this.onTime = onTime;
        this.lastUpdateTime = Timer.getFPGATimestamp();
        this.isOn = false;
        
        led.setManualAnimation(this::update);
    }

    /**
     * Called periodically by the LED subsystem.
     * Updates the LED buffer to blink the entire strip.
     */
    public void update() {
        double now = Timer.getFPGATimestamp();
        double elapsedTime = now - lastUpdateTime;

        if (isOn && elapsedTime >= onTime) {
            turnOff();
            lastUpdateTime = now;
        } else if (!isOn && elapsedTime >= offTime) {
            turnOn();
            lastUpdateTime = now;
        }
    }

    private void turnOn() {
        for (int i = 0; i < led.getBuffer().getLength(); i++) {
            led.getBuffer().setRGB(i, color.r, color.g, color.b);
        }
        isOn = true;
    }

    private void turnOff() {
        for (int i = 0; i < led.getBuffer().getLength(); i++) {
            led.getBuffer().setRGB(i, 0, 0, 0);
        }
        isOn = false;
    }
}
