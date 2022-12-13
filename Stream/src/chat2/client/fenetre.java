package chat2.client;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;


public class fenetre extends JPanel {

    private JFrame frame;
    public JOptionPane J;
    public JOptionPane J2;
    public JTextField zone;
    public client c;

    private void initialize_client() {
        
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        JPanel contentPane2 = new JPanel();
        JLabel receive = new JLabel("Zone de text de receive ");
        JLabel receive2 = new JLabel(main.userInput);

        c = new client(J.showInputDialog(frame, "Entrez server Ip:"), J2.showInputDialog(frame, "Entrez un Pseudo:"));
        JLabel titre = new JLabel("Zone de text de "+c.getPseudo());
        contentPane.add(titre);
        contentPane2.add(receive);
        contentPane2.add(receive2);

        zone = new JTextField();
        frame.add(contentPane, BorderLayout.WEST);
        frame.add(contentPane2, BorderLayout.EAST);

        frame.add(zone, BorderLayout.CENTER);
        frame.setVisible(true);
        repaint(1);
    }

   

    public fenetre() {
        initialize_client();
    }
}
