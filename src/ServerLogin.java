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

public class ServerLogin implements ActionListener {
	JFrame frame;
	JPanel panel1, panel2, panel3, panel4;
	JLabel lblUsername, lblPassword, lblTitle;
	JTextField textUsername;
	JPasswordField textPassword;
	JButton btnLoginServer;

	public ServerLogin() {
		createObjects();
		addActionListeners();
		setLayout();
		addComponentsIntoPanels();
		addPanelsIntoFrame();
	}

	private void addActionListeners() {
		btnLoginServer.addActionListener(this);
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
		panel4.add(btnLoginServer);
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
		frame = new JFrame("SERVER LOGIN");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		lblTitle = new JLabel("Enter Details");
		lblUsername = new JLabel("Username");
		lblPassword = new JLabel("Password");
		textUsername = new JTextField(30);
		textPassword = new JPasswordField(30);
		btnLoginServer = new JButton("LOGIN");
	}

	public void show() {
		showFrame();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Database db = new Database();
		String username = textUsername.getText();
		String password = textPassword.getText();
		boolean result = db.checkServerCredentials(username, password);
		if (result) {
			JOptionPane.showMessageDialog(frame, "Login Successful",
					"SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
			textUsername.setText("");
			textPassword.setText("");
			frame.hide();
			ServerChatWindow serverChat = ServerChatWindow
					.getServerChatReference();
			serverChat.show();
		} else {
			JOptionPane.showMessageDialog(frame, "Login Failed", "FAILURE!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
