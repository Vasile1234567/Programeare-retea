import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface from Programing Network - 4 Lab");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 500);
        JPanel PANEL = new JPanel();
        frame.add(PANEL);

        JButton buttonSMTP = new JButton("SMTP");
        JButton buttonPOP3 = new JButton("POP3");
        PANEL.add(buttonSMTP);
        PANEL.add(buttonPOP3);

        buttonSMTP.addActionListener(new SMTP_SendMailSSL());
        buttonPOP3.addActionListener(new POP3());

        frame.pack();
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.pack();
    }
}