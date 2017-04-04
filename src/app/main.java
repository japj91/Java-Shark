package app;
/*
This main runs shows how ip addresses can be put in a set and displays in console.
 */

import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.packet.format.FormatUtils;
import java.util.HashSet;

public class main {
    public static void main(String[] args) {

        String FILENAME = "test3.pcap";
        StringBuilder errbuf = new StringBuilder();

        final Pcap pcap = Pcap.openOffline(FILENAME, errbuf);
        if (pcap == null) {
            System.err.println(errbuf); // Error is stored in errbuf if any

        } else {
            System.out.println("Pcap loaded!");
        }

        pcap.loop(11760, new JPacketHandler<StringBuilder>() {
            final Ip4 ip4 = new Ip4();
            byte[] dIP = new byte[4];
            byte[] sIP = new byte[4];
            HashSet<String> ips = new HashSet<>();

            public void nextPacket(JPacket packet, StringBuilder errbuf) {

                if(packet.hasHeader(Ip4.ID)){
                    packet.getHeader(ip4);

                    dIP = ip4.destination();
                    sIP = ip4.source();
                    String sourceIP = FormatUtils.ip(sIP);
                    String destIP = FormatUtils.ip(dIP);
                    if(ips.contains(sourceIP)){
                        System.out.println("Duplicate!");
                    } else {
                        ips.add(sourceIP);
                    }
                    if(ips.contains(destIP)){
                        System.out.println("Duplicate!");
                    } else {
                        ips.add(destIP);
                    }

                    System.out.printf("ip.dst = %s%n", destIP);
                    System.out.printf("ip.src = %s%n", sourceIP);
                    System.out.printf("Time to Live = %d%n", ip4.ttl());
                }
                System.out.printf("frame #%d%n", packet.getFrameNumber());
                System.out.println("Set status: " + ips);
            }
        }, errbuf);
    }
}
