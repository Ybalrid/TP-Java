import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Cursor;


public class GUI extends JFrame
{

    //Creating a MenuBar
    JMenuBar menuBar = new JMenuBar();

	//Creating the categories of the MenuBar
    JMenu file = new JMenu("File");
	JMenu edit = new JMenu("Edit");
	JMenu algo = new JMenu("Algorithms");
	JMenu help = new JMenu("Help");

	//Creating the items displayed in File
    JMenuItem new_ = new JMenuItem("New");
    JMenuItem open_ = new JMenuItem("Open");
    JMenuItem save_ = new JMenuItem("Save");
    JMenuItem save_as_ = new JMenuItem("Save as...");
    JMenuItem quit_ = new JMenuItem("Quit");
	
	//Creating the items displayed in Edit
	JMenuItem clear_ = new JMenuItem("Clear");
	
	//Creating the items displayed in Algorithms
	JMenuItem depth_ = new JMenuItem("Depth-First");
	JMenuItem breadth_ = new JMenuItem("Breadth-First");
	JMenuItem djikstra_ = new JMenuItem("Djikstra");
	
	//Creating the items displayed in Help
	JMenuItem manual_ = new JMenuItem("Manual");
	JMenuItem about_ = new JMenuItem("About");		

	GraphEditor graph = new GraphEditor();
	Cursor main = new Cursor(Cursor.HAND_CURSOR);
	Cursor algoCurs = new Cursor(Cursor.CROSSHAIR_CURSOR);
    
    
    public GUI()
    {
		System.out.println("Constructeur !");
	    
        boolean isOnMac = System.getProperty("os.name").equals("Mac OS X");
        
        //Construct window : 
        setTitle("Graph Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 450);
	
        this.add(graph);
	    
        //Construct menubar
        file.add(new_);
        file.add(open_);
        file.addSeparator();
        file.add(save_);
        file.add(save_as_);
        if(!isOnMac) //handeled by OS X
        {
            file.addSeparator();
            file.add(quit_);
        }
        
        edit.add(clear_);
		
        algo.add(depth_);
		algo.add(breadth_);
		algo.addSeparator();
		algo.add(djikstra_);
		help.add(manual_);
        if(!isOnMac)   
    		help.add(about_);
	
        setVisible(true);//Show the window THEN add the menu bar
		
        menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(algo);
		menuBar.add(help);
		
        setJMenuBar(menuBar);
		setCursor(algoCurs);

		quit_.addActionListener(new ActionListener()
		{
      		public void actionPerformed(ActionEvent arg0) 
			{
	        	System.exit(0);
	      	}        
    	});

		clear_.addActionListener(new ActionListener()
		{
      		public void actionPerformed(ActionEvent arg0) 
			{
	        	graph.clear();
	      	}        
    	});

					       
		
    }

	 public void paint(Graphics g)
     {

         Graphics2D g2 =(Graphics2D) g;
         graph.paint(g2);
     }
}
