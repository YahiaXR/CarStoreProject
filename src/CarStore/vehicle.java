package CarStore;

public abstract class vehicle {

    private String make;
    private String model;
    private int price;
    private String imagePath;
    private int year;
    protected engineSpecs Engine;
    private int doors;


    public vehicle(String make, String model, int year, int price, engineSpecs engine, String imagePath, int doors) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.Engine = engine;
        this.imagePath = imagePath;
        this.doors = doors;
    }
    public abstract String getDetails();
    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getPrice() {
        return price;
    }
    public String getImagePath() {
        return imagePath;
    }
    public int getYear() {
        return year;
    }
    public int getDoors() {
        return doors;
    }
    public engineSpecs getEngine() {
        return Engine;
    }

    public static class Spec {
        private String icon;
        private String value;

        public Spec(String icon, String value) {
            this.icon = icon;
            this.value = value;
        }

        public String getIcon() { return icon; }
        public String getValue() { return value; }
    }

    public Spec[] getUiSpecs() {
        return new Spec[] {
                new Spec("\uf52f", getEngine().getFuel()),        // Fuel Icon
                new Spec("\uf538", getEngine().getEngineSpecs())  // Engine Icon
        };
    }
}
