package org.nust.wf.Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.nust.wf.basicstruct.Edge;
import org.nust.wf.basicstruct.Graph;
import org.nust.wf.basicstruct.Trace;
import org.nust.wf.frame.SWMCPlusFrame;
import org.nust.wf.frame.WorkflowProtocolMining2;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class MainFrame extends JFrame implements ActionListener{

	/**
	 * @param args
	 */
	JMenuBar menuBar;
	JMenu Start;
	JMenuItem SWMCPlusItem;
	JMenuItem protocolItem;
	
	public MainFrame(){
		menuBar = new JMenuBar();
		Start = new JMenu("Start");
		SWMCPlusItem = new JMenuItem("SWMC+ Mining");
		SWMCPlusItem.addActionListener(this);
		protocolItem = new JMenuItem("Workflow Protocol Mining");
		protocolItem.addActionListener(this);
		Start.add(SWMCPlusItem);
		Start.add(protocolItem);
		menuBar.add(Start);
		add(menuBar,BorderLayout.NORTH);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new MainFrame();
				frame.setTitle("SWMC+");
				frame.setSize(800,500);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {


		if (e.getSource() == SWMCPlusItem) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					JFrame frame = new SWMCPlusFrame();
					frame.setTitle("SWMC+");
					frame.setSize(800,400);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				}
				
			});
		}else if (e.getSource() == protocolItem) {
			SwingUtilities.invokeLater(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					JFrame frame = new WorkflowProtocolMining2();
					frame.setTitle("Workflow Protocol Mining");
					frame.setSize(800,400);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				}
				
			});
		}
		
	}

}
