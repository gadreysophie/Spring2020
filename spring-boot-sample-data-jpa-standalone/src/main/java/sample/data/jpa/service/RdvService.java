package sample.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import sample.data.jpa.dao.ProfessionnelDao;
import sample.data.jpa.dao.RdvDao;
import sample.data.jpa.dao.TypeRdvDao;
import sample.data.jpa.dao.UtilisateurDao;
import sample.data.jpa.domain.*;
import sample.data.jpa.dto.CreneauxDispoParProfEtDateEtTypeRdvDto;
import sample.data.jpa.dto.RdvsParProfessionnelEtDateDto;
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
     * To get the list of available rdvs of a professional in function of a date and a type of rdv
     * @param creneauxDispoParProfEtDateEtTypeRdv DTO CreneauxDispoParProfEtDateEtTypeRdv
     * @param rdvDao rdvDao
     * @param typeRdvDao typeRdvDao
     * @return a list of rdvs
     */
    public static List<Rdv> listCreneauxDispo(CreneauxDispoParProfEtDateEtTypeRdvDto creneauxDispoParProfEtDateEtTypeRdv, RdvDao rdvDao, TypeRdvDao typeRdvDao){
        Professionnel prof = creneauxDispoParProfEtDateEtTypeRdv.getProfessionnel();
        Date date = creneauxDispoParProfEtDateEtTypeRdv.getDate();
        TypeRdv typeRdv = creneauxDispoParProfEtDateEtTypeRdv.getTypeRdv();

        Calendar c = GregorianCalendar.getInstance();
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
            RdvsParProfessionnelEtDateDto rdvsParProfessionnelEtDate = new RdvsParProfessionnelEtDateDto(prof, date);

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

        List<Rdv> creneauxDispo = new ArrayList<>();
        Utilisateur user = new Utilisateur("Nom0", "Prenom0", "Prenom0","Prenom0@hotmail.fr", "prenom0");
        user.setId(0L);
        typeRdv = typeRdvDao.searchTypeRdvById(typeRdv.getId());
        prof = typeRdv.getProfessionnel();
        for (Time dateDebut : tabDebutCreneauxDispo) {
            Calendar calendar1 = Calendar.getInstance();
            List<String> date1 = Arrays.asList(String.valueOf(dateDebut).split(":"));
            calendar1.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                    Integer.parseInt(date1.get(0)), Integer.parseInt(date1.get(1)), Integer.parseInt(date1.get(2))
            );
            Calendar calendar2 = GregorianCalendar.getInstance();
            calendar2.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                    calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), calendar1.get(Calendar.SECOND));
            Rdv rdv = new Rdv(typeRdv, prof, user, calendar2.getTime());
            rdv.setId(0L);
            creneauxDispo.add(rdv);
        }
        return creneauxDispo;
    }

    /**
     * Build a table of available time slots
     * @param debutRdv start hour of the rdv
     * @param finRdv end hour of the rdv
     * @param tabDebutCreneau table of the start hours of rdvs
     * @param tabFinCreneau table of the end hours of rdvs
     */
    private static void constructTabOfTempsLibre(Time debutRdv, Time finRdv, List<Time> tabDebutCreneau, List<Time> tabFinCreneau){
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
     * Build lists of available time slots
     * @param tabDebutTempsLibre list of start time available slots
     * @param tabFinTempsLibre list of end time available slots
     * @param dureeRdv duration of the rdv
     * @param minduree minimum duration of a rdv for the professional
     * @param tabDebutCreneau list of start time of rdv
     * @param tabFinCreneau list of end time of rdv
     */
    private static void constructTabOfCreneaux(List<Time> tabDebutTempsLibre, List<Time> tabFinTempsLibre, Integer dureeRdv, Integer minduree, List<Time> tabDebutCreneau, List<Time> tabFinCreneau){
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
     * To create false data of rdvs in the database
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
     * To print the list of rdvs
     * @throws ParseException exception
     */
    public void listRdvTest() throws ParseException {
        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateDuJour = "2021-10-29";

        RdvsParProfessionnelEtDateDto rdvsParProfessionnelEtDate = new RdvsParProfessionnelEtDateDto(professionnel, dateFormat.parse(dateDuJour + " 00:00"));
        List<Rdv> resultList = rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate.getProfessionnel().getId(), rdvsParProfessionnelEtDate.getDate(), rdvsParProfessionnelEtDate.getDate2());
        System.out.println("\nNombre de rdvs au " + dateDuJour + " pour " + professionnel.getNom() + " " + professionnel.getPrenom() + ": " + resultList.size());
        for (Rdv next : resultList) {
            System.out.println("Rdv suivant : " + next);
        }
        System.out.println();
    }

    /**
     * To print the list of available time slots for a date, a professional and a date
     * @throws ParseException exception
     */
    public void listCreneauxDispoTest() throws ParseException {
        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        TypeRdv typeRdv = typeRdvDao.searchTypeRdvById(1L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateDuJour = "2021-10-29";
        List<Rdv> creneauxDispo = listCreneauxDispo(new CreneauxDispoParProfEtDateEtTypeRdvDto(professionnel, dateFormat.parse(dateDuJour + " 00:00"), typeRdv), rdvDao, typeRdvDao);
        System.out.println("\nListe de créneaux disponibles pour le " + dateDuJour +" avec " + professionnel.getPrenom() + " " + professionnel.getNom() + " :");
        System.out.println(creneauxDispo);
    }
}
