package passwordManagement;

import javax.swing.*;
import java.awt.*;

public class DisplayMetadata {
		static MyMethods myMethods = new MyMethods();

		public static JFrame frame;
		public static JTextArea consoleArea;
		public static JPanel panel;
		public static JScrollPane scrollPane;

		DisplayMetadata(){
			frame = new JFrame();
	        frame.setTitle("Metaadatok");
	        frame.setSize(800, 300);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        Image icon = Toolkit.getDefaultToolkit().getImage("myIcon.png");
	        frame.setIconImage(icon);

	        panel = new JPanel();
	        panel.setLayout(new BorderLayout());
	        //panel.setBackground(Color.white);
	        frame.add(panel);
	        
	        consoleArea = new JTextArea();
	        consoleArea.setBounds(0, 0, 800, 300);
	        consoleArea.setEditable(false);
	        consoleArea.setFont(new Font("Consolas", Font.PLAIN, 14));
	        myMethods.retrieveMetadata(consoleArea);
	        
	        scrollPane = new JScrollPane(consoleArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBounds(0, 0, 800, 300);
	        scrollPane.setBorder(BorderFactory.createEmptyBorder());
	        
	        panel.add(scrollPane, BorderLayout.CENTER);
	        
	        
	        frame.setVisible(true);
		}
}
