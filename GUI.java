import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.FileDialog;

public class GUI extends JFrame
{
    JFrame parentFrameForDialog = this;
    //Creating a MenuBar
    JMenuBar menuBar = new JMenuBar();

    //Creating the categories of the MenuBar
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu tools = new JMenu("Tools");
    JMenu algo = new JMenu("Algorithms");
    JMenu help = new JMenu("Help");
    JMenu debug = new JMenu("Debug");

	//Submenu for choosing animation speed.
	JMenu speed_ = new JMenu("Animation Speed");

    //Creating the items displayed in File
    JMenuItem new_ = new JMenuItem("New");
    JMenuItem open_ = new JMenuItem("Open");
    JMenuItem save_ = new JMenuItem("Save");
    JMenuItem save_as_ = new JMenuItem("Save as...");
    JMenuItem quit_ = new JMenuItem("Quit");

    //Creating the items displayed in Edit
    JMenuItem clear_ = new JMenuItem("Clear");
	JMenuItem reset_ = new JMenuItem("Reset");
	JCheckBox valuated_ = new JCheckBox("Valuated");

    //Creating the items displayed in Tools
    JMenuItem add_ = new JMenuItem("Add");
    JMenuItem move_ = new JMenuItem("Move");
    JMenuItem delete_ = new JMenuItem("Delete");

    //Creating the items displayed in Algorithms
    JMenuItem depth_ = new JMenuItem("Depth-First");
    JMenuItem breadth_ = new JMenuItem("Breadth-First");
    JMenuItem dijkstra_ = new JMenuItem("Dijkstra");

    //Creating the items displayed in Help
    JMenuItem manual_ = new JMenuItem("Manual");
    JMenuItem about_ = new JMenuItem("About");		

    //Creating the items displayed in Debug
    //JMenuItem coloriseAllNodes_ = new JMenuItem("Colorise All Nodes");

	//Creating the items displayed in Animation Speed submenu.
	JRadioButtonMenuItem ufast_ = new JRadioButtonMenuItem("Ultra Fast");
	JRadioButtonMenuItem fast_ = new JRadioButtonMenuItem("Fast");
	JRadioButtonMenuItem normal_ = new JRadioButtonMenuItem("Normal", true);
	JRadioButtonMenuItem slow_ = new JRadioButtonMenuItem("Slow");
	JRadioButtonMenuItem uslow_ = new JRadioButtonMenuItem("Ultra Slow");

    GraphEditor graph = new GraphEditor();

    //Creating cursors to be able to switch between them depending on the tool selected by the user.
    Cursor mainCurs = new Cursor(Cursor.DEFAULT_CURSOR);
    Cursor algoCurs = new Cursor(Cursor.HAND_CURSOR);
    Cursor deleteCurs = new Cursor(Cursor.CROSSHAIR_CURSOR);
    Cursor moveCurs = new Cursor(Cursor.MOVE_CURSOR);    
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
		edit.add(reset_);
		edit.addSeparator();
		edit.add(valuated_);
		edit.add(speed_);

		speed_.add(ufast_);
		speed_.add(fast_);
		speed_.add(normal_);
		speed_.add(slow_);
		speed_.add(uslow_);

        tools.add(add_);
        tools.add(move_);
        tools.add(delete_);

        algo.add(depth_);
        algo.add(breadth_);
        algo.addSeparator();
        algo.add(dijkstra_);
        
		help.add(manual_);
        if(!isOnMac)   
            help.add(about_);

        //debug.add(coloriseAllNodes_);
        setVisible(true);//Show the window THEN add the menu bar

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(tools);
        menuBar.add(algo);
        menuBar.add(help);
        //menuBar.add(debug);
        setJMenuBar(menuBar);

	// ----------------------- SHORTCUTS SECTION -------------------------------

