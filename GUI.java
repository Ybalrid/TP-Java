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

    //Creating the items displayed in File
    JMenuItem new_ = new JMenuItem("New");
    JMenuItem open_ = new JMenuItem("Open");
    JMenuItem save_ = new JMenuItem("Save");
    JMenuItem save_as_ = new JMenuItem("Save as...");
    JMenuItem quit_ = new JMenuItem("Quit");

    //Creating the items displayed in Edit
    JMenuItem clear_ = new JMenuItem("Clear");

    //Creating the items displayed in Tools
    JMenuItem add_ = new JMenuItem("Add");
    JMenuItem move_ = new JMenuItem("Move");
    JMenuItem delete_ = new JMenuItem("Delete");

    //Creating the items displayed in Algorithms
    JMenuItem depth_ = new JMenuItem("Depth-First");
    JMenuItem breadth_ = new JMenuItem("Breadth-First");
    JMenuItem djikstra_ = new JMenuItem("Djikstra");

    //Creating the items displayed in Help
    JMenuItem manual_ = new JMenuItem("Manual");
    JMenuItem about_ = new JMenuItem("About");		

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

        tools.add(add_);
        tools.add(move_);
        tools.add(delete_);

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
        menuBar.add(tools);
        menuBar.add(algo);
        menuBar.add(help);

        setJMenuBar(menuBar);


        /************ACTION LISTENERS FOR THE MENU ITEMS******************/
        /*this.addComponentListener(new ComponentAdapter() 
          {
          public void componentResized(ComponentEvent e) 
          {             

          }
          });*/
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
            graph.saveMenuAction();
        }        
        });


        save_as_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            FileDialog fd = new FileDialog(parentFrameForDialog, "Save Graph as...", FileDialog.SAVE);
            fd.setMultipleMode(false);
            fd.show();
            graph.saveAsMenuAction(fd.getFile());
        }        
        });

        open_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0)
        {
            FileDialog fd = new FileDialog(parentFrameForDialog, "Open Graph...", FileDialog.LOAD);
            fd.setMultipleMode(false);
            fd.show();
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

        clear_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.clear();
        }        
        });	

        add_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.setEditorMode(0);
            setCursor(mainCurs);
        }        
        });	

        move_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.setEditorMode(1);
            setCursor(moveCurs);
        }        
        });	

        delete_.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) 
        {
            graph.setEditorMode(2);
            setCursor(deleteCurs);
        }        
        });	
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
