package passwordManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminModifyUserWindow implements ActionListener, MouseListener, FocusListener {
    static MyMethods myMethods = new MyMethods();

    public static JFrame frame;
    public static JPanel panel;
    public static JLabel userLabel;
    public static JTextField usernameField;
    public static JLabel passwordLabel;
    public static JTextField passwordAccountField;
    public static JButton updateButton;
    public static JButton xButton;
    public static JLabel errorLabel;
    public static JButton backButton;
    public static JLabel useridLabel;
    public static JTextField useridField;

    AdminModifyUserWindow(){
        frame = new JFrame();
        frame.setTitle("Fiók módosítása");
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
        frame.setIconImage(icon);

        panel = new JPanel();
        panel.setLayout(null);
        //panel.setBackground(Color.white);
        frame.add(panel);

        updateButton = new JButton("Frissítés");
        updateButton.setBounds(300, 315, 100, 30);
        updateButton.setFocusable(false);
        updateButton.setBackground(new Color(252, 78, 78));
        updateButton.setForeground(Color.white);
        updateButton.setEnabled(true);
        updateButton.setBorder(BorderFactory.createEmptyBorder());
        updateButton.addMouseListener(this);
        updateButton.addActionListener(this);
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(updateButton);

        xButton = new JButton("X");
        xButton.setBounds(0, 414, 45, 30);
        xButton.setFocusable(false);
        xButton.setBackground(new Color(252, 78, 78));
        xButton.setForeground(Color.white);
        xButton.setBorder(BorderFactory.createEmptyBorder());
        xButton.addMouseListener(this);
        xButton.addActionListener(this);
        xButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(xButton);

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.red);
        errorLabel.setBounds(100, 100, 0, 30);
        errorLabel.setFont(new Font("", Font.BOLD, 10));
        panel.add(errorLabel);

        useridLabel = new JLabel("UserID:");
        useridLabel.setBounds(275, 70, 200, 30);
        useridLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        panel.add(useridLabel);

        useridField = new JTextField();
        useridField.setBounds(275, 100, 150, 25);
        useridField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        useridField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        useridField.addFocusListener(this);
        useridField.addActionListener(this);
        panel.add(useridField);

        userLabel = new JLabel("Felhasználónév:");
        userLabel.setBounds(275, 130, 200, 30);
        userLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(275, 160, 150, 25);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        usernameField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        usernameField.addFocusListener(this);
        usernameField.addActionListener(this);
        panel.add(usernameField);

        passwordLabel = new JLabel("Jelszó:");
        passwordLabel.setBounds(275, 190, 200, 25);
        passwordLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        panel.add(passwordLabel);

        passwordAccountField = new JTextField();
        passwordAccountField.setBounds(275, 220, 150, 25);
        passwordAccountField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        passwordAccountField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        passwordAccountField.addFocusListener(this);
        passwordAccountField.addActionListener(this);
        panel.add(passwordAccountField);

        backButton = new JButton("Vissza");
        backButton.setBounds(565, 315, 100, 30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.gray);
        backButton.setForeground(Color.white);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addMouseListener(this);
        backButton.addActionListener(this);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(backButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == updateButton){
            try {
                int num = Integer.parseInt(useridField.getText());
                if(num < 2){
                    JOptionPane.showMessageDialog(null, "A beírt ID csak 1-nél nagyobb pozitív egész szám lehet!");
                } else {
                    Object[] options = {"OK", "Mégse"};
                    int result = JOptionPane.showOptionDialog(frame, "Biztosan frissited a fiok bejelentkezesi adatait?", "Megerõsítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    switch (result) {
                        case 0:
                            myMethods.updateUser(num, usernameField.getText(), passwordAccountField.getText());
                            break;
                        case 1:
                            break;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "A beírt ID csak 1-nél nagyobb pozitív egész szám lehet!");
            }
        }

        if(e.getSource() == backButton){
            frame.dispose();
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
