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

	public void DepthFirst_(Node startNode)
	{
		boolean trace[] = new boolean[nodes.size()];
		
		trace[startNode.getID()] = true;
		algoDisp.addToDisplayQueue(startNode);	
		
		//Launch display when every Nodes have been visited.
		algoDisp.show();
	}

	public void DepthFirst(boolean trace[], Node startNode)
	{
		
	}

	public void BreadthFirst()
	{
		boolean trace[] = new boolean[nodes.size()];
	}
}
