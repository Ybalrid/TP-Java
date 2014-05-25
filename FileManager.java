import java.util.*;
import java.io.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager
{
    private FileWriter fw = null;
    private FileReader fr = null;
    private File fi = null;
    private File fo = null;


    private void printf(String s)
    {
        System.out.println(s);
    }

    public FileManager()
    {
        System.out.println("FileManager constructed.");
    }

    public ArrayList<Node> readListFromFile(String path)
    {
        ArrayList<Node> readingList = new ArrayList<Node>();
        try{
            fi = new File(path);
            fr = new FileReader(fi);
            Scanner s = new Scanner(fi);
            int r,i = 0;
            boolean nodeFinished = false;


            //            while((r = fr.read()) != -1)
            while(s.hasNextInt())
            {
                r = s.nextInt();
                printf("read " + r); 
                if(nodeFinished)
                {
                    Node from = readingList.get(r);
                    r = s.nextInt();
                    Node to = readingList.get(r);

                    to.addLink(from);
                    from.addLink(to);

                    continue;
                }
                if(r == -1)
                {
                    i = 0;
                    nodeFinished = true;
                    continue;
                }

                printf("we are still getting nodes");
                //readingList.add(new Node(r,fr.read(),i++));
                readingList.add(new Node(r, s.nextInt(), i++));
            }
        }
    /*    catch(IOException e)
        {
            return null;
        }*/
        catch (FileNotFoundException e)
        {
            return null;
        }

        fi = null;
        return readingList;
    }
}
