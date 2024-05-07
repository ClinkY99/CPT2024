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

/**
 * Class used while within a game, contains both client and server data, just condenses the already created classes into one controlling class
 */
public class MPHandle {

    ServerInterface server;
    ClientInterface client;

    boolean isHost;

    /**
     * creates the respective server or client class
     * @param isHost is this the host computer
     */
    public MPHandle(boolean isHost) {
        this.isHost = isHost;
        if(isHost){
            server = new ServerInterface(MPInterface.confirm.class,MPInterface.ready.class, MPInterface.levelCompletion.class,MPInterface.characterSelection.class);
        } else {
            client = new ClientInterface(MPInterface.confirm.class, MPInterface.ready.class, MPInterface.levelCompletion.class,MPInterface.characterSelection.class);
        }
    }

    /**
     * starts the client/server and if you are the client tries to connect to the server until it connects.
     * @param ip ip of the host server, only required if you are the client
     */
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

    /**
     * Gets local IP address, this is used to bypass a bug found with mac computers
     * @return Local IP Adress
     */
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

    /**
     * Sends an object to the client/server
     * @param object object to be sent
     */
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

    /**
     * Binds a function to the client/server, this function will be called when respective object is received
     * @param function function to be called
     * @param cls class to be received
     */
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
