import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServer {
	// Main Method--------------------------------------------------------------------------------------
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket m_ServerSocket = new ServerSocket(2004, 10);// new socket
		int id = 0;
		while (true) {
			Socket clientSocket = m_ServerSocket.accept();
			ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
			cliThread.start();
		}
	}
}
//Inner class
class ClientServiceThread extends Thread 
{
	//Variables-------------------------------------------------------------------------------------------
	Socket clientSocket;
	String message, message2;
	int clientID = -1;
	int choice,choice2;
	boolean running = true;
	ObjectOutputStream out;
	ObjectInputStream in;
	Account temp;
	Boolean flag=false;
	
	ArrayList<Account> list = new ArrayList<Account>();
	
	//Constructor---------------------------------------------------------------------------------------
 	ClientServiceThread(Socket s, int i) 
	{
		clientSocket = s;
		clientID = i;
	}

	//Methods--------------------------------------------------------------------------------------------
	void sendMessage(String msg) 
	{
		try 
		{
			out.writeObject(msg);
			out.flush();
			System.out.println("server> " + msg);
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		}
	}

	public void run() 
	{
		//System.out.println("Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		try 
		{
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());

			do 
			{
				try 
				{
					sendMessage("Please enter 1 for new user ,2 for returning user or bye to close");	
					message = (String)in.readObject();
					choice = new Integer(message);
					
					if(choice==1)
					{
						sendMessage("Please enter Name");
						message = (String)in.readObject();
						String name = message;
						
						sendMessage("Please enter Address");
						message = (String)in.readObject();
						String address = message;
						
						sendMessage("Please enter A/C number");
						message = (String)in.readObject();
						int aCnum = new Integer(message);
						
						sendMessage("Please enter Username");
						message = (String)in.readObject();
						String username = message;
						
						sendMessage("Please enter Password");
						message = (String)in.readObject();
						String password = message;
						
						int balance=0;
						
						list.add(new Account(name, address, aCnum, username, password, balance));
						
						sendMessage("Account: "+list);
					}	
					else if(choice==2)
					{
							sendMessage("Please enter Username");
							message = (String)in.readObject();
							System.out.println(message);
							
							sendMessage("Please enter Password");
							message2 = (String)in.readObject();
							System.out.println(message2);
							
						for(int i=0; i<list.size();i++)
						{
							if(list.get(i).getUsername().equals(message) && list.get(i).getPassword().equals(message2))
							{
								temp=list.get(i);
								flag=true;
								sendMessage("Welcome " + temp.getName() + "\nAccount details " + temp);
							}
							if(list.get(i).getUsername()!=(message) && list.get(i).getPassword()!=(message2))
							{
								sendMessage("Authentication un-succesful");
							}
							else
							{
								sendMessage("not working");
							}
						}
						if(flag==true)
						{
							do
							{
								//second menu for logged-in users
								sendMessage("Please enter 1 for new details , 3 return to main menu");
								message=(String)in.readObject();
								choice2 = new Integer(message);
								
								if(choice2==1)
								{	
									
									sendMessage("Please enter Name");
									message = (String)in.readObject();
									String name = message;
									
									sendMessage("Please enter Address");
									message = (String)in.readObject();
									String address = message;
									
									sendMessage("Please enter A/C number");
									message = (String)in.readObject();
									int aCnum = new Integer(message);
									
									sendMessage("Please enter Username");
									message = (String)in.readObject();
									String username = message;
									
									sendMessage("Please enter Password");
									message = (String)in.readObject();
									String password = message;
									
									for(int i=0;i<list.size();i++)
									{
										if(list.get(i).equals(temp.getACnum()))
										{
											list.get(i).setName(name);
											list.get(i).setAddress(address);
											list.get(i).setACnum(aCnum);
											list.get(i).setUsername(username);
											list.get(i).setPassword(password);
											System.out.println(temp=list.get(i));
										}
									}
									choice2=0;
									sendMessage("Account updated"+list);
								
								}
								if(choice2==3)
								{
									flag=false;
								}
								
							}while(flag==true);
							System.out.println("\n\nafter while flag not true");
						}
						System.out.println("\n\nafter choice2 condition");
					}
					System.out.println("\n\nafter choice condition");
					
				}	
				
				catch (ClassNotFoundException classnot) 
				{
					System.err.println("Data received in unknown format");
				}

			} while (!message.equals("bye"));
			System.out.println(
					"Ending Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

/*else if(choice==2)




//reads user response (menu2)
message = (String) in.readObject();
System.out.println(message);


if (choice==5) 
{

	
	message=(String)in.readObject();
	
	if(message.compareTo("bye")==0)
		sendMessage("bye");
}*/

/*sendMessage("Please enter 1 for new user OR 2 for returning user");
message = (String)in.readObject();
choice = new Integer(message);
if(choice==1)
{
	sendMessage("Please enter Name");
	message = (String)in.readObject();
	name = message;
	
	sendMessage("Please enter address");
	message = (String)in.readObject();
	address = message;
	
	sendMessage("Please enter A/C number");
	message = (String)in.readObject();
	aCnum = new Integer(message);
	
	sendMessage("Please enter Username");
	message = (String)in.readObject();
	username = message;
	
	
	sendMessage("Please enter Password");
	message = (String)in.readObject();
	password = message;
	
	account = new Accessors(name, address, aCnum, username, password, credit);
	
	list.add(account);
	
	sendMessage("Account details: " + account.toString());
	
	
}
if (choice==2)
{
	
	message = (String)in.readObject();
	tempACno = new Integer(message);
	//message="bye";
	for(int i=0; i<list.size();i++)
	{	
		if(list.get(i).getACnum()==tempACno)
		{
			sendMessage(list.get(i).toString());
		}	
	}
	//sendMessage(message);
}*/

/*//1. Send message to client and read it back
sendMessage("Please enter 1 for new user");
//read message from user 
message = (String)in.readObject();
System.out.println(message);*/