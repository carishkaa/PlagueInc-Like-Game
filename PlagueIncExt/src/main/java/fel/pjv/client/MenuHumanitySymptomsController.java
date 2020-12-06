/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.RuleChangeRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * 
 * Represents a class for Humanity Symptoms Window
 */
public class MenuHumanitySymptomsController {
    
    String[] description = new String[15];
    Map<Integer, Integer> DNA = new HashMap<>();
    private RuleChangeRequest ruleChangeRequest = new RuleChangeRequest(0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

    @FXML Button transmissionBtn;
    @FXML Button symptomsBtn;
    @FXML Button abilitiesBtn;
    
    @FXML Button exitBtn;
    
    @FXML Button evolveBtn;
    
    @FXML Button Nausea;
    @FXML Button Coughing;
    @FXML Button Rash;
    
    @FXML Button Pheumonia;
    @FXML Button ImmuneSuppresion;
    @FXML Button Sweating;
    
    @FXML Button TotalOrganFailure;
    @FXML Button Coma;
    @FXML Button HemorrhagicShock;
    
    @FXML Button Seizures;
    @FXML Button Paranoia;
    @FXML Button Tumours;
    
    @FXML Button Insomnia;
    @FXML Button Cysts;
    @FXML Button Anaemia;
    
    @FXML Label descriptionTitleLabel;
    @FXML Label descriptionLabel;
    @FXML Label DNALabel;
    
    Button thisBtn;
    
    ArrayList<Button> allBtnList = new ArrayList<>();
    ArrayList<Button> childrenList = new ArrayList<>();
    ArrayList<Button> boughtBtnList = new ArrayList<>();
    
    @FXML
    private void nauseaBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Nausea");
        descriptionLabel.setText(description[0]);
        childrenList.clear();
        childrenList.add(Pheumonia);
        thisBtn = Nausea;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
           -0.001f,     -0.002f,     -0.001f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void coughingBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Coughing");
        descriptionLabel.setText(description[1]);
        childrenList.clear();
        childrenList.add(Pheumonia);
        childrenList.add(ImmuneSuppresion);
        thisBtn = Coughing;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
           -0.005f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void rashBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Rash");
        descriptionLabel.setText(description[2]);
        childrenList.clear();
        childrenList.add(ImmuneSuppresion);
        childrenList.add(Sweating);
        thisBtn = Rash;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
           -0.002f,     -0.001f,     -0.001f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void pheumoniaBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Pheumonia");
        descriptionLabel.setText(description[3]);
        childrenList.clear();
        childrenList.add(TotalOrganFailure);
        childrenList.add(Coma);
        thisBtn = Pheumonia;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,     -0.005f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void suppresionBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Immune Suppresion");
        descriptionLabel.setText(description[4]);
        childrenList.clear();
        childrenList.add(Coma);
        childrenList.add(HemorrhagicShock);
        thisBtn = ImmuneSuppresion;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
           -0.001f,     -0.002f,     -0.002f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void sweatingBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Sweating");
        descriptionLabel.setText(description[5]);
        childrenList.clear();
        childrenList.add(HemorrhagicShock);
        thisBtn = Sweating;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,     -0.005f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void organFailureBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Total Organ Failure");
        descriptionLabel.setText(description[6]);
        childrenList.clear();
        thisBtn = TotalOrganFailure;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
           -0.001f,      0.000f,     -0.001f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
           -0.001f,      0.000f,     -0.001f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void comaBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Coma");
        descriptionLabel.setText(description[7]);
        childrenList.clear();
        thisBtn = Coma;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,     -0.001f,      0.000f,     //temperature 
           -0.001f,     -0.001f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
           -0.001f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void hemorrhagicShockBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Hemorrhagic Shock");
        descriptionLabel.setText(description[8]);
        childrenList.clear();
        thisBtn = HemorrhagicShock;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,     -0.001f,     //humidity
            0.000f,      0.000f,     -0.001f      //development
        );
    }
    
    @FXML
    private void seizuresBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Seizures");
        descriptionLabel.setText(description[9]);
        childrenList.clear();
        childrenList.add(TotalOrganFailure);
        thisBtn = Seizures;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
           -0.001f,      0.000f,     -0.001f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
           -0.001f,      0.000f,     -0.001f     //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
           -0.001f,      0.000f,     -0.001f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
           -0.001f,      0.000f,     -0.001f      //development
        );
    }
    
    @FXML
    private void paranoiaBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Paranoia");
        descriptionLabel.setText(description[10]);
        childrenList.clear();
        childrenList.add(TotalOrganFailure);
        childrenList.add(Coma);
        thisBtn = Paranoia;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
           -0.001f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
           -0.001f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void tumoursBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Tumours");
        descriptionLabel.setText(description[11]);
        childrenList.clear();
        childrenList.add(HemorrhagicShock);
        childrenList.add(Coma);
        thisBtn = Tumours;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,     -0.005f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
           -0.005f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,     -0.001f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
           -0.001f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void insomniaBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Insomnia");
        descriptionLabel.setText(description[12]);
        childrenList.clear();
        childrenList.add(Seizures);
        childrenList.add(Paranoia);
        thisBtn = Insomnia;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
           -0.005f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,     -0.005f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void cystsBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Cysts");
        descriptionLabel.setText(description[13]);
        childrenList.clear();
        childrenList.add(Paranoia);
        childrenList.add(Tumours);
        thisBtn = Cysts;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,     -0.005f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,     -0.005f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void anaemiaBtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Anaemia");
        descriptionLabel.setText(description[14]);
        childrenList.clear();
        childrenList.add(Tumours);
        thisBtn = Anaemia;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeInfData(
            //HIGH      MEDIUM      LOW
            0.000f,     -0.005f,      0.000f,     //temperature 
            0.000f,      0.000f,     -0.005f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
        
        ruleChangeDeadData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void evolveBtnAction(ActionEvent event) throws IOException {
        childrenList.forEach((button) -> {
            button.setVisible(true);
        });
        boughtBtnList.add(thisBtn);
        Request request = new Request(Client.getMyIP(), Client.getOpponentIP(), Request.RequestType.RULECHANGE, ruleChangeRequest.toSendString());
        request.SendRequest();
        
        DNALabel.setVisible(false);
        thisBtn.setStyle("-fx-background-color: #090a0c, "
                    + "linear-gradient(#38424b 0%, #1f2429 20%, #191d22 100%), "
                    + "linear-gradient(#20262b, #191d22), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(114,131,148,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        Client.setDNA(Client.getDNA() - DNA.get(allBtnList.indexOf(thisBtn)));
        System.out.println("current DNA: " + Client.getDNA());
        
        evolveBtn.setDisable(true);
    }
    
    private void evolveBtnSetDisable(){
        if (evolveBtn.isDisable() || (Client.getDNA() >= DNA.get(allBtnList.indexOf(thisBtn)))) {
            evolveBtn.setDisable(false);
        }

        if (boughtBtnList.contains(thisBtn) || (Client.getDNA() < DNA.get(allBtnList.indexOf(thisBtn)))) {
            evolveBtn.setDisable(true);
        }

        if (boughtBtnList.contains(thisBtn)) {
            DNALabel.setVisible(false);
        } else {
            DNALabel.setVisible(true);
        }
    }
    
    private void ruleChangeInfData(float infectionRateTempHigh, float infectionRateTempMed, 
            float infectionRateTempLow, float infectionRateUrbHigh, 
            float infectionRateUrbMed, float infectionRateUrbLow, 
            float infectionRateHumHigh, float infectionRateHumMed, 
            float infectionRateHumLow, float infectionRateDevHigh, 
            float infectionRateDevMed, float infectionRateDevLow){
        
        ruleChangeRequest.setInfectionRateTempHigh(infectionRateTempHigh);
        ruleChangeRequest.setInfectionRateTempMed(infectionRateTempMed);
        ruleChangeRequest.setInfectionRateTempLow(infectionRateTempLow);
        
        ruleChangeRequest.setInfectionRateUrbHigh(infectionRateUrbHigh);
        ruleChangeRequest.setInfectionRateUrbMed(infectionRateUrbMed);
        ruleChangeRequest.setInfectionRateUrbLow(infectionRateUrbLow);
        
        ruleChangeRequest.setInfectionRateHumHigh(infectionRateHumHigh);
        ruleChangeRequest.setInfectionRateHumMed(infectionRateHumMed);
        ruleChangeRequest.setInfectionRateHumLow(infectionRateHumLow);
        
        ruleChangeRequest.setInfectionRateDevHigh(infectionRateDevHigh);
        ruleChangeRequest.setInfectionRateDevMed(infectionRateDevMed);
        ruleChangeRequest.setInfectionRateDevLow(infectionRateDevLow);
    }
    
    private void ruleChangeDeadData(float deadRateTempHigh, float deadRateTempMed, 
            float deadRateTempLow, float deadRateUrbHigh, 
            float deadRateUrbMed, float deadRateUrbLow, 
            float deadRateHumHigh, float deadRateHumMed, 
            float deadRateHumLow, float deadRateDevHigh, 
            float deadRateDevMed, float deadRateDevLow){
        
        ruleChangeRequest.setDeadRateTempHigh(deadRateTempHigh);
        ruleChangeRequest.setDeadRateTempMed(deadRateTempMed);
        ruleChangeRequest.setDeadRateTempLow(deadRateTempLow);
        
        ruleChangeRequest.setDeadRateUrbHigh(deadRateUrbHigh);
        ruleChangeRequest.setDeadRateUrbMed(deadRateUrbMed);
        ruleChangeRequest.setDeadRateUrbLow(deadRateUrbLow);
        
        ruleChangeRequest.setDeadRateHumHigh(deadRateHumHigh);
        ruleChangeRequest.setDeadRateHumMed(deadRateHumMed);
        ruleChangeRequest.setDeadRateHumLow(deadRateHumLow);
        
        ruleChangeRequest.setDeadRateDevHigh(deadRateDevHigh);
        ruleChangeRequest.setDeadRateDevMed(deadRateDevMed);
        ruleChangeRequest.setDeadRateDevLow(deadRateDevLow);
    }
    
    @FXML
    private void transmissionBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageT = menu.getStageTransmission();
        Stage stageS = menu.getStageSymptoms();
        
        stageS.hide();
        stageT.show();
    }
    
    @FXML
    private void abilitiesBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageA = menu.getStageAbilities();
        Stage stageS = menu.getStageSymptoms();
        
        stageS.hide();
        stageA.show();
    }
    
    @FXML
    private void symptomsBtnAction(ActionEvent event) throws IOException {
    }
    
    @FXML
    private void exitBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageS = menu.getStageSymptoms();
        stageS.hide();
    }

    /**
     * 
     * Initializes Humanity Symptoms Window
     * @throws FileNotFoundException
     */     
    @FXML
    public void initialize() throws FileNotFoundException {
        loadDescription();
        loadDNA();
        allBtnList.add(Nausea);
        allBtnList.add(Coughing);
        allBtnList.add(Rash);
        allBtnList.add(Pheumonia);
        allBtnList.add(ImmuneSuppresion);
        allBtnList.add(Sweating);
        allBtnList.add(TotalOrganFailure);
        allBtnList.add(Coma);
        allBtnList.add(HemorrhagicShock);
        allBtnList.add(Seizures);
        allBtnList.add(Paranoia);
        allBtnList.add(Tumours);
        allBtnList.add(Insomnia);
        allBtnList.add(Cysts);
        allBtnList.add(Anaemia);

        for (Button btn : allBtnList) {
            btn.setStyle("-fx-background-color: #090a0c "
                    + "linear-gradient(#0055a5 0%, #003363 20%, #001930 100%), "
                    + "linear-gradient(#021326, #260202), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(32,72,165,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                    + "-fx-text-fill: linear-gradient(white, #d0d0d0);");
        }
    }
    
    private void loadDescription() throws FileNotFoundException {
        try (
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/menu_humanity_symptoms_description.txt"));
        ) {
            String s = br.readLine();
            int i = 0;
            while (s != null) {
                description[i++] = s;
                s = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("=== Error loadDescription()");        
        } 
    }
    
    private void loadDNA() throws FileNotFoundException {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/menu_humanity_symptoms_DNA.txt"));) {
            String s = br.readLine();
            int i = 0;
            while (s != null) {
                DNA.put(i, Integer.parseInt(s));
                System.out.println("=====" + i);
                System.out.println("strToInt " + Integer.parseInt(s));
                System.out.println("allBtnList size " + allBtnList.size());
                i++;
                s = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("=== Error loadDescription()");
        }
    }
}
