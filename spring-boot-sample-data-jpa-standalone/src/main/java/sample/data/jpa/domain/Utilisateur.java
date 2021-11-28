package sample.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("U")
@XmlRootElement(name = "user")
@NamedQueries(
        {
                @NamedQuery(name="searchUserById", query="SELECT u FROM Utilisateur u WHERE u.id =:id")
        }
)
public class Utilisateur extends Personne {

    private List<Rdv> rdvs = new ArrayList<>();

    /**
     * To instantiate user
     */
    public Utilisateur() {
    }

    /**
     * To create user on the database
     * @param nom the name of the user
     */
    public Utilisateur (String nom) {
        super(nom);
    }

    /**
     * To create user on the database
     * @param nom the name of the user
     * @param prenom the lastname of the user
     * @param identifiant the login of the user
     * @param mail the email of the user
     * @param mdp the password of the user
     */
    public Utilisateur (String nom, String prenom, String identifiant, String mail, String mdp) {
        super(nom, prenom, identifiant, mail, mdp);
    }

    /**
     * To create the user on the database
     * @param id the id of the user
     * @param nom the name of the user
     * @param prenom the lastname of the user
     * @param identifiant the login of the user
     * @param mail the email of the user
     * @param mdp the password of the user
     */
    public Utilisateur (Long id, String nom, String prenom, String identifiant, String mail, String mdp) {
        super(id, nom, prenom, identifiant, mail, mdp);
    }

    /**
     * To get the list of rdv of the user
     * @return a list of rdv
     */
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    /**
     * To set rdv for the user
     * @param rdvs a list of rdv
     */
    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }

    /**
     * To display data on the user
     * @return a String of data on the user
     */
    @Override
    public String toString() {
        return "Utilisateur [id=" + getId() + ", nom=" + getNom() + ", prénom=" + getPrenom() + "]";
    }
}
