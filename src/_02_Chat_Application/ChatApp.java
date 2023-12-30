package _02_Chat_Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp implements ActionListener {

	String ClientOrServer = JOptionPane.showInputDialog("Would you prefer to be the server or the client?");
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel incoming = new JLabel();
	JLabel outgoing = new JLabel();
	JButton button = new JButton();
	JTextField jtf = new JTextField();
	public void run() {
		panel.add(jtf);
		frame.add(panel);
		panel.add(outgoing);
		panel.add(incoming);
		frame.setVisible(true);
		button.setText("send");
		if (ClientOrServer.equalsIgnoreCase("Server")) {
			Server();
		}
	}

	public void Server() {
		String IPAddress = "localhost";
		int PortNum = 2963;
		Boolean b = true;
		try {
			Socket s = new Socket(IPAddress, PortNum);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			button.addActionListener(this);
		} catch (IOException e) {

		}
	}


	public void actionPerformed(ActionEvent e) {
		String submit = jtf.getText();
		outgoing.setText(outgoing + "\n" + submit);
	}

	public static void main(String[] args) {
		ChatApp ca = new ChatApp();
		ca.run();
	}
	
}
