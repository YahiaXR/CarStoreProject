package CarStore;

public class micro extends vehicle{

    private double footprint;

    public micro(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, double footprint) {
        super(make, model, year, price, engine, imagePath, doors);

        this.footprint = footprint;
    }

    public String getDetails() {
        return "Truck: " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }

    public double getFootprint() { return footprint; }

    @Override
    public Spec[] getUiSpecs() {
        return new Spec[]{
                new Spec("\uf52f", getEngine().getFuel()),
                new Spec("\uf545", String.format("%.2fm Length", getFootprint())),
                new Spec("\uf52b", getDoors() + " Doors")
        };
    }
}

