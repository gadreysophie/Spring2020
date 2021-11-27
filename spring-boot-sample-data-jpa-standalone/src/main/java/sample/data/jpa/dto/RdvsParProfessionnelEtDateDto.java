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

    /**
     * To build rdv by professional and date
     * @param professionnel the professional
     * @param date the date of rdv
     */
    public RdvsParProfessionnelEtDateDto(Professionnel professionnel, Date date){
        this.professionnel = professionnel;
        this.date = date;
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
     * To get the date of rdv
     * @return the date
     */
    @JsonProperty("Date")
    public Date getDate() { return date; }

    /**
     * To set the date of the rdv
     * @param value
     */
    @JsonProperty("Date")
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * To get the date on the calendar
     * @return the date on the calendar
     */
    @JsonProperty("Date2")
    public Date getDate2() { setDate2(); return date2; }

    /**
     * To add the date on the calendar
     */
    @JsonProperty("Date2")
    public void setDate2() {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        this.date2 = c.getTime();
    }
}
