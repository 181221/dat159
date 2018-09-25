/**
 * 
 */
package no.pederyo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tosindo
 *
 */
public class Server implements IParent {
	/**
	   * Main Method
	   * 
	   * @param args
	   */
	  public static void main(String args[])
	  {
		  Server server = new Server();
		  server.setup();
		  // Wait for requests
		  while (true) {
			  server.receiveAndSend();
		  }
	  }
	  
	private void setup(){
		
	}
	
	public void receiveAndSend() {
		ServerSocket server;
	    Socket client;
	    ObjectOutputStream oos;
	    ObjectInputStream ois;
	    
	    try{
	    	server = new ServerSocket(PORT);
	    	System.out.println("Waiting for requests from client...");
	    	client = server.accept();
	    	System.out.println("Connected to client at the address: "+client.getInetAddress());
	    	
	    	oos = new ObjectOutputStream(client.getOutputStream());
	        ois = new ObjectInputStream(client.getInputStream());

	        // Receive message from the client
	        byte[] clientMsg = (byte[]) ois.readObject();
	        
	        // Print the message in UTF-8 format
	        System.out.println("Message from Client: "+ new String(clientMsg, "UTF-8"));
	        
	        // Create a response to client if message received
	        String response = "No message received";
	        
	        if(ois != null){
	        	response = "Message received from client";
	        	
	        	// Send the plaintext response message to the client
	            oos.writeObject(response.getBytes());
	            oos.flush();
	        }
	        
	        // Close Client and Server sockets
	        client.close();
	        server.close();
	        oos.close();
	        ois.close();
	        
	    }catch(IOException | ClassNotFoundException e){
	    	e.printStackTrace();
	    }
		
	}

	@Override
	public byte[] encryptMessage(byte[] plaintext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] decryptMessage(byte[] ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}

}
