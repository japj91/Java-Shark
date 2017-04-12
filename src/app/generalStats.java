package app;

import org.jnetpcap.packet.JPacket;

import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


import java.util.ArrayList;

/**
 * Created by jap on 4/5/2017.
 * General information class
 * Shortest longest time
 * wanted to put in packet calss but b/c its super class did not put in there
 */
public class generalStats extends Packets {
    ArrayList<JPacket> jPackets;
    Tcp tcp = new Tcp();
    Udp udp = new Udp();
    Ip4 ip4 = new Ip4();
    Ip6 ip6 = new Ip6();


    public generalStats() {
        jPackets = new ArrayList<>();
        this.jPackets = getPackets();
    }

    public String largestPacket() {
        int largest = Integer.MIN_VALUE;
        for (JPacket packet : jPackets) {
            if (packet.size() > largest) {
                largest = packet.size();
            }
        }
        return String.valueOf(largest);
    }

    public String shortestPacket() {
        int largest = Integer.MAX_VALUE;
        for (JPacket packet : jPackets) {
            if (packet.size() < largest) {
                largest = packet.size();
            }
        }
        return String.valueOf(largest);
    }

    public String numPackets() {
        return String.valueOf(jPackets.size());
    }

    public String timeForCapture() {
        JPacket firstPacket = jPackets.get(0);
        JPacket lastPacket = jPackets.get(jPackets.size() - 1);

        long first = firstPacket.getCaptureHeader().seconds();
        long last = lastPacket.getCaptureHeader().seconds();
        long diff = last - first;
        String timeDiff = String.valueOf(diff);
        return timeDiff;

    }

    public String networkTraffic() {
        // method tells u how many bytes are in the file
        int x = 0;
        for (JPacket Packet : jPackets) {

            x += Packet.size();// gets lenght of the packet

        }
        double totalSizeOfPackets = (double) x / 1000000;

        String size = String.format("%.2f", totalSizeOfPackets);

        return size;
    }

    public ArrayList<String> packetTypes() {
        ArrayList<String> map = new ArrayList<>();
        int udpCount = 0;
        int tcpCount = 0;
        int ipv4 = 0;
        int ipv6 = 0;

        for (JPacket packet : jPackets) {
            tcpCount += checkTCPheader(packet);
            udpCount += checkUDPHeader(packet);
            ipv4 += checkipv4Header(packet);
            ipv6 += checkIpv6Header(packet);
        }

        map.add(String.format("TCP packets %s", tcpCount));
        map.add(String.format("UDP packets %s", udpCount));
        map.add(String.format("IPv4 packets %s", ipv4));
        map.add(String.format("IPv6 packets %s", ipv6));
        return map;
    }

    private int checkipv4Header(JPacket packet) {
        if (packet.hasHeader(ip4)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int checkIpv6Header(JPacket packet) {
        if (packet.hasHeader(ip6)) {
            return 1;
        }
        return 0;
    }

    private int checkTCPheader(JPacket packet) {
        if (packet.hasHeader(tcp)) {
            return 1;
        }
        return 0;
    }

    private int checkUDPHeader(JPacket packet) {
        if (packet.hasHeader(udp)) {
            return 1;
        }
        return 0;
    }


}
