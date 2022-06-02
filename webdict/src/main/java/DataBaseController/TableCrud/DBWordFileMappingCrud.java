package DataBaseController.TableCrud;

import DataBaseController.DBWorker;
import DataBaseController.TableDescriptor.WordFileMappingDescriptor;

import java.sql.SQLException;
import java.sql.ResultSet;


public class DBWordFileMappingCrud {
    
    public static boolean dbcrud_wordFileMapping_insert(DBWorker dbWorker, Integer fkFileID, Integer fkWordID, Integer Count)
    {
        boolean resStatus = true;

        WordFileMappingDescriptor descriptor = new WordFileMappingDescriptor("INSERT");
        descriptor.set_fkFileID(fkFileID);
        descriptor.set_fkWordID(fkWordID);
        descriptor.set_Count(Count);
        dbWorker.execute(descriptor);

        return resStatus;
    }


    public static ResultSet dbcrud_wordFileMapping_browse(DBWorker dbWorker, Integer WordID)
    {
        WordFileMappingDescriptor descriptor = new WordFileMappingDescriptor("SELECT");
        descriptor.set_fkWordID(WordID);
        dbWorker.execute(descriptor);

        return descriptor.get_resSet();
    }
}
