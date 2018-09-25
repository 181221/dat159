/**
 * 
 */
package no.pederyo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author tosindo
 *
 */
public class Client implements IParent {
	
	public static void main(String[] args){
		Client client = new Client();
		client.sendAndReceice();
	}
	
	public void sendAndReceice() {
		Socket client;
	    ObjectOutputStream oos;
	    ObjectInputStream ois;
	    
	    try {
	    	client = new Socket("localhost",PORT);
			
	    	System.out.println("Connected to Server on "+ client.getInetAddress());

	    	oos = new ObjectOutputStream(client.getOutputStream());
	    	ois = new ObjectInputStream(client.getInputStream());
	    	
	    	// send a plaintext message to server
	    	String plaintxt = "Hello from client";
	    	
	    	// send message to server
	    	oos.writeObject(plaintxt.getBytes());
	    	oos.flush();
	    	
	    	// receive response from server
	    	byte[] response = (byte[]) ois.readObject();
	    	
	    	System.out.println("Response from server: "+ new String(response, "ASCII"));
	    	
	    	// close cliet socket
	    	client.close();
	    	ois.close();
	    	oos.close();
	    	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public byte[] encryptMessage(byte[] plaintext) {
		
		return null;
	}

	@Override
	public byte[] decryptMessage(byte[] ciphertext) {
		
		return null;
	}

}
