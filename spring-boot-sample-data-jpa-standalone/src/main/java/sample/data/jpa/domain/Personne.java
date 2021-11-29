package sample.data.jpa.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@XmlRootElement(name = "personne")
public abstract class Personne {

    private Long id;
    private String nom;
    private String identifiant;
    private String mdp;
    private String mail;
    private String prenom;

    /**
     * To instantiate person
     */
    public Personne(){
    }

    /**
     * To create person on the database
     * @param nom the name of the person
     */
    public Personne(String nom) {
        this.nom = nom;
    }

    /**
     * To create a person on the database
     * @param nom the name of the person
     * @param prenom the lastname of the person
     * @param identifiant the login of the person
     * @param mail the email of the person
     * @param mdp the password of the person
     */
    public Personne(String nom, String prenom, String identifiant, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    /**
     * To create a person on the database
     * @param id the id of the person
     * @param nom the name of the person
     * @param prenom the lastname of the person
     * @param identifiant the login of the person
     * @param mail the email of the person
     * @param mdp the password of the person
     */
    public Personne(Long id, String nom, String prenom, String identifiant, String mail, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

    /**
     * To get the id of the person
     * @return the id of the person
     */
    @Id
    @GeneratedValue(generator="generatorIdPersonne")
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    /**
     * To set the id of the person
     * @param id the Long id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the name of the person
     * @return the String name
     */
    public String getNom() {
        return nom;
    }

    /**
     * To set the name of the person
     * @param nom the name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * To get the lastname of the person
     * @return the String lastname
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * To set the lastname of the person
     * @param prenom the lastname to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * To get the login of the person
     * @return the String login which is unique
     */
    @Column(unique = true)
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * To set the login of the person
     * @param identifiant the String login
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * To get the password of the person
     * @return the String password
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * To set the password of the person
     * @param mdp the String password
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * To get of email of the person
     * @return to String email of the person which is unique
     */
    @Column(unique = true)
    public String getMail() {
        return mail;
    }

    /**
     * To set the email of the person
     * @param mail the String email of the person
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}
