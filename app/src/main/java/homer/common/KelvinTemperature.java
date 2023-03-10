package homer.common;

/**
 * Temperature implementation that uses kelvin as a base measurement unit.
 */
public final class KelvinTemperature implements Temperature {

    private double temperature;

    /**
     * 
     * @param temperature
     */
    public KelvinTemperature(final double temperature) {
        this.temperature = temperature;

    }

    @Override
    public double getKelvin() {
        return temperature;
    }

    @Override
    public double getFarenheit() {
        return 1 / Temperature.FIVE_NINES * (temperature - Temperature.DELTA_KELVIN_CELSIUS) + Temperature.DELTA_KELVIN_FARENHEIT;
    }

    @Override
    public double getCelsius() {
        return temperature - Temperature.DELTA_KELVIN_CELSIUS;
    }

}
