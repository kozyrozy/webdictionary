package DataBaseController.TableDescriptor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import DataBaseController.TableDescriptor.AbstractTableDescriptor;
import Logger.Logger;

public class FileDescriptor extends AbstractTableDescriptor
{
    private String filePath = "";
    private Integer fileID = -1;

    public FileDescriptor(String statementType)
    {
        this.statementType = statementType;
    }


    public boolean set_filePath(String filePath)
    {
        this.filePath = filePath;
        return true;
    }

    public boolean set_fileID(Integer fileID)
    {
        this.fileID = fileID;
        return true;
    }

    public String get_sql_template()
    {
        if (this.statementType == "INSERT")
        {
            return "INSERT INTO \"File\" (\"FilePath\") VALUES (?)";
        }
        else if (this.statementType == "SELECT")
        {
            return "SELECT * FROM \"File\" where (\"FilePath\")=?";
        }
        else if (this.statementType == "SELECTBYID")
        {
            return "SELECT * FROM \"File\" where (\"FileID\")=?";
        }
        return "";
    }

    public PreparedStatement prepare_statement(PreparedStatement statement)
    {   
        if (this.statementType == "INSERT")
        {
            try
            {
                statement.setString(1, this.filePath);
                return statement;
            }
            catch (SQLException e)
            {
            }
        } 
        else if (this.statementType == "SELECT")
        {
            try
            {
                statement.setString(1, this.filePath);
                Logger.setLog(" File Path: " + this.filePath);
                return statement;
            }
            catch (SQLException e)
            {
            }
        }
        else if (this.statementType == "SELECTBYID")
        {
            try
            {
                statement.setInt(1, this.fileID);
                Logger.setLog(" fileID: " + this.fileID);
                return statement;
            }
            catch (SQLException e)
            {
            }
        }
        return null;
    }

}
