package app;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jap on 4/5/2017.
 * General TCP class
 * info on TCP listOfURls
 */
public class InfoTCP  extends  Packets{
    ArrayList<JPacket> jPackets;

    public InfoTCP(){
        jPackets = new ArrayList<>();
        jPackets = getPackets();
    }

    private ArrayList<JPacket> OriginHost(){
        Tcp tcp = new Tcp();
        ArrayList<JPacket> packTempList = new ArrayList<>();

        for(JPacket packet: jPackets){
            if (packet.hasHeader(tcp)){
                if (tcp.flags_SYN() && !tcp.flags_ACK()){
                   packTempList.add(packet);

                }
            }
        }

        return packTempList;
    }

    public Set<String> getOriginHost(){
        ArrayList<JPacket> hostPackets = OriginHost();
        Ip4 ip = new Ip4();
        HashSet<String> hosts = new HashSet<>();

        for (JPacket packet: hostPackets){
            if (packet.hasHeader(ip)){
                byte[] myListSource;
                myListSource = ip.source();
                String source = org.jnetpcap.packet.format.FormatUtils.ip(myListSource);
                hosts.add(source);
            }
        }
        return hosts;
    }


}
