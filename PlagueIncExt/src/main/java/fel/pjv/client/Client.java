/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Represents a Game Client
 */
public class Client {
    static PlayerStatus state = PlayerStatus.ACCESSIBLE;
    static private ByteBuffer buffer;
    static private SocketChannel client;
    private static volatile String myIP;
    private static volatile String opponentIP;
    private static Boolean online = false;
    private static int DNA = 20;
    
    /**
    * Represents possible Player statuses
    */
    public enum PlayerStatus{
        ACCESSIBLE,
        NOTACCESSIBLE,
        AWAITING,
        DECLINED,
        ACCEPTED
    }

    /**
    * Sets new myIP for a Client
    * @param myIPset new myIP value
    */
    public static void setMyIP(String myIPset) {
        myIP = myIPset;
    }
    
    /**
    * Gets opponentIP from a Client
    * @return opponentIP
    */
    public  static String getOpponentIP() {
        return opponentIP;
    }

    /**
    * Sets new opponentIP for a Client
    * @param opponentIPset new opponentIP value
    */
    public static void setOpponentIP(String opponentIPset) {
        opponentIP = opponentIPset;
    }
    
    /**
    * Gets state from a Client
    * @return state
    */
    public static PlayerStatus getState() {
        return state;
    }
    
    /**
    * Sets new state for a Client
    * @param stateSet new state value
    */
    public static void setState(PlayerStatus stateSet) {
        state = stateSet;
    }

    /**
    * gets the DNA count
    * @return DNA
    */    
    public static int getDNA() {
        return DNA;
    }

    /**
    * sets the DNA count
    * @param DNA new DNA count
    */ 
    public static void setDNA(int DNA) {
        Client.DNA = DNA;
    }
    
    /**
    * starts new Client
    * @throws IOException
    */    
    public static void startClient() throws IOException  {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 9083);
	client = SocketChannel.open(hostAddress);
        myIP = client.socket().getRemoteSocketAddress().toString();
        System.out.println("Client... started");
        buffer = ByteBuffer.allocate(1024);
    }

    /**
    * Gets myIP from a Client
    * @return myIP
    */    
    public static String getMyIP() {
        return myIP;
    }

    /**
    * Gets SocketChannel client from a Client
    * @return client
    */
    public static SocketChannel getClient() {
        return client;
    }

    /**
    * Sends request to a server
    * @param message message to send
    * @throws IOException
    */    
    public static void sendRequest(byte[] message) throws IOException{
        buffer.put(message);
	buffer.flip();
	client.write(buffer);
	System.out.println(message);
	buffer.clear();
    }
}
