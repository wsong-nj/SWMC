package org.nust.wf.basicstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Node;

/**
 * 
 * ������Graph
 * ��;��ͼ�Ļ�����
 *
 */
public class Graph implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6712637811832637633L;
	
	protected int id;


	/**
	 * ͼ�к��еĽ�㼯��
	 */
	protected ArrayList<Node> nodes;
	/**
	 * ͼ�к��еı߼���
	 */
	protected ArrayList<Edge> edges;
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	public  ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	/**
	 * ���PDG��ʼ���
	 * @return ����PDG��ʼ���Node����
	 */
	public Node getEntryNode() {
		Node node = new Node();
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getName().equalsIgnoreCase("entry")) {
				node = nodes.get(i);
			}
		}
		return node;
	}
	
	
	/**
	 * ����Ŀ����
	 * @param node Ŀ����
	 */
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	/**
	 * ɾ��Ŀ����
	 * @param node Ŀ����
	 */
	public void deleteNode(Node node) {
		nodes.remove(node);
	}
	
	/**
	 * ����Ŀ���
	 * @param edge Ŀ���
	 */
	public void addEdge(Edge edge) {
		edges.add(edge);
//		Node source = Edge.getSource();
//		Node target = Edge.getTarget();
//		source.setOutdegree(source.getOutdegree()+1);
//		target.setIndegree(target.getIndegree()+1);
	}
	
	public void addEdge(Node n1,Node n2) {
		Edge a= new Edge();
		a.setSource(n1);
		a.setTarget(n2);
		edges.add(a);
//		n1.setOutdegree(n1.getOutdegree()+1);
//		n2.setIndegree(n2.getIndegree()+1);
	}
	/**
	 * ɾ����
	 * @param edge Ŀ���
	 */
	public void deleteEdge(Edge edge) {
		edges.remove(edge);
//		Node source = Edge.getSource();
//		Node target = Edge.getTarget();
//		source.setOutdegree(source.getOutdegree()-1);
//		target.setIndegree(target.getIndegree()-1);
	}
	
	/**
	 * ɾ����n1��n2�����Ŀ���
	 * @param n1 ����ߵĵ�һ�����
	 * @param n2 ����ߵĵڶ������
	 */
	public void deleteEdge(Node n1,Node n2) {
		deleteEdge(new Edge(n1,n2));
//		n1.setOutdegree(n1.getOutdegree()-1);
//		n2.setIndegree(n2.getIndegree()-1);
	}

	//��ͼ��ͨ��id��ȡNodeԪ��
	public Node getNodeById(String id){
		//System.out.println(id);
		for(Node node:nodes){
			//System.out.println(node.getName());
			if(node.getName().equalsIgnoreCase(id))
				return node;
		}
		return null;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nodes.toString()+"|"+edges.toString();
	}

	@Override
	protected Graph clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Graph o = null;
		o = (Graph)super.clone();
		for(Node node:o.getNodes())
			node.clone();
		for(Edge edge:o.getEdges())
			edge.clone();
		return o;
	}
	
	
	/**
	 * �õ���Ŀ����ΪԴ�������б�
	 * @param node Ŀ����
	 * @return ����Ŀ����ΪԴ�������бߣ���������ArrayList<Edge>��
	 */
	public ArrayList<Edge> getAllEdges(Node node) {
		ArrayList<Edge> allEdges = new ArrayList<Edge>();
		for(int i=0;i<edges.size();i++) {
			Edge edge = edges.get(i);
			if(node.equals(edge.getSource())) {
				allEdges.add(edge);
			}
		}
		return allEdges;
	}
	
	/**
	 * ���Graph���ɽ��n1��n2���ɵı�
	 * @param n1 ����ߵĵ�һ�����
	 * @param n2 ����ߵĵڶ������
	 * @return ������n1��n2����ı�Edge����
	 */
	public Edge getEdge(Node n1, Node n2) {
		for (int i = 0; i < edges.size(); i++) {
			Edge e = edges.get(i);
			if (e.equals(new Edge(n1, n2))) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * �õ�Ŀ����ĺ�̽�㼯��
	 * @param node Ŀ����
	 * @return ����Ŀ��������к�̽�㼯�ϣ������ArrayList<Node>��
	 */
	public ArrayList<Node> getOAL(Node node) {
		ArrayList<Node> OAL = new ArrayList<Node>();
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			if (node.getName().equalsIgnoreCase(edge.getSource().getName())) {
				OAL.add(edge.getTarget());
			}
		}
		return OAL;
	}
	
	/**
	 * �õ�Ŀ�����ǰ����㼯��
	 * @param node Ŀ����
	 * @return ����Ŀ���������ǰ����㼯�ϣ������ArrayList<Node>��
	 */
	public ArrayList<Node> getIAL(Node node) {
		ArrayList<Node> OAL = new ArrayList<Node>();
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			if (node.getName().equalsIgnoreCase(edge.getTarget().getName())) {
				OAL.add(edge.getSource());
			}
		}
		return OAL;
	}	
	
	
	/*
	 * ɾ���ظ���
	 */
	public ArrayList<Edge> deleteReplicateEdges(ArrayList<Edge> edge){
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		for(int i=0;i<edge.size();i++){
			while(!edges.contains(edge.get(i))){
				edges.add(edge.get(i));
			}
		}
		return edges;
	}
	
	
	
	/*
	 * ���ݹ�Լ
	 */
	public void transitiveReduction(Graph graph) {
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
	} 
}
