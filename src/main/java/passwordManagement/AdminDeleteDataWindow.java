package passwordManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDeleteDataWindow implements ActionListener, MouseListener, FocusListener {
    static MyMethods myMethods = new MyMethods();

    public static JFrame frame;
    public static JPanel panel;
    public static JLabel dataidLabel;
    public static JTextField dataidField;
    public static JButton saveButton;

    AdminDeleteDataWindow() {
        frame = new JFrame("Adat törlése");

        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        dataidLabel = new JLabel("DataID:");
        dataidLabel.setBounds(45, 10, 200, 30);
        dataidLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
        panel.add(dataidLabel);

        dataidField = new JTextField();
        dataidField.setBounds(45, 55, 300, 25);
        dataidField.setBorder(BorderFactory.createLineBorder(Color.gray.brighter()));
        dataidField.setFont(new Font("Poppins Light", Font.PLAIN, 14));
        dataidField.addFocusListener(this);
        dataidField.addActionListener(this);
        panel.add(dataidField);

        saveButton = new JButton("Törlés");
        saveButton.setBounds(150, 110, 100, 30);
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
        if(e.getSource() == dataidField || e.getSource() == saveButton){
            try {
                int num = Integer.parseInt(dataidField.getText());
                if (num < 1) {
                    JOptionPane.showMessageDialog(null, "A beírt adat csak pozitív egész szám lehet!");
                } else {
                    Object[] options = {"OK", "Mégse"};
                    int result = JOptionPane.showOptionDialog(frame, "Biztosan kitörlöd a kijelölt adatot?", "Megerõsítés", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    switch (result) {
                        case 0:
                            myMethods.removeDataRow(num);
                            break;
                        case 1:
                            break;
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "A beírt adat csak pozitív egész szám lehet!");
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

