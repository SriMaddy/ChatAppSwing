import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ServerRegistration implements ActionListener {
	JFrame frame;
	JPanel panel1, panel2, panel3, panel4;
	JLabel lblUsername, lblPassword, lblTitle;
	JTextField textUsername;
	JPasswordField textPassword;
	JButton btnAddServer;

	public ServerRegistration() {
		createObjects();
		addActionListeners();
		setLayout();
		addComponentsIntoPanels();
		addPanelsIntoFrame();
	}

	private void addActionListeners() {
		btnAddServer.addActionListener(this);
	}

	private void setLayout() {
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel4.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.setLayout(new GridLayout(4, 1));
	}

	private void addComponentsIntoPanels() {
		panel1.add(lblTitle);
		panel2.add(lblUsername);
		panel2.add(textUsername);
		panel3.add(lblPassword);
		panel3.add(textPassword);
		panel4.add(btnAddServer);
	}

	private void addPanelsIntoFrame() {
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
	}

	private void showFrame() {
		frame.setSize(500, 300);
		frame.setVisible(true);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createObjects() {
		frame = new JFrame("SERVER REGISTRATION");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		lblTitle = new JLabel("Enter Details");
		lblUsername = new JLabel("Username");
		lblPassword = new JLabel("Password");
		textUsername = new JTextField(30);
		textPassword = new JPasswordField(30);
		btnAddServer = new JButton("REGISTER");
	}

	public void show() {
		showFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Database db = new Database();
		String username = textUsername.getText();
		@SuppressWarnings("deprecation")
		String password = textPassword.getText();
		int result = db.registerServer(username, password);
		if (result == 1) {
			JOptionPane.showMessageDialog(frame,
					"Server Registered Successfully", "SUCCESS!",
					JOptionPane.INFORMATION_MESSAGE);
			textUsername.setText("");
			textPassword.setText("");
		} else if (result == -1) {
			JOptionPane.showMessageDialog(frame, "Database Connection is Null",
					"SQLException!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(frame,
					"Server Registration Failed. Try with new username",
					"USERNAME ALREADY EXISTS!", JOptionPane.ERROR_MESSAGE);
			textUsername.setText("");
		}
	}
}
