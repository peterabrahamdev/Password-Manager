package passwordManagement;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import mypackage.ColumnDatas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class MainPage extends Component implements ActionListener, MouseListener, FocusListener{

	static MyMethods myMethods = new MyMethods();

	public static JFrame frame;
	public static JPanel panel;
	public static JPanel fillPanel;
	public static JLabel accountLabel;
	public static JTextField accountField;
	public static JLabel userLabel;
	public static JTextField usernameField;
	public static JLabel passwordLabel;
	public static JTextField passwordAccountField;
	public static JLabel emailLabel;
	public static JTextField emailField;
	public static JLabel detailsLabel;
	public static JTextField detailsField;
	public static JCheckBox checkBox;
	public static JButton saveButton;
	public static JButton updateButton;
	public static JButton xButton;
	public static JLabel errorLabel;
	public static JButton logoutButton;
	public static JButton exitButton;
	public static ArrayList<ColumnDatas> columnDatas;
	public static String[] columnNames = {"Fiók", "Felhasználónév", "Jelszó", "E-mail", "Dátum", "Megjegyzés"};
	public static int[] columnWidth = {175, 175, 175, 175, 175, 175};
	public static JTable dataTable;
	public static JSeparator tableSeparator;
	public static DefaultTableModel tableModel;
	public static JButton deleteDataButton;
	public static JButton deleteAccountButton;
	public static JButton okButton;
	public static JButton megseButton;
	public static JScrollPane sp;
	public static JMenu fileMenu, settingsMenu, moreMenu;
	public static JMenuItem kilep;
	public static JMenuItem deleteAccountItem;
	public static JMenuItem deleteDataItem;
	public static JMenuItem deleteDatasItem;
	public static JMenuItem logoutItem;
	public static JMenuItem writeToFile, requestMetadata;
	public static JMenuItem kaz, knev, kiq;
	public static JCheckBoxMenuItem nightModeItem;
	public static JMenuBar menusor;
	public static boolean darkMode;

	private int user_id;


	// account, username, password, date, e-mail, details, checkbox
	// alulról 35px minusz

	MainPage(int user_id)  {

		this.user_id = user_id;
		frame = new JFrame();
		frame.setTitle("Jelszókezelõ");
		frame.setSize(1100, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
		frame.setIconImage(icon);

		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.white);
		frame.add(panel);

		String query2 = "SELECT * FROM datas WHERE user_id =" + user_id + "ORDER BY account";
		ResultSet rs;

		tableModel = new DefaultTableModel(columnNames, 0);/*{
			@Override
			public
		    Class<?> getColumnClass(int columnIndex) {
		        return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
		    }
		};*/
		String account;
		String user;
		String password;
		String email;
		String date;
		String details;

		try {
			Statement st = myMethods.connect().createStatement();
			rs = st.executeQuery(query2);

			while(rs.next()) {
				account = rs.getString("account");
				user = rs.getString("username");
				password = rs.getString("password");
				email = rs.getString("email");
				date = rs.getString(String.valueOf("datum"));
				details = rs.getString("details");

				Object[] data = {account, user, password, email, date, details};
				tableModel.addRow(data);
			}

			dataTable = new JTable(tableModel);

			int i = 0;
			for(int width : columnWidth) {
				TableColumn column = dataTable.getColumnModel().getColumn(i++);
				column.setMinWidth(width);
				column.setMaxWidth(width+6);
				column.setPreferredWidth(width);
			}

			dataTable.setFont(new Font("Poppins Light", Font.PLAIN, 12));
			sp = new JScrollPane(dataTable);
			sp.setBounds(0, 0, 1085, 195);
			/*DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			for(i=0; i<6; i++) {
				dataTable.isCellEditable(i, i);
			}
			 */

			dataTable.setRowHeight(25);
			//dataTable.setSelectionBackground(new Color(33, 146, 217));
			//dataTable.setSelectionForeground(Color.white);
			dataTable.getTableHeader().setFont(new Font("Poppins Medium", Font.PLAIN, 12));
			dataTable.setPreferredSize(null);
			dataTable.addMouseListener(this);
			dataTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//dataTable.setAutoCreateRowSorter(true);

			sp.setBorder(BorderFactory.createEmptyBorder());

			panel.add(sp);

		} catch (SQLException e1) {
			myMethods.sm("Table Creation: " + e1.getMessage());
		}

		tableSeparator = new JSeparator();
		tableSeparator.setBounds(500, 400, 0, 1100);
		panel.add(tableSeparator);

		fillPanel = new JPanel();
		fillPanel.setBounds(385, 200, 300, 365);
		fillPanel.setLayout(null);
		//fillPanel.setBackground(Color.white);
		//fillPanel.setBorder(BorderFactory.createDashedBorder(Color.black));
		panel.add(fillPanel);

		saveButton = new JButton("Mentés");
		saveButton.setBounds(70, 314, 100, 30);
		saveButton.setFocusable(false);
		saveButton.setBackground(new Color(252, 78, 78));
		saveButton.setForeground(Color.white);
		saveButton.setBorder(BorderFactory.createEmptyBorder());
		saveButton.addMouseListener(this);
		saveButton.addActionListener(this);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fillPanel.add(saveButton);

		updateButton = new JButton("Frissítés");
		updateButton.setBounds(200, 314, 100, 30);
		updateButton.setFocusable(false);
		updateButton.setBackground(new Color(242,242,242));
		updateButton.setForeground(new Color(130, 130, 130));
		updateButton.setEnabled(false);
		updateButton.setBorder(BorderFactory.createEmptyBorder());
		updateButton.addMouseListener(this);
		updateButton.addActionListener(this);
		updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fillPanel.add(updateButton);

		xButton = new JButton("X");
		xButton.setBounds(0, 314, 40, 30);
		xButton.setFocusable(false);
		xButton.setBackground(new Color(252, 78, 78));
		xButton.setForeground(Color.white);
		xButton.setBorder(BorderFactory.createEmptyBorder());
		xButton.addMouseListener(this);
		xButton.addActionListener(this);
		xButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fillPanel.add(xButton);

		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);
		errorLabel.setBounds(100, 0, 0, 30);
		errorLabel.setFont(new Font("", Font.BOLD, 10));
		panel.add(errorLabel);

		accountLabel = new JLabel("Fiók:");
		accountLabel.setBounds(0, 0, 200, 30);
		accountLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		fillPanel.add(accountLabel);

		accountField = new JTextField();
		accountField.setBounds(0, 30, 300, 25);
		accountField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
		accountField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		accountField.addFocusListener(this);
		accountField.addActionListener(this);
		fillPanel.add(accountField);

		userLabel = new JLabel("Felhasználónév:");
		userLabel.setBounds(0, 60, 200, 30);
		userLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		fillPanel.add(userLabel);

		usernameField = new JTextField();
		usernameField.setBounds(0, 90, 300, 25);
		usernameField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
		usernameField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		usernameField.addFocusListener(this);
		usernameField.addActionListener(this);
		fillPanel.add(usernameField);

		passwordLabel = new JLabel("Jelszó:");
		passwordLabel.setBounds(0, 120, 200, 30);
		passwordLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		fillPanel.add(passwordLabel);

		passwordAccountField = new JTextField();
		passwordAccountField.setBounds(0, 150, 300, 25);
		passwordAccountField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
		passwordAccountField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		passwordAccountField.addFocusListener(this);
		passwordAccountField.addActionListener(this);
		fillPanel.add(passwordAccountField);

		emailLabel = new JLabel("E-mail:");
		emailLabel.setBounds(0, 180, 200, 30);
		emailLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		fillPanel.add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(0, 210, 300, 25);
		emailField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
		emailField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		emailField.addFocusListener(this);
		emailField.addActionListener(this);
		fillPanel.add(emailField);

		detailsLabel = new JLabel("Megjegyzés:");
		detailsLabel.setBounds(0, 240, 200, 30);
		detailsLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		fillPanel.add(detailsLabel);

		detailsField = new JTextField();
		detailsField.setBounds(0, 270, 300, 25);
		detailsField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
		detailsField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
		detailsField.addFocusListener(this);
		detailsField.addActionListener(this);
		fillPanel.add(detailsField);

		exitButton = new JButton("Kilépés");
		exitButton.setBounds(965, 515, 100, 30);
		exitButton.setFocusable(false);
		exitButton.setBackground(Color.gray);
		exitButton.setForeground(Color.white);
		exitButton.setBorder(BorderFactory.createEmptyBorder());
        //exitButton.putClientProperty("JButton.buttonType", "roundRect");
		exitButton.addMouseListener(this);
		exitButton.addActionListener(this);
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(exitButton);

		logoutButton = new JButton("Kijelentkezés");
		logoutButton.setBounds(845, 515, 100, 30);
		logoutButton.setFocusable(false);
		logoutButton.setBackground(Color.gray);
		logoutButton.setForeground(Color.white);
		logoutButton.setBorder(BorderFactory.createEmptyBorder());
		logoutButton.addMouseListener(this);
		logoutButton.addActionListener(this);
		logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(logoutButton);

		deleteDataButton = new JButton("Adat törlése");
		deleteDataButton.setBounds(145, 515, 100, 30);
		deleteDataButton.setFocusable(false);
		myMethods.deleteDataButtonOff();
		deleteDataButton.setBorder(BorderFactory.createEmptyBorder());
		deleteDataButton.addActionListener(this);
		deleteDataButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(deleteDataButton);

		deleteAccountButton = new JButton("Fiók törlése");
		deleteAccountButton.setBounds(20, 515, 100, 30);
		deleteAccountButton.setFocusable(false);
		deleteAccountButton.setBorder(BorderFactory.createEmptyBorder());
		deleteAccountButton.addMouseListener(this);
		deleteAccountButton.addActionListener(this);
		logoutButton.setBackground(Color.gray);
		logoutButton.setForeground(Color.white);
		deleteAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		panel.add(deleteAccountButton);

		fileMenu = new JMenu("Fájl");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		deleteAccountItem = new JMenuItem("Fiók törlése");
		KeyStroke delAccShort = KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK);
		deleteAccountItem.setAccelerator(delAccShort);
		deleteAccountItem.addActionListener(this);

		deleteDataItem = new JMenuItem("Kijelölt adat törlése");
		KeyStroke delDatShort = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK);
		deleteDataItem.setAccelerator(delDatShort);
		deleteDataItem.addActionListener(this);
		deleteDataItem.setEnabled(false);

		deleteDatasItem = new JMenuItem("Összes adat törlése");
		KeyStroke delDatsShort = KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK);
		deleteDatasItem.setAccelerator(delDatsShort);
		deleteDatasItem.addActionListener(this);
		myMethods.setDeleteDatasItemState();

		writeToFile = new JMenuItem("Adatok letöltése szöveges fájlba");
		KeyStroke writeToFileShort = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		writeToFile.setAccelerator(writeToFileShort);
		writeToFile.addActionListener(this);

		requestMetadata = new JMenuItem("A táblázat metaadatainak lekérdezése");
		KeyStroke reqMetShort = KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK);
		requestMetadata.setAccelerator(reqMetShort);
		requestMetadata.addActionListener(this);

		logoutItem = new JMenuItem("Kijelentkezés");
		KeyStroke logoutShort = KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK);
		logoutItem.setAccelerator(logoutShort);
		logoutItem.addActionListener(this);

		kilep = new JMenuItem("Kilépés");
		KeyStroke kilepShort = KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK);
		kilep.setAccelerator(kilepShort);
		kilep.addActionListener(this);

		fileMenu.add(deleteAccountItem);
		fileMenu.add(deleteDataItem);
		fileMenu.add(deleteDatasItem);
		fileMenu.addSeparator();
		fileMenu.add(writeToFile);
		fileMenu.add(requestMetadata);
		fileMenu.addSeparator();
		fileMenu.add(logoutItem);
		fileMenu.add(kilep);


		settingsMenu = new JMenu("Beállítások");
		settingsMenu.setMnemonic(KeyEvent.VK_B);
		nightModeItem = new JCheckBoxMenuItem("Éjszakai mód");
		nightModeItem.setState(false);
		KeyStroke nightModeShort = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
		nightModeItem.setAccelerator(nightModeShort);
		nightModeItem.addActionListener(this);
		settingsMenu.add(nightModeItem);
		moreMenu = new JMenu("Egyéb");
		moreMenu.setMnemonic(KeyEvent.VK_E);
		menusor = new JMenuBar();
		frame.setJMenuBar(menusor);
		menusor.add(fileMenu);
		menusor.add(settingsMenu);
		menusor.add(moreMenu);
		//menusor.setBackground(new Color(255, 255, 255));

		if("FlatLaf - FlatLaf Darcula".equals(UIManager.getLookAndFeel().getID())){
			darkMode = true;
			JButton[] buttons = {exitButton, deleteDataButton, logoutButton, deleteAccountButton};
			for(JButton button : buttons){
				button.setBackground(null);
				button.setForeground(null);
			}
			buttons = new JButton[]{xButton, saveButton, updateButton};
			for(JButton button : buttons){
				button.setBackground(new Color(255,69,58));
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}else{
			darkMode = false;
			JButton[] buttons = {exitButton, deleteDataButton, logoutButton, deleteAccountButton};
			for(JButton button : buttons){
				button.setBackground(Color.gray);
				button.setForeground(Color.white);
			}

			buttons = new JButton[]{xButton, saveButton, updateButton};
			for(JButton button : buttons){
				button.setBackground(new Color(252, 78, 78));
			}

			SwingUtilities.updateComponentTreeUI(frame);
		}
		dataTable.setShowVerticalLines(true);
		dataTable.setShowHorizontalLines(true);

		if(myMethods.loadLookAndFeel())
			MainPage.nightModeItem.setSelected(true);
		else
			MainPage.nightModeItem.setSelected(false);

		frame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(nightModeItem.isSelected()){
			try {
				UIManager.setLookAndFeel(new FlatDarculaLaf());
			}
			catch(Exception ex){
				ex.getMessage();
			}
			if("FlatLaf - FlatLaf Darcula".equals(UIManager.getLookAndFeel().getID())) {
                darkMode = true;
				JButton[] buttons = {exitButton, deleteDataButton, logoutButton, deleteAccountButton};
				for (JButton button : buttons) {
					button.setBackground(null);
					button.setForeground(null);
					SwingUtilities.updateComponentTreeUI(frame);
				}

				buttons = new JButton[]{xButton, saveButton, updateButton};
				for(JButton button : buttons){
					button.setBackground(new Color(255,69,58));
				}

			}
			fillPanel.setBackground(null);
			panel.setBackground(null);

			SwingUtilities.updateComponentTreeUI(frame);
			myMethods.setTableLines();
			myMethods.saveLookAndFeel(darkMode);
		}else{
			try {
				UIManager.setLookAndFeel(new FlatIntelliJLaf());
			}
			catch(Exception ex){
				ex.getMessage();
			}
            darkMode = false;
			JButton[] buttons = {exitButton, deleteDataButton, logoutButton, deleteAccountButton};
			for(JButton button : buttons){
				button.addMouseListener(this);
				button.setBackground(Color.gray);
				button.setForeground(Color.white);
			}

			buttons = new JButton[]{xButton, saveButton, updateButton};
			for(JButton button : buttons){
				button.setBackground(new Color(252, 78, 78));
			}

			SwingUtilities.updateComponentTreeUI(frame);

			myMethods.setTableLines();

			myMethods.saveLookAndFeel(darkMode);

		}

		if(e.getSource().equals(kilep)) {myMethods.saveLookAndFeel(darkMode); frame.dispose(); System.exit(0);}

		if(e.getSource() == saveButton || e.getSource() == accountField || e.getSource() == usernameField || e.getSource() == passwordAccountField || e.getSource() == emailField || e.getSource() == detailsField) {

			myMethods.updateButtonOff();
			myMethods.deleteDataButtonOff();
			deleteDataItem.setEnabled(false);
			myMethods.setTableLines();

			String account = accountField.getText();
			String user = usernameField.getText();
			String password = passwordAccountField.getText();
			String email = emailField.getText();
			String details = detailsField.getText();
			String date;

			String[] notNullFields = {account, user, password};
			boolean check = false;
			for(int i=0; i<notNullFields.length; i++){
				check = true;
				if(notNullFields[i].isEmpty() || notNullFields[i].equals(" ")){
					check = false;
					break;
				}
			}

			if(!check)
				JOptionPane.showMessageDialog(null, "A fiok, felhasznalonev es jelszo nem lehet ures!");
			else {
				String sql = "SELECT data_id_seq.nextval from DUAL";
				Connection conn = myMethods.connect();
				PreparedStatement ps = null;
				int nextID_from_seq = 0;
				String sqlp;
				int db = 0;
				boolean ok = false;
				int data_id = 0;
				String query = "INSERT INTO datas (data_id, user_id, account, username, password, email, details) VALUES(?,?,?,?,?,?,?)";
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs.next())
						nextID_from_seq = rs.getInt(1);
					sqlp = "SELECT * FROM datas WHERE data_id=" + nextID_from_seq;
					Statement s = conn.createStatement();
					db = s.executeUpdate(sqlp);
					if (db != 0) {
						do {
							s = conn.createStatement();
							sqlp = "SELECT * FROM datas WHERE data_id=" + nextID_from_seq;
							db = s.executeUpdate(sqlp);
							if (db > 0) {
								ok = false;
								nextID_from_seq++;
							} else if (db == 0) {
								ok = true;
							}
						} while (!ok);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				ResultSet rs;

				if (account.equals("") && user.equals("") && password.equals("") && email.equals("") && details.equals("")) {
					return;
				}

				try {
					ps = myMethods.connect().prepareStatement(query);

					ps.setInt(1, nextID_from_seq);
					ps.setInt(2, user_id);
					ps.setString(3, account);
					ps.setString(4, user);
					ps.setString(5, password);
					ps.setString(6, email);
					ps.setString(7, details);

					if (ps.executeUpdate() > 0) {
						accountField.setText("");
						usernameField.setText("");
						passwordAccountField.setText("");
						emailField.setText("");
						detailsField.setText("");
					}
					String query2 = "SELECT account, username, password, email, datum, details FROM datas WHERE user_id =" + user_id + "AND data_id =" + nextID_from_seq;
					Statement st = myMethods.connect().createStatement();
					rs = st.executeQuery(query2);

					while (rs.next()) {

						account = rs.getString("account");
						user = rs.getString("username");
						password = rs.getString("password");
						email = rs.getString("email");
						date = rs.getString(String.valueOf("datum"));
						details = rs.getString("details");

						Object[] data = {account, user, password, email, date, details};
						tableModel.addRow(data);
						dataTable.repaint();
					}

					myMethods.setDeleteDatasItemState();


				} catch (SQLException e1) {

					myMethods.sm(e1.getMessage());
				}
			}
		}

		if(e.getSource() == exitButton) {
			System.exit(0);
		}

		if(e.getSource() == logoutButton || e.getSource() == logoutItem) {
			tableModel = (DefaultTableModel) dataTable.getModel();
			tableModel.setRowCount(0);
			new HomePage();
			myMethods.saveLookAndFeel(darkMode);
			frame.dispose();

		}

		if(e.getSource() == deleteDataButton || e.getSource() == deleteDataItem) {

			Object[] options = {"OK", "Mégse"};
			int result = JOptionPane.showOptionDialog(null, "Biztosan kitörlöd a kijelölt adatot a fiókról?", "Meger?sítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			switch (result) {
				case 0:
					if(tableModel.getRowCount() > 0 && !dataTable.getSelectionModel().isSelectionEmpty()) {
						myMethods.removeRow(user_id, tableModel, dataTable, accountField, usernameField, passwordAccountField, emailField, detailsField);
						myMethods.deleteDataButtonOff();
						deleteDataItem.setEnabled(false);
						dataTable.setShowVerticalLines(true);
						dataTable.setShowHorizontalLines(true);
					}
					else{
						updateButton.setBackground(new Color(252, 78, 78));
						updateButton.setForeground(Color.white);
						updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
					break;
				case 1:
					break;
			}
			myMethods.setDeleteDatasItemState();


		}

		if(e.getSource() == deleteDatasItem) {

			Object[] options = {"OK", "Mégse"};
			int result = JOptionPane.showOptionDialog(null, "Biztosan kitörlöd az összes adatot err?l a fiókról?", "Meger?sítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			switch (result) {
				case 0:
					String sqlp = null;
					sqlp = "DELETE FROM datas WHERE user_id = "+user_id;
					myMethods.commandExec(sqlp);
					while(tableModel.getRowCount() > 0) {
						tableModel.removeRow(0);
					}
					break;
				case 1:
					break;
			}
			myMethods.setDeleteDatasItemState();
			myMethods.updateButtonOff();
			myMethods.deleteDataButtonOff();
			deleteDataItem.setEnabled(false);

		}

		if(e.getSource() == deleteAccountButton || e.getSource() == deleteAccountItem) {
			Object[] options = {"OK", "Mégse"};
			int result = JOptionPane.showOptionDialog(null, "Biztosan kitörlöd ezt a fiókot?", "Meger?sítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
			switch (result) {
				case 0:
					String sqlp = "DELETE FROM datas WHERE user_id = "+user_id;
					myMethods.commandExec(sqlp);
					sqlp = "DELETE FROM users WHERE user_id ="+user_id;
					myMethods.commandExec(sqlp);
					new HomePage();
					myMethods.saveLookAndFeel(darkMode);
					frame.dispose();
					break;
				case 1:
					break;
			}
		}

		if(e.getSource() == updateButton) {

			if(dataTable.getRowCount()>0) {

				myMethods.updateRow(user_id, tableModel, dataTable, accountField, usernameField, passwordAccountField, emailField, detailsField);
				ResultSet rs;
				String query2 = "SELECT account, username, password, email, datum, details FROM datas WHERE user_id =" + user_id;
				Statement st;
				try {
					st = myMethods.connect().createStatement();
					rs = st.executeQuery(query2);
					while(rs.next()) {

						String account = rs.getString("account");
						String user = rs.getString("username");
						String password = rs.getString("password");
						String email = rs.getString("email");
						String date = rs.getString(String.valueOf("datum"));
						String details = rs.getString("details");

						Object[] data = {account, user, password, email, date, details};
						tableModel.addRow(data);

					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == xButton) {
			accountField.setText("");
			usernameField.setText("");
			passwordAccountField.setText("");
			emailField.setText("");
			detailsField.setText("");

			myMethods.updateButtonOff();

		}

		if(e.getSource() == writeToFile){
			File file = new File("passwords.txt");
			if(myMethods.writeToFile(file, user_id)){
				JOptionPane.showMessageDialog(frame,"Sikeres letöltés");
			}else{
				JOptionPane.showMessageDialog(frame,"Sikertelen letöltés");
			}
		}

		if(e.getSource() == requestMetadata){
			new DisplayMetadata();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == dataTable) {
			myMethods.displayTextField(tableModel, dataTable, accountField, usernameField, passwordAccountField, emailField, detailsField, user_id);
			//if("FlatLaf - FlatLaf Darcula".equals(UIManager.getLookAndFeel().getID()))
			//	MainPage.deleteDataButton.removeMouseListener(this);
			deleteDataItem.setEnabled(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == exitButton && !darkMode) {
			exitButton.setBackground(Color.gray.darker());
		}

		if(e.getSource() == logoutButton && !darkMode) {
			logoutButton.setBackground(Color.gray.darker());
		}

		if(e.getSource() == deleteAccountButton && !darkMode) {
			deleteAccountButton.setBackground(Color.gray.darker());
		}

		if(e.getSource() == saveButton && !darkMode) {
			saveButton.setBackground(Color.red);
		}else if(e.getSource() == saveButton && darkMode){
			saveButton.setBackground(Color.red);
		}

		if(e.getSource() == xButton && !darkMode) {
			xButton.setBackground(Color.red);
		}else if(e.getSource() == xButton && darkMode){
			xButton.setBackground(Color.red);
		}

		if (e.getSource() == MainPage.updateButton && !MainPage.darkMode) {
			MainPage.updateButton.setBackground(Color.red);
			MainPage.updateButton.setForeground(Color.white);
		} else if (e.getSource() == MainPage.updateButton && MainPage.darkMode) {
			MainPage.updateButton.setBackground(Color.red);
			MainPage.updateButton.setForeground(Color.white);
		}

		if(e.getSource() == okButton) {
			okButton.setBackground(Color.gray.darker());
		}

		if(e.getSource() == megseButton) {
			megseButton.setBackground(Color.gray.darker());
		}

		if(e.getSource() == fileMenu){

		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == exitButton && !darkMode) {
			exitButton.setBackground(Color.gray);
		}

		if(e.getSource() == logoutButton && !darkMode) {
			logoutButton.setBackground(Color.gray);
		}

		if(e.getSource() == deleteAccountButton && !darkMode) {
			deleteAccountButton.setBackground(Color.gray);
		}

		if(e.getSource() == saveButton && !darkMode) {
			saveButton.setBackground(new Color(252, 78, 78));
		}else if(e.getSource() == saveButton && darkMode){
			saveButton.setBackground(new Color(255,69,58));
		}

		if(e.getSource() == xButton && !darkMode) {
			xButton.setBackground(new Color(252, 78, 78));
		}else if(e.getSource() == xButton && darkMode){
			xButton.setBackground(new Color(255,69,58));
		}

		if (e.getSource() == MainPage.updateButton && !MainPage.darkMode) {
			MainPage.updateButton.setBackground(new Color(252, 78, 78));
			MainPage.updateButton.setForeground(Color.white);
		} else if (e.getSource() == MainPage.updateButton && MainPage.darkMode) {
			MainPage.updateButton.setBackground(new Color(255, 69, 58));
			MainPage.updateButton.setForeground(Color.white);
		}

		if(e.getSource() == okButton) {
			okButton.setBackground(Color.gray);
		}

		if(e.getSource() == megseButton) {
			megseButton.setBackground(Color.gray);
		}


	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {

	}



}
