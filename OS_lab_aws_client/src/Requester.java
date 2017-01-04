
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Requester 
{
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Account account;
	String message = "";
	String userinput;
	String ipaddress;
	int choice,userinput2;
	Scanner stdin;

	Requester() {
	}

	//Methds--------------------------------------------------------------------------------------------------------------------------------
	void run() 
	{
		stdin = new Scanner(System.in);
		try 
		{
			// 1. creating a socket to connect to the server
			//System.out.println("Please Enter your IP Address: ");
			//ipaddress = stdin.next();//user enters ip
			ipaddress = "127.0.0.1";
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
					//read message from server (options)
					message = (String) in.readObject();
					System.out.println(message);
					//send message to server
					message = stdin.next();
					sendMessage(message);

					if(message=="1")
					{
						//read message from server (Name)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
	
						//read message from server (Address)
						message = (String) in.readObject();
						System.out.println(message);			
						//send message to server
						message = stdin.next();
						sendMessage(message);
	
						//read message from server  (Ac no)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
	
						//read message from server  (Username)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
	
						//read message from server  (Password)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
	
						//read message from server (Confirmation)
						message = (String) in.readObject();
						System.out.println(message);
						
						System.out.println("Would you like to return to main menu.... Enter yes or no");
						
						message = stdin.next();
						if(message.compareToIgnoreCase("no")==0)
						{
							sendMessage("bye");
							message = (String)in.readObject();
							System.out.println(message);
						}
						else
						{
							sendMessage("Continue");
						}
					}
					if(message=="2")
					{
						//read message from server (Username)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//read message from server (Password)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//message about (authentication)
						message = (String) in.readObject();
						System.out.println(message);
						
						//read message from server (menu2 choice)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						/**/
					}
					if(message=="3")
					{
						//read message from server (update name)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//read message from server (address)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//read message from server (a/c no)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//read message from server (username)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//read message from server (password)
						message = (String) in.readObject();
						System.out.println(message);
						//send message to server
						message = stdin.next();
						sendMessage(message);
						
						//read message from server (results)
						message = (String) in.readObject();
						System.out.println(message);
						
						/*System.out.println("Would you like to continue or.... Enter yes or no");
						
						message = stdin.next();
						if(message.compareToIgnoreCase("no")==0)
						{
							sendMessage("bye");
							message = (String)in.readObject();
						}
						else if(message.compareToIgnoreCase("yes")==0)
						{
							sendMessage("Continue");
						}*/
					}
					
				}
					
					/*else if(message=="2")
					{
						
						
					if(message.contains("Authentication succesful"))
						{
							
							System.out.println("Welcome please choose another option"
										+ "\n1 for change details,"
										+ "\n2 for Lodgement,"
										+ "\n3 for Withdrawel,"
										+ "\n4 for View last ten transactions,"
										+ "\n5 to exit");
						}
						
							message = stdin.next();
							sendMessage(message);
								
							if(message.compareTo("1")==0){System.out.println("1");message="5";}
							if(message.compareTo("2")==0){System.out.println("2");message="5";}
							if(message.compareTo("3")==0){System.out.println("3");message="5";}
							if(message.compareTo("4")==0){System.out.println("4");message="5";}
							if(message.compareTo("5")==0)
							{
								sendMessage("bye");
								message = (String)in.readObject();
								System.out.println(message);
							}
								
						
							else
							{
								System.out.println("sorry returning to main menu try again");
							}
					}
						
					else if(message.compareTo("5")==0)
					{
						sendMessage("bye");
						
						message = (String)in.readObject();
						System.out.println(message);
					}*/
				
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

/*void run() 
{
	stdin = new Scanner(System.in);
	try 
	{
		// 1. creating a socket to connect to the server
		//System.out.println("Please Enter your IP Address: ");
		//ipaddress = stdin.next();//user enters ip
		ipaddress = "127.0.0.1";
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
				message = (String)in.readObject();
				System.out.println(message);
				//user input
				message=stdin.next();
				choice=new Integer(message);
				if(choice==1)
				{
					addAccount();
				}
				
				//send message back to server
				message=stdin.next();
				sendMessage(message);

				
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

void addAccount(){
	
	Account account = new Account();
	
	System.out.println("Enter name");
	userinput=stdin.next();
	account.setName(userinput);
	//need to figure out how to enter more than on address maybe split the address up into multiple lines 
	System.out.println("Enter address");
	userinput=stdin.next();
	account.setAddress(userinput);
	
	System.out.println("Enter an account number");
	userinput2=stdin.nextInt();
	account.setACnum(userinput2);
	
	System.out.println("Enter username");
	userinput=stdin.next();
	account.setUsername(userinput);
	
	System.out.println("Enter password");
	userinput=stdin.next();
	account.setPassword(userinput);
	
	//System.out.println(account.toString());//whats in acount object here
	
	sendMessage(account.toString());
}*/

