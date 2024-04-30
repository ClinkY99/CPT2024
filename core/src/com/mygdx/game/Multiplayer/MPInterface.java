package com.mygdx.game.Multiplayer;

import com.esotericsoftware.kryonet.Connection;

public interface MPInterface {
    void Reaction(Connection connection, Object object);
    class serverDetails {
        public String name;
        public String hostName;
        public boolean serverOpen;
        public int completion;

        public serverDetails() {

        }

        public serverDetails(String name, String hostName, boolean serverOpen, int completion) {
            this.name = name;
            this.hostName = hostName;
            this.serverOpen = serverOpen;
            this.completion = completion;
        }
    }
    class connectionDetails {
        public String clientName;

        public connectionDetails() {

        }

        public connectionDetails(String clientName) {
            this.clientName = clientName;
        }

    }

}
