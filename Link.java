import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Link extends JPanel
{
	private Node from;
	private Node to;
    
    private boolean oriented = false;
    private boolean valuated = false;
    private int value = 0;
	
    private Color defaultColor = Color.BLACK;
    private Color drawingColor = null;

	//Class contructor
    Link(Node from, Node to)
	{
        System.out.println("Creating an ling from " + from.getID() + " to " + to.getID());
		this.from = from;
		this.to = to;
	}

	// ----------------------- SOME METHODS-------------------------------	

	public void paint(Graphics g)
	{   
        if(!oriented && from.getID() > to.getID()) return;
        Graphics2D g2 = (Graphics2D) g;
        
        if(drawingColor != null)
            g2.setColor(drawingColor);

        g2.drawLine(from.x(), from.y(), to.x(), to.y());

        if(valuated)
        { 
            g2.setColor(Color.BLUE);
            g2.drawString(Integer.toString(value),(from.x() + to.x())/2, 5 + (from.y() + to.y())/2);
        }

        g2.setColor(defaultColor);

	}

	// ----------------------- GETTERS AND SETTERS -------------------------------

    public Node from()
    {
        return from;
    }

    public Node to()
    {
        return to;
    }

    public int getValue()
    {
        return value;
    }


    public void setColor(Color color)
    {
        drawingColor = color;
    }

    public void setValue(int newValue)
    {
        value = newValue;
    }

    public void setOriented(boolean state)
    {
        oriented = state;
    }

    public void setValuated(boolean state)
    {
        valuated = state;
    }

    public boolean isValuated()
    {
        return valuated;
    }

    public boolean isOriented()
    {
        return oriented;
    }
}
