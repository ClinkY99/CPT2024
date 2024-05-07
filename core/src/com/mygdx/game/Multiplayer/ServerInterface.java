package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryo.Kryo;


import java.io.IOException;

/**
 * Handles server control as well as easy interfacing with the kryo server interface for easy integration into our game
 */
public class ServerInterface extends Server {
    public int portTCP;
    public int portUDP;

    public Array<MPInterface> Functions;
    public Array<Class> Classes;

    /**
     * Default constructor with no classes being bound
     */
    public ServerInterface(){
        this((Class) null);

    }
    /**
     * sets up the server class, but does not yet open the sever to connections
     * @param classes All classes you want to bind to the sever in initialization, ensure this list is in the same order on client and server
     */
    public ServerInterface(Class... classes){
        portTCP = 54555;
        portUDP = 54777;

        Functions = new Array<>();
        Classes = new Array<>();

        for (Class cls : classes) {
            bindClass(cls);
        }
    }
    /**
     * opens the server for connections and adds a connection listener, to automatically run the correct function when an object is sent from the server
     */
    public void init() throws IOException {
        start();
        bind(portTCP,portUDP);
        addListener(new Listener(){
            public void received (Connection connection, Object object){
                Functions.setSize(Functions.size);
                for (int i = 0; i < Classes.size; i++) {
                    if(Classes.get(i).equals(object.getClass())){
                        Functions.get(i).Reaction(connection, object);
                    }
                }
            }
        });

    }

    /**
     * Binds an additional class to the client, ensure this is in the same order as the server
     * @param classToBind class to be bound
     */
    public void bindClass(Class classToBind){
        Kryo kryo = getKryo();
        kryo.register(classToBind);
        Classes.add(classToBind);
    }

    /**
     * binds a function to a specific class, this function will be called when the client receives this object
     * @param function function to be bound
     * @param classBound the class that this function is bound to
     */
    public void BindFunction(MPInterface function, Class classBound){
        Functions.setSize(Classes.size);
        if (Classes.contains(classBound, true)){
            Functions.set(Classes.indexOf(classBound, true), function);
        }
    }
}
