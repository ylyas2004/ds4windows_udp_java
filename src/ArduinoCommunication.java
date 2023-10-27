import com.fazecast.jSerialComm.SerialPort;

import java.util.Scanner;
import java.io.IOException;

public class ArduinoCommunication {
    public static void main(String[] args) {
        // Find and open the Arduino's serial port
        SerialPort arduinoPort = SerialPort.getCommPort("COM3"); // Adjust the port name for your Arduino

        if (arduinoPort.openPort()) {
            arduinoPort.setComPortParameters(9600, 8, 1, 0); // Match the Arduino's baud rate and settings
            arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

            String command = "";

            Scanner scanner = new Scanner(System.in);

            while (!command.equals("exit")) {
                // Read a command from the user
                System.out.print("Enter a command (or 'exit' to quit): ");
                command = scanner.nextLine();

                try {
                    // Send the command to the Arduino
                    arduinoPort.getOutputStream().write(command.getBytes());

                    // You can add a delay here if needed
                    // Thread.sleep(1000); // 1-second delay, for example
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // Close the serial port
            arduinoPort.closePort();
        } else {
            System.err.println("Failed to open the serial port.");
        }
    }
}
