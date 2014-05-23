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

    int ID = 0;	

    private Node linkOrigin = null;
    private Node linkDest = null;
    
    Font f;
    
    public GUI()
    {
        setTitle("GraphESIEA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 450);
        setVisible(true);

        JPanel panel = new JPanel();
        addMouseListener(this);

        f = new Font("Helvetica", Font.BOLD, 12);
    }

    public void paint(Graphics g)
    {
        g.setFont(f);
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
        double reference = 1000000000;
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
        return Math.sqrt((target.x()-x)*(target.x()-x) + (target.y()-y)*(target.y()-y));
    }

    // ----------------------- MOUSE LISTENER -------------------------------

    public void mouseClicked(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        if(e.getButton() == 1)
        {
            Node closestNode = getClosest(x,y);
            if(closestNode != null)
                if(getDistance(closestNode, x, y) <= 5)
                {
                    System.out.println("Node " + closestNode.getID() + " clicked");

                    for(Link link : closestNode.getLinks())
                    {
                        System.out.println("processing link");
                        Node node_to = link.to();
//                        for(Link link_to : node_to.getLinks())
                        for(int i = 0; i < node_to.getLinks().size(); i++) 
                        {
                            Link link_to = node_to.getLinks().get(i);
                            System.out.println(" Link form \"child\" node");
                            if(link_to.to() == closestNode)
                            {
                                System.out.println("  That link goes to the clicked node");
                                node_to.getLinks().remove(link_to);
                            }
                    }
                    }
                    NodeList.remove(closestNode);
                    repaint();
                    return;
                }

            NodeList.add(new Node(x,y,ID++));
            repaint();
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
                    //Non-oriented.
                    linkOrigin.addLink(linkDest);
                    linkDest.addLink(linkOrigin);
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
