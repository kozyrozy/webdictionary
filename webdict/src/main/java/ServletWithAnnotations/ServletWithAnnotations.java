package ServletWithAnnotations;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    
import java.util.List;
import java.util.LinkedList;

import DictionaryProcesser.Dictionary;
import DictionaryProcesser.MyTreeBrowser;

import DataBaseController.DBDictionaryController;

import Logger.Logger;

class FileReaderThread implements Runnable
{
    public void run()
    {
        
    }
}

class TextProcesserTread implements Runnable
{
    List<String> filePathList = new LinkedList<>();
    Dictionary processor = null;

    public TextProcesserTread(Dictionary processor, List<String> filePathList)
    {
        this.filePathList = filePathList;
        this.processor = processor;
    }

    public void run()
    {
        for (String filePath : filePathList)
        {
            this.processor.process_raw_text(filePath);
        }
    }
}


@WebServlet("/parseFiles")
public class ServletWithAnnotations extends HttpServlet {


    private static final long serialVersionUID = -3462096228274971485L;
    
    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
            throws ServletException, IOException 
    {
        
        DictionaryProcesser.Dictionary treeDict = new Dictionary();
        MyTreeBrowser reader = new MyTreeBrowser("D:\\Programming\\VS code\\Projects\\Java prj\\webdictionary\\InputText");
        reader.prepare_path_list();

        Integer step = reader.get_file_count() / 4;
        List<String> pathLst = reader.get_path_list();
        List<Thread> threadLst = new LinkedList<>();

        for (int i = 0; i < 4; i++)
        {
            if (i < 3)
            {
                Thread procTread = new Thread(new TextProcesserTread(treeDict, pathLst.subList(step * i, step * i + step)));
                procTread.start();
                System.out.println("Thread Id " + procTread.getId());
                threadLst.add(procTread);
            }
            else
            {
                Thread procTread = new Thread(new TextProcesserTread(treeDict, pathLst.subList(step * i, reader.get_file_count())));
                procTread.start();
                System.out.println("Thread Id " + procTread.getId());
                threadLst.add(procTread);
            }
        }

        for (Thread trd : threadLst)
        {
            try
            {
                trd.join();
            } catch (InterruptedException e){}
        }


        // Thread proc = new Thread(new TextProcesserTread(treeDict, pathLst));
        // proc.start();
        // try
        // {
        //     proc.join();
        // } catch (InterruptedException e){}


        //System.out.println(" Res treeDict: " + treeDict.toString());

        boolean res = DBDictionaryController.insert_dictionary(treeDict);
        
        response.getWriter().append("parseFiles log: " + Logger.getLog());
    }
}