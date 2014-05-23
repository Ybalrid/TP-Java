import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Node extends JPanel
{
	private ArrayList<Link> LinkList = new ArrayList<Link>();

	private int ID;
	private int X;
	private int Y;

	Node(int X, int Y, int ID)
	{
		this.X = X;
		this.Y = Y;
		this.ID = ID;
	}

	public void paint(Graphics g)
	{
        System.out.println("print node's point");
        //Draw node point
		g.fillOval(X - 5, Y - 5, 10, 10);
        //Draw node ID
        System.out.println("print node's ID");
		g.drawString(Integer.toString(ID), X + 5, Y + 5);
	}
    
    public void addLink(Node dest)
    {
        System.out.println("adding link");
        LinkList.add(new Link (this, dest));
    }

    public ArrayList<Link> getLinks()
    {
        return LinkList;
    }

    public int getID()
    {
        return ID;
    }
    
    public int x()
    {
        return X;
    }

    public int y()
    {
        return Y;
    }


    public void setID(int id)
    {
        if(ID > 0)
            ID = id;
    }

	public void setX(int x)
	{
        if(x > 0)
    		X = x;
	}
	
    public void setY(int y)
	{
        if(y > 0)
    		Y = y;
	}

}
