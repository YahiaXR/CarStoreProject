package CarStore;

public class motorbike extends vehicle{
    int wheels;
    double suspension;
    public motorbike(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, int wheels){
        super(make, model, year, price, engine, imagePath, 0);
        this.wheels = wheels;
    }
    public String getDetails() {
        return "Motorbike " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }


    @Override
    public Spec[] getUiSpecs() {
        return new Spec[] {
                new Spec("\uf52f", getEngine().getFuel()),
                new Spec("\uf538", getEngine().getEngineSpecs()),
                new Spec("\uf21c", "2 Wheels")
        };
    }
}


