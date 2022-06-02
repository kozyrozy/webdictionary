package DataBaseController.TableCrud;

import java.util.Set;

import java.sql.ResultSet;

import DataBaseController.DBWorker;
import DataBaseController.TableDescriptor.WordDescriptor;

import java.sql.SQLException;
import Logger.Logger;

public class DBWordCrud {
    
    
    public static boolean dbcrud_word_mass_insert(DBWorker dbWorker, Set<String> words)
    {
        Logger.setLog("method dbcrud_word_mass_insert insert");
        for (String word : words)
        {
            dbcrud_word_insert(dbWorker, word);
        }
        return true;
    }

    public static boolean dbcrud_word_insert(DBWorker dbWorker, String word)
    {
        boolean resStatus = true;

        WordDescriptor descriptor = new WordDescriptor("INSERT");
        descriptor.set_wordText(word);
        dbWorker.execute(descriptor);

        return resStatus;
    }

    public static ResultSet dbcrud_word_browse(DBWorker dbWorker, String word)
    {
        WordDescriptor descriptor = new WordDescriptor("SELECT");
        descriptor.set_wordText(word);
        dbWorker.execute(descriptor);

        //Logger.setLog(tbDescr.get_resSet().getString("WordID"));

        return descriptor.get_resSet();
    }

}

