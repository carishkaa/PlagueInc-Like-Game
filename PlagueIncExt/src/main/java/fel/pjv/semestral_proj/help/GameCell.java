/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.semestral_proj.help;

import fel.pjv.server.PopulationCell;

/**
 *
 * A class to represent a Game Cell
 */
public class GameCell {
    public int x, y;
    public float totalCount;
    public float sickCount;
    public float deadCount;

    /**
     *
     * An empty constructor
     */    
    public GameCell() {}

    /**
     *
     * A constructor to create  Game Cell from Population Cell
     * @param cell a Population Cell to create a Game Cell from
     */      
    public GameCell(PopulationCell cell) {
        if(cell != null){
            this.x = cell.getX();
            this.y = cell.getY();
            this.totalCount = cell.getTotalCount();
            this.sickCount = cell.getSickCount();
            this.deadCount = cell.getDeadCount();
        }
        else x = -1;
    }

    /**
     *
     * Method returns if an object equals to the cell
     * @param o an object to compare
     * @return true if equals
     */     
    @Override
     public boolean equals(Object o) { 
         if (o == this) { 
            return true; 
        } 
        if (!(o instanceof GameCell)) { 
            return false; 
        } 
        
        GameCell c = (GameCell) o; 
        return x == c.x && y == c.y;
     }

    /**
     *
     * Return hash code of a cell
     * @return hash code
     */      
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
