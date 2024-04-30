package com.mygdx.game.Multiplayer;

import com.esotericsoftware.kryonet.Connection;

public interface MPInterface {
    void Reaction(Connection connection, Object object);
    class serverDetails {
        String name;
        String hostName;
        boolean serverOpen;
        int completion;

        public serverDetails(String name, String hostName, boolean serverOpen, int completion) {
            this.name = name;
            this.hostName = hostName;
            this.serverOpen = serverOpen;
            this.completion = completion;
        }

        public String getName() {
            return name;
        }

        public String getHostName() {
            return hostName;
        }

        public boolean isServerOpen() {
            return serverOpen;
        }

        public int getCompletion() {
            return completion;
        }
    }
    class connectionDetails {
        String clientName;
        public connectionDetails(String clientName) {
            this.clientName = clientName;
        }

        public String getClientName() {
            return clientName;
        }
    }

}
