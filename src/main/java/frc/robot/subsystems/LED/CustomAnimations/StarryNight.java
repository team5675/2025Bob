package frc.robot.subsystems.LED.CustomAnimations;

import frc.robot.subsystems.LED.LED;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class StarryNight {
    private final LED led;
    private final Random random = new Random();
    private final ArrayList<Star> stars = new ArrayList<>();

    public StarryNight() {
        led = LED.getInstance();
        led.setManualAnimation(this::update);
    }

    
    private class Star {
        int index;
        double phase;   
        double speed;  

        Star(int index, double speed) {
            this.index = index;
            this.phase = 0;
            this.speed = speed;
        }
    }

    
    public void update() {
        for (int i = 0; i < led.getBuffer().getLength(); i++) {
            led.getBuffer().setRGB(i, 0, 25, 115);
        }

        Iterator<Star> iterator = stars.iterator();
        while (iterator.hasNext()) {
            Star star = iterator.next();
            star.phase += star.speed;
            if (star.phase >= Math.PI) {
                iterator.remove();
            } else {
                double brightness = Math.sin(star.phase);
                int brightVal = (int) (brightness * 255);
                led.getBuffer().setRGB(star.index, brightVal, brightVal, 0);
            }
        }

        if (random.nextDouble() < 0.02) {
            int index = random.nextInt(led.getBuffer().getLength());
            double speed = 0.05 + random.nextDouble() * 0.02; // Speed between 0.05 and 0.07 radians per update
            stars.add(new Star(index, speed));
        }
    }
}
