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
 * Represents a request to changes the rules of the simulation
 */
public class RuleChangeRequest {
    private float infectionRateTempHigh;
    private float infectionRateTempMed;
    private float infectionRateTempLow;
    
    private float infectionRateUrbHigh;
    private float infectionRateUrbMed;
    private float infectionRateUrbLow;
    
    private float infectionRateHumHigh;
    private float infectionRateHumMed;
    private float infectionRateHumLow;
    
    private float infectionRateDevHigh;
    private float infectionRateDevMed;
    private float infectionRateDevLow;
    
    private float deadRateTempHigh;
    private float deadRateTempMed;
    private float deadRateTempLow;
    
    private float deadRateUrbHigh;
    private float deadRateUrbMed;
    private float deadRateUrbLow;
    
    private float deadRateHumHigh;
    private float deadRateHumMed;
    private float deadRateHumLow;
    
    private float deadRateDevHigh;
    private float deadRateDevMed;
    private float deadRateDevLow;

    /**
     *
     * Creates empty rule change request
     */
    public RuleChangeRequest() {
    }

    /**
     * Creates rule change request
     * @param infectionRateTempHigh    new infection rate for high temperature
     * @param infectionRateTempMed     new infection rate for medium temperature
     * @param infectionRateTempLow     new infection rate for low temperature
     * @param infectionRateUrbHigh     new infection rate for high urbanization
     * @param infectionRateUrbMed      new infection rate for medium urbanization
     * @param infectionRateUrbLow      new infection rate for low urbanization
     * @param infectionRateHumHigh     new infection rate for high humidity
     * @param infectionRateHumMed      new infection rate for medium humidity
     * @param infectionRateHumLow      new infection rate for low humidity
     * @param infectionRateDevHigh     new infection rate for high development
     * @param infectionRateDevMed      new infection rate for medium development
     * @param infectionRateDevLow      new infection rate for low development
     * @param deadRateTempHigh         new dead rate for high temperature
     * @param deadRateTempMed          new dead rate for medium temperature
     * @param deadRateTempLow          new dead rate for low temperature
     * @param deadRateUrbHigh          new dead rate for high urbanization
     * @param deadRateUrbMed           new dead rate for medium urbanization
     * @param deadRateUrbLow           new dead rate for low urbanization
     * @param deadRateHumHigh          new dead rate for high humidity
     * @param deadRateHumMed           new dead rate for medium humidity
     * @param deadRateHumLow           new dead rate for low humidity
     * @param deadRateDevHigh          new dead rate for high development
     * @param deadRateDevMed           new dead rate for medium development
     * @param deadRateDevLow           new dead rate for low development
     */
    public RuleChangeRequest(float infectionRateTempHigh, float infectionRateTempMed, 
            float infectionRateTempLow, float infectionRateUrbHigh, 
            float infectionRateUrbMed, float infectionRateUrbLow, 
            float infectionRateHumHigh, float infectionRateHumMed, 
            float infectionRateHumLow, float infectionRateDevHigh, 
            float infectionRateDevMed, float infectionRateDevLow, 
            float deadRateTempHigh, float deadRateTempMed, 
            float deadRateTempLow, float deadRateUrbHigh, 
            float deadRateUrbMed, float deadRateUrbLow, 
            float deadRateHumHigh, float deadRateHumMed, 
            float deadRateHumLow, float deadRateDevHigh, 
            float deadRateDevMed, float deadRateDevLow) {
        this.infectionRateTempHigh = infectionRateTempHigh;
        this.infectionRateTempMed = infectionRateTempMed;
        this.infectionRateTempLow = infectionRateTempLow;
        this.infectionRateUrbHigh = infectionRateUrbHigh;
        this.infectionRateUrbMed = infectionRateUrbMed;
        this.infectionRateUrbLow = infectionRateUrbLow;
        this.infectionRateHumHigh = infectionRateHumHigh;
        this.infectionRateHumMed = infectionRateHumMed;
        this.infectionRateHumLow = infectionRateHumLow;
        this.infectionRateDevHigh = infectionRateDevHigh;
        this.infectionRateDevMed = infectionRateDevMed;
        this.infectionRateDevLow = infectionRateDevLow;
        this.deadRateTempHigh = deadRateTempHigh;
        this.deadRateTempMed = deadRateTempMed;
        this.deadRateTempLow = deadRateTempLow;
        this.deadRateUrbHigh = deadRateUrbHigh;
        this.deadRateUrbMed = deadRateUrbMed;
        this.deadRateUrbLow = deadRateUrbLow;
        this.deadRateHumHigh = deadRateHumHigh;
        this.deadRateHumMed = deadRateHumMed;
        this.deadRateHumLow = deadRateHumLow;
        this.deadRateDevHigh = deadRateDevHigh;
        this.deadRateDevMed = deadRateDevMed;
        this.deadRateDevLow = deadRateDevLow;
    }

