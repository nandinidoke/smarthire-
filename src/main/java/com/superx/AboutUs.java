package com.superx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutUs  {

    public Scene createScene(Stage mainStage,Runnable backAction){

        // ===== Root Layout =====
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:rgb(13, 25, 38);");
        Button back=new Button("<-back");
        back.setOnAction(e->backAction.run());
        

        back.setStyle("-fx-background-color: rgba(11, 12, 12, 0.03); -fx-text-fill: white;");
   

        // ===== Top: Logos =====
        HBox topLogos = new HBox();
        topLogos.setPadding(new Insets(15, 30, 15, 30));
        topLogos.setAlignment(Pos.CENTER_LEFT);

        ImageView instituteLogoView = new ImageView(new Image("/Assets/Image/core2webLogo.png"));
        instituteLogoView.setFitHeight(100);
        instituteLogoView.setPreserveRatio(true);

        ImageView projectLogoView = new ImageView(new Image("/Assets/Image/Logo1.jpeg"));
        projectLogoView.setFitHeight(150);
        projectLogoView.setPreserveRatio(true);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topLogos.getChildren().addAll(back,instituteLogoView, spacer, projectLogoView);
        root.setTop(topLogos);

        // ===== Main Layout =====
        HBox mainLayout = new HBox(40);
        mainLayout.setPadding(new Insets(0, 30, 10, 30));

        // ===== Left: Teacher Photo =====
        Image teacherImage = new Image("/Assets/Image/ShashiSir.png");
        ImageView teacherView = new ImageView(teacherImage);
        teacherView.setFitWidth(500);
        teacherView.setFitHeight(450);
        teacherView.setStyle("-fx-effect: dropshadow(gaussian, gray, 10, 0.5, 2, 2);");

        VBox leftBox = new VBox(teacherView);
        leftBox.setAlignment(Pos.TOP_LEFT);
        leftBox.setPadding(new Insets(-50, 0, 0, 30));

        // ===== Right: Text Info and Cards =====
        VBox rightBox = new VBox(20);
        rightBox.setAlignment(Pos.TOP_LEFT);
        rightBox.setPadding(new Insets(-50, 0, 0, 0)); // shift About Us section upward

        // About Us Heading in a styled box
        // Label heading = new Label("About Us");
        // heading.setFont(Font.font("Arial", 22));
        // heading.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label heading = new Label("About Us");
        heading.setFont(Font.font("Arial", 22));
        heading.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        StackPane headingBox = new StackPane(heading);
        headingBox.setAlignment(Pos.CENTER);
        headingBox.setPadding(new Insets(10));
        headingBox.setMaxWidth(200); // reduced visible width
        headingBox.setStyle(
    "-fx-background-color: #1f2e3e;" +
    "-fx-background-radius: 10;" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 8, 0, 2, 2);"
);



        // Teacher & Mentor Labels
        VBox teacherBox = new VBox(4); // reduced spacing
        teacherBox.setAlignment(Pos.TOP_LEFT);

        Label teacherLabel = new Label("Teacher: Prof. Shashi Bagal sir");
        Label teacher1Label = new Label("Prof. Sachin Patil sir");
        Label teacher2Label = new Label("Prof. Pramod sir");
        Label mentorLabel = new Label("Mentor: Punam Khedkar");

        for (Label label : new Label[]{teacherLabel, teacher1Label, teacher2Label, mentorLabel}) {
            label.setFont(Font.font("Arial", 14));
            label.setStyle("-fx-text-fill: white;");
        }

        teacherBox.getChildren().addAll(teacherLabel, teacher1Label, teacher2Label, mentorLabel);

        // ===== Project Card =====
        VBox projectCard = new VBox(10);
        projectCard.setPadding(new Insets(20));
        projectCard.setPrefWidth(400);
        projectCard.setStyle(
            "-fx-background-color: #1f2e3e;" +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 12, 0, 4, 4);"
        );

        Label aboutProjectLabel = new Label("About Project:");
        aboutProjectLabel.setFont(Font.font("Arial", 20));
        aboutProjectLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label projectDesc = new Label(
            "SmartHire is an intelligent interview preparation platform developed as part of the Core2Web training program. " +
            "It helps students and job seekers prepare for technical and HR interviews with curated content, aptitude questions, and mock interview simulations."
        );
        projectDesc.setWrapText(true);
        projectDesc.setFont(Font.font("Arial", 15));
        projectDesc.setStyle("-fx-text-fill: #dce3ea;");

        projectCard.getChildren().addAll(aboutProjectLabel, projectDesc);

        // ===== Team Members Card =====
        VBox cardBox = new VBox(10);
        cardBox.setPadding(new Insets(20));
        cardBox.setPrefWidth(400);
        cardBox.setStyle(
            "-fx-background-color: #1f2e3e;" +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 12, 0, 4, 4);"
        );

        Label teamLabel = new Label("Team Members:");
        teamLabel.setFont(Font.font("Arial", 18));
        teamLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Label members = new Label("1. Anuja Shelar\n2. Shubhangi Sapate\n3. Nandini Doke\n4. Akanksha Pokharkar");
        members.setFont(Font.font("Arial", 15));
        members.setStyle("-fx-text-fill: #dce3ea;");

        cardBox.getChildren().addAll(teamLabel, members);

        rightBox.getChildren().addAll(headingBox, teacherBox, projectCard, cardBox);

        // Add left and right to main layout
        mainLayout.getChildren().addAll(leftBox, rightBox);
        root.setCenter(mainLayout);

        // ===== Scene & Stage =====
        Scene scene = new Scene(root, 1100, 650);
        return scene;
}
}
