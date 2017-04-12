package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// common class among tcp udp. Ipv4 and 6
// what about the filter website list
// how about the maven geolocation questions
// make Ip objects once in packets class and grab them as needed
// make them static then easier to get ? ????
// pakcets saying replace with lambda
// make anohter small file with controller least used functions


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

