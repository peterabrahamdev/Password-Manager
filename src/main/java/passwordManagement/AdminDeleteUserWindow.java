package passwordManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDeleteUserWindow implements ActionListener, MouseListener, FocusListener {
    static MyMethods myMethods = new MyMethods();

    public static JFrame frame;
    public static JPanel panel;
    public static JLabel useridLabel;
    public static JTextField useridField;
    public static JButton saveButton;
    public static JCheckBox deleteDatas;

    AdminDeleteUserWindow() {
        frame = new JFrame("Fiók törlése");


        frame.setSize(400, 230);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        useridLabel = new JLabel("UserID:");
        useridLabel.setBounds(45, 10, 200, 30);
        useridLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        panel.add(useridLabel);

        useridField = new JTextField();
        useridField.setBounds(45, 55, 300, 25);
        useridField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        useridField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        useridField.addFocusListener(this);
        useridField.addActionListener(this);
        panel.add(useridField);

        deleteDatas = new JCheckBox(" Adatok törlése a fiókkal együtt");
        deleteDatas.setBounds(105, 100, 200, 25);
        deleteDatas.setSelected(false);
        panel.add(deleteDatas);

        saveButton = new JButton("Törlés");
        saveButton.setBounds(150, 140, 100, 30);
        saveButton.setFocusable(false);
        saveButton.setBackground(new Color(252, 78, 78));
        saveButton.setForeground(Color.white);
        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.addMouseListener(this);
        saveButton.addActionListener(this);
        saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(saveButton);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == useridField || e.getSource() == saveButton){
            try {
                int num = Integer.parseInt(useridField.getText());
                if(num <= 1){
                    JOptionPane.showMessageDialog(null, "A beírt adat csak 1-nél nagyobb pozitív egész szám lehet!");
                } else {
                    Object[] options = {"OK", "Mégse"};
                    int result = JOptionPane.showOptionDialog(frame, "Biztosan kitörlöd a kijelölt fiókot?", "Megerõsítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    switch (result) {
                        case 0:
                            if(deleteDatas.isSelected()){
                                myMethods.deleteAllDatas(num);
                            }
                            myMethods.removeUserRow(num);
                            break;
                        case 1:
                            break;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "A beírt adat csak 1-nél nagyobb pozitív egész szám lehet!");
            }

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

