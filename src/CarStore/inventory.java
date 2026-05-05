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
    public void addNewVehicle(vehicle v){
        vehicleList.add(v);
    }
    public void removeVehicle(int x){
        vehicleList.remove(x);
    }
    public vehicle searchByPrice(double price){
        for(vehicle v : vehicleList){
            if(v.getPrice()<=price){
                System.out.println(v.getDetails());
            }
        }
        return null;
    }


}
