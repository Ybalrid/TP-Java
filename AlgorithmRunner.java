import java.awt.*;
import java.awt.Graphics2D;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.*;

public class AlgorithmRunner
{
    
    private AlgorithmDisplayer algoDisp = null;
    private ArrayList<Node> nodes = null;

    public AlgorithmRunner(AlgorithmDisplayer ad, ArrayList<Node> NodeList)
    {
        nodes = NodeList;
        algoDisp = ad;
    }
}
