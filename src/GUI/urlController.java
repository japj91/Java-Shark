package GUI;

import app.ShareableData;
import app.infoHttp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by jap on 4/8/2017.
 */
public class urlController implements Initializable {

    @FXML
    private ListView noFilterURL;

    @FXML
    private ListView filterURL;


    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<File> tempList = ShareableData.getInstance().getFile();
        File file =tempList.get(0);
        infoHttp http = new infoHttp();
        http.load(file);

        Set<String> set = http.packets(1);
        ObservableList<String> items = FXCollections.observableArrayList(set);
        noFilterURL.setItems(items);

        Set<String> set1 = http.packets(0);
        ObservableList<String> items1 = FXCollections.observableArrayList(set1);
        filterURL.setItems(items1);



    }
}
