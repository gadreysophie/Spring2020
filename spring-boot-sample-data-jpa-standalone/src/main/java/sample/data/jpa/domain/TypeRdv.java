package sample.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name = "typeRdv")
@NamedQueries(
        {
                @NamedQuery(name="typeRdvParId", query="SELECT t FROM TypeRdv t WHERE t.id =:id"),
                @NamedQuery(name="tousLesTypeRdvParProf", query="SELECT t FROM TypeRdv t WHERE t.professionnel =:prof"),
                @NamedQuery(name="minDureeTypeRdvByProf", query="SELECT MIN(t.duree) FROM TypeRdv t WHERE t.professionnel =:prof"),
        }
)

public class TypeRdv {

    /**
     * The id of the type of rdv
     */
    private Long id;

    /**
     * The name of the type of rdv
     */
    private String nom;

    /**
     * The professional of the type of rdv
     */
    private Professionnel professionnel;

    /**
     * The duration of the type of rdv
     */
    private Integer duree;

    /**
     * The list of rdvs of the type of rdv
     */
    private List<Rdv> rdvs = new ArrayList<>();

    public TypeRdv() {
    }

    public TypeRdv(String nom, Professionnel professionnel, Integer duree) {
        this.nom = nom;
        this.professionnel = professionnel;
        this.duree = duree;
    }

    public TypeRdv(Long id, String nom, Professionnel professionnel, Integer duree) {
        this.id = id;
        this.nom = nom;
        this.professionnel = professionnel;
        this.duree = duree;
    }

    /**
     * To get the id of the type of rdv
     * @return the id of the type of rdv
     */
    @Id
    @GeneratedValue(generator="generatorIdTypeRdv")
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    /**
     * To set the id of the type of rdv
     * @param id a long id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the duration of the type of rdv
     * @return an integer
     */
    public Integer getDuree() {
        return duree;
    }

    /**
     * To set the duration of the type of rdv
     * @param duree an integer
     */
    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    /**
     * To get the name of the type of rdv
     * @return a string
     */
    public String getNom() {
        return nom;
    }

    /**
     * To set the name of the type of rdv
     * @param nom the string name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * To get the professional of the type of rdv
     * @return a professional
     */
    @ManyToOne
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    /**
     * To set the professional of the type of rdv
     * @param professionnel a professional
     */
    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }

    /**
     * To get the list of rdv associates to the type of rdv
     * @return a list of rdvs
     */
    @OneToMany(mappedBy = "typeRdv", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    /**
     * To set the list of rdv associates to the type of rdv
     * @param rdvs a list of rdvs
     */
    public void setRdvs(List<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

    /**
     * To get a string to describe the type of rdv with its data
     * @return a string
     */
    @Override
    public String toString() {
        return "Type de RDV [id=" + id + ", professionnel=" + professionnel + ", nom=" + nom + ", durée=" + duree + "]";
    }
}
