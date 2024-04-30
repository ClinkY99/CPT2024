package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryo.Kryo;


import java.io.IOException;

public class ServerInteface extends Server {
    public int portTCP;
    public int portUDP;

    public Array<MPInterface> Functions;
    public Array<Class> Classes;

    public ServerInteface(){
        this((Class) null);

    }
    public ServerInteface(Class... classes){
        portTCP = 54555;
        portUDP = 54777;

        Functions = new Array<>();
        Classes = new Array<>();

        for (Class cls : classes) {
            bindClass(cls);
        }

    }

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

    public void bindClass(Class classToBind){
        Kryo kryo = getKryo();
        kryo.register(classToBind);
        Classes.add(classToBind);
    }

    public void BindFunction(MPInterface function, Class classBound){
        Functions.setSize(Classes.size);
        if (Classes.contains(classBound, true)){
            Functions.set(Classes.indexOf(classBound, true), function);
        }
    }
}
