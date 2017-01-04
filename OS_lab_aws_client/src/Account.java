import java.util.ArrayList;

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

	public Account(String name, String address, int aCnum, String username, String password, int credit) {
		this.Name = name;
		this.Address = address;
		this.ACnum = aCnum;
		this.Username = username;
		this.Password = password;
		this.Credit = credit + 1000;
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

	@Override
	public String toString() {
		return "[Name= " + Name + ", Address= " + Address + ", ACnum= " + ACnum + ", Username= " + Username
				+ ", Password= " + Password + ", Credit= " + Credit + "]";
	}
	
	public void accountValidation(ArrayList<Account> l, int ac){
		//run through list of objects
		for(int i=0;i<l.size();i++) {
			
			if(l.get(i).getACnum()==ac)
			{
				System.out.println("Account already taken, next available account given");
				ac+=1;
			}
			else
			{
				System.out.println("Account created");
			}	
		}
		
	}

	public void makeWithdrawal(float withDrawal) {
		
		//accountBalance = accountBalance - withDrawal;
		
	}

	public void makeLodgement(float lodgement) {
		//accountBalance = accountBalance + lodgement;
	}

}
