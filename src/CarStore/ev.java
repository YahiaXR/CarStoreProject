package CarStore;

public class ev extends vehicle {


    private double range;
    private double batteryCapacity;

    public ev(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, double range, double batteryCapacity) {
        super(make, model, year, price, engine, imagePath, doors);
        this.range = range;
        this.batteryCapacity = batteryCapacity;
    }

    public String getDetails() {
        return "EV: " + getMake() + " " + getModel() + " | Price: " + getPrice();

    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getRange() {
        return range;
    }

    @Override
    public Spec[] getUiSpecs() {
        return new Spec[]{
                new Spec("\uf5df", getRange() + " mi"),
                new Spec("\uf0e7", getBatteryCapacity() + " kWh"),
                new Spec("\uf52b", getDoors() + " Doors")
        };
    }
}