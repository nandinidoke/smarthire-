package com.superx;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Resume {

    private static final String[] SECTIONS = {
            "Basic Details", "Contact Details", "Education", "Attachments",
            "Family Details", "Professional Experience", "Internship", "Projects",
            "Publications / Research / White Papers", "Seminars / Trainings / Workshops",
            "Certification / Assessments", "Positions of Responsibility", "Other Details"
    };

    private final HashMap<String, String> sectionContent = new HashMap<>();
    private VBox previewBox;
    private Stage mainStage;
    private Runnable backAction; 

    // ✅ Constructor with stage and back button action
    public Resume(Stage mainStage, Runnable backAction) {
        this.mainStage = mainStage;
        this.backAction = backAction;
    }

    public Scene createScene() {

        VBox sectionBox = new VBox(10);
        sectionBox.setPadding(new Insets(30));
       sectionBox.setAlignment(Pos.TOP_CENTER);
       sectionBox.setMaxWidth(400);
       sectionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.92); -fx-background-radius: 15;");

// Add section buttons
for (String section : SECTIONS) {
    Button btn = createSectionButton(section);
    sectionBox.getChildren().add(btn);
}

// Add Generate Resume button
Button generateBtn = new Button("Generate Resume");
generateBtn.setStyle("-fx-background-color: #6C4DFF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px; -fx-background-radius: 8;");
generateBtn.setMaxWidth(Double.MAX_VALUE);
generateBtn.setOnAction(e -> {
    try {
        generatePDFResume();
    } catch (IOException ex) {
        ex.printStackTrace();
        new Alert(Alert.AlertType.ERROR, "Failed to generate PDF.").showAndWait();
    }
});
sectionBox.getChildren().add(generateBtn);

// ✅ Make section scrollable
ScrollPane sectionScrollPane = new ScrollPane(sectionBox);
sectionScrollPane.setFitToWidth(true);
sectionScrollPane.setMaxHeight(600); // limits height so it's scrollable
sectionScrollPane.setStyle("-fx-background-color: transparent;");

        previewBox = new VBox(10);
        
        previewBox.setPadding(new Insets(20));
        previewBox.setStyle("-fx-background-color: rgba(255,255,255,0.85); -fx-background-radius: 15;");

        ScrollPane previewScroll = new ScrollPane(previewBox);
        previewScroll.setFitToWidth(true);
        previewScroll.setPrefViewportWidth(500);
        previewScroll.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");

        HBox contentBox = new HBox(30, sectionScrollPane, previewScroll);
        contentBox.setPadding(new Insets(30));
        contentBox.setMaxWidth(1000);

        Label heading = new Label("Resume Builder");
        heading.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-padding: 20 0 10 0;");

        // ✅ Back Button returns to main scene
        Button backButton = new Button("Back");
       backButton.setStyle("-fx-background-color: #9382f1ff; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(e->backAction.run());
// Back button container aligned to top-left
HBox backContainer = new HBox(backButton);
backContainer.setAlignment(Pos.TOP_LEFT);
backContainer.setPadding(new Insets(10, 0, 10, 10)); // Add some padding from the top and left


        VBox pageLayout = new VBox(20, backContainer,heading, contentBox);
        pageLayout.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane(pageLayout);
        StackPane.setAlignment(pageLayout, Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f4f6f9, #75a5d5ff);");

        return new Scene(root, 1280, 770);
    }

    private Button createSectionButton(String section) {
        Button btn = new Button(section);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle(buttonBaseStyle());
        btn.setOnMouseEntered(e -> btn.setStyle(buttonHoverStyle()));
        btn.setOnMouseExited(e -> btn.setStyle(buttonBaseStyle()));
        btn.setOnAction(e -> openInputDialog(section));
        return btn;
    }

    private void openInputDialog(String sectionTitle) {
        TextArea inputArea = new TextArea();
        inputArea.setWrapText(true);
        inputArea.setPrefRowCount(5);

        if (sectionContent.containsKey(sectionTitle)) {
            inputArea.setText(sectionContent.get(sectionTitle));
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(sectionTitle);
        dialog.getDialogPane().setContent(inputArea);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                sectionContent.put(sectionTitle, inputArea.getText());
                updatePreview();
            }
        });
    }

    private void updatePreview() {
        previewBox.getChildren().clear();
        for (String sec : SECTIONS) {
            if (sectionContent.containsKey(sec)) {
                Label title = new Label(sec);
                title.setFont(Font.font("Arial", 16));
                Label content = new Label(sectionContent.get(sec));
                content.setWrapText(true);
                VBox block = new VBox(5, title, content);
                block.setPadding(new Insets(10));
                block.setStyle("-fx-background-color: #f0f0ff; -fx-background-radius: 10;");
                previewBox.getChildren().add(block);
            }
        }
    }

    private void generatePDFResume() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        float margin = 50;
        float y = 770;
        float lineHeight = 15;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resume As");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("My_Resume.pdf");
        File file = fileChooser.showSaveDialog(mainStage);
        if (file == null) return;

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset(margin, y);
        contentStream.showText("Resume");
        contentStream.endText();
        y -= 40;

        contentStream.setFont(PDType1Font.HELVETICA, 12);

        for (String section : SECTIONS) {
            if (!sectionContent.containsKey(section)) continue;
            if (y < 100) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                y = 770;
            }
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(margin, y);
            contentStream.showText(section);
            contentStream.endText();
            y -= 20;

            String[] lines = sectionContent.get(section).split("\n");
            for (String line : lines) {
                if (y < 80) {
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    y = 770;
                }
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(margin + 10, y);
                contentStream.showText(line);
                contentStream.endText();
                y -= lineHeight;
            }
            y -= 15;
        }

        contentStream.close();
        document.save(file);
        document.close();
        new Alert(Alert.AlertType.INFORMATION, "Resume saved to:\n" + file.getAbsolutePath()).showAndWait();
    }

    private String buttonBaseStyle() {
        return "-fx-font-size: 14px;-fx-background-color: #f8f8f8;-fx-border-color: #ccc;-fx-border-radius: 5;-fx-background-radius: 5;-fx-cursor: hand;";
    }

    private String buttonHoverStyle() {
        return "-fx-font-size: 14px;-fx-background-color: #e0e0ff;-fx-border-color: #6C4DFF;-fx-border-radius: 5;-fx-background-radius: 5;-fx-cursor: hand;-fx-effect: dropshadow(gaussian, #6C4DFF, 6, 0.3, 0, 2);";
    }
}
