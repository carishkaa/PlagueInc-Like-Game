/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import javafx.stage.Stage;

/**
 * 
 * Represents class that contains Menu stages
 */
public class MenuStages {
    private Stage stageTransmission;
    private Stage stageSymptoms;
    private Stage stageAbilities;
    
    /**
     * A constructor to create Menu Stages
     * @param stageTransmission stage where player can improve transmission
     * @param stageSymptoms stage where player can improve symptoms
     * @param stageAbilities stage where player can improve abilities
     */
    public MenuStages(Stage stageTransmission, Stage stageSymptoms, Stage stageAbilities) {
        this.stageTransmission = stageTransmission;
        this.stageSymptoms = stageSymptoms;
        this.stageAbilities = stageAbilities;
    }

    /**
     * Gets Transmission stage
     * @return Transmission stage
     */
    public Stage getStageTransmission() {
        return stageTransmission;
    }

    /**
     * Sets Transmission stage 
     * @param stageTransmission Transmission stage
     */
    public void setStageTransmission(Stage stageTransmission) {
        this.stageTransmission = stageTransmission;
    }

    /**
     * Gets Symptoms stage
     * @return Symptoms stage
     */
    public Stage getStageSymptoms() {
        return stageSymptoms;
    }
    
    /**
     * Sets Symptoms stage 
     * @param stageSymptoms Symptoms stage
     */
    public void setStageSymptoms(Stage stageSymptoms) {
        this.stageSymptoms = stageSymptoms;
    }

    /**
     * Gets Abilities stage
     * @return Abilities stage
     */
    public Stage getStageAbilities() {
        return stageAbilities;
    }
    
    /**
     * Sets Abilities stage
     * @param stageAbilities Abilities stage
     */
    public void setStageAbilities(Stage stageAbilities) {
        this.stageAbilities = stageAbilities;
    }
}
