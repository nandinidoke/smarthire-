package com.superx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class Explore extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Get full screen dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double WIDTH = screenBounds.getWidth();
        double HEIGHT = screenBounds.getHeight();

        BorderPane backgroundLayer = new BorderPane();
        backgroundLayer.setPadding(new Insets(30));
        backgroundLayer.setStyle(
            "-fx-background-color: linear-gradient(to bottom right, rgb(182, 189, 198), rgb(41, 141, 199));"
        );

        // --- Logo at top-left ---
        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.TOP_LEFT);
        logoBox.setPadding(new Insets(5, 0, 0, 20));
        Image logoImage = new Image(getClass().getResourceAsStream("/Assets/Image/smrthirelogo.png"));
        ImageView logo = new ImageView(logoImage);
        logo.setFitHeight(200);  // Adjust size if needed
        logo.setPreserveRatio(true);
        logoBox.getChildren().add(logo);
        backgroundLayer.setTop(logoBox);

        // --- Center Text Content ---
       // --- Center Text Content ---
        VBox centerBox = new VBox(5);
        centerBox.setAlignment(Pos.CENTER_LEFT);

        // Reduce top padding from 20 to 0 (or try 5/10 for slight spacing)
        centerBox.setPadding(new Insets(0, 20, 20, 20)); 

        Text quote = new Text("Improve Your Skills");
        quote.setFill(Color.WHITE);
        quote.setFont(Font.font("Arial", FontWeight.BOLD, 62));


        Text subText1 = new Text("Opportunities don't happen, you create them. Prepare today for a better tomorrow.");
        Text subText2 = new Text("Your all-in-one prep platform for coding, aptitude, and interviews.");
        Text subText3 = new Text("Sharpen your skills and get job-ready with smart, focused learning.");

        for (Text t : new Text[]{subText1, subText2, subText3}) {
            t.setFill(Color.WHITE);
            t.setFont(Font.font("Arial", FontPosture.ITALIC, 22));
        }

        Region spacer = new Region();
        spacer.setMinHeight(30);

        Button exploreBtn = new Button("Explore");
        exploreBtn.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 30;" +
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-padding: 12 36 12 36;"
        );

        exploreBtn.setOnAction(e -> {
            System.out.println("Explore button clicked");
            try {
                Stage loginStage = new Stage();
                new LoginPage().show(loginStage);
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        centerBox.getChildren().addAll(quote, subText1, subText2, subText3, spacer, exploreBtn);
        backgroundLayer.setLeft(centerBox);

        // --- Right side image ---
        ImageView rightImage = new ImageView(new Image(getClass().getResourceAsStream("/Assets/Image/exploreImg.png")));
        
        rightImage.setFitHeight(HEIGHT * 0.75);  // Responsive scaling
        rightImage.setPreserveRatio(true);

        StackPane rightWrapper = new StackPane();
        rightWrapper.setPadding(new Insets(0, 40, 0, 0)); // Keep right padding
        rightWrapper.setAlignment(Pos.CENTER); // Center the image vertically and horizontally

        rightWrapper.getChildren().add(rightImage);
        backgroundLayer.setRight(rightWrapper);



        Scene scene = new Scene(backgroundLayer, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Smart UI");
        primaryStage.setMaximized(true); // Make the window full screen
        primaryStage.show();
    }
}
