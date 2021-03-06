package CodeAnalyzer.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;

import CodeAnalyzer.Summary.Individual;
import CodeAnalyzer.Summary.SummaryItem;

public class IndividualWriter 
{
	public void writeToFile(String file, Individual indv)
	{
		try
		{
			  // Create file 
			  FileWriter fstream = new FileWriter(file);
			  BufferedWriter out = new BufferedWriter(fstream);
			  
			  out.write("RULE: " + indv.getRuleString() + "\r\n");
			  out.write("SUMMARY: \r\n");
			  out.flush();
			  for(SummaryItem item : indv.getSummary())
			  {
				  out.write(item.toString() + "\r\n");
				  out.flush();
			  }
			  
			  //Close the output stream
			  out.close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
	}
}
