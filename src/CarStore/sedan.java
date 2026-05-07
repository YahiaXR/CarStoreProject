package CarStore;

public class sedan extends vehicle{

    private int doors;

    public sedan(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors) {
        super(make, model, year, price, engine, imagePath, doors);
        this.doors = doors;
    }

    @Override
    public String getDetails() {
        return "Sedan: " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }


}
