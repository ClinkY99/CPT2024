package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

public class ClientInterface extends Client {
    public int portTCP;
    public int portUDP;
    public int timeoutMS;

    public Array<MPInterface> Functions;
    public Array<Class> Classes;

    public Array<MPInterface.serverDetails> availibleServerDetails;

    public ClientInterface(Class... classes){
        timeoutMS = 5000;
        portTCP = 54555;
        portUDP = 54777;

        Functions = new Array<>();
        Classes = new Array<>();
        availibleServerDetails = new Array<>();

        for (Class c : classes){
            bindClass(c);
        }
    }
    public void init(){
        start();
        addListener(new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                Functions.setSize(Classes.size);
                for (int i = 0; i < Classes.size; i++){
                    if(Classes.get(i).equals(object.getClass())){
                        Functions.get(i).Reaction(connection, object);
                    }
                }
            }
        });
    }

    public void bindClass(Class cls){
        Kryo kryo = getKryo();
        kryo.register(cls);
        Classes.add(cls);
    }

    public void bindFunction(MPInterface function, Class classBound){
        Functions.setSize(Classes.size);
        if(Classes.contains(classBound,true)){
            Functions.set(Classes.indexOf(classBound,true), function);
        }
    }


    public void lanServers() {

        new Thread(() -> {

            List<InetAddress> addresses = discoverHosts(portUDP, timeoutMS);

            bindFunction((connection, object) -> availibleServerDetails.add((MPInterface.serverDetails) object), MPInterface.connectionDetails.class);

            for (InetAddress address : addresses) {
                try {
                    if (address.isReachable(timeoutMS)) {
                        connect(timeoutMS, address, portTCP, portUDP);
                        sendTCP(new MPInterface.connectionDetails("Test"));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public Array<MPInterface.serverDetails> getAvailibleServerDetails() {
        return availibleServerDetails;
    }
}
