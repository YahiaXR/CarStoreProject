package CarStore;

public class engineSpecs {

    private String type;
    private int horsepower;
    private String fuel;

    public engineSpecs(String type, int horsepower, String fuel) {
        this.type = type;
        this.horsepower = horsepower;
        this.fuel = fuel;
    }


    public String getEngineSpecs() {
        return type + " (" + horsepower + " HP)";
    }

    public String getFuel() {
        return fuel;
    }
}
