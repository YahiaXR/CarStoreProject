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
                System.out.println("Warning: Font Awesome not found. Using system default.");
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

            HBox fuelGroup = new HBox(5);
            fuelGroup.setAlignment(Pos.CENTER);
            Label fuelIcon = new Label("\uf52f");
            fuelIcon.setFont(iconFont);
            Label fuelText = new Label(v.getEngine().getFuel());
            fuelText.setStyle(textStyle);
            fuelGroup.getChildren().addAll(fuelIcon, fuelText);

            HBox engineGroup = new HBox(5);
            engineGroup.setAlignment(Pos.CENTER);
            Label engineIcon = new Label("\uf538");
            engineIcon.setFont(iconFont);
            Label engineText = new Label(v.getEngine().getEngineSpecs());
            engineText.setStyle(textStyle);
            engineGroup.getChildren().addAll(engineIcon, engineText);

            HBox doorGroup = new HBox(5);
            doorGroup.setAlignment(Pos.CENTER);
            Label doorIcon = new Label("\uf52b");
            doorIcon.setFont(iconFont);
            Label doorText = new Label(v.getDoors() + " Doors");
            doorText.setStyle(textStyle);
            doorGroup.getChildren().addAll(doorIcon, doorText);

            iconRow.getChildren().addAll(fuelGroup, engineGroup, doorGroup);


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

        engineSpecs VVA = new engineSpecs("VVA", 15, "Gas");
        engineSpecs V_TwinEngine = new engineSpecs("V-Twin",72, "Gas");
        engineSpecs DOCH = new engineSpecs("DOCH", 120, "Gas");

        engineSpecs EcoTec3_V8 = new engineSpecs("V8",355,"Gas");
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

        //Yamaha
        myInventory.addVehicle(new motorbike("Yamaha", "NMAX 155",2017, 3500, VVA, "2017_Yamaha_NMAX 155_Trans_Front.png", 0 ,2));
        myInventory.addVehicle(new motorbike("Yamaha", "Stryker Bullet Cowl", 2016, 12100, V_TwinEngine, "2016_Yamaha_Stryker Bullet Cowl_Trans_Front.png", 0, 2));

        //Suzuki
        myInventory.addVehicle(new motorbike("Suzuki", "GSX-R600",2005, 8300, DOCH, "2005_Suzuki_GSX-R600_Trans_Front.png",0, 2  ));

        //GMC
        myInventory.addVehicle(new truck("GMC", "Sierra 1500", 2017, 45000, EcoTec3_V8, "2017_GMC _Sierra 1500_Trans_Front.png", 4, 1.78, 519));

        //Ford
        myInventory.addVehicle(new truck("Ford", "F-150 Raptor", 2017, 67000, Twin_Turbo_V6_EcoBoost, "2017_Ford_F-150 Raptor_Trans_Fronts.png", 2, 1.49, 691));

        //Dodge
        myInventory.addVehicle(new truck("Dodge", "RAM 1500", 2022, 40000, luxuryV6, "2022_Dodge_RAM 1500_Trans_Front.png",4 ,1.72, 651));

        // Toyota
        myInventory.addVehicle(new truck("Toyota", "Hilux", 2017, 30000,modernTurboI4, "2017_Toyota_Hilux_Trans_Front.png", 4,1.52,450));
        myInventory.addVehicle(new sedan("Toyota", "Camry", 2018, 19000, modernTurboI4, "2018_Toyota_Camry_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Toyota", "Corolla", 2019, 18000, luxuryV6, "2019_Toyota_Corolla_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Toyota", "Yaris", 2007, 16000, economyI4, "2007_Toyota_Yaris_Trans_Front.png", 4));

        // Honda
        myInventory.addVehicle(new sedan("Honda", "Civic", 2023, 24840, luxuryV6, "2023_Honda_Civic_Trans_Front.png", 4));

        // Mercedes
        myInventory.addVehicle(new sedan("Mercedes Benz", "A-Class", 2018, 24000, luxuryV6, "2018_MercedesBenz_A-Class_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes Benz", "C-Class", 2018, 50000, luxuryV6, "2018_MeredesBenz_C-Class_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes Benz", "S-Class Coupe (S63 AMG)", 2020, 115000, sportV8, "2020_MercedesBenz_S-ClassCoupeS63AMG_Trans_Front.png", 2));
        myInventory.addVehicle(new sedan("Mercedes Benz", "Maybach (S600)", 2017, 190000, amgV8, "2017_Mercedes-Maybach_Maybach S 600_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes Benz", "Evo II", 1990, 80000, smallI4, "1990_Mercedes-Benz_Evo II_Trans_Front.png", 4));

        // Daewoo
        myInventory.addVehicle(new sedan("Daewoo", "Lanos Hatchback", 2002, 1450, economyI4, "2002_Daewoo_Lanos_Hatchback_Trans_Front.png", 4));

        // Hyundai
        myInventory.addVehicle(new sedan("Hyundai", "Accent", 2003, 1700, smallI4, "2003_Hyundai_Accent_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Hyundai", "Tucson", 2018, 14000, economyI4, "2018_Hyundai_Tucson_Trans_Front.png", 4));

        // Chevrolet
        myInventory.addVehicle(new truck("Chevrolet", "Silverado 1500", 2015,35000, EcoTec3_V8, "2015_Chevrolet_Silverado 1500_Trans_Front.png",4, 1.78, 355 ));
        myInventory.addVehicle(new sedan("Chevrolet", "Prisma", 2017, 13400, economyI4, "2017_Chevrolet_Prisma_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Chevrolet", "Aveo", 2010, 2975, economyI4, "2010_Chevrolet_Aveo_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Chevrolet", "CamaroSS", 2015, 22000, luxuryV6, "2015_Chevrolet_Camaro_Trans_Front.png", 2));
        myInventory.addVehicle(new sedan("Chevrolet", "Cruze", 2012, 18000, modernTurboI4, "2012_Chevrolet_Cruze_Trans_Front.png", 4));

        // Audi
        myInventory.addVehicle(new sedan("Audi", "A6 Sedan", 2018, 29000, sportV8, "2018_Audi_A6_Sedan_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Audi", "RS3", 2017, 34000, sportV8, "2017_Audi_RS3_Trans_Front.png", 4));

        // Porsche
        myInventory.addVehicle(new sedan("Porsche", "911 Turbo S (Type 911.2)", 2017, 185000, porscheFlat6, "2018_Porsche_911TS_Trans_Front.png", 2));

        // Lada
        myInventory.addVehicle(new sedan("Lada", "2107", 1982, 1700, vintageI4, "2107_Lada_2107_Trans_Front.png", 4));

        // Mazda
        myInventory.addVehicle(new sedan("Mazda", "6", 2015, 10000, modernTurboI4, "2015_Mazda_Mazda6_Trans_Front.png", 4));

        // BMW
        myInventory.addVehicle(new sedan("BMW", "X6", 2019, 75000, modernTurboI4, "2019_BMW_X6_Trans_Front.png", 4));

        // Volkswagen
        myInventory.addVehicle(new truck("Volkswagen","Amarok", 2020, 55000, V6_Turbo_Diesel, "2020_Volkswagen_Amarok_Trans_Front.png",4, 2.52, 550));
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