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
 * Represents a class for Humanity Abilities Window
 */
public class MenuHumanityAbilitiesController {
    
    String[] description = new String[4];
    Map<Integer, Integer> DNA = new HashMap<>();
    private RuleChangeRequest ruleChangeRequest = new RuleChangeRequest(0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

    @FXML Button transmissionBtn;
    @FXML Button symptomsBtn;
    @FXML Button abilitiesBtn;
    
    @FXML Button exitBtn;
    
    @FXML Button coldResistance1;
    @FXML Button coldResistance2;
    @FXML Button heatResistance1;
    @FXML Button heatResistance2;
    @FXML Button drugResistance1;
    @FXML Button drugResistance2;
    @FXML Button bacterialResilience1;
    @FXML Button bacterialResilience2;
    
    @FXML Button evolveBtn;
    
    @FXML Label descriptionTitleLabel;
    @FXML Label descriptionLabel;
    @FXML Label DNALabel;
    
    Button thisBtn;
    Button nextBtn;
    
    ArrayList<Button> allBtnList = new ArrayList<>();
    ArrayList<Button> boughtBtnList = new ArrayList<>();
    
    @FXML
    private void coldResistance1BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Cold Resistance 1");
        descriptionLabel.setText(description[0]);
        thisBtn = coldResistance1;
        nextBtn = coldResistance2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,     -0.005f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
           -0.005f,     -0.001f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void coldResistance2BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Cold Resistance 2");
        descriptionLabel.setText(description[0]);
        thisBtn = coldResistance2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,     -0.005f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
           -0.005f,     -0.001f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f     //development
        );
    }
    
    
    @FXML
    private void heatResistance1BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Heat Resistance 1");
        descriptionLabel.setText(description[1]);
        thisBtn = heatResistance1;
        nextBtn = heatResistance2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
           -0.005f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void heatResistance2BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Heat Resistance 2");
        descriptionLabel.setText(description[1]);
        thisBtn = heatResistance2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
           -0.005f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f     //development
        );
    }
    
    @FXML
    private void drugResistance1BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Drug Resistance 1");
        descriptionLabel.setText(description[2]);
        thisBtn = drugResistance1;
        nextBtn = drugResistance2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            -0.005f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void drugResistance2BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Drug Resistance 2");
        descriptionLabel.setText(description[2]);
        thisBtn = drugResistance2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            0.000f,      0.000f,      0.000f,     //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            0.000f,      0.000f,      0.000f,     //humidity
            -0.005f,      0.000f,      0.000f      //development
        );
    }
    
    
    @FXML
    private void bacteriaResilience1BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Bacterial Resilience 1");
        descriptionLabel.setText(description[3]);
        thisBtn = bacterialResilience1;
        nextBtn = bacterialResilience2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            -0.005f,     -0.005f,      -0.005f,    //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            -0.005f,     0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void bacteriaResilience2BtnAction(ActionEvent event) throws IOException {
        descriptionTitleLabel.setText("Bacterial Resilience 2");
        descriptionLabel.setText(description[3]);
        thisBtn = bacterialResilience2;
        DNALabel.setText("-" + DNA.get(allBtnList.indexOf(thisBtn)) + " DNA");
        evolveBtnSetDisable();
        
        ruleChangeData(
            //HIGH      MEDIUM      LOW
            -0.005f,     -0.005f,      -0.005f,    //temperature 
            0.000f,      0.000f,      0.000f,     //urbanisation
            -0.005f,     0.000f,      0.000f,     //humidity
            0.000f,      0.000f,      0.000f      //development
        );
    }
    
    @FXML
    private void evolveBtnAction(ActionEvent event) throws IOException {
        nextBtn.setVisible(true);
        boughtBtnList.add(thisBtn);
        Request request = new Request(Client.getMyIP(), Client.getOpponentIP(), Request.RequestType.RULECHANGE, ruleChangeRequest.toSendString());
        request.SendRequest();
        evolveBtn.setDisable(true);
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
    
    private void ruleChangeData(float infectionRateTempHigh, float infectionRateTempMed, 
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
    
    @FXML
    private void transmissionBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageT = menu.getStageTransmission();
        Stage stageA = menu.getStageAbilities();
        
        stageA.hide();
        stageT.show();
    }
    
    @FXML
    private void symptomsBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageS = menu.getStageSymptoms();
        Stage stageA = menu.getStageAbilities();
        
        stageA.hide();
        stageS.show();
    }
    
    @FXML
    private void abilitiesBtnAction(ActionEvent event) throws IOException {
    }
    
    @FXML
    private void exitBtnAction(ActionEvent event) throws IOException {
        MenuStages menu = MainMapHumanity.menu;
        Stage stageA = menu.getStageAbilities();
        stageA.hide();
    }

    /**
     * 
     * Initializes Humanity ABilities Window
     * @throws FileNotFoundException
     */     
    @FXML
    public void initialize() throws FileNotFoundException {
        loadDescription();
        loadDNA();
        allBtnList.add(coldResistance1);
        allBtnList.add(coldResistance2);
        allBtnList.add(heatResistance1);
        allBtnList.add(heatResistance2);
        allBtnList.add(drugResistance1);
        allBtnList.add(drugResistance2);
        allBtnList.add(bacterialResilience1);
        allBtnList.add(bacterialResilience2);

        for (Button btn : allBtnList) {
            btn.setStyle("-fx-background-color: #090a0c "
                    + "linear-gradient(#0055a5 0%, #003363 20%, #001930 100%), "
                    + "linear-gradient(#021326, #260202), "
                    + "radial-gradient(center 50% 0%, radius 100%, rgba(32,72,165,0.9), rgba(255,255,255,0));" 
                    + "-fx-background-radius: 5,4,3,5;" 
                    + "-fx-background-insets: 0,1,2,0;" 
                    + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
        }
    }
    
    private void loadDescription() throws FileNotFoundException {
        try (
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/menu_humanity_abilities_description.txt"));
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
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/menu_humanity_abilities_DNA.txt"));) {
            String s = br.readLine();
            int i = 0;
            while (s != null) {
                DNA.put(i, Integer.parseInt(s));
                System.out.println("=====" + i);
                System.out.println("strToInt " +Integer.parseInt(s));
                System.out.println("allBtnList size " + allBtnList.size());
                i++;
                s = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("=== Error loadDescription()");
        }
    }
}
