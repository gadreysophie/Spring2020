package sample.data.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import sample.data.jpa.domain.Departement;

public interface DepartementDao extends JpaRepository<Departement, Long> {
    @Query("SELECT d FROM Departement d WHERE d.id =:id")
    Departement searchDepartementById(@PathVariable("id") Long id);
}