    /**
     *
     * gets the infection rate for high temperature
     * @return infection rate for high temperature
     */    
    public float getInfectionRateTempHigh() {
        return infectionRateTempHigh;
    }

    /**
     *
     * sets the infection rate for high temperature
     * @param infectionRateTempHigh new infection rate for high temperature
     */    
    public void setInfectionRateTempHigh(float infectionRateTempHigh) {
        this.infectionRateTempHigh = infectionRateTempHigh;
    }

    /**
     *
     * gets the infection rate for medium temperature
     * @return infection rate for medium temperature
     */    
    public float getInfectionRateTempMed() {
        return infectionRateTempMed;
    }

    /**
     *
     * sets the infection rate for medium temperature
     * @param infectionRateTempMed new infection rate for medium temperature
     */    
    public void setInfectionRateTempMed(float infectionRateTempMed) {
        this.infectionRateTempMed = infectionRateTempMed;
    }

    /**
     *
     * gets the infection rate for low temperature
     * @return infection rate for low temperature
     */
    public float getInfectionRateTempLow() {
        return infectionRateTempLow;
    }

    /**
     *
     * sets the infection rate for low temperature
     * @param infectionRateTempLow new infection rate for low temperature
     */
    public void setInfectionRateTempLow(float infectionRateTempLow) {
        this.infectionRateTempLow = infectionRateTempLow;
    }

    /**
     *
     * gets the infection rate for high urbanization
     * @return infection rate for high urbanization
     */    
    public float getInfectionRateUrbHigh() {
        return infectionRateUrbHigh;
    }

    /**
     *
     * sets the infection rate for high urbanization
     * @param infectionRateUrbHigh new infection rate for high urbanization
     */
    public void setInfectionRateUrbHigh(float infectionRateUrbHigh) {
        this.infectionRateUrbHigh = infectionRateUrbHigh;
    }

    /**
     *
     * gets the infection rate for medium urbanization
     * @return infection rate for medium urbanization
     */   
    public float getInfectionRateUrbMed() {
        return infectionRateUrbMed;
    }

    /**
     *
     * sets the infection rate for medium urbanization
     * @param infectionRateUrbMed new infection rate for medium urbanization
     */
    public void setInfectionRateUrbMed(float infectionRateUrbMed) {
        this.infectionRateUrbMed = infectionRateUrbMed;
    }

    /**
     *
     * gets the infection rate for low urbanization
     * @return infection rate for low urbanization
     */   
    public float getInfectionRateUrbLow() {
        return infectionRateUrbLow;
    }

    /**
     *
     * sets the infection rate for low urbanization
     * @param infectionRateUrbLow new infection rate for low urbanization
     */
    public void setInfectionRateUrbLow(float infectionRateUrbLow) {
        this.infectionRateUrbLow = infectionRateUrbLow;
    }

    /**
     *
     * gets the infection rate for high humidity
     * @return infection rate for high humidity
     */
    public float getInfectionRateHumHigh() {
        return infectionRateHumHigh;
    }

