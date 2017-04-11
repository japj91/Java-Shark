package app;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jap on 4/5/2017.
 */
public class netAnalysis  extends Packets{

    ArrayList<JPacket> jPackets;
    Ip4 ip = new Ip4();
    public netAnalysis(){
        jPackets = new ArrayList<>();
        this.jPackets = getPackets();
    }

    public HashMap<String,Integer> packetsPerIp(){
        HashMap<String, Integer> packetMap = new HashMap<>();
        byte[] list;

        for(JPacket packet: jPackets){

            if (packet.hasHeader(ip)){
                list = ip.destination();
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
        return packetMap;
    }

    public HashMap<String,Integer> bytesPerIP(){
        HashMap<String,Integer> packetMap = new HashMap<>();

        byte[] stanList;

        for(JPacket packet: jPackets){

            if (packet.hasHeader(ip)){
                stanList = ip.destination();

                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(stanList);


                if(!packetMap.containsKey(IPString)){
                    packetMap.put(IPString,packet.size());
                }
                else {
                    int temp = packetMap.get(IPString);
                    temp += packet.size();
                    packetMap.put(IPString,temp);
                }
            }
        }
        return packetMap;
    }
}
