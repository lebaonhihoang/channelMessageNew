package com.example.hoangleb.channelmessaging;

/**
 * Created by nhi on 11/02/2017.
 */

public class GetChannel {
    private int ID;
    private String name;
    private String connected;

    public GetChannel(int ID, String name, String connected) {
        this.ID = ID;
        this.name = name;
        this.connected = connected;
    }


    public int getChannelID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getusers() {
        return connected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setusers(String connected) {
        this.connected = connected;
    }

    public void setChannelID(int channelID) {
        this.ID = ID;
    }
}
