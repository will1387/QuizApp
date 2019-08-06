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

/**
 * A CircleDrawer has a box for input for the radius of the circle
 * The circle will be drawn with the center being at the user's mouse click
 */
public class AdminWindow extends JFrame implements ActionListener
{
	private ArrayList<JButton> buttons;
	private ArrayList<String> questions;
	private JPanel frame;
	private JPanel rightSide;
	private String help;
	
	/**
	 * Constructs the GUI
	 */
	public AdminWindow()
	{
		setSize(710,600);
		setTitle("Admin's Area");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rightSide = new JPanel();
		help = "";
		
		questions = new ArrayList<String>();
		try
		{
			open();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		frame = new JPanel();
		frame.setLayout(new GridLayout(11,1));
		
		buttons = new ArrayList<JButton>();
		buttons.add(new JButton("Admin's Area Help"));
		buttons.add(new JButton("Reset Password"));
		buttons.add(new JButton("Reset Quiz"));
		
		buttons.add(new JButton("Set Quiz Title"));
		buttons.add(new JButton("Add Question"));
		buttons.add(new JButton("Remove Question"));
		buttons.add(new JButton("Clear Questions"));
		buttons.add(new JButton("View Questions"));
		buttons.add(new JButton("Change Question"));
		buttons.add(new JButton("Change Answer"));
		
		buttons.add(new JButton("Back to Main Window"));
		
		for(JButton b: buttons)
		{
			frame.add(b);
			b.addActionListener(this);
		}
		
		add(frame, BorderLayout.WEST);
		add(rightSide, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
   	{ 
  	 	JButton button = (JButton)e.getSource();
  	 	if(button.getText().equals("Admin's Area Help"))
   		{
   			helpMenu();
   		}
   		else if(button.getText().equals("Reset Password"))
   		{
   			resetPasswordPanel();
   		}
   		else if(button.getText().equals("Set Password"))
   		{
   			resetPassword();
   		}
   		else if(button.getText().equals("Reset Quiz"))
   		{
   			resetQuiz();
   		}
   		else if(button.getText().equals("Set Quiz Title"))
   		{
   			setTitlePanel();
   		}
   		else if(button.getText().equals(" Set Quiz Title "))
   		{
   			setTitle();
   		}
   		else if(button.getText().equals("Add Question"))
   		{
   			addQuestionPanel();
   		}
   		else if(button.getText().equals(" Add Question "))
   		{
   			addQuestion();
   		}		
   		else if(button.getText().equals("Remove Question"))
   		{
   			removeQuestionPanel();
   		}
   		else if(button.getText().equals(" Remove Question "))
   		{
   			removeQuestion();
   		}
   		else if(button.getText().equals("Clear Questions"))
   		{
   			clearQuestions();
   		}
   		else if(button.getText().equals("View Questions"))
   		{
   			viewQuestions();
   		}
   		else if(button.getText().equals("Change Question"))
   		{
   			changeQuestionPanel();
   		}
   		else if(button.getText().equals(" Change Question "))
   		{
   			changeQuestion();
   		}
   		else if(button.getText().equals("Change Answer"))
   		{
   			changeAnswerPanel();
   		}
   		else if(button.getText().equals(" Change Answer "))
   		{
   			changeAnswer();
   		}
   		else 
   		{
   			setVisible(false);
   			new MainMenu();
   		}

   			
   	}
   	
   	private void helpMenu()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.setLayout(new FlowLayout(FlowLayout.LEFT));
   		try
   		{
   			Scanner in = new Scanner(new File("help.txt"));
   			if(help.length() == 0)
   				while(in.hasNextLine())
   				{
   					help += in.nextLine() + "\n";
   				}
   			JTextArea text = new JTextArea(help, 34, 47);
   			text.setLineWrap(true);
   			
   			rightSide.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
   		}
   		catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
   		
   		rightSide.setVisible(true);
   	}
   	
   	private void resetPasswordPanel()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.add(new JLabel("Password:                   "));
		rightSide.add(new TextField(55));
		rightSide.add(new JLabel("Confirm Password:   "));
		rightSide.add(new TextField(55));
		rightSide.add(new JButton("Set Password"));
		((TextField)rightSide.getComponent(1)).setEchoCharacter('*');
		((TextField)rightSide.getComponent(3)).setEchoCharacter('*');
		((JButton)rightSide.getComponent(4)).addActionListener(this);
   		rightSide.setVisible(true);
   	}
   	
   	private void resetPassword()
   	{
   		String pass = ((TextField)rightSide.getComponent(1)).getText();
   		String confirmPass = ((TextField)rightSide.getComponent(3)).getText();
   		rightSide.setVisible(false);
   		if(pass.equals(confirmPass))
   		{
   			try
   			{
   				PrintWriter writer = new PrintWriter(new File("password.txt"));
   				writer.println(pass);
   				writer.close();
   				if(rightSide.getComponentCount() == 5)
   					rightSide.add(new JLabel("Password has been changed."));
   				else
   					((JLabel)rightSide.getComponent(5)).setText("Password has been changed.");
   					
  	 		}
	   		catch(IOException except)
   			{
   	  		 	except.printStackTrace();
   	  		}
   		}
   		else
   		{
   			if(rightSide.getComponentCount() == 5)
   				rightSide.add(new JLabel("Passwords do not match."));
   			else
   				((JLabel)rightSide.getComponent(5)).setText("Passwords do not match.");
   		}
   		((TextField)rightSide.getComponent(1)).setText("");
   		((TextField)rightSide.getComponent(3)).setText("");
   		rightSide.setVisible(true);
   	}
   	
   	private void resetQuiz()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		for(int i = 2; i < questions.size(); i+=3)
   		{
   			questions.set(i, "false");
   		}
   		try
   		{
   			save();
   		}
   		catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
   	  	rightSide.add(new JLabel("Quiz has been reset."));
		rightSide.setVisible(true);
   	}
   	
