package CarStore;

public class sedan extends vehicle{

    public sedan(String make, String model, double price, String imagePath, engineSpecs engine){
        super(make, model, price, imagePath, engine);
    }

    @Override
    public String getDetails() {
        return "Sedan: " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }
}
