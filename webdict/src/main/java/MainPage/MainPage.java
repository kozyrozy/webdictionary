package MainPage;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainPage")
public class MainPage extends HttpServlet 
{
    private static final long serialVersionUID = -3462096228274971485L;
    
    @Override
    protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
            throws ServletException, IOException 
    {
        String rsp =    "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Заголовок документа</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <form action=\"http://localhost:8080/webdict/ResultPage\" method=\"get\">\n" +
                        "    write word here: <input type=\"text\" name=\"word\" />\n" +
                        "    <input type = \"submit\"/>\n" +
                        "    </form>\n" +
                        "</body>\n" +
                        "</html>\n";
                
        response.getWriter().append(rsp);
    }
}

