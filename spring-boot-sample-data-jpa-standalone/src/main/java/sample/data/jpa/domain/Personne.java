package sample.data.jpa.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
    @Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@XmlRootElement(name = "personne")
public abstract class Personne {

    /**
     * The id of the person
     */
    private Long id;

    /**
     * The last name of the person
     */
    private String nom;

    /**
     * The login of the person
     */
    private String identifiant;

    /**
     * The password of the person
     */
    private String mdp;

    /**
     * The E-mail of the person
     */
    private String mail;

    /**
     * The first name of the person
     */
    private String prenom;

    public Personne(){
    }

    public Personne(String nom) {
        this.nom = nom;
    }

    public Personne(String nom, String prenom, String identifiant, String mail, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.identifiant = identifiant;
        this.mdp = mdp;
    }

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
     * @param id a long id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To get the last name of the person
     * @return a string
     */
    public String getNom() {
        return nom;
    }

    /**
     * To set the last name of the person
     * @param nom a string of the last name
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * To get the first name of the person
     * @return a string
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * To set the first name  of the person
     * @param prenom a string of the first name
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * To get the login of the person
     * @return a string
     */
    @Column(unique = true)
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * To set the login of the person
     * @param identifiant a string of a login
     */
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * To get the password of the person
     * @return a string
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * To set the password of the person
     * @param mdp a string of a password
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * To get the E-mail of the person
     * @return a string
     */
    @Column(unique = true)
    public String getMail() {
        return mail;
    }

    /**
     * To set the E-mail of the person
     * @param mail a string of an E-mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
}
