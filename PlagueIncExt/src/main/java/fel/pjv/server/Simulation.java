/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.server;

import fel.pjv.semestral_proj.help.GameCell;
import fel.pjv.semestral_proj.help.RuleChangeRequest;
import fel.pjv.server.PopulationCell.CellProperty;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Class represents simulation of disease on a planet
 */
public class Simulation {
    private PopulationCell[][] pc;
    private PopulationCell[][] old_pc;
    private List<GameCell> changedCells = new ArrayList<>();
    private List<GameCell> guessedCells = new ArrayList<>();
    private static final int ROWS = 75;
    private static final int COLUMNS = 166;
    private static final float VALUE_LOW = 0.02f;
    private static final float VALUE_MEDIUM = 0.05f;
    private static final float VALUE_HIGH = 0.09f;
    
    private volatile int guessCount = 10;
    private volatile float guessRate = 0.1f;
    
    private float totalPCCount;
    private float sickPCCount;
    private boolean everybodyDied;
    private boolean everybodyHealth;
    
    //parameters
    private volatile float infectionRateTempHigh = 0;
    private volatile float infectionRateTempMed = 0;
    private volatile float infectionRateTempLow = 0;

    private volatile float infectionRateUrbHigh = 0;
    private volatile float infectionRateUrbMed = 0;
    private volatile float infectionRateUrbLow = 0;

    private volatile float infectionRateHumHigh = 0;
    private volatile float infectionRateHumMed = 0;
    private volatile float infectionRateHumLow = 0;

    private volatile float infectionRateDevHigh = VALUE_LOW;
    private volatile float infectionRateDevMed = VALUE_MEDIUM;
    private volatile float infectionRateDevLow = VALUE_HIGH;

    private volatile float deadRateTempHigh = 0;
    private volatile float deadRateTempMed = 0;
    private volatile float deadRateTempLow = 0;

    private volatile float deadRateUrbHigh = 0;
    private volatile float deadRateUrbMed = 0;
    private volatile float deadRateUrbLow = 0;

    private volatile float deadRateHumHigh = 0;
    private volatile float deadRateHumMed = 0;
    private volatile float deadRateHumLow = 0;

    private volatile float deadRateDevHigh = 0;
    private volatile float deadRateDevMed = 0;
    private volatile float deadRateDevLow = 0;

    private ArrayList<PopulationCell> airportsList = new ArrayList<>();
    private ArrayList<PopulationCell> portsList = new ArrayList<>();

    /**
     *
     * Initializes the simulation
     * @param xStart X coordinate of start cell
     * @param yStart Y coordinate of start cell
     */     
    public void start(int xStart, int yStart) {
        int minX = 1000;
        int minY = 1000;
        // pokud uzivatel klikne na neexistujici bunku -> program hleda nejblizsi port
        if (pc[yStart][xStart] == null) {
            for (int i = 0; i < portsList.size(); i++) {
                if ((portsList.get(i).getX() - xStart) < minX && (portsList.get(i).getY() - yStart) < minY) {
                    minX = portsList.get(i).getX() - xStart;
                    minY = portsList.get(i).getY() - yStart;
                }
            }
            xStart = minX;
            yStart = minY;
        }
        pc[yStart][xStart].setSickCount(1);
        Logger.getLogger(SimulationRunner.class.getName()).log(Level.INFO, "yStart = " + yStart + ", xStart = " + xStart);
    }

    /**
     *
     * Basic constructor of simulation
     */ 
    public Simulation() {
        initPopulationCell();
    }

    /**
     *
     * Gets the list of changed cells
     * @return changedCells
     */
    public List<GameCell> getChangedCells() {
        return changedCells;
    }
    
