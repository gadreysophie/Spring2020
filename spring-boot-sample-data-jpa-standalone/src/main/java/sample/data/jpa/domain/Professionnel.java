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

    /**
     * The department of the professional
     */
    private Departement departement;

    /**
     * The list of rdvs of the professional
     */
    private List<Rdv> rdvs = new ArrayList<>();

    /**
     * The start time of the professional
     */
    private Time heureDebut;

    /**
     * The end time of the professional
     */
    private Time heureFin;

    /**
     * The break start time of the professional
     */
    private Time heureDebutPause;

    /**
     * The break end time of the professional
     */
    private Time heureFinPause;

    /**
     * Days of presence of the professional.
     * A binary string of 7-day from Sunday to Saturday.
     */
    private String joursDePresence; // Attention : du dimanche au samedi

    /**
     * The list of types of rdv of the professional
     */
    private List<TypeRdv> typeDeRdv;

    public Professionnel() {
    }

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
     * To get the department of the professional
     * @return a department
     */
    @ManyToOne
    public Departement getDepartement() {
        return departement;
    }

    /**
     * To set the department of the professional
     * @param departement a department
     */
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    /**
     * To get the list of rdvs of the professional
     * @return a list of rdvs
     */
    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    /**
     * To set a list of rdvs for the professional
     * @param rdvs a list of rdvs
     */
    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }

    /**
     * To get the start time of the professional
     * @return a time
     */
    public Time getHeureDebut() {
        return heureDebut;
    }

    /**
     * To set the start time of the professional
     * @param heureDebut Time of the start time of the professional
     */
    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * To get the end time of the professional
     * @return a time
     */
    public Time getHeureFin() {
        return heureFin;
    }

    /**
     * To set the end time of the professional
     * @param heureFin Time of the end time of the professional
     */
    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * To get the break start time of the professional
     * @return a time
     */
    public Time getHeureDebutPause() {
        return heureDebutPause;
    }

    /**
     * To set the break start time of the professional
     * @param heureDebutPause Time of the break start time of the professional
     */
    public void setHeureDebutPause(Time heureDebutPause) {
        this.heureDebutPause = heureDebutPause;
    }

    /**
     * To get the break end time of the professional
     * @return a time
     */
    public Time getHeureFinPause() {
        return heureFinPause;
    }

    /**
     * To set the break end time of the professional
     * @param heureFinPause Time of the break end time of the professional
     */
    public void setHeureFinPause(Time heureFinPause) {
        this.heureFinPause = heureFinPause;
    }

    /**
     * To get days of presence of the professional
     * @return a string
     */
    public String getJoursDePresence() {
        return joursDePresence;
    }

    /**
     * To set days of presence of the professional
     * @param joursDePresence a string of binary
     */
    public void setJoursDePresence(String joursDePresence) {
        this.joursDePresence = joursDePresence;
    }

    /**
     * To get the list of type of rdv of the professional
     * @return a list of type of rdv
     */
    @OneToMany(mappedBy = "professionnel", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<TypeRdv> getTypeDeRdv() {
        return typeDeRdv;
    }

    /**
     * To set the list of type of rdv of the professional
     * @param typeDeRdv a list of type of rdv
     */
    public void setTypeDeRdv(List<TypeRdv> typeDeRdv) {
        this.typeDeRdv = typeDeRdv;
    }

    /**
     * To get a string to describe the professional with its data
     * @return a string
     */
    @Override
    public String toString() {
        return "Professionnel [id=" + getId() + ", nom=" + getNom() + ", prénom=" + getPrenom() + ", département="
                + departement.getNom() + "]";
    }
}

