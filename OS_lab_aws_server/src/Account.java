
public class Account implements accountFeats {

	private String Name;
	private String Address;
	private int ACnum;
	private String Username;
	private String Password;
	private int Credit;
	private int balance;

	public Account() {

	}

	public Account(String name, String address, int ACnum, String username, String password, int balance) {
		this.Name = name;
		this.Address = address;
		this.ACnum = ACnum;
		this.Username = username;
		this.Password = password;
		//this.Credit = credit + 1000;//int credit,
		this.balance=balance;
	}

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

	public int getCredit() {
		return Credit;
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

	public void setCredit(int credit) {
		Credit = credit;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "[ Name= " + Name + 
				", Address= " + Address + 
				", ACnum= " + ACnum + 
				", Username= " + Username + 
				", Password= " + Password + 
				", Credit= " + Credit + " ]\n";
	}

	@Override
	public int Withdraw(int i) {
		
		return i;
	}

	@Override
	public int Withdraw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void AddUser() {
		// TODO Auto-generated method stub
		
	}

}