    /**
     *
     * Computes next step of simulation
     */ 
    public void iterate(){
        GameCell cell;
        changedCells.clear();
        totalPCCount = 0;
        sickPCCount = 0;
        
        copyArray(pc, old_pc, COLUMNS, ROWS);

        for (int y = 1; y < ROWS - 1; y++) {
            for (int x = 1; x < COLUMNS - 1; x++) {
                // populace neexistuje
                if (old_pc[y][x] == null || old_pc[y][x].getSickCount() == 0) {
                    continue;
                }
                
                
                float infectionRate = calculateInfRate(old_pc[y][x]);
                float deadRate = calculateDeadRate(old_pc[y][x]);

                // pokud jeste jsou zdrave lidi v bunce -> infikujeme je
                if (old_pc[y][x].getSickCount() < old_pc[y][x].getTotalCount()) {
                    float sickCount = round(pc[y][x].getSickCount() * (infectionRate + 1));
                    pc[y][x].setSickCount(sickCount);
                }

                // pokud je pulka lidi v bunce infikovana -> infikujeme dalsi sousedni bunky 
                if (old_pc[y][x].getSickCount() >= old_pc[y][x].getTotalCount() / 3) {
                    
                    // pokud sousedni bunka existuje -> infikujeme ji (nahodne)
                    int yRandom = randomValue();
                    int xRandom = randomValue();
                    if (old_pc[y + yRandom][x + xRandom] != null && !(yRandom == 0 && xRandom == 0)) {
                        pc[y + yRandom][x + xRandom].setSickCount(round(old_pc[y + yRandom][x + xRandom].getSickCount() + 1));
                        cell = new GameCell(pc[y + yRandom][x + xRandom]);
                        addToChangedCellsList(cell);
                    }
                    
                    // pokud je v bunce letiste -> infekuje i jine (nahodne)
                    if (pc[y][x].isAirport() && randomValue(10) == 1) {
                        int indexRandom = randomValue(airportsList.size() - 1);
                        airportsList.get(indexRandom).setSickCount(round(airportsList.get(indexRandom).getSickCount() + 1));
                        cell = new GameCell(airportsList.get(indexRandom));
                        addToChangedCellsList(cell);
                    }
                    
                    // pokud je v bunce port -> infekuje i jine (nahodne)
                    if (pc[y][x].isPort() && randomValue(10) == 1) {
                        int indexRandom = randomValue(portsList.size() - 1);
                        portsList.get(indexRandom).setSickCount(round(portsList.get(indexRandom).getSickCount() + 1));
                        
                        cell = new GameCell(portsList.get(indexRandom));
                        addToChangedCellsList(cell);
                    }
                }

                // pokud lidi uz umiraji -> umira vic 
                if (old_pc[y][x].getDeadCount() > 0 && old_pc[y][x].getTotalCount() > 0 && randomDeadValue()) {
                    float tmpDeadCount = old_pc[y][x].getDeadCount() * deadRate; //pocet lidi ktere umreli prave ted
                    pc[y][x].setDeadCount(round(tmpDeadCount + old_pc[y][x].getDeadCount()));
                    pc[y][x].setSickCount(round(old_pc[y][x].getSickCount() - tmpDeadCount));
                    pc[y][x].setTotalCount(round(old_pc[y][x].getTotalCount() - tmpDeadCount));
                }
                
                if (!old_pc[y][x].equalsAll(pc[y][x])){
                    cell = new GameCell(pc[y][x]);
                    addToChangedCellsList(cell);
                }
                if(pc[y][x].getDeadCount() > 0){
                    addToGuessedCellsList(new GameCell(pc[y][x]));
                }
                totalPCCount += pc[y][x].getTotalCount();
                sickPCCount += pc[y][x].getSickCount();
            }
        }
        
        // pandemia is winner
        if (totalPCCount == 0) {
            everybodyDied = true;
        } else 
        // humanity is winner
        if (sickPCCount == 0) {
            everybodyHealth = true;
        }
}

    private int randomValue(int size) {
        int randomIntValue = (int) (Math.random() * size);
        return randomIntValue;
    }

    private int randomValue() {
        int randomIntValue = -1 + (int) (Math.random() * 3);
        return randomIntValue;
    }
    
    private boolean randomDeadValue() {
        int randomIntValue = (int) (Math.random() * 2);
        return randomIntValue != 0;
    }

