package sunpp.its.demo.shared.services;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import sunpp.its.demo.shared.entities.DepartmentEntity;
import sunpp.its.demo.shared.entities.EmployeeEntity;
import sunpp.its.demo.shared.entities.StaffUnitEntity;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;
import sunpp.its.demo.shared.entities.service.request.TypeResponseRequestRoleInServiceEntity;
import sunpp.its.demo.shared.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirstInitDbService {
  private final DepartmentRepository departmentRepository;
  private final StaffUnitRepository staffUnitRepository;
  private final EmployeeRepository employeeRepository;
  private final UserRepository userRepository;
  private final ServiceRepository serviceRepository;
  private final ServiceUserRepository serviceUserRepository;
  private final UserRoleInServiceRepository userRoleInServiceRepository;
  private final TypeResponseRequestRoleInServiceRepository typeResponseRequestRoleInServiceRepository;


  /**
   * @param departmentRepository
   * @param staffUnitRepository
   * @param employeeRepository
   * @param userRepository
   * @param serviceRepository
   * @param serviceUserRepository
   * @param userRoleInServiceRepository
   * @param typeResponseRequestRoleInServiceRepository
   */
  public FirstInitDbService(
      DepartmentRepository departmentRepository,
      StaffUnitRepository staffUnitRepository,
      EmployeeRepository employeeRepository,
      UserRepository userRepository,
      ServiceRepository serviceRepository,
      ServiceUserRepository serviceUserRepository,
      UserRoleInServiceRepository userRoleInServiceRepository,
      TypeResponseRequestRoleInServiceRepository typeResponseRequestRoleInServiceRepository
  ) {
    this.departmentRepository = departmentRepository;
    this.staffUnitRepository = staffUnitRepository;
    this.employeeRepository = employeeRepository;
    this.userRepository = userRepository;
    this.serviceRepository = serviceRepository;
    this.serviceUserRepository = serviceUserRepository;
    this.userRoleInServiceRepository = userRoleInServiceRepository;
    this.typeResponseRequestRoleInServiceRepository = typeResponseRequestRoleInServiceRepository;
  }


  /**
   *
   */
  public void create() {
    this.createTypeUserRoleInService();
    this.createTypeResponseRequestRoleInService();
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
        staffUnitEntity.setDepartment(departmentEntities.get((int) (Math.random() * departmentEntities.size())));
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
        employeeEntity.setStaffUnit(staffUnitEntities.get((int) (Math.random() * staffUnitEntities.size())));
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
        userEntity.setUserName(String.format("u%d", employee.getEmployeeId()));
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
    this.serviceRepository.deleteAll();
  }


  /**
   * Create types of user role in service
   */
  private void createTypeUserRoleInService() {
    for (var typeUserRoleService : new String[]{"user", "owner", "admin"}) {
      try {
        if (this.userRoleInServiceRepository.existsByRoleName(typeUserRoleService))
          continue;

        TypeUserRoleInServiceEntity userRoleEntity = new TypeUserRoleInServiceEntity();
        userRoleEntity.setRoleName(typeUserRoleService);
        this.userRoleInServiceRepository.save(userRoleEntity);
      } catch (DataAccessException ignored) {
      }
    }
  }


  /**
   * Create types of response for requested role in service
   */
  private void createTypeResponseRequestRoleInService() {
    for (var typeResponseRequestRoleInService : new String[]{"approved", "rejected"}) {
      try {
        if (this.typeResponseRequestRoleInServiceRepository.existsByTypeResponseName(typeResponseRequestRoleInService))
          continue;

        TypeResponseRequestRoleInServiceEntity responseRequestRoleInServiceEntity = new TypeResponseRequestRoleInServiceEntity();
        responseRequestRoleInServiceEntity.setTypeResponseName(typeResponseRequestRoleInService);
        this.typeResponseRequestRoleInServiceRepository.save(responseRequestRoleInServiceEntity);
      } catch (DataAccessException ignored) {
      }
    }
  }
}
