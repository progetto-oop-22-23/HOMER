package homer.model.temperaturechangers;

import java.time.Duration;

import homer.api.DeviceInfo;
import homer.common.temperature.TemperatureFactory;
import homer.model.environment.Environment;

/**
 * Models a heating device.
 */
public final class Heating extends AbstractTemperatureChanger {



    /**
     * @param minIntensity
     * @param maxIntensity
     * @param environment
     * @param info
     */
    public Heating(final double minIntensity, final double maxIntensity, 
    final Environment environment, final DeviceInfo info) {
        super(minIntensity, maxIntensity, environment, info);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double oldTemp = this.getEnvironment().getTemperature().getCelsius();
        final double updatedTemp = oldTemp + this.getState() * deltaTime.toMillis() * this.getScaler();
        final double newTemp = Math.min(this.getMaxTemperature().getCelsius(), updatedTemp);
        this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}
