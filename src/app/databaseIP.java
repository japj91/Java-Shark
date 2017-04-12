package app;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import jdk.nashorn.internal.ir.CatchNode;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

/**
 *Created by Jap Johal A00980851 and Mohammed Bajaman A00991074
 * This class provides a geolocation for IPs.
 * The IPs are cross referenced with the GeoLite2-Country.mmdb database.
 *
 */
public class databaseIP extends Packets {
    ArrayList<JPacket> jPackets = new ArrayList<>();
    Ip4 ip4 = new Ip4();

    public databaseIP() {
        jPackets = new ArrayList<>();
        jPackets = getPackets();
    }

    public ArrayList<String> getLocationByIP() throws IOException, GeoIp2Exception {
        TreeMap<String, String> map = new TreeMap<>(); // map to store country name and ip related to that country

        File database = new File("src/GeoLite2-Country.mmdb"); // connecting to database note database needes to be unzipped and inside the source folder

        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        for (JPacket jPacket : jPackets) {
            if (jPacket.hasHeader(ip4)) {

                byte[] source = ip4.source();
                String IPString = org.jnetpcap.packet.format.FormatUtils.ip(source);
                InetAddress ipAddress = InetAddress.getByName(IPString);

                try {
                    CountryResponse response = reader.country(ipAddress);
                    Country country = response.getCountry();
                    String countryName = country.getName();

                    if (map.containsKey(IPString)) {
                        map.put(countryName, IPString);
                    } else {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(IPString);
                        map.put(countryName, IPString);
                    }
                } catch (AddressNotFoundException e) { // if database does not have the address in the database skip that IP
                    continue;
                }
            }
        }
        return returnArrayList(map);
    }

    private ArrayList<String> returnArrayList(TreeMap<String, String> map) {
        Set<String> lol = map.keySet();
        ArrayList<String> list = new ArrayList<>();
        for (String temp : lol) {
            String a = map.get(temp);
            String tempp = String.format("%s   %s", temp, a);
            list.add(tempp);
        }
        return list;
    }
}
