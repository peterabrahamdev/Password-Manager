package passwordManagement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DisplayDatas {
		static MyMethods myMethods = new MyMethods();
	
		public static JFrame frame;
		public static JTextArea consoleArea;
		public static JPanel panel;
		public static JScrollPane scrollPane;
		
		DisplayDatas(){
			frame = new JFrame();
	        frame.setTitle("Adatlista");
	        frame.setSize(1800, 800);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
	        frame.setIconImage(icon);

	        panel = new JPanel();
	        panel.setLayout(new BorderLayout());
	        //panel.setBackground(Color.white);
	        frame.add(panel);
	        
	        consoleArea = new JTextArea();
	        consoleArea.setBounds(0, 0, 1600, 600);
	        consoleArea.setEditable(false);
	        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 14));
	        myMethods.readDatasData(consoleArea);
	        
	        scrollPane = new JScrollPane(consoleArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(0, 0, 1100, 400);
	        scrollPane.setBorder(BorderFactory.createEmptyBorder());
	        
	        panel.add(scrollPane, BorderLayout.CENTER);
	        
	        
	        
	        
	        frame.setVisible(true);
		}
}
