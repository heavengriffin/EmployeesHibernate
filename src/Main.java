import db.Employee;
import db.HibernateUtil;
import gui.EmployeePanel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;

public class Main extends JFrame{

    public static void main(String[] args) {

        Main frame = new Main();
        EmployeePanel employeePanel = new EmployeePanel();
        frame.setContentPane(employeePanel);
        frame.setSize(500, 270);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
}
