import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;

public class Link extends JPanel
{
	Node from;
	Node to;
    boolean oriented;

	Link(Node from, Node to)
	{
		this.from = from;
		this.to = to;
        this.oriented = false;
	}

    public boolean isOriented()
    {
        return oriented;
    }

    public void setOirientedState(boolean state)
    {
        oriented = state;
    }


	public void paint(Graphics g)
	{

	}
}
