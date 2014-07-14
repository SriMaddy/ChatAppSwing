import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeFrame implements ActionListener {
	public JFrame homeFrame;
	JPanel panel1, panel2, panel3, panel4, panel5;
	JButton btnShowServerRegisterFrame, btnshowClientRegisterFrame,
			btnShowServerLoginFrame, btnShowClientLoginFrame;
	JLabel label;

	public HomeFrame() {
		createObjects();
		addActionListeners();
		setLayout();
		addComponentsIntoPanels();
		addPanelsIntoFrame();
		showFrame();

	}

	private void addComponentsIntoPanels() {
		panel1.add(label, JLabel.CENTER);
		panel2.add(btnShowServerRegisterFrame, JButton.CENTER);
		panel3.add(btnshowClientRegisterFrame, JButton.CENTER);
		panel4.add(btnShowServerLoginFrame, JButton.CENTER);
		panel5.add(btnShowClientLoginFrame, JButton.CENTER);
	}

	private void addPanelsIntoFrame() {
		homeFrame.add(panel1);
		homeFrame.add(panel2);
		homeFrame.add(panel3);
		homeFrame.add(panel4);
		homeFrame.add(panel5);
	}

	private void showFrame() {
		homeFrame.setSize(500, 300);
		homeFrame.setVisible(true);
		homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setLayout() {
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
		homeFrame.setLayout(new GridLayout(5, 1));
	}

	private void addActionListeners() {
		btnShowClientLoginFrame.addActionListener(this);
		btnshowClientRegisterFrame.addActionListener(this);
		btnShowServerLoginFrame.addActionListener(this);
		btnShowServerRegisterFrame.addActionListener(this);
	}

	private void createObjects() {
		homeFrame = new JFrame("CHAT HOME");
		label = new JLabel("Select Your Choice");
		btnShowServerRegisterFrame = new JButton("SERVER REGISTRATION");
		btnshowClientRegisterFrame = new JButton("CLIENT REGISTRATION");
		btnShowServerLoginFrame = new JButton("SERVER LOGIN");
		btnShowClientLoginFrame = new JButton("CLIENT LOGIN");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();

	}

	public static void main(String[] args) {
		new HomeFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnShowServerRegisterFrame) {
			ServerRegistration sr = new ServerRegistration();
			sr.show();
		} else if (e.getSource() == btnshowClientRegisterFrame) {
			ClientRegistration cr = new ClientRegistration();
			cr.show();
		} else if (e.getSource() == btnShowServerLoginFrame) {
			ServerLogin sl = new ServerLogin();
			sl.show();
		} else if (e.getSource() == btnShowClientLoginFrame) {
			ClientLogin cl = new ClientLogin();
			cl.show();
		}

	}

}
