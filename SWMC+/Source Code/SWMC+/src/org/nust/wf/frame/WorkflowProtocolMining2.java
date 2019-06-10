package org.nust.wf.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
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
import javax.xml.crypto.NodeSetData;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Graph;
import org.nust.wf.basicstruct.Node;
import org.nust.wf.basicstruct.Trace;
import org.nust.wf.util.LogUtil;
import org.nust.wf.util.MiningEffective;
import org.nust.wf.util.WorkflowMining;
import org.nust.wf.util.WorkflowMining2;
import org.nust.wf.view.GraphViewer;



public class WorkflowProtocolMining2 extends JFrame implements ActionListener{

	JTextField jtf1,jtf2;
	JTextArea jta;
	JButton open,open2,mining1,mining2,effective1,effective2,sourceDAG;
	Graph graph1,graph2;
	Set<String> nodeSet1;
	Set<String> nodeSet2;
	ArrayList<Edge> testEdges1;
	ArrayList<Edge> testEdges2;

	List<String> lists;
	
	public WorkflowProtocolMining2(){
		JPanel panel = new JPanel(new GridLayout(3, 4));
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Log file:"));
		jtf1 = new JTextField(30);
		p1.add(jtf1);
		open = new JButton("open");
		open.addActionListener(this);
		p1.add(open);
		panel.add(p1);
		
		JPanel p2 = new JPanel();
		p2.add(new JLabel("Communicating activities:"));
		jtf2 = new JTextField(30);
		p2.add(jtf2);
		open2 = new JButton("open");
		open2.addActionListener(this);
		//p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.add(open2);
		panel.add(p2);
		
		JPanel p3 = new JPanel();
		sourceDAG = new JButton("Source DAG");
		sourceDAG.addActionListener(this);
		p3.add(sourceDAG);
		mining1 = new JButton("Algorithm1 Mining");
		mining1.addActionListener(this);
		p3.add(mining1);
		effective1 = new JButton("DAG1");
		effective1.addActionListener(this);
		p3.add(effective1);
		mining2 = new JButton("Algorithm2 Mining");
		mining2.addActionListener(this);
		p3.add(mining2);
		effective2 = new JButton("DAG2");
		effective2.addActionListener(this);
		p3.add(effective2);
		panel.add(p3);
		
		add(panel,BorderLayout.NORTH);
		
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		add(jsp,BorderLayout.CENTER);
		add(jta,BorderLayout.CENTER);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==open){
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
			int state = jfc.showOpenDialog(WorkflowProtocolMining2.this);
			if(state == JFileChooser.APPROVE_OPTION){
				File file = jfc.getSelectedFile();
				jtf1.setText(file.getAbsolutePath());
			}
		}else if(e.getSource()==open2){
			JFileChooser jfc = new JFileChooser(".");
			jfc.setFileFilter(new FileFilter() {
				@Override
				public String getDescription() {
					return null;
				}
				
				@Override
				public boolean accept(File f) {
					if(f.getName().endsWith(".txt")||f.isDirectory()){
						return true;
					}
					return false;
				}
			});
			jfc.setVisible(true);
			jfc.setMultiSelectionEnabled(false);
			jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int state = jfc.showOpenDialog(WorkflowProtocolMining2.this);
			if(state == JFileChooser.APPROVE_OPTION){
				File file = jfc.getSelectedFile();
				jtf2.setText(file.getAbsolutePath());
			}
			lists = new ArrayList<String>();
			try {
				FileInputStream is = new FileInputStream(new File(jtf2.getText()));
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader bw = new BufferedReader(isr);
				
				String line = null;
				jta.append("Communicating activities:"+"\n");
				while((line = bw.readLine())!= null){
					lists.add(line);
					jta.append(line+"\n");
				}
				bw.close();
				isr.close();
				is.close();
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			} catch (IOException e3) {
				e3.printStackTrace();
			}
		}else if(e.getSource() == sourceDAG){
			
			Graph sourceGraph = new Graph();
			File file = new File(jtf1.getText());
			ArrayList<Trace> trace = new ArrayList<Trace>();
			trace = LogUtil.xesParse(file);

			WorkflowMining2 workflow = new WorkflowMining2(trace);
			
			ArrayList<Edge> testEdges = new ArrayList<Edge>();
			testEdges.addAll(workflow.getEdges());
			HashSet<String> nodeSet = new HashSet<String>();
			for(int i=0;i<trace.get(0).getEvents().size();i++){
				nodeSet.add(trace.get(0).getEvents().get(i));
			}

			for(String s:nodeSet){
				sourceGraph.addNode(new Node(s));
			}
			for(int i=0;i<testEdges.size();i++){
				sourceGraph.addEdge(testEdges.get(i));
			}
			sourceGraph = workflow.transitiveReduction(sourceGraph);
			GraphViewer frame = new GraphViewer(sourceGraph);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}else if(e.getSource()==mining1){
			//Alg2+TPµÄÍÚ¾ò·½·¨
			String filePath = jtf1.getText();
			File file = new File(filePath);
			ArrayList<Trace> traces = new ArrayList<Trace>();
			traces = LogUtil.xesParse(file);
			
			long start = System.nanoTime();
			WorkflowMining2 workflow = new WorkflowMining2(traces);
			ArrayList<Edge> testEdges = new ArrayList<Edge>();
			testEdges = workflow.getEdges();
			
			Set<String> nodeSet = new HashSet<String>();
			for(int i=0;i<traces.get(0).getEvents().size();i++){
				nodeSet.add(traces.get(0).getEvents().get(i));
			}

			Graph graph = new Graph();
			graph = new Graph();
			for(String s:nodeSet){
				graph.addNode(new Node(s));
			}
			
			for(int i=0;i<testEdges.size();i++){
				graph.addEdge(testEdges.get(i));
			}

			graph = workflow.transitiveReduction(graph);
			ArrayList<Edge> reductionEdges = new ArrayList<>();
			reductionEdges = graph.getEdges();
			String filePathString = jtf2.getText();
			Iterator iterator = nodeSet.iterator();
			List<String> deleteNode = new ArrayList<String>();
			for(int i=0;i<nodeSet.size();i++){
				String node = (String) iterator.next();
				if(!lists.contains(node)){
					deleteNode.add(node);
				}
			}
			ArrayList<Edge> addEdges = new ArrayList<Edge>();
			for(int i=0;i<reductionEdges.size();i++){
				Node sourceNode = new Node();
				sourceNode = reductionEdges.get(i).getSource();
				Node targetNode = new Node();
				targetNode = reductionEdges.get(i).getTarget();
				if(lists.contains(sourceNode.getName())){
					if(deleteNode.contains(targetNode.getName())){
						for(int j=0;j<reductionEdges.size();j++){
							if(reductionEdges.get(j).getSource().getName().equals(targetNode.getName())){
								Node nextNode = new Node();
								nextNode = reductionEdges.get(j).getTarget();
								Edge edge = new Edge();
								edge.setSource(sourceNode);
								edge.setTarget(nextNode);
								reductionEdges.add(edge);
							}
						}
					}
				}
			}
			
			
			testEdges1 = new ArrayList<Edge>();
			for(int i=0;i<reductionEdges.size();i++){
				if(lists.contains(reductionEdges.get(i).getSource().getName())&&lists.contains(reductionEdges.get(i).getTarget().getName())){
					if(!testEdges1.contains(reductionEdges.get(i))){
						testEdges1.add(reductionEdges.get(i));
					}
				}
			}
			for(int i=0;i<testEdges1.size();i++){
				if(testEdges1.get(i).getTarget().getName().equals(testEdges1.get(i).getSource().getName())){
					testEdges1.remove(i);
				}
			}
			
	

			graph1 = new Graph();
			for(String s:lists){
				graph1.addNode(new Node(s));
			}
			
			for(int i=0;i<testEdges1.size();i++){
				graph1.addEdge(testEdges1.get(i));
			}

			graph1 = workflow.transitiveReduction(graph1);
			
			long end = System.nanoTime();
			jta.append("Algorithm1          Mining time:"+(end-start)/1000000.0+"ms"+"\n");

		}else if(e.getSource()==effective1){
			GraphViewer frame = new GraphViewer(graph1);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}else if(e.getSource()==mining2){
			
			File file = new File(jtf1.getText());
			ArrayList<Trace> trace = new ArrayList<Trace>();
			trace = LogUtil.xesParse(file);

			long start = System.nanoTime();
			Trace trace2;
			ArrayList<Trace> workflowProtocolLogTrace = new ArrayList<Trace>();
			for(int i=0;i<trace.size();i++){
				trace2 = new Trace();
				for(int j=0;j<trace.get(i).getEvents().size();j++){
					if(lists.contains(trace.get(i).getEvents().get(j))){
						trace2.addEvent(trace.get(i).getEvents().get(j));
					}
				}
					workflowProtocolLogTrace.add(trace2);
			}	

			WorkflowMining2 workflow = new WorkflowMining2(workflowProtocolLogTrace);
			testEdges2 = new ArrayList<Edge>();
			testEdges2 = workflow.getEdges();

			graph2 = new Graph();
			for(String s:lists){
				graph2.addNode(new Node(s));
			}
			for(int i=0;i<testEdges2.size();i++){
				graph2.addEdge(testEdges2.get(i));
			}

			graph2 = workflow.transitiveReduction(graph2);
			long end = System.nanoTime();
			jta.append("Algorithm2          Mining time:"+(end-start)/1000000.0+"ms"+"\n");
			
		}else if(e.getSource()==effective2){
			GraphViewer frame = new GraphViewer(graph2);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new WorkflowProtocolMining2();
				frame.setTitle("Workflow Protocol Mining");
				frame.setSize(800,400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
			
		});
	}
}




