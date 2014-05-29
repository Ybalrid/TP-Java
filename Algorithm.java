import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;

public class Algorithm extends Thread
{
	private ArrayList<Node> NodeCp = null;
    
    private long delay = 200; //ms
    
    private boolean state = false;
    
    private GraphEditor parent = null;

	public Algorithm(ArrayList<Node> NodeList, GraphEditor p)
	{
		NodeCp = NodeList;
        parent = p;
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
        if(state)
            NodeCp.get(0).setColor(Color.RED);
        else
            NodeCp.get(0).setColor(Color.GREEN);

        toogle();
        if(parent != null)
            parent.callRepaint();
    }

    private boolean toogle()
    {
        return state = !state;
    }

}
