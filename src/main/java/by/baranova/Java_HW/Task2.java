package by.baranova.Java_HW;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
        name = "Task2",
        urlPatterns = "/names"
)
public class Task2 extends HttpServlet {

    private static final Set<String> names = new HashSet<>();
    static {
        names.add("Kate");
        names.add("Ilya");
        names.add("Bob");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Names: " + names);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        if (name == null || name.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("Error: Name is required in the request.");
            return;
        }

        if (names.contains(name)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            out.println("Error: Name already exists.");
        } else {
            names.add(name);
            out.println("Name added: " + name);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        if (names.contains(name)) {
            names.remove(name);
            out.println("Name deleted: " + name);
        } else {

            //  response.sendError(404, "Error: Name not found.");
            // Можно по другому написать
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.println("Error: Name not found.");
        }

    }
}