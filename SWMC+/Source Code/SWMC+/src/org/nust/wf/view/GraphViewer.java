package org.nust.wf.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import org.nust.wf.basicstruct.Graph;
import org.nust.wf.util.GraphVizUtil;

public class GraphViewer extends JFrame {
	private JLabel label;
	private JFileChooser chooser;
	private ImageIcon icon;
	private static Graph graph;
	private static final int DEFAULT_WIDTH = 1000;
	private static final int DEFAULT_HEIGHT = 1000;

	public GraphViewer(Graph g) {
		
		graph = g;

		setTitle("ImageViewer");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save");
		menu.add(saveItem);

		File out = GraphVizUtil.saveAsTemp(g);

		label = new JLabel();
		icon = new ImageIcon(out.getPath());
		label.setIcon(icon);
//		setSize(icon.getIconWidth(), icon.getIconHeight());
//		add(label);
		//label.setPreferredSize(new Dimension(3000,3000));
		//label.setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
		JScrollPane jsp = new JScrollPane(label);
		add(jsp,BorderLayout.CENTER);
		
		

		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				int state = chooser.showOpenDialog(GraphViewer.this);
				if (state == JFileChooser.APPROVE_OPTION) {
					icon = new ImageIcon(chooser.getSelectedFile().getPath());
					label.setIcon(icon);
					setSize(icon.getIconWidth(), icon.getIconHeight());
				}

			}
		});
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("save");
				chooser = new JFileChooser();
				int state = chooser.showSaveDialog(GraphViewer.this);
				if (state == JFileChooser.APPROVE_OPTION) {
					File saveFile = chooser.getSelectedFile();
					GraphVizUtil.saveAsSVG(graph, saveFile);
				}
			}
		});
	}

	public GraphViewer() {
		setTitle("ImageViewer");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save");
		menu.add(saveItem);

		label = new JLabel();
		add(label);

		openItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser();
				int state = chooser.showOpenDialog(GraphViewer.this);
				if (state == JFileChooser.APPROVE_OPTION) {
					icon = new ImageIcon(chooser.getSelectedFile().getPath());
					label.setIcon(icon);
					setSize(icon.getIconWidth(), icon.getIconHeight());
				}

			}
		});
		saveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("save");

			}

		});
	}
}
