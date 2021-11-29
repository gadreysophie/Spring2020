package sample.data.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.TypeRdv;
import java.util.Date;

public class CreneauxDispoParProfEtDateEtTypeRdvDto {

    private Professionnel professionnel;
    private Date date;
    private TypeRdv typeRdv;

    public CreneauxDispoParProfEtDateEtTypeRdvDto(){}

    /**
     * Available schedule by professional, date and type of rdv.
     * @param professionnel the professional
     * @param date the date of the rdv
     * @param typeRdv the type of rdv
     */
    public CreneauxDispoParProfEtDateEtTypeRdvDto(Professionnel professionnel, Date date, TypeRdv typeRdv){
        this.professionnel = professionnel;
        this.date = date;
        this.typeRdv = typeRdv;
    }

    /**
     * To get the professional
     * @return the professional
     */
    @JsonProperty("Professionnel")
    public Professionnel getProfessionnel() { return professionnel; }

    /**
     * To set the professional
     * @param value the professional to set
     */
    @JsonProperty("Professionnel")
    public void setProfessionnel(Professionnel value) { this.professionnel = value; }

    /**
     * To get the date of the rdv
     * @return the date to check
     */
    @JsonProperty("Date")
    public Date getDate() { return date; }

    /**
     * To set the date
     * @param value the date
     */
    @JsonProperty("Date")
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * To get the type of rdv of the schedule
     * @return the type of rdv
     */
    @JsonProperty("TypeRdv")
    public TypeRdv getTypeRdv() { return typeRdv; }

    /**
     * To set the type of rdv
     * @param typeRdv
     */
    @JsonProperty("TypeRdv")
    public void setTypeRdv(TypeRdv typeRdv) { this.typeRdv = typeRdv; }
}
