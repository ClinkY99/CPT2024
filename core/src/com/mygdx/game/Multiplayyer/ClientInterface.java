package com.mygdx.game.Multiplayyer;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.net.InetAddress;

public class ClientInterface {
    public Client client;
    public int portTCP;
    public int portUDP;
    public int timeoutMS;
    public ClientInterface(){
        client = new Client();
        timeoutMS = 5000;
    }
    public ClientInterface(int Timeout){
        client = new Client();
        timeoutMS = Timeout;
    }
    public void Init(int portTCP, int portUDP){
        client.start();
        this.portTCP = portTCP;
        this.portUDP = portUDP;
    }
    public boolean LanConnection() throws IOException {
        InetAddress address = client.discoverHost(portUDP, timeoutMS);

        if(address.isReachable(timeoutMS)){
            client.connect(timeoutMS, address, portTCP, portUDP);
            return true;

        }
        return false;
    }
}
