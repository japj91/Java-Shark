package app;

import java.io.File;
import java.util.ArrayList;

/**
 *Created by Jap Johal A00980851 and Mohammed Bajaman A00991074
 */

public class ShareableData {

    private final static ShareableData instance = new ShareableData();

    public static ShareableData getInstance() {
        return instance;
    }

    private ArrayList<String> hostUserList = new ArrayList<>();

    public ArrayList<String> getHostUserList() {
        return hostUserList;
    }

    private ArrayList<File> fileList = new ArrayList<>();

    public ArrayList<File> getFile() {
        return fileList;
    }
}



