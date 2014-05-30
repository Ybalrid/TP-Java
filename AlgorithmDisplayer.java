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
    private long baseDelay = 100;
	private long delay; //ms
	private int multiplier = 3;
    private boolean state = false;
    private GraphEditor parent = null;
    private boolean animate = false;
    private Queue<Node> displayQueue = new LinkedList<Node>();
    
    private Node lastNode = null;

	public AlgorithmDisplayer(ArrayList<Node> NodeList, GraphEditor p)
	{
		NodeCp = NodeList;
        parent = p;
	}

    public void setNodeList(ArrayList<Node> NodeList)
    {
        NodeCp = NodeList;
    }

    public void addToDisplayQueue(Node n)
    {
        if(!animate)
            displayQueue.add(n);
    }

    public void show()
    {
        clearDisp();
        animate = true;
    }

    public void setMultiplier(int value)
    {
        multiplier = value;
    }

	public int getMultiplier()
	{
		return multiplier;
	}

	public void run() 
    {
        try
        {

            System.out.println("Child Thread started");
            while(true)
            {
				delay = baseDelay * multiplier;
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
                System.out.println("Node " + nextNode.getID());
                nextNode.setColor(Color.GREEN);
                if(lastNode != null)
                {
                    lastNode.setColor(Color.RED);
                    boolean Oriented = false;
                    for(int i = 0; i < lastNode.getLinks().size(); i++)
                    {
                        if(lastNode.getLinks().get(i).to() == nextNode)
                            lastNode.getLinks().get(i).setColor(Color.RED);
                     //   Oriented = lastNode.getLinks().get(i).isOriented();
                    }

                //    if(!Oriented)
                    for(int i = 0; i < nextNode.getLinks().size(); i++)
                    {
                        if(nextNode.getLinks().get(i).to() == lastNode)
                            nextNode.getLinks().get(i).setColor(Color.RED);
                    }
                        
                }

                lastNode = nextNode;
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
        {
            NodeCp.get(i).setColor(Color.BLACK);
            for(int j = 0; j < NodeCp.get(i).getLinks().size(); j++)
                NodeCp.get(i).getLinks().get(j).setColor(Color.BLACK);
        }
        lastNode = null;
    }
}


