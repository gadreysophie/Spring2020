package sample.data.jpa.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.Date;

@Entity
@XmlRootElement(name = "rdv")
@NamedQueries(
        {
                @NamedQuery(name="tousLesRdvParId", query="SELECT r FROM Rdv r WHERE r.id =:id"),
                @NamedQuery(name="tousLesRdvParProfEtDate", query="SELECT r FROM Rdv r WHERE r.professionnel=:prof AND  r.dateDebut >=:date AND r.dateFin <=: date2")
        }
)

public class Rdv {

    /**
     * The id of the rdv
     */
    private Long id;

    /**
     * The type of rdv of the rdv
     */
    private TypeRdv typeRdv;

    /**
     * The professional of the rdv
     */
    private Professionnel professionnel;

    /**
     * The user of the rdv
     */
    private Utilisateur utilisateur;

    /**
     * The start date of the rdv
     */
    private Date dateDebut;

    /**
     * The end date of the rdv
     */
    private Date dateFin;

    public Rdv() {
    }

    public Rdv(TypeRdv typeRdv, Professionnel professionnel, Utilisateur utilisateur, Date dateDebut) {
        this.typeRdv = typeRdv;
        this.professionnel = professionnel;
        this.utilisateur = utilisateur;
        this.dateDebut = dateDebut;

        // dateFin = dateDebut + typeRdv.getDuree()
        Calendar c = Calendar.getInstance();
        c.setTime(dateDebut);
        c.add(Calendar.MINUTE, typeRdv.getDuree());
        this.dateFin = c.getTime();
    }

    public Rdv(Long id, TypeRdv typeRdv, Professionnel professionnel, Utilisateur utilisateur, Date dateDebut) {
        this.id = id;
        this.typeRdv = typeRdv;
        this.professionnel = professionnel;
        this.utilisateur = utilisateur;
        this.dateDebut = dateDebut;

        Calendar c = Calendar.getInstance();
        c.setTime(dateDebut);
        c.add(Calendar.MINUTE, typeRdv.getDuree());
        this.dateFin = c.getTime();
    }

    /**
     * To get the id of the rdv
     * @return the id of the rdv
     */
    @Id
    @GeneratedValue(generator="generatorIdRdv")
    public Long getId() {
        return id;
    }

    /**
     * To set the id of the rdv
     * @param id a long id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the type of rdv of the rdv
     * @return a type of rdv
     */
    @ManyToOne
    public TypeRdv getTypeRdv() {
        return typeRdv;
    }

    /**
     * To set the type of rdv of the rdv
     * @param typeRdv a type of rdv
     */
    public void setTypeRdv(TypeRdv typeRdv) {
        this.typeRdv = typeRdv;
    }

    /**
     * To get the professional of the rdv
     * @return a professional
     */
    @ManyToOne
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    /**
     * To set the professional of the rdv
     * @param professionnel a professional
     */
    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }

    /**
     * To get the user of the rdv
     * @return a user
     */
    @ManyToOne
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * To set the user of the rdv
     * @param utilisateur a user
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * To get the start date of the rdv
     * @return a date
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * To set the start date of the rdv
     * @param dateDebut the start date of the rdv
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * To get the end date of the rdv
     * @return a date
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * To set the end date of the rdv
     * @param dateFin the end date of the rdv
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * To get a string to describe the rdv with its data
     * @return a string
     */
    @Override
    public String toString() {
        return "RDV [id=" + id + ", Type de RDV=" + typeRdv + ", professionnel=" + professionnel + ", " +
                "utilisateur=" + utilisateur + ", Date début=" + dateDebut + ", date fin=" + dateFin + "]";
    }
}
