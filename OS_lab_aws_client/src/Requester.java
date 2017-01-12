
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Requester 
{
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message = "";
	String userinput;
	String ipaddress;
	int choice,userinput2;
	Scanner stdin;

	Requester() {
	}

	//Methods--------------------------------------------------------------------------------------------------------------------------------
	void run() 
	{
		stdin = new Scanner(System.in);
		try 
		{
			// 1. creating a socket to connect to the server
			System.out.println("Please Enter your IP Address: ");
			ipaddress = stdin.next();//user enters ip
			//ipaddress = "127.0.0.1";//"35.166.51.207"
			requestSocket = new Socket(ipaddress, 2004);
			System.out.println("Connected to " + ipaddress + " in port 2004");
			
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//System.out.println("Hello");
			
			// 3: Communicating with the server
			do 
			{
				try 
				{
					
					//read message from server
					message = (String) in.readObject();
					System.out.println(message);
					//send message to server
					message = stdin.next();
					sendMessage(message);
					/*if (message == "Try again")
						sendMessage("Continue");
					if (message == "Account updated")
						sendMessage("Continue");*/
					
				} 
				catch (ClassNotFoundException classNot) 
				{
					System.err.println("data received in unknown format");
				}
			} while (!message.equals("bye"));
		} 
		catch (UnknownHostException unknownHost) 
		{
			System.err.println("You are trying to connect to an unknown host!");
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		} 
		finally 
		{	
			// 4: Closing connection
			try 
			{
				in.close();
				out.close();
				requestSocket.close();
			} 
			catch (IOException ioException) 
			{
				ioException.printStackTrace();
			}
		}
	}

	
	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	//Main method
	public static void main(String args[]) {
		Requester client = new Requester();
		client.run();
	}
}

