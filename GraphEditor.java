import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;

public class GraphEditor extends JPanel implements MouseListener, MouseMotionListener
{

    Font f;

    private ArrayList<Node> NodeList = new ArrayList<Node>();    
	
	private int editorMode = 0;
	//Editor mode descriptor : 
    // 0 = Add
    // 1 = Move
    // 2 = Delete

	private int algoMode = 0;
	//Algo mode descriptor : 
    // 0 = None
    // 1 = DepthFirst
    // 2 = BreadthFirst
	// 3 = Djisktra

    private int ID = 0;	

    private Node linkOrigin = null;
    private Node linkDest = null;


    //Set if the next user drawn Link will be An oriented one. true by default.
    private boolean orientedMode = true;
	private boolean valuatedMode = false;
    private boolean draggNode = false;
    private Node draggedNode = null;


    AlgorithmDisplayer algoDisp = null;
    AlgorithmRunner algoRun = null;

	//Class constructor
    public GraphEditor()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
        f = new Font("Helvetica", Font.BOLD, 12);
        setVisible(true);

        algoDisp = new AlgorithmDisplayer(NodeList,this);
        algoDisp.start(); //start the thread

        algoRun = new AlgorithmRunner(algoDisp,NodeList);
    }

	 // ----------------------- GETTERS AND SETTERS -------------------------------
	public AlgorithmDisplayer getADisp()
	{
		return algoDisp;
	}

	public AlgorithmRunner getARun()
	{
		return algoRun;
	}

	public void setValuated(boolean state)
	{
		valuatedMode = state;
	}

    public void setEditorMode(int mode)
    {
        editorMode = mode;
    }

	public void setAlgoMode(int mode)
	{
		algoMode = mode;
	}

	 // ----------------------- SOME METHODS -------------------------------

    public void testAlgoDisp()
    {
        for(Node n : NodeList)
            algoDisp.addToDisplayQueue(n);
        algoDisp.show();
    }
    
    public void callRepaint()
    {
        repaint();
    }

    ///Reset Node ID from their position in NodeList ArrayList
    private void reIndex()
    {
        for(int i = 0; i < NodeList.size(); i++)
            NodeList.get(i).setID(i);
        ID = NodeList.size();
    }

    //Draw the Grap panel
    public void paint(Graphics g)
    {
        //Recast to acces advenced 2D graphics composition tools
        Graphics2D g2 = (Graphics2D) g;

        //Reset drawing color to Black (default)
        g2.setColor(new Color(0,0,0));

        //Set antialiasing for drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        //Set a nicer font
        g2.setFont(f);

        //Clear the whole panel 
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        //Draw each node
        for (Node nodeIterator : NodeList)
        {
            nodeIterator.paint(g);

            //if no connection, iterate to next node
            if(nodeIterator.getLinks().isEmpty())
                continue;

            //draw all links 
            for(Link linksIterator : nodeIterator.getLinks())
                linksIterator.paint(g);
        }
    }

    //Remove all nodes
    public void clear()
    {
        while(!NodeList.isEmpty())
            NodeList.remove(0);
        repaint();
        ID = 0;
    }


    public Node getClosest(int x, int y)
    {
        //drop if there is no node on the editor
        if(NodeList.isEmpty()) return null;


        double distance = 0;
        //big fat pixel number... Stupidest way to initializa that value, but it's working flawlessly so...
        double reference = 1000000000;
        Node closest = null;

        //for each Node in NodeList
        for(Node myNode : NodeList)
        {

            //calculate distance
            distance = getDistance(myNode,x,y);
            //System.out.println("distance : " + distance);

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

			if(algoMode == 0)
			{		
         	   if(editorMode == 2)//The rather ugly chunk of code that erase a Node.
            	{

            	    if(closestNode != null)
            	    {
            	        if(getDistance(closestNode, x, y) <= 10)
            	        {
	
            	            for(Link link : closestNode.getLinks())
            	            {
            	                Node node_to = link.to();
            	                //                        for(Link link_to : node_to.getLinks())
            	                for(int i = 0; i < node_to.getLinks().size(); i++) 
            	                {
            	                    Link link_to = node_to.getLinks().get(i);
            	                    if(link_to.to() == closestNode)
            	                    {
            	                        node_to.getLinks().remove(link_to);
            	                    }
            	                }
            	            }
            	            NodeList.remove(closestNode);
            	            reIndex(); //only moment it's usefull
            	            ID = NodeList.size();
            	            repaint();
            	            return;
            	        }
            	    }
            	}
            	if(editorMode == 0)
            	{	
            	    if(closestNode != null)	
            	    {
            	        if(getDistance(closestNode, x, y) >= 30)
            	            NodeList.add(new Node(x,y,ID++));
            	    }
            	    else
            	        NodeList.add(new Node(x,y,ID++));
            	}
            	repaint();
        	}
			
			else if(algoMode == 1)
			{
				if(getDistance(closestNode, x, y) <= 10)
					algoRun.DepthFirst_(closestNode);
			}

			else if(algoMode == 2)
			{
				if(getDistance(closestNode, x, y) <= 10)
					algoRun.BreadthFirst(closestNode);
			}
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
        if(e.getButton() == 1 && editorMode == 0 && algoMode == 0)
        {
            System.out.println("Button 1");
            int x = e.getX();
            int y = e.getY();

            System.out.println("Pos : " + x + "x" + y);

            linkDest = getClosest(x,y);

            if(linkDest != null)
                System.out.println("Dest found : " +  linkDest.x() +  "x" +  linkDest.y());

            if(linkOrigin != null && linkDest != null)
            {
                if(linkOrigin != linkDest)
                {
                    boolean noLink = true;
                    for(Link l : linkOrigin.getLinks())
                        if(l.to() == linkDest)
                            noLink = false;
                    if(noLink)
                    {
						if(valuatedMode)
						{
                        	linkOrigin.addLink(linkDest,true);
							
							if(!orientedMode)
	                            linkDest.addLink(linkOrigin, true); //Add the reverse link.
						}

						else
						{
							linkOrigin.addLink(linkDest,false);
			
							if(!orientedMode)
                            	linkDest.addLink(linkOrigin, false); //Add the reverse link.
						}													

                        
							
                    }

                }
            }
        }
        draggNode = false;
        draggedNode = null;
        repaint();
    }

    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        System.out.println("Pressed");
        if(e.getButton() == 1)
        {
            System.out.println("Button 1");

            if(editorMode == 1 && algoMode == 0)
            {
                System.out.println("Middle click button");
                System.out.println("Dragged on " + x + "x" + y); 
                Node closestNode = getClosest(x,y);

                if(getDistance(closestNode, x, y) <= 10)
                {
                    draggedNode = closestNode;
                    draggNode = true;
                }
            }


            System.out.println("Pos : " + x + "x" + y);

            if(editorMode == 0 && algoMode == 0)
            {
                linkOrigin = getClosest(x,y);

                if(linkOrigin != null)
                    System.out.println("Origin found : " +  linkOrigin.x() +  "x" +  linkOrigin.y());

                linkDest = null;
            }
        }

    }

    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        //System.out.println("mouseDragged");

        if(editorMode == 1 && algoMode == 0)
        {
            if(draggedNode != null)
                if(draggNode)
                {
                    draggedNode.setX(x);
                    draggedNode.setY(y);
                    repaint();
                }
        }

    }

    public void mouseMoved(MouseEvent e)
    {
        //System.out.println("mouseMoved");
        int x = e.getX();
        int y = e.getY();

    }

    // ----------------------- FILE MANAGER INTERFACE -------------------------------  

    private boolean knowFilePath = false;
    private String filePath;

    private boolean changesSaved = false;

    private FileManager Fm = new FileManager();
    private void setFilePath(String path)
    {
        knowFilePath = true;
        filePath = path;

    }

    private boolean askForConfirmation(String message)
    {
        return true;
    }

    public void newMenuAction()
    {
        if(!changesSaved) 
            if(!askForConfirmation("Changes not saved. Do you want to discard changes ?"))
                return;

        knowFilePath = false;
        filePath = null;
        clear();
    }

    public boolean saveMenuAction()
    {
        if(knowFilePath)
        {
            if(!Fm.writeListToFile(filePath, NodeList))
                System.out.println("something goes wrong...");
            return true;
        }
        else
            //will display a dialog... 
            return false;
    }

    public void saveAsMenuAction(String Path)
    {
        if(Path == null)
        {
            System.out.println("Something goes wrong here. null path ?");
            return;
        }
        setFilePath(Path);
        saveMenuAction();
    }

    public void openMenuAction(String path)
    {
        newMenuAction();
        NodeList = Fm.readListFromFile(path);
        algoDisp.setNodeList(NodeList);
        algoRun.setNodeList(NodeList);
        reIndex();
        repaint();

    }

}
