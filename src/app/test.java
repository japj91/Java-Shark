package app;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import javafx.scene.chart.XYChart;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.omg.PortableServer.IdAssignmentPolicyValue;
import sun.plugin2.ipc.IPCFactory;
import sun.reflect.generics.tree.Tree;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;


public class test extends Packets {
    ArrayList<JPacket> jPackets = new ArrayList<>();
    Ip4 ip4 = new Ip4();

    public test(){
        jPackets = new ArrayList<>();
        jPackets = getPackets();
    }


    public ArrayList<String> print() throws IOException, GeoIp2Exception {
        TreeMap<String,String> set = new TreeMap<>();

        File database = new File("C:\\Users\\jap\\Documents\\GitHub\\JavaGUI\\src\\GeoLite2-Country.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        for (JPacket jPacket: jPackets){

            if (jPacket.hasHeader(ip4)){

                byte[] xx = ip4.source();
                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(xx);

                InetAddress ipAddress = InetAddress.getByName(IPString);
                CountryResponse response = reader.country(ipAddress);
                Country country = response.getCountry();

                if (set.containsKey(IPString)){
                    set.put(country.getName(),IPString);
                }
                else{
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(IPString);
                    set.put(country.getName(),IPString);
                }
            }
        }
        return  retrurnArrayList(set);
    }
    private ArrayList<String> retrurnArrayList(TreeMap<String,String> map) {
        Set<String> lol = map.keySet();
        ArrayList<String> list = new ArrayList<>();
        for (String temp : lol) {
            String a = map.get(temp);
            String tempp = String.format("%s   %s", temp,a);
            list.add(tempp);
        }
        return list;
    }
}
