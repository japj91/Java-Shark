package app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class ShareableData {
        private final static ShareableData instance = new ShareableData();

        public static ShareableData getInstance(){
            return instance;
        }

        private ArrayList<String> list = new ArrayList<>();

        public ArrayList<String> getList(){
            return list;
        }

        private String hostUser=""; // being set infoTCP origin host method

        public String getHostUser(){
            return hostUser;
        }

        private HashMap<String,Integer> bytesPerIP = new HashMap<>(); // this map gets set in geninfo packets method

        public HashMap<String,Integer> getBytesPerIP(){
            return bytesPerIP;
        }

        private HashMap<String,Integer> packetsPerIp = new HashMap<>();

        public HashMap<String,Integer> getPacketsPerIp(){
            return packetsPerIp;
        }

        private ArrayList<File> fileList = new ArrayList<>();

        public ArrayList<File> getFile(){
            return fileList;
        }

    }



