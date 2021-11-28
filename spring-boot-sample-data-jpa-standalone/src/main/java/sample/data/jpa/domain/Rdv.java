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

    private Long id;
    private TypeRdv typeRdv;
    private Professionnel professionnel;
    private Utilisateur utilisateur;
    private Date dateDebut;
    private Date dateFin;

    /**
     * To instantiate rdv
     */
    public Rdv() {
    }

    /**
     * To create rdv on the database
     * @param typeRdv the type of rdv
     * @param professionnel the professional
     * @param utilisateur the user
     * @param dateDebut the beginning date
     */
    public Rdv(TypeRdv typeRdv, Professionnel professionnel, Utilisateur utilisateur, Date dateDebut) {
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
     * To create rdv on the database
     * @param id the rdv id
     * @param typeRdv the type of rdv
     * @param professionnel the professional
     * @param utilisateur the user
     * @param dateDebut the beginning date of the rdv
     */
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
     * To get the id of the rdv on the database
     * @return the id
     */
    @Id
    @GeneratedValue(generator="generatorIdRdv")
    public Long getId() {
        return id;
    }

    /**
     * To set the id
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the type of rdv of the rdv
     * @return the type of rdv
     */
    @ManyToOne
    public TypeRdv getTypeRdv() {
        return typeRdv;
    }

    /**
     * To set the type of rdv of the rdv
     * @param typeRdv the type of rdv
     */
    public void setTypeRdv(TypeRdv typeRdv) {
        this.typeRdv = typeRdv;
    }

    /**
     * To get the professional of the rdv
     * @return the professional
     */
    @ManyToOne
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    /**
     * To set the professional of the rdv
     * @param professionnel the professional
     */
    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }

    /**
     * To get the user of the rdv
     * @return the user
     */
    @ManyToOne
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * To set the user of the rdv
     * @param utilisateur the user
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * To get the beginning date of the rdv
     * @return the beginning date
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * To set the beginning date of the rdv
     * @param dateDebut the beginning date
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * To get the end date of the rdv
     * @return the end date of the rdv
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * To set the end date of the rdv
     * @param dateFin the end date
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * To display data on the rdv
     * @return a String of data on the rdv
     */
    @Override
    public String toString() {
        return "RDV [id=" + id + ", Type de RDV=" + typeRdv + ", professionnel=" + professionnel + ", " +
                "utilisateur=" + utilisateur + ", Date début=" + dateDebut + ", date fin=" + dateFin + "]";
    }
}
