import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;

public class AlgorithmRunner
{
    
    private AlgorithmDisplayer algoDisp = null;
    private ArrayList<Node> nodes = null;

    public AlgorithmRunner(AlgorithmDisplayer ad, ArrayList<Node> NodeList)
    {
        nodes = NodeList;
        algoDisp = ad;
    }

    public void setNodeList(ArrayList<Node> NodeList)
    {
        nodes = NodeList;
    }

	public void DepthFirst_(Node startNode)
	{
		boolean trace[] = new boolean[nodes.size()];
		
		trace[startNode.getID()] = true;
		algoDisp.addToDisplayQueue(startNode);	
		
		ArrayList<Link> tmpLink = startNode.getLinks();

		for(Link linkIterator : tmpLink)
		{
			DepthFirst(trace, linkIterator.to());
		    algoDisp.addToDisplayQueue(startNode);	
		}
		
		//Launch display when every Nodes have been visited.
		algoDisp.show();
	}

	public void DepthFirst(boolean trace[], Node startNode)
	{
		if(trace[startNode.getID()])
			return;

		trace[startNode.getID()] = true;

		algoDisp.addToDisplayQueue(startNode);

		ArrayList<Link> tmpLink = startNode.getLinks();

		for(Link linkIterator : tmpLink)
		{
			DepthFirst(trace, linkIterator.to());
		    algoDisp.addToDisplayQueue(startNode);
		}
		
	}

	public void BreadthFirst(Node startNode)
	{
		boolean trace[] = new boolean[nodes.size()];

		Node current = null;
		Node next = null;				
		
		Queue<Node> nodeQueue = new LinkedList<Node>();
		ArrayList<Link> tmpLink = startNode.getLinks();		

		nodeQueue.add(startNode);
		
		while(nodeQueue.peek() != null)
		{
			current = nodeQueue.remove();
			trace[current.getID()] = true;
			algoDisp.addToDisplayQueue(current);

			tmpLink = current.getLinks();

			for(Link linkIterator : tmpLink)
			{
				next = linkIterator.to();

					if(trace[next.getID()] == false)
					{
						nodeQueue.add(next);
						trace[next.getID()] = true;
					}
			}
		}

		algoDisp.show();
	}

	
}
