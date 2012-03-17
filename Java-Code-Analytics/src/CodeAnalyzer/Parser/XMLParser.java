package CodeAnalyzer.Parser;

import java.util.HashMap;
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
		try
		{
			HashMap<String, SummaryItem> summaryMap = new HashMap<String, SummaryItem>();
			
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
						NodeList values = ((Element)el.getFirstChild()).getElementsByTagName("Value");
						
						//Weeee for Java7 allowing strings in switch cases :)
						switch(el.getAttribute("id"))
						{
							case "NOM":								
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMap.containsKey(val.getAttribute("name")))
									{
										summaryMap.get(val.getAttribute("name")).getMetrics().put("NOM", Double.parseDouble(val.getAttribute("value")));
									}
									else
									{
										SummaryItem item = new SummaryItem(val.getAttribute("name"), SummaryType.Class);
										item.getMetrics().put("NOM", Double.parseDouble(val.getAttribute("value")));
										
										summaryMap.put(val.getAttribute("name"), item);
									}
								}
								break;
								
							case "NOF":
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMap.containsKey(val.getAttribute("name")))
									{
										summaryMap.get(val.getAttribute("name")).getMetrics().put("NOF", Double.parseDouble(val.getAttribute("value")));
									}
									else
									{
										SummaryItem item = new SummaryItem(val.getAttribute("name"), SummaryType.Class);
										item.getMetrics().put("NOF", Double.parseDouble(val.getAttribute("value")));
										
										summaryMap.put(val.getAttribute("name"), item);
									}
								}
								break;
								
							case "MLOC":
								for(int j = 0; j < values.getLength(); j++)
								{
									Element val = (Element)values.item(j);
									
									if(summaryMap.containsKey(val.getAttribute("name")))
									{
										summaryMap.get(val.getAttribute("name")).getMetrics().put("MLOC", Double.parseDouble(val.getAttribute("value")));
									}
									else
									{
										SummaryItem item = new SummaryItem("", val.getAttribute("name"), SummaryType.Method);
										item.getMetrics().put("MLOC", Double.parseDouble(val.getAttribute("value")));
										
										summaryMap.put(val.getAttribute("name"), item);
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
			
		}
		
		return null;
	}
}
