package passwordManagement;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomePage implements ActionListener, FocusListener, MouseListener{

	public static enum Language {
		HU, EN, ES
	}

	static MyMethods myMethods = new MyMethods();
	
	public static JFrame frame;
	public static JPanel panel;
	public static JLabel titleLabel;
	public static JLabel userLabel;
	public static JTextField userField;
	public static JLabel userErrorLabel;
	public static JSeparator userSeparator;
	public static JLabel passwordLabel;
	public static JPasswordField passwordField;
	public static JLabel passwordErrorLabel;
	public static JSeparator passwordSeparator;
	public static JButton loginButton;
	public static JLabel registerLabel;
	public static JButton exitButton;
	public static JLabel helpLabel;
	public static JLabel enLabel;
	public static JLabel esLabel;
	public static String username;
	public static String password;
	public static Language language;
	public static boolean darkMode;
	
	
	
	HomePage(){

		frame = new JFrame();
		frame.requestFocusInWindow();
		frame.setTitle("Jelszókezelõ");
		frame.setResizable(false);
		frame.setSize(700, 400);
		frame.setLayout(new BorderLayout());
		frame.setLocationRelativeTo(null);
		frame.addMouseListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		language = Language.HU;

		Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
		frame.setIconImage(icon);

		panel = new JPanel();
		panel.setLayout(null);
		//panel.setBackground(Color.white);
		frame.add(panel);



		titleLabel = new JLabel("Jelszókezelõ");
		titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		//titleLabel.setForeground(Color.black);
		titleLabel.setBounds(285, 20, 130, 20);
		panel.add(titleLabel);

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
			exitButton.removeMouseListener(this);
			exitButton.setBackground(null);
			exitButton.setForeground(null);
			SwingUtilities.updateComponentTreeUI(frame);
		}else{
			darkMode = false;
			exitButton.addMouseListener(this);
			exitButton.setBackground(Color.gray);
			exitButton.setForeground(Color.white);
			SwingUtilities.updateComponentTreeUI(frame);
		}

		userField = new JTextField("Felhasználónév");
		userField.setBounds(275, 105, 150, 30);
		userField.setFont(new Font("", Font.PLAIN, 14));
		userField.setForeground(new Color(153, 153, 153));
		userField.setOpaque(false);
		userField.setBorder(null);
		userField.addFocusListener(this);
		userField.addActionListener(this);
		panel.add(userField);
		
		userSeparator = new JSeparator();
		userSeparator.setBounds(275, 133, 150, 30);
		userSeparator.setBackground(Color.DARK_GRAY);
		panel.add(userSeparator);
		
		userErrorLabel = new JLabel("");
		userErrorLabel.setForeground(Color.red);
		userErrorLabel.setBounds(275, 131, 150, 30);
		userErrorLabel.setFont(new Font("", Font.BOLD, 10));
		panel.add(userErrorLabel);
		
		passwordField = new JPasswordField("********");
		passwordField.setBounds(275, 155, 150, 30);
		passwordField.setFont(new Font("", Font.PLAIN, 14));
		passwordField.setForeground(new Color(153, 153, 153));
		passwordField.setOpaque(false);
		passwordField.setBorder(null);
		passwordField.putClientProperty( FlatClientProperties.STYLE, "showRevealButton: true" );
		passwordField.addFocusListener(this);
		passwordField.addActionListener(this);
		panel.add(passwordField);
		
		passwordSeparator = new JSeparator();
		passwordSeparator.setBounds(275, 183, 150, 30);
		passwordSeparator.setBackground(Color.DARK_GRAY);
		panel.add(passwordSeparator);
		
		passwordErrorLabel = new JLabel("");
		passwordErrorLabel.setForeground(Color.red);
		passwordErrorLabel.setBounds(275, 181, 200, 30);
		passwordErrorLabel.setFont(new Font("", Font.BOLD, 10));
		panel.add(passwordErrorLabel);
		
		loginButton = new JButton("Bejelentkezés");
		loginButton.setBounds(300, 210, 100, 30);
		loginButton.setFocusable(false);
		loginButton.setBackground(new Color(252, 78, 78));
		loginButton.setForeground(Color.white);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.addMouseListener(this);
		loginButton.addActionListener(this);
		panel.add(loginButton);
		
		registerLabel = new JLabel("Fiók létrehozása");
		registerLabel.setBounds(312, 250, 80, 10);
		registerLabel.setFont(new Font("", Font.PLAIN, 10));
		if(!darkMode)
			registerLabel.setForeground(Color.BLUE.darker());
		else
			registerLabel.setForeground(new Color(85,148,231));
		registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		registerLabel.addMouseListener(this);
		panel.add(registerLabel);

		helpLabel = new JLabel("?");
		helpLabel.setBounds(30, 325, 10, 10);
		if(!darkMode)
			helpLabel.setForeground(Color.black);
		else
			helpLabel.setForeground(Color.white);
		helpLabel.addMouseListener(this);
		helpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(helpLabel);

		esLabel = new JLabel("ES");
		esLabel.setBounds(45, 325, 15, 10);
		if(!darkMode)
			esLabel.setForeground(Color.black);
		else
			esLabel.setForeground(Color.white);
		esLabel.addMouseListener(this);
		esLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(esLabel);


		enLabel = new JLabel("EN");
		enLabel.setBounds(65, 325, 15, 10);
		if(!darkMode)
			enLabel.setForeground(Color.black);
		else
			enLabel.setForeground(Color.white);
		enLabel.addMouseListener(this);
		enLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(enLabel);

		frame.setVisible(true);
		frame.requestFocusInWindow();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == loginButton || e.getSource() == userField || e.getSource() == passwordField) {
			PreparedStatement ps;
			ResultSet rs;
			username = userField.getText();
			password = String.valueOf(passwordField.getPassword());
			
			String query = "SELECT * FROM users WHERE username =? AND password =?";
			
			try {
				ps = myMethods.connect().prepareStatement(query);
				
				ps.setString(1, username);
				ps.setString(2, password);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					int user_id = rs.getInt("user_id");
					if(user_id == 1)
						new AdminPage();
					else
						new MainPage(user_id);
					frame.dispose();
				}
				else if((username.equals("") || username.equals("Felhasználónév")) && (password.equals("") || password.equals("********"))) {
					passwordErrorLabel.setText("Írjon be felhasználónevet és jelszót!");
					
					return ;
				}
				else {
					passwordErrorLabel.setText("Hibás felhasználónév vagy jelszó!");
					passwordField.setText(null);
					
					return ;
				}
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
		
		if(e.getSource() == exitButton) {
			System.exit(0);
		}
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
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == registerLabel) {
			new RegisterPage();
			frame.dispose();
		}
		
		if(e.getSource() == frame) {
			frame.requestFocusInWindow();
		}

		if(e.getSource() == helpLabel){
			try {
				File file = new File("UserManual_PasswordManager.pdf");
				Desktop.getDesktop().open(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if(e.getSource() == enLabel){
			language = Language.EN;
			titleLabel.setSize(200, 30);
			titleLabel.setText("Password Manager");
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
		if(e.getSource() == loginButton) {
			loginButton.setBackground(Color.red);
		}
		
		if(e.getSource() == registerLabel) {
			registerLabel.setForeground(Color.red);
		}
		
		if(e.getSource() == helpLabel) {
			helpLabel.setForeground(Color.red);
		}

		if(e.getSource() == enLabel) {
			enLabel.setForeground(Color.red);
		}

		if(e.getSource() == esLabel) {
			esLabel.setForeground(Color.red);
		}
		
		if(e.getSource() == exitButton) {
			exitButton.setBackground(Color.gray.darker());
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == loginButton) {
			loginButton.setBackground(new Color(252, 78, 78));
		}
		
		if(e.getSource() == exitButton) {
			exitButton.setBackground(Color.gray);
		}
		
		if(e.getSource() == helpLabel && !darkMode) {
			helpLabel.setForeground(Color.black);
		} else if(e.getSource() == helpLabel && darkMode) {
			helpLabel.setForeground(Color.white);
		}

		if(e.getSource() == enLabel && !darkMode) {
			enLabel.setForeground(Color.black);
		} else if(e.getSource() == enLabel && darkMode) {
			enLabel.setForeground(Color.white);
		}

		if(e.getSource() == esLabel && !darkMode) {
			esLabel.setForeground(Color.black);
		} else if(e.getSource() == esLabel && darkMode) {
			esLabel.setForeground(Color.white);
		}
		
		if(e.getSource() == registerLabel) {
			if(!darkMode)
				registerLabel.setForeground(Color.blue.darker());
			else
				registerLabel.setForeground(new Color(85,148,231));
		}
	}
	
	
	
}
