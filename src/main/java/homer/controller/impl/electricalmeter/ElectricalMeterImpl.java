package homer.controller.impl.electricalmeter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.model.outlets.Outlet;

/**
 * Implements {@link homer.controller.api.electricalmeter.ElectricalMeter}.
 * 
 * @author Alessandro Monticelli
 */
public final class ElectricalMeterImpl implements ElectricalMeter {
    private List<Outlet> outlets = new ArrayList<>();
    private double globalConsumption;
    private static final double MAX_GLOBAL_CONSUMPTION = 4.0; // kW

    /**
     * Constructor for
     * {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl}.
     * 
     * @param outlets The list of outlets to control.
     */
    public ElectricalMeterImpl(final List<Outlet> outlets) {
        this.globalConsumption = 0.0;
        this.outlets = outlets;
    }

    @Override
    public List<Outlet> getOutlets() {
        return this.outlets;
    }

    @Override
    public void setOutlets(final List<Outlet> outlets) {
        this.outlets = outlets;
    }

    @Override
    public void addOutlet(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        this.outlets.add(outlet);
    }

    @Override
    public void removeOutlet(final Outlet outlet) throws IllegalArgumentException {
        Objects.requireNonNull(outlet);
        if (this.outlets.contains(outlet)) {
            this.outlets.remove(outlet);
        } else {
            throw new IllegalArgumentException(outlet.getInfo() + " not in 'outlets'");
        }
    }

    @Override
    public void computeConsumption() {
        this.globalConsumption = outlets.stream()
                .mapToDouble(Outlet::getState)
                .sum();
    }

    private void sortForConsumption() {
        this.outlets.sort((o1, o2) -> o2.getState().compareTo(o1.getState()));
    }

    @Override
    public void cutPowerTo(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        outlet.unplug();
    }

    @Override
    public void checkConsumption() {
        this.sortForConsumption();
        int i = 0;
        while (this.getGlobalConsumption() >= ElectricalMeterImpl.MAX_GLOBAL_CONSUMPTION) {
            this.cutPowerTo(this.outlets.get(i));
            i++;
        }
    }

    @Override
    public double getGlobalConsumption() {
        this.computeConsumption();
        return this.globalConsumption;
    }
}
