// Tutorials
// Radio Buttons: http://docs.oracle.com/javase/tutorial/uiswing/components/button.html


package CodeAnalyzer;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton; 
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.JRadioButton;

import CodeAnalyzer.DialogueGUI;

import CodeAnalyzer.Parser.MetricsParser;
import CodeAnalyzer.Parser.SourceParser;
import CodeAnalyzer.Rules.RulesGenerator;
import CodeAnalyzer.RulesEvaluator.Evaluator;
import CodeAnalyzer.Summary.Individual;
import CodeAnalyzer.Writer.IndividualWriter;

public class FileChooser extends JFrame 
{
	private JTextField codeExampleTextField = new JTextField(),
			targetCodeTextField = new JTextField(),
			numIterationsTextField = new JTextField(),
			numSolutionsTextField = new JTextField();
	private JTextField maxNumRulesPerSolutionTextField = new JTextField();
	
	private JRadioButton useRulesGeneratorRadioButton = new JRadioButton();
	private JRadioButton useLastRulesRadioButton = new JRadioButton();
	
	private JLabel useRulesGeneratorLabel = new JLabel("Use Rules Generator");
	private JLabel useLastRulesLabel = new JLabel("Use Last Rules");
	private JLabel codeExampleLabel = new JLabel("Code Example Path");
	private JLabel targetExampleLabel = new JLabel("Target Path");
	private JLabel numIterationsLabel = new JLabel("Num Iterations");
	private JLabel numSolutionsLabel = new JLabel("Num Solutions");
	private JLabel maxRulesPerSolutionTextField = new JLabel("Rule Depth Per Solution");
	
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
		numIterationsTextField.setEditable(true);
		
		p = new JPanel();
		p.setLayout(new GridLayout(7,2));
		
		// radio buttons
		useRulesGeneratorRadioButton.setMnemonic(KeyEvent.VK_B);
		//useRulesGeneratorRadioButton.setActionCommand(birdString);
		useRulesGeneratorRadioButton.setSelected(true);
		
		useLastRulesRadioButton.setMnemonic(KeyEvent.VK_C);
		
		//Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(useRulesGeneratorRadioButton);
	    group.add(useLastRulesRadioButton);

		
		p.add(useRulesGeneratorLabel);
		p.add(useRulesGeneratorRadioButton);
		
		p.add(useLastRulesLabel);
		p.add(useLastRulesRadioButton);
		
		p.add(codeExampleLabel);
		p.add(codeExampleTextField);

		p.add(targetExampleLabel);
		p.add(targetCodeTextField);


		p.add(numIterationsLabel);
		p.add(numIterationsTextField);

		p.add(numSolutionsLabel);
		p.add(numSolutionsTextField);

		p.add(maxRulesPerSolutionTextField);
		p.add(maxNumRulesPerSolutionTextField);

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
			MetricsParser mParser = new MetricsParser(targetCodeTextField.getText());
			SourceParser sParser = new SourceParser(codeExampleTextField.getText());
			
			Evaluator eval = new Evaluator(mParser.getFragments());
			FitnessFunction fitness = new FitnessFunction();
			
			if(useRulesGeneratorRadioButton.isSelected() && !useLastRulesRadioButton.isSelected())
			{
			
				List<Individual> best = new LinkedList<Individual>();
			
				for(int j = 0; j < Integer.parseInt(numIterationsTextField.getText()); j++)
				{
					List<Individual> solutions = new LinkedList<Individual>();
			
					for(int i = 0; i < Integer.parseInt(numSolutionsTextField.getText()); i++)
					{
						RulesGenerator gen = new RulesGenerator(Integer.parseInt(maxNumRulesPerSolutionTextField.getText()));
			
						Individual indv = new Individual();
						indv.setRules(gen.generateRules());
			
						eval.Evaluate(indv);
				
						solutions.add(indv);
					}
			
				
					best.add(fitness.getBestFitness(sParser.getSummaryItems(), solutions));
				}
			
				Individual bestest = fitness.getBestFitness(sParser.getSummaryItems(), best);
			
				RulesGenerator.setLastBestRuleset(bestest.getRules());
			
				IndividualWriter writer = new IndividualWriter();
				writer.writeToFile("results.txt", bestest);
			}
			else if(!useRulesGeneratorRadioButton.isSelected() && useLastRulesRadioButton.isSelected())//saftey check in case the radiogroup doesn't work properly
			{
				if(RulesGenerator.getLastBestRuleset() != null)
				{
					Individual bestest = new Individual();
					bestest.setRules(RulesGenerator.getLastBestRuleset());
				
					eval.Evaluate(bestest);
					
					IndividualWriter writer = new IndividualWriter();
					writer.writeToFile("results-crossvalidation.txt", bestest);
				}
			}
		}
	}
}
