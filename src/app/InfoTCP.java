package app;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.util.*;

/**
 * Created by jap on 4/5/2017.
 * General TCP class
 * info on TCP listOfURls
 */
public class InfoTCP extends Packets {
    ArrayList<JPacket> jPackets;
    Tcp tcp = new Tcp();


    public InfoTCP() {
        jPackets = new ArrayList<>();
        jPackets = getPackets();
    }

    public String getOriginHost() {
        // two helper functions to create an array list of all TCP packets with certain flags set
        // other is to find the source host

        ArrayList<JPacket> hostPackets = OriginHost();
        Ip4 ip = new Ip4();
        HashMap<String, Integer> hosts = new HashMap<>();

        for (JPacket packet : hostPackets) {
            if (packet.hasHeader(ip)) {
                byte[] myListSource;
                myListSource = ip.source();
                String source = org.jnetpcap.packet.format.FormatUtils.ip(myListSource);
                hosts.put(source, packet.size());
            }
        }
        return getHost(hosts);
    }

    private ArrayList<JPacket> OriginHost() {

        ArrayList<JPacket> packTempList = new ArrayList<>();

        for (JPacket packet : jPackets) {
            if (packet.hasHeader(tcp)) {
                if (tcp.flags_SYN() && !tcp.flags_ACK()) {
                    packTempList.add(packet);

                }
            }
        }
        return packTempList;
    }

    private String getHost(HashMap<String, Integer> map) {
        int x = Integer.MIN_VALUE;
        String temp = "";
        Set<String> set = map.keySet();

        for (String key : set) {
            int sizeOfPacket = map.get(key);
            if (sizeOfPacket > x) {
                x = sizeOfPacket;
                temp = key;
            }
        }
        return temp;
    }

    public ArrayList<String> getPorts() {
        HashMap<Integer, Integer> portsHash = new HashMap<>();
        ArrayList<String> ports = new ArrayList<>();
        for (JPacket packet : jPackets) {
            if (packet.hasHeader(tcp)) {
                if (portsHash.containsKey(tcp.destination())) {
                    portsHash.put(tcp.destination(), portsHash.get(tcp.destination()) + 1);
                } else {
                    portsHash.put(tcp.destination(), 1);
                }
            }
        }
        for(Map.Entry<Integer, Integer> entry: portsHash.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            String item = "\t"+key.toString() +"\t: \t"+ value.toString();
            ports.add(item);
        }
        return ports;
    }
}
