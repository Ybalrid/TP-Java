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

    public ArrayList<Link> getLinks()
    {
        return linkList;
    }
}
