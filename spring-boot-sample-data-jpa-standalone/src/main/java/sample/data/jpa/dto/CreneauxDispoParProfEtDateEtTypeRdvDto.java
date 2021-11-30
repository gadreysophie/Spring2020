package sample.data.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.TypeRdv;
import java.util.Date;

public class CreneauxDispoParProfEtDateEtTypeRdvDto {

    /**
     * The professional for the rdv
     */
    private Professionnel professionnel;

    /**
     * Date of day for the rdv
     */
    private Date date;

    /**
     * Type of rdv for the rdv
     */
    private TypeRdv typeRdv;

    public CreneauxDispoParProfEtDateEtTypeRdvDto(){}

    public CreneauxDispoParProfEtDateEtTypeRdvDto(Professionnel professionnel, Date date, TypeRdv typeRdv){
        this.professionnel = professionnel;
        this.date = date;
        this.typeRdv = typeRdv;
    }

    /**
     * To get the professional for the rdv
     * @return a professional
     */
    @JsonProperty("Professionnel")
    public Professionnel getProfessionnel() { return professionnel; }

    /**
     * To set the professional for the rdv
     * @param value a professional
     */
    @JsonProperty("Professionnel")
    public void setProfessionnel(Professionnel value) { this.professionnel = value; }

    /**
     * To get the date of day for the rdv
     * @return a date
     */
    @JsonProperty("Date")
    public Date getDate() { return date; }

    /**
     * To set the date of day for the rdv
     * @param value a date
     */
    @JsonProperty("Date")
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * To get the type of rdv for the rdv
     * @return a type of rdv
     */
    @JsonProperty("TypeRdv")
    public TypeRdv getTypeRdv() { return typeRdv; }

    /**
     * To set the type of rdv for the rdv
     * @param typeRdv a type of rdv
     */
    @JsonProperty("TypeRdv")
    public void setTypeRdv(TypeRdv typeRdv) {
        this.typeRdv = typeRdv;
    }
}
