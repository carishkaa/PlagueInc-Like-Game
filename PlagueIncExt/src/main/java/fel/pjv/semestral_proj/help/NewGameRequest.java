/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.semestral_proj.help;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Represents a request for a new game
 */
public class NewGameRequest {
    public Role SenderRole;
    public Role ReceiverRole;
    public GameRequestStatus Status;
    
    /**
     *
     * Types of new game request
     */    
    public enum GameRequestStatus{
        INVITED,
        ACCEPTED,
        DECLINED
    }

    /**
     *
     * Player roles
     */     
    public enum Role{
        PANDEMIA,
        HUMANITY
    }

    /**
     *
     * An empty constructor
     */ 
    public NewGameRequest() {
    }

    /**
     *
     * Creates a new game request with set role
     * @param myRole this player's role
     */     
    public NewGameRequest(Role myRole){
        SenderRole = myRole;
        ReceiverRole = (myRole == Role.HUMANITY)? 
                Role.PANDEMIA : Role.HUMANITY;
        Status = GameRequestStatus.INVITED;
    }

    /**
     *
     * Creates a new game request
     * @param SenderRole this player's role
     * @param ReceiverRole other player's role
     * @param Status status of the request
     */  
    public NewGameRequest(Role SenderRole, Role ReceiverRole, GameRequestStatus Status) {
        this.SenderRole = SenderRole;
        this.ReceiverRole = ReceiverRole;
        this.Status = Status;
    }

    /**
     *
     * Serializes this request to json
     * @return serialized object
     */    
    public String toSendString(){
        ObjectMapper objMapper = new ObjectMapper();
        String json = null;
        try {
            json = objMapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(NewGameRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
