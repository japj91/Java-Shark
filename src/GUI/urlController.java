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
 * loading of the websites visited
 * might need to get rid of second filtered tab
 */
public class urlController implements Initializable {

    @FXML
    private ListView noFilterURL;

    @FXML
    private ListView filterURL;


    public void initialize(URL location, ResourceBundle resources) {
        ListLoader(noFilterURL,1);
        ListLoader(filterURL,0);


    }

    private void ListLoader(ListView list, int x){
        ArrayList<File> fileList = ShareableData.getInstance().getFile(); // get the files
        File file = fileList.get(0);

        infoHttp http = new infoHttp();
        http.load(file);

        Set<String> set = http.listOfURls(x);
        ObservableList<String> items = FXCollections.observableArrayList(set);
        list.setItems(items);

    }
}
