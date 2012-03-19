package CodeAnalyzer;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

import java.io.File;
import javax.swing.JButton; 
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileChooser extends JFrame 
{
	private JTextField codeExampleTextField = new JTextField(), targetCodeTextField = new JTextField();
	
	private JButton chooseCodeExampleButton =  new JButton("Choose Code Example"),
			chooseTargetExampleButton = new JButton("Choose Target Code"),
			goButton = new JButton("Go!");
	
	public FileChooser()
	{
		JPanel p = new JPanel();
		chooseCodeExampleButton.addActionListener( new CodeExamplePressed());
		p.add(chooseCodeExampleButton);
		chooseTargetExampleButton.addActionListener(new TargetExamplePressed());
		p.add(chooseTargetExampleButton);
		
		goButton.addActionListener(new GoPressed());
		p.add(goButton);
		
		Container cp = getContentPane();
		cp.add(p, BorderLayout.SOUTH);
		targetCodeTextField.setEditable(false);
		codeExampleTextField.setEditable(false);
		p = new JPanel();
		p.setLayout(new GridLayout(2,1));
		p.add(codeExampleTextField);
		p.add(targetCodeTextField);
		cp.add(p, BorderLayout.NORTH);
	}
	
	class CodeExamplePressed implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser c = new JFileChooser();
			
			c.addChoosableFileFilter(new FileFilter() {

	            // Handles which files are allowed by filter.
	            @Override
	            public boolean accept(File f) {
	                
	                // Allow directories to be seen.
	                if (f.isDirectory()) return true;

	                // Allows files with .xml extension to be seen.
	                if (f.getName().toLowerCase().endsWith(".xml"))
	                    return true;

	                // Otherwise file is not shown.
	                return false;
	            }

	            // 'Files of Type' description
	            @Override
	            public String getDescription() {
	                return "*.xml";
	            }
	        });
			
			c.setAcceptAllFileFilterUsed(false);
			// open dialog
			int rVal = c.showOpenDialog(FileChooser.this);
			if (rVal == JFileChooser.APPROVE_OPTION)
			{
				codeExampleTextField.setText(c.getCurrentDirectory().toString()+"/"+c.getSelectedFile().getName());
			}
			if (rVal == JFileChooser.CANCEL_OPTION)
			{
				//codeExampleTextField.setText("You pressed cancel");
			}
		}
	}
	
	class TargetExamplePressed implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser c = new JFileChooser();
			// open dialog
			
			c.addChoosableFileFilter(new FileFilter() {

	            // Handles which files are allowed by filter.
	            @Override
	            public boolean accept(File f) {
	                
	                // Allow directories to be seen.
	                if (f.isDirectory()) return true;

	                // Allows files with .xml extension to be seen.
	                if (f.getName().toLowerCase().endsWith(".xml"))
	                    return true;

	                // Otherwise file is not shown.
	                return false;
	            }

	            // 'Files of Type' description
	            @Override
	            public String getDescription() {
	                return "*.xml";
	            }
	        });
			
			c.setAcceptAllFileFilterUsed(false);
			
			int rVal = c.showOpenDialog(FileChooser.this);
			if (rVal == JFileChooser.APPROVE_OPTION)
			{
				targetCodeTextField.setText(c.getCurrentDirectory().toString()+"/"+c.getSelectedFile().getName());
			}
			if (rVal == JFileChooser.CANCEL_OPTION)
			{
				//targetCodeTextField.setText("You pressed cancel");
			}
		}
	}
	class GoPressed implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.print("go button pressed\n");
		}
	}
}