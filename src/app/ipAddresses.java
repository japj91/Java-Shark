package app;


import java.io.File;
import java.util.*;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;

public class ipAddresses extends Packets {
    Ip4 ip4 = new Ip4();
    Ip6 ip6 = new Ip6();
    ArrayList<JPacket> packets;

    public ipAddresses(){
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
