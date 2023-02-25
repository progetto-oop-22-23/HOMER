package homer.api;

import java.util.Set;

public interface Controller {

    /**
     * Connects a new device to the controller.
     * @param device The device to connect.
     */
    void connectDevice(Device<?> device);

    /**
     * Returns a set containing the information of each device to be displayed visually.
     * @return The devices connected to the controller.
     */
    Set<DeviceView<?>> getDevices();

    /**
     * Command an update to the given device.
     * @param device The device to command.
     */
    void updateDevice(DeviceView<?> device);

}
