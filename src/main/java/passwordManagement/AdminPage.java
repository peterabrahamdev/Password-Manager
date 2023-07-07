package passwordManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminPage implements ActionListener, MouseListener, FocusListener{
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
    public static JButton deleteDataButton;
    public static JButton okButton;
    public static JButton megseButton;
    public static JLabel useridLabel;
    public static JTextField useridField;
    public static JLabel dataidLabel;
    public static JTextField dataidField;
    public static JButton deleteAccountButton;
    public static JButton userModificationButton;
    public static JButton dataModificationButton;
    public static JButton addUserButton;
    public static JButton addDataButton;
    public static JButton userDatas;
    public static JButton datasDatas;
    public static JMenu menu;
    public static JCheckBoxMenuItem dbItem;
    public static JMenuBar menuBar;
    public static boolean darkMode;

    AdminPage(){
        frame = new JFrame();
        frame.setTitle("Jelszókezelõ - Admin Panel");
        frame.setSize(1270, 100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX()/2 - frame.getWidth()/2;
        int y = 20;
        frame.setLocation(x, y);

        Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
        frame.setIconImage(icon);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        menu = new JMenu("Opciók");
		menu.setMnemonic(KeyEvent.VK_O);

		/*dbItem = new JCheckBoxMenuItem("Adatbázis váltás");
		KeyStroke delAccShort = KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK);
		dbItem.setAccelerator(delAccShort);
		dbItem.addActionListener(this);

		menu.add(dbItem);

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(menu);*/

        userDatas = new JButton("Fióklista");
        userDatas.setBounds(890, 15, 100, 30);
        userDatas.setFocusable(false);
        userDatas.setBackground(Color.gray);
        userDatas.setForeground(Color.white);
        userDatas.setBorder(BorderFactory.createEmptyBorder());
        userDatas.addMouseListener(this);
        userDatas.addActionListener(this);
        userDatas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(userDatas);

        datasDatas = new JButton("Adatlista");
        datasDatas.setBounds(765, 15, 100, 30);
        datasDatas.setFocusable(false);
        datasDatas.setBackground(Color.gray);
        datasDatas.setForeground(Color.white);
        datasDatas.setBorder(BorderFactory.createEmptyBorder());
        datasDatas.addMouseListener(this);
        datasDatas.addActionListener(this);
        datasDatas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(datasDatas);

        logoutButton = new JButton("Kijelentkezés");
        logoutButton.setBounds(1015, 15, 100, 30);
        logoutButton.setFocusable(false);
        logoutButton.setBackground(Color.gray);
        logoutButton.setForeground(Color.white);
        logoutButton.setBorder(BorderFactory.createEmptyBorder());
        logoutButton.addMouseListener(this);
        logoutButton.addActionListener(this);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(logoutButton);

        exitButton = new JButton("Kilépés");
        exitButton.setBounds(1140, 15, 100, 30);
        exitButton.setFocusable(false);
        exitButton.setBackground(Color.gray);
        exitButton.setForeground(Color.white);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        //exitButton.putClientProperty("JButton.buttonType", "roundRect");
        exitButton.addMouseListener(this);
        exitButton.addActionListener(this);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(exitButton);

        deleteDataButton = new JButton("Adat törlése");
        deleteDataButton.setBounds(515, 15, 100, 30);
        deleteDataButton.setFocusable(false);
        deleteDataButton.setBackground(Color.gray);
        deleteDataButton.setForeground(Color.white);
        deleteDataButton.setBorder(BorderFactory.createEmptyBorder());
        deleteDataButton.addActionListener(this);
        deleteDataButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(deleteDataButton);

        deleteAccountButton = new JButton("Fiók törlése");
        deleteAccountButton.setBounds(640, 15, 100, 30);
        deleteAccountButton.setFocusable(false);
        deleteAccountButton.setBackground(Color.gray);
        deleteAccountButton.setForeground(Color.white);
        deleteAccountButton.setBorder(BorderFactory.createEmptyBorder());
        deleteAccountButton.addMouseListener(this);
        deleteAccountButton.addActionListener(this);
        logoutButton.setBackground(Color.gray);
        logoutButton.setForeground(Color.white);
        deleteAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(deleteAccountButton);

        userModificationButton = new JButton("Fiók módosítása");
        userModificationButton.setBounds(390, 15, 100, 30);
        userModificationButton.setFocusable(false);
        userModificationButton.setBackground(Color.gray);
        userModificationButton.setForeground(Color.white);
        userModificationButton.setBorder(BorderFactory.createEmptyBorder());
        userModificationButton.addMouseListener(this);
        userModificationButton.addActionListener(this);
        userModificationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(userModificationButton);

        dataModificationButton = new JButton("Adat módosítása");
        dataModificationButton.setBounds(265, 15, 100, 30);
        dataModificationButton.setFocusable(false);
        dataModificationButton.setBackground(Color.gray);
        dataModificationButton.setForeground(Color.white);
        dataModificationButton.setBorder(BorderFactory.createEmptyBorder());
        dataModificationButton.addMouseListener(this);
        dataModificationButton.addActionListener(this);
        dataModificationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(dataModificationButton);

        addUserButton = new JButton("Fiók hozzáadása");
        addUserButton.setBounds(140, 15, 100, 30);
        addUserButton.setFocusable(false);
        addUserButton.setBackground(Color.gray);
        addUserButton.setForeground(Color.white);
        addUserButton.setBorder(BorderFactory.createEmptyBorder());
        addUserButton.addMouseListener(this);
        addUserButton.addActionListener(this);
        addUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(addUserButton);

        addDataButton = new JButton("Adat hozzáadása");
        addDataButton.setBounds(15, 15, 100, 30);
        addDataButton.setFocusable(false);
        addDataButton.setBackground(Color.gray);
        addDataButton.setForeground(Color.white);
        addDataButton.setBorder(BorderFactory.createEmptyBorder());
        addDataButton.addMouseListener(this);
        addDataButton.addActionListener(this);
        addDataButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(addDataButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == saveButton || e.getSource() == accountField || e.getSource() == usernameField || e.getSource() == passwordAccountField || e.getSource() == emailField || e.getSource() == detailsField) {

            myMethods.updateButtonOff();
            myMethods.deleteDataButtonOff();
            myMethods.setTableLines();

            String query = "INSERT INTO datas (data_id, user_id, account, username, password, email, details) VALUES(data_id_seq.nextval,?,?,?,?,?,?)";

            PreparedStatement ps;
            ResultSet rs;

            String account = accountField.getText();
            String user = usernameField.getText();
            String password = passwordAccountField.getText();
            String email = emailField.getText();
            String details = detailsField.getText();
            String date;

            if(account.equals("") && user.equals("") && password.equals("") && email.equals("") && details.equals("")) {
                return ;
            }

           /* try {
                ps = myMethods.connect().prepareStatement(query);

              //  ps.setInt(1, user_id);
                ps.setString(2, account);
                ps.setString(3, user);
                ps.setString(4, password);
                ps.setString(5, email);
                ps.setString(6, details);

                if(ps.executeUpdate() > 0) {
                    accountField.setText("");
                    usernameField.setText("");
                    passwordAccountField.setText("");
                    emailField.setText("");
                    detailsField.setText("");
                }
                String data_id = "(SELECT MAX(data_id) FROM datas)";
                String query2 = "SELECT account, username, password, email, datum, details FROM datas WHERE user_id =" + user_id + "AND data_id =" + data_id;
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
                }

                myMethods.setDeleteDatasItemState();


            } catch (SQLException e1) {

                myMethods.sm(e1.getMessage());
            }*/
        }

        if(e.getSource() == exitButton) {
            System.exit(0);
        }

        if(e.getSource() == logoutButton) {
            new HomePage();
            frame.dispose();
        }

        if(e.getSource() == deleteDataButton) {
            new AdminDeleteDataWindow();
        }

        if(e.getSource() == deleteAccountButton){
            new AdminDeleteUserWindow();
        }

        if(e.getSource() == userModificationButton){
            new AdminModifyUserWindow();
        }

        if(e.getSource() == dataModificationButton){
            new AdminModifyDataWindow();
        }

        if(e.getSource() == addUserButton){
            new AdminAddUserWindow();
        }

        if(e.getSource() == addDataButton){
            new AdminAddDataWindow();
        }

        if(e.getSource() == datasDatas){
            new DisplayDatas();
        }

        if(e.getSource() == userDatas){
           new DisplayUsers();
        }


    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
