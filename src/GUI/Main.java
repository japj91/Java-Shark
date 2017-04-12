package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *Created by Jap Johal A00980851 and Mohammed Bajaman A00991074
 * Due: 12th April 2017
 * This program uses a capture file and provides useful statistics that can be used by IT personnel.
 * This includes information such as, sites visited, capture size, Geolocation of IPs, etc.
 *
 * ACIT 2515
 * Instructor : Rob Neilson
 * BCIT Semester 2 Assignment
 *
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/MainWindow.fxml"));
        primaryStage.setTitle("Break Down of Information");
        primaryStage.setScene(new Scene(root, 767, 474));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

