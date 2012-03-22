// Animated Gif: http://www.devdaily.com/blog/post/jfc-swing/use-animated-gif-image-in-jfc-swing-application

package CodeAnalyzer;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
		p.setLayout(new GridLayout(3,1));
		p.add(titleLabel, java.awt.BorderLayout.CENTER);
		p.add(progressBar);
		
        //ImageIcon ii = new ImageIcon(this.getClass().getResource("LINDA_PENGUIN_10-27-2001.gif"));
        //imageLabel.setIcon(ii);
        //contentPane.add(imageLabel, java.awt.BorderLayout.CENTER);
		
		Container cp = getContentPane();
		cp.add(p, BorderLayout.CENTER);
	}
}
