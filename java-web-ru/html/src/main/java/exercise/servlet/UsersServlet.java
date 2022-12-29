package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String content = Files.readString(Path.of("src/main/resources/users.json"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<List<Map<String, String>>>() { });
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> usersList = getUsers();
        StringBuilder body = new StringBuilder();
        body.append("""
                <html>
                    <body>
                        <table>
                """);
        for (Map<String, String> user : usersList) {
            body.append("<tr><td>" + user.get("id") + "</td>");
            body.append("<td><a href=\"/users/" + user.get("id") + "\">" + user.get("firstName") + " "
                    + user.get("lastName") + "</a></td>");
            body.append("<td>" + user.get("email") + "</td><tr>");
        }
        body.append("""
                        </table>
                    </body>
                </html>
                """);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> usersList = getUsers();
        StringBuilder body = new StringBuilder();

        for (Map<String, String> user : usersList) {
            if(user.get("id").equals(id)) {
                body.append("""
                <html>
                    <body>
                        <table>
                """);
                body.append("<tr><td>" + user.get("id") + "</td>");
                body.append("<td><a href=\"/users/" + user.get("id") + "\">" + user.get("firstName") + " "
                        + user.get("lastName") + "</a></td>");
                body.append("<td>" + user.get("email") + "</td><tr>");
                body.append("""
                        </table>
                    </body>
                </html>
                """);
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(body);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        // END
    }
}
