package org.nust.wf.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.Set;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Graph;
import org.nust.wf.basicstruct.Node;
import org.nust.wf.basicstruct.Trace;

public class WorkflowMining {	

	//public Set<String> getNodeSet(ArrayLIst<Trace> traces)
	Set<String> nodeSet;
	
	
	public Set<String> getNodeSet() {
		return nodeSet;
	}


	public void setNodeSet(Set<String> nodeSet) {
		this.nodeSet = nodeSet;
	}


	public ArrayList<Edge> WorkflowMining(ArrayList<Trace> traces){
		ArrayList<Edge> edges = new ArrayList<Edge>();
		Map<String, HashSet<String>> dp = new HashMap<String, HashSet<String>>();
		int traceSize = traces.size();
		if(traceSize>1){
			//HashSet nodeSet = new HashSet<String>();
			nodeSet = new HashSet<String>();
			for (Trace trace : traces) {
				int size = trace.getEvents().size();
				for (int i = 0; i < size - 1; i++) {
					String id = trace.getEvents().get(i);
					nodeSet.add(id);
					String idNext = trace.getEvents().get(i + 1);
					if (dp.get(id) == null) {
						dp.put(id, new HashSet<String>());
						nodeSet.add(idNext);
					} 
						dp.get(id).add(idNext);
						//System.out.println(id+"----->"+dp.get(id));
				}
			}

			/*Iterator iterator = nodeSet.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
			}*/
			HashMap<String, ArrayList<String>> casualRelations = new HashMap<String, ArrayList<String>>();
			for (Entry<String, HashSet<String>> item : dp.entrySet()) {
				for (String s : item.getValue()) {
					if (dp.get(s) == null
							|| !dp.get(s).contains(item.getKey())) {
						//System.out.println(dp.get(s));
						//jta.append(item.getKey() + "------------>" + s + "\n");
						Node n1 = new Node();
						n1.setName(item.getKey());
						Node n2 = new Node();
						n2.setName(s);
						Edge edge = new Edge();
						edge.setSource(n1);
						edge.setTarget(n2);
						edges.add(edge);
						//System.out.println(edge.getSource().getName()+"--->"+edge.getTarget().getName());
//						casualRelations.put(item.getKey(), s);
						if(casualRelations.get(item.getKey())==null){
							ArrayList<String> strList = new ArrayList<String>();
							strList.add(s);
							casualRelations.put(item.getKey(), strList);
						}
						else{
							casualRelations.get(item.getKey()).add(s);
						}
							
					}
				}
			}
		}else{
			
			Trace trace = traces.get(0);
			for(int i=0;i<trace.getEvents().size()-1;i++){
				Node n1 = new Node();
				Node n2 = new Node();
				n1.setName(trace.getEvents().get(i));
				n2.setName(trace.getEvents().get(i+1));
				Edge edge = new Edge();
				edge.setSource(n1);
				edge.setTarget(n2);
				//while(!edges.contains(edge)){
					edges.add(edge);
				//}
				
				//System.out.println(edge.getSource().getName()+"------->"+edge.getTarget().getName());
			}
			
		}
		return edges;
	}

	/*
	 * ´«µÝ¹éÔ¼
	 */
	public Graph transitiveReduction(Graph graph) {
		//System.out.println("transitiveReducton-----------------");
		Stack<Node> stack = new Stack<Node>();
		for (int i = 0; i < graph.getNodes().size(); i++) {
			ArrayList<Node> nodes=new ArrayList<Node>();
			Node node = graph.getNodes().get(i);
			node.setMark(i + 1);

	//		ArrayList<Node> OAL = mapOAL.get(node);
			ArrayList<Node> OAL = graph.getOAL(node);
			int count = OAL.size();
			if (count > 1) 
			{
				for (int j = 0; j < OAL.size(); j++) 
				{
					nodes.add(OAL.get(j));
					stack.push(OAL.get(j));
					Node nodej = OAL.get(j);
					nodej.setMark(i + 1 + graph.getNodes().size());
					
				
				}
			} 
			else 
			{
				continue;
			}
			while (stack.size() > 0 && count > 1)
			{
				Node n = stack.pop();
				if(!n.getName().equalsIgnoreCase(node.getName()))
				{
					ArrayList<Node> wOAL = graph.getOAL(n);
					if(wOAL.size()>0)
					{
    					for(Node no:wOAL)
    					{
    						if(!no.getName().equalsIgnoreCase(node.getName())
    								&&nodes.contains(no)){
    							graph.deleteEdge(node,no);
    							//System.out.println(node.getName()+"    ------>   "+no.getName());
    						}
    						else
    						{
    							
    							nodes.add(no);
    							stack.push(no);
    						}
    					}
				     }
				}
			}

		
		}
		return graph;
	} 
	/*public static void main(String[] args) {
		File file = new File("E:\\JavaProjects\\WorkflowMining\\text\\1\\1.xes");
		ArrayList<Trace> trace = new ArrayList<Trace>();
		trace = LogUtil.xesParse(file);
		WorkflowMining wf = new WorkflowMining();
		wf.WorkflowMining(trace);
		Set<String> nodeSet;
		nodeSet = wf.getNodeSet();
		
		for(int i=0;i<nodeSet.size();i++){
			System.out.println(nodeSet.iterator().next() );
		}
	}*/
}
