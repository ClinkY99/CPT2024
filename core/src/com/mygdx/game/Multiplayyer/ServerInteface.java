package com.mygdx.game.Multiplayyer;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryo.Kryo;


import java.io.IOException;

public class ServerInteface {
    public Server server;
    public int portTCP;
    public int portUDP;

    public Array<Reactions> Functions;
    public Array<Class> Classes;

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
                for (int i = 0; i < Classes.size; i++) {
                    if(Classes.get(i) == object.getClass()){
                        Functions.get(i).Reaction(connection);
                    }
                }
            }
        });
    }

    void bindClass(Class classToBind){
        Kryo kryo = server.getKryo();
        kryo.register(classToBind);
    }

    public void BindFunction(Reactions function, Class classBound){
        if (Classes.contains(classBound, true)){
            Functions.set(Classes.indexOf(classBound, true), function);
        }
    }

}
