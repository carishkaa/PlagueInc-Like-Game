/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */

import fel.pjv.semestral_proj.help.GameCell;
import fel.pjv.server.PopulationCell;
import fel.pjv.server.PopulationCell.CellProperty;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
public class GameCellTest {
    @Test
    public void equalsTest1() {
        GameCell cell1 = new GameCell(new PopulationCell(0,0, CellProperty.LOW,
                    CellProperty.LOW, CellProperty.LOW, CellProperty.LOW));
        
        GameCell cell2 = new GameCell(new PopulationCell(0,0, CellProperty.HIGH,
                    CellProperty.HIGH, CellProperty.HIGH, CellProperty.HIGH));
        
        assertTrue("Cells should be equal.", cell1.equals(cell2));
    }  
    
    @Test
    public void equalsTest2() {
        GameCell cell1 = new GameCell(new PopulationCell(0,0, CellProperty.LOW,
                    CellProperty.LOW, CellProperty.LOW, CellProperty.LOW));
        
        PopulationCell cell2 = new PopulationCell(0,0, CellProperty.LOW,
                    CellProperty.LOW, CellProperty.LOW, CellProperty.LOW);
        
        assertTrue("Cells shouldn't be equal.", !cell1.equals(cell2));
    }
}
