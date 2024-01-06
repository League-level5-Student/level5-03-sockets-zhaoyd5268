package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Server {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	static JLabel messages = new JLabel();
	JButton button = new JButton();
	JTextField jtf = new JTextField();

	int PortNum;

	ServerSocket server;
	Socket connection;

	ObjectOutputStream oos;
	ObjectInputStream ois;

	public Server(int PortNum) {
		this.PortNum = PortNum;
	}

	public void run() {

		panel.add(messages);
		panel.add(button);
		panel.add(jtf);
		button.setText("Send");
		frame.add(panel);
		frame.setSize(new Dimension(500, 800));
		frame.setVisible(true);

		try {
			server = new ServerSocket(PortNum, 5382);
			connection = server.accept();
			oos = new ObjectOutputStream(connection.getOutputStream());
			ois = new ObjectInputStream(connection.getInputStream());
			while (connection.isConnected()) {
				try {
					String message = (String) ois.readObject();
					messages.setText(messages.getText() + "<html><br/>The Client computer said <html>" + message);
					System.out.println(message);
				} catch (EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SendMessage(String str) {
		try {
			if (oos != null) {
				oos.writeObject(str + ".");
				messages.setText(messages.getText() + "<html><br/> You said, <html>" + str);
				oos.flush();
			} else {
				JOptionPane.showMessageDialog(null, "Error");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPortNum() {
		return PortNum;
	}

	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
