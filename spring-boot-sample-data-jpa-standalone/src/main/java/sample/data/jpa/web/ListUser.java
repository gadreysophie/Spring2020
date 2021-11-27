package sample.data.jpa.web;

import sample.data.jpa.domain.Utilisateur;
import sample.data.jpa.rest.UtilisateurResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="listuser",
        urlPatterns={"/ListUser"})
public class ListUser extends HttpServlet {

    /**
     * to get the list of User in the database
     * @param request the http request
     * @param response the http response
     * @throws IOException exception
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UtilisateurResource utilisateurResource = new UtilisateurResource();

        response.setContentType("text/html");

        StringBuilder listUsers = new StringBuilder();
        for (Utilisateur next : utilisateurResource.getUsers()) {
            listUsers.append(" <LI>Nom : ").append(next.getNom()).append(", Prénom : ").append(next.getPrenom());
        }

        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Liste des utilisateurs</H1>\n" +
                "<UL>\n" +
                listUsers
                +
                "</UL>\n"+
                "</BODY></HTML>");
    }
}
