package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import app.*;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Controller {

    @FXML
    Label textArea;

    @FXML
    Label sourceHostLabel;

    @FXML
    ListView<String> list;

    @FXML
    ListView<String> openList;

    @FXML
    TextArea FileName;

    @FXML
    Button OpenFileChooser;

    @FXML
    TextField packetTotal;

    @FXML
    TextField time;

    @FXML
    TextField shortest;

    @FXML
    TextField longest;

    @FXML
    TextField sourceHost;

    @FXML
    String sourceIP;

    model model;

    String fileName;

    public Controller(){
        model = new model();
    }

    public void MainWindowLoader(){
        // when open file is chossen comes here
        // users selects file and then the popup is filled up
        // Can implement second window but windows arent able to communicate
        // error shows up but to show bassically make the open button launch openFilechooser then on second GUI make file button
        // launch MainWindowLoader

        File file = load();

        ipLoad(file);
        genInfo(file);
        TCP(file);
        networkAnalysis(file);


    }

    private void networkAnalysis(File file) {
        netAnalysis net = new netAnalysis();
        net.load(file);
        net.mapping();
        net.packetSize();
    }

    private void TCP(File file) {
        InfoTCP tcp = new InfoTCP();
        tcp.load(file);
        String ipAdderes ="";
        for(String x: tcp.getOriginHost()){  // for loop is here in case person is capturing traffic from their router.
            ipAdderes += x+"\n ";
        }
        ArrayList<String> user = ShareableData.getInstance().getList();
        user.add(ipAdderes.trim());
        System.out.println(user);
        sourceHost.setText(ipAdderes);
    }

    public void findFile(){
        // probally dead code
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        fileName = file.getAbsolutePath();
        System.out.println(fileName);

        textArea.setText(fileName);
        final StringBuilder errbuf = new StringBuilder();
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
            System.out.println(e.getCause());
        }
    }

    public File load(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file==null){
            System.out.println("No File Choosen");
            System.exit(1);
        }

        return file;
    }

    public void test(){
        // below is a test method
        // wanted to return pcap objects from Packets

        File file = new File("test.pcap");
        Packets tcp = new Packets();

        tcp.load(file);
        ArrayList<JPacket> j = tcp.getPackets();

        int xa = 0;

        for(JPacket x :j){
            Tcp tap = new Tcp();
            System.out.println(x.toString());
        }




    }

    public void ipLoad(File file){

        ipAddresses ipAddresses = new ipAddresses();
        ipAddresses.load(file);
        Set<String> set = ipAddresses.getIPAdderres();

        sourceIP = String.valueOf(set);
        ObservableList<String> items = FXCollections.observableArrayList(set);
        list.setItems(items);
    }

    public void genInfo(File file ){
        generalInfo gen = new generalInfo();
        gen.load(file);
        packetTotal.setText(gen.numPackets());

        longest.setText(gen.largestPacket());
        shortest.setText(gen.shortestPacket());
        time.setText(gen.timeForCapture()+" secs");

        gen.networkTraffic();

    }

    public void setColor(){
        sourceHostLabel.setTextFill(Color.web("#0076a3"));
    }
    public void normalColor(){
        sourceHostLabel.setTextFill(Color.web("#000"));
        System.out.println("ajp");
    }

}
