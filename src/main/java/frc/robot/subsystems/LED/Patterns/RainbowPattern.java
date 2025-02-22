package frc.robot.subsystems.LED.Patterns;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.LEDPattern;


public class RainbowPattern {
    private static final int SATURATION = 255;
    private static final int VALUE = 128;
    private static final Distance LED_SPACING = Meters.of(1.0 / 120.0);

    public static LEDPattern getScrollingRainbow() {
        LEDPattern baseRainbow = LEDPattern.rainbow(SATURATION, VALUE);
        return baseRainbow.scrollAtAbsoluteSpeed(MetersPerSecond.of(1), LED_SPACING);
    }

    public static LEDPattern getStaticRainbow() {
        return LEDPattern.rainbow(SATURATION, VALUE);
    }
}
