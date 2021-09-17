package sample.data.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.data.jpa.domain.Professionnel;

import java.util.Date;
import java.util.List;

public class RdvsParProfessionnelEtDate {

    private Professionnel professionnel;
    private Date date;

    @JsonProperty("Professionnel")
    public Professionnel getProfessionnel() { return professionnel; }
    @JsonProperty("Professionnel")
    public void setProfessionnel(Professionnel value) { this.professionnel = value; }

    @JsonProperty("Date")
    public Date getDate() { return date; }
    @JsonProperty("Date")
    public void setDate(Date value) { this.date = value; }

}
