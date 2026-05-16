package CarStore;

public class truck extends vehicle{

private double bed_size;
private double torque;
    private int doors;
public truck(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, double bed_size, double torque){
    super(make, model, year, price, engine, imagePath, doors);
    this.bed_size = bed_size;
    this.torque = torque;
    this.doors = doors;
}
    public String getDetails() {
        return "Truck " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }
}
