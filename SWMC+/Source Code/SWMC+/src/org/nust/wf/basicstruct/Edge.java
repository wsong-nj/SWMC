package org.nust.wf.basicstruct;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 * 
 * 类名：Node 用途：图结构中边的基本类
 * 
 */
public class Edge implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3486802786745182175L;
	/**
	 * 边的ID
	 */
	private String ID;
	/**
	 * 边的源
	 */
	private Node Source;
	/**
	 * 边的目标
	 */
	private Node Target;
	
	/**
	 * 边的类型
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 标识边是真分支还是加分支，都不是则为null
	 */
	private String label;
	/**
	 * 边的spline
	 */
	private int[] splineX = new int[100];
	private int[] splineY = new int[100];
	/**
	 * 边的end
	 */
	private int endX;
	private int endY;
	/**
	 * 判断是否访问过此边，用于深度优先遍历图的
	 */
	private boolean visited = false;
	/**
	 * 图显的时候判断此边有没有访问到
	 */
	private boolean printVisited = false;
	/**
	 * 判断边是否是PetriNet格式
	 */
	private boolean petriNet = false;

	public boolean isDeleted  = false;
	/**
	 * 边的初始化
	 */
	public Edge() {

	}

	/**
	 * 边的初始化
	 */
	public Edge(Node Source, Node Target) {
		this.Source = Source;
		this.Target = Target;
	}

	public Edge(Node Source, Node Target, String label) {
		this.Source = Source;
		this.Target = Target;
		this.label = label;
	}

	public Node getSource() {
		return Source;
	}

	public void setSource(Node Source) {
		this.Source = Source;
	}

	public Node getTarget() {
		return Target;
	}

	public void setTarget(Node Target) {
		this.Target = Target;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int[] getSplineX() {
		return splineX;
	}

	public int[] getSplineY() {
		return splineY;
	}

	public void setSplineX(int i, int j) {
		splineX[i] = j;
	}

	public void setSplineY(int i, int j) {
		splineY[i] = j;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public boolean isVisited() {
		return visited;
	}
	
	public Boolean getVisited() {
		return visited;
	}

	public void setVisited(Boolean visited) {
		this.visited = visited;
	}

	public Boolean getprintVisited() {
		return printVisited;
	}

	public void setprintVisited(Boolean printVisited) {
		this.printVisited = printVisited;
	}

	public boolean isPetriNet() {
		return petriNet;
	}

	public void setPetriNet(boolean petriNet) {
		this.petriNet = petriNet;
	}
	

	/**
	 * 边的屏幕显示
	 */
	public void show() {
		System.out.print(Source.getID() + Target.getID() + " label: "
				+ this.label);
		System.out.println();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Edge))
			return false;
		Edge a = (Edge) obj;
		return this.getSource().equals(a.getSource())
				&& this.getTarget().equals(a.getTarget());
	}
	

	/**
	 * 边的图显
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(Source.getX() + 9, Source.getY() + 12, Target.getX() + 9,
				Target.getY());

		// 箭头另两个边的确定
		double d = Math.sqrt((Target.getY() - Source.getY())
				* (Target.getY() - Source.getY())
				+ (Target.getX() - Source.getX())
				* (Target.getX() - Source.getX()));
		double xa = Target.getX()
				+ 9
				+ 10
				* ((Source.getX() - Target.getX()) + (Source.getY() - Target
						.getY()) / 2) / d;
		double ya = Target.getY()
				+ 10
				* ((Source.getY() - Target.getY()) - (Source.getX() - Target
						.getX()) / 2) / d;
		double xb = Target.getX()
				+ 9
				+ 10
				* ((Source.getX() - Target.getX()) - (Source.getY() - Target
						.getY()) / 2) / d;
		double yb = Target.getY()
				+ 10
				* ((Source.getY() - Target.getY()) + (Source.getX() - Target
						.getX()) / 2) / d;

		g.drawLine(Target.getX() + 9, Target.getY(), (int) xa, (int) ya);
		g.drawLine(Target.getX() + 9, Target.getY(), (int) xb, (int) yb);
		g.setColor(Color.BLACK);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Source.toString()+"->"+Target.toString();
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Source.getName().hashCode()+Target.getName().hashCode();
	}
	
	public Edge clone(){
		Edge o = null;
		try{
			o=(Edge)super.clone();
			o.Source=(Node)Source.clone();
			o.Target=(Node)Target.clone();
			
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
			
		}
		return o;
	}
	
	/**
	 *  判断边是否为控制边
	 * @return 如果是控制边则返回true，否则返回false
	 */
	public boolean isControlEdge() {
		if (this.getSource().getType().equalsIgnoreCase("entry")
				|| this.getSource().getType().equalsIgnoreCase("while")
				|| this.getSource().getType().equalsIgnoreCase("if")
				|| this.getSource().getType().equalsIgnoreCase("repeatUntil")
				|| this.getSource().getType().equalsIgnoreCase("switch")) {
			return true;
		}

		return false;
	}
	
	
	
}
