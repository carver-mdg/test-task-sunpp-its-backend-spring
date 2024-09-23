package sunpp.its.demo.shared.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunpp.its.demo.shared.entities.DepartmentEntity;
import sunpp.its.demo.shared.repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     *
     * @return
     */
    public DepartmentEntity getDepartmentByID_native(Integer id) {
        return this.departmentRepository.getDepartmentByID(id);
    }

    /**
     *
     * @return
     */
    public DepartmentEntity getDepartmentByID(Integer id) {
        return this.departmentRepository.findById(id).orElseThrow();
    }

    /**
     *
     * @return
     */
    public List<DepartmentEntity> getDepartmentList() {
        return this.departmentRepository.findAll();
    }

    /**
     *
     * @param department
     * @return
     */
    public DepartmentEntity addDepartment(DepartmentEntity department) {
        return this.departmentRepository.save(department);
    }

    /**
     *
     * @param department
     * @return
     */
    public DepartmentEntity updateDepartment(DepartmentEntity department) {
        return this.departmentRepository.save(department);
    }

    /**
     *
     * @param id
     * @return
     */
    public DepartmentEntity deleteDepartment(Integer id) {
        Optional<DepartmentEntity> dep = this.departmentRepository.findById(id);
        this.departmentRepository.delete(dep.orElseThrow());
        return dep.get();
    }
}
