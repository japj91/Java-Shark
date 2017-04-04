package app;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.*;
import org.jnetpcap.protocol.tcpip.Tcp;
import java.util.ArrayList;

public class TCP {

    public ArrayList play(Pcap pcap){

        ArrayList<String> list = new ArrayList<String>();
        final StringBuilder errbuf = new StringBuilder();
        pcap.loop(-1, new JPacketHandler<StringBuilder>() {

            final Tcp tcp = new Tcp();
            public void nextPacket(JPacket packet, StringBuilder stringBuilder) {

                if(packet.hasHeader(tcp)){
                    String dest = String.valueOf(tcp.destination());
                    list.add(dest);
                }
            }
        }, errbuf);
        return list;
    }
}

