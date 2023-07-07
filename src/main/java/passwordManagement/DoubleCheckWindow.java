package passwordManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoubleCheckWindow implements ActionListener, MouseListener, WindowListener {
	
	public static JFrame frame;
	public static JPanel panel;
	public static JLabel successLabel;
	public static JButton okButton;
	public static JButton megseButton;
	public static boolean bool;
	public static JDialog d;
	public static ActionEvent e;

	public JPanel dcw() {
		
		
		
		
		//d = new JDialog(frame, "Jelszó Kezelõ");
		
		
		//d.setSize(400, 200);
		//d.setResizable(false);
		//d.setLocationRelativeTo(null);
		//d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		d.add(panel);
		
		successLabel = new JLabel("Biztos vagy benne?");
		successLabel.setBounds(110, 40, 200, 40);
		successLabel.setFont(new Font("", Font.BOLD, 18));
		successLabel.setForeground(new Color(252, 78, 78));
		panel.add(successLabel);
		
		okButton = new JButton("OK");
		okButton.setBounds(265, 115, 100, 30);
		okButton.setFocusable(false);
		okButton.setBackground(new Color(252, 78, 78));
		okButton.setBackground(Color.gray);
		okButton.setForeground(Color.white);
		okButton.setBorder(BorderFactory.createEmptyBorder());
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.addMouseListener(this);
		okButton.addActionListener((ActionListener) this);
		panel.add(okButton);
		
		megseButton = new JButton("Mégse");
		megseButton.setBounds(150, 115, 100, 30);
		megseButton.setFocusable(false);
		megseButton.setBackground(new Color(252, 78, 78));
		megseButton.setBackground(Color.gray);
		megseButton.setForeground(Color.white);
		megseButton.setBorder(BorderFactory.createEmptyBorder());
		megseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		megseButton.addMouseListener(this);
		megseButton.addActionListener((ActionListener) this);
		panel.add(megseButton);
		
		
		return panel;
		//d.setVisible(true);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == okButton) {
			okButton.setBackground(Color.gray.darker());
		}
		
		if(e.getSource() == megseButton) {
			megseButton.setBackground(Color.gray.darker());
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == okButton) {
			okButton.setBackground(Color.gray);
		}
		
		if(e.getSource() == megseButton) {
			megseButton.setBackground(Color.gray);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			bool = true;
			//frame.setEnabled(true);
			d.dispose();
			
		}
		
		if(e.getSource() == megseButton) {
			bool = false;
			//frame.setEnabled(true);
			d.dispose();
			
			
		}
	}
	
	
	
	public static boolean isBool() {
		return bool;
	}

	public static void setBool(boolean bool) {
		DoubleCheckWindow.bool = bool;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
