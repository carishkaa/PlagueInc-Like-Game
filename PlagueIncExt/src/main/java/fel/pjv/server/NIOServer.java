/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fel.pjv.client.NIOListener;
import fel.pjv.semestral_proj.help.Request;
import fel.pjv.semestral_proj.help.RuleChangeRequest;
import fel.pjv.semestral_proj.help.Request.RequestType;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Non-blocking I/O server
 */
public class NIOServer {
    private Selector selector;
    private InetSocketAddress listenAddress;
    private final int port;
    private List<SocketAddress> connectedClients;
    private Set<SelectionKey> readyKeys;
    private Map<String, SimulationRunner> simulationMap = 
            new TreeMap<String, SimulationRunner>();
   

    /**
     *
     * Creates new NIOServer instance
     * @param address
     * @param port
     * @throws java.io.IOException
     */    
    public NIOServer(String address, int port) throws IOException {
        this.port = port;
	listenAddress = new InetSocketAddress(address, port);
    }
    
    /**
     *
     * main method
     * @param args
     * @throws java.lang.Exception
     */  
    public static void main(String[] args) throws Exception {
		try {
			new NIOServer("localhost", 9083).startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     *
     * initializes the server
     * @throws IOException
     */  
    private void startServer() throws IOException {
                connectedClients = new ArrayList<SocketAddress>();
		this.selector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);

		// bind server socket channel to port
		serverChannel.socket().bind(listenAddress);
		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);

                Logger.getLogger(NIOServer.class.getName()).log(Level.INFO, "Server started on port >> " + port);

		while (true) {
			// wait for events
			int readyCount = selector.select();
			if (readyCount == 0) {
				continue;
			}

			// process selected keys...
			readyKeys = selector.selectedKeys();
			Iterator iterator = readyKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = (SelectionKey) iterator.next();

				// Remove key from set so we don't process it twice
				iterator.remove();

				if (!key.isValid()) {
					continue;
				}

				if (key.isAcceptable()) { // Accept client connections
					this.accept(key);
				} else if (key.isReadable()) { // Read from client
					this.read(key);
				} else if (key.isWritable()) {
					// write data to client...
				}
			}
		}
	}
    
    // accept client connection
    private void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
		SocketChannel channel = serverChannel.accept();
		channel.configureBlocking(false);
		Socket socket = channel.socket();
		SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                connectedClients.add(remoteAddr);
                Logger.getLogger(NIOServer.class.getName()).log(Level.INFO, "Connected to: " + remoteAddr);

		/*
		 * Register channel with selector for further IO (record it for read/write
		 * operations, here we have used read operation)
		 */
		channel.register(this.selector, SelectionKey.OP_READ);
	}
    
    // read from the socket channel
    private void read(SelectionKey key) throws IOException {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(7000000);
            int numRead = -1;
            try {
            numRead = channel.read(buffer);
            } catch (IOException e) {
                Socket socket = channel.socket();
                SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                Logger.getLogger(NIOServer.class.getName()).log(Level.INFO, 
                        "Client " + remoteAddr + " is no longer responding. Closing connection.");
                connectedClients.remove(remoteAddr);
                key.cancel();
                channel.close();
                return;
            }
            

            if (numRead == -1) {
                Socket socket = channel.socket();
                SocketAddress remoteAddr = socket.getRemoteSocketAddress();
                Logger.getLogger(NIOServer.class.getName()).log(Level.INFO, 
                        "Connection closed by client: " + remoteAddr);
                connectedClients.remove(remoteAddr);
                channel.close();
                key.cancel();
                return;
            }

            byte[] data = new byte[numRead];
            System.arraycopy(buffer.array(), 0, data, 0, numRead);
            Logger.getLogger(NIOServer.class.getName()).log(Level.INFO,
                        "Got: " + new String(data));
            
            parseRequest(data, key);
        }
    //parse the income request
    private void parseRequest(byte[] data, SelectionKey key) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = objectMapper.readValue(data, Request.class);
        Request answer = null;
        
        if(null != request.requestType)switch (request.requestType) {
            case GETPLAYERS:
                request.SenderIP = ((SocketChannel)key.channel()).socket().getRemoteSocketAddress().toString().substring(1);
                String clientsBytes = objectMapper.writeValueAsString(connectedClients);
                answer = new Request(null, request.SenderIP, RequestType.GETPLAYERS, clientsBytes);
                write(key, answer);
                break;
            case NEWGAME:
                answer = request;
                request.SenderIP = ((SocketChannel)key.channel()).socket().getRemoteSocketAddress().toString().substring(1);
                SelectionKey opponentKey = getOpponentKey(request);
                if(opponentKey != null)
                    write(opponentKey, answer);
                break;
            case STARTPOINT:{
                int startPoint = objectMapper.readValue(request.data, Integer.class);
                Simulation simulation = new Simulation();
                SimulationRunner runner = new SimulationRunner(simulation, startPoint/1000, startPoint%1000);
                runner.setServer(this);
                runner.setPlayer1(key);
                
                SelectionKey secondKey = getOpponentKey(request);
                runner.setPlayer2(secondKey);
                simulationMap.put(request.ReceiverIP, runner);
                simulationMap.put(request.SenderIP, runner);
                runner.setDaemon(true);
                runner.start();
                    break;
                }
            case RULECHANGE:{
                RuleChangeRequest changes;
                ObjectMapper objMapper = new ObjectMapper();
                changes = objMapper.readValue(request.data, RuleChangeRequest.class);
                Simulation simulation = simulationMap.get(request.SenderIP).getSimulation();
                simulation.update(changes);
                    break;
                }
            case ENDGAME:{
                SimulationRunner runner = simulationMap.get(request.SenderIP);
                runner.stopSimulation(request.SenderIP);
                    break;
                }
            case CONFIRM:
                break;   
            default:
                break;
        }
    }
    

    /**
     *
     * writes message to client
     * @param key     where to write
     * @param request what to write
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */     
    public void write(SelectionKey key, Request request) throws JsonProcessingException, IOException{
        key.interestOps(SelectionKey.OP_WRITE);
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] data = objectMapper.writeValueAsBytes(request);
        
        SocketChannel channel = (SocketChannel) key.channel();
        channel.write(ByteBuffer.wrap(data));
        key.interestOps(SelectionKey.OP_READ);
        Logger.getLogger(NIOServer.class.getName()).log(Level.INFO, 
                        "Sent: " + new String(data));
    }
    
    private SelectionKey getOpponentKey(Request request){
        readyKeys = selector.keys();
        Iterator iteratorFind = readyKeys.iterator();
        SelectionKey keySearch = null;
        while (iteratorFind.hasNext()) {
            keySearch = (SelectionKey) iteratorFind.next();

            if (!keySearch.isValid()) {
                continue;
            }
            try{
                SocketChannel channelSearch = (SocketChannel) keySearch.channel();
                Socket socketSearch = channelSearch.socket();
                String remoteAddrSearch = socketSearch.getRemoteSocketAddress().toString().substring(1);

                if(remoteAddrSearch.equals(request.ReceiverIP)){
                    return keySearch;
                }
            }
                catch(Exception ex){
            }
        }
        return keySearch;
    }
}
