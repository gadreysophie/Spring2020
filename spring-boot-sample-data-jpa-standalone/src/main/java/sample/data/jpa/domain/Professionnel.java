package sample.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("P")
@XmlRootElement(name = "prof")
@NamedQueries(
        {
                @NamedQuery(name="tousLesProfessionnelsParNom", query="SELECT p FROM Professionnel p WHERE p.nom LIKE CONCAT('%',:name,'%') ORDER BY p.nom"),
                @NamedQuery(name="professionnelParId", query="SELECT p FROM Professionnel p WHERE p.id =:id")
        }
)
public class Professionnel extends Personne {

    private Departement departement;
    private List<Rdv> rdvs = new ArrayList<>();
    private Time heureDebut;
    private Time heureFin;
    private Time heureDebutPause;
    private Time heureFinPause;
    private String joursDePresence; // Attention : du dimanche au samedi
    private List<TypeRdv> typeDeRdv;

    /**
     * To create the instance of professional for the database
     */
    public Professionnel() {
    }

    /**
     * To create the professional
     * @param name the name of the professional
     * @param department the department of the professional
     * @param heureDebut the beginning time
     * @param heureFin the end time
     * @param heureDebutPause the beginning break time
     * @param heureFinPause the end break time
     * @param joursDePresence the days of work
     */
    public Professionnel(String name, Departement department, Time heureDebut, Time heureFin, Time heureDebutPause,
                         Time heureFinPause, String joursDePresence) {
        super(name);
        this.departement = department;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.heureDebutPause = heureDebutPause;
        this.heureFinPause = heureFinPause;
        this.joursDePresence = joursDePresence;
    }

    /**
     * To create a professional
     * @param nom the name of the professional
     * @param prenom the lastname of the professional
     * @param identifiant the username of the professional
     * @param mail the email of the professional
     * @param mdp the passwword of the professional
     * @param department the department of the professional
     * @param heureDebut the beginning time
     * @param heureFin the end time
     * @param heureDebutPause the beginning time of break
     * @param heureFinPause the end time of break
     * @param joursDePresence the day of work
     */
    public Professionnel(String nom, String prenom, String identifiant, String mail, String mdp, Departement department,
                         Time heureDebut, Time heureFin, Time heureDebutPause, Time heureFinPause,
                         String joursDePresence) {
        super(nom, prenom, identifiant, mail, mdp);
        this.departement = department;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.heureDebutPause = heureDebutPause;
        this.heureFinPause = heureFinPause;
        this.joursDePresence = joursDePresence;
    }

    /**
     * To create a professional
     * @param id the professional id
     * @param nom the name of the professional
     * @param prenom the lastname of the professional
     * @param identifiant the username of the professional
     * @param mail the email of the professional
     * @param mdp the password of the professional
     * @param department the department of the professional
     * @param heureDebut the beginning time of the work day
     * @param heureFin the end time of the work day
     * @param heureDebutPause the beginning break time
     * @param heureFinPause the end break time
     * @param joursDePresence the day of work
     */
    public Professionnel(Long id, String nom, String prenom, String identifiant, String mail, String mdp,
                         Departement department, Time heureDebut, Time heureFin, Time heureDebutPause,
                         Time heureFinPause, String joursDePresence) {
        super(id, nom, prenom, identifiant, mail, mdp);
        this.departement = department;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.heureDebutPause = heureDebutPause;
        this.heureFinPause = heureFinPause;
        this.joursDePresence = joursDePresence;
    }

    /**
     * To get the department
     * @return the department of the professional
     */
    @ManyToOne
    public Departement getDepartement() {
        return departement;
    }

    /**
     * To set the department of the professional
     * @param departement the department set
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    /**
     * To get the list of rdv of the professional
     * @return
     */
    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    /**
     * To set rdv for the professional
     * @param rdvs the rdv set
     */
    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }
    /**
    * To get the beginning time
    */
    public Time getHeureDebut() {
        return heureDebut;
    }

    /**
     * To set the beginning time
     * @param heureDebut the beginning time of the work day
     */
    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * To get the end time
     * @return the end time
     */
    public Time getHeureFin() {
        return heureFin;
    }

    /**
     * To set the end time of the day
     * @param heureFin the end time of work day
     */
    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * To get the beginning time of the breal
     * @return the beginning time break
     */
    public Time getHeureDebutPause() {
        return heureDebutPause;
    }

    /**
     * To set beginning time break
     * @param heureDebutPause the beginning time break
     */
    public void setHeureDebutPause(Time heureDebutPause) {
        this.heureDebutPause = heureDebutPause;
    }

    /**
     * To get the end time break
     * @return the end time break
     */
    public Time getHeureFinPause() {
        return heureFinPause;
    }

    /**
     * To set end time break
     * @param heureFinPause the end time break
     */
    public void setHeureFinPause(Time heureFinPause) {
        this.heureFinPause = heureFinPause;
    }

    /**
     * To get time of work day
     * @return the day of works for the professional
     */
    public String getJoursDePresence() {
        return joursDePresence;
    }

    /**
     * TO set work days
     * @param joursDePresence the work days
     */
    public void setJoursDePresence(String joursDePresence) {
        this.joursDePresence = joursDePresence;
    }

    /**
     * To get the list of type fo rdv for the professional
     * @return the type fo rdv
     */
    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<TypeRdv> getTypeDeRdv() {
        return typeDeRdv;
    }

    /**
     * To set the type of rdv for the professional
     * @param typeDeRdv the type of rdv
     */
    public void setTypeDeRdv(List<TypeRdv> typeDeRdv) {
        this.typeDeRdv = typeDeRdv;
    }

    /**
     * To display data on the professional
     * @return a String of data on the professional
     */
    @Override
    public String toString() {
        return "Professionnel [id=" + getId() + ", nom=" + getNom() + ", prénom=" + getPrenom() + ", département="
                + departement.getNom() + "]";
    }
}

