package app;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jap on 4/5/2017.
 */
public class netAnalysis  extends Packets{
    ArrayList<JPacket> jPackets;

    public netAnalysis(){
        jPackets = new ArrayList<>();
        this.jPackets = getPackets();
    }
    public void mapping(){
        HashMap<String, Integer> packetMap = new HashMap<>();
        Ip4 ip = new Ip4();
        byte[] list;

        for(JPacket packet: jPackets){

            if (packet.hasHeader(ip)){
                list = ip.source();
                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(list);

                if(!packetMap.containsKey(IPString)){
                    packetMap.put(IPString,1);
                }
                else {
                    int temp = packetMap.get(IPString);
                    temp++;
                    packetMap.put(IPString,temp);
                }
            }
        }
        System.out.println(packetMap);
    }
}
