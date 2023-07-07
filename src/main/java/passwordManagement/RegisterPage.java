package passwordManagement;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterPage implements ActionListener, FocusListener, MouseListener{

	static MyMethods myMethods = new MyMethods();

	public static JFrame frame;
	public static JPanel panel;
	public static JPanel titlePanel;
	public static JLabel titleLabel;
	public static JLabel userLabel;
	public static JTextField userField;
	public static JLabel userErrorLabel;
	public static JSeparator userSeparator;
	public static JLabel passwordLabel;
	public static JPasswordField passwordField;
	public static JSeparator passwordSeparator;
	public static JLabel passwordAgainLabel;
	public static JLabel passwordAgainErrorLabel;
	public static JPasswordField passwordAgainField;
	public static JSeparator passwordAgainSeparator;
	public static JButton registerButton;
	public static JButton exitButton;
	public static JButton backButton;
	public static boolean darkMode;


	RegisterPage(){

		frame = new JFrame();
		frame.requestFocusInWindow();
		frame.setTitle("Jelszókezelõ");
		frame.setResizable(false);
		frame.setSize(700, 400);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
		frame.setIconImage(icon);

		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.white);
		frame.add(panel);

		titleLabel = new JLabel("Jelszókezelõ");
		titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		titleLabel.setBounds(285, 20, 130, 20);
		panel.add(titleLabel);

		backButton = new JButton("Vissza");
		backButton.setBounds(455, 315, 100, 30);
		backButton.setFocusable(false);
		backButton.setBackground(Color.gray);
		backButton.setForeground(Color.white);
		backButton.setBorder(BorderFactory.createEmptyBorder());
		backButton.addMouseListener(this);
		backButton.addActionListener(this);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(backButton);

		exitButton = new JButton("Kilépés");
		exitButton.setBounds(565, 315, 100, 30);
		exitButton.setFocusable(false);
		exitButton.setBackground(Color.gray);
		exitButton.setForeground(Color.white);
		exitButton.setBorder(BorderFactory.createEmptyBorder());
		exitButton.addMouseListener(this);
		exitButton.addActionListener(this);
		exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(exitButton);

		if("FlatLaf - FlatLaf Darcula".equals(UIManager.getLookAndFeel().getID())){
			darkMode = true;
			JButton[] buttons = {exitButton, backButton};
			for(JButton button : buttons){
				button.removeMouseListener(this);
				button.setBackground(null);
				button.setForeground(null);
			}

			SwingUtilities.updateComponentTreeUI(frame);
		}else{
			darkMode = false;
			JButton[] buttons = {exitButton, backButton};
			for(JButton button : buttons){
				button.removeMouseListener(this);
				button.setBackground(Color.gray);
				button.setForeground(Color.white);
			}
		}

		userField = new JTextField("Felhasználónév");
		userField.setBounds(275, 98, 150, 30);
		userField.setFont(new Font("", Font.PLAIN, 14));
		userField.setForeground(new Color(153, 153, 153));
		userField.setOpaque(false);
		userField.setBorder(null);
		userField.addFocusListener(this);
		userField.addActionListener(this);
		panel.add(userField);

		userSeparator = new JSeparator();
		userSeparator.setBounds(275, 126, 150, 30);
		userSeparator.setBackground(Color.DARK_GRAY);
		panel.add(userSeparator);

		userErrorLabel = new JLabel("");
		userErrorLabel.setForeground(Color.red);
		userErrorLabel.setBounds(275, 124, 200, 30);
		userErrorLabel.setFont(new Font("", Font.BOLD, 10));
		panel.add(userErrorLabel);

		passwordField = new JPasswordField("********");
		passwordField.setBounds(275, 148, 150, 30);
		passwordField.setFont(new Font("", Font.PLAIN, 14));
		passwordField.setForeground(new Color(153, 153, 153));
		passwordField.setOpaque(false);
		passwordField.setBorder(null);
		passwordField.putClientProperty( FlatClientProperties.STYLE, "showRevealButton: true" );
		passwordField.addFocusListener(this);
		passwordField.addActionListener(this);
		panel.add(passwordField);

		passwordSeparator = new JSeparator();
		passwordSeparator.setBounds(275, 176, 150, 30);
		passwordSeparator.setBackground(Color.DARK_GRAY);
		panel.add(passwordSeparator);

		passwordAgainField = new JPasswordField("********");
		passwordAgainField.setBounds(275, 198, 150, 30);
		passwordAgainField.setFont(new Font("", Font.PLAIN, 14));
		passwordAgainField.setForeground(new Color(153, 153, 153));
		passwordAgainField.setOpaque(false);
		passwordAgainField.setBorder(null);
		passwordAgainField.putClientProperty( FlatClientProperties.STYLE, "showRevealButton: true" );
		passwordAgainField.addFocusListener(this);
		passwordAgainField.addActionListener(this);
		panel.add(passwordAgainField);

		passwordAgainSeparator = new JSeparator();
		passwordAgainSeparator.setBounds(275, 226, 150, 30);
		passwordAgainSeparator.setBackground(Color.DARK_GRAY);
		panel.add(passwordAgainSeparator);

		passwordAgainErrorLabel = new JLabel("");
		passwordAgainErrorLabel.setForeground(Color.red);
		passwordAgainErrorLabel.setBounds(275, 224, 150, 30);
		passwordAgainErrorLabel.setFont(new Font("", Font.BOLD, 10));
		panel.add(passwordAgainErrorLabel);

		registerButton = new JButton("Regisztrálás");
		registerButton.setBounds(300, 253, 100, 30);
		registerButton.setFocusable(false);
		registerButton.setBackground(new Color(252, 78, 78));
		registerButton.setForeground(Color.white);
		registerButton.setBorder(BorderFactory.createEmptyBorder());
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerButton.addMouseListener(this);
		registerButton.addActionListener(this);
		panel.add(registerButton);





		frame.setVisible(true);
		frame.requestFocusInWindow();
	}


	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == userField) {

			if(userField.getText().trim().isEmpty() == true || userField.getText().trim().equals("Felhasználónév")) {
				userField.setText(null);
				if(darkMode)
					userField.setForeground(Color.white);
				else
					userField.setForeground(Color.black);
			}

		}
		if(e.getSource() == passwordField) {
			if(passwordField.getPassword().length == 0 ||String.valueOf(passwordField.getPassword()).equals("********")) {
				passwordField.setText(null);
				if(darkMode)
					passwordField.setForeground(Color.white);
				else
					passwordField.setForeground(Color.black);
			}
		}

		if(e.getSource() == passwordAgainField) {
			if(passwordAgainField.getPassword().length == 0 ||String.valueOf(passwordAgainField.getPassword()).equals("********")) {
				passwordAgainField.setText(null);
				if(darkMode)
					passwordAgainField.setForeground(Color.white);
				else
					passwordAgainField.setForeground(Color.black);
			}
		}
	}


	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == userField) {
			if(userField.getText().trim().isEmpty() == true) {
				userField.setForeground(new Color(153, 153, 153));
				userField.setText("Felhasználónév");
			}
		}

		if(e.getSource() == passwordField) {
			if(String.valueOf(passwordField.getPassword()).isEmpty()) {
				passwordField.setForeground(new Color(153, 153, 153));
				passwordField.setText("********");
			}
		}

		if(e.getSource() == passwordAgainField) {
			if(String.valueOf(passwordAgainField.getPassword()).isEmpty()) {
				passwordAgainField.setForeground(new Color(153, 153, 153));
				passwordAgainField.setText("********");
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource() == frame) {
			frame.requestFocusInWindow();
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
		if(e.getSource() == registerButton) {
			registerButton.setBackground(Color.red);
		}

		if(e.getSource() == backButton) {
			backButton.setBackground(Color.gray.darker());
		}

		if(e.getSource() == exitButton) {
			exitButton.setBackground(Color.gray.darker());
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == registerButton) {
			registerButton.setBackground(new Color(252, 78, 78));
		}

		if(e.getSource() == backButton) {
			backButton.setBackground(Color.gray);
		}

		if(e.getSource() == exitButton) {
			exitButton.setBackground(Color.gray);
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == registerButton || e.getSource() == userField || e.getSource() == passwordField || e.getSource() == passwordAgainField) {
			String username = userField.getText();
			String password = String.valueOf(passwordField.getPassword());
			String passwordAgain = String.valueOf(passwordAgainField.getPassword());

			String sql = "SELECT id_seq.nextval from DUAL";
			Connection conn = myMethods.connect();
			PreparedStatement ps = null;
			int nextID_from_seq = 0;
			String sqlp;
			int db = 0;
			boolean ok = false;
			String query = "INSERT INTO users (user_id, username, password) VALUES(?, ?,?)";
			try {
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if (rs.next())
					nextID_from_seq = rs.getInt(1);
				sqlp = "SELECT * FROM users WHERE user_id=" + nextID_from_seq;
				Statement s = conn.createStatement();
				db = s.executeUpdate(sqlp);
				if(db != 0) {
					do {
						s = conn.createStatement();
						sqlp = "SELECT * FROM users WHERE user_id=" + nextID_from_seq;
						db = s.executeUpdate(sqlp);
						if(db > 0) {
							ok = false;
							nextID_from_seq++;
						}
						else if(db == 0)
							ok = true;
					} while (!ok);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}


			try {
				ps = myMethods.connect().prepareStatement(query);
				ps.setInt(1, nextID_from_seq);
				ps.setString(2, username);
				ps.setString(3, password);

				if(checkUser(username)) {
					userErrorLabel.setText("Ez a felhasználólév már foglalt!");

					return ;
				}
				else {
					userErrorLabel.setText(null);
				}

				if(password.equals("********") || password.equals(null)) {
					passwordAgainErrorLabel.setText("Írjon be jelszót!");
					frame.requestFocusInWindow();

					return ;
				}

				if(password.equals(passwordAgain) == false) {
					passwordAgainErrorLabel.setText("Nem egyezik a két jelszó!");
					passwordField.setText(null);
					passwordField.requestFocus();
					passwordAgainField.setText(null);

					return ;
				}

				if(ps.executeUpdate() > 0) {
					JOptionPane registerSuccessfulOpt = new JOptionPane();
					registerSuccessfulOpt.showMessageDialog(frame, "Sikeres regisztráció", "Regisztráció", JOptionPane.OK_OPTION);
					new HomePage();
					frame.dispose();

				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}

		if(e.getSource() == backButton) {
			new HomePage();
			frame.dispose();

		}

		if(e.getSource() == exitButton) {
			System.exit(0);
		}
	}

	public boolean checkUser(String username) {
		boolean exists = false;
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT * FROM users WHERE username =?";

		try {
			ps = myMethods.connect().prepareStatement(query);

			ps.setString(1, username);

			rs = ps.executeQuery();

			if(rs.next())
				exists = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}
}
