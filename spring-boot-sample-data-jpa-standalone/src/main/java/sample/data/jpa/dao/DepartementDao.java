package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sample.data.jpa.domain.Departement;
import java.util.List;

public interface DepartementDao extends JpaRepository<Departement, Long> {
    /**
     * To search a department by its id
     * @param id the id of the department
     * @return the department
     */
    @Query("SELECT d FROM Departement d WHERE d.id =:id")
    Departement searchDepartementById(@Param("id") Long id);

    /**
     * To get the list of department
     * @return the list of Department
     */
    @Query("Select d From Departement d")
    List<Departement> listDepartements() ;
}
