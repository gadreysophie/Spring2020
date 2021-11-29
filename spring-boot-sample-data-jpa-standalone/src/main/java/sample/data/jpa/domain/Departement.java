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

    private Long id;
    private String nom;
    private List<Professionnel> professionnels = new ArrayList<>();

    /**
     * To create the instance of department for the database
     */
    public Departement() {
    }

    /**
     * To create the Department on the database
     * @param nom the name of the department
     */
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
    */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the name
     * @return the name of the department
     */
    @Column(unique = true)
    public String getNom() {
        return nom;
    }

    /**
     * To set the name
     * @param nom the name of the department
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * To display data on the department
     * @return a String of data on the department
     */
    @Override
    public String toString() {
        return "Departement [id=" + id + ", nom=" + nom + "]";
    }

    /**
     * To get the list of professionals who are in the department
     * @return a list of professionals
     */
    @OneToMany(mappedBy = "departement", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Professionnel> getProfessionnels() {
        return professionnels;
    }

    /**
     * To set the list of professionals that are in the department
     * @param professionnels the list of professionals
     */
    public void setProfessionnels(List<Professionnel> professionnels) {
        this.professionnels = professionnels;
    }
}