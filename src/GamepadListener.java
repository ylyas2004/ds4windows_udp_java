import java.net.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class GamepadListener {
    public static void main(String[] args) {
        int port = 9001; // Replace with the port you want to listen on
        DatagramSocket socket = null;

        PS4Map ps4Map = new PS4Map(); // Create an instance of the PS4Map class

        try {
            socket = new DatagramSocket(port);
            byte[] buffer = new byte[100]; // Adjust the buffer size as needed

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                int dataLength = packet.getLength();
                byte[] data = packet.getData();
                // Extract and print data after the F302 offset
                byte[] dataAfterF302 = Arrays.copyOfRange(data, 0, dataLength);
                String sData = bytesToHex(dataAfterF302);
                System.out.println("Data: " + sData);

                // Map the data to button names and display the input name if it matches
                Map<String, String> ps4ControllerMap = ps4Map.getPS4ControllerMap();
                for (Map.Entry<String, String> entry : ps4ControllerMap.entrySet()) {
                    String buttonName = entry.getKey();
                    String buttonData = entry.getValue();
                    if (sData.startsWith(buttonData)) {
                        String dataWithoutButtonData = sData.substring(buttonData.length()); // Remove buttonData from sData
                        int value = sBytesToInt(dataWithoutButtonData);
                        System.out.println(buttonName + " : " + value);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    // bytesToHex method to convert a byte array to a hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString().substring(43);
    }

    public static int sBytesToInt(String sData){
        // Convert hexString to an integer
        int intValue = Integer.parseInt(sData, 16);
        return intValue;
    }
}
