package com.superx;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Capgemini {

    public Scene creatScene(Stage mainStage,Runnable backAction) {
        mainStage.setTitle("Capgemini Technical Questions");

        Label header = new Label(" Capgemini Technical Interview Questions");
        header.setFont(new Font("Arial", 20));
        header.setStyle("-fx-padding: 15; -fx-font-weight: bold; -fx-text-fill: #2a2a2a;");

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(getCapgeminiQuestions());
        listView.setStyle("-fx-font-size: 14; -fx-padding: 10;");

        // Back button
        Button backButton = new Button("⬅ Back");
        backButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16; -fx-background-color: #7364e2ff;");
        backButton.setOnAction(e->backAction.run());

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(listView);
        root.setBottom(backButton);
        BorderPane.setMargin(backButton, new Insets(10));
        root.setStyle("-fx-background-color: #f9f9f9;");

        Scene scene = new Scene(root, 800, 600);
        return scene;
    }

    private List<String> getCapgeminiQuestions() {
        return List.of(
            "1. Difference between == and equals() in Java.",
            "2. What is a pointer? Explain dangling pointers.",
            "3. What is the difference between stack and heap memory?",
            "4. What is recursion? Write code for factorial using recursion.",
            "5. What are static variables and functions in C?",
            "6. Difference between compile-time and run-time polymorphism.",
            "7. What is the use of final, finally, and finalize() in Java?",
            "8. Write a program to check if a string is palindrome.",
            "9. What is constructor overloading?",
            "10. Explain the concept of exception handling with example.",
            "11. What are the four pillars of OOP?",
            "12. What is inheritance? Types of inheritance.",
            "13. What is encapsulation and abstraction?",
            "14. Difference between interface and abstract class.",
            "15. What is method overriding vs overloading?",
            "16. What is polymorphism with real-life example?",
            "17. What is constructor chaining in Java?",
            "18. Explain super and this keywords.",
            "19. Can a constructor be private? When to use it?",
            "20. What is the diamond problem in multiple inheritance?",
            "21. Write code for binary search.",
            "22. Difference between Array and LinkedList.",
            "23. What is a stack? Implement using array.",
            "24. What is a queue? Applications of queue.",
            "25. Explain tree traversal (inorder, preorder, postorder).",
            "26. What is a hash table? How is it implemented?",
            "27. Explain sorting algorithms (bubble, selection, merge).",
            "28. What is the time complexity of binary search?",
            "29. What is a graph? Types of graphs.",
            "30. Difference between BFS and DFS.",
            "31. Difference between primary key and unique key.",
            "32. What are joins in SQL? Types with example.",
            "33. Write a query to find the second highest salary.",
            "34. What is normalization? Explain 1NF, 2NF, 3NF.",
            "35. What is the difference between DELETE, DROP, and TRUNCATE?",
            "36. What is indexing in SQL?",
            "37. What are views and why are they used?",
            "38. Write a query to count duplicate records in a table.",
            "39. What is a foreign key constraint?",
            "40. Difference between clustered and non-clustered index.",
            "41. Difference between process and thread.",
            "42. What is a deadlock? How to prevent it?",
            "43. Explain paging and segmentation.",
            "44. What is context switching?",
            "45. What is a TCP handshake?",
            "46. Difference between TCP and UDP.",
            "47. What is IP address and MAC address?",
            "48. Explain DNS and how it works.",
            "49. OSI Model - explain all layers.",
            "50. What happens when you type google.com in your browser?"
        );
    }

}

