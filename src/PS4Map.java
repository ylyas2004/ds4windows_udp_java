import java.util.HashMap;
import java.util.Map;

public class PS4Map {
    // Define the PS4 controller map as a class field
    private Map<String, String> ps4ControllerMap = new HashMap<>();

    public PS4Map() {
        // Initialize the map in the constructor with empty second strings
        ps4ControllerMap.put("Triangle", "F747269616E676C6500002C6900000000000");
        ps4ControllerMap.put("Circle", "F636972636C65000000002C6900000000000");
        ps4ControllerMap.put("Cross", "F63726F7373002C6900000000000");
        ps4ControllerMap.put("Square", "F737175617265000000002C6900000000000");
        ps4ControllerMap.put("Up", "F647061647570000000002C6900000000000");
        ps4ControllerMap.put("Down", "F64706164646F776E00002C6900000000000");
        ps4ControllerMap.put("Left", "F647061646C65667400002C6900000000000");
        ps4ControllerMap.put("Right", "F647061647269676874002C6900000000000");
        ps4ControllerMap.put("L1", "F6C31000000002C6900000000000");
        ps4ControllerMap.put("L2", "F6C32000000002C690000000000");
        ps4ControllerMap.put("R1", "F7231000000002C6900000000000");
        ps4ControllerMap.put("R2", "F7232000000002C690000000000");
        ps4ControllerMap.put("Left Thumbstick Button", "F6C33000000002C6900000000000");
        ps4ControllerMap.put("Right Thumbstick Button", "F7233000000002C6900000000000");
        ps4ControllerMap.put("Left Thumbstick H", "F6C78000000002C690000000000");
        ps4ControllerMap.put("Right Thumbstick H", "F7278000000002C690000000000");
        ps4ControllerMap.put("Left Thumbstick V", "F6C79000000002C690000000000");
        ps4ControllerMap.put("Right Thumbstick V", "F7279000000002C690000000000");
        ps4ControllerMap.put("PS Button", "F7073000000002C6900000000000");
        //ps4ControllerMap.put("Touchpad Button", "");
        ps4ControllerMap.put("Options Button", "F6F7074696F6E730000002C6900000000000");
        ps4ControllerMap.put("Share Button", "F7368617265002C6900000000000");
    }

    public Map<String, String> getPS4ControllerMap() {
        return ps4ControllerMap;
    }
}
