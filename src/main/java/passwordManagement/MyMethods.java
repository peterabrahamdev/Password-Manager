package passwordManagement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyMethods {

	private static final String String = null;
  private String url = "...";
  private String username = "...";
  private String password = "...";


	public void reg() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//sm("Sikeres driver regisztrďż˝lďż˝s!");
		} catch(Exception e) {
			sm(e.getMessage());
		}
	}

	public void sm(String s) {
		System.out.println(s + "\n");
	}

	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			//sm("Sikeres kapcsolódás!");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Sikertelen kapcsolódás az adatbázishoz!");
			return conn;
		}
	}

	public void disconnect(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
				//sm("Sikeres lekapcsolďż˝dďż˝s");
			} catch(Exception e) {
				sm(e.getMessage());
			}
		}
	}

	public void commandExec(String command) {
		Connection conn = connect();
		String sqlp = command;
		sm("Command: " + sqlp);
		try {
			Statement s = conn.createStatement();
			s.execute(sqlp);
			sm("Command OK!");
		} catch(SQLException e) {
			sm("CommandExec: " + e.getMessage());
		}
		disconnect(conn);
	}

	public void readUsersData(JTextArea a) {
		String username = "", password = "", x = "\t";
		int user_id = 0;
		String sqlp = "SELECT user_id, username, password FROM users Order by user_id";
		Connection conn = connect();

		try{
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			a.append(String.format("%-20s %-30s %-1s", "USER ID","FELHASZNALONEV", "JELSZO"));
			a.append("\n");
			while(rs.next()){
				user_id = rs.getInt("user_id");
				username = rs.getString("username");
				password = rs.getString("password");
				a.append(String.format("%-20s %-30s %-1s", user_id, username, password));
				a.append("\n");
			}
			rs.close();
		} catch (SQLException e) {
			sm("ReadAllData: " + e.getMessage());
		}

		disconnect(conn);
	}

	public void readDatasData(JTextArea a) {
		Connection conn = connect();
		String account = null, username = null, password = null, details = null, email = null, date = null, x = "\t";
		int data_id = 0, user_id = 0;

		String sqlp = "SELECT * FROM datas ORDER BY user_id, data_id";
		commandExec(sqlp);

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);
			a.append(String.format("%-10s %-10s %-30s %-25s %-25s %-55s %-30s %-10s\n", "DATA ID", "USER_ID", "FIOK", "FELHASZNALONEV", "JELSZO", "EMAIL", "DATUM", "MEGJEGYZES"));
			while(rs.next()) {
				data_id = rs.getInt("data_id");
				user_id = rs.getInt("user_id");
				account = rs.getString("account");
				username = rs.getString("username");
				password = rs.getString("password");
				email = rs.getString("email");
				date = rs.getString("datum");
				details = rs.getString("details");

				a.append(String.format("%-10s %-10s %-30s %-25s %-25s %-55s %-30s %-10s\n", data_id, user_id, account, username, password, email , date, details));
			}
		}catch(SQLException e) {
			sm("ReadDatasData: " + e.getMessage());
		}

		disconnect(conn);
	}

	public int displayTextField(DefaultTableModel tableModel, JTable dataTable, JTextField a, JTextField u, JTextField p, JTextField e, JTextField d, int user_id) {
		Connection conn = connect();
		int data_id = 0;

		if(tableModel.getRowCount() > -2) {
			if(tableModel.getValueAt(dataTable.getSelectedRow(), 0)!= null) {
				String account = tableModel.getValueAt(dataTable.getSelectedRow(), 0).toString();
				a.setText(account);
			} else {
				a.setText(null);
			}
			if(tableModel.getValueAt(dataTable.getSelectedRow(), 1)!= null) {
				String account = tableModel.getValueAt(dataTable.getSelectedRow(), 1).toString();
				u.setText(account);
			} else {
				u.setText(null);
			}
			if(tableModel.getValueAt(dataTable.getSelectedRow(), 2)!= null) {
				String account = tableModel.getValueAt(dataTable.getSelectedRow(), 2).toString();
				p.setText(account);
			} else {
				p.setText(null);
			}
			if(tableModel.getValueAt(dataTable.getSelectedRow(), 3)!= null) {
				String account = tableModel.getValueAt(dataTable.getSelectedRow(), 3).toString();
				e.setText(account);
			} else {
				e.setText(null);
			}
			if(tableModel.getValueAt(dataTable.getSelectedRow(), 5)!= null) {
				String account = tableModel.getValueAt(dataTable.getSelectedRow(), 5).toString();
				d.setText(account);
			} else {
				d.setText(null);
			}

			String account = a.getText();
			String username = u.getText();
			String password = p.getText();
			String email = e.getText();
			String details = d.getText();

			if(account.isEmpty()) {
				account = null;
			}
			if(username.isEmpty()) {
				username = null;
			}
			if(password.isEmpty()) {
				password = null;
			}
			if(email.isEmpty()) {
				email = null;
			}
			if(details.isEmpty()) {
				details = null;
			}

			String date = (String) tableModel.getValueAt(dataTable.getSelectedRow(), 4);
			//sm(date);

			String x = " ";
			//String sqlp = "SELECT data_id FROM datas WHERE account = '"+account+"'AND username = '"+username+"'AND password = '"+password+"'AND email = '"+email+"'AND details = '"+details+"'AND datum = '"+date+"' AND user_id = "+user_id;
			String sqlp = "SELECT * FROM datas WHERE datum = TO_DATE('"+date+"', 'YYYY-MM-DD HH24:MI:SS')";
			//sm(account + x + username + x + password + x + email + x + details);

			try {

				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery(sqlp);
				while(rs.next()) {
					data_id = rs.getInt("data_id");
				}
			} catch(SQLException ex) {
				sm("displayTextField: " + ex.getMessage());
			}
		}

		updateButtonOn();
		deleteDataButtonOn();
		setTableLines();

		disconnect(conn);
		return data_id;

	}

	public void updateRow(int user_id, DefaultTableModel tableModel, JTable dataTable, JTextField a, JTextField u, JTextField p, JTextField e, JTextField d) {
		Connection conn = connect();

		String account = a.getText();
		String username = u.getText();
		String password = p.getText();
		String email = e.getText();
		String details = d.getText();
		String date =  (String) tableModel.getValueAt(dataTable.getSelectedRow(), 4);

		String[] notNullFields = {account, username, password};
		boolean ok = false;
		for(int i=0; i<notNullFields.length; i++){
			ok = true;
			if(notNullFields[i].isEmpty() || notNullFields[i].equals(" ")){
				ok = false;
				break;
			}
		}

		if(!ok)
			JOptionPane.showMessageDialog(null, "A fiok, felhasznalonev es jelszo nem lehet ures!");
		else {
			//String sqld = "(SELECT data_id FROM datas WHERE account = '"+account+"'AND username = '"+username+"'AND password = '"+password+"'AND email = '"+email+"'AND details = '"+details+"' AND user_id = "+user_id+")";
			String sqld = "(SELECT data_id FROM datas WHERE datum = TO_DATE('" + date + "', 'YYYY-MM-DD HH24:MI:SS'))";

			String sqlp = "UPDATE datas SET account = '" + account + "', username = '" + username + "', password = '" + password + "', email = '" + email
					+ "', details = '" + details + "' WHERE user_id = " + user_id + " AND data_id = " + sqld;
			//tableModel.removeRow(dataTable.getSelectedRow());

			try {
				Statement s = conn.createStatement();
				s.executeUpdate(sqlp);
			} catch (SQLException ex) {
				sm("updateRow: " + ex.getMessage());
			}


			DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
			model.setRowCount(0);

			a.setText("");
			u.setText("");
			p.setText("");
			e.setText("");
			d.setText("");

			updateButtonOff();
			deleteDataButtonOff();
			setTableLines();
			//Object[] data = {account, username, password, email, date, details};
			//tableModel.addRow(data);
		}
		disconnect(conn);
	}

	public void updateRow(int user_id, int data_id, JTextField a, JTextField u, JTextField p, JTextField e, JTextField d) {
		Connection conn = connect();

		String account = a.getText();
		String username = u.getText();
		String password = p.getText();
		String email = e.getText();
		String details = d.getText();

		String check1 = "SELECT * FROM users WHERE user_id="+user_id;
		String check2 = "SELECT * FROM datas WHERE data_id="+data_id+" AND user_id="+user_id;

		String sqlp = "UPDATE datas SET account = '"+account+"', username = '"+username+"', password = '"+password+"', email = '"+email
				+"', details = '"+details+"' WHERE user_id = "+user_id+" AND data_id = "+data_id;

		try {
			Statement s = conn.createStatement();
			int db1 = s.executeUpdate(check1);
			if(db1 > 0) {
				int db2 = s.executeUpdate(check2);
				if(db2 > 0){
					s.executeUpdate(sqlp);
					JOptionPane.showMessageDialog(null, "Sikeres frissites!");
					a.setText("");
					u.setText("");
					p.setText("");
					e.setText("");
					d.setText("");
				}else
					JOptionPane.showMessageDialog(null, "A megadott DataID-val rendelkezo adat nem frissitheto, mert nem letezik!");
			}else
				JOptionPane.showMessageDialog(null, "A megadott UserID-val rendelkezo fiok nem kezelheto, mert nem letezik!");

		} catch (SQLException ex) {
			sm("updateRow: " + ex.getMessage());
		}



		disconnect(conn);
	}

	public void updateUser(int user_id, String username, String password){
		Connection conn = connect();

		String check1 = "SELECT * FROM users WHERE user_id="+user_id;
		String check2 = "SELECT * FROM users WHERE username='"+username+"'AND user_id !="+user_id;
		String sqlp = "UPDATE users SET username='"+username+"', password='"+password+"' WHERE user_id="+user_id+"" ;

		try{
			if(username.equals("") || password.equals("") || username.equals(" ") || password.equals(" "))
				JOptionPane.showMessageDialog(null, "Hibas felhasznalonev es/vagy jelszo!");
			else {
				Statement s = conn.createStatement();
				int db = s.executeUpdate(check1);
				if (db > 0) {
					int db2 = s.executeUpdate(check2);
					if (db2 == 0) {
						s.executeUpdate(sqlp);
						JOptionPane.showMessageDialog(null, "Sikeres frissites!");
					}
					else
						JOptionPane.showMessageDialog(null, "Ez a felhasznalonev foglalt!");
				} else {
					JOptionPane.showMessageDialog(null, "A megadott ID-val rendelkezo fiok nem frissitheto, mert nem letezik!");
				}
			}
		} catch (SQLException ex) {
			sm("updateUser: " + ex.getMessage());
		}


		disconnect(conn);

	}

	public void removeRow(int user_id, DefaultTableModel tableModel, JTable dataTable, JTextField a, JTextField u, JTextField p, JTextField e, JTextField d) {
		Connection conn = connect();

		String date =  (String) tableModel.getValueAt(dataTable.getSelectedRow(), 4);

		//String sqld = "(SELECT data_id FROM datas WHERE account = '"+account+"'AND username = '"+username+"'AND password = '"+password+"'AND email = '"+email+"'AND details = '"+details+"' AND user_id = "+user_id+")";
		String sqld= "(SELECT data_id FROM datas WHERE datum = TO_DATE('"+date+"', 'YYYY-MM-DD HH24:MI:SS'))";

		String sqlp = "DELETE FROM datas WHERE data_id = "+sqld;
		try{
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if(db == 0) JOptionPane.showMessageDialog(null, "A megadott ID-val rendelkezĹ adat nem lĂŠtezik, nem tĂśrĂślhetĹ.");
			else {
				JOptionPane.showMessageDialog(null, "Sikeres torles");
				tableModel.removeRow(dataTable.getSelectedRow());
			}
		} catch (SQLException ex) {
			sm("removeRow: " + ex.getMessage());
		}


		dataTable.repaint() ;

		updateButtonOff();


		disconnect(conn);
	}

	public void removeDataRow(int data_id) {
		Connection conn = connect();

		String sqlp = "DELETE FROM datas WHERE data_id = "+data_id;

		try{
			Statement s = conn.createStatement();
			int db = s.executeUpdate(sqlp);
			if(db == 0) JOptionPane.showMessageDialog(null, "A megadott ID-val rendelkezo adat nem letezik, nem torolheto.");
			else {
				JOptionPane.showMessageDialog(null, "Sikeres torles");
			}
		} catch (SQLException ex) {
			sm("removeRow: " + ex.getMessage());
		}


		disconnect(conn);
	}

	public void deleteAllDatas(int user_id){
		Connection conn = connect();
		String sqlp = "DELETE FROM datas WHERE user_id="+user_id;
		try{
			Statement s = conn.createStatement();
			s.executeUpdate(sqlp);
		}catch (SQLException e){
			sm("deleteAllDatas: " + e.getMessage());
		}

		disconnect(conn);
	}

	public void removeUserRow(int user_id) {
		Connection conn = connect();

		String sqlp = "DELETE FROM users WHERE user_id = "+user_id;
		String check = "SELECT * FROM datas WHERE user_id="+user_id;

		try{
			Statement s = conn.createStatement();
			int db = s.executeUpdate(check);
			if(db == 0){
				int db2 = s.executeUpdate(sqlp);
				if(db2 > 0)
					JOptionPane.showMessageDialog(null, "Sikeres torles!");
				else
					JOptionPane.showMessageDialog(null, "A megadott ID-val rendelkezo fiok nem torolheto, mert nem letezik!");
			}
			else {
				JOptionPane.showMessageDialog(null, "A fiokot nem lehet torolni, mert tartoznak hozza adatok!");
			}
		} catch (SQLException ex) {
			sm("removeRow: " + ex.getMessage());
		}


		disconnect(conn);
	}

	public void insertRow(int user_id, int data_id, JTextField a, JTextField u, JTextField p, JTextField e, JTextField d) {
		Connection conn = connect();

		String account = a.getText();
		String username = u.getText();
		String password = p.getText();
		String email = e.getText();
		String details = d.getText();

		String check1 = "SELECT * FROM users WHERE user_id="+user_id;
		String check2 = "SELECT * FROM datas WHERE data_id="+data_id;

		String sqlp = "INSERT INTO datas (data_id, user_id, account, username, password, email, details) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			Statement s = conn.createStatement();
			int db1 = s.executeUpdate(check1);
			if(db1 > 0) {
				int db2 = s.executeUpdate(check2);
				if(db2 == 0){
					ps = conn.prepareStatement(sqlp);

					ps.setInt(1, data_id);
					ps.setInt(2, user_id);
					ps.setString(3, account);
					ps.setString(4, username);
					ps.setString(5, password);
					ps.setString(6, email);
					ps.setString(7, details);

					if(ps.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "Sikeres adatfeltoltes!");
						a.setText("");
						u.setText("");
						p.setText("");
						e.setText("");
						d.setText("");
					}
					else
						JOptionPane.showMessageDialog(null, "Sikertelen adatfeltoltes!");
				}else
					JOptionPane.showMessageDialog(null, "A megadott DataID-val mar letezik adat!");
			}else
				JOptionPane.showMessageDialog(null, "A megadott UserID-val rendelkezo fiok nem kezelheto, mert nem letezik!");

		} catch (SQLException ex) {
			sm("insertRow: " + ex.getMessage());
		}
		disconnect(conn);
	}

	public void insertUser(int user_id, String username, String password){
		Connection conn = connect();
		String sqlp = "INSERT INTO users VALUES("+user_id+",'"+username+"', '"+password+"')";
		String checkID = "SELECT * FROM users WHERE user_id="+user_id;
		String checkUser = "SELECT * FROM users WHERE username='"+username+"'";
		try{
			if(username.equals("") || password.equals("") || username.equals(" ") || password.equals(" "))
				JOptionPane.showMessageDialog(null, "Hibas felhasznalonev es/vagy jelszo!");
			else {
				Statement s = conn.createStatement();
				int db = s.executeUpdate(checkID);
				if (db == 0) {
					int db2 = s.executeUpdate(checkUser);
					if (db2 == 0) {
						s.execute(sqlp);
						JOptionPane.showMessageDialog(null, "Sikeres hozzaadas!");
					} else
						JOptionPane.showMessageDialog(null, "Ez a felhasznalonev mar foglalt!");
				} else {
					JOptionPane.showMessageDialog(null, "Ezzel az azonositoval mar letezik felhasznalo!");
				}
			}
		} catch (SQLException ex) {
			sm("insertUser: " + ex.getMessage());
		}

	}

	public void updateButtonOff(){
		MainPage.updateButton.setBackground(new Color(242,242,242));
		MainPage.updateButton.setForeground(new Color(130, 130, 130));
		MainPage.updateButton.setEnabled(false);
		MainPage.updateButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				MainPage.updateButton.setBackground(new Color(242,242,242));
				MainPage.updateButton.setForeground(new Color(130, 130, 130));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				MainPage.updateButton.setBackground(new Color(242,242,242));
				MainPage.updateButton.setForeground(new Color(130, 130, 130));
			}
		});
	}

	public void updateButtonOn(){
		MainPage.updateButton.setEnabled(true);
		if(MainPage.darkMode) {
			MainPage.updateButton.setBackground(new Color(255, 69, 58));
			MainPage.updateButton.setForeground(Color.white);
			MainPage.updateButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (e.getSource() == MainPage.updateButton && MainPage.darkMode) {
						MainPage.updateButton.setBackground(Color.red);
						MainPage.updateButton.setForeground(Color.white);
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (e.getSource() == MainPage.updateButton && MainPage.darkMode) {
						MainPage.updateButton.setBackground(new Color(255, 69, 58));
						MainPage.updateButton.setForeground(Color.white);
					}
				}
			});
		} else {
			MainPage.updateButton.setBackground(new Color(252, 78, 78));
			MainPage.updateButton.setForeground(Color.white);
			MainPage.updateButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (e.getSource() == MainPage.updateButton && !MainPage.darkMode) {
						MainPage.updateButton.setBackground(Color.red);
						MainPage.updateButton.setForeground(Color.white);
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (e.getSource() == MainPage.updateButton && MainPage.darkMode) {
						MainPage.updateButton.setBackground(new Color(255, 69, 58));
						MainPage.updateButton.setForeground(Color.white);
					}
				}
			});
		}
	}

	public void deleteDataButtonOn(){

		if("FlatLaf - FlatLaf Darcula".equals(UIManager.getLookAndFeel().getID())){

			MainPage.darkMode = true;
			MainPage.deleteDataButton.setBackground(null);
			MainPage.deleteDataButton.setForeground(null);
			MainPage.deleteDataButton.setEnabled(true);
			SwingUtilities.updateComponentTreeUI(MainPage.frame);
		}else{

			MainPage.darkMode = true;
			MainPage.deleteDataButton.setBackground(Color.gray);
			MainPage.deleteDataButton.setForeground(Color.white);
			MainPage.deleteDataButton.setEnabled(true);
			MainPage.deleteDataButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (e.getSource() == MainPage.deleteDataButton && !MainPage.darkMode) {
						MainPage.deleteDataButton.setBackground(Color.gray.darker());
						MainPage.deleteDataButton.setForeground(Color.white);
					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (e.getSource() == MainPage.deleteDataButton && !MainPage.darkMode) {
						MainPage.deleteDataButton.setBackground(Color.gray);
						MainPage.deleteDataButton.setForeground(Color.white);
					}
				}
			});
		}

	}

	public void deleteDataButtonOff() {
		MainPage.deleteDataButton.setEnabled(false);
		if("FlatLaf - FlatLaf Darcula".equals(UIManager.getLookAndFeel().getID())) {
			MainPage.darkMode = true;
			MainPage.deleteDataButton.setBackground(null);
			MainPage.deleteDataButton.setForeground(null);
			SwingUtilities.updateComponentTreeUI(MainPage.frame);
		}else {
			MainPage.deleteDataButton.setBackground(new Color(242, 242, 242));
			MainPage.deleteDataButton.setForeground(Color.white);
			MainPage.deleteDataButton.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {

				}

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (e.getSource() == MainPage.deleteDataButton) {

					}
				}

				@Override
				public void mouseExited(MouseEvent e) {
					if (e.getSource() == MainPage.deleteDataButton) {

					}
				}
			});
		}
	}

	public void doubleCheckWindow(){

		MainPage.okButton = new JButton("OK");
		MainPage.okButton.setPreferredSize(new Dimension(100, 30));
		MainPage.okButton.setFocusable(false);
		MainPage.okButton.setBackground(Color.gray);
		MainPage.okButton.setForeground(Color.white);
		MainPage.okButton.setBorder(BorderFactory.createEmptyBorder());
		MainPage.okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MainPage.panel.add(MainPage.okButton);

		MainPage.megseButton = new JButton("MĂŠgse");
		MainPage.megseButton.setPreferredSize(new Dimension(100, 30));
		MainPage.megseButton.setFocusable(false);
		MainPage.megseButton.setBackground(Color.gray);
		MainPage.megseButton.setForeground(Color.white);
		MainPage.megseButton.setBorder(BorderFactory.createEmptyBorder());
		MainPage.megseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


	}

	public boolean writeToFile(File file, int user_id) {
		boolean ok;
		Connection conn = connect();
		String account = null, username = null, password = null, details = null, email = null, date = null, x = "\t\t";
		int data_id = 0;

		ArrayList<Object[]> datas = new ArrayList<>();

		String sqlp = "SELECT * FROM datas WHERE user_id = "+user_id+" ORDER BY data_id";
		commandExec(sqlp);

		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sqlp);

			while(rs.next()) {
				data_id = rs.getInt("data_id");
				account = rs.getString("account");
				username = rs.getString("username");
				password = rs.getString("password");
				email = rs.getString("email");
				date = rs.getString("datum");
				details = rs.getString("details");

				Object[] objArr = {data_id, account, username, password, email, date, details};
				datas.add(objArr);
			}

			try{
				FileWriter fw = new FileWriter(file);
				fw.write(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s \r\n", "DATA ID",  "FIOK", "FELHASZNALONEV", "JELSZO", "EMAIL", "DATUM", "MEGJEGYZES"));
				for(Object[] data : datas){
					fw.write(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s \r\n", String.valueOf(data[0]), String.valueOf(data[1]), String.valueOf(data[2]), String.valueOf(data[3]), String.valueOf(data[4]), String.valueOf(data[5]), String.valueOf(data[6])));
				}
				fw.flush();
				fw.close();
				ok = true;
			} catch (IOException e) {
				System.out.println("Nem lehet megnyitni a filet");
				ok = false;
			}
		}catch(SQLException e) {
			sm("writeToFile: " + e.getMessage());
			ok = false;
		}

		disconnect(conn);
		return ok;
	}

	public void setDeleteDatasItemState(){
		if(MainPage.tableModel.getRowCount()>0)
			MainPage.deleteDatasItem.setEnabled(true);
		else
			MainPage.deleteDatasItem.setEnabled(false);
	}

	public void saveLookAndFeel(boolean state) {
		File file = new File("LookAndFeel.txt");
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(String.valueOf(state));
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean loadLookAndFeel(){
		boolean state = false;
		String line = null;
		String path = "LookAndFeel.txt";
		try {
			BufferedReader input = new BufferedReader(new FileReader(path));
			while((line = input.readLine()) != null)
				state = Boolean.parseBoolean(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return state;
	}

	public void setTableLines(){
		MainPage.dataTable.setShowVerticalLines(true);
		MainPage.dataTable.setShowHorizontalLines(true);
	}

	public void retrieveMetadata(JTextArea a){
		String x = "\t";
		Connection connection = connect();
		try {
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet columns = dbm.getColumns(null,null, "DATAS", null);
			a.append(String.format("%-20s %-20s %-20s %-20s %-20s\n", "COLUMN_NAME", "COLUMN_SIZE", "DATA_TYPE", "IS_NULLABLE", "IS_AUTOINCREMENT"));
			while(columns.next()) {
				String columnName = columns.getString("COLUMN_NAME");
				String columnSize = columns.getString("COLUMN_SIZE");
				String datatype = columns.getString("DATA_TYPE");
				String isNullable = columns.getString("IS_NULLABLE");
				String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");

				a.append(String.format("%-20s %-20s %-20s %-20s %-20s\n", columnName, columnSize, datatype, isNullable, isAutoIncrement));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect(connection);

	}

}
