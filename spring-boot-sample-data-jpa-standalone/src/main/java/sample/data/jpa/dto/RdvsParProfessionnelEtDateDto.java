package sample.data.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.data.jpa.domain.Professionnel;
import java.util.Calendar;
import java.util.Date;

public class RdvsParProfessionnelEtDateDto {

    private Professionnel professionnel;
    private Date date;
    private Date date2;

    public RdvsParProfessionnelEtDateDto(){}

    public RdvsParProfessionnelEtDateDto(Professionnel professionnel, Date date){
        this.professionnel = professionnel;
        this.date = date;
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

    @JsonProperty("Date2")
    public Date getDate2() { setDate2(); return date2; }

    @JsonProperty("Date2")
    public void setDate2() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        this.date2 = c.getTime();
    }

}
