package DictionaryProcesser;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class MyTreeBrowser
{
    private List<String> pathLst = new LinkedList<String>();
    private String startDirectory = "";

    public MyTreeBrowser(String startDirectory)
    {
        this.startDirectory = startDirectory;
    }

    public void prepare_path_list()
    {
        this.prepare_path_list(this.startDirectory);
    }

    private void prepare_path_list(String currentDirectory)
    {
        File dir = new File(currentDirectory); 

        if (dir.exists())
        {
            //System.out.println("Start dir: " + currentDirectory + "|| list dir: " + Arrays.toString(dir.list()));

            for (String fileName : dir.list())
            {
                //System.out.println("fileName: " + fileName + "|| isfolder: " + new File(currentDirectory + '/' + fileName).isDirectory());
                if (new File(currentDirectory + '\\' + fileName).isDirectory() == true)
                {
                    prepare_path_list(currentDirectory + '\\' + fileName);
                }
                else
                {
                    this.pathLst.add(currentDirectory + '\\' + fileName);
                }
            }
        }
        else
        {
            System.out.println("Directory not found!");
        }
    }


    public List<String> get_path_list()
    {
        return pathLst;
    }

    public Integer get_file_count()
    {
        return pathLst.size();
    }


    public String get_strat_directory()
    {
        return this.startDirectory;
    }
}
