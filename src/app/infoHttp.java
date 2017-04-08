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
    public infoHttp(){}

    public HashSet<String> listOfURls(int x){
        // looking for all urls that were visited.
        ArrayList<JPacket> packets = getPackets();
        Http http = new Http();
        HashSet<String> set = new HashSet<>();
        Tcp tcp = new Tcp();


        for (JPacket packet:packets){
            if (packet.hasHeader(http)&& packet.hasHeader(tcp) && !http.isResponse()){

                String temp =http.fieldValue((Http.Request.Referer));
                String urlVisited = String.valueOf(temp);

                if (!urlVisited.equals("null")){ // getting rid of null values
                    set.add(urlVisited);
                }
            }
        }
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
