import java.awt.List;
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
import java.util.Random;

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
	String message, message2, tempString;
	int clientID = -1;
	int choice,choice2, withdrawchoice, depositChoice;
	int temp;
	boolean running = true;
	boolean flag=false;
	ObjectOutputStream out;
	ObjectInputStream in;
	Random rand = new Random();
	ArrayList<Account> list = new ArrayList<Account>();
	
	//Constructor---------------------------------------------------------------------------------------
 	ClientServiceThread(Socket s, int i) 
	{
		clientSocket = s;
		clientID = i;
	}

	//Methods--------------------------------------------------------------------------------------------
 	/*Method used for message passing*/
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
	
	/*Method used for the choices that user send to the server after server sends first message to client*/
	public void UI() throws ClassNotFoundException, IOException{
		
		if(choice==1)
		{
			addUser();
		}
		else if(choice==2)
		{
			verifyUser();
		}
		else if(choice==3)
		{
			Deposit();
		}
		else if(choice==4)
		{
			Withdraw();
		}
	}
	
	/*Method used for adding users*/
	public void addUser() throws ClassNotFoundException, IOException {
		
		sendMessage("Please enter Name");
		message = (String)in.readObject();
		String name = message;
		
		sendMessage("Please enter Address");
		message = (String)in.readObject();
		String address = message;
		
		int aCnum=rand.nextInt(50) + 1;
		for(Account a:list){
			while(a.getACnum()==aCnum)
			{
				aCnum=rand.nextInt(50) + 1;
			}
			break;
		}
		
		sendMessage("Please enter Username");
		message = (String)in.readObject();
		String username = message;
		
		sendMessage("Please enter Password");
		message = (String)in.readObject();
		String password = message;
		
		int balance=0;
		
		list.add(new Account(name, address, aCnum, username, password, balance));
		
		System.out.println("\n\n"+list);
	}
	
	/*Method used for authenticating users*/
	public void verifyUser() throws ClassNotFoundException, IOException {
		
		sendMessage("Please enter Username");
		message = (String)in.readObject();
		System.out.println(message);
			
		sendMessage("Please enter Password");
		message2 = (String)in.readObject();
		System.out.println(message2);
			
		for(Account a:list)
		{
			if(a.getUsername().equals(message) && a.getPassword().equals(message2))
			{
				tempString=a.getName();
				temp = a.getACnum();
				//tempAC = a.getACnum();
				flag=true;
				break;
			}
			else if(a.getUsername()!=(message) && a.getPassword()!=(message2))
			{
				System.out.println("Try again");
				//sendMessage("Try again"); Cant get this working right as it messes up the program
				break;
			}
		}
	}
	
	/*Method used for sending a menu and tasks for logged in users*/
	public void UILoggedin() throws ClassNotFoundException, IOException {
		
			//second menu for logged-in users
			sendMessage("\nWelcome "+tempString+"\n\nPlease enter 1 for new details , 2 return to main menu, 3 to withdraw, 4 to deposit");
			message=(String)in.readObject();
			choice2 = new Integer(message);
			
			if(choice2==1)
			{
				updateUser();
			}
			else if(choice2==2){
				flag=false;
			}
			else if(choice2==3)
			{
				Withdraw();
			}
			else if(choice2==4)
			{
				Deposit();
			}
	}
	
	/*Method used for updating users details*/
	public void updateUser() throws ClassNotFoundException, IOException{
		
		sendMessage("Please enter Name");
		message = (String)in.readObject();
		String name = message;
		
		sendMessage("Please enter Address");
		message = (String)in.readObject();
		String address = message;
		
		//int aCnum=rand.nextInt(50) + 1;
		
		sendMessage("Please enter Username");
		message = (String)in.readObject();
		String username = message;
		
		sendMessage("Please enter Password");
		message = (String)in.readObject();
		String password = message;
		//Fix here the code runs to here
		for(Account a:list)
		{
			
			if(a.getACnum()==temp)
			{
				a.setName(name);
				a.setAddress(address);
				//a.setACnum(aCnum);
				a.setUsername(username);
				a.setPassword(password);
				System.out.println(list);
			}
			
			/*while(a.getACnum()==aCnum)
			{
				aCnum=rand.nextInt(50) + 1;
				a.setACnum(aCnum);
				break;
			}*/
				
		}
		choice2=0;
		tempString=name;
		sendMessage("Account updated");
		//System.out.println(temp);
	}
	
	/*Method used for withdrawing from users accounts*/
	public void Withdraw() throws ClassNotFoundException, IOException{
		int tempamount=0;
		sendMessage("How much would you like to withdraw");	
		message = (String) in.readObject();
		withdrawchoice = new Integer(message);
		
		//loops through list
		for (Account a : list) 
		{
			//if account numbers match
			if (a.getACnum()==temp)
			{
				
				tempamount = a.getBalance();
				tempamount-=withdrawchoice;
				
				if(tempamount < -1000)
				{
					System.out.println(tempamount);
					sendMessage("Unable to complete task because of insuficent credit balance:"+tempamount+" credit limit is -1000");	
				}
				else
				{
					a.setBalance(tempamount);
					System.out.println(tempamount);
					sendMessage("Account balance:"+tempamount);
				}	
			}
		}
	}
	
	/*Method used for depositing to users accounts*/
	public void Deposit() throws ClassNotFoundException, IOException{
		int tempamount=0;
		sendMessage("How much would you like to deposit");	
		message = (String) in.readObject();
		depositChoice = new Integer(message);
		
		//loops through list
		for (Account a : list) 
		{
			//if account numbers match do task
			if (a.getACnum()==temp)
			{	
				tempamount = a.getBalance();
				tempamount+=depositChoice;
				a.setBalance(tempamount);
				System.out.println(tempamount);
				sendMessage("Account balance:"+tempamount);
				break;
			}
		}
	}
	
	//overriding the run method
	public void run() 
	{
		System.out.println("Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
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
					/*Send a message to the client and read back response*/
					//sendMessage("Please choose an option enter 1 for new user, 2 for returning user, bye to close");
					sendMessage("Please choose an option enter 1 for new user, 2 for returning user, 3 to deposit, 4 to withdraw or bye to close");
					message = (String) in.readObject();
					choice = new Integer(message);
					
					UI();/*enter the UI method*/
					if(flag==true)
					{
							do/*do this while flag is set to true*/
							{
								UILoggedin();/*enter the logged in UI*/
								
							}
							while(flag==true);
					}
				}	
				
				catch (ClassNotFoundException classnot) 
				{
					System.err.println("Data received in unknown format");
				}

			} while (!message.equals("bye"));//do the above while client hasnt sent a messsage bye to server
			System.out.println(
					"Ending Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
}