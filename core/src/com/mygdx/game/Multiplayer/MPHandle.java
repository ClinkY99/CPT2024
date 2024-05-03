package com.mygdx.game.Multiplayer;

import com.badlogic.gdx.utils.Null;

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
                client.connect(ip);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTCP(Object object){
        if(isHost){
            server.sendToAllTCP(object);
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
