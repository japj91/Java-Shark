package app;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.*;

import java.io.File;
import java.util.ArrayList;
// made it so when you initailize class you need to send a file
// created a getter for a array list that contians all packets


public class Packets {
    private ArrayList<JPacket> packets;

    public Packets(){
        packets = new ArrayList<>();
    }

    public void load(File file){
        // loading of all packets into a array list
        final StringBuilder errbuf = new StringBuilder();
        Pcap pcap = Pcap.openOffline(file.getAbsolutePath(),errbuf);

        pcap.loop(-1, new JPacketHandler<StringBuilder>() {


            public void nextPacket(JPacket packet, StringBuilder stringBuilder) {
                packets.add(packet);
            }
        }, errbuf);

    }

    public ArrayList<JPacket> getPackets(){
        return packets;
    }

}

