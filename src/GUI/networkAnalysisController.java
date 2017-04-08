package GUI;

import app.ShareableData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sun.reflect.generics.tree.Tree;

import java.net.URL;
import java.util.*;

/**
 * Created by jap on 4/7/2017.
 */
public class networkAnalysisController implements Initializable {



    @FXML
    private Parent root;

    @FXML
    BarChart<String,Integer> chart;

    @FXML
    CategoryAxis x;

    @FXML
    NumberAxis y;

    @FXML
    Label packetCount;


    public void closer(){
        System.out.println("Close not yet implemented");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // putting the graph togethere
        // have to get the source host
        // create a map that has the num of packets as the key b/c u only want to show top 5

        HashMap<String,Integer> map = ShareableData.getInstance().getHashMap();
        map.remove(ShareableData.getInstance().getList().get(0)); // removing the host user from graph the admin doesnt need to see how often they are the source

        TreeMap<Integer,String> orderedMap = createOrderedSet(map);  // creating a ordered map
        XYChart.Series series = new XYChart.Series<>();

        Set<Integer> keySet = orderedMap.keySet();
        System.out.println(orderedMap);
        Iterator<Integer> itr = keySet.iterator();
        int x =0;

        while (itr.hasNext() && x!=5){
            int key = itr.next();
            String value = orderedMap.get(key);
            series.getData().add(new XYChart.Data(value,key));
            x++;
            packetCount.setText(value);
        }
        chart.getData().addAll(series);
    }

    private TreeMap<Integer, String> createOrderedSet(HashMap<String,Integer> map){
       Set<String> keys = map.keySet();
       TreeMap<Integer,String> tempMap= new TreeMap<>();

       for(String temp:keys){
           tempMap.put(map.get(temp),temp);
       }
       return tempMap;
    }
}
