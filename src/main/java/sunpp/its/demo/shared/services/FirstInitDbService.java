package sunpp.its.demo.shared.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import sunpp.its.demo.shared.entities.*;
import sunpp.its.demo.shared.repositories.*;

import java.util.List;

@Service
public class FirstInitDbService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private StaffUnitRepository staffUnitRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    /**
     *
     */
    public void create() {
        this.createTypeUserRoleInService();
    }

    /**
     * Create departments
     */
    public void createDepartments() {
        for (var departmentName : new String[]{"ЦТАИ", "ТЦ-1", "ТЦ-2", "ЭЦ", "ЭРП"}) {
            try {
                DepartmentEntity departmentEntity = new DepartmentEntity();
                departmentEntity.setDepartmentName(departmentName);
                this.departmentRepository.save(departmentEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }

    /**
     * Create staff units
     */
    public void createStaffUnits() {
        List<DepartmentEntity> departmentEntities = this.departmentRepository.findAll();

        for (var staffUnitName : new String[]{"Мастер", "Инженер 1к", "Инженер 2к", "Инженер 3к", "Слесарь 3р"}) {
            try {
                StaffUnitEntity staffUnitEntity = new StaffUnitEntity();
                staffUnitEntity.setStaffUnitName(staffUnitName);
                staffUnitEntity.setDepartment(departmentEntities.get( (int)(Math.random() * departmentEntities.size())) );
                this.staffUnitRepository.save(staffUnitEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }

    /**
     * Create employees
     */
    public void createEmployees() {
        List<StaffUnitEntity> staffUnitEntities = this.staffUnitRepository.findAll();

        for (var employeeFullName : new String[]{"Штайнер О.Р", "Кравченко Д.Н", "Драгович Ш.В", "Alex Mason"}) {
            try {
                EmployeeEntity employeeEntity = new EmployeeEntity();
                employeeEntity.setFullName(employeeFullName);
                employeeEntity.setStaffUnit(staffUnitEntities.get( (int)(Math.random() * staffUnitEntities.size())) );
                this.employeeRepository.save(employeeEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }

    /**
     * Create users
     */
    public void createUsers() {
        List<EmployeeEntity> employeeEntities = this.employeeRepository.findAll();

        for (var employee : employeeEntities) {
            try {
                UserEntity userEntity = new UserEntity();
                userEntity.setLogin(String.format("l%d", employee.getEmployeeID()));
                userEntity.setPassword(String.format("p%d", employee.getEmployeeID()));
                userEntity.setEmployee(employee);
                this.userRepository.save(userEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }

    /**
     * Truncate tables
     */
    public void truncateDB() {
        this.departmentRepository.deleteAll();
        this.staffUnitRepository.deleteAll();
        this.employeeRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    /**
     * Create types of user role in service
     */
    private void createTypeUserRoleInService() {
        for (var typeUserRoleService : new String[]{"user", "owner", "admin"}) {
            try {
                if(this.userRoleRepository.existsByRoleName(typeUserRoleService))
                    continue;

                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setRoleName(typeUserRoleService);
                this.userRoleRepository.save(userRoleEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }
}
