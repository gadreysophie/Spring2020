package sample.data.jpa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import sample.data.jpa.domain.Professionnel;
import java.util.Calendar;
import java.util.Date;

public class RdvsParProfessionnelEtDateDto {

    /**
     * The professional associate to rdvs
     */
    private Professionnel professionnel;

    /**
     * Date day
     */
    private Date date;

    /**
     * Date day + 1
     */
    private Date date2;

    public RdvsParProfessionnelEtDateDto(){}

    public RdvsParProfessionnelEtDateDto(Professionnel professionnel, Date date){
        this.professionnel = professionnel;
        this.date = date;
    }

    /**
     * To get the professional
     * @return a professional
     */
    @JsonProperty("Professionnel")
    public Professionnel getProfessionnel() { return professionnel; }

    /**
     * To set the professional
     * @param professionnel a professional
     */
    @JsonProperty("Professionnel")
    public void setProfessionnel(Professionnel professionnel) { this.professionnel = professionnel; }

    /**
     * To get the date day
     * @return a date
     */
    @JsonProperty("Date")
    public Date getDate() { return date; }

    /**
     * To set the date day
     * @param value a date
     */
    @JsonProperty("Date")
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * To get the date + 1
     * @return a date
     */
    @JsonProperty("Date2")
    public Date getDate2() { setDate2(); return date2; }

    /**
     * To set the date + 1
     */
    @JsonProperty("Date2")
    public void setDate2() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        this.date2 = c.getTime();
    }
}
