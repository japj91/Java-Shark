package GUI;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import app.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class Controller {

    @FXML
    TextField mbTotal;

    @FXML
    private Label sourceHostLabel;

    @FXML
    private ListView<String> list;

    @FXML
    private TextField packetTotal;

    @FXML
    private TextField time;

    @FXML
    ListView typesList;

    @FXML
    private TextField shortest;

    @FXML
    private TextField longest;

    @FXML
    private ListView sourceHost;

    @FXML
    private ListView portView;


    public void MainWindowLoader() throws IOException, GeoIp2Exception {
        // when open file is chossen comes here
        // users selects file and then the popup is filled up
        // error shows up but to show bassically make the open button launch openFilechooser then on second GUI make file button
        // launch MainWindowLoader

        File file = load();

        if (file !=null) {
            ArrayList<File> tempFile = ShareableData.getInstance().getFile();
            tempFile.add(file);

            ipLoad(file);
            genInfo(file);
            TCP(file);
            setNonEditable();
            ports(file);
            temp(file);

        }
    }

    private void temp(File file) throws IOException, GeoIp2Exception {
        test test = new test();
        test.load(file);
        ArrayList<String> map = test.print();
        ObservableList<String> items = FXCollections.observableArrayList(map);
        typesList.setItems(items);

    }
    private void TCP(File file) {
        InfoTCP tcp = new InfoTCP();
        tcp.load(file);

        String IpSource = tcp.getOriginHost();

        ArrayList<String> user = ShareableData.getInstance().getHostUserList();
        user.add(IpSource.trim());

        ObservableList<String> items = FXCollections.observableArrayList(IpSource);
        sourceHost.setItems(items);
    }

    public void openNetworkAnalysis(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/NetworkAnalysis.fxml"));
            Parent root1 = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("JM Networks");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openURL(){

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/URL.fxml"));
            Parent root1 = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("JM Networks");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public File load(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file==null){
            return null;
        }


        return file;
    }

    public void ports(File file){
        InfoTCP port = new InfoTCP();

        port.load(file);
        ArrayList<String> ports = port.getPorts();

        ObservableList<String> items = FXCollections.observableArrayList(ports);
        portView.setItems(items);
    }

    public void ipLoad(File file){

        infoIP infoIP = new infoIP();
        infoIP.load(file);
        Set<String> set = infoIP.getIPAdderres();

        ObservableList<String> items = FXCollections.observableArrayList(set);
        list.setItems(items);

    }

    public void genInfo(File file ){
        generalStats gen = new generalStats();
        gen.load(file);

        packetTotal.setText(gen.numPackets());
        longest.setText(gen.largestPacket());
        shortest.setText(gen.shortestPacket());
        time.setText(gen.timeForCapture()+" secs");
        mbTotal.setText(gen.networkTraffic());

        ObservableList<String> items = FXCollections.observableArrayList(gen.packetTypes());
        typesList.setItems(items);


    }

    public void setColor(){
        sourceHostLabel.setTextFill(Color.web("#0076a3"));
    }

    public void normalColor(){
        sourceHostLabel.setTextFill(Color.web("#000"));
    }

    public void alertMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Source Host is defined as:\n\n- A user who is making SYN requests\n- Their ACK flag is NOT SET");

        alert.showAndWait();
    }

    public void exitProgram(){
        System.exit(0);
    }

    private void setNonEditable(){
        packetTotal.setEditable(false);
        longest.setEditable(false);
        shortest.setEditable(false);
        time.setEditable(false);
        mbTotal.setEditable(false);
        longest.setEditable(false);
        typesList.setEditable(false);
        list.setEditable(false);

    }

}
