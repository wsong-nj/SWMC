package org.nust.wf.basicstruct;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;


/**
 * 
 * ������Node
 * ���ࣺActivity
 * ��;��ͼ�ṹ�н��Ļ�����
 *
 */
public class Node  implements Cloneable {


	/**
	 * �����첽���������ڵ���
	 */
	private int ID;
	
	/**
	 * PetriNets���ID
	 */
	private String PID;
	/**
	 * ͼ��ʱ���Ŀ��
	 */
	private int width = 20;
	
	/**
	 * �������
	 */
	private int inDegree = 0;
	
	/**
	 * ���ĳ���
	 */
	private int outDegree = 0;
	
	
	public int getInDegree() {
		return inDegree;
	}
	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}
	public int getOutDegree() {
		return outDegree;
	}
	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}
	public void inDegreeSubOne(){
		inDegree--;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * ͼ��ʱ���ĸ߶�
	 */
	private int height = 20;

	/**
	 *  �ж��Ƿ���ʹ��˽�㣬����������ȱ���ͼ��
	 */
	private boolean visited = false;

	/**
	 *  �жϴ˽���Ƿ���һ��weakly connected component��
	 */
	private boolean inCompNodeSets = false;
	
	/**
	 * ͼ�Ե�ʱ���жϴ˽����û�з��ʵ�
	 */
	private boolean printVisited = false;

	/**
	 * sequenceReduction��ʱ���жϴ˽���Ƿ񱻷��ʵ�
	 */
	private boolean sequenceReductionVisited = false;
	private boolean nodeissequenceadded = false;
	private boolean nodeisflowadded = false;
	private boolean flowReductionVisited = false;
	private boolean searchshareOALVisited=false;
	private boolean searchshareIALVisited=false;
	private boolean nodeisshareOALadded = false;
	private boolean nodeisshareIALadded = false;
	private boolean deleteVisited=false;
	private boolean findmaxsimplepathvisited=false;
	private boolean flag=false;
	/**
	 *  ����transitive reduction�ı��
	 */
	private int mark = 0;	
	private ArrayList<String> conditions = new ArrayList<String>();
	
	private ArrayList<Node> startNodes=new ArrayList<Node>();
	private ArrayList<Node> endnodes=new ArrayList<Node>();
	/**
	 * ��������X
	 */
	private int x;
	/**
	 * ��������y
	 */
	private int y;
	//�滻������ͣ�TYPE={SequenceReplaceNode��FlowReplaceNode}
	private String type;
	//�滻�������String���ʽ��SequenceReplaceNode=s<ni,nj/>;FlowReplaceNode=f<ni,nj/>
	private String CFString;
	
	//�滻����滻�Ľ����Ŀ
	private int replaceNum;
	
	private ArrayList<Node> replacednodes;
	
	public Object clone()    
    {    
        Node o=null;    
       try    
        {    
            o=(Node)super.clone();    
        }    
       catch(CloneNotSupportedException e)    
        {    
            System.out.println(e.toString());    
        } 
       o.setName(this.getName());
       o.setType(this.getType());
       o.setflag(this.getflag());
      // o.setMark(this.getMark());
       
       return o;    
    }  
	public boolean getflag(){
		return flag;
	}
	public void setflag(boolean flag){
		this.flag=flag;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCFString() {
		return CFString;
	}
    public ArrayList<Node> getstart(){
    	return this.startNodes;
    }
    public ArrayList<Node> getend(){
    	return this.endnodes;
    }
    public void setstar(ArrayList<Node> nodes){
    	this.startNodes=nodes;
    }
    public void setend(ArrayList<Node> nodes){
    	this.endnodes=nodes;
    }
    public void addStart(Node n){
    	startNodes.add(n);
    }
    public void addEnd(Node n){
    	endnodes.add(n);
    }
	public void setCFString(String cFString) {
		CFString = cFString;
	}

	public int getReplaceNum() {
		return replaceNum;
	}

	public void setReplaceNum(int replaceNum) {
		this.replaceNum = replaceNum;
	}

	public ArrayList<Node> getReplacednodes() {
		return replacednodes;
	}

	public void setReplacednodes(ArrayList<Node> replacednodes) {
		this.replacednodes = replacednodes;
	}
	
	public void setconditions(ArrayList<String> conditions){
		this.conditions=conditions;
	}
   public ArrayList<String> getconditions(){
	   return conditions;
   }
	//���빹���ͬʱ����type��type={Sequence,Flow}
	
	//����replaceNode������Ľṹ�������Sequence���ͷ���s<ni,nj/>;�����Flow���ͷ���f<ni,nj/>��
	public String toCFString() {
		
		return null;
	}
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(String name) {
		this.name=name;
		// TODO Auto-generated constructor stub
	}

	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isInCompNodeSets() {
		return inCompNodeSets;
	}

	public void setInCompNodeSets(boolean inCompNodeSets) {
		this.inCompNodeSets = inCompNodeSets;
	}

	public boolean isPrintVisited() {
		return printVisited;
	}

	public void setPrintVisited(boolean printVisited) {
		this.printVisited = printVisited;
	}

	public boolean isSequenceReductionVisited() {
		return sequenceReductionVisited;
	}

	public void setSequenceReductionVisited(boolean sequenceReductionVisited) {
		this.sequenceReductionVisited = sequenceReductionVisited;
	}
	public boolean issequenceadded() {
		return nodeissequenceadded;
	}
    public void setsearchshareOALVisited(boolean searchshareOALVisited){
    	 this.searchshareOALVisited=searchshareOALVisited;
    }
    public boolean issearchshareOALVisited(){
    	return searchshareOALVisited;
    }
    public void setsearchshareIALVisited(boolean searchshareIALVisited){
   	 this.searchshareIALVisited=searchshareIALVisited;
   }
   public boolean issearchshareIALVisited(){
   	return searchshareIALVisited;
   }
   public boolean isshareOALadded() {
		return nodeisshareOALadded;
	}

	public void setisshareOALadded(boolean nodeisshareOALadded) {
		this.nodeisshareOALadded = nodeisshareOALadded;
	}
	 public boolean isdeleteVisited() {
			return deleteVisited;
		}

		public void setisdeleteVisited(boolean deleteVisited) {
			this.deleteVisited = deleteVisited;
		}
	public boolean isshareIALadded() {
			return nodeisshareIALadded;
	}

    public void setisshareIALadded(boolean nodeisshareIALadded) {
			this.nodeisshareIALadded = nodeisshareIALadded;
	}
		
	
	public void setissequenceadded(boolean nodeissequenceadded) {
		this.nodeissequenceadded = nodeissequenceadded;
	}
	
	public boolean isflowadded() {
		return nodeisflowadded;
	}

	public void setisflowadded(boolean nodeisflowadded) {
		this.nodeisflowadded = nodeisflowadded;
	}
	
	public boolean isflowReductionVisited() {
		return flowReductionVisited;
	}
	public void setflowReductionVisited(boolean flowReductionVisited) {
		this.flowReductionVisited = flowReductionVisited;
	}
	
	public boolean isfindmaxsimplepathvisited(){
		return findmaxsimplepathvisited;
	}
	public void setfindmaxsimplepathvisited(boolean findmaxsimplepathvisited){
		this.findmaxsimplepathvisited=findmaxsimplepathvisited;
	}
	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	/**
	 *  �жϴ˽���Ƿ�Ϊ���ƽ��
	 * @return ������򷵻�true�����򷵻�false
	 */
	public boolean isControlNode() {
		if ("if".equalsIgnoreCase(this.getType())
				|| "while".equalsIgnoreCase(this.getType())
				|| "switch".equalsIgnoreCase(this.getType())
				|| "repeatUntil".equalsIgnoreCase(this.getType())
				|| "scope".equalsIgnoreCase(this.getType())
						|| "pick".equalsIgnoreCase(this.getType()		)
				) {
			return true;
		}

		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node n = (Node) obj;
		return this.getName().equals(n.getName());
	}

	public void show() {
		System.out.print(this.getName());
	}

	/**
	 * ����ͼ��
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Color c = g2d.getColor();
		Stroke s = new BasicStroke(2.0f);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(s);
		g2d.drawRect(x, y, (int) (width + width / 4), height);
		g2d.setColor(Color.RED);
		Font f = new Font("time new romans", Font.BOLD, 16);
		g2d.setFont(f);
		g2d.drawString(this.getName(), x + 7, y + 13);
		g2d.setColor(c);
	}

	/**
	 * ���ؽ����ռ������
	 * @return ���ؽ����ռ���������Rectangle����
	 */
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
