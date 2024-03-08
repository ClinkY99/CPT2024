package com.mygdx.game.Multiplayyer;

import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryo.Kryo;


import java.io.IOException;

public class ServerInteface {
    public Server server;
    public int portTCP;
    public int portUDP;

    ServerInteface(){
        server = new Server();
    }

    public void init(int portTCP, int portUDP) throws IOException {
        server.start();
        server.bind(portTCP,portUDP);
        this.portTCP = portTCP;
        this.portUDP = portUDP;
        server.addListener(new Listener(){
            public void recieved(Connection connection, Object object){

            }
        });
    }

    public void bindClass(Class classToBind){
        Kryo kryo = server.getKryo();
        kryo.register(classToBind);
    }

    public void BindFunction(Void function, Class classBound){

    }

}
