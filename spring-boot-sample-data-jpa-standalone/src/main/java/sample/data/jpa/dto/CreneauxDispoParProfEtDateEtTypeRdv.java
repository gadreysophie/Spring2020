package sample.data.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.TypeRdv;
import java.util.Date;

public class CreneauxDispoParProfEtDateEtTypeRdv {

    private Professionnel professionnel;
    private Date date;
    private TypeRdv typeRdv;

    public CreneauxDispoParProfEtDateEtTypeRdv(){}

    public CreneauxDispoParProfEtDateEtTypeRdv(Professionnel professionnel, Date date, TypeRdv typeRdv){
        this.professionnel = professionnel;
        this.date = date;
        this.typeRdv = typeRdv;
    }

    @JsonProperty("Professionnel")
    public Professionnel getProfessionnel() { return professionnel; }

    @JsonProperty("Professionnel")
    public void setProfessionnel(Professionnel value) { this.professionnel = value; }

    @JsonProperty("Date")
    public Date getDate() { return date; }

    @JsonProperty("Date")
    public void setDate(Date value) {
        this.date = value;
    }

    @JsonProperty("TypeRdv")
    public TypeRdv getTypeRdv() { return typeRdv; }

    @JsonProperty("TypeRdv")
    public void setTypeRdv(TypeRdv typeRdv) { this.typeRdv = typeRdv; }
}
