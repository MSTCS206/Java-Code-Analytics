package CodeAnalyzer;

import java.awt.BorderLayout;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;


public class DialogueGUI extends JFrame
{
	private JProgressBar progressBar = new JProgressBar();
	private JLabel titleLabel = new JLabel("Processing Rules");
	
	public DialogueGUI()
	{
		progressBar.setString("0%");

		JPanel p = new JPanel();
		p.add(progressBar);
		
		Container cp = getContentPane();
		cp.add(p, BorderLayout.CENTER);
	}
}
