package ResultPage;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import Logger.Logger;

import java.util.ArrayList;
import DataBaseController.DBDictionaryController;

@WebServlet("/ResultPage")
public class ResultPage extends HttpServlet 
{
    
    private static final long serialVersionUID = -3462096228274971485L;
    
    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
            throws ServletException, IOException 
    {

      Enumeration params = reqest.getParameterNames();


      while(params.hasMoreElements())
      {
        String param = (String)params.nextElement();
        Logger.setLog("Search files for word: " + reqest.getParameter(param) + "\n");

        ArrayList<String> res = DBDictionaryController.get_files_for_word(reqest.getParameter(param));

        String rsp =    "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "  border-collapse: collapse;\n" +
                        "}\n" +
                        "th, td {\n" +
                        "  padding: 5px;\n" +
                        "  text-align: left;    \n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "\n" +
                        "<table style=\"width:100%\">\n" +
                        "  <tr>\n" +
                        "    <th>Files</th>\n" +
                        "  </tr>\n";
                        

        String rspEnd = "</table>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n";


        for (String file : res) 
        {
          rsp += "<tr>\n<td>" + file + "</td>\n<tr>\n";
        }

        rsp += rspEnd;
        Logger.save_to_file();
        response.getWriter().append(rsp);
        //response.getWriter().append("parseFiles log: " + Logger.getLog());

      }

    }
}

