package CarStore;

public class bus extends vehicle{

    private int seatCount;

    public bus(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, int seatCount) {
        super(make, model, year, price, engine, imagePath, doors);
        this.seatCount = seatCount;
    }

    public String getDetails() {
        return "Truck: " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }
}