    /**
     *
     * sets the infection rate for high humidity
     * @param infectionRateHumHigh new infection rate for high humidity
     */
    public void setInfectionRateHumHigh(float infectionRateHumHigh) {
        this.infectionRateHumHigh = infectionRateHumHigh;
    }

    /**
     *
     * gets the infection rate for medium humidity
     * @return infection rate for medium humidity
     */
    public float getInfectionRateHumMed() {
        return infectionRateHumMed;
    }

    /**
     *
     * sets the infection rate for medium humidity
     * @param infectionRateHumMed new infection rate for medium humidity
     */
    public void setInfectionRateHumMed(float infectionRateHumMed) {
        this.infectionRateHumMed = infectionRateHumMed;
    }

    /**
     *
     * gets the infection rate for low humidity
     * @return infection rate for low humidity
     */
    public float getInfectionRateHumLow() {
        return infectionRateHumLow;
    }

    /**
     *
     * sets the infection rate for low humidity
     * @param infectionRateHumLow new infection rate for low humidity
     */
    public void setInfectionRateHumLow(float infectionRateHumLow) {
        this.infectionRateHumLow = infectionRateHumLow;
    }

    /**
     *
     * gets the infection rate for high development
     * @return infection rate for high development
     */
    public float getInfectionRateDevHigh() {
        return infectionRateDevHigh;
    }

    /**
     *
     * sets the infection rate for high development
     * @param infectionRateDevHigh new infection rate for low humidity
     */    
    public void setInfectionRateDevHigh(float infectionRateDevHigh) {
        this.infectionRateDevHigh = infectionRateDevHigh;
    }

    /**
     *
     * gets the infection rate for medium development
     * @return infection rate for medium development
     */    
    public float getInfectionRateDevMed() {
        return infectionRateDevMed;
    }

    /**
     *
     * sets the infection rate for medium development
     * @param infectionRateDevMed new infection rate for medium humidity
     */        
    public void setInfectionRateDevMed(float infectionRateDevMed) {
        this.infectionRateDevMed = infectionRateDevMed;
    }

    /**
     *
     * gets the infection rate for low development
     * @return the infection rate for low development
     */    
    public float getInfectionRateDevLow() {
        return infectionRateDevLow;
    }

    /**
     *
     * sets the infection rate for low development
     * @param infectionRateDevLow new infection rate for low humidity
     */  
    public void setInfectionRateDevLow(float infectionRateDevLow) {
        this.infectionRateDevLow = infectionRateDevLow;
    }

    /**
     *
     * gets the dead rate for high temperature
     * @return dead rate for high temperature
     */      
    public float getDeadRateTempHigh() {
        return deadRateTempHigh;
    }

    /**
     *
     * sets the dead rate for high temperature
     * @param deadRateTempHigh new dead rate for high temperature
     */  
    public void setDeadRateTempHigh(float deadRateTempHigh) {
        this.deadRateTempHigh = deadRateTempHigh;
    }

    /**
     *
     * gets the dead rate for medium temperature
     * @return dead rate for medium temperature
     */ 
    public float getDeadRateTempMed() {
        return deadRateTempMed;
    }

    /**
     *
     * sets the dead rate for medium temperature
     * @param deadRateTempMed new dead rate for medium temperature
     */  
    public void setDeadRateTempMed(float deadRateTempMed) {
        this.deadRateTempMed = deadRateTempMed;
    }

    /**
     *
     * gets the dead rate for low temperature
     * @return dead rate for low temperature
     */ 
    public float getDeadRateTempLow() {
        return deadRateTempLow;
    }

    /**
     *
     * sets the dead rate for low temperature
     * @param deadRateTempLow new dead rate for low temperature
     */  
    public void setDeadRateTempLow(float deadRateTempLow) {
        this.deadRateTempLow = deadRateTempLow;
    }

    /**
     *
     * gets the dead rate for high urbanization
     * @return dead rate for high urbanization
     */ 
    public float getDeadRateUrbHigh() {
        return deadRateUrbHigh;
    }

