import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Node extends JPanel
{
	ArrayList<Link> LinkList = new ArrayList<Link>();

	int ID;
	int X;
	int Y;

	Node(int X, int Y, int ID)
	{
		this.X = X;
		this.Y = Y;
		this.ID = ID;
	}

	public void paint(Graphics g)
	{
        //Draw node point
		g.fillOval(X - 5, Y - 5, 10, 10);
        //Draw node ID
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
}
