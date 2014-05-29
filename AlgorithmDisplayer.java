import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;

public class AlgorithmDisplayer extends Thread
{
	private ArrayList<Node> NodeCp = null;
    private long delay = 200; //ms
    private boolean state = false;
    private GraphEditor parent = null;
    private boolean animate = false;
    private Queue displayQueue = new LinkedList();

	public AlgorithmDisplayer(ArrayList<Node> NodeList, GraphEditor p)
	{
		NodeCp = NodeList;
        parent = p;
	}

    public void addToDisplayQueye(Node n)
    {
        if(!animate)
            displayQueue.add(n);
    }

    public void show()
    {
        animate = true;
    }

    public void setDelay(long value)
    {
        delay = value;
    }

	public void run() 
    {
        try
        {

            System.out.println("run called");
            while(true)
            {
                System.out.println("Iterate");
                this.sleep(delay);
                refresh();
            }
        }
        catch(InterruptedException e)
        {
        }
    }

    private void refresh()
    {
        if(NodeCp.isEmpty())
            return;

        if(toogle())
            NodeCp.get(0).setColor(Color.RED);
        else
            NodeCp.get(0).setColor(Color.GREEN);


        if(animate)
        {
            //Do the hard job here
        }

        if(parent != null)
            parent.callRepaint();
    }

    private boolean toogle()
    {
        return state = !state;
    }

}
