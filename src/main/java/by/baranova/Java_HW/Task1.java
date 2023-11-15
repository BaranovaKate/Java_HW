package by.baranova.Java_HW;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
@WebServlet(
        name = "Task1",
        urlPatterns = "/time"
)
public class Task1 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Date currentTime = new Date();
        String timeZoneParam = req.getParameter("zone");

        if (timeZoneParam != null && !timeZoneParam.isEmpty()) {
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneParam);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(timeZone);
            String timeInZone = sdf.format(currentTime);
            out.println("Current Time in " + timeZone.getID() + ": " + timeInZone);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String currentTimeStr = sdf.format(currentTime);
            out.println("Current Time: " + currentTimeStr);
        }
    }
}
