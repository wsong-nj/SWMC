package org.nust.wf.util;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.io.*;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Document;


import org.nust.wf.basicstruct.*;


public class XMLparser{
	private Graph graph;
	
    public XMLparser() {
		// TODO Auto-generated constructor stub
    	graph=new Graph();
	}
	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	
	
	public Document read(File file) throws MalformedURLException,DocumentException {
          SAXReader reader = new SAXReader();
          Document document = reader.read(file);
          return document;
        }
	
	public void XMLtoGraph(File file){
		graph = new Graph();
		try{

			Document doc = read(file);
			Element rootElement = doc.getRootElement();

            
			for (Iterator<?> gIt = rootElement.elementIterator("g"); gIt
					.hasNext();) {
            	Element gElement = (Element) gIt.next();
				//Attribute att=gElement.attribute("class");
			    String s=gElement.attributeValue("class");
			    if(s.equalsIgnoreCase("graph")){
			    	for(Iterator<?> nIt = gElement.elementIterator("g"); nIt
					.hasNext();){
			    		Element nElement=(Element) nIt.next();
				    if(nElement.attributeValue("class").equalsIgnoreCase("node")){
					Node node=new Node();
					for (Iterator<?> tIt = nElement
							.elementIterator("title"); tIt.hasNext();){
						Element tElement = (Element) tIt.next();
						String str=tElement.getStringValue();
						node.setName(str);
						node.setPID(str);
						node.setType(str);
					}
					/*String text = nElement.elementText("text");
					if(text == null)
						continue;*/
					graph.addNode(node);

				}
						
			}
			    }
			}
			
	   for (Iterator<?> gIt = rootElement.elementIterator("g"); gIt
				.hasNext();) {
        	    Element gElement = (Element) gIt.next();
		       String s=gElement.attributeValue("class");
		    if(s.equalsIgnoreCase("graph")){
			    	for (Iterator<?> eIt = gElement.elementIterator("g"); eIt.hasNext();) {
			   		 Element eElement = (Element) eIt.next();		   		 
			   		 if(eElement.attributeValue("class").equalsIgnoreCase("edge")){	
			   			for (Iterator<?> tIt = eElement
			   					.elementIterator("title"); tIt.hasNext();){	
			   				Element tElement = (Element) tIt.next();
			   				String str=tElement.getStringValue();
			   				String[] s1 = str.split("-");
			   				String n1 = s1[0];
			   				String[] s2 = s1[s1.length-1].split(">");
			   				String n2 = s2[s2.length-1];
			   				for(Node n3:graph.getNodes()){
			   					if(n3.getName().equalsIgnoreCase(n1)){
			   						for(Node no:graph.getNodes()){
			   							if(no.getName().equalsIgnoreCase(n2)){
			   								Edge edge=new Edge(n3,no);
			   								edge.setType("datadependence");
			   								graph.addEdge(edge);
			   								
			   							}
			   						}
			   					}
			   				}
			   			  }
			   		
			   		  }
			   				
			   	    }
			    }


			}
			
		/*ArrayList<Node> dNodes=new ArrayList<Node>();
		for(Node n:pdg.getNodes()){
			if(pdg.getIAL(n).size()==0&&pdg.getOAL(n).size()==0){
				dNodes.add(n);
			}
		}
		for(Node n:dNodes){
			pdg.deleteNode(n);
		}*/
				
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
	}
	
	
}
	



