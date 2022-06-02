package DataBaseController.TableCrud;

import java.util.Set;

import java.sql.ResultSet;

import DataBaseController.DBWorker;
import DataBaseController.TableDescriptor.FileDescriptor;

import Logger.Logger;

public class DBFileCrud {
    
    public static boolean dbcrud_file_mass_insert(DBWorker dbWorker, Set<String> files)
    {
        Logger.setLog("method dbcrud_file_mass_insert insert");
        for (String file : files)
        {
            dbcrud_file_insert(dbWorker, file);
        }
        return true;
    }

    public static boolean dbcrud_file_insert(DBWorker dbWorker, String file)
    {
        boolean resStatus = false;

        FileDescriptor descriptor = new FileDescriptor("INSERT");
        descriptor.set_filePath(file);
        dbWorker.execute(descriptor);

        return resStatus;
    }

    public static ResultSet dbcrud_file_browse(DBWorker dbWorker, String file)
    {
        FileDescriptor descriptor = new FileDescriptor("SELECT");
        descriptor.set_filePath(file);
        dbWorker.execute(descriptor);
        
        return descriptor.get_resSet();
    }

    public static ResultSet dbcrud_file_browse(DBWorker dbWorker, Integer fileID)
    {
        FileDescriptor descriptor = new FileDescriptor("SELECTBYID");
        descriptor.set_fileID(fileID);
        dbWorker.execute(descriptor);
        
        return descriptor.get_resSet();
    }
}
