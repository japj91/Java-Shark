package app;
import java.io.File;
import java.util.*;

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;

public class ipAddresses {
    public Set add(File fIle){

        final StringBuilder errbuf = new StringBuilder();
        System.out.println(fIle.getName());
        System.out.println(fIle.getAbsolutePath());
        final Pcap pcap = Pcap.openOffline(fIle.getAbsolutePath(), errbuf);

        HashSet<String> SourceIP = new HashSet<>();
        HashSet<String> DestIP = new HashSet<>();
        pcap.loop(-1, new JPacketHandler<StringBuilder>() {

            Ip4 ip = new Ip4();
            byte[] myListSource = new byte[10];
            byte[] myListDest = new byte[10];

            @Override
            public void nextPacket(JPacket jPacket, StringBuilder stringBuilder) {
                if (jPacket.hasHeader(ip)){
                    myListSource = ip.source();
                    myListDest  = ip.destination();
                    String sourceIPString = org.jnetpcap.packet.format.FormatUtils.ip(myListSource);
                    String DestIpString = org.jnetpcap.packet.format.FormatUtils.ip(myListDest);

                    SourceIP.add(sourceIPString);
                    DestIP.add(DestIpString);
                }
            }
        }, errbuf);
        System.out.println(DestIP);
        return SourceIP;
    }
}
