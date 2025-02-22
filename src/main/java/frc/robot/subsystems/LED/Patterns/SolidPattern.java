package frc.robot.subsystems.LED.Patterns;

import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.util.Color;

public class SolidPattern {
    public static LEDPattern getSolidPattern(Color color) {
        return LEDPattern.solid(color);
    }
}
