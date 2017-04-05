package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jnetpcap.Pcap;

import app.*;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class Controller {

    @FXML
    Label textArea;

    public javafx.scene.control.ListView<String> list = new javafx.scene.control.ListView<>();

    public ProgressBar bar;

    model model;
    public File file;
    String fileName;


    public Controller(){
        model = new model();
    }

    public void findFile(){

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        fileName = file.getAbsolutePath();
        System.out.println(fileName);

        textArea.setText(fileName);
        final StringBuilder errbuf = new StringBuilder();
    }

    public void runDiagnostic(){


        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/MainWindow.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("JM Networks");
            stage.setScene(new Scene(root1));
            stage.show();


        }catch (Exception e){
            System.out.println("cannot find new MainWindow");
        }
        test();
    }

    public void load(){
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(null);
        fileName = file.getAbsolutePath();

        ipAddresses atm = new ipAddresses();
        Set<String> temp = atm.add(file);

        StringBuilder build = new StringBuilder();
        build.append("IP Addreeses in Pcap File\n");
        for (String line:temp){
            build.append(line+"\n");

        }
        String lineew = build.toString();
        ObservableList<String> items = FXCollections.observableArrayList(lineew);
        list.setItems(items);
        System.out.println(lineew);

    }

    public void test(){
        // below is a test method
        // wanted to return pcap objects from TCP
        /*
        File file = new File("test.pcap");
        TCP tcp = new TCP(file);


        ArrayList<JPacket> j = tcp.getJackets();

        for(JPacket x :j){
            Tcp tap = new Tcp();
            if(x.hasHeader(tap)){
                //System.out.println(x.toString());
            }
        }
        */
    }


}
