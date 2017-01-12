/*This class contains the access or info that will be used to hold user info in the program*/
public class Account {
	//Variables------------------------------------------------------------------------------------
	private String Name;
	private String Address;
	private int ACnum;
	private String Username;
	private String Password;
	private int balance;
	//Constructors------------------------------------------------------------------------------------
	public Account() {

	}

	public Account(String name, String address, int ACnum, String username, String password, int balance) {
		this.Name = name;
		this.Address = address;
		this.ACnum = ACnum;
		this.Username = username;
		this.Password = password;
		this.balance=balance;
	}

	//Accessors------------------------------------------------------------------------------------
	public String getName() {
		return Name;
	}

	public String getAddress() {
		return Address;
	}

	public int getACnum() {
		return ACnum;
	}

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void setACnum(int aCnum) {
		ACnum = aCnum;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//Methods------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "[ Name= " + Name + 
				", Address= " + Address + 
				", ACnum= " + ACnum + 
				", Username= " + Username + 
				", Password= " + Password + " ]\n";
	}

}
