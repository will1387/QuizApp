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

public class Quiz extends JFrame implements MouseMotionListener, ActionListener, KeyListener
{
	private ArrayList<JButton> buttons;
	private ArrayList<String> questions;
	private JPanel westSide;
	private JPanel center;
	private JPanel eastSide;
	private JPanel northSide;
	private JPanel southSide;
	private int questionNumber;
	
	public Quiz()
	{
		setSize(1080,780);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		westSide = new JPanel();
		center = new JPanel();
		southSide = new JPanel();
		
		JToolBar leftSide = new JToolBar(JToolBar.VERTICAL);
//		leftSide.setMinimumSize(leftSide.getPreferredSize());
//		leftSide.setMaximumSize(leftSide.getPreferredSize());
		
		eastSide = new JPanel();
		eastSide.add(new JLabel());
		
		southSide.add(new JColorChooser());
		
		questions = new ArrayList<String>();
		try
		{
			open();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		leftSide.setLayout(new GridLayout((questions.size()/3)+1,1));
		buttons = new ArrayList<JButton>();
		for(int i = 1; i <= questions.size()/3; i++)
		{
			String s = "Question " + i;
			buttons.add(new JButton(s));
		}
		buttons.add(new JButton("Back"));
		for(JButton b: buttons)
		{
			leftSide.add(b);
			b.addActionListener(this);
		}
//		leftSide.setPreferredSize(new Dimension(100,300));
		westSide.add(new JScrollPane(leftSide, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		((JScrollPane)westSide.getComponent(0)).setPreferredSize(new Dimension(100,415));
		add(southSide, BorderLayout.SOUTH);
		add(westSide, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		add(eastSide, BorderLayout.EAST);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		eastSide.setVisible(false);
		if(center.getComponentCount() == 0)
		{
			center.add(new JLabel("Question: "));
			center.add(new JTextField(40));
			center.add(new JButton("Submit Answer"));
			((JButton)center.getComponent(2)).addActionListener(this);
			center.addMouseMotionListener(this);
			((JTextField)center.getComponent(1)).addKeyListener(this);
		}	
		
		JButton button = (JButton)e.getSource();
  	 	if(button.getText().equals("Submit Answer"))
  	 	{
  	 		checkAnswer();
  	 	}
  	 	else if(button.getText().equals("Back"))
  	 	{
  	 		setVisible(false);
  	 		new MainMenu();
  	 	}
  	 	else
  	 	{
  	 		questionNumber = Integer.parseInt(button.getText().substring(9));
  	 		if(questions.get(questionNumber*3-1).equals("false"))
 	 	 	{
  		 		String s = "Question: " + questions.get(questionNumber*3-3) + " ";
  	 			((JLabel)center.getComponent(0)).setText(s);
  	 		}
  	 		else
  	 		{
  	 			String s = "Question: " + questions.get(questionNumber*3-3) + "       Answer: " + questions.get(questionNumber*3-2) + " ";
  	 			((JLabel)center.getComponent(0)).setText(s);
  	 		}
  	 		center.setVisible(true);
  	 	}
  	 	
	}
	
	public void mouseDragged(MouseEvent event) 
    {
    	int x = event.getX();
     	int y = event.getY();
      	
      	Graphics g = center.getGraphics();
      	g.setColor(((JColorChooser)southSide.getComponent(0)).getColor());
		g.fillOval(x, y, 5, 5);
    }
    public void mouseMoved(MouseEvent event) {}
	
	private void checkAnswer()
	{
		eastSide.setVisible(false);
		center.setVisible(false);
		eastSide.remove(0);
		
		String userAnswer = ((JTextField)center.getComponent(1)).getText();
		if(questions.get(questionNumber*3-2).equalsIgnoreCase(userAnswer))
		{
			eastSide.add(new JLabel(new ImageIcon("check.png")));
		}
		else
		{
			eastSide.add(new JLabel(new ImageIcon("redX.jpg")));
		}
		eastSide.setVisible(true);
		((JTextField)center.getComponent(1)).setText("");
		questions.set(questionNumber*3-1, "true");
		try
   		{
   			save();
   		}
   		catch(IOException except)
   		{
   	  	 	except.printStackTrace();
   	  	}
	}
	
	public void keyReleased(KeyEvent e)
   	{
   		if(e.getKeyCode() == KeyEvent.VK_ENTER)
   		{
   			checkAnswer();
   		}
   	}
   	
   	public void keyTyped(KeyEvent e) {}
   	public void keyPressed(KeyEvent e) {}
	
	private void open() throws IOException
   	{
   		Scanner quiz = new Scanner(new File("questions.txt"));
   		Scanner title = new Scanner(new File("title.txt"));
   		while(quiz.hasNextLine())
   		{
   			questions.add(quiz.nextLine());
   		}
   		setTitle(title.nextLine());
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