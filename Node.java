import java.awt.*;
import java.awt.Graphics2D;
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
    private int Xpoints[] = new int[3];
	private int Ypoints[] = new int[3];
    private Color defaultColor = Color.BLACK;
    
    private Color drawingColor = null;

	//Class constructor
	Node(int X, int Y, int ID)
	{
		this.X = X;
		this.Y = Y;
		this.ID = ID;
	}

	// ----------------------- SOME METHODS-------------------------------	
	
	public void paint(Graphics g)
	{
		
        Graphics2D g2 = (Graphics2D) g;
        //Draw node point

        if(drawingColor != null)
            g2.setColor(drawingColor);
		g2.fillOval(X - 5, Y - 5, 10, 10);

        //Draw node ID
		g2.drawString(Integer.toString(ID), X + 5, Y + 5);
        
        g2.setColor(defaultColor);
	}
    
    public void addLink(Node dest, boolean valuated)
    {		
        System.out.println("adding link");
		Link nLink = new Link(this, dest);
        LinkList.add(nLink);
		
		if(valuated)
			nLink.setValuated();

    }

	// ----------------------- GETTERS AND SETTERS -------------------------------

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

    public void setColor(Color color)
    {
        drawingColor = color;
    }

}
