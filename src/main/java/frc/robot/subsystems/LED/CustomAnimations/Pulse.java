package frc.robot.subsystems.LED.CustomAnimations;

import frc.robot.subsystems.LED.LED;
import frc.robot.subsystems.LED.RGB;
import edu.wpi.first.wpilibj.Timer;

public class Pulse {
    private final LED led;
    private final RGB targetColor;
    private final double minBrightness;
    private final double maxBrightness;
    private final double pulseCycleTime; // Total time (in seconds) for one full pulse cycle
    private double phase = 0.0;
    private double lastUpdateTime = 0.0;

    /**
     * Constructs a pulsing LED animation with custom RGB color.
     *
     * @param red            The red component of the color (0-255).
     * @param green          The green component of the color (0-255).
     * @param blue           The blue component of the color (0-255).
     * @param minBrightness  The minimum brightness (0.0 to 1.0) during the pulse cycle.
     * @param maxBrightness  The maximum brightness (0.0 to 1.0) during the pulse cycle.
     * @param pulseCycleTime The total time in seconds for one full pulse cycle.
     */
    public Pulse(RGB target, double minBrightness, double maxBrightness, double pulseCycleTime) {
        this.led = LED.getInstance();
        this.targetColor = target;
        this.minBrightness = minBrightness;
        this.maxBrightness = maxBrightness;
        this.pulseCycleTime = pulseCycleTime;
        this.lastUpdateTime = Timer.getFPGATimestamp();
        led.setManualAnimation(this::update);
    }

    /**
     * Called periodically by the LED subsystem.
     * Updates the LED buffer to pulse the entire strip.
     */
    public void update() {
        double now = Timer.getFPGATimestamp();
        double dt = now - lastUpdateTime;
        lastUpdateTime = now;

        // Increase phase such that a full cycle (2π) happens in pulseCycleTime seconds.
        phase += (2 * Math.PI * dt / pulseCycleTime);
        phase %= (2 * Math.PI); // Keep phase within [0, 2π)

        // Sine oscillates from -1 to 1; normalize it to [0, 1].
        double brightnessFactor = (Math.sin(phase) + 1) / 2.0;
        // Map the normalized value to the specified brightness range.
        double currentBrightness = minBrightness + (maxBrightness - minBrightness) * brightnessFactor;

        // Apply the pulsing color to every LED.
        for (int i = 0; i < led.getBuffer().getLength(); i++) {
            int adjustedRed = (int) (targetColor.r * currentBrightness);
            int adjustedGreen = (int) (targetColor.g * currentBrightness);
            int adjustedBlue = (int) (targetColor.b * currentBrightness);
            led.getBuffer().setRGB(i, adjustedRed, adjustedGreen, adjustedBlue);
        }
    }
}
