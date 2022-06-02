package DataBaseController.TableDescriptor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import DataBaseController.TableDescriptor.AbstractTableDescriptor;


public class WordDescriptor extends AbstractTableDescriptor
{
    
    private String wordText = "";

    public WordDescriptor(String statementType)
    {
        this.statementType = statementType;
    }


    public boolean set_wordText(String wordText)
    {
        this.wordText = wordText;
        return true;
    }

    public String get_sql_template()
    {
        if (this.statementType == "INSERT")
        {
            return "INSERT INTO \"Word\" (\"WordText\") VALUES (?)";
        }
        if (this.statementType == "SELECT")
        {
            return "SELECT * FROM \"Word\" where (\"WordText\")=?";
        }
        return "";
    }

    public PreparedStatement prepare_statement(PreparedStatement statement)
    {   
        if (this.statementType == "INSERT")
        {
            try
            {
                statement.setString(1, this.wordText);
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
                statement.setString(1, this.wordText);
                return statement;
            }
            catch (SQLException e)
            {
            }
        }
        return null;
    }

    
}
