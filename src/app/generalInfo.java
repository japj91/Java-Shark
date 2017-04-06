package app;

import com.sun.xml.internal.ws.api.message.Packet;
import org.jnetpcap.packet.JPacket;


import java.util.ArrayList;

/**
 * Created by jap on 4/5/2017.
 * General information class
 * Shortest longest time
 * wanted to put in packet calss but b/c its super class did not put in there
 */
public class generalInfo  extends Packets{
    ArrayList<JPacket> jPackets;

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
}
