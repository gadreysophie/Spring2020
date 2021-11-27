package sample.data.jpa.web;

import sample.data.jpa.domain.Utilisateur;
import sample.data.jpa.rest.UtilisateurResource;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="adduser",
        urlPatterns={"/AddUser"})
public class AddUser extends HttpServlet {

    /**
     * to add a user in the database
     * @param request the http request
     * @param response the http response
     * @throws IOException exception
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Création de l'objet utilisateur
        Utilisateur user = new Utilisateur();
        user.setNom(request.getParameter("nom"));
        user.setPrenom(request.getParameter("prenom"));

        // Ajout de données à la database
        UtilisateurResource userRessource = new UtilisateurResource();
        userRessource.addUser(user);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n<BODY>\n" +
                "<H1>Récapitulatif des informations de l'utilisateur</H1>\n" +
                "<UL>\n" +
                " <LI>Nom : "
                + request.getParameter("nom") +
                ", Prénom : "
                + request.getParameter("prenom") +
                "</UL>\n" +
                "</BODY></HTML>");
    }
}