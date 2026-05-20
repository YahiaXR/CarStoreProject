package CarStore;

public class bus extends vehicle{

    private int seatCount;
    private double length;

    public bus(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors, double length, int SeatCount) {
        super(make, model, year, price, engine, imagePath, doors);

        this.seatCount = SeatCount;
        this.length = length;
    }

    @Override
    public String getDetails() {
        return "Bus: " + getMake() + " " + getModel() + " | Seats: " + seatCount;
    }

    public int getSeatCount() { return seatCount; }
    public double getLength() { return length; }

    @Override
    public Spec[] getUiSpecs() {
        return new Spec[]{
                new Spec("\uf52f", getEngine().getFuel()),
                new Spec("\uf4c6", getSeatCount() + " Seats"),
                new Spec("\uf545", String.format("%.2fm Length", getLength()))
        };
    }
}


