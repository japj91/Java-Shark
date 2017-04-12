package app;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.*;

import java.io.File;
import java.util.ArrayList;

/**
 *Created by Jap Johal A00980851 and Mohammed Bajaman A00991074
 * Super class required to load a file across all subclasses by creating an array list of packets.
 */

public class Packets {

    private ArrayList<JPacket> packets;

    public Packets() {
        packets = new ArrayList<>();
    }

    public void load(File file) {

        final StringBuilder errbuf = new StringBuilder();
        Pcap pcap = Pcap.openOffline(file.getAbsolutePath(), errbuf);

        pcap.loop(Pcap.LOOP_INFINITE, (JPacketHandler<StringBuilder>) (packet, stringBuilder) -> packets.add(packet), errbuf);
    }

    public ArrayList<JPacket> getPackets() {
        return packets;
    }

}

