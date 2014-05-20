import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame implements MouseListener
{
	ArrayList<Node> NodeList = new ArrayList<Node>();
	ArrayList<Integer> usedID = new ArrayList<Integer>();

	int x;
	int y;
	int ID = 0;	
	
	public GUI()
	{
		this.setTitle("GraphESIEA");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 450);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		this.addMouseListener(this);
		
	}

	public void paint(Graphics g)
	{
        //clear the whole panel 
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        //draw each node
        for (Node myNode : NodeList)
        {
			myNode.paint(g);
		}
	}

	public Node getClosest(int x, int y)
	{
        //drop if there is no node on the editor
		if(NodeList.isEmpty()) return null;
        
		double distance = 0;
		double reference = Math.sqrt(
                this.getWidth() * this.getWidth() 
                + this.getHeight() * this.getHeight());
		Node closest = null;
		
        //for each Node in NodeList
		for(Node myNode : NodeList)
		{
            //calculate distance
			distance = Math.sqrt(x*x + y*y);
			
            //if distance < reference 
			if(distance < reference)
			{
				closest = myNode;
				reference = distance;
			}
		}
		
		return closest;
	}

	public double getDistance(Node target, int x, int y)
	{
        return Math.sqrt((target.X-x)*(target.X-x) + (target.Y-y)*(target.Y-y));
	}

	/*public boolean canCreate(int x, int y)
	{
		if
	}*/

    // ----------------------- MOUSE LISTENER -------------------------------

	public void mouseClicked(MouseEvent e)
	{
			x = e.getX();
			y = e.getY();
		
		if(e.getButton() == 1)
		{
			if(usedID.isEmpty())
			{
				Node last = new Node(x,y,ID);
				NodeList.add(last);
				repaint();
				ID++;
			}
		
			else
			{
				Node last = new Node(x,y,usedID.get(0));
				NodeList.add(last);
				usedID.remove(0);
				repaint();
			}
		}

		if(e.getButton() == 3)
		{

		}
	
	}
	
	public void mouseExited(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
	}
	
}