   	private void setTitlePanel()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.add(new JLabel("New Quiz Title: "));
		rightSide.add(new JTextField(27));
		rightSide.add(new JButton(" Set Quiz Title "));
		((JButton)rightSide.getComponent(2)).addActionListener(this);
		rightSide.setVisible(true);
   	}
   	
   	private void setTitle()
   	{
   		try
   		{
   			rightSide.setVisible(false);
   			PrintWriter writer = new PrintWriter(new File("title.txt"));
   			writer.println(((JTextField)rightSide.getComponent(1)).getText());
   			writer.close();
   			((JTextField)rightSide.getComponent(1)).setText("");
   			if(rightSide.getComponentCount() == 3)
   				rightSide.add(new JLabel("New quiz title has been set."));
   			else
   				((JLabel)rightSide.getComponent(3)).setText("New quiz title has been set.");
   			rightSide.setVisible(true);	
  	 	}
	   	catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
   	}
   	
   	private void addQuestionPanel()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.add(new JLabel("Question: "));
		rightSide.add(new JTextField(40));
		rightSide.add(new JLabel("Answer:   "));
		rightSide.add(new JTextField(40));
		rightSide.add(new JButton(" Add Question "));
		((JButton)rightSide.getComponent(4)).addActionListener(this);
		rightSide.setVisible(true);
   	}
   	
   	private void addQuestion()
   	{
   		questions.add(((JTextField)rightSide.getComponent(1)).getText());
   		questions.add(((JTextField)rightSide.getComponent(3)).getText());
   		questions.add("false");
   		((JTextField)rightSide.getComponent(1)).setText("");
   		((JTextField)rightSide.getComponent(3)).setText("");
   		try
   		{
   			save();
   		}
   		catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
   		
   	}
   	
   	private void removeQuestionPanel()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.add(new JLabel("Enter the NUMBER of the question you want to remove: "));
		rightSide.add(new JTextField(10));
		rightSide.add(new JButton(" Remove Question "));
		String s = "";
		for(int i = 1; i <= questions.size(); i++)
		{
			if(i % 3 == 1)
			{
				s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
			}
			else if(i % 3 == 2)
			{
				s += "     A: " + questions.get(i-1) + "\n";
			}
		}
		JTextArea text = new JTextArea(s, 31, 47);
		rightSide.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		((JButton)rightSide.getComponent(2)).addActionListener(this);
   		rightSide.setVisible(true);
   	}
   	
   	private void removeQuestion()
   	{
   		String s = ((JTextField)rightSide.getComponent(1)).getText();
   		if(s.matches("[\\d]+"))
   		{
   			rightSide.setVisible(false);
   			int remove = Integer.parseInt(s);
   			if(remove <= questions.size()/3 + 1)
   			{
   				remove = remove*3 - 3;
   				for(int i = 0; i < 3; i++)
   				{
   					questions.remove(remove);
   				}
   			}
   			try
   			{
   				save();
   			}
   			catch(IOException except)
   			{
   		  	 	except.printStackTrace();
   	  		}
   	  		s = "";
			for(int i = 1; i <= questions.size(); i++)
			{
				if(i % 3 == 1)
				{
					s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
				}
				else if(i % 3 == 2)
				{
					s += "     A: " + questions.get(i-1) + "\n";
				}
			}
			((JTextField)rightSide.getComponent(1)).setText("");
			((JTextArea)((JScrollPane)rightSide.getComponent(3)).getViewport().getView()).setText(s);
   			rightSide.setVisible(true);
   		}
   	}
   	
   	private void clearQuestions() 
   	{
   		rightSide.setVisible(false);
   		for(int i = questions.size()-1; i >= 0; i--)
   		{
   			questions.remove(i);
   		}
   		try
   		{
   			save();
   		}
   		catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
   	}
   	
   	private void viewQuestions()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		String s = "";
		for(int i = 1; i <= questions.size(); i++)
		{
			if(i % 3 == 1)
			{
				s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
			}
			else if(i % 3 == 2)
			{
				s += "     A: " + questions.get(i-1) + "\n";
			}
		}
		JTextArea text = new JTextArea(s, 34, 47);
		rightSide.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
   		rightSide.setVisible(true);
   	}
   	
   	private void changeQuestionPanel()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.add(new JLabel("Question Number: "));
		rightSide.add(new JTextField(4));
		rightSide.add(new JLabel("New Question: "));
		rightSide.add(new JTextField(25));
		rightSide.add(new JButton(" Change Question "));
		String s = "";
		for(int i = 1; i <= questions.size(); i++)
		{
			if(i % 3 == 1)
			{
				s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
			}
			else if(i % 3 == 2)
			{
				s += "     A: " + questions.get(i-1) + "\n";
			}
		}
		JTextArea text = new JTextArea(s, 31, 47);
		rightSide.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		((JButton)rightSide.getComponent(4)).addActionListener(this);
   		rightSide.setVisible(true);
   	}
   	
   	private void changeQuestion()
   	{
   		String s = ((JTextField)rightSide.getComponent(1)).getText();
   		if(s.matches("[\\d]+"))
   		{
   			rightSide.setVisible(false);
   			int change = Integer.parseInt(s);
   			questions.set(change*3-3, ((JTextField)rightSide.getComponent(3)).getText());
   			try
   			{
   				save();
   			}
   			catch(IOException except)
   			{
   		  	 	except.printStackTrace();
   	  		}
   	  		s = "";
			for(int i = 1; i <= questions.size(); i++)
			{
				if(i % 3 == 1)
				{
					s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
				}
				else if(i % 3 == 2)
				{
					s += "     A: " + questions.get(i-1) + "\n";
				}
			}
			((JTextField)rightSide.getComponent(1)).setText("");
			((JTextField)rightSide.getComponent(3)).setText("");
			((JTextArea)((JScrollPane)rightSide.getComponent(5)).getViewport().getView()).setText(s);
   			rightSide.setVisible(true);
   		}
   	}
   	
   	private void changeAnswerPanel()
   	{
   		rightSide.setVisible(false);
   		while(rightSide.getComponentCount() > 0)
   		{
   			rightSide.remove(0);
   		}
   		rightSide.add(new JLabel("Question Number: "));
		rightSide.add(new JTextField(4));
		rightSide.add(new JLabel("New Answer: "));
		rightSide.add(new JTextField(25));
		rightSide.add(new JButton(" Change Answer "));
		String s = "";
		for(int i = 1; i <= questions.size(); i++)
		{
			if(i % 3 == 1)
			{
				s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
			}
			else if(i % 3 == 2)
			{
				s += "     A: " + questions.get(i-1) + "\n";
			}
		}
		JTextArea text = new JTextArea(s, 31, 47);
		rightSide.add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		((JButton)rightSide.getComponent(4)).addActionListener(this);
   		rightSide.setVisible(true);
   	}
   	
   	private void changeAnswer()
   	{
   		String s = ((JTextField)rightSide.getComponent(1)).getText();
   		if(s.matches("[\\d]+"))
   		{
   			rightSide.setVisible(false);
   			int change = Integer.parseInt(s);
   			questions.set(change*3-2, ((JTextField)rightSide.getComponent(3)).getText());
   			try
   			{
   				save();
   			}
   			catch(IOException except)
   			{
   		  	 	except.printStackTrace();
   	  		}
   	  		s = "";
			for(int i = 1; i <= questions.size(); i++)
			{
				if(i % 3 == 1)
				{
					s += i/3+1 + ") Q: " + questions.get(i-1) + "\n";
				}
				else if(i % 3 == 2)
				{
					s += "     A: " + questions.get(i-1) + "\n";
				}
			}
			((JTextField)rightSide.getComponent(1)).setText("");
			((JTextField)rightSide.getComponent(3)).setText("");
			((JTextArea)((JScrollPane)rightSide.getComponent(5)).getViewport().getView()).setText(s);
   			rightSide.setVisible(true);
   		}
   	}
   	
   	private void open() throws IOException
   	{
   		Scanner in = new Scanner(new File("questions.txt"));
   		while(in.hasNextLine())
   		{
   			questions.add(in.nextLine());
   		}
   	}
   	
   	private void save() throws IOException
   	{
   		PrintWriter writer = new PrintWriter(new File("questions.txt"));
   		if(questions.size() == 0)
   		{
   			writer.print("");
   		}
   		for(String s: questions)
   		{
   			writer.println(s);
   		}
   		writer.close();
   	}
}