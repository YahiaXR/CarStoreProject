package CarStore;

public class truck extends vehicle {

    private double bed_size;
    private double torque;
    private int doors;

    public truck(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, double bed_size, double torque) {
        super(make, model, year, price, engine, imagePath, doors);
        this.bed_size = bed_size;
        this.torque = torque;
        this.doors = doors;
    }

    public String getDetails() {
        return "Truck: " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }

    public double getBed_size() {
        return bed_size;
    }

    public double getTorque() {
        return torque;
    }

    @Override
    public Spec[] getUiSpecs() {
        return new Spec[]{
                new Spec("\uf52f", getEngine().getFuel()),
                new Spec("\uf53c", String.format("%.0f lb-ft", getTorque())),
                new Spec("\uf4df", String.format("%.2fm Bed", getBed_size())),
                new Spec("\uf52b", getDoors() + " Doors"),
        };
    }
}
