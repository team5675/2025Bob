package frc.robot.subsystems.LED.CustomAnimations;

import frc.robot.subsystems.LED.LED;
import frc.robot.subsystems.LED.LEDAnimation;
import frc.robot.subsystems.LED.RGB;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Glitch implements LEDAnimation {
    private final RGB glitchColor;
    private final int segmentSize;
    private final double cycleTime;
    
    private LED ledSubsystem;
    private AddressableLEDBuffer buffer;
    private List<GlitchSegment> activeSegments = new ArrayList<>();
    private double cycleStartTime;
    private double lastUpdateTime;
    private final Random random = new Random();
    
    private double fadeInTime;
    private double pulseMin;     
    private double pulseMax;     
    private double pulseFrequency;
    
    /**
     * Creates a glitching LED animation with segments of customizable color.
     *
     * @param glitchColor The RGB color for the glitching segments
     * @param segmentSize The number of LEDs per glitched segment
     * @param cycleTime   The time in seconds for the entire glitch cycle to restart
     * @param fadeInTime  The time in seconds for the glitch to fade in
     * @param pulseMin    The minimum brightness (0.0 to 1.0) during the pulse cycle.
     * @param pulseMax    The maximum brightness (0.0 to 1.0) during the pulse cycle.
     * @param pulseFreq   The pulses per second for the glitch
     */
    public Glitch(RGB glitchColor, int segmentSize, double cycleTime, double fadeInTime, double pulseMin, double pulseMax, double pulseFreq) {
        this.glitchColor = glitchColor;
        this.segmentSize = Math.max(1, segmentSize);
        this.cycleTime = Math.max(0.1, cycleTime);
        this.fadeInTime = Math.max(0.0, fadeInTime);
        this.pulseMin = Math.max(0.0, pulseMin);
        this.pulseMax = Math.max(pulseMin, pulseMax);
        this.pulseFrequency = Math.max(0.1, pulseFreq);
    }
    
    @Override
    public void init(LED ledSubsystem) {
        this.ledSubsystem = ledSubsystem;
        this.buffer = ledSubsystem.getBuffer();
        this.lastUpdateTime = Timer.getFPGATimestamp();
        this.cycleStartTime = this.lastUpdateTime;
        
        // Generate initial set of random segments
        generateRandomSegments();
    }
    
    @Override
    public void execute() {
        double currentTime = Timer.getFPGATimestamp();
        double dt = currentTime - lastUpdateTime;
        lastUpdateTime = currentTime;
        
        // Check if it's time for a new cycle
        if (currentTime - cycleStartTime > cycleTime) {
            cycleStartTime = currentTime;
            generateRandomSegments();
        }
        
        // Clear the LED buffer
        clearBuffer();
        
        // Update and render all active segments
        for (GlitchSegment segment : activeSegments) {
            segment.update(currentTime - cycleStartTime, cycleTime);
            segment.render(buffer);
        }
    }
    
    @Override
    public void end() {
        // Clear the LED buffer when animation ends
        if (buffer != null) {
            clearBuffer();
        }
        activeSegments.clear();
    }
    
    // Generate a new set of random segments covering about half the strip
    private void generateRandomSegments() {
        activeSegments.clear();
        
        int stripLength = buffer.getLength();
        int totalSegments = stripLength / segmentSize;
        
        // Target about half the segments to be active
        int targetActive = Math.max(1, totalSegments / 2);
        
        for (int i = 0; i < targetActive; i++) {
            // Generate a random start position that doesn't go off the end of the strip
            int startPos = random.nextInt(stripLength - segmentSize + 1);
            
            // Create and add new segment
            activeSegments.add(new GlitchSegment(startPos, segmentSize));
        }
    }
    
    // Clear all LEDs in the buffer
    private void clearBuffer() {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, 0, 0, 0);
        }
    }
    
    // Represents a single glitching segment
    private class GlitchSegment {
        private final int startIndex;
        private final int length;
        private double brightness = 0.0;
        
        public GlitchSegment(int startIndex, int length) {
            this.startIndex = startIndex;
            this.length = length;
        }
        
        // Update segment brightness based on current cycle time
        public void update(double elapsedTime, double totalCycleTime) {
            if (elapsedTime < fadeInTime) {
                // Fade in phase
                brightness = elapsedTime / fadeInTime;
            } else {
                // Pulse phase
                double pulsePhase = elapsedTime * pulseFrequency;
                // Convert to range between 0 and 1
                double pulseAmount = (Math.sin(pulsePhase * 2 * Math.PI) + 1) / 2;
                // Map to our brightness range
                brightness = pulseMin + pulseAmount * (pulseMax - pulseMin);
                
                // Optional: add some random flicker
                if (random.nextDouble() < 0.05) { // 5% chance per update
                    brightness *= random.nextDouble() * 0.3 + 0.7; // Random factor between 0.7 and 1.0
                }
            }
        }
        
        // Render the segment to the LED buffer
        public void render(AddressableLEDBuffer buffer) {
            for (int i = 0; i < length; i++) {
                int index = startIndex + i;
                
                // Ensure we don't go out of bounds
                if (index >= 0 && index < buffer.getLength()) {
                    // Adjust color based on brightness
                    int r = (int)(glitchColor.r * brightness);
                    int g = (int)(glitchColor.g * brightness);
                    int b = (int)(glitchColor.b * brightness);
                    
                    buffer.setRGB(index, r, g, b);
                }
            }
        }
    }
}