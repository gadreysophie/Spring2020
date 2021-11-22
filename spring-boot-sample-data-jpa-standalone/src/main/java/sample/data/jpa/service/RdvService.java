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
     * Retourne la liste des disponibilités d'un professionnel
     * en fonction de ses créneaux disponibles enregistrés
     * @param prof Professionnel
     * @param date Date
     * @param typeRdv Type de Rdv
     * @return la liste des créneaux disponibles par professionnel pour une date et par type de Rdv
     */
    public HashMap<Integer, List<Time>> listCreneauxDispo(Professionnel prof, Date date, TypeRdv typeRdv){
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

            RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate = new RdvsParProfessionnelEtDate();
            rdvsParProfessionnelEtDate.setProfessionnel(prof);
            rdvsParProfessionnelEtDate.setDate(date);
            rdvsParProfessionnelEtDate.setDate2();

            List<Rdv> resultCreneauxRes = rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate);
            Integer dureeTypeRdv = typeRdv.getDuree();
            Integer minDuree = typeRdvDao.minDureeTypeRdvByProf(prof);

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

//                System.out.println("\nRDV suivant : " + next);
//                System.out.println("Début rdv : "+debutRdv);
//                System.out.println("Fin rdv : "+finRdv);

                if(finRdv.before(prof.getHeureFinPause()) || finRdv.equals(prof.getHeureFinPause())) {
                    constructTabOfTempsLibre(debutRdv, finRdv, tabDebutCreneauxDispoMatin, tabFinCreneauxDispoMatin);
//                    System.out.println(tabDebutCreneauxDispoMatin);
//                    System.out.println(tabFinCreneauxDispoMatin);
                }else{
                    constructTabOfTempsLibre(debutRdv, finRdv, tabDebutCreneauxDispoAprem, tabFinCreneauxDispoAprem);
//                    System.out.println(tabDebutCreneauxDispoAprem);
//                    System.out.println(tabFinCreneauxDispoAprem);
                }
            }
            constructTabOfCreneaux(tabDebutCreneauxDispoMatin, tabFinCreneauxDispoMatin, dureeTypeRdv, minDuree, tabDebutCreneauxDispo, tabFinCreneauxDispo);
            constructTabOfCreneaux(tabDebutCreneauxDispoAprem, tabFinCreneauxDispoAprem, dureeTypeRdv, minDuree, tabDebutCreneauxDispo, tabFinCreneauxDispo);
        }

//        System.out.println("\nTemps libre : ");
//        System.out.println(tabDebutCreneauxDispoMatin);
//        System.out.println(tabFinCreneauxDispoMatin);
//        System.out.println(tabDebutCreneauxDispoAprem);
//        System.out.println(tabFinCreneauxDispoAprem);

//        System.out.println("\nCréneaux disponibles : ");
//        System.out.println(tabDebutCreneauxDispo);
//        System.out.println(tabFinCreneauxDispo);

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
     * Construit le tableau des disponibilités du professionnel
     * @param debutRdv heure de début du rdv
     * @param finRdv heure de fin du rdv
     * @param tabDebutCreneau tableau des heures de début des créneaux
     * @param tabFinCreneau tableau des heures de fin des créneaux
     */
    private void constructTabOfTempsLibre(Time debutRdv, Time finRdv, List<Time> tabDebutCreneau, List<Time> tabFinCreneau){
        Time time1;
        Time time2;
        Time tmp;
        for (int i = 0; i < tabDebutCreneau.size(); i++) {
            time1 = tabDebutCreneau.get(i);
            time2 = tabFinCreneau.get(i);
//            System.out.println("Début dispo : " + time1);
//            System.out.println("Fin dispo : " + time2);

            if (debutRdv.equals(time1)) {
//                System.out.println("    Remplacer : " + time1 + " par "+finRdv);
                tabDebutCreneau.set(tabDebutCreneau.indexOf(time1), finRdv);
//                System.out.println("\n  Test : ");
//                System.out.println(tabDebutCreneau);
//                System.out.println(tabFinCreneau);
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
     * Construit le tableau des disponibilités d'un professionnel en fonction de la duree d'un rdv
     * @param tabDebutTempsLibre tableau de l'heure de début de la disponibilité
     * @param tabFinTempsLibre tableau de l'heure de fin de la disponibilité
     * @param dureeRdv durée du rdv
     * @param minduree minimum de la duree des rdv d'un professionnel
     * @param tabDebutCreneau tableau des heures de début des créneaux
     * @param tabFinCreneau tableau des heures de fin des créneaux
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

    public void createRdvs() throws ParseException {
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

    public void listRdvTest() throws ParseException {
        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateDuJour = "2021-10-29";

        RdvsParProfessionnelEtDate rdvsParProfessionnelEtDate = new RdvsParProfessionnelEtDate();
        rdvsParProfessionnelEtDate.setProfessionnel(professionnel);
        rdvsParProfessionnelEtDate.setDate(dateFormat.parse(dateDuJour + " 00:00"));
        rdvsParProfessionnelEtDate.setDate2();
        List<Rdv> resultList = rdvDao.rdvsParProfessionnelEtDate(rdvsParProfessionnelEtDate);
        System.out.println("\nNombre de rdvs au " + dateDuJour + " pour " + professionnel.getNom() + " " + professionnel.getPrenom() + ": " + resultList.size());
        for (Rdv next : resultList) {
            System.out.println("Rdv suivant : " + next);
        }
        System.out.println();
    }

    public void testListCreneauxDispo() throws ParseException {
        Professionnel professionnel = professionnelDao.searchProfessionnelById(2L);
        TypeRdv typeRdv = typeRdvDao.searchTypeRdvById(1L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateDuJour = "2021-10-29";
        HashMap<Integer, List<Time>> creneauxDispo = listCreneauxDispo(professionnel, dateFormat.parse(dateDuJour + " 00:00"), typeRdv);
        System.out.println("\nListe de créneaux disponibles pour le " + dateDuJour +" avec " + professionnel.getPrenom() + " " + professionnel.getNom() + " :");
        System.out.println(creneauxDispo);
    }
//
//    public List<Rdv> rdvsParProfessionnelEtDate(Professionnel prof, Date date){
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.DATE, 1);
//        return manager.createNamedQuery("tousLesRdvParProfEtDate").setParameter("prof", prof).
//                setParameter("date",date).setParameter("date2",c.getTime()).getResultList();
//    }
}
