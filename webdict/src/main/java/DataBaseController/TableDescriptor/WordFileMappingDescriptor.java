package DataBaseController.TableDescriptor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WordFileMappingDescriptor extends AbstractTableDescriptor
{
    private Integer fkFileID = -1;
    private Integer fkWordID = -1;
    private Integer Count = -1;

    public WordFileMappingDescriptor(String statementType)
    {
        this.statementType = statementType;
    }


    public boolean set_fkFileID(Integer fkFileID)
    {
        this.fkFileID = fkFileID;
        return true;
    }

    public boolean set_Count(Integer Count)
    {
        this.Count = Count;
        return true;
    }

    public boolean set_fkWordID(Integer fkWordID)
    {
        this.fkWordID = fkWordID;
        return true;
    }

    
    public String get_sql_template()
    {
        if (this.statementType == "INSERT")
        {
            return "INSERT INTO \"WordFileMapping\" (\"fkFileID\", \"fkWordID\", \"Count\") VALUES (?,?,?)";
        }
        else if (this.statementType == "SELECT")
        {
            return "SELECT * FROM \"WordFileMapping\" where (\"fkWordID\")=? ORDER BY \"Count\" DESC";
        }
        return "";
    }

    public PreparedStatement prepare_statement(PreparedStatement statement)
    {   
        if (this.statementType == "INSERT")
        {
            try
            {
                statement.setInt(1, this.fkFileID);
                statement.setInt(2, this.fkWordID);
                statement.setInt(3, this.Count);
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
                statement.setInt(1, this.fkWordID);
                return statement;
            }
            catch (SQLException e)
            {
            }
        }
        return null;
    }
}
