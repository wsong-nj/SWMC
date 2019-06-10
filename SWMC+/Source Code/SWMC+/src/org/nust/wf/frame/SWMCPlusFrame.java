package org.nust.wf.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Graph;
import org.nust.wf.basicstruct.Node;
import org.nust.wf.basicstruct.Trace;
import org.nust.wf.util.LogUtil;
import org.nust.wf.util.WorkflowMining2;
import org.nust.wf.util.XMLparser;
import org.nust.wf.view.GraphViewer;


public class SWMCPlusFrame  extends JFrame implements ActionListener{
	JButton openButton,miningButton,showPicButton;
	JTextField fileNameTXT;
	JTextArea jta;
	JPanel panel;
	String FilePath;
	ArrayList<Edge> testEdges;	//存储挖掘到的边
	ArrayList<Trace> traces;	//存储日志
	Set<String> nodeSet;		//存储节点集合
	Graph graph;				//挖掘到的图
	
	/**
	 * SWMC的挖掘
	 */
	public SWMCPlusFrame(){
		JPanel p = new JPanel(new GridLayout(1, 4));
		JPanel p1 = new JPanel();
		p1.add(new JLabel("LOG:"));
		fileNameTXT = new JTextField(20);
		//fileNameTXT.setText("E:\\JavaProjects\\WorkflowMining\\workflow50\\50\\50_4_A.xes");//一份的时候是XX.xes;2份的是
		p1.add(fileNameTXT);
		openButton = new JButton("open");
		openButton.addActionListener(this);
		p1.add(openButton);
		
		miningButton = new JButton("Mining");
		showPicButton = new JButton("DAG");
		miningButton.addActionListener(this);
		showPicButton.addActionListener(this);
		p1.add(miningButton);
		p1.add(showPicButton);
		p.add(p1);
		add(p,BorderLayout.NORTH);
		
		
		jta = new JTextArea(15,50);
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp,BorderLayout.CENTER);
		add(jta,BorderLayout.CENTER);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==openButton){
			JFileChooser jfc = new JFileChooser(".");
			jfc.setFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return null;
				}
				
				@Override
				public boolean accept(File f) {
					if(f.getName().endsWith(".xes")||f.isDirectory()){
						return true;
					}
					return false;
				}
			});
			jfc.setVisible(true);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int state = jfc.showOpenDialog(SWMCPlusFrame.this);
			if(state == JFileChooser.APPROVE_OPTION){
				File file = jfc.getSelectedFile();
				FilePath = file.getAbsolutePath();
				fileNameTXT.setText(FilePath);
				
			}
		}else if(e.getSource()==miningButton){
			File file = new File(fileNameTXT.getText());
			ArrayList<Trace> trace = new ArrayList<Trace>();
			trace = LogUtil.xesParse(file);

			jta.append("LOG:  "+FilePath+"   Mining Success！！！");
			traces = new ArrayList<Trace>();
			for(int i=0;i<trace.size();i++){
				traces.add(trace.get(i));
			}
			long start = System.nanoTime();
			    
			WorkflowMining2 workflow = new WorkflowMining2(traces);
			testEdges = new ArrayList<Edge>();
			testEdges.addAll(workflow.getEdges());
			nodeSet = new HashSet<String>();
			for(int i=0;i<trace.get(0).getEvents().size();i++){
				nodeSet.add(trace.get(0).getEvents().get(i));
			}
			graph = new Graph();
			for(String s:nodeSet){
				graph.addNode(new Node(s));
			}
			for(int i=0;i<testEdges.size();i++){
				graph.addEdge(testEdges.get(i));
			}
			graph = workflow.transitiveReduction(graph);
			testEdges.clear();
			testEdges.addAll(graph.getEdges());
			long end = System.nanoTime();
			jta.append("          Mining Time:"+(end-start)/1000000.0+"ms"+"\n");
		}else if(e.getSource()==showPicButton){
			GraphViewer frame = new GraphViewer(graph);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame frame = new SWMCPlusFrame();
				frame.setTitle("SWMC+");
				frame.setSize(800,400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}
