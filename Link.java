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

	
    private Color defaultColor = Color.BLACK;
    private Color drawingColor = null;

    Link(Node from, Node to)
	{
        System.out.println("Creating an ling from " + from.getID() + " to " + to.getID());
		this.from = from;
		this.to = to;
	}


	public void paint(Graphics g)
	{   
        if(!oriented && from.getID() > to.getID()) return;
        Graphics2D g2 = (Graphics2D) g;
        
        if(drawingColor != null)
            g2.setColor(drawingColor);

        g2.drawLine(from.x(), from.y(), to.x(), to.y());
	}

    public Node from()
    {
        return from;
    }

    public Node to()
    {
        return to;
    }

    public void setColor(Color color)
    {
        drawingColor = color;
    }
}
