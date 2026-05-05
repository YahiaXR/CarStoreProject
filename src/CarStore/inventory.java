package CarStore;

import java.util.ArrayList;

public class inventory {

    private ArrayList<vehicle> vehicleList;

    public inventory() {
        this.vehicleList = new ArrayList<>();
    }

    public void addVehicle(vehicle v) {
        this.vehicleList.add(v);
    }

    public ArrayList<vehicle> getVehicleList() {
        return vehicleList;
    }

    public vehicle searchForMe(String model){
        for (vehicle v : vehicleList) {
            if (v.getModel().equalsIgnoreCase(model)) {
                return v;
            }
        }
        return null;
    }


}
