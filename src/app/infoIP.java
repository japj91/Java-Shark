package app;


import java.util.*;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

/**
 *Created by Jap Johal A00980851 and Mohammed Bajaman A00991074
 * This class retreives IPAddresses from the capture file.
 *
 */

public class infoIP extends Packets {
    Ip4 ip4 = new Ip4();
    ArrayList<JPacket> packets;

    public infoIP() {
        packets = new ArrayList<>();
        packets = getPackets();
    }

    public Set<String> getIPAddresses() {
        // method gets a set of all listOfURls from parent class manipulates them to get data
        HashSet<String> IPaddresses = new HashSet<>();

        for (JPacket pack : packets) {

            if (pack.hasHeader(ip4)) {
                byte[] myListSource;
                myListSource = ip4.source();
                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(myListSource);
                IPaddresses.add(IPString);

            }

        }
        return IPaddresses;
    }


}
