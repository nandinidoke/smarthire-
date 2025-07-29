package com.superx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Cpp {

    VBox notesLayout;

    public Scene creatScene(Stage mainStage) {

        // Optional Icon + Title
        Label titleLabel = new Label("Complete C++ Notes");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.web("#2e86de"));

        // If you have a C++ icon image, uncomment the below:
        // ImageView icon = new ImageView(new Image("cpp_icon.png"));
        // icon.setFitHeight(40);
        // icon.setFitWidth(40);
        // HBox titleBox = new HBox(10, icon, titleLabel);

        // Notes Content
        String fullNotes = """
✅✅ 1. Basics of C++ 
• Syntax: Similar to C with classes and objects. 
• Header files: 
#include <iostream> 
using namespace std; 
• Main Function: 
int main() { 
    cout << "Hello, World!";
    return 0; 
} 

• Input/Output: 
cin >> variable; 
cout << variable;

✅ 2. Data Types & Variables 
• int, float, double, char, bool 
• Constants: const int x = 10; 
• Type casting: float(x), int(y) 

✅ 3. Control Structures 
• If/Else: 
if (a > b) { ... } else { ... } 
• Switch: 
switch(x) { 
    case 1: ...; break; 
    default: ...; 
} 
• Loops: 
for (int i = 0; i < n; i++) { ... } 
while(condition) { ... } 
do { ... } while(condition); 

✅ 4. Functions 
int add(int a, int b) { 
    return a + b; 
} 
• Pass by value/reference 
• Function overloading 

✅ 5. Object-Oriented Programming (OOP) 
• Class & Object: 
class Student { 
public: 
    int age; 
    void display() { 
        cout << age; 
    } 
}; 
• Constructor & Destructor 
• Inheritance 
• Polymorphism (Compile-time: overloading, Run-time: overriding) 
• Encapsulation and Abstraction 

✅ 6. Arrays & Strings 
int arr[5] = {1, 2, 3, 4, 5}; 
string str = "Hello"; 
• String Functions: length(), substr(), find() 

✅ 7. Pointers 
int x = 10; 
int* p = &x; 
cout << *p; 
• Pointer to pointer: int** ptr2 = &p; 
• Dynamic memory: new, delete 

✅ 8. STL (Standard Template Library) – Very Important 
Containers: 
• Vector: 
vector<int> v; 
v.push_back(1); 
v.size(); v.begin(); v.end(); v.clear(); 
• Stack: stack<int> s; s.push(); s.pop(); s.top(); 
• Queue, Priority Queue 
• Set, Multiset 
• Map, Unordered Map 
Algorithms: 
sort(v.begin(), v.end()); 
reverse(v.begin(), v.end()); 
binary_search(v.begin(), v.end(), key); 

✅ 9. Recursion & Backtracking 
• Function calling itself with base condition. 
• Common problems: factorial, Fibonacci, subsets, permutations 

✅ 10. Important Concepts for Placements 
• Time & Space Complexity 
• Sorting Algorithms: Bubble, Selection, Merge, Quick 
• Searching: Linear, Binary 
• Bit Manipulation 
• Greedy, DP (Dynamic Programming) 
• Graphs, Trees, Linked List, Hashing 

📌 Quick Tips: 
• Practice questions on LeetCode, GFG, HackerRank 
• Master STL – 30–40% of questions can be solved easily using it. 
• Understand recursion, pointers, and memory well. 
""";

        TextArea notesArea = new TextArea(fullNotes);
        notesArea.setWrapText(true);
        notesArea.setEditable(false);
        notesArea.setFont(Font.font("Consolas", 14));
        notesArea.setStyle("""
            -fx-control-inner-background: #fefefe;
            -fx-font-family: 'Consolas';
            -fx-font-size: 14px;
            -fx-text-fill: black;
            -fx-border-color: lightgray;
            -fx-border-radius: 5;
            -fx-focus-color: transparent;
        """);

        ScrollPane scrollPane = new ScrollPane(notesArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefViewportHeight(600);

        // Buttons
        Button downloadBtn = new Button("Download PDF");
        Button backButton = new Button("← Back");

        String buttonStyle = """
            -fx-background-color: #2ecc71;
            -fx-text-fill: white;
            -fx-font-size: 14px;
            -fx-padding: 8 16 8 16;
            -fx-background-radius: 6;
        """;

        downloadBtn.setStyle(buttonStyle);
        backButton.setStyle(buttonStyle.replace("#2ecc71", "#e74c3c"));

        downloadBtn.setOnMouseEntered(e -> downloadBtn.setStyle(buttonStyle.replace("#2ecc71", "#27ae60")));
        downloadBtn.setOnMouseExited(e -> downloadBtn.setStyle(buttonStyle));

        backButton.setOnMouseEntered(e -> backButton.setStyle(buttonStyle.replace("#2ecc71", "#c0392b")));
        backButton.setOnMouseExited(e -> backButton.setStyle(buttonStyle.replace("#2ecc71", "#e74c3c")));

        downloadBtn.setOnAction(e -> printToPDF());
        backButton.setOnAction(e -> {
            programming programmingPage = new programming();
            Scene programmingScene = programmingPage.creatScene(mainStage);
            mainStage.setScene(programmingScene);
        });

        HBox buttonRow = new HBox(20, downloadBtn, backButton);
        buttonRow.setAlignment(Pos.CENTER);

        // Notes Layout with shadow effect
        notesLayout = new VBox(20, titleLabel, scrollPane, buttonRow);
        notesLayout.setAlignment(Pos.TOP_CENTER);
        notesLayout.setPadding(new Insets(20));
        notesLayout.setMaxWidth(1000);
        notesLayout.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #dcdde1;
            -fx-border-width: 1px;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 4);
            -fx-background-radius: 10;
            -fx-border-radius: 10;
        """);

        // Root Layout
        VBox root = new VBox(notesLayout);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f0f0f0;");

        Scene scene = new Scene(root, 1000, 820);
        return scene;
    }

    public void printToPDF() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(null)) {
            boolean success = job.printPage(notesLayout);
            if (success) {
                job.endJob();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF Saved Successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save PDF.");
                alert.showAndWait();
            }
        }
    }

   
}