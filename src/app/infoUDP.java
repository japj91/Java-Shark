package app;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Udp;

import java.util.ArrayList;

/**
 * Created by jap on 4/9/2017.
 */
public class infoUDP extends Packets {
    ArrayList<JPacket> list;
    Udp udp = new Udp();

    public infoUDP(){
        list = new ArrayList<>();
        list = getPackets();
    }

    public int numOfUDP(){
        int x =0;
        for(JPacket packet: list){
            if (packet.hasHeader(udp)){
                x++;
            }
        }
        return x;
    }
}
