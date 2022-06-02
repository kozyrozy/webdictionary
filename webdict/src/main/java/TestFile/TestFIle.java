package TestFIle;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.LinkedList;

import DictionaryProcesser.Dictionary;
import DictionaryProcesser.MyTreeBrowser;

import DataBaseController.DBDictionaryController;

import Logger.Logger;

import DataBaseController.TableCrud.DBFileCrud;
import DataBaseController.TableCrud.DBWordCrud;
import DataBaseController.TableCrud.DBWordFileMappingCrud;
import DataBaseController.DBWorker;

import Logger.Logger;

@WebServlet("/TestFIle")
public class TestFIle extends HttpServlet {


    private static final long serialVersionUID = -3462096228274971485L;
    
    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
            throws ServletException, IOException 
    {
        
        String file = "D:\\Programming\\VS code\\Projects\\Java prj\\webdictionary\\InputText\\Text.txt";

        DBWorker dbWorker = new DBWorker();
        dbWorker.test_connection();

        // try
        // {
            ResultSet resFile = DBFileCrud.dbcrud_file_browse(dbWorker, file);
            // if (resFile != null)
            // {
            //     resFile.next();
            //     Integer fileID = resFile.getInt("FileID");
            //     resFile.close();
            //     Logger.setLog("TestFIle ID: " + fileID);
            // }
            // else 
            // {
            //     Logger.setLog("resFile is null"); 
            // }

        // }
        // catch (SQLException e)
        // {

        // }        
        Logger.save_to_file();
        response.getWriter().append("TestFIle log: " + Logger.getLog());


    }

}