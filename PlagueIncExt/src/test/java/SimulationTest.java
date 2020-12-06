/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */

import fel.pjv.server.Simulation;
import org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
public class SimulationTest {
    @Test(expected = Test.None.class)
    public void nullStartPointTest() {
        Simulation simulation = new Simulation();
        simulation.start(0, 0); //null population cell on this coordinate
    }
    
    @Test
    public void notInfectingTest(){
        Simulation simulation = new Simulation();
        simulation.start(16, 16);
        simulation.iterate();
        while(simulation.getPc()[16][16].getSickCount() < 
                simulation.getPc()[16][16].getTotalCount()/3){
            assertTrue("Cell is not enough infected to start infecting other cells",
                    simulation.getPc()[16][16].getSickCount()
                            == simulation.getSickPCCount());
            simulation.iterate();
        }
    }
    
    @Test
    public void InfectingTest(){
        Simulation simulation = new Simulation();
        simulation.getPc()[17][16].setSickCount(400);
        simulation.start(16, 16);
        simulation.iterate();
        assertTrue("Cell should have started infecting",
                    simulation.getPc()[16][16].getSickCount() + 1
                            < simulation.getSickPCCount());
    }
}
