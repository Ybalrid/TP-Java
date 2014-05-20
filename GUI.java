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
    
    int ID = 0;	
    
    Node linkOrigin = null;
    Node linkDest = null;

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
        for (Node nodeIterator : NodeList)
        {
            nodeIterator.paint(g);

            if(nodeIterator.getLinks().isEmpty())
                continue;

            for(Link linksIterator : nodeIterator.getLinks())
                linksIterator.paint(g);
        }
    }

    public Node getClosest(int x, int y)
    {
        //drop if there is no node on the editor
        if(NodeList.isEmpty()) return null;
        
        System.out.println("Get colsest node from " + x + "x" + y);

        double distance = 0;
        double reference = 100000000;// getDistance(NodeList.get(0), x, y);
        Node closest = null;

        //for each Node in NodeList
        for(Node myNode : NodeList)
        {
            System.out.println("getting distace for node " + myNode.getID());
            
            //calculate distance
            distance = getDistance(myNode,x,y);
            System.out.println("distance : " + distance);
            
            if(distance < reference)
            {
                System.out.println("New closest is now : " +  myNode.getID());
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

    // ----------------------- MOUSE LISTENER -------------------------------

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

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
        System.out.println("Released");
        if(e.getButton() == 1)
        {
            System.out.println("Button 1");
            int x = e.getX();
            int y = e.getY();

            System.out.println("Pos : " + x + "x" + y);
            
            linkDest = getClosest(x,y);
            
            if(linkDest != null)
                System.out.println("Dest found : " +  linkDest.x() +  "x" +  linkDest.y());
            
            if(linkOrigin != null && linkDest != null)
                if(linkOrigin != linkDest)
                    {
                        linkOrigin.addLink(linkDest);
                        repaint();
                    }
        }
    }

    public void mousePressed(MouseEvent e)
    {
        System.out.println("Pressed");
        if(e.getButton() == 1)
        {
            System.out.println("Button 1");
            
            int x = e.getX();
            int y = e.getY();
            
            System.out.println("Pos : " + x + "x" + y);
            
            linkOrigin = getClosest(x,y);
            
            if(linkOrigin != null)
                System.out.println("Origin found : " +  linkOrigin.x() +  "x" +  linkOrigin.y());

            linkDest = null;
        }

    }

}
