package sample.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "dept")
@NamedQueries(
        {
                @NamedQuery(name="departementParId", query="SELECT p FROM Departement p WHERE p.id =:id")
        }
)
public class Departement {

    /**
     * The id of the department
     */
    private Long id;

    /**
     * The name of the department
     */
    private String nom;

    /**
     * The list of Professionnel of the departement
     */
    private List<Professionnel> professionnels = new ArrayList<>();

    public Departement() {
    }

    public Departement(String nom) {
        this.nom = nom;
    }

    public Departement(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    /**
     * To get the id of the department
     * @return the id of the department
     */
    @Id
    @GeneratedValue(generator="generatorIdDepartement")
    public Long getId() {
        return id;
    }

    /**
    * To set the id of the department
     * @param id a long id
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the name of the department
     * @return the name of the department
     */
    @Column(unique = true)
    public String getNom() {
        return nom;
    }

    /**
     * To set the name of the department
     * @param nom the name of the department
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * To get a string to describe the departement with its data
     * @return a string
     */
    @Override
    public String toString() {
        return "Departement [id=" + id + ", nom=" + nom + "]";
    }

    /**
     * To get the list of professionals of the department
     * @return a list of professionals
     */
    @OneToMany(mappedBy = "departement", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Professionnel> getProfessionnels() {
        return professionnels;
    }

    /**
     * To set the list of professionals of the department
     * @param professionnels a list of professionals
     */
    public void setProfessionnels(List<Professionnel> professionnels) {
        this.professionnels = professionnels;
    }
}