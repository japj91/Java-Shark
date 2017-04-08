package app;


import java.io.File;
import java.util.*;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

public class ipAddresses extends Packets {

    public ipAddresses(){}

    public Set<String> getIPAdderres(){
        // method gets a set of all listOfURls from parent class manipulates them to get data
        HashSet<String> IPadderes = new HashSet<>();
        ArrayList<JPacket> packets = getPackets();

        for (JPacket pack: packets){
            Ip4 ip4 = new Ip4();

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
