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


    public FileManager()
    {
        System.out.println("FileManager constructed.");
    }
    public ArrayList<Node> NodeList(String path)
    {
        ArrayList<Node> readingList = new ArrayList<Node>();
            try{
            fi = new File(path);
            fr = new FileReader(fi);
                        
            int r,i = 0;
            boolean nodeFinished = false;
            while((r = fr.read()) != -1)
            {
                if(nodeFinished)
                {
                    Link newLink = new Link(readingList.get(r),readingList.get(fr.read()));
                    readingList.get(i++).add(newLink);
                    newLink = null;
                    continue;
                }
                if(r == -2)
                {
                    i = 0;
                    nodeFinished = true;
                    continue;
                }

                readingList.add(new Node(r,fr.read(),i++));
            }
            }
            catch (FileNotFoundException e)
            {
                return null;
            }
            catch(IOException e)
            {
                return null;
            }

        fi = null;
        return readingList;
    }
}
