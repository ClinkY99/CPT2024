package com.mygdx.game.Multiplayer;

import com.esotericsoftware.kryonet.Connection;

/**
 * Interface contains the classes that are able to be sent by the server and the client, it also contains the reaction function to be overridden.
 */
public interface MPInterface {
    /**
     * Function called when respective class is received by the connection
     * @param connection connection data between client and server
     * @param object object received
     */
    void Reaction(Connection connection, Object object);

    /**
     * this class stores and sends the servers details, this contains IP address and all other important information for the client
     */
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


    /**
     * This class stores and sends all information about the client to the server.
     */
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

    /**
     * This class is sent to confirm that both client and server are ready to continue
     */
    class ready {
        public String playerID;
        public boolean loaded;

        public ready() {}

        public ready(String playerID, boolean loaded) {
            this.playerID = playerID;
            this.loaded = loaded;
        }

    }

    /**
     * Holds all information required to be passed between server and client during character selection
     */
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

    /**
     * Holds all information to be passed on the completion of a level, this allows server and client to confirm that they are ready for continuing on to the next level
     */
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

    /**
     * Allows the client to confirm selection with the server
     */
    class confirm{
        public boolean confirmed;
        public String ID;
        public confirm() {
            this.confirmed = false;
        }
        public confirm(boolean confirmed) {
            this.confirmed = confirmed;
        }
        public confirm(String ID, boolean confirmed) {
            this.ID = ID;
            this.confirmed = confirmed;
        }
    }

    class playerLoc{
        public int locx;
        public int locy;
        public int character;

        public int spawnx;
        public int spawny;
    }
}
