package GUI;

import app.*;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 *Created by Jap Johal A00980851 and Mohammed Bajaman A00991074
 * The controller class acquires the latest information from the app package and uses it to insert information into
 * the GUI through the FXML package.
 *
 */

public class Controller {

    @FXML
    TextField mbTotal;

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
    private TextField sourceHost;

    @FXML
    private ListView portView;


    public void MainWindowLoader() throws IOException, GeoIp2Exception {
        // when open file is chossen comes here
        // users selects file and then the popup is filled up
        // program does nothing if no file is selected

        File file = load();

        if (file != null) {

            ArrayList<File> tempFile = ShareableData.getInstance().getFile();
            tempFile.add(file);
            ipLoad(file);

            genInfo(file);
            TCP(file);

            setNonEditable();
            IpLookUP(file);
        }
    }

    private void IpLookUP(File file) throws IOException, GeoIp2Exception {
        databaseIP databaseIP = new databaseIP();
        databaseIP.load(file);

        ArrayList<String> map = databaseIP.getLocationByIP();
        ObservableList<String> items = FXCollections.observableArrayList(map);

        typesList.setItems(items);
    }

    private void TCP(File file) {
        InfoTCP tcp = new InfoTCP();
        tcp.load(file);

        String IpSource = tcp.getOriginHost();
        ArrayList<String> user = ShareableData.getInstance().getHostUserList();

        user.add(IpSource.trim());
        ArrayList<String> ports = tcp.getPorts();

        ObservableList<String> list = FXCollections.observableArrayList(ports);

        sourceHost.setText(IpSource);
        portView.setItems(list);
    }

    public void openNetworkAnalysis() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/NetworkAnalysis.fxml"));
            Parent root1 = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("JM Networks");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openURL() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/URL.fxml"));
            Parent root1 = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("JM Networks");
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File load() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            return null;
        }
        return file;
    }

    public void ipLoad(File file) {

        infoIP infoIP = new infoIP();
        infoIP.load(file);
        Set<String> set = infoIP.getIPAddresses();

        ObservableList<String> items = FXCollections.observableArrayList(set);
        list.setItems(items);

    }

    public void genInfo(File file) {
        generalStats stats = new generalStats();
        stats.load(file);

        packetTotal.setText(stats.numPackets());
        longest.setText(stats.largestPacket());
        shortest.setText(stats.shortestPacket());
        time.setText(stats.timeForCapture() + " secs");
        mbTotal.setText(stats.networkTraffic());
    }

    public void alertMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Source Host is defined as:\n\n- A user who is making SYN requests\n- Their ACK flag is NOT SET");

        alert.showAndWait();
    }

    public void exitProgram() {
        System.exit(0);
    }

    private void setNonEditable() {
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
