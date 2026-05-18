package CarStore;

public class motorbike extends vehicle{
    int seats;
    double suspension;
    public motorbike(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, int seats){
        super(make, model, year, price, engine, imagePath, 0);
        this.seats = seats;
    }
    public String getDetails() {
        return "Motorbike " + getMake() + " " + getModel() + " | Price: " + getPrice();
    }
}
