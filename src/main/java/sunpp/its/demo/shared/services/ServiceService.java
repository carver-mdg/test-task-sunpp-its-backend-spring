package sunpp.its.demo.shared.services;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunpp.its.demo.controllers.service.dto.CreateServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.ServiceResponseDTO;
import sunpp.its.demo.controllers.service.dto.UpdateServiceRequestDTO;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;
import sunpp.its.demo.shared.repositories.ServiceRepository;
import sunpp.its.demo.shared.repositories.ServiceUserRepository;
import sunpp.its.demo.shared.repositories.UserRepository;
import sunpp.its.demo.shared.repositories.UserRoleInServiceRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ServiceService {
  @Autowired
  private ServiceRepository serviceRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserRoleInServiceRepository userRoleInServiceRepository;
  @Autowired
  private ServiceUserRepository serviceUserRepository;


  /**
   * Get list of all services of system
   *
   * @return list of DTOs
   */
  public List<ServiceResponseDTO> getServicesList() {
    List<ServiceResponseDTO> response = new LinkedList<>();
    for (ServiceEntity service : this.serviceRepository.findAll())
      response.add(ServiceResponseDTO.convertEntityToDTO(service));
    return response;
  }

  /**
   * Create new service from createDTO
   *
   * @param reqDTO DTO for create entity
   * @return DTO
   */
  public ServiceResponseDTO createService(CreateServiceRequestDTO reqDTO) {
    ServiceEntity serviceEntity = new ServiceEntity();
    serviceEntity.setServiceName(reqDTO.getServiceName());
    serviceEntity.setServiceDesc(reqDTO.getServiceDesc());
    serviceEntity.setUsers(new ArrayList<>());
    this.serviceRepository.save(serviceEntity);

    // add users to service with role as `user`
    for (var userId : reqDTO.getUsersIdsAsRoleUser()) {
      ServiceUserEntity userInService = new ServiceUserEntity();
      userInService.setUser(this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
      userInService.setUserRole(this.userRoleInServiceRepository.findByRoleName("user"));
      userInService.setService(serviceEntity);
      this.serviceUserRepository.save(userInService);
    }

    // add users to service with role as `owner`
    for (var userId : reqDTO.getUsersIdsAsRoleOwner()) {
      ServiceUserEntity userInService = new ServiceUserEntity();
      userInService.setUser(this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
      userInService.setUserRole(this.userRoleInServiceRepository.findByRoleName("owner"));
      userInService.setService(serviceEntity);
      this.serviceUserRepository.save(userInService);
    }

    // add users to service with role as `admin`
    for (var userId : reqDTO.getUsersIdsAsRoleAdmin()) {
      ServiceUserEntity userInService = new ServiceUserEntity();
      userInService.setUser(this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
      userInService.setUserRole(this.userRoleInServiceRepository.findByRoleName("admin"));
      userInService.setService(serviceEntity);
      this.serviceUserRepository.save(userInService);
    }

    ServiceResponseDTO responseDTO = new ServiceResponseDTO();
    responseDTO.setServiceId(serviceEntity.getServiceId());
    responseDTO.setServiceName(serviceEntity.getServiceName());
    responseDTO.setServiceDesc(serviceEntity.getServiceDesc());

    responseDTO.setUsersIdsAsRoleUser(reqDTO.getUsersIdsAsRoleUser());
    responseDTO.setUsersIdsAsRoleOwner(reqDTO.getUsersIdsAsRoleOwner());
    responseDTO.setUsersIdsAsRoleAdmin(reqDTO.getUsersIdsAsRoleAdmin());

    return responseDTO;
  }

  /**
   * Update service from updateDTO
   *
   * @param reqDTO DTO for update entities
   * @return DTO
   */
  public ServiceResponseDTO updateService(UpdateServiceRequestDTO reqDTO) {
    this.serviceRepository.findById(reqDTO.getServiceId()).orElseThrow(EntityNotFoundException::new);

    ServiceEntity serviceEntity = new ServiceEntity();
//    userEntity.setUserId(reqDTO.getUserID());
//    userEntity.setLogin(reqDTO.getLogin());
//    userEntity.setPassword(reqDTO.getPassword());
//    userEntity.setEmployee(this.employeeRepository.findById(reqDTO.getEmployeeID()).orElseThrow());

    this.serviceRepository.save(serviceEntity);
    return ServiceResponseDTO.convertEntityToDTO(serviceEntity);
  }

  /**
   * Delete service
   *
   * @param id ID of entity
   */
  public void deleteService(Integer id) {
    this.serviceRepository.delete(this.serviceRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }
}
