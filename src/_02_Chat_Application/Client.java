package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	static JLabel messages = new JLabel();
	JButton button = new JButton();
	JTextField jtf = new JTextField();
	Socket Connection;
	
	ObjectInputStream ois;
	ObjectOutputStream oos; 
	
	int JoinPort;
	String JoinIP;
	
	public Client(int port, String IP) {
		this.JoinPort = port;
		this.JoinIP = IP;
	}
	
	
	public void run() {
		panel.add(messages);
		panel.add(button);
		button.setText("Send");
		frame.add(panel);
		panel.add(jtf);
		frame.setSize(new Dimension(500,800));
		frame.setVisible(true);
		
		try {
			Connection = new Socket(JoinIP, JoinPort);
			
			ois = new ObjectInputStream(Connection.getInputStream());
			oos = new ObjectOutputStream(Connection.getOutputStream());
			
			while (Connection.isConnected()) {
				try {
					String message = (String) ois.readObject();
					messages.setText(messages.getText() + "<html><br/>The Server Host said <html>" + message);
					System.out.println(message);
					
				}catch(EOFException e) {
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
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void SendMessage(String str) {
		try {
			if (oos != null) {
				oos.writeObject(str+".");
				messages.setText(messages.getText()+ "<html><br/> You said, <html>" + str);
				oos.flush();
				
				
			} else {
				JOptionPane.showMessageDialog(null, "Error");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Sorry, we had some problems getting this to send.");
			e.printStackTrace();
		}
	}
	
	
}