    private static float round(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
    private void addToChangedCellsList(GameCell cell) {
        if (!changedCells.contains(cell) && cell.x != -1) {
            changedCells.add(cell);
        } else if (changedCells.contains(cell)) {
            int index = changedCells.indexOf(cell);
            changedCells.get(index).sickCount = cell.sickCount;
            changedCells.get(index).deadCount = cell.deadCount;
            changedCells.get(index).totalCount = cell.totalCount;
        }
    }
    
    private void addToGuessedCellsList(GameCell cell) {
        if (!guessedCells.contains(cell) && cell.x != -1) {
            guessedCells.add(cell);
        } else if (guessedCells.contains(cell)) {
            int index = guessedCells.indexOf(cell);
            guessedCells.get(index).sickCount = cell.sickCount;
            guessedCells.get(index).deadCount = cell.deadCount;
            guessedCells.get(index).totalCount = cell.totalCount;
        }
    }
    
    private void copyArray(PopulationCell[][] pc, PopulationCell[][] old_pc, int xSize, int ySize){
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (pc[i][j] == null){
                    old_pc[i][j] = null;
                    continue;
                }
                old_pc[i][j].setTotalCount(pc[i][j].getTotalCount());
                old_pc[i][j].setSickCount(pc[i][j].getSickCount());
                old_pc[i][j].setDeadCount(pc[i][j].getDeadCount());
                old_pc[i][j].setTemperature(pc[i][j].getTemperature());
                old_pc[i][j].setHumidity(pc[i][j].getHumidity());
                old_pc[i][j].setUrbanisation(pc[i][j].getUrbanisation());
                old_pc[i][j].setDevelopment(pc[i][j].getDevelopment());
            }
        }
    }
    
    private float calculateDeadRate(PopulationCell cell) {
        float deadRateTemperature = 0;
        float deadRateHumidity = 0;
        float deadRateUrbanisation = 0;
        float deadRateDevelopment = 0;

        if (cell.getTemperature().equals(PopulationCell.CellProperty.LOW)) {
            deadRateTemperature = getDeadRateTempLow();
        }
        if (cell.getTemperature().equals(PopulationCell.CellProperty.MEDIUM)) {
            deadRateTemperature = getDeadRateTempMed();
        }
        if (cell.getTemperature().equals(PopulationCell.CellProperty.HIGH)) {
            deadRateTemperature = getDeadRateTempHigh();
        }

        if (cell.getHumidity().equals(PopulationCell.CellProperty.LOW)) {
            deadRateHumidity = getDeadRateHumLow();
        }
        if (cell.getHumidity().equals(PopulationCell.CellProperty.MEDIUM)) {
            deadRateHumidity = getDeadRateHumMed();
        }
        if (cell.getHumidity().equals(PopulationCell.CellProperty.HIGH)) {
            deadRateHumidity = getDeadRateHumHigh();
        }

        if (cell.getUrbanisation().equals(PopulationCell.CellProperty.LOW)) {
            deadRateUrbanisation = getDeadRateUrbLow();
        }
        if (cell.getUrbanisation().equals(PopulationCell.CellProperty.MEDIUM)) {
            deadRateUrbanisation = getDeadRateUrbMed();
        }
        if (cell.getUrbanisation().equals(PopulationCell.CellProperty.HIGH)) {
            deadRateUrbanisation = getDeadRateUrbHigh();
        }

        if (cell.getDevelopment().equals(PopulationCell.CellProperty.LOW)) {
            deadRateDevelopment = getDeadRateDevLow();
        }
        if (cell.getDevelopment().equals(PopulationCell.CellProperty.MEDIUM)) {
            deadRateDevelopment = getDeadRateDevMed();
        }
        if (cell.getDevelopment().equals(PopulationCell.CellProperty.HIGH)) {
            deadRateDevelopment = getDeadRateDevHigh();
        }

        return deadRateTemperature
                + deadRateHumidity
                + deadRateUrbanisation
                + deadRateDevelopment;
    }

    private float calculateInfRate(PopulationCell cell) {
        float infectionRateTemperature = 0;
        float infectionRateHumidity = 0;
        float infectionRateUrbanisation = 0;
        float infectionRateDevelopment = 0;
        if (cell.getTemperature().equals(PopulationCell.CellProperty.LOW)) {
            infectionRateTemperature = getInfectionRateTempLow();
        }
        if (cell.getTemperature().equals(PopulationCell.CellProperty.MEDIUM)) {
            infectionRateTemperature = getInfectionRateTempMed();
        }
        if (cell.getTemperature().equals(PopulationCell.CellProperty.HIGH)) {
            infectionRateTemperature = getInfectionRateTempHigh();
        }

        if (cell.getHumidity().equals(PopulationCell.CellProperty.LOW)) {
            infectionRateHumidity = getInfectionRateHumLow();
        }
        if (cell.getHumidity().equals(PopulationCell.CellProperty.MEDIUM)) {
            infectionRateHumidity = getInfectionRateHumMed();
        }
        if (cell.getHumidity().equals(PopulationCell.CellProperty.HIGH)) {
            infectionRateHumidity = getInfectionRateHumHigh();
        }

        if (cell.getUrbanisation().equals(PopulationCell.CellProperty.LOW)) {
            infectionRateUrbanisation = getInfectionRateUrbLow();
        }
        if (cell.getUrbanisation().equals(PopulationCell.CellProperty.MEDIUM)) {
            infectionRateUrbanisation = getInfectionRateUrbMed();
        }
        if (cell.getUrbanisation().equals(PopulationCell.CellProperty.HIGH)) {
            infectionRateUrbanisation = getInfectionRateUrbHigh();
        }

        if (cell.getDevelopment().equals(PopulationCell.CellProperty.LOW)) {
            infectionRateDevelopment = getInfectionRateDevLow();
        }
        if (cell.getDevelopment().equals(PopulationCell.CellProperty.MEDIUM)) {
            infectionRateDevelopment = getInfectionRateDevMed();
        }
        if (cell.getDevelopment().equals(PopulationCell.CellProperty.HIGH)) {
            infectionRateDevelopment = getInfectionRateDevHigh();
        }

        return infectionRateTemperature
                + infectionRateHumidity
                + infectionRateUrbanisation
                + infectionRateDevelopment;
    }

    private void initPopulationCell() {
        pc = new PopulationCell[ROWS][COLUMNS];
        old_pc = new PopulationCell[ROWS][COLUMNS];
        CellProperty[] cellPropertiesValues = CellProperty.values();
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/main/resources/txt_files/population_cell.txt"));) {
            String line = br.readLine();
            while (line != null) {
                String[] cellPropertiesLine = line.split(" ");

                int x = Integer.parseInt(cellPropertiesLine[0]);
                int y = Integer.parseInt(cellPropertiesLine[1]);
                CellProperty temperature = cellPropertiesValues[Integer.parseInt(cellPropertiesLine[2])];
                CellProperty humidity = cellPropertiesValues[Integer.parseInt(cellPropertiesLine[3])];
                CellProperty urbanisation = cellPropertiesValues[Integer.parseInt(cellPropertiesLine[4])];
                CellProperty development = cellPropertiesValues[Integer.parseInt(cellPropertiesLine[5])];

                if (cellPropertiesLine.length == 8) {
                    boolean isAirport = (Integer.parseInt(cellPropertiesLine[6]) == 1);
                    boolean isPort = (Integer.parseInt(cellPropertiesLine[7]) == 1);
                    createPCInstance(x, y, temperature, humidity, urbanisation, development, isAirport, isPort);
                } else {
                    createPCInstance(x, y, temperature, humidity, urbanisation, development);
                }
                line = br.readLine();
            }

        } catch (IOException ex) {
            Logger.getLogger(NIOServer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createPCInstance(int x, int y, PopulationCell.CellProperty temperature,
            PopulationCell.CellProperty humidity, PopulationCell.CellProperty urbanisation,
            PopulationCell.CellProperty development) {
        pc[y][x] = new PopulationCell(x, y, temperature, humidity, urbanisation, development);
        old_pc[y][x] = new PopulationCell(x, y, temperature, humidity, urbanisation, development);

    }

    private void createPCInstance(int x, int y, PopulationCell.CellProperty temperature,
            PopulationCell.CellProperty humidity, PopulationCell.CellProperty urbanisation,
            PopulationCell.CellProperty development, boolean isAirport, boolean isPort) {
        pc[y][x] = new PopulationCell(x, y, temperature, humidity, urbanisation, development, isAirport, isPort);
        old_pc[y][x] = new PopulationCell(x, y, temperature, humidity, urbanisation, development);

        if (isAirport) {
            airportsList.add(pc[y][x]);
        }
        if (isPort) {
            portsList.add(pc[y][x]);
        }
    }
    
    /**
     *
     * True if there are no alive people left
     * @return everybodyDied
     */
    public boolean isEverybodyDied() {
        return everybodyDied;
    }
    
    /**
     *
     * True if there are no infected people left
     * @return everybodyHealth
     */
    public boolean isEverybodyHealth() {
        return everybodyHealth;
    }

    /**
     *
     * Gets the infection rate for regions with high temperature
     * @return infectionRateTempHigh
     */
    public float getInfectionRateTempHigh() {
        return infectionRateTempHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high temperature
     * @param infectionRateTempHigh
     */
    public void setInfectionRateTempHigh(float infectionRateTempHigh) {
        this.infectionRateTempHigh = infectionRateTempHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium temperature
     * @return infectionRateTempMed
     */
    public float getInfectionRateTempMed() {
        return infectionRateTempMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium temperature
     * @param infectionRateTempMed
     */
    public void setInfectionRateTempMed(float infectionRateTempMed) {
        this.infectionRateTempMed = infectionRateTempMed;
    }

    /**
     *
     * Gets the infection rate for regions with low temperature
     * @return infectionRateTempLow
     */
    public float getInfectionRateTempLow() {
        return infectionRateTempLow;
    }

    /**
     *
     * Sets the infection rate for regions with low temperature
     * @param infectionRateTempLow
     */
    public void setInfectionRateTempLow(float infectionRateTempLow) {
        this.infectionRateTempLow = infectionRateTempLow;
    }

    /**
     *
     * Gets the infection rate for regions with high urbanization
     * @return infectionRateUrbHigh
     */
    public float getInfectionRateUrbHigh() {
        return infectionRateUrbHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high urbanization
     * @param infectionRateUrbHigh
     */
    public void setInfectionRateUrbHigh(float infectionRateUrbHigh) {
        this.infectionRateUrbHigh = infectionRateUrbHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium urbanization
     * @return infectionRateUrbMed
     */
    public float getInfectionRateUrbMed() {
        return infectionRateUrbMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium urbanization
     * @param infectionRateUrbMed
     */
    public void setInfectionRateUrbMed(float infectionRateUrbMed) {
        this.infectionRateUrbMed = infectionRateUrbMed;
    }

    /**
     *
     * Gets the infection rate for regions with low urbanization
     * @return infectionRateUrbLow
     */
    public float getInfectionRateUrbLow() {
        return infectionRateUrbLow;
    }

    /**
     *
     * Sets the infection rate for regions with low urbanization
     * @param infectionRateUrbLow
     */
    public void setInfectionRateUrbLow(float infectionRateUrbLow) {
        this.infectionRateUrbLow = infectionRateUrbLow;
    }

    /**
     *
     * Gets the infection rate for regions with high humidity
     * @return infectionRateHumHigh
     */
    public float getInfectionRateHumHigh() {
        return infectionRateHumHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high humidity
     * @param infectionRateHumHigh
     */
    public void setInfectionRateHumHigh(float infectionRateHumHigh) {
        this.infectionRateHumHigh = infectionRateHumHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium humidity
     * @return infectionRateHumMed
     */
    public float getInfectionRateHumMed() {
        return infectionRateHumMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium humidity
     * @param infectionRateHumMed
     */
    public void setInfectionRateHumMed(float infectionRateHumMed) {
        this.infectionRateHumMed = infectionRateHumMed;
    }

    /**
     *
     * Gets the infection rate for regions with low humidity
     * @return infectionRateHumLow
     */
    public float getInfectionRateHumLow() {
        return infectionRateHumLow;
    }

    /**
     *
     * Sets the infection rate for regions with low humidity
     * @param infectionRateHumLow
     */
    public void setInfectionRateHumLow(float infectionRateHumLow) {
        this.infectionRateHumLow = infectionRateHumLow;
    }

    /**
     *
     * Gets the infection rate for regions with high development
     * @return infectionRateDevHigh
     */
    public float getInfectionRateDevHigh() {
        return infectionRateDevHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high development
     * @param infectionRateDevHigh
     */
    public void setInfectionRateDevHigh(float infectionRateDevHigh) {
        this.infectionRateDevHigh = infectionRateDevHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium development
     * @return infectionRateDevMed
     */
    public float getInfectionRateDevMed() {
        return infectionRateDevMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium development
     * @param infectionRateDevMed
     */
    public void setInfectionRateDevMed(float infectionRateDevMed) {
        this.infectionRateDevMed = infectionRateDevMed;
    }

    /**
     *
     * Gets the infection rate for regions with low development
     * @return infectionRateDevLow
     */
    public float getInfectionRateDevLow() {
        return infectionRateDevLow;
    }

    /**
     *
     * Sets the infection rate for regions with low development
     * @param infectionRateDevLow
     */
    public void setInfectionRateDevLow(float infectionRateDevLow) {
        this.infectionRateDevLow = infectionRateDevLow;
    }

    /**
     *
     * Gets the infection rate for regions with high temperature
     * @return deadRateTempHigh
     */
    public float getDeadRateTempHigh() {
        return deadRateTempHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high temperature
     * @param deadRateTempHigh
     */
    public void setDeadRateTempHigh(float deadRateTempHigh) {
        this.deadRateTempHigh = deadRateTempHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium temperature
     * @return deadRateTempMed
     */
    public float getDeadRateTempMed() {
        return deadRateTempMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium temperature
     * @param deadRateTempMed
     */
    public void setDeadRateTempMed(float deadRateTempMed) {
        this.deadRateTempMed = deadRateTempMed;
    }

    /**
     *
     * Gets the infection rate for regions with low temperature
     * @return deadRateTempLow
     */
    public float getDeadRateTempLow() {
        return deadRateTempLow;
    }

    /**
     *
     * Sets the infection rate for regions with low temperature
     * @param deadRateTempLow
     */
    public void setDeadRateTempLow(float deadRateTempLow) {
        this.deadRateTempLow = deadRateTempLow;
    }

    /**
     *
     * Gets the infection rate for regions with high urbanization
     * @return deadRateUrbHigh
     */
    public float getDeadRateUrbHigh() {
        return deadRateUrbHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high urbanization
     * @param deadRateUrbHigh
     */
    public void setDeadRateUrbHigh(float deadRateUrbHigh) {
        this.deadRateUrbHigh = deadRateUrbHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium urbanization
     * @return deadRateUrbMed
     */
    public float getDeadRateUrbMed() {
        return deadRateUrbMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium urbanization
     * @param deadRateUrbMed
     */
    public void setDeadRateUrbMed(float deadRateUrbMed) {
        this.deadRateUrbMed = deadRateUrbMed;
    }

    /**
     *
     * Gets the infection rate for regions with low urbanization
     * @return deadRateUrbLow
     */
    public float getDeadRateUrbLow() {
        return deadRateUrbLow;
    }

    /**
     *
     * Sets the infection rate for regions with low urbanization
     * @param deadRateUrbLow
     */
    public void setDeadRateUrbLow(float deadRateUrbLow) {
        this.deadRateUrbLow = deadRateUrbLow;
    }

    /**
     *
     * Gets the infection rate for regions with high humidity
     * @return deadRateHumHigh
     */
    public float getDeadRateHumHigh() {
        return deadRateHumHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high humidity
     * @param deadRateHumHigh
     */
    public void setDeadRateHumHigh(float deadRateHumHigh) {
        this.deadRateHumHigh = deadRateHumHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium humidity
     * @return deadRateHumMed
     */
    public float getDeadRateHumMed() {
        return deadRateHumMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium humidity
     * @param deadRateHumMed
     */
    public void setDeadRateHumMed(float deadRateHumMed) {
        this.deadRateHumMed = deadRateHumMed;
    }

    /**
     *
     * Gets the infection rate for regions with low humidity
     * @return deadRateHumLow
     */
    public float getDeadRateHumLow() {
        return deadRateHumLow;
    }

    /**
     *
     * Sets the infection rate for regions with low humidity
     * @param deadRateHumLow
     */
    public void setDeadRateHumLow(float deadRateHumLow) {
        this.deadRateHumLow = deadRateHumLow;
    }

    /**
     *
     * Gets the infection rate for regions with high development
     * @return deadRateDevHigh
     */
    public float getDeadRateDevHigh() {
        return deadRateDevHigh;
    }

    /**
     *
     * Sets the infection rate for regions with high development
     * @param deadRateDevHigh
     */
    public void setDeadRateDevHigh(float deadRateDevHigh) {
        this.deadRateDevHigh = deadRateDevHigh;
    }

    /**
     *
     * Gets the infection rate for regions with medium development
     * @return deadRateDevMed
     */
    public float getDeadRateDevMed() {
        return deadRateDevMed;
    }

    /**
     *
     * Sets the infection rate for regions with medium development
     * @param deadRateDevMed
     */
    public void setDeadRateDevMed(float deadRateDevMed) {
        this.deadRateDevMed = deadRateDevMed;
    }

    /**
     *
     * Gets the infection rate for regions with low development
     * @return deadRateDevLow
     */
    public float getDeadRateDevLow() {
        return deadRateDevLow;
    }

    /**
     *
     * Sets the infection rate for regions with low development
     * @param deadRateDevLow
     */
    public void setDeadRateDevLow(float deadRateDevLow) {
        this.deadRateDevLow = deadRateDevLow;
    }

    /**
     *
     * Updates the rules of simulation
     * @param changes
     */
    public void update(RuleChangeRequest changes) {
        this.setInfectionRateTempHigh(this.getInfectionRateTempHigh() + changes.getInfectionRateTempHigh());
        this.setInfectionRateTempMed(this.getInfectionRateTempMed() + changes.getInfectionRateTempMed());
        this.setInfectionRateTempLow(this.getInfectionRateTempLow() + changes.getInfectionRateTempLow());

        this.setInfectionRateDevHigh(this.getInfectionRateDevHigh() + changes.getInfectionRateDevHigh());
        this.setInfectionRateDevMed(this.getInfectionRateDevMed() + changes.getInfectionRateDevMed());
        this.setInfectionRateDevLow(this.getInfectionRateDevLow() + changes.getInfectionRateDevLow());

        this.setInfectionRateHumHigh(this.getInfectionRateHumHigh() + changes.getInfectionRateHumHigh());
        this.setInfectionRateHumMed(this.getInfectionRateHumMed() + changes.getInfectionRateHumMed());
        this.setInfectionRateHumLow(this.getInfectionRateHumLow() + changes.getInfectionRateHumLow());

        this.setInfectionRateUrbHigh(this.getInfectionRateUrbHigh() + changes.getInfectionRateUrbHigh());
        this.setInfectionRateUrbMed(this.getInfectionRateUrbMed() + changes.getInfectionRateUrbMed());
        this.setInfectionRateUrbLow(this.getInfectionRateUrbLow() + changes.getInfectionRateUrbLow());
    }

    /**
     *
     * Gets the guess rate
     * @return guessRate
     */
    public float getGuessRate() {
        return guessRate;
    }

    /**
     *
     * Sets the guess rate
     * @param guessRate
     */
    public void setGuessRate(float guessRate) {
        this.guessRate = guessRate;
    }

    /**
     *
     * Gets the guessed cells
     * @return guessedCells
     */
    public List<GameCell> getGuessedCells() {
        return guessedCells;
    }
    
    /**
     *
     * Guesses what cells are infected and updates guessedCells
     */
    public void guess(){
        Random rand = new Random();
        for(int i = 1; i < guessCount; ++i){
            int x = rand.nextInt(75);
            int y = rand.nextInt(166);
            if(pc[x][y] == null){
                --i;
            }
            else{
                if(pc[x][y].getSickCount() > guessRate*pc[x][y].getTotalCount()
                        && !guessedCells.contains(new GameCell(pc[x][y]))){
                    addToGuessedCellsList(new GameCell(pc[x][y]));
                }
            }
        }
        if(guessedCells.size() > 0)
        {
            if(guessCount < 100)
                guessCount *= 1.1;
            guessRate /= 0.99;
        }
    }
    
    /**
     *
     * Gets count of sick people
     * @return sickPCCount
     */
    public float getSickPCCount() {
        return sickPCCount;
    }

    /**
     *
     * Gets the array of POpulacion cells
     * @return pc population cell array
     */
    public PopulationCell[][] getPc() {
        return pc;
    }
}
