import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;



public class GUI extends JFrame
{

    
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");

    JMenuItem new_ = new JMenuItem("New");
    JMenuItem open_ = new JMenuItem("Open");
    JMenuItem save_ = new JMenuItem("Save");
    JMenuItem save_as_ = new JMenuItem("Save as...");
    JMenuItem quit_ = new JMenuItem("Quit");
	GraphEditor graph = new GraphEditor();
    
    private FileManager Fm = new FileManager();
    
    public GUI()
    {
		System.out.println("Constructeur !");
		
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 450);
		this.add(graph);
		file.add(new_);
        file.add(open_);
        file.addSeparator();
        file.add(save_);
        file.add(save_as_);
        file.addSeparator();
        file.add(quit_);
		setVisible(true);
		menuBar.add(file);
		setJMenuBar(menuBar);
        
		
    }

	 public void paint(Graphics g)
    {
		graph.paint(g);
	}
}
