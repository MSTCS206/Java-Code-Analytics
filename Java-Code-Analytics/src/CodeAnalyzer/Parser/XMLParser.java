package CodeAnalyzer.Parser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import CodeAnalyzer.Summary.*;
import CodeAnalyzer.Summary.SummaryItem.SummaryType;

public class XMLParser 
{
	private String xmlFile;
	
	public XMLParser(String xmlFile)
	{
		this.xmlFile = xmlFile;
	}
	
	public List<SummaryItem> getFragments()
	{
		HashMap<String, SummaryItem> summaryMapClasses = new HashMap<String, SummaryItem>();
		HashMap<String, SummaryItem> summaryMapMethods = new HashMap<String, SummaryItem>();
		
		try
		{
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(xmlFile);
			
			Element docEle = dom.getDocumentElement();
			
			NodeList nl = docEle.getElementsByTagName("Metric");
			
			if(nl != null && nl.getLength() > 0) 
			{
				for(int i = 0 ; i < nl.getLength();i++) 
				{
					Element el = (Element)nl.item(i);
					
					if(el.hasAttribute("id"))
					{
						NodeList values = el.getElementsByTagName("Value");
						
						//Weeee for Java7 allowing strings in switch cases :)
						switch(el.getAttribute("id"))
						{
							case "NOM":								
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMapClasses.containsKey(val.getAttribute("name")))
									{
										summaryMapClasses.get(val.getAttribute("name")).getMetrics().put("NOM", Double.parseDouble(val.getAttribute("value")));
										
										System.out.println("Adding to NOM " + val.getAttribute("name"));
									}
									else
									{
										SummaryItem item = new SummaryItem(val.getAttribute("name"), val.getAttribute("source"), SummaryType.Class);
										item.getMetrics().put("NOM", Double.parseDouble(val.getAttribute("value")));
										
										summaryMapClasses.put(val.getAttribute("name"), item);
										System.out.println("Creating NOM " + val.getAttribute("name"));
									}
								}
								break;
								
							case "NOF":
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMapClasses.containsKey(val.getAttribute("name")))
									{
										summaryMapClasses.get(val.getAttribute("name")).getMetrics().put("NOF", Double.parseDouble(val.getAttribute("value")));
										System.out.println("Adding to NOF " + val.getAttribute("name"));
									}
									else
									{
										SummaryItem item = new SummaryItem(val.getAttribute("name"), val.getAttribute("source"), SummaryType.Class);
										item.getMetrics().put("NOF", Double.parseDouble(val.getAttribute("value")));
										
										summaryMapClasses.put(val.getAttribute("name"), item);
										System.out.println("Creating NOF " + val.getAttribute("name"));
									}
								}
								break;
								
							case "MLOC":
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMapMethods.containsKey(val.getAttribute("name")))
									{
										summaryMapMethods.get(val.getAttribute("name")).getMetrics().put("MLOC", Double.parseDouble(val.getAttribute("value")));
										System.out.println("Adding to MLOC " + val.getAttribute("name"));
									}
									else
									{
										SummaryItem item = new SummaryItem("", val.getAttribute("name"), val.getAttribute("source"), SummaryType.Method);
										item.getMetrics().put("MLOC", Double.parseDouble(val.getAttribute("value")));
										
										summaryMapMethods.put(val.getAttribute("name"), item);
										System.out.println("Creating MLOC " + val.getAttribute("name"));
									}
								}
								break;
								
							case "DIT":
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMapClasses.containsKey(val.getAttribute("name")))
									{
										summaryMapClasses.get(val.getAttribute("name")).getMetrics().put("DIT", Double.parseDouble(val.getAttribute("value")));
										System.out.println("Adding to DIT " + val.getAttribute("name"));
									}
									else
									{
										SummaryItem item = new SummaryItem(val.getAttribute("name"), val.getAttribute("source"), SummaryType.Class);
										item.getMetrics().put("DIT", Double.parseDouble(val.getAttribute("value")));
										
										summaryMapClasses.put(val.getAttribute("name"), item);
										
										System.out.println("Creating DIT " + val.getAttribute("name"));
									}
								}
								break;
						}
					}
				}
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		List<SummaryItem> temp = new LinkedList<SummaryItem>(summaryMapClasses.values());
		temp.addAll(summaryMapMethods.values());
		return temp;
	}
}
