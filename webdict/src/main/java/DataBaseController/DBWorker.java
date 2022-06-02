package DataBaseController;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import DataBaseController.TableDescriptor.AbstractTableDescriptor;

import Logger.Logger;
import java.sql.ResultSet;


public class DBWorker {
    private String url = "jdbc:postgresql://localhost:5432/WordDictionary";
    private String name = "postgres";
    private String password = "12345678";


    public void test_connection()
    {
        Logger.setLog("DBWorker method test_connection");
        
        Connection conn = null;
        try 
        {
            conn = DriverManager.getConnection(this.url, this.name, this.password);
        }
        catch (SQLException e)
        {
            Logger.setLog("Connection Failed. Messsage: " + e);
            //System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (conn != null) {
            Logger.setLog("You successfully connected to database now");
            try 
            {
                conn.close();
            }
            catch (SQLException e)
            {

            }
            //System.out.println("You successfully connected to database now");
        } else {
            Logger.setLog("Failed to make connection to database");
            //System.out.println("Failed to make connection to database");
        }
    }


    public boolean execute(AbstractTableDescriptor tbDescr)
    {
        boolean resStatus = false;

        Connection conn = null;

        Logger.setLog("DBWorker method execute");

        try 
        {
            conn = DriverManager.getConnection(this.url, this.name, this.password);
        }
        catch (SQLException e)
        {
            Logger.setLog("Connection Failed");
            //System.out.println("Connection Failed");
            e.printStackTrace();
            resStatus = false;
        }

        if (conn != null)
        {
            Logger.setLog("Connected");
            //System.out.println("Connected");
            System.out.println(tbDescr.get_sql_template());
            try
            {
                PreparedStatement st = conn.prepareStatement(tbDescr.get_sql_template());
                st = tbDescr.prepare_statement(st);
                resStatus = tbDescr.execute(st);
            }
            catch(SQLException e)
            {
                Logger.setLog("SQL Failed");
                //System.out.println("SQL Failed");
                e.printStackTrace();
                resStatus = false;
            }

            try
            {
                conn.close();
                Logger.setLog("Connection closed");
                //System.out.println("Connection closed");
                resStatus = true;
            }
            catch(SQLException e)
            {
                Logger.setLog("Connection closing Failed");
                //System.out.println("Connection closing Failed");
                e.printStackTrace();
                resStatus = false;
            }

        }

        return resStatus;
    }

    


}
