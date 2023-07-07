package passwordManagement;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main(String[] args) {

		MyMethods myMethods = new MyMethods();
		
		myMethods.reg();
		if(!myMethods.loadLookAndFeel()) {
			try {
				UIManager.setLookAndFeel(new FlatIntelliJLaf());
			} catch (Exception e) {
				e.getMessage();
			}
		} else {
			try {
				UIManager.setLookAndFeel(new FlatDarculaLaf());
			} catch (Exception e) {
				e.getMessage();
			}
		}
		UIManager.put("OptionPane.minimumSize",new Dimension(350,125));

		//String sqlp = "DELETE FROM datas";
		//String sqlp = "DROP TABLE users";
		//String sqlp = "CREATE TABLE users(user_id int primary key, username varchar(30), password varchar(50))";
		//String sqlp = "INSERT INTO users VALUES(1, 'admin', 'admin')";
		//String sqlp = "DELETE FROM USERS WHERE user_id = 68";
		//String sqlp = "CREATE TABLE datas(data_id int primary key, user_id int not null, account varchar(100), username varchar(100), password varchar(100), email varchar(100), datum timestamp default sysdate, details varchar(150), foreign key(user_id) references users(user_id))";
		//String sqlp = "DROP SEQUENCE data_id_seq";
		//String sqlp = "CREATE SEQUENCE id_seq START WITH 1 INCREMENT BY 1";
		//String sqlp = "CREATE SEQUENCE data_id_seq START WITH 1 INCREMENT BY 1";
		//String sqlp = "CREATE OR REPLACE TRIGGER id_seq_trigger BEFORE INSERT ON users FOR EACH ROW BEGIN SELECT id_seq.nextval INTO :new.userid FROM dual";
		//String sqlp = "CREATE OR REPLACE TRIGGER data_id_seq_trigger BEFORE INSERT ON datas FOR EACH ROW BEGIN SELECT data_id_seq.nextval INTO :new.data_id FROM dual";
		//String sqlp = "ALTER TABLE datas DROP COLUMN datum";
		//String sqlp = "DROP TRIGGER data_id_seq_trigger";
		//String sqlp = "INSERT INTO datas(data_id, user_id, account, username, password, email, details) VALUES(1, 1, 'szoveg', 'szoveg', 'szoveg', 'szoveg', 'szoveg')";
		//String sqlp = "select * from user_errors where type = 'TRIGGER' and name = 'data_id_seq_trigger'";
		
		//myMethods.commandExec(sqlp);
		
		//System.out.println(LocalDate.now());
		
		//myMethods.readDatasData();
		//myMethods.readUsersData();
		new HomePage();
		//new AdminPage();
		//new DoubleCheckWindow();
		//new RegisterSuccessfulWindow();
		//new MainPage(66);
	}

}
