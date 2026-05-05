package CarStore;

public class engineSpecs {

    private String type;
    private int horsepower;

    public engineSpecs(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }


    public String getEngineSpecs() {
        return type + " (" + horsepower + " HP)";
    }
}
