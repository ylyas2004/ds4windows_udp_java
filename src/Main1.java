import com.fazecast.jSerialComm.SerialPort;
import net.java.games.input.*;

public class Main1 {
    public static void main(String[] args) {
        Controller ps4Controller = findPS4Controller();

        if (ps4Controller == null) {
            System.out.println("PS4 controller not found.");
            return;
        }

        SerialCommunication arduino = new SerialCommunication("COM3"); // Adjust the port name for your Arduino

        EventQueue eventQueue = ps4Controller.getEventQueue();
        Event event = new Event();

        while (true) {
            ps4Controller.poll();

            while (eventQueue.getNextEvent(event)) {
                String command = mapControllerInputToCommand(event);
                arduino.sendCommand(command);
            }

            delay(10);
        }
    }

    private static Controller findPS4Controller() {
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] controllers = ce.getControllers();

        for (Controller controller : controllers) {
            if (controller.getName().contains("Wireless Controller")) {
                return controller;
            }
        }

        return null;
    }

    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String mapControllerInputToCommand(Event event) {
        Component component = event.getComponent();
        Component.Identifier id = component.getIdentifier();
        float value = event.getValue();

        String commandPrefix = "led" + id.getName();
        return commandPrefix + ((value == 1.0f) ? "1" : "0");
    }
}
