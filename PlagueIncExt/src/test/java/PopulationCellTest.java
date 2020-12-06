/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */

import fel.pjv.server.PopulationCell;
import fel.pjv.server.PopulationCell.CellProperty;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class PopulationCellTest {
    @Test
    public void equalsTest1() {
        PopulationCell cell1 = new PopulationCell(0,0, CellProperty.LOW,
                    CellProperty.LOW, CellProperty.LOW, CellProperty.LOW);
        
        cell1.setDeadCount(10);
        
        PopulationCell cell2 = new PopulationCell(0,0, CellProperty.HIGH,
                    CellProperty.HIGH, CellProperty.HIGH, CellProperty.HIGH);
        
        assertTrue("Cells should be equal.", cell1.equals(cell2));
    }  
    
    @Test
    public void equalsALLTest() {
        PopulationCell cell1 = new PopulationCell(0,0, CellProperty.LOW,
                    CellProperty.LOW, CellProperty.LOW, CellProperty.LOW);
        
        cell1.setDeadCount(10);
        
        PopulationCell cell2 = new PopulationCell(0,0, CellProperty.HIGH,
                    CellProperty.HIGH, CellProperty.HIGH, CellProperty.HIGH);
        
        assertTrue("Cells shouldn't be equalAll.", !cell1.equalsAll(cell2));
    } 
}
