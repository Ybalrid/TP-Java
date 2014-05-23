import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.Math;
import java.awt.Component;
import java.awt.event.MouseListener;

public class Graph
{

    public static void main(String[] args)
    {
        String name = "os.name";
        System.out.println(System.getProperty(name));
        if(System.getProperty(name).equals("Mac OS X"))
        {
            try 
            {
                System.setProperty( "com.apple.mrj.application.apple.menu.about.name", "Graph Editor" );
                System.setProperty( "com.apple.macos.useScreenMenuBar", "true" );
                System.setProperty( "apple.laf.useScreenMenuBar", "true" ); // for older versions of Java
            } 
            catch ( SecurityException e ) 

            {
            }
        }
        GUI UI = new GUI();
    }
}
