/*
 * Zachary Williams
 * Mrs. Gallatin
 * 2nd
 * CircleDrawerRunner
 * Draws a circle centered at the user's mouse click
 * with a specified user's input radius
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainMenu extends JFrame implements ActionListener
{
	private ArrayList<JButton> buttons;
	private JPanel frame;
	private String password;
	
	public MainMenu()
	{
		setSize(300,100);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try
		{
			Scanner pass = new Scanner(new File("password.txt"));
			if(pass.hasNextLine())
				password = pass.nextLine();
			else
				password = "";
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		frame = new JPanel();
		frame.setLayout(new GridLayout(1,2));
		
		buttons = new ArrayList<JButton>();
		buttons.add(new JButton("To Admin's Area"));
		buttons.add(new JButton("Take Quiz"));
		
		for(JButton b: buttons)
		{
			frame.add(b);
			b.addActionListener(this);
		}
		
		add(frame, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
   	{
   		JButton button = (JButton)e.getSource();
  	 	setVisible(false);
  	 	if(button.getText().equals("To Admin's Area"))
   		{
   			if(password.equals(""))
   				new AdminWindow();
   			else
	   			new Password(password);
   		}
   		else
   		{
   			setVisible(false);
   			new Quiz();
   		}
   	}
}

class Password extends JFrame implements ActionListener, KeyListener
{
	private String password;
	private JPanel frame;
	
	public Password(String s)
	{
		setSize(325,105);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		password = s;
		
		frame = new JPanel();
		frame.add(new JLabel("Password: "));
		frame.add(new TextField(28));
		((TextField)frame.getComponent(1)).addKeyListener(this);
		((TextField)frame.getComponent(1)).setEchoCharacter('*');
		
		ArrayList<JButton> button = new ArrayList<JButton>();
		button.add(new JButton("Login"));
		button.add(new JButton("Back to Main Menu"));
		
		for(JButton b: button)
		{
			frame.add(b);
			b.addActionListener(this);
		}
		
		add(frame, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
   	{
   		JButton button = (JButton)e.getSource();
   		if(button.getText().equals("Login"))
   		{
   			String pass = ((TextField)frame.getComponent(1)).getText();
	   		if(pass.equals(password))
   			{
   				setVisible(false);
   				new AdminWindow();
 	  		}
   			else
   			{
   				((TextField)frame.getComponent(1)).setText("");
   			}
   		}
   		else
   		{
   			setVisible(false);
   			new MainMenu();
   		}
   		
   	}
   	
   	public void keyReleased(KeyEvent e)
   	{
   		if(e.getKeyCode() == KeyEvent.VK_ENTER)
   		{
   			String pass = ((TextField)frame.getComponent(1)).getText();
	   		if(pass.equals(password))
   			{
   				setVisible(false);
   				new AdminWindow();
 	  		}
   			else
   			{
   				((TextField)frame.getComponent(1)).setText("");
   			}
   		}
   	}
   	
   	public void keyTyped(KeyEvent e) {}
   	public void keyPressed(KeyEvent e) {}
}