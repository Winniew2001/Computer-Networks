package no.ntnu.gr30.sensors;
import java.util.Random;

/**
 * Base class for sensors with maximum and minimum values.
 * Used for simulation.
 */
public abstract class RangeRestrictedSensor implements Sensor{
    private final double min;
    private final double max;
    private double currentValue;
    private static final Random randomGenerator = new Random();


    public RangeRestrictedSensor(double minValue, double maxValue, double startValue){
        this.min = minValue;
        this.max = maxValue;
        this.currentValue = startValue;
    }

    @Override
    public double readValue(){
        changeCurrentValueRandomly();
        return currentValue;
    }

    private void changeCurrentValueRandomly(){
        double delta = generateRandomDelta();
        currentValue += delta;
        if (isCurrentValueOutOfBoundaries()){
            currentValue -= 2 * delta;
        }
    }

    private double generateRandomDelta(){
        double maxDelta = (max - min) / 50.0;
        return randomGenerator.nextDouble(-maxDelta, maxDelta);
    }

    private boolean isCurrentValueOutOfBoundaries() {
        return currentValue > max || currentValue < min;
    }
}
