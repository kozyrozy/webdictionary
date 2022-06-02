package Logger;

import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;

public class Logger {

    private static ArrayList<String> log = new ArrayList<>();


    public static void setLog(String textlog)
    {
        log.add(textlog + '\n');
    }

    public static ArrayList getLog()
    {
        return log;
    }

    public static void save_to_file()
    {
        String path = "D:\\Programming\\VS code\\Projects\\Java prj\\webdictionary\\log.txt";

        try {
            Files.write(Paths.get(path), log.toString().getBytes());

        } catch (IOException e) {
            log.add("File IOException: " + e);
            e.printStackTrace();
        }
    }

}