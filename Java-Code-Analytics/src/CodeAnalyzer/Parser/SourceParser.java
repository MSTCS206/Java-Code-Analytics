package CodeAnalyzer.Parser;

import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.*;
import org.w3c.dom.*;

import CodeAnalyzer.Summary.SummaryItem;
import CodeAnalyzer.Summary.SummaryItem.SummaryType;

public class SourceParser 
{
	private String xmlFile;
	
	public SourceParser(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	
	public List<SummaryItem> getSummaryItems()
	{
		List<SummaryItem> summaryItems = new LinkedList<SummaryItem>();
		
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(xmlFile);
			
			Element docEle = dom.getDocumentElement();
			
			NodeList nl = docEle.getElementsByTagName("SummaryItem");
			
			if(nl != null && nl.getLength() > 0) 
			{
				for(int i = 0 ; i < nl.getLength();i++) 
				{
					Element el = (Element)nl.item(i);
					
					if(el.hasAttribute("name") && el.hasAttribute("type") && !el.getAttribute("name").equals("") && (el.getAttribute("type").equalsIgnoreCase("class") || el.getAttribute("type").equalsIgnoreCase("method")))
					{
						if(el.getAttribute("type").equalsIgnoreCase("method"))
						{
							summaryItems.add(new SummaryItem(el.getAttribute("name"), SummaryType.Method));
						}
						else
						{
							summaryItems.add(new SummaryItem(el.getAttribute("name"), SummaryType.Class));
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		return summaryItems;
	}
}