			//---File Subsection---
		new_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N,  KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));

		open_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		save_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		save_as_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_S, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));

		quit_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_Q, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));

			//---Edit Subsection---

		clear_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_C, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));
		
		reset_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_R, KeyEvent.CTRL_MASK));

		/*valuated_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_R, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_MASK));*/
			
				//--Animation Speed Sub-Subsection--
		/*plus_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_PLUS, KeyEvent.CTRL_MASK));*/

		/*minus_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_MINUS, KeyEvent.CTRL_MASK));*/
		
			//---Tools Subsection---

		add_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		
		move_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_M, KeyEvent.CTRL_MASK));

		delete_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_D, KeyEvent.CTRL_MASK));

			//---Algorithms Subsection---

		depth_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_D, KeyEvent.ALT_MASK));
		
		breadth_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_B, KeyEvent.ALT_MASK));

		dijkstra_.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_D, KeyEvent.ALT_MASK + KeyEvent.SHIFT_MASK));		

        /************ACTION LISTENERS FOR THE MENU ITEMS******************/
        /*this.addComponentListener(new ComponentAdapter() 
          {
          public void componentResized(ComponentEvent e) 
          {             

          }
          });*/

	 // ----------------------- FILE SECTION -------------------------------
        new_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.newMenuAction();
        }        
        });

        save_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            if(!graph.saveMenuAction())
            {
            FileDialog fd = new FileDialog(parentFrameForDialog, "Save Graph as...", FileDialog.SAVE);
            fd.setMultipleMode(false);
            fd.setFile("*.graph");
            fd.setVisible(true);
            graph.saveAsMenuAction(fd.getFile());

            }
        }        
        });


        save_as_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            FileDialog fd = new FileDialog(parentFrameForDialog, "Save Graph as...", FileDialog.SAVE);
            fd.setMultipleMode(false);
            fd.setFile("*.graph");
            fd.setVisible(true);
            graph.saveAsMenuAction(fd.getFile());

        }        
        });

        open_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0)
        {
            FileDialog fd = new FileDialog(parentFrameForDialog, "Open Graph...", FileDialog.LOAD);
            fd.setMultipleMode(false);
            fd.setFile("*.graph");
            fd.setVisible(true);
            graph.openMenuAction(fd.getFile());
        }
        });


        quit_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            System.exit(0);
        }        
        });

	 // ----------------------- EDIT SECTION -------------------------------

        clear_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.clear();
        }        
        });

		reset_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			AlgorithmDisplayer temp = null;
            temp = graph.getADisp();
			temp.clearDisp();
        }        
        });

		
        valuated_.addItemListener(new ItemListener() 
				{

		            public void itemStateChanged(ItemEvent e) 
		{
                if(e.getStateChange() == ItemEvent.SELECTED)
					graph.setValuated(true);
				
				else if(e.getStateChange() == ItemEvent.SELECTED)
					graph.setValuated(false);
                    
        }
        });
	
					//---ANIMATION SPEED SUBSECTION---

		ufast_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			AlgorithmDisplayer temp = null;
            temp = graph.getADisp();
			temp.setMultiplier(1);
        }        
        });
		
		fast_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			AlgorithmDisplayer temp = null;
            temp = graph.getADisp();
			temp.setMultiplier(2);
        }        
        });

		normal_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			AlgorithmDisplayer temp = null;
            temp = graph.getADisp();
			temp.setMultiplier(3);
        }        
        });

		slow_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			AlgorithmDisplayer temp = null;
            temp = graph.getADisp();
			temp.setMultiplier(4);
        }        
        });

		uslow_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			AlgorithmDisplayer temp = null;
            temp = graph.getADisp();
			temp.setMultiplier(5);
        }        
        });
				

	 // ----------------------- TOOLS SECTION -------------------------------

        add_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			graph.setAlgoMode(0);
            graph.setEditorMode(0);
            setCursor(mainCurs);
        }        
        });	

        move_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.setEditorMode(1);
			graph.setAlgoMode(0);
            setCursor(moveCurs);
        }        
        });	

        delete_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
			graph.setAlgoMode(0);
            graph.setEditorMode(2);
            setCursor(deleteCurs);
        }        
        });
	
	 // ----------------------- ALGORITHM SECTION -------------------------------

        depth_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {			
            setCursor(algoCurs);
			graph.setAlgoMode(1);
        }        
        });
		
		breadth_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {			
            setCursor(algoCurs);
			graph.setAlgoMode(2);
        }        
        });

	 // ----------------------- HELP SECTION -------------------------------

		manual_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {			
			JOptionPane.showMessageDialog(null,"For more informations about how to run this program\ncheck the README.txt file.");
        }        
        });

		about_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {			
			JOptionPane.showMessageDialog(null,"This program has been created by :\n Arthur Brainville & Thomas Thous.");
        }        
        });

	 // ----------------------- DEBUG SECTION -------------------------------

        /*coloriseAllNodes_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.testAlgoDisp();
        }        
        });	*/
    }

    public void paint(Graphics g)
    {

        Graphics2D g2 =(Graphics2D) g;
        graph.paint(g2);
    }

    public void actionPerformed(ActionEvent e)
    {

        repaint();
    }

}
