import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientChatWindow implements ActionListener, KeyListener {
	JFrame frame;
	JPanel panel1, panel2, panel3, panel4;
	JLabel lblTitle;
	JTextField textMsg;
	JTextArea textarea;
	JScrollPane scrollPane;
	JButton btnSend, btnLogout;
	Database db;
	String message = "", retrivedMsg = "";
	private static ClientChatWindow clientChat = new ClientChatWindow();
	private ServerChatWindow serverChat;

	public ClientChatWindow() {
		createObjects();
		addActionListeners();
		setLayout();
		addComponentsIntoPanels();
		addPanelsIntoFrame();
		db = new Database();
		loadMessage();
	}

	public static ClientChatWindow getClientChatReference() {
		return clientChat;
	}

	public void loadMessage() {
		retrivedMsg = db.retriveMessage();
		textarea.setText("");
		textarea.append(retrivedMsg);
		retrivedMsg = "";
	}

	private void addActionListeners() {
		btnSend.addActionListener(this);
		btnLogout.addActionListener(this);
		textMsg.addKeyListener(this);
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
		panel2.add(scrollPane);
		panel3.add(textMsg);
		panel4.add(btnSend);
		panel4.add(btnLogout);
	}

	private void addPanelsIntoFrame() {
		frame.add(panel1);
		frame.add(panel2);
		frame.add(panel3);
		frame.add(panel4);
	}

	private void showFrame() {
		frame.setSize(700, 500);
		frame.setVisible(true);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createObjects() {
		frame = new JFrame("CLIENT CHAT WINDOW");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		lblTitle = new JLabel("Chat History...");
		int row = 5;
		int col = 40;
		textarea = new JTextArea(row, col);
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		scrollPane = new JScrollPane(textarea);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textMsg = new JTextField(40);
		textMsg.requestFocus();
		btnSend = new JButton("SEND");
		btnLogout = new JButton("LOG OUT");
	}

	public void show() {
		showFrame();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSend) {
			sendMsg();
		} else if (e.getSource() == btnLogout) {
			db.close();
			frame.hide();
		}
	}

	private void sendMsg() {
		message = textMsg.getText();
		db.storeMessage(message, "Client");
		retrivedMsg = "";
		loadMessage();
		serverChat = ServerChatWindow.getServerChatReference();
		serverChat.loadMessage();
		textMsg.setText("");
		textMsg.requestFocus();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			sendMsg();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
