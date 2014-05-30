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
    private Queue<Node> displayQueue = new LinkedList<Node>();

	public AlgorithmDisplayer(ArrayList<Node> NodeList, GraphEditor p)
	{
		NodeCp = NodeList;
        parent = p;
	}

    public void addToDisplayQueue(Node n)
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

            System.out.println("Child Thread started");
            while(true)
            {
                //System.out.println("Iterate");
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

/*        if(toogle())
            NodeCp.get(0).setColor(Color.RED);
        else
            NodeCp.get(0).setColor(Color.GREEN);*/


        if(animate)
        {
            //Do the hard job here
            if(displayQueue.isEmpty())
            {
                animate = false;
            }
            else
            {
                Node nextNode = displayQueue.remove();
                nextNode.setColor(Color.RED);
            }

        }

        if(parent != null)
            parent.callRepaint();
    }

    private boolean toogle()
    {
        return state = !state;
    }

    public void clearDisp()
    {
        if(animate) return;
        for(int i = 0; i < NodeCp.size(); i++)
            NodeCp.get(i).setColor(Color.BLACK);
    }
}


