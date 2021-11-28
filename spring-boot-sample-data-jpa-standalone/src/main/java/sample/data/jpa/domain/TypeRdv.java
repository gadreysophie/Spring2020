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

    private Long id;
    private String nom;
    private Professionnel professionnel;
    private Integer duree;
    private List<Rdv> rdvs = new ArrayList<>();

    /**
     * To instantiate type of rdv
     */
    public TypeRdv() {
    }

    /**
     * To create type of rdv on the database
     * @param nom the name of the type of rdv
     * @param professionnel the professional whom type of rdv belong to
     * @param duree the length of the type of rdv
     */
    public TypeRdv(String nom, Professionnel professionnel, Integer duree) {
        this.nom = nom;
        this.professionnel = professionnel;
        this.duree = duree;
    }

    /**
     * To create type of rdv on the database
     * @param id the id of the type of rdv
     * @param nom the name of the type of rdv
     * @param professionnel the professional whom type of rdv belong to
     * @param duree the length of the type of rdv
     */
    public TypeRdv(Long id, String nom, Professionnel professionnel, Integer duree) {
        this.id = id;
        this.nom = nom;
        this.professionnel = professionnel;
        this.duree = duree;
    }

    /**
     * To get the id of the type of rdv
     * @return the id of the type of rdv autogenerated
     */
    @Id
    @GeneratedValue(generator="generatorIdTypeRdv")
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    /**
     * To set the id of the type of rdv
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the length of the type of rdv
     * @return the length
     */
    public Integer getDuree() {
        return duree;
    }

    /**
     * To set the length of the type of rdv
     * @param duree a Integer which is the length of type of rdv
     */
    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    /**
     * To get the name of type of rdv
     * @return a String name
     */
    public String getNom() {
        return nom;
    }

    /**
     * To set the name of the type of rdv
     * @param nom the String name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * To get the professional who uses the type of rdv
     * @return the professional
     */
    @ManyToOne
    public Professionnel getProfessionnel() {
        return professionnel;
    }

    /**
     * To set professional who uses the type of rdv
     * @param professionnel the professional
     */
    public void setProfessionnel(Professionnel professionnel) {
        this.professionnel = professionnel;
    }

    /**
     * To get the list of rdv which use the type of rdv
     * @return the list of rdv
     */
    @OneToMany(mappedBy = "typeRdv", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    /**
     * To set the list of rdv that use the type of rdv
     * @param rdvs the list of rdv
     */
    public void setRdvs(List<Rdv> rdvs) {
        this.rdvs = rdvs;
    }

    /**
     * To display data on the type of rdv
     * @return a String with data on the type of rdv
     */
    @Override
    public String toString() {
        return "Type de RDV [id=" + id + ", professionnel=" + professionnel + ", nom=" + nom + ", durée=" + duree + "]";
    }
}
