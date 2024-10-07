package sunpp.its.demo.shared.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import sunpp.its.demo.shared.entities.*;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;
import sunpp.its.demo.shared.repositories.*;

import java.util.ArrayList;
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
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceUserRepository serviceUserRepository;
    @Autowired
    private UserRoleInServiceRepository userRoleInServiceRepository;

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
                userEntity.setLogin(String.format("login-%d", employee.getEmployeeId()));
                userEntity.setPassword(String.format("p%d", employee.getEmployeeId()));
                userEntity.setEmployee(employee);
                this.userRepository.save(userEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }

    /**
     * Create services
     */
    public void createServices() {
        List<UserEntity> usersEntities = this.userRepository.findAll();
//        if(usersEntities.size() < 2) throw new Exception("count of users less than 2");

        for (var userIdx = 0; userIdx < usersEntities.size(); userIdx += 2) {
            try {
                ServiceEntity serviceEntity = new ServiceEntity();
                serviceEntity.setServiceName(String.format("service-name-%d", userIdx));
                serviceEntity.setServiceDesc(String.format("service-desc-%d", userIdx));
                serviceEntity.setUsers(new ArrayList<>());
                this.serviceRepository.save(serviceEntity);

                // user with role owner
                ServiceUserEntity userInServiceOwner = new ServiceUserEntity();
                userInServiceOwner.setUser(usersEntities.get(userIdx));
                userInServiceOwner.setUserRole(this.userRoleInServiceRepository.findByRoleName("owner"));
                userInServiceOwner.setService(serviceEntity);
                this.serviceUserRepository.save(userInServiceOwner);

                // user with role admin
                ServiceUserEntity userInServiceAdmin = new ServiceUserEntity();
                userInServiceAdmin.setUser(usersEntities.get(userIdx + 1));
                userInServiceAdmin.setUserRole(this.userRoleInServiceRepository.findByRoleName("admin"));
                userInServiceAdmin.setService(serviceEntity);
                this.serviceUserRepository.save(userInServiceAdmin);
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
                if(this.userRoleInServiceRepository.existsByRoleName(typeUserRoleService))
                    continue;

                TypeUserRoleInServiceEntity userRoleEntity = new TypeUserRoleInServiceEntity();
                userRoleEntity.setRoleName(typeUserRoleService);
                this.userRoleInServiceRepository.save(userRoleEntity);
            } catch (DataAccessException ignored) {
            }
        }
    }
}
