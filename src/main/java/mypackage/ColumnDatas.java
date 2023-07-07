package mypackage;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class ColumnDatas {
	//"account", "Username", "Password", "E-mail", "Date", "Details"
	
	private String account;
	private String username;
	private String password;
	private String email;
	private LocalDate date;
	private String details;
	
	public ColumnDatas(String account, String username, String password, String email, LocalDate date, String details) {
		super();
		this.account = account;
		this.username = username;
		this.password = password;
		this.email = email;
		this.date = date;
		this.details = details;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	DefaultTableModel tableModel = new DefaultTableModel() {

		   @Override
		   public boolean isCellEditable(int row, int column) {
		       //Only the third column
		       return false;
		   }
		};
		
	
	
}
