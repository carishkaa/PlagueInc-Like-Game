/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fel.pjv.client.NIOListener;
import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.Request.RequestType;
import java.io.IOException;
import java.net.Socket;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Runs the simulation in own thread
 */
public class SimulationRunner extends Thread{
    private volatile Simulation simulation;
    private int startX;
    private int startY;
    private NIOServer server;
    private SelectionKey player1; //pandemia
    private SelectionKey player2; //humanity
    private Boolean stop = false;

    /**
     *
     * Sets first player - always pandemia
     * @param player1
     */
    public void setPlayer1(SelectionKey player1) {
        this.player1 = player1;
    }

    /**
     *
     * Sets second player - always humanity
     * @param player2
     */
    public void setPlayer2(SelectionKey player2) {
        this.player2 = player2;
    }
    
    /**
     *
     * Gets the running simulation
     * @return simulation
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     *
     * Sets the server where the simulation runs
     * @param server
     */
    public void setServer(NIOServer server) {
        this.server = server;
    }

    /**
     *
     * Simulation runner constructor
     * @param simulation
     * @param startX
     * @param startY
     */
    public SimulationRunner(Simulation simulation, int startX, int startY) {
        this.simulation = simulation;
        this.startX = startX;
        this.startY = startY;
    }
    
    /**
     *
     * Stops the simulation
     * @param senderIP who stopped the simulation
     * @throws java.io.IOException
     */
    public void stopSimulation(String senderIP) throws IOException{
        SocketChannel channel1 = (SocketChannel) player1.channel();
        Socket socket1 = channel1.socket();
        String remoteAddrPlayer1 = socket1.getRemoteSocketAddress().toString().substring(1);
        
        Request request = new Request();
        request.requestType = RequestType.ENDGAME;
        
        if (senderIP.equals(remoteAddrPlayer1)){
            server.write(player2, request);
        }
        else {
            server.write(player1, request);
        }
        
        this.interrupt();
    }

    /**
     *
     * Runs the simulation
     */    
    @Override
    public void run()
    {
        Request mapUpdate = new Request();
        mapUpdate.requestType = RequestType.MAPUPDATE;
        ObjectMapper objMapper = new ObjectMapper();
        
        Request endGameWinner = new Request();
        endGameWinner.requestType = RequestType.ENDGAMEWINNER;
        simulation.start(startX, startY);
        while (!stop) {
            try {
                Logger.getLogger(SimulationRunner.class.getName()).log(Level.INFO, "Run");
                simulation.iterate();
                if (simulation.isEverybodyDied() || simulation.isEverybodyHealth()) 
                    stop = true;
                try {
                    //send pandemia the illness map
                    //pause = true;
                    mapUpdate.data = objMapper.writeValueAsString(simulation.getChangedCells());
                    mapUpdate.ReceiverIP = ((SocketChannel)player1.channel()).socket().getRemoteSocketAddress().toString().substring(1);
                    server.write(player1, mapUpdate);
                    //while(pause);
                    
                    simulation.guess();
                    //send humanity 
                    mapUpdate.data = objMapper.writeValueAsString(simulation.getGuessedCells());
                    //pause = true;
                    mapUpdate.ReceiverIP = ((SocketChannel)player2.channel()).socket().getRemoteSocketAddress().toString().substring(1);
                    server.write(player2, mapUpdate);
                    //while(pause);
                } catch (Exception ex) {
                    Logger.getLogger(SimulationRunner.class.getName()).log(Level.SEVERE, null, ex);
                    stop = true;
                }
                Thread.sleep(1500);
            } catch (Exception ex) {
                Logger.getLogger(SimulationRunner.class.getName()).log(Level.SEVERE, null, ex);
                stop = true;
            }
        }
        
        try {
            // pandemia
            if (simulation.isEverybodyDied()){
                endGameWinner.data = objMapper.writeValueAsString("Pandemia killed humanity! You are the winner!");
                server.write(player1, endGameWinner);
                endGameWinner.data = objMapper.writeValueAsString("Pandemia killed humanity! You lose!");
                server.write(player2, endGameWinner);
            } else 
            // humanity
            if (simulation.isEverybodyHealth()){
                endGameWinner.data = objMapper.writeValueAsString("Humanity is healthy! You lose!");
                server.write(player1, endGameWinner);
                endGameWinner.data = objMapper.writeValueAsString("Humanity is healthy! You are the winner!");
                server.write(player2, endGameWinner);
            }
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SimulationRunner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimulationRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
