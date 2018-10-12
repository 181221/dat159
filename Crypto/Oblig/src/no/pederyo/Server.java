/**
 * 
 */
package no.pederyo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static no.pederyo.Utility.bytesToString;

/**
 * @author tosindo
 *
 */
public class Server {
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
	    DES des;
	    
	    try{
	    	server = new ServerSocket(9090);
	    	System.out.println("Waiting for requests from client...");
	    	client = server.accept();
	    	System.out.println("Connected to client at the address: "+client.getInetAddress());
	    	
	    	oos = new ObjectOutputStream(client.getOutputStream());
	        ois = new ObjectInputStream(client.getInputStream());
			des = new DES();
	        // Receive message from the client
	        byte[] clientMsg = (byte[]) ois.readObject();
			byte[] clientMsgDecrypted = des.decryptDes(clientMsg);

            System.out.println(Utility.hexToAscii(Utility.bytesToString(clientMsgDecrypted)));
	        // Print the message in UTF-8 format
	        //System.out.println("Message from Client: "+ new String(clientMsg, "UTF-8"));
	        
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
	    } catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

    }

}
