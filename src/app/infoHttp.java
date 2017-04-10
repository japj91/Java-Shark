package app;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * Created by jap on 4/8/2017.
 * Grab URLs that were visited by user
 */
public class infoHttp extends Packets{
    Http http = new Http();
    public infoHttp(){}

    public HashSet<String> listOfURls(int x){
        // looking for all urls that were visited.
        ArrayList<JPacket> packets = getPackets();

        HashSet<String> set = new HashSet<>();

        HashSet<String> string = new HashSet<>();

        for (JPacket packet:packets){
            if (packet.hasHeader(http)){

                String temp =http.fieldValue((Http.Request.Referer));
                String temp2 = http.fieldValue(Http.Request.Host);

                String w = String.valueOf(temp2);
               // System.out.println(w);

//                string.add(w);
                // can implement this will print names differently go to line 51 - 53 for print statment


                String urlVisited = String.valueOf(temp);

                if (!urlVisited.equals("null")){ // getting rid of null values
                    set.add(urlVisited);
                }
            }

        }
//        for (String xx:string){
//            System.out.println(xx);
//        }
        if (x==0) { // returns a filtered set
            return filteredSet(set);
        }
        return set;
    }

    private HashSet<String> filteredSet(HashSet<String> set){
        // filters for only websites
        // websites are defined as links that have "/" at the end.
        HashSet<String> hashSet = new HashSet<>();
        for (String temp : set) {
            if (temp.endsWith("/")) {
                hashSet.add(temp);
            }
        }
        return hashSet;
    }
}
