package org.nust.wf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Graph;
import org.nust.wf.basicstruct.Node;
import org.nust.wf.basicstruct.Trace;


/*
 * 
 * 基于间接先于关系的科学工作流的挖掘
 * 
 */

public class WorkflowMining2 {

	
	List<String> nodeSet;
	ArrayList<Edge> edges;
	public List<String> getNodeSet() {
		return nodeSet;
	}
	public void setNodeSet(List<String> nodeSet) {
		this.nodeSet = nodeSet;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public WorkflowMining2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WorkflowMining2(List<String> nodeSet, ArrayList<Edge> edges) {
		super();
		this.nodeSet = nodeSet;
		this.edges = edges;
	}
	public WorkflowMining2(ArrayList<Trace> traces){
		
		Trace tra = new Trace();
		tra = traces.get(0);
		nodeSet = new ArrayList<String>();
		for(int i=0;i<tra.getEvents().size();i++){
			nodeSet.add(tra.getEvents().get(i));
		}
		
		//ArrayList<E>
		
		Map<String, ArrayList<String>> directRelation = new HashMap<String, ArrayList<String>>();
		for(int i=0;i<nodeSet.size();i++){
			String nodeString = nodeSet.get(i);
			ArrayList<String> list = new ArrayList<String>();
			list.addAll(nodeSet);
			for(int j=0;j<traces.size();j++){
				Trace subTrace = new Trace();
				subTrace = traces.get(j);
				for(int k=0;!(subTrace.getEvents().get(k).equals(nodeString));k++){
					String deleteNode = subTrace.getEvents().get(k);
					for(int m=0;m<list.size();m++){
						if(deleteNode.equals(list.get(m))){
							list.remove(m);
						}
					}
				}
			}
			/*for(int n=0;n<list.size();n++){
				if(list.get(n).equals(nodeString)){
					list.remove(n);
				}
			}*/
			list.remove(0);
			directRelation.put(nodeString, list);
		}
		
		
		//构成ArrayList<Edge>的形式
		edges = new ArrayList<Edge>();
		for(String key:directRelation.keySet()){
			//System.out.print(key+":    ");
			Node node1 = new Node();
			node1.setName(key);
			for(int i=0;i<directRelation.get(key).size();i++){
				Node node2 = new Node();
				node2.setName(directRelation.get(key).get(i));
				Edge edge = new Edge();
				edge.setSource(node1);
				edge.setTarget(node2);
				edges.add(edge);
			}
			//System.out.print(directRelation.get(key).get(i)+" ");
			//System.out.println();
		}
		
	}

	/*
	 * 传递归约
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
}
