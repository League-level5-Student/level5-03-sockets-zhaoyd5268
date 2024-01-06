package _02_Chat_Application;


import java.util.Random;

import javax.swing.JOptionPane;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {

	String ClientOrServer = JOptionPane.showInputDialog("Would you prefer to be the server or the client?");
	Server s;
	Client c;
	
	public void run() {
		if (ClientOrServer.equalsIgnoreCase("Server")) {
			Random r = new Random();
			
			s = new Server(999+r.nextInt(8881));
		
			JOptionPane.showMessageDialog(null, "We've started a server for you. Here's the IP and Port number:"
					+ " \n IP: " + s.getIP() + "\n" + " Port Number: " + s.getPortNum());
			
			s.button.addActionListener((e)-> {
				s.SendMessage(s.jtf.getText());
			});
			s.run();
		} else if (ClientOrServer.equalsIgnoreCase("Client")) {
			String joinPort = JOptionPane.showInputDialog("We'll get you right in, just type in the Port Number here.");
			int intPort = Integer.parseInt(joinPort);
			
			String joinIP = JOptionPane.showInputDialog("We'll get you right in, just type in the IP here.");
			
			c = new Client(intPort, joinIP);
			
			c.button.addActionListener((e)-> {
				c.SendMessage(c.jtf.getText());
			});
			c.run();
		}
	}



	public static void main(String[] args) {
		ChatApp ca = new ChatApp();
		ca.run();
	}


	
}
