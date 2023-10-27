import com.fazecast.jSerialComm.SerialPort;
import net.java.games.input.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Get the default controller environment
        ControllerEnvironment ce = ControllerEnvironment.getDefaultEnvironment();
        Controller[] controllers = ce.getControllers();

        Controller ps4Controller = null;

        // Find the PS4 controller by name
        for (Controller controller : controllers) {
            if (controller.getName().contains("Wireless Controller")) {
                ps4Controller = controller;
                break;
            }
        }

        if (ps4Controller == null) {
            System.out.println("PS4 controller not found.");
            return;
        }

        EventQueue eventQueue = ps4Controller.getEventQueue();
        Event event = new Event();

        //SerialCommunication arduino = new SerialCommunication("COM3"); // Adjust the port name for your Arduino

        while (true) {
            ps4Controller.poll();

            Component components[] = ps4Controller.getComponents();
            for (Component c: components
                 ) {
                System.out.println(c.getName() + " : " + c.getIdentifier() + " : " + c.getPollData());
            }

//            while (eventQueue.getNextEvent(event)) {
//                Component component = event.getComponent();
//                Component.Identifier id = component.getIdentifier();
//                float value = event.getValue();
//
//                String command = mapControllerInputToCommand(id.getName(), value);
//                System.out.println(id.getName() + " : " + value);
//                System.out.println("Sending command: " + command);
//                arduino.sendCommand(command);
//                try {
//                    // Read and print the response from the Arduino
//                    String response = arduino.readResponse();
//                    System.out.println(response);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }

            // Delay to avoid high CPU usage
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String mapControllerInputToCommand(String inputName, float value) {
        // Customize the mapping based on your needs
        String command = "led" + inputName + ((value == 1.0f) ? "1" : "0");
        return command;
    }
}

class SerialCommunication {
    private final SerialPort arduinoPort;

    public SerialCommunication(String portName) {
        arduinoPort = SerialPort.getCommPort(portName);
        if (!arduinoPort.openPort()) {
            System.err.println("Failed to open the serial port.");
            System.exit(1);
        }
        arduinoPort.setComPortParameters(9600, 8, 1, 0);
        arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public void sendCommand(String command) {
        try {
            arduinoPort.getOutputStream().write((command + "\n").getBytes()); // Send a newline-terminated command
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readResponse() throws IOException {
        byte[] buffer = new byte[64]; // Adjust the buffer size as needed
        int bytesRead = arduinoPort.getInputStream().read(buffer);
        return new String(buffer, 0, bytesRead);
    }

    public void close() {
        arduinoPort.closePort();
    }
}
