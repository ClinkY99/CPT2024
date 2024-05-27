package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * This is the class that is used to be interfaced with the kryo client, it has multiple functions to make connections easy within our game functionality
 */
public class ClientInterface extends Client {
    public int portTCP;
    public int portUDP;
    public int timeoutMS;

    public Array<MPInterface> Functions;
    public Array<Class> Classes;

    public Array<MPInterface.serverDetails> availibleServerDetails;
    private boolean newServerDetailsAvailable;

    /**
     * sets up the client class, but does not yet open the client to connections
     * @param classes All classes you want to bind to the client in initialization, ensure this list is in the same order on client and server
     */
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

    /**
     * opens the client for connections and adds a connection listener, to automatically run the correct function when an object is sent from the server
     */
    public void init(){
        start();
        addListener(new Listener(){
            @Override
            public void received(Connection connection, Object object) {
                Functions.setSize(Classes.size);
                for (int i = 0; i < Classes.size; i++){
                    if(Classes.get(i).equals(object.getClass())){
                        if(Functions.get(i)!=null) {
                            Functions.get(i).Reaction(connection, object);
                        }
                    }
                }
            }
        });
    }

    /**
     * allows the client to try and connect to a host ip
     * @param host host IP
     * @throws IOException if unable to connect to server will crash
     */
    public void connect(String host) throws IOException {
        connect(timeoutMS, host, portTCP, portUDP);
    }

    /**
     * Binds an additional class to the client, ensure this is in the same order as the server
     * @param cls class to be bound
     */
    public void bindClass(Class cls){
        Kryo kryo = getKryo();
        kryo.register(cls);
        Classes.add(cls);
    }

    /**
     * binds a function to a specific class, this function will be called when the client receives this object
     * @param function function to be bound
     * @param classBound the class that this function is bound to
     */
    public void bindFunction(MPInterface function, Class classBound){
        Functions.setSize(Classes.size);
        if(Classes.contains(classBound,true)){
            Functions.set(Classes.indexOf(classBound,true), function);
        }
    }

    /**
     * this function searches for all servers that are open on the lan in a thread so not to hang the main thread. It sets the available server details array to be equal to the returned server data
     */
    public void lanServers() {

        availibleServerDetails.clear();

        new Thread(() -> {

            List<InetAddress> addresses = discoverHosts(portUDP, 500);

            bindFunction((connection, object) -> {
                availibleServerDetails.add((MPInterface.serverDetails) object);
                newServerDetailsAvailable = true;
            }, MPInterface.serverDetails.class);

            for (InetAddress address : addresses) {
                System.out.println("test");
                try {
                    connect(timeoutMS, address, portTCP, portUDP);
                    sendTCP(new MPInterface.connectionDetails("Test", InetAddress.getLocalHost().toString()));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

//            try {
//                System.out.println("test");
//                connect(timeoutMS, "172.20.10.2", portTCP, portUDP);
//                sendTCP(new MPInterface.connectionDetails("Test"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }).start();
    }

    /**
     * @return the available server details found by the client
     */
    public Array<MPInterface.serverDetails> getAvailibleServerDetails() {
        return availibleServerDetails;
    }

    /**
     * @return have new servers been found since the last check
     */
    public boolean isNewServerDetailsAvailable() {
        return newServerDetailsAvailable;
    }

    public void setNewServerDetailsAvailable(boolean newServerDetailsAvailable) {
        this.newServerDetailsAvailable = newServerDetailsAvailable;
    }
}
