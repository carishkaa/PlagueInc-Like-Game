/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.semestral_proj.help;

import com.fasterxml.jackson.databind.ObjectMapper;
import fel.pjv.client.Client;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * Request class is used for communication between client and server.
 * All types of requests will be listed as properties.
 * Client sends serialized Request to server. Server deserializes Request, 
 * gets requestType and than gets the needed request from 
 * the corresponding property
 */
public class Request {
    public String SenderIP = "";
    public String ReceiverIP = "";
    public RequestType requestType;
    public String data;
   
    /**
     *
     * Types of request
     */ 
   public enum RequestType{
        NEWGAME,
        RULECHANGE,
        GETPLAYERS,
        STARTPOINT,
        GETINFO,
        MAPUPDATE,
        ENDGAME,
        CONFIRM,
        ENDGAMEWINNER
    }

    /**
     *
     * An empty constructor
     */   
    public Request() {
    }

    /**
     *
     * Creates a request
     * @param SenderIP     this player's IP
     * @param ReceiverIP   other player's IP
     * @param requestType  type of request
     * @param data         serialized request
     */
    public Request(String SenderIP, String ReceiverIP, RequestType requestType, String data) {
        this.SenderIP = SenderIP;
        this.ReceiverIP = ReceiverIP;
        this.requestType = requestType;
        this.data = data;
    }

    /**
     *
     * Creates a request
     * @param SenderIP     this player's IP
     * @param ReceiverIP   other player's IP
     * @param requestType  type of request
     */
    public Request(String SenderIP, String ReceiverIP, RequestType requestType) {
        this.SenderIP = SenderIP;
        this.ReceiverIP = ReceiverIP;
        this.requestType = requestType;
    }

    /**
     *
     * Creates a request
     * @param resIP        other player's IP
     * @param req          new game request to be sent 
     * @throws java.net.UnknownHostException 
     */    
    public Request(String resIP, NewGameRequest req) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        SenderIP = inetAddress.getHostAddress();
        ReceiverIP = resIP;
        data = req.toSendString();
        requestType = RequestType.NEWGAME;
    }

    /**
     *
     * Passes request to client to be sent
     * @return true if success
     */    
    public Boolean SendRequest(){
        try {
            ObjectMapper objMapper = new ObjectMapper();
            byte[] json = objMapper.writeValueAsBytes(this);
            Client.sendRequest(json);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
