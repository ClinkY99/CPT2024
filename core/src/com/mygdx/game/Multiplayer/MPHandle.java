package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.utils.Null;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MPHandle {

    ServerInteface server;
    ClientInterface client;

    boolean isHost;

    public MPHandle(boolean isHost) {
        this.isHost = isHost;
        if(isHost){
            server = new ServerInteface(MPInterface.confirm.class,MPInterface.ready.class, MPInterface.levelCompletion.class,MPInterface.characterSelection.class);
        } else {
            client = new ClientInterface(MPInterface.confirm.class, MPInterface.ready.class, MPInterface.levelCompletion.class,MPInterface.characterSelection.class);
        }
    }

    public void init(@Null String ip){
        try {
            if (isHost) {
                server.init();
            } else {
                client.init();
                while(true) {
                    try {
                        client.connect(ip);
                        break;
                    } catch (Exception ignored){}
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static public String getLocalIP() {
        List<InetAddress> addresses = new ArrayList<>();

        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        for(NetworkInterface netInt: Collections.list(net)) {
            Enumeration<InetAddress> address = netInt.getInetAddresses();
            for(InetAddress addr: Collections.list(address)) {
                if(!addr.isLoopbackAddress()&& addr instanceof Inet4Address) {
                    return addr.getHostAddress();
                }
            }
        }
        return null;
    }

    public void sendTCP(Object object){
        if(isHost){
            server.sendToAllTCP(object);
            if(server.getConnections().length == 0){
                throw new RuntimeException();
            }
        } else {
            client.sendTCP(object);
        }
    }

    public void bindFunction(MPInterface function, Class cls){
        if(isHost){
            server.BindFunction(function, cls);
        } else {
            client.bindFunction(function, cls);
        }
    }

    public boolean isHost() {
        return isHost;
    }
}