    /**
     *
     * sets the dead rate for high urbanization
     * @param deadRateUrbHigh new dead rate for high urbanization
     */  
    public void setDeadRateUrbHigh(float deadRateUrbHigh) {
        this.deadRateUrbHigh = deadRateUrbHigh;
    }

    /**
     *
     * gets the dead rate for medium urbanization
     * @return dead rate for medium urbanization
     */ 
    public float getDeadRateUrbMed() {
        return deadRateUrbMed;
    }

    /**
     *
     * sets the dead rate for medium urbanization
     * @param deadRateUrbMed new dead rate for medium urbanization
     */ 
    public void setDeadRateUrbMed(float deadRateUrbMed) {
        this.deadRateUrbMed = deadRateUrbMed;
    }

    /**
     *
     * gets the dead rate for low urbanization
     * @return dead rate for low urbanization
     */ 
    public float getDeadRateUrbLow() {
        return deadRateUrbLow;
    }

    /**
     *
     * sets the dead rate for low urbanization
     * @param deadRateUrbLow new dead rate for low urbanization
     */ 
    public void setDeadRateUrbLow(float deadRateUrbLow) {
        this.deadRateUrbLow = deadRateUrbLow;
    }

    /**
     *
     * gets the dead rate for high humidity
     * @return dead rate for high humidity
     */ 
    public float getDeadRateHumHigh() {
        return deadRateHumHigh;
    }

    /**
     *
     * sets the dead rate for high humidity
     * @param deadRateHumHigh new dead rate for high humidity
     */ 
    public void setDeadRateHumHigh(float deadRateHumHigh) {
        this.deadRateHumHigh = deadRateHumHigh;
    }

    /**
     *
     * gets the dead rate for medium humidity
     * @return dead rate for medium humidity
     */ 
    public float getDeadRateHumMed() {
        return deadRateHumMed;
    }

    /**
     *
     * sets the dead rate for medium humidity
     * @param deadRateHumMed new dead rate for medium humidity
     */ 
    public void setDeadRateHumMed(float deadRateHumMed) {
        this.deadRateHumMed = deadRateHumMed;
    }

    /**
     *
     * gets the dead rate for low humidity
     * @return dead rate for low humidity
     */ 
    public float getDeadRateHumLow() {
        return deadRateHumLow;
    }

    /**
     *
     * sets the dead rate for low humidity
     * @param deadRateHumLow new dead rate for low humidity
     */
    public void setDeadRateHumLow(float deadRateHumLow) {
        this.deadRateHumLow = deadRateHumLow;
    }

    /**
     *
     * gets the dead rate for high development
     * @return dead rate for high development
     */ 
    public float getDeadRateDevHigh() {
        return deadRateDevHigh;
    }

    /**
     *
     * sets the dead rate for high development
     * @param deadRateDevHigh new dead rate for high development
     */
    public void setDeadRateDevHigh(float deadRateDevHigh) {
        this.deadRateDevHigh = deadRateDevHigh;
    }

    /**
     *
     * gets the dead rate for medium development
     * @return dead rate for medium development
     */ 
    public float getDeadRateDevMed() {
        return deadRateDevMed;
    }

    /**
     *
     * sets the dead rate for medium development
     * @param deadRateDevMed new dead rate for medium development
     */
    public void setDeadRateDevMed(float deadRateDevMed) {
        this.deadRateDevMed = deadRateDevMed;
    }

    /**
     *
     * gets the dead rate for low development
     * @return dead rate for low development
     */ 
    public float getDeadRateDevLow() {
        return deadRateDevLow;
    }

    /**
     *
     * sets the dead rate for low development
     * @param deadRateDevLow new dead rate for low development
     */
    public void setDeadRateDevLow(float deadRateDevLow) {
        this.deadRateDevLow = deadRateDevLow;
    }
    
    /**
     *
     * Serializes this request to json
     * @return serialized json or null if exception
     */
    public String toSendString(){
        ObjectMapper objMapper = new ObjectMapper();
        String json = null;
        try {
            json = objMapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RuleChangeRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
