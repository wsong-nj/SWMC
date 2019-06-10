package org.nust.wf.util;

import java.io.File;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Graph;
import org.nust.wf.basicstruct.Node;
import org.nust.wf.graphviz.GraphViz;

public class GraphVizUtil {
	public static File saveAsTemp(Graph g){
		  GraphViz gv = new GraphViz();
	      gv.addln(gv.start_graph());
	      for(Node n:g.getNodes()){
	    	  gv.add(n.getName()+"[shape=box]");
	      }
	      for(Edge e:g.getEdges()){
	    	  gv.addln(e.getSource().getName()+"->"+e.getTarget().getName());
	      }
	      gv.addln(gv.end_graph());
//	      System.out.println(gv.getDotSource());
	      
	      String type = "png";
	      //String type = "svg";
	      File out = new File("C:/tmp/"+g.hashCode()+"." + type);   
	      
	      gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	      
	      return out;
		
	}
	public static void saveAsSVG(Graph g,File file){
		GraphViz gv = new GraphViz();
	      gv.addln(gv.start_graph());
	      for(Node n:g.getNodes()){
	    	  gv.add(n.getName()+"[shape=box]");
	      }
	      for(Edge e:g.getEdges()){
	    	  gv.addln(e.getSource().getName()+"->"+e.getTarget().getName());
	      }
	      gv.addln(gv.end_graph());
//	      System.out.println(gv.getDotSource());
	      
	      String type = "svg";
	     // String type = "png";
	      File out = new File(file.getPath()+".svg" );   
	     // File out = new File(file.getPath()+".png" );   
	      gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
}
