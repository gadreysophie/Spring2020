package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import sample.data.jpa.dao.ProfessionnelDao;
import sample.data.jpa.dao.RdvDao;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.dao.UtilisateurDao;
import sample.data.jpa.domain.Professionnel;
import sample.data.jpa.domain.Rdv;
import sample.data.jpa.domain.TypeRdv;
import sample.data.jpa.domain.Utilisateur;
import sample.data.jpa.dto.RdvsParProfessionnelEtDate;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RdvService {

    @Autowired
    RdvDao rdvDao;

    @Autowired
    TypeRdvDao typeRdvDao;

    @Autowired
    ProfessionnelDao professionnelDao;

    @Autowired
    UtilisateurDao utilisateurDao;

    /**
     * To get the list of availabilities of a professional in function of his saved availabilities
     * @param prof Professional
     * @param date Date
     * @param typeRdv Type of Rdv
     * @return the list of availabilities by professional for a date and type of rdv
     */
    public HashMap<Integer, List<Time>> listCreneauxDispoTest(Professionnel prof, Date date, TypeRdv typeRdv){
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        // Listes des temps libres dispos initialisées à vide
        List<Time> tabDebutCreneauxDispoMatin = new ArrayList<>();
        List<Time> tabFinCreneauxDispoMatin = new ArrayList<>();
        List<Time> tabDebutCreneauxDispoAprem = new ArrayList<>();
        List<Time> tabFinCreneauxDispoAprem = new ArrayList<>();
        List<Time> tabDebutCreneauxDispo = new ArrayList<>();
        List<Time> tabFinCreneauxDispo = new ArrayList<>();

        String [] joursPresenceTab = prof.getJoursDePresence().split("(?!^)");
        if(joursPresenceTab[c.get(Calendar.DAY_OF_WEEK)-1].equals("1")){

            RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate = new RdvsParProfessionnelEtDate(prof, date);

            List<Rdv> resultCreneauxRes = rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate.getProfessionnel().getId(), rdvsParProfessionnelEtDate.getDate(), rdvsParProfessionnelEtDate.getDate2());
            Integer dureeTypeRdv = typeRdv.getDuree();
            Integer minDuree = typeRdvDao.minDureeTypeRdvByProf(prof.getId());

            // Generate liste de creneaux dispo
            tabDebutCreneauxDispoMatin.add(prof.getHeureDebut());
            tabFinCreneauxDispoMatin.add(prof.getHeureDebutPause());
            tabDebutCreneauxDispoAprem.add(prof.getHeureFinPause());
            tabFinCreneauxDispoAprem.add(prof.getHeureFin());

            for (Rdv next : resultCreneauxRes) {
                Calendar calendar1 = GregorianCalendar.getInstance();
                calendar1.setTime(next.getDateDebut());
                Time debutRdv = Time.valueOf(calendar1.get(Calendar.HOUR_OF_DAY)+":"+calendar1.get(Calendar.MINUTE)+":00");

                Calendar calendar2 = GregorianCalendar.getInstance();
                calendar2.setTime(next.getDateFin());
                Time finRdv = Time.valueOf(calendar2.get(Calendar.HOUR_OF_DAY)+":"+calendar2.get(Calendar.MINUTE)+":00");

                if(finRdv.before(prof.getHeureFinPause()) || finRdv.equals(prof.getHeureFinPause())) {
                    constructTabOfTempsLibre(debutRdv, finRdv, tabDebutCreneauxDispoMatin, tabFinCreneauxDispoMatin);
                }else{
                    constructTabOfTempsLibre(debutRdv, finRdv, tabDebutCreneauxDispoAprem, tabFinCreneauxDispoAprem);
                }
            }
            constructTabOfCreneaux(tabDebutCreneauxDispoMatin, tabFinCreneauxDispoMatin, dureeTypeRdv, minDuree, tabDebutCreneauxDispo, tabFinCreneauxDispo);
            constructTabOfCreneaux(tabDebutCreneauxDispoAprem, tabFinCreneauxDispoAprem, dureeTypeRdv, minDuree, tabDebutCreneauxDispo, tabFinCreneauxDispo);
        }

        HashMap<Integer, List<Time>> creneauxDispo = new HashMap<>();
        for (int i = 0; i < tabDebutCreneauxDispo.size(); i++) {
            List<Time> listTime = new ArrayList<>();
            listTime.add(tabDebutCreneauxDispo.get(i));
            listTime.add(tabFinCreneauxDispo.get(i));
            creneauxDispo.put(i,listTime);
        }
        return creneauxDispo;
    }

    /**
     * Build the table of availabilities of the professional
     * @param debutRdv begin hour of the rdv
     * @param finRdv end hour of the rdv
     * @param tabDebutCreneau table of the begin hours
     * @param tabFinCreneau table of the end hours
     */
    private void constructTabOfTempsLibre(Time debutRdv, Time finRdv, List<Time> tabDebutCreneau, List<Time> tabFinCreneau){
        Time time1;
        Time time2;
        Time tmp;
        for (int i = 0; i < tabDebutCreneau.size(); i++) {
            time1 = tabDebutCreneau.get(i);
            time2 = tabFinCreneau.get(i);

            if (debutRdv.equals(time1)) {
                tabDebutCreneau.set(tabDebutCreneau.indexOf(time1), finRdv);
                if (tabFinCreneau.contains(finRdv)) {
                    tabDebutCreneau.remove(finRdv);
                    tabFinCreneau.remove(finRdv);
                }
                break;
            } else {
                if (debutRdv.before(time2)) {
                    tmp = time2;
                    tabFinCreneau.set(i, debutRdv);
                    if (!finRdv.equals(tmp)) {
                        tabFinCreneau.add(tabFinCreneau.size(), tmp);
                        Collections.sort(tabFinCreneau);
                        tabDebutCreneau.add(tabDebutCreneau.size(), finRdv);
                        Collections.sort(tabDebutCreneau);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Build the table of availabilities for a professional in function of the length of a rdv
     * @param tabDebutTempsLibre table of the begin date of availability
     * @param tabFinTempsLibre table of the end date of the availability
     * @param dureeRdv length of the rdv
     * @param minduree minimum of the length of the rdv by professional
     * @param tabDebutCreneau table of the begin dates
     * @param tabFinCreneau table of the end dates
     */
    private void constructTabOfCreneaux(List<Time> tabDebutTempsLibre, List<Time> tabFinTempsLibre, Integer dureeRdv, Integer minduree, List<Time> tabDebutCreneau, List<Time> tabFinCreneau){
        for (int i = 0; i < tabDebutTempsLibre.size(); i++) {
            Time heureDebutTempsLibre = tabDebutTempsLibre.get(i);
            Time heureFinTempsLibre = tabFinTempsLibre.get(i);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tabDebutTempsLibre.get(i));
            calendar.add(Calendar.MINUTE, dureeRdv);
            Time heureEstimeeFinTempsLibre = Time.valueOf(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":00");

            while(heureEstimeeFinTempsLibre.before(heureFinTempsLibre) || heureEstimeeFinTempsLibre.equals(heureFinTempsLibre)){
                tabDebutCreneau.add(heureDebutTempsLibre);
                tabFinCreneau.add(heureEstimeeFinTempsLibre);

                calendar.setTime(heureDebutTempsLibre);
                calendar.add(Calendar.MINUTE, minduree);
                heureDebutTempsLibre = Time.valueOf(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":00");

                calendar.add(Calendar.MINUTE, dureeRdv);
                heureEstimeeFinTempsLibre = Time.valueOf(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":00");
            }
        }
    }

    /**
     * To create a rdv on the database for example
     * @throws ParseException exception
     */
    public void createFalseRdvs() throws ParseException {
        int numOfRdvs = rdvDao.listRdvs().size();
        if (numOfRdvs == 0) {

            Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
            Utilisateur utilisateur = utilisateurDao.searchUserById(4L);
            Utilisateur utilisateur2 = utilisateurDao.searchUserById(3L);
            TypeRdv typeRdv = typeRdvDao.searchTypeRdvById(1L);
            TypeRdv typeRdv2 = typeRdvDao.searchTypeRdvById(2L);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


            rdvDao.save(new Rdv(typeRdv, professionnel, utilisateur, dateFormat.parse("2021-10-29 11:30")));
            rdvDao.save(new Rdv(typeRdv2, professionnel, utilisateur, dateFormat.parse("2021-10-29 14:30")));
            rdvDao.save(new Rdv(typeRdv2, professionnel, utilisateur2, dateFormat.parse("2021-10-29 10:30")));
            rdvDao.save(new Rdv(typeRdv, professionnel, utilisateur2, dateFormat.parse("2021-10-29 16:00")));
            rdvDao.save(new Rdv(typeRdv, professionnel, utilisateur2, dateFormat.parse("2021-10-29 09:00")));
            rdvDao.save(new Rdv(typeRdv, professionnel, utilisateur2, dateFormat.parse("2021-10-29 12:15")));
            rdvDao.save(new Rdv(typeRdv, professionnel, utilisateur2, dateFormat.parse("2021-10-29 14:00")));
            rdvDao.save(new Rdv(typeRdv2, professionnel, utilisateur2, dateFormat.parse("2021-10-29 16:30")));
        }
    }

    /**
     * to get the list of rdv for example
     * @throws ParseException exception
     */
    public void listRdvTest() throws ParseException {
        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateDuJour = "2021-10-29";

        RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate = new RdvsParProfessionnelEtDate(professionnel, dateFormat.parse(dateDuJour + " 00:00"));
        List<Rdv> resultList = rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate.getProfessionnel().getId(), rdvsParProfessionnelEtDate.getDate(), rdvsParProfessionnelEtDate.getDate2());
        System.out.println("\nNombre de rdvs au " + dateDuJour + " pour " + professionnel.getNom() + " " + professionnel.getPrenom() + ": " + resultList.size());
        for (Rdv next : resultList) {
            System.out.println("Rdv suivant : " + next);
        }
        System.out.println();
    }

    /**
     * the list of availabilities
     * @throws ParseException exception
     */
    public void listCreneauxDispoTest() throws ParseException {
        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        TypeRdv typeRdv = typeRdvDao.searchTypeRdvById(1L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateDuJour = "2021-10-29";
        HashMap<Integer, List<Time>> creneauxDispo = listCreneauxDispoTest(professionnel, dateFormat.parse(dateDuJour + " 00:00"), typeRdv);
        System.out.println("\nListe de créneaux disponibles pour le " + dateDuJour +" avec " + professionnel.getPrenom() + " " + professionnel.getNom() + " :");
        System.out.println(creneauxDispo);
    }

}
