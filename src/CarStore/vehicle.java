package CarStore;

public abstract class vehicle {

    private String make;
    private String model;
    private double price;
    private String imagePath;
    protected engineSpecs Engine;


    public vehicle(String make, String model, double price, String imagePath, engineSpecs engine){

        this.make = make;
        this.model = model;
        this.price = price;
        this.imagePath = imagePath;
        this.Engine = engine;

    }

    public abstract String getDetails();

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}
