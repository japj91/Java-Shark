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

    public void packetSize(){
        HashMap<String,Integer> packetMap = new HashMap<>();
        Ip4 ip = new Ip4();
        byte[] stanList;
        byte[] stanList2;

        for(JPacket packet: jPackets){

            if (packet.hasHeader(ip)){
                stanList = ip.destination();
                stanList2 = ip.source();
                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(stanList);
               // String IPString2 = org.jnetpcap.packet.format.FormatUtils.ip(stanList2);

                if(!packetMap.containsKey(IPString)){
                    packetMap.put(IPString,packet.size());
                }
                else {
                    int temp = packetMap.get(IPString);
                    temp += packet.size();
                    packetMap.put(IPString,temp);
                }
//                if (!packetMap.containsKey(IPString2)){
//                    packetMap.put(IPString2,packet.size());
//                }else{
//                    int temp = packetMap.get(IPString2);
//                    temp += packet.size();
//                    packetMap.put(IPString2,temp);
//                }
            }
        }
        System.out.println(packetMap);
    }
}
