package GUI;

import app.ShareableData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

/**
 * Created by jap on 4/7/2017.
 */
public class networkAnalysisController implements Initializable {



    @FXML
    private Parent root;

    @FXML
    BarChart<String,Integer> byteChart;

    @FXML
    BarChart<String,Integer> packetChart;

    @FXML
    CategoryAxis x;

    @FXML
    NumberAxis y;

    @FXML
    Label packetCount;

    @FXML
    Label byteCount;


    public void closer(){
        System.out.println("Close not yet implemented");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setChart(ShareableData.getInstance().getBytesPerIP(), byteCount, byteChart);
        setChart(ShareableData.getInstance().getPacketsPerIp(), packetCount, packetChart);

    }

    private TreeMap<Integer, String> createOrderedSet(HashMap<String,Integer> map){
       Set<String> keys = map.keySet();
       TreeMap<Integer,String> tempMap= new TreeMap<>();

       for(String temp:keys){
           tempMap.put(map.get(temp),temp);
       }
       return tempMap;
    }

    private void setChart(HashMap<String,Integer> map, Label label, BarChart chart){
        // putting the graph togethere
        // have to get the source host
        // create a map that has the num of listOfURls as the key b/c u only want to show top 5
        map.remove(ShareableData.getInstance().getList().get(0)); // removing the host user from graph the admin doesnt need to see how often they are the source

        TreeMap<Integer,String> orderedMap = createOrderedSet(map);  // creating a ordered map
        XYChart.Series series = new XYChart.Series<>();

        Set<Integer> keySet = orderedMap.descendingKeySet();
        Iterator<Integer> itr = keySet.iterator();
        int x =0;

        while (itr.hasNext() && x!=5){
            int key = itr.next();
            String value = orderedMap.get(key);
            series.getData().add(new XYChart.Data(value,key));
            if(x==0){
                label.setText(value);
            }
            x++;

        }
        chart.getData().addAll(series);
    }


}
