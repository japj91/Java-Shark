package app;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.*;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.io.File;
import java.util.ArrayList;
// made it so when you initailize class you need to send a file
// created a getter for a array list that contians all packets
public class TCP {
    private ArrayList<JPacket> JPackets;

    public  TCP(File file){
        // play creates a list of all pcap packets
        // every method can access list of all pcap to different operations
        // changed this method so its takes a file instead of a pcap object
        JPackets = new ArrayList<>();
        final StringBuilder errbuf = new StringBuilder();
        Pcap pcap = Pcap.openOffline(file.getAbsolutePath(),errbuf);

        pcap.loop(-1, new JPacketHandler<StringBuilder>() {

            final Tcp tcp = new Tcp();
            public void nextPacket(JPacket packet, StringBuilder stringBuilder) {
                JPackets.add(packet);
            }
        }, errbuf);


    }

    public ArrayList getJackets(){
        return JPackets;
    }

}

