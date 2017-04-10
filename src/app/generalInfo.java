package app;

import com.sun.xml.internal.ws.api.message.Packet;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.annotate.Protocol;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jap on 4/5/2017.
 * General information class
 * Shortest longest time
 * wanted to put in packet calss but b/c its super class did not put in there
 */
public class generalInfo  extends Packets{
    ArrayList<JPacket> jPackets;
    Tcp tcp = new Tcp();
    Udp udp = new Udp();


    public generalInfo(){
        jPackets = new ArrayList<>();
        this.jPackets = getPackets();
    }

    public String largestPacket(){
        int largest = Integer.MIN_VALUE;
        for (JPacket packet: jPackets){
            if (packet.size()>largest){
                largest = packet.size();
            }
        }
     return String.valueOf(largest);
    }

    public String shortestPacket(){
        int largest = Integer.MAX_VALUE;
        for (JPacket packet: jPackets){
            if (packet.size()<largest){
                largest = packet.size();
            }
        }
        return String.valueOf(largest);
    }

    public String numPackets(){
        return String.valueOf(jPackets.size());
    }

    public String  timeForCapture(){
        JPacket firstPacket = jPackets.get(0);
        JPacket lastPacket = jPackets.get(jPackets.size()-1);

        long first = firstPacket.getCaptureHeader().seconds();
        long last = lastPacket.getCaptureHeader().seconds();
        long diff = last-first;
        String timeDiff = String.valueOf(diff);
        return timeDiff;

    }

    public String networkTraffic(){
        // method tells u how many bytes are in the file
         int x =0;
        for(JPacket Packet:jPackets){

            x+=Packet.size();// gets lenght of the packet

        }
        double totalSizeOfPackets =(double) x/ 1000000;
        System.out.println(x+" bytes in file");
        String size = String.format("%.2f",totalSizeOfPackets);

        return size;
    }

    public ArrayList<String> packetTypes(){
        ArrayList<String> map = new ArrayList<>();
        int udpCount = 0;
        int tcpCount = 0;
        for(JPacket packet:jPackets){
            if (packet.hasHeader(tcp)){
                tcpCount++;
            }
            else if (packet.hasHeader(udp)){
                udpCount++;
            }
        }
        map.add(String.format("TCP packets %s",tcpCount));
        map.add(String.format("UDP packets %s",udpCount));
        return map;
    }


}
