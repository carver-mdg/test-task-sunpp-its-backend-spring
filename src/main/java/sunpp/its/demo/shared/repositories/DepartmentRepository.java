package sunpp.its.demo.shared.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sunpp.its.demo.shared.entities.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    @Query(value = "SELECT * FROM Department WHERE DepartmentID = :departmentID", nativeQuery = true)
    DepartmentEntity getDepartmentByID(@Param("departmentID") Integer departmentID);

//    @Query(value = "SELECT d FROM DepartmentEntity d")
//    List<DepartmentEntity> getDepartments();
}
