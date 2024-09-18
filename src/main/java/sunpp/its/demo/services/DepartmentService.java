package sunpp.its.demo.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sunpp.its.demo.entities.DepartmentEntity;
import sunpp.its.demo.repositories.DepartmentRepository;
import sunpp.its.demo.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     *
     * @return
     */
    public DepartmentEntity getDepartmentByID_native(Integer id) {
        return (DepartmentEntity)departmentRepository.getDepartmentByID(id);
    }

    /**
     *
     * @return
     */
    public List<DepartmentEntity> getDepartmentList() {
        return (List<DepartmentEntity>)departmentRepository.findAll();
    }

    /**
     *
     * @param department
     * @return
     */
    public DepartmentEntity addDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    /**
     *
     * @param department
     * @return
     */
    public DepartmentEntity updateDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    /**
     *
     * @param id
     * @return
     */
    public DepartmentEntity deleteDepartment(Integer id) {
        Optional<DepartmentEntity> dep = departmentRepository.findById(id);
        departmentRepository.delete(dep.orElseThrow());
        return dep.get();
    }
}
