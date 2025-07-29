package com.superx;


import javafx.scene.control.Button;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrepInstaStyledPage {

    VBox subMenuBox = new VBox(5);
    private Stage mainStage;
    

    public Scene show(Stage stage) {
        this.mainStage = stage;

        
        HBox navMenu = new HBox(30);
        navMenu.setAlignment(Pos.CENTER_LEFT);
        navMenu.setPadding(new Insets(5, 20, 5, 20));
        //navMenu.setStyle("-fx-background-color: #cce5ff; -fx-border-color: lightgrey;");
        navMenu.setStyle("-fx-background-color: linear-gradient(to right, #4e88bcff, #00f2fe);");
        navMenu.setPrefWidth(700); 
        navMenu.setMaxHeight(40);// Or any smaller value you want
        VBox navBar = new VBox();
navBar.setPrefWidth(200);  // or your desired width

// Set background image using CSS-style string
BackgroundImage bgImage = new BackgroundImage(
    new Image("file:resources/navbg.jpg", true), // Update path accordingly
    BackgroundRepeat.NO_REPEAT,
    BackgroundRepeat.NO_REPEAT,
    BackgroundPosition.DEFAULT,
    new BackgroundSize(100, 100, true, true, true, false)
);
navBar.setBackground(new Background(bgImage));

        // Label aboutUs = new Label("AboutUs");
        // aboutUs.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        // aboutUs.setOnMouseClicked(e -> openAboutUsPage()); //  add action
        // navMenu.getChildren().add(aboutUs); // add it to the nav bar


        // BACK BUTTON TO GO TO EXPLORE PAGE
        Button backButton = new Button("<Back");
    backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14px; -fx-font-weight: bold;");


      backButton.setOnAction(e -> {
    try {
        Stage exploreStage = new Stage();
        new Explore().start(exploreStage); // Launch Explore page
        stage.close(); // Close PrepInstaStyledPage
    } catch (Exception ex) {
        ex.printStackTrace();
    }  
     });

    Label aboutUs = createMenuLabel("About Us >");
    aboutUs.setOnMouseClicked(e -> setSubMenu("About Us >"));

// Add to navMenu
    VBox mainMenuBox = new VBox(5);
    mainMenuBox.setPadding(new Insets(20));
    mainMenuBox.setStyle("-fx-background-color: #f1f3f6;");
    mainMenuBox.setPrefWidth(300);

    subMenuBox.setPadding(new Insets(20));
    subMenuBox.setStyle("-fx-background-color: #ffffff;");
    subMenuBox.setPrefWidth(950);
    subMenuBox.setPrefHeight(800);

    navMenu.getChildren().addAll(backButton,aboutUs); // Add it before the logo
    ImageView sideImage = new ImageView(new Image(getClass().getResource("/Assets/Image/backg1.jpeg").toExternalForm()));
    sideImage.setFitWidth(300); 
    sideImage.setPreserveRatio(true);
    sideImage.setPreserveRatio(true);
    mainMenuBox.getChildren().addAll(sideImage);

    ScrollPane scrollPane = new ScrollPane(subMenuBox);
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background-color: transparent;");

    HBox rightContentBox = new HBox(20, scrollPane, sideImage);
    rightContentBox.setAlignment(Pos.TOP_LEFT);
    rightContentBox.setPadding(new Insets(20));

    HBox leftAndSubMenu = new HBox(mainMenuBox, rightContentBox);


        HBox logoBox = new HBox(5); 
        logoBox.setAlignment(Pos.CENTER_LEFT);  
        logoBox.setPadding(new Insets(10, 0, 10, 0));

        ImageView logoImage = new ImageView(new Image(getClass().getResource("/Assets/Image/smrthirelogo.png").toExternalForm()));
        logoImage.setFitHeight(150);
        logoImage.setPreserveRatio(true);

        navMenu.setSpacing(700); // Adjust this spacing according to your window width
        logoBox.getChildren().addAll(logoImage);
        navMenu.getChildren().add(logoBox);


        Label prep = createMenuLabel("Preparation   >");
        prep.setOnMouseClicked(e -> setSubMenu("Aptitude", "Fundamentals", "Programming"));           

        Label quiz = createMenuLabel("Quiz   >");
        quiz.setOnMouseClicked(e -> setSubMenu("Aptitude Quiz", "Fundamentals Quiz", "Programming Quiz"));

        Label company = createMenuLabel("Company Wise Questions   >");
        company.setOnMouseClicked(e -> setSubMenu(
                "Accenture", "BNY", "Infosys", "Google", "Cognizant", "Colgate", "Dessult",
                "IBM", "Microsoft", "PTC", "TCS", "Tibco","HCL Technologies","Vmware","Capgemini","Persistant System","Zensar",
                "NovoSoft"));

        Label mock = createMenuLabel("Mock Test >");
        mock.setOnMouseClicked(e -> setSubMenu("Start Test"));
        
        Label mockIN=createMenuLabel("Mock Interview >");
        mockIN.setOnMouseClicked(e->setSubMenu("Start Mock Interview >"));

        Label resume = createMenuLabel("Resume   >");
        resume.setOnMouseClicked(e -> setSubMenu("Create Your Resume"));

        Label feedback = createMenuLabel("Feedback   >");
        feedback.setOnMouseClicked(e -> setSubMenu("Submit Feedback"));

        Label interviewEX = createMenuLabel("InterviewEX >");
        interviewEX.setOnMouseClicked(e -> setSubMenu("InterviewEX >"));
        
        
        
        mainMenuBox.getChildren().addAll(prep, quiz, company, mock,interviewEX ,resume,mockIN,feedback,aboutUs);


        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(15));
        footer.setStyle("-fx-background-color: #222;");
        Label offer = new Label("Smart Interview Preparation System");
        offer.setStyle("-fx-text-fill: lightgreen; -fx-font-size: 20;");
        footer.getChildren().add(offer);

        BorderPane root = new BorderPane();
        root.setTop(navMenu);
        root.setLeft(leftAndSubMenu);
        root.setBottom(footer);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f1f3f6, #d6e4f0);");
        // Get the screen bounds (excluding taskbar)
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

        Scene scene = new Scene(root, 1100, 1002);
        stage.setScene(scene);
        stage.setTitle("SmartHire");
        stage.show();
        return scene;
    }

   
    private Label createMenuLabel(String text) {
        Label label = new Label(text);
        label.setFont(new Font(18));
        label.setPadding(new Insets(10));
        label.setStyle("-fx-text-fill: #1c1d1f;");
        label.setOnMouseEntered(e -> label.setStyle("-fx-background-color: #e1e8f0; -fx-text-fill: #1c1d1f;"));
        label.setOnMouseExited(e -> label.setStyle("-fx-background-color: transparent; -fx-text-fill: #1c1d1f;"));
        return label;
    }

    private void setSubMenu(String... options) {
        subMenuBox.getChildren().clear();

        for (int i = 0; i < options.length; i += 2) {
            HBox row = new HBox(20);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(10));

            HBox firstOption = createCompanyOption(options[i]);
            row.getChildren().add(firstOption);

            if (i + 1 < options.length) {
                HBox secondOption = createCompanyOption(options[i + 1]);
                row.getChildren().add(secondOption);
            }

            subMenuBox.getChildren().add(row);
        }
    }

    private HBox createCompanyOption(String optText) {
        HBox optionBox = new HBox(10);
        optionBox.setAlignment(Pos.CENTER_LEFT);

       ImageView logo = null;
        if (optText.equals("Accenture")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Accenture-Logo.png").toExternalForm()));
        } else if (optText.equals("BNY")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/BNY.png").toExternalForm()));
        } else if (optText.equals("Infosys")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/infosys.png").toExternalForm()));
        } else if (optText.equals("Google")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Google.png").toExternalForm()));
        } else if (optText.equals("Cognizant")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Cognizant.png").toExternalForm()));
        } else if (optText.equals("Colgate")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/colgate.png").toExternalForm()));
        } else if (optText.equals("Dessult")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/dessult.png").toExternalForm()));
        } else if (optText.equals("IBM")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/IBM.png").toExternalForm()));
        } else if (optText.equals("Microsoft")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/microsoft.png").toExternalForm()));
        } else if (optText.equals("PTC")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/PTC.png").toExternalForm()));
        } else if (optText.equals("TCS")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/TCS.png").toExternalForm()));
        } else if (optText.equals("Tibco")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/tibco.jpeg").toExternalForm()));
        }else if (optText.equals("Quick Heal")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/quick.png").toExternalForm()));
        }else if (optText.equals("HSBC")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/HSBC.png").toExternalForm()));
        }else if (optText.equals("Oracle")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/oracle.png").toExternalForm()));
        }else if (optText.equals("Deolitte")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/deolitte.webp").toExternalForm()));
        }else if (optText.equals("HCL Technologies")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/HCL_Technologies-Logo.wine.png").toExternalForm()));
        }else if (optText.equals("Persistant System")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/persistant.jpeg").toExternalForm()));
        }else if (optText.equals("Vmware")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Vmware.png").toExternalForm()));
        }else if (optText.equals("Capgemini")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/capgemini.png").toExternalForm()));

        }else if (optText.equals("Zensar")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Zensar.jpg").toExternalForm()));
        }else if (optText.equals("NovoSoft")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Novosoft.png").toExternalForm()));
        }
        
      if (optText.equals("Aptitude")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/apti.png").toExternalForm()));
        } else if (optText.equals("Programming")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/programming.png").toExternalForm()));
        }else if (optText.equals("Fundamentals")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Prepeartion.png").toExternalForm()));
        }
         

      if (optText.equals("Aptitude Quiz")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/atiQuiz.png").toExternalForm()));
        } else if (optText.equals("Programming Quiz")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/ProgramQuiz.png").toExternalForm()));
        }else if (optText.equals("Fundamentals Quiz")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/fund1Quiz.png").toExternalForm()));
        }

     if (optText.equals("InterviewEX >")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/feedback1.png").toExternalForm()));
      }
      if (optText.equals("Start Test")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/apti1mock.png").toExternalForm()));
            
        }
      if (optText.equals("Submit Feedback")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/feedback1.png").toExternalForm()));
      }
       if (optText.equals("About Us >")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/Aboutus.jpeg").toExternalForm()));
      }
      if (optText.equals("Create Your Resume")) {
            logo = new ImageView(new Image(getClass().getResource("/Assets/Image/resume.png").toExternalForm()));
      }
      
     

    
        if (logo != null) {
            logo.setFitWidth(50);
            logo.setFitHeight(50);
        }
        Label opt = new Label(optText);
        opt.setFont(new Font(16));

        if (logo != null) {
            optionBox.getChildren().addAll(logo, opt);
        } else {
            optionBox.getChildren().add(opt);
        }
       if (optText.equals("Aptitude Quiz")) {
           optionBox.setOnMouseClicked(event -> openAptitude2Page());
       }
       if (optText.equals("Programming Quiz")) {
           optionBox.setOnMouseClicked(event -> openProgrammings2Page());
       }
       if (optText.equals("Fundamentals Quiz")) {
           optionBox.setOnMouseClicked(event -> openFundamental2Page());
       }

        if (optText.equals("Aptitude")) {
           optionBox.setOnMouseClicked(event -> openAptitudePage());
       }
       if (optText.equals("Programming")) {
           optionBox.setOnMouseClicked(event -> openProgrammingsPage());
       }
       if (optText.equals("Fundamentals")) {
           optionBox.setOnMouseClicked(event -> openFundamentalPage());
       }
        if (optText.equals("About Us >")) {
           optionBox.setOnMouseClicked(event -> openAboutUSPage());
       }



        if (optText.equals("Start Mock Interview >")) {
            optionBox.setOnMouseClicked(event -> openMockInterviewPage());
        }

        if (optText.equals("Accenture")) {
           optionBox.setOnMouseClicked(event -> openAccenturePage()); // Add this
        }
        if (optText.equals("PTC")) {
           optionBox.setOnMouseClicked(event -> openPTCPage()); // Add this
        }
        if (optText.equals("Cognizant")) {
           optionBox.setOnMouseClicked(event -> openCognizantPage()); //  Add this
        }

        if (optText.equals("Capgemini")) {
           optionBox.setOnMouseClicked(event -> openCapgeminiPage()); // Add this
        }
        if (optText.equals("Google")) {
           optionBox.setOnMouseClicked(event -> openGooglePage()); // Add this
        }

         if (optText.equals("Start Test")) {
            optionBox.setOnMouseClicked(event -> openMockTestPage());
        }

         if (optText.equals("InterviewEX >")) {
            optionBox.setOnMouseClicked(event -> openStudentEXPage());
        }

        if (optText.equals("Submit Feedback")) {
           optionBox.setOnMouseClicked(event -> openFeedbackPage());
        }

       if (optText.equals("Create Your Resume")) {
            optionBox.setOnMouseClicked(event -> openResumePage());
        }

        optionBox.setPadding(new Insets(10));
        optionBox.setPrefWidth(200);
        optionBox.setStyle("-fx-text-fill: #1c1d1f; -fx-border-color: #d6e4f0; -fx-border-width: 1; -fx-background-radius: 5;");
        //optionBox.setOnMouseEntered(e -> optionBox.setStyle("-fx-background-color: #d6e4f0; -fx-border-radius: 5;"));
        //optionBox.setOnMouseExited(e -> optionBox.setStyle("-fx-border-color: #d6e4f0; -fx-border-width: 1; -fx-background-radius: 5;"));

        return optionBox;
    }
    private void openMockInterviewPage() {
       Mock_Interview mi = new Mock_Interview();
       Scene InterviewScene = mi.creatScene(mainStage,() -> show(mainStage));
       mainStage.setScene(InterviewScene);
    }

    private void openAboutUSPage() {
       AboutUs as = new AboutUs();
       Scene AboutScene = as.createScene(mainStage,() -> show(mainStage));
       mainStage.setScene(AboutScene);
    }

    private void openAccenturePage() {
       AccenturePage accenturePage = new AccenturePage();
       Scene accentureScene = accenturePage.creatScene(mainStage,() -> show(mainStage));
       mainStage.setScene(accentureScene);
    }
    private void openPTCPage() {
       PTC ptcPage = new PTC();
       Scene PTCScene = ptcPage.creatScene(mainStage,()->show(mainStage));
       mainStage.setScene(PTCScene);
    }
    private void openCapgeminiPage() {
       Capgemini cp = new Capgemini();
       Scene CapgeminiScene = cp.creatScene(mainStage,()->show(mainStage));
       mainStage.setScene(CapgeminiScene);
    }
    private void openGooglePage() {
       Google gl= new Google();
       Scene GoogleScene = gl.creatScene(mainStage,()->show(mainStage));
       mainStage.setScene(GoogleScene);
    }
    private void openCognizantPage() {
       Cognizant cg=new Cognizant();
       Scene CognizantScene = cg.creatScene(mainStage,()->show(mainStage));
       mainStage.setScene(CognizantScene);
    }

    private void openStudentEXPage() {
       FresherEX ex=new FresherEX();
       Scene fresherExScene = ex.creatScene(mainStage,()->show(mainStage));
       mainStage.setScene(fresherExScene);
    }


    private void openAptitudePage() {
    Aptitude aptitudePage = new Aptitude();
    try {
        Scene aptitudeScene = aptitudePage.createScene(mainStage); // Same stage
        mainStage.setScene(aptitudeScene);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
  private void openProgrammingsPage() {
    programming pr=new programming();
    Scene programmingScene = pr.creatScene(mainStage);
    mainStage.setScene(programmingScene);
   }

    private void openFundamentalPage() {
    Fundamentals fundamentalsPage = new Fundamentals();
    Scene fundamentalsScene = fundamentalsPage.createScene(mainStage);
    mainStage.setScene(fundamentalsScene);
    }


      private void openAptitude2Page() {
    Aptitude2 aptitudePage = new Aptitude2();
    try {
        Scene aptitudeScene = aptitudePage.createScene(mainStage); // Same stage
        mainStage.setScene(aptitudeScene);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
  private void openProgrammings2Page() {
    Programming2 pr=new Programming2();
    Scene programmingScene = pr.creatScene(mainStage);
    mainStage.setScene(programmingScene);
}

    private void openFundamental2Page() {
    Fundamentals2 fundamentalsPage = new Fundamentals2();
    Scene fundamentalsScene = fundamentalsPage.createScene(mainStage);
    mainStage.setScene(fundamentalsScene);
}

    private void openMockTestPage() {
    Mock_Test1 mockTestPage = new Mock_Test1();
    try {
        Scene mockTestScene = mockTestPage.creatScene(mainStage,()->show(mainStage));
        mainStage.setScene(mockTestScene);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private void openResumePage() {
    Resume resumePage = new Resume(mainStage, () -> {
        // Back button action: navigate back to PrepInstaStyledPage
        show(mainStage);
    });

    Scene resumeScene = resumePage.createScene();
    mainStage.setScene(resumeScene);
    }

  private void openFeedbackPage() {
    Feedbackpage feedbackPage = new Feedbackpage();
    try {
        Scene feedbackScene = feedbackPage.createScene(mainStage);
        mainStage.setScene(feedbackScene);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


  public Scene createMainScene(Stage mainStage2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createMainScene'");
  }
}

