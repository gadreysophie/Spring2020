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

    public Utilisateur() {
    }

    public Utilisateur (String nom) {
        super(nom);
    }

    public Utilisateur (String nom, String prenom, String identifiant, String mail, String mdp) {
        super(nom, prenom, identifiant, mail, mdp);
    }

    public Utilisateur (Long id, String nom, String prenom, String identifiant, String mail, String mdp) {
        super(id, nom, prenom, identifiant, mail, mdp);
    }

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.PERSIST)
    @JsonIgnore
    public List<Rdv> getRdvs() {
        return rdvs;
    }

    public void setRdvs(List<Rdv> rdvs){
        this.rdvs = rdvs;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + getId() + ", nom=" + getNom() + ", prénom=" + getPrenom() + "]";
    }
}
