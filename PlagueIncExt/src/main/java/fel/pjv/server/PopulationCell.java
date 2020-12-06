/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.server;

/**
 *
 * Represents a population cell
 */
public class PopulationCell {

    private final int x, y;
    private float totalCount = 1000;
    private float sickCount = 0;
    private float deadCount = 0;
    private CellProperty temperature;
    private CellProperty humidity;
    private CellProperty urbanization;
    private CellProperty development;
    private Boolean isAirport = false;
    private Boolean isPort = false;

    /**
     *
     * Possible values of properties
     */    
    public enum CellProperty {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     *
     * Constructor setting the coordinates of cell
     * @param x
     * @param y
     */ 
    public PopulationCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * Constructor setting the coordinates of cell and properties
     * @param x
     * @param y
     * @param temperature
     * @param humidity
     * @param urbanisation
     * @param development
     */ 
    public PopulationCell(int x, int y, CellProperty temperature,
            CellProperty humidity, CellProperty urbanisation,
            CellProperty development) {
        this.x = x;
        this.y = y;
        this.temperature = temperature;
        this.humidity = humidity;
        this.urbanization = urbanisation;
        this.development = development;
        this.isAirport = false;
        this.isPort = false;
        this.totalCount = 500;
        this.sickCount = 0;
        this.deadCount = 0;
    }

    /**
     *
     * Constructor setting the coordinates of cell, properties 
     * and airports/ports
     * @param x
     * @param y
     * @param temperature
     * @param humidity
     * @param urbanisation
     * @param development
     * @param air
     * @param water
     */     
    public PopulationCell(int x, int y, CellProperty temperature,
            CellProperty humidity, CellProperty urbanisation,
            CellProperty development, Boolean air, Boolean water) {
        this.x = x;
        this.y = y;
        this.temperature = temperature;
        this.humidity = humidity;
        this.urbanization = urbanisation;
        this.development = development;
        this.isAirport = air;
        this.isPort = water;
        this.totalCount = 500;
        this.sickCount = 0;
        this.deadCount = 0;
    }

    /**
     *
     * Gets X coordinate
     * @return x
     */ 
    public int getX() {
        return x;
    }

    /**
     *
     * Gets Y coordinate
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     *
     * Gets count of infected people
     * @return sickCount
     */
    public float getSickCount() {
        return sickCount;
    }

    /**
     *
     * Sets count of infected people
     * @param sickCount
     */
    public void setSickCount(float sickCount) {
        this.sickCount = sickCount;
    }

    /**
     *
     * Gets count of dead people
     * @return deadCount
     */
    public float getDeadCount() {
        return deadCount;
    }

    /**
     *
     * Sets count of dead people
     * @param deadCount
     */
    public void setDeadCount(float deadCount) {
        this.deadCount = deadCount;
    }

    /**
     *
     * Gets count of people coordinate
     * @return totalCount
     */
    public float getTotalCount() {
        return totalCount;
    }

    /**
     *
     * Sets count of people coordinate
     * @param totalCount
     */
    public void setTotalCount(float totalCount) {
        this.totalCount = totalCount;
    }

    /**
     *
     * Gets temperature property
     * @return temperature
     */
    public CellProperty getTemperature() {
        return temperature;
    }

    /**
     *
     * Sets temperature level
     * @param temperature
     */
    public void setTemperature(CellProperty temperature) {
        this.temperature = temperature;
    }

    /**
     *
     * Gets humidity property
     * @return temperature
     */
    public CellProperty getHumidity() {
        return humidity;
    }

    /**
     *
     * Sets humidity level
     * @param humidity
     */
    public void setHumidity(CellProperty humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * Gets urbanization property
     * @return urbanization
     */
    public CellProperty getUrbanisation() {
        return urbanization;
    }

    /**
     *
     * Sets urbanization level
     * @param urbanization
     */
    public void setUrbanisation(CellProperty urbanization) {
        this.urbanization = urbanization;
    }

    /**
     *
     * Gets development property
     * @return development
     */
    public CellProperty getDevelopment() {
        return development;
    }

    public void setDevelopment(CellProperty development) {
        this.development = development;
    }

    /**
     *
     * Gets airport property
     * @return isAirport
     */    public boolean isAirport() {
        return isAirport;
    }

    /**
     *
     * Gets port property
     * @return isPort
     */
     public boolean isPort() {
        return isPort;
    }
    

    /**
     *
     * Decides of object equals this cell based on coordinates
     * @return equals
     */
    @Override
     public boolean equals(Object o) { 
         if (o == this) { 
            return true; 
        } 
        if (!(o instanceof PopulationCell)) { 
            return false; 
        } 
        
        PopulationCell c = (PopulationCell) o; 
        return x == c.x && y == c.y;
     }
     
     
    /**
     *
     * Decides of object equals this cell based on all parameters
     * @return equals
     */
     public boolean equalsAll(Object o) { 
         if (o == this) { 
            return true; 
        } 
        if (!(o instanceof PopulationCell)) { 
            return false; 
        } 
        
        PopulationCell c = (PopulationCell) o; 
        return x == c.x && y == c.y && sickCount == c.sickCount 
                && deadCount == c.deadCount && totalCount == c.totalCount;
     }

    /**
     *
     * Computes hash code
     * @return hashCode
     */     
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
        
}
