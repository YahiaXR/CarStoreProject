package CarStore;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class carStoreGUI extends Application {

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button settingsButton;

    @FXML
    private TilePane galleryTilePane;

    @FXML
    private HBox searchContainer;

    private inventory myInventory = new inventory();
    private Font fontAwesome;


    @Override
    public void start(Stage primaryStage) {
        try {

            fontAwesome = Font.loadFont(getClass().getResourceAsStream("/CarStore/fonts/fontawesome.otf"), 16);

            if (fontAwesome == null) {
                System.out.println("Font awesome wasn't found sad");
                fontAwesome = Font.font("System", 16);
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            loader.setController(this);
            Parent root = loader.load();

            primaryStage.setTitle("Car Store Frontend");
            primaryStage.setScene(new Scene(root, 1360, 800));
            primaryStage.show();

            System.out.println("GUI Loaded Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayVehicles(java.util.List<vehicle> vehicles) {
        galleryTilePane.getChildren().clear();

        for (vehicle v : vehicles) {

            VBox card = new VBox(15);
            card.setAlignment(Pos.CENTER);
            card.setPadding(new Insets(20));
            card.setPrefWidth(420);

            String standardStyle =
                    "-fx-background-color: white; " +
                            "-fx-background-radius: 15; " +
                            "-fx-border-radius: 15; " +
                            "-fx-border-color: #f0f0f0; " +
                            "-fx-border-width: 1.5; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 10, 0, 0, 4);";

            String hoverStyle =
                    "-fx-background-color: white; " +
                            "-fx-background-radius: 15; " +
                            "-fx-border-radius: 15; " +
                            "-fx-border-color: #3498db; " +
                            "-fx-border-width: 1.5; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 15, 0, 0, 6);";

            String searchFieldStyle =
                    "-fx-background-color: #f8f9fa; " +
                            "-fx-background-radius: 25; " +
                            "-fx-border-color: #dee2e6; " +
                            "-fx-border-radius: 25; " +
                            "-fx-padding: 8 15 8 15; " +
                            "-fx-font-size: 14px; " +
                            "-fx-text-fill: #2c3e50;";


            String searchBtnStyle =
                    "-fx-background-color: #3498db; " +
                            "-fx-text-fill: white; " +
                            "-fx-background-radius: 20; " +
                            "-fx-font-weight: bold; " +
                            "-fx-padding: 8 20 8 20; " +
                            "-fx-cursor: hand;";


            String settingsBtnStyle =
                    "-fx-background-color: #95a5a6; " +
                            "-fx-text-fill: white; " +
                            "-fx-background-radius: 20; " +
                            "-fx-padding: 8 15 8 15; " +
                            "-fx-cursor: hand;";

            card.setStyle(standardStyle);


            searchField.setStyle(searchFieldStyle);
            searchField.setPromptText("Type something to get started...");

            searchButton.setStyle(searchBtnStyle);
            searchButton.setText("Search");

            settingsButton.setStyle(settingsBtnStyle);


            ImageView imageView = new ImageView();
            try {
                String fullPath = "/CarStore/images/" + v.getImagePath();
                Image img = new Image(getClass().getResourceAsStream(fullPath));
                if (img != null && !img.isError()) {
                    imageView.setImage(img);
                }
            } catch (Exception e) {
                System.out.println("Error loading: " + v.getImagePath());
            }


            imageView.setFitWidth(220);
            imageView.setFitHeight(140);
            imageView.setPreserveRatio(true);


            HBox iconRow = new HBox(25);
            iconRow.setAlignment(Pos.CENTER);
            VBox.setMargin(iconRow, new Insets(15, 0, 10, 0));

            String textStyle = "-fx-text-fill: #34495e; -fx-font-weight: bold;";
            Font iconFont = (fontAwesome != null) ? Font.font(fontAwesome.getFamily(), 18) : Font.font("System", 18);

            for (vehicle.Spec spec : v.getUiSpecs()) {
                HBox specGroup = new HBox(5);
                specGroup.setAlignment(Pos.CENTER);

                Label iconLabel = new Label(spec.getIcon());
                iconLabel.setFont(iconFont);

                Label textLabel = new Label(spec.getValue());
                textLabel.setStyle(textStyle);

                specGroup.getChildren().addAll(iconLabel, textLabel);
                iconRow.getChildren().add(specGroup);
            }


            Label nameLabel = new Label(v.getYear() + " " + v.getMake() + " " + v.getModel());
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #2c3e50;");

            Label priceLabel = new Label(String.format("%,.0f USD", (double) v.getPrice()));
            priceLabel.setStyle("-fx-text-fill: #27ae60; " +
                    "-fx-font-size: 18px; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 5 0 0 0;");


            card.getChildren().addAll(imageView, iconRow, nameLabel, priceLabel);


            card.setOnMouseEntered(e -> {
                card.setStyle(hoverStyle);
                card.setTranslateY(-5);
            });
            card.setOnMouseExited(e -> {
                card.setStyle(standardStyle);
                card.setTranslateY(0);
            });

            searchField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (isNowFocused) {
                    searchField.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 25; -fx-border-color: #3498db; -fx-border-width: 2; -fx-border-radius: 25; -fx-padding: 7 14 7 14;");
                } else {
                    searchField.setStyle("-fx-background-color: #f8f9fa; -fx-background-radius: 25; -fx-border-color: #dee2e6; -fx-border-width: 1; -fx-border-radius: 25; -fx-padding: 8 15 8 15;");
                }
            });

            searchContainer.setSpacing(10);
            searchContainer.setPadding(new Insets(15, 0, 20, 0));


            galleryTilePane.getChildren().add(card);
        }
    }

    @FXML
    public void initialize() {

        galleryTilePane.setAlignment(Pos.TOP_LEFT);
        galleryTilePane.setHgap(20);
        galleryTilePane.setVgap(20);

        engineSpecs D4GA = new engineSpecs("D4GA", 140, "Gas");
        engineSpecs _1HZ = new engineSpecs("Toyota", 130, "Gas");
        engineSpecs D13 = new engineSpecs("D13", 435, "Gas");
        engineSpecs BlueTec_6 = new engineSpecs("BlueTec", 315, "Gas");
        engineSpecs coasterDiesel = new engineSpecs("4.2L Inline-6", 129, "Diesel");
        engineSpecs hiaceDiesel = new engineSpecs("2.8L Inline-4", 174, "Diesel");
        engineSpecs sprinterDiesel = new engineSpecs("2.1L Twin-Turbo I4", 168, "Diesel");
        engineSpecs VVA = new engineSpecs("VVA", 15, "Gas");
        engineSpecs V_TwinEngine = new engineSpecs("V-Twin", 72, "Gas");
        engineSpecs DOCH = new engineSpecs("DOCH", 120, "Gas");

        engineSpecs DMAWD = new engineSpecs("Tesla Motor AWD", 670, "Electric");
        engineSpecs TeslaYMotor = new engineSpecs("Tesla Motor AWD", 384, "Electric");
        engineSpecs Tesla3Motor = new engineSpecs("Tesla Motor AWD", 446, "Electric");
        engineSpecs RoadsterV1 = new engineSpecs("Tesla Roadster", 288, "Electric");

        engineSpecs EcoTec3_V8 = new engineSpecs("V8", 355, "Gas");
        engineSpecs Twin_Turbo_V6_EcoBoost = new engineSpecs("V6", 450, "Gas");
        engineSpecs V6_Turbo_Diesel = new engineSpecs("V6", 161, "Gas");

        engineSpecs economyI4 = new engineSpecs("Inline-4", 140, "Gas");
        engineSpecs modernTurboI4 = new engineSpecs("Turbo I4", 250, "Gas");
        engineSpecs luxuryV6 = new engineSpecs("V6", 310, "Gas");

        engineSpecs sportV8 = new engineSpecs("V8", 450, "Gas");
        engineSpecs amgV8 = new engineSpecs("Biturbo V8", 603, "Gas");
        engineSpecs porscheFlat6 = new engineSpecs("Twin-Turbo Flat-6", 580, "Gas");
        engineSpecs rollsV12 = new engineSpecs("Twin-Turbo V12", 563, "Gas");

        engineSpecs vintageI4 = new engineSpecs("Inline-4", 75, "Gas");
        engineSpecs smallI4 = new engineSpecs("Inline-4", 105, "Gas");

        engineSpecs microlino = new engineSpecs("Single RWD", 17, "Electric");

        //Tesla
        myInventory.addVehicle(new ev("Tesla", "Model S", 2022, 75000, DMAWD, "2022_Tesla_Model S_Trans_Front.png", 4, 405, 100));
        myInventory.addVehicle(new ev("Tesla", "Model 3", 2023, 43000, Tesla3Motor, "2023_Tesla_Model 3_Trans_Front.png", 4, 358, 82));
        myInventory.addVehicle(new ev("Tesla", "Model X", 2024, 80000, DMAWD, "2024_Tesla_Model X_Trans_Front.png", 4, 335, 100));
        myInventory.addVehicle(new ev("Tesla", "Model Y", 2025, 48000, TeslaYMotor, "2025_Tesla_Model Y_Trans_Front.png", 4, 310, 75));
        myInventory.addVehicle(new ev("Tesla", "Roadster", 2010, 110000, RoadsterV1, "2010_Tesla_Roadster_Trans_Front.png", 2, 244, 53));

        //Volvo
        myInventory.addVehicle(new bus("Vovlo", "9700", 2014, 420000, D13, "Volvo_9700_2012_Trans_Front.png", 1, 13.7, 55));

        //Yamaha
        myInventory.addVehicle(new motorbike("Yamaha", "NMAX 155", 2017, 3500, VVA, "2017_Yamaha_NMAX 155_Trans_Front.png", 0, 2));
        myInventory.addVehicle(new motorbike("Yamaha", "Stryker Bullet Cowl", 2016, 12100, V_TwinEngine, "2016_Yamaha_Stryker Bullet Cowl_Trans_Front.png", 0, 2));

        //Suzuki
        myInventory.addVehicle(new motorbike("Suzuki", "GSX-R600", 2005, 8300, DOCH, "2005_Suzuki_GSX-R600_Trans_Front.png", 0, 2));

        //GMC
        myInventory.addVehicle(new truck("GMC", "Sierra 1500", 2017, 45000, EcoTec3_V8, "2017_GMC _Sierra 1500_Trans_Front.png", 4, 1.78, 519));

        //Ford
        myInventory.addVehicle(new truck("Ford", "F-150 Raptor", 2017, 67000, Twin_Turbo_V6_EcoBoost, "2017_Ford_F-150 Raptor_Trans_Fronts.png", 2, 1.49, 691));

        //Dodge
        myInventory.addVehicle(new truck("Dodge", "RAM 1500", 2022, 40000, luxuryV6, "2022_Dodge_RAM 1500_Trans_Front.png", 4, 1.72, 651));

        // Toyota
        myInventory.addVehicle(new bus("Toyota", "Coaster", 2013, 65000, _1HZ, "Toyota_Coaster_2013_Trans_Front.png", 2, 7, 30 ));
        myInventory.addVehicle(new truck("Toyota", "Hilux", 2017, 30000, modernTurboI4, "2017_Toyota_Hilux_Trans_Front.png", 4, 1.52, 450));
        myInventory.addVehicle(new sedan("Toyota", "Camry", 2018, 19000, modernTurboI4, "2018_Toyota_Camry_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Toyota", "Corolla", 2019, 18000, luxuryV6, "2019_Toyota_Corolla_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Toyota", "Yaris", 2007, 16000, economyI4, "2007_Toyota_Yaris_Trans_Front.png", 4));
        myInventory.addVehicle(new bus("Toyota", "Coaster", 2024, 137550, coasterDiesel, "2024_Toyota_Coaster_Trans_Front.png", 3, 6.99, 23));
        myInventory.addVehicle(new bus("Toyota", "HiAce Commuter", 2025, 95000, hiaceDiesel, "2025_Toyota_HiAce Commuter_Trans_Front.png", 4, 5.91, 14));

        // Honda
        myInventory.addVehicle(new sedan("Honda", "Civic", 2023, 24840, luxuryV6, "2023_Honda_Civic_Trans_Front.png", 4));

        // Microlino
        myInventory.addVehicle(new micro("Micro", "Microlino", 2024, 19000, microlino, "2024_Microlino_Trans_Front.png", 1, 2.52));


        // Mercedes
        myInventory.addVehicle(new bus("Mercedes-Benz", "Toursimo L", 2013, 300000, BlueTec_6,"Mercedes-Benz_Toursimo L_2013_Trans_Front.png", 2, 13.95, 65));
        myInventory.addVehicle(new sedan("Mercedes-Benz", "A-Class", 2018, 24000, luxuryV6, "2018_MercedesBenz_A-Class_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes-Benz", "C-Class", 2018, 50000, luxuryV6, "2018_MeredesBenz_C-Class_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes-Benz", "S-Class Coupe (S63 AMG)", 2020, 115000, sportV8, "2020_MercedesBenz_S-ClassCoupeS63AMG_Trans_Front.png", 2));
        myInventory.addVehicle(new sedan("Mercedes-Benz", "Maybach (S600)", 2017, 190000, amgV8, "2017_Mercedes-Maybach_Maybach S 600_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes-Benz", "Evo II", 1990, 80000, smallI4, "1990_Mercedes-Benz_Evo II_Trans_Front.png", 4));
        myInventory.addVehicle(new bus("Mercedes-Benz", "Sprinter Passenger", 2025, 115000, sprinterDiesel, "2025_Mercedes_Sprinter Passenger_Trans_Front.png", 4, 7.37, 23));

        // Daewoo
        myInventory.addVehicle(new sedan("Daewoo", "Lanos Hatchback", 2002, 1450, economyI4, "2002_Daewoo_Lanos_Hatchback_Trans_Front.png", 4));

        // Hyundai
        myInventory.addVehicle(new bus("Hyundai", "County", 2016, 55000, D4GA, "Hyundai_County_2013_Trans_Front.png", 2, 6.35, 30 ));
        myInventory.addVehicle(new sedan("Hyundai", "Accent", 2003, 1700, smallI4, "2003_Hyundai_Accent_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Hyundai", "Tucson", 2018, 14000, economyI4, "2018_Hyundai_Tucson_Trans_Front.png", 4));

        // Chevrolet
        myInventory.addVehicle(new truck("Chevrolet", "Silverado 1500", 2015, 35000, EcoTec3_V8, "2015_Chevrolet_Silverado 1500_Trans_Front.png", 4, 1.78, 355));
        myInventory.addVehicle(new sedan("Chevrolet", "Prisma", 2017, 13400, economyI4, "2017_Chevrolet_Prisma_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Chevrolet", "Aveo", 2010, 2975, economyI4, "2010_Chevrolet_Aveo_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Chevrolet", "CamaroSS", 2015, 22000, luxuryV6, "2015_Chevrolet_Camaro_Trans_Front.png", 2));
        myInventory.addVehicle(new sedan("Chevrolet", "Cruze", 2012, 18000, modernTurboI4, "2012_Chevrolet_Cruze_Trans_Front.png", 4));

        // Audi
        myInventory.addVehicle(new sedan("Audi", "A6 Sedan", 2018, 29000, sportV8, "2018_Audi_A6_Sedan_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Audi", "RS3", 2017, 34000, sportV8, "2017_Audi_RS3_Trans_Front.png", 4));

        // Porsche
        myInventory.addVehicle(new sedan("Porsche", "911 Turbo S (Type 911.2)", 2017, 185000, porscheFlat6, "2018_Porsche_911TS_Trans_Front.png", 2));

        // Setra
        myInventory.addVehicle(new sedan("Porsche", "911 Turbo S (Type 911.2)", 2017, 185000, porscheFlat6, "2018_Porsche_911TS_Trans_Front.png", 2));


        // Lada
        myInventory.addVehicle(new sedan("Lada", "2107", 1982, 1700, vintageI4, "2107_Lada_2107_Trans_Front.png", 4));

        // Mazda
        myInventory.addVehicle(new sedan("Mazda", "6", 2015, 10000, modernTurboI4, "2015_Mazda_Mazda6_Trans_Front.png", 4));

        // BMW
        myInventory.addVehicle(new sedan("BMW", "X6", 2019, 75000, modernTurboI4, "2019_BMW_X6_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("BMW", "M3", 2001, 250000, modernTurboI4, "2001_BMW_M3_Trans_Front.png", 4));

        // Volkswagen
        myInventory.addVehicle(new truck("Volkswagen", "Amarok", 2020, 55000, V6_Turbo_Diesel, "2020_Volkswagen_Amarok_Trans_Front.png", 4, 2.52, 550));
        myInventory.addVehicle(new sedan("Volkswagen", "Passat", 2018, 18500, modernTurboI4, "2018_Wolkswagen_Passat_Trans_Front.png", 4));

        // Rolls-Royce
        myInventory.addVehicle(new sedan("Rolls Royce", "Ghost I", 2026, 418000, rollsV12, "2026_RollsRoyce_Ghost_I_Trans_Front.png", 4));

        // Renault
        myInventory.addVehicle(new sedan("Renault", "Megane", 2018, 20000, luxuryV6, "2018_Renault_Megane_Trans_Front.png", 4));


        displayVehicles(myInventory.getVehicleList());
    }

    public static void main(String[] args) {
        launch(args);
    }


}