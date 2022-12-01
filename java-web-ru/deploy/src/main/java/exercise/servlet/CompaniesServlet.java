package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        PrintWriter outputWriter = response.getWriter();
        String searchParam = request.getParameter("search");

        if (searchParam != null && searchParam.length() != 0) {
            companies = companies.stream()
                    .filter(company -> company.contains(searchParam))
                    .collect(Collectors.toList());
        }


        if (companies.size() == 0) {
            outputWriter.println("Companies not found");
        }
        companies.stream()
                .forEach(company -> outputWriter.println(company));
        // END
    }
}
