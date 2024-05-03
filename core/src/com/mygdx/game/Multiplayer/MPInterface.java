package com.mygdx.game.Multiplayer;

import com.esotericsoftware.kryonet.Connection;


public interface MPInterface {
    void Reaction(Connection connection, Object object);
    class serverDetails {
        public String name;
        public String hostName;
        public String ip;
        public boolean serverOpen;
        public int completion;
        public boolean confirmed;

        public serverDetails() {

        }

        public serverDetails(String name, String hostName, String ip, boolean serverOpen, int completion) {
            this(name, hostName, ip, serverOpen, completion, false);
        }

        public serverDetails(String name, String hostName, String ip, boolean serverOpen, int completion, boolean confirmed) {
            this.name = name;
            this.hostName = hostName;
            this.ip = ip;
            this.serverOpen = serverOpen;
            this.completion = completion;
            this.confirmed = confirmed;
        }
    }
    class connectionDetails {
        public String clientName;
        public String ip;
        public boolean confirm;

        public connectionDetails() {

        }

        public connectionDetails(String clientName, String ip) {
            this(clientName, ip, false);
        }

        public connectionDetails(String clientName, String ip, boolean confirm) {
            this.clientName = clientName;
            this.confirm = confirm;
            this.ip = ip;
        }

    }
    class ready {
        public String playerID;
        public boolean loaded;

        public ready() {}

        public ready(String playerID, boolean loaded) {
            this.playerID = playerID;
            this.loaded = loaded;
        }

    }
    class characterSelection {
        public String playerID;
        public int character;
        public boolean confirmed;

        public characterSelection() {

        }
        public characterSelection(String playerID, int character) {
            this(playerID, character, false);
        }

        public characterSelection(String playerID, int character, boolean confirmed) {
            this.playerID = playerID;
            this.character = character;
            this.confirmed = confirmed;
        }

    }
    class levelCompletion {
        public String playerID;
        public int level;
        public boolean confirmed;

        public levelCompletion(){}

        public levelCompletion(String playerID, int level) {
            this(playerID, level, false);
        }

        public levelCompletion(String playerID, int level, boolean confirmed) {
            this.playerID = playerID;
            this.level = level;
            this.confirmed = confirmed;
        }

    }
    class confirm{
        public boolean confirmed;
        public confirm() {
            this.confirmed = false;
        }
        public confirm(boolean confirmed) {
            this.confirmed = confirmed;
        }
    }

}
