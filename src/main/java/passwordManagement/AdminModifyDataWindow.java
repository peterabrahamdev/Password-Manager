package passwordManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminModifyDataWindow implements ActionListener, MouseListener, FocusListener {
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
    public static JButton backButton;
    public static JButton deleteDataButton;
    public static JButton okButton;
    public static JButton megseButton;
    public static JLabel useridLabel;
    public static JTextField useridField;
    public static JLabel dataidLabel;
    public static JTextField dataidField;

    AdminModifyDataWindow(){
        frame = new JFrame();
        frame.setTitle("Adat módosítása");
        frame.setSize(900, 530);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
        frame.setIconImage(icon);

        panel = new JPanel();
        panel.setLayout(null);
        //panel.setBackground(Color.white);
        frame.add(panel);

        fillPanel = new JPanel();
        fillPanel.setBounds(300, 0, 300, 500);
        fillPanel.setLayout(null);
        //fillPanel.setBackground(Color.white);
        //fillPanel.setBorder(BorderFactory.createDashedBorder(Color.black));
        panel.add(fillPanel);

        updateButton = new JButton("Frissítés");
        updateButton.setBounds(130, 444, 100, 30);
        updateButton.setFocusable(false);
        updateButton.setBackground(new Color(252, 78, 78));
        updateButton.setForeground(Color.white);
        updateButton.setEnabled(true);
        updateButton.setBorder(BorderFactory.createEmptyBorder());
        updateButton.addMouseListener(this);
        updateButton.addActionListener(this);
        updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fillPanel.add(updateButton);

        xButton = new JButton("X");
        xButton.setBounds(70, 444, 40, 30);
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
        errorLabel.setBounds(100, 100, 0, 30);
        errorLabel.setFont(new Font("", Font.BOLD, 10));
        panel.add(errorLabel);

        useridLabel = new JLabel("UserID:");
        useridLabel.setBounds(0, 0, 200, 30);
        useridLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(useridLabel);

        useridField = new JTextField();
        useridField.setBounds(0, 30, 300, 25);
        useridField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        useridField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        useridField.addFocusListener(this);
        useridField.addActionListener(this);
        fillPanel.add(useridField);

        dataidLabel = new JLabel("DataID:");
        dataidLabel.setBounds(0, 60, 200, 30);
        dataidLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(dataidLabel);

        dataidField = new JTextField();
        dataidField.setBounds(0, 90, 300, 25);
        dataidField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        dataidField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        dataidField.addFocusListener(this);
        dataidField.addActionListener(this);
        fillPanel.add(dataidField);

        accountLabel = new JLabel("Fiók:");
        accountLabel.setBounds(0, 120, 200, 30);
        accountLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(accountLabel);

        accountField = new JTextField();
        accountField.setBounds(0, 150, 300, 25);
        accountField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        accountField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        accountField.addFocusListener(this);
        accountField.addActionListener(this);
        fillPanel.add(accountField);

        userLabel = new JLabel("Felhasználónév:");
        userLabel.setBounds(0, 180, 200, 30);
        userLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(0, 210, 300, 25);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        usernameField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        usernameField.addFocusListener(this);
        usernameField.addActionListener(this);
        fillPanel.add(usernameField);

        passwordLabel = new JLabel("Jelszó:");
        passwordLabel.setBounds(0, 240, 200, 30);
        passwordLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(passwordLabel);

        passwordAccountField = new JTextField();
        passwordAccountField.setBounds(0, 270, 300, 25);
        passwordAccountField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        passwordAccountField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        passwordAccountField.addFocusListener(this);
        passwordAccountField.addActionListener(this);
        fillPanel.add(passwordAccountField);

        emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(0, 300, 200, 30);
        emailLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(0, 330, 300, 25);
        emailField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        emailField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        emailField.addFocusListener(this);
        emailField.addActionListener(this);
        fillPanel.add(emailField);

        detailsLabel = new JLabel("Megjegyzés:");
        detailsLabel.setBounds(0, 360, 200, 30);
        detailsLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        fillPanel.add(detailsLabel);

        detailsField = new JTextField();
        detailsField.setBounds(0, 390, 300, 25);
        detailsField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        detailsField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        detailsField.addFocusListener(this);
        detailsField.addActionListener(this);
        fillPanel.add(detailsField);

        backButton = new JButton("Vissza");
        backButton.setBounds(765, 445, 100, 30);
        backButton.setFocusable(false);
        backButton.setBackground(Color.gray);
        backButton.setForeground(Color.white);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        //backButton.putClientProperty("JButton.buttonType", "roundRect");
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
                int num1 = Integer.parseInt(useridField.getText());
                int num2 = Integer.parseInt(dataidField.getText());
                if(num1 < 2){
                    JOptionPane.showMessageDialog(null, "A beírt UserID csak 1-nél nagyobb pozitív egész szám lehet!");
                } else {
                    if(num2 < 1)
                        JOptionPane.showMessageDialog(null, "A beírt DataID csak 1-nél nagyobb pozitív egész szám lehet!");
                    else{
                        Object[] options = {"OK", "Mégse"};
                        int result = JOptionPane.showOptionDialog(frame, "Biztosan frissited az adatot?", "Megerõsítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                        switch (result) {
                            case 0:
                                myMethods.updateRow(num1, num2, accountField, usernameField, passwordAccountField, emailField, detailsField);
                                break;
                            case 1:
                                break;
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID csak szám lehet!");
            }
        }

        if(e.getSource() == backButton){
            frame.dispose();
        }

        if(e.getSource() == xButton) {
            accountField.setText("");
            usernameField.setText("");
            passwordAccountField.setText("");
            emailField.setText("");
            detailsField.setText("");
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
