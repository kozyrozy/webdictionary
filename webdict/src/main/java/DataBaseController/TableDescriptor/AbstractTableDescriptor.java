package DataBaseController.TableDescriptor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import Logger.Logger;


public abstract class  AbstractTableDescriptor {

    protected String statementType = ""; 
    protected ResultSet resSet = null;

    public abstract String get_sql_template();
    public abstract PreparedStatement prepare_statement(PreparedStatement statement);
   
    public String get_statement_type()
    {
        return statementType;
    }
    
    public boolean execute(PreparedStatement statement)
    {
        if (this.statementType == "SELECT" || this.statementType == "SELECTBYID") 
        {
            try
            {
                Logger.setLog("SELECT statement: " + statement);
                this.resSet = statement.executeQuery();
                if (this.resSet != null)
                {
                    Logger.setLog("this.resSet not null");
                }
                else 
                {
                    Logger.setLog("this.resSet is null");
                }
            }
            catch (SQLException e)
            {
                // e.printStackTrace();
                Logger.setLog("SQLException: " + e.toString());
                System.out.println(e);
                e.printStackTrace();
                return false;
            }
            catch (NullPointerException e)
            {
                Logger.setLog("NullPointerException: " + e.toString());
            }
        }
        else
        {
            try
            {
                Logger.setLog("INSERT statement: " + statement);
                statement.executeUpdate();
                statement.close(); 
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public ResultSet get_resSet()
    {
        return this.resSet;
    }
}
