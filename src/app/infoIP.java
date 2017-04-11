package app;


import java.util.*;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;

public class infoIP extends Packets {
    Ip4 ip4 = new Ip4();
    ArrayList<JPacket> packets;

    public infoIP(){
        packets = new ArrayList<>();
        packets = getPackets();
    }

    public Set<String> getIPAdderres(){
        // method gets a set of all listOfURls from parent class manipulates them to get data
        HashSet<String> IPadderes = new HashSet<>();

        for (JPacket pack: packets){

            if(pack.hasHeader(ip4)){
                byte[] myListSource;
                myListSource = ip4.source();
                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(myListSource);
                IPadderes.add(IPString);

            }

        }
        return IPadderes;
    }






}
