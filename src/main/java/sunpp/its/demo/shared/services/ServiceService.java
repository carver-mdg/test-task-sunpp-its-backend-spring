package sunpp.its.demo.shared.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunpp.its.demo.controllers.service.dto.HistoryRequestAccessResponseDTO;
import sunpp.its.demo.controllers.service.dto.UserRoleInServiceResponseDTO;
import sunpp.its.demo.controllers.service.dto.adminsys.CreateServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.adminsys.ServiceResponseDTO;
import sunpp.its.demo.controllers.service.dto.adminsys.UpdateServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.user.ResponseRequestObtainUserRoleInServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.user.ServiceWaitingAccessResponseDTO;
import sunpp.its.demo.shared.entities.service.ServiceEntity;
import sunpp.its.demo.shared.entities.service.ServiceUserEntity;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;
import sunpp.its.demo.shared.entities.service.request.RequestObtainUserRoleInServiceEntity;
import sunpp.its.demo.shared.entities.service.request.ResponseRequestRoleInServiceEntity;
import sunpp.its.demo.shared.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
  @Autowired
  private RequestObtainUserRoleInServiceRepository requestObtainUserRoleInServiceRepository;
  @Autowired
  private ResponseRequestRoleInServiceRepository responseRequestRoleInServiceRepository;
  @Autowired
  TypeResponseRequestRoleInServiceRepository typeResponseRequestRoleInServiceRepository;


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
   * Get list of all services of system
   *
   * @return list of DTOs
   */
  public List<sunpp.its.demo.controllers.service.dto.user.ServiceResponseDTO> getServicesViewerList(Integer currentUserId) {
    List<sunpp.its.demo.controllers.service.dto.user.ServiceResponseDTO> response = new LinkedList<>();
    for (ServiceEntity service : this.serviceRepository.findAll()) {
      var user = this.userRepository.findById(currentUserId).orElseThrow(EntityNotFoundException::new);
      var userInService = this.serviceUserRepository.findByUserAndService(user, service);
      boolean isHasAccess;
      String userRoleName;

      if(userInService == null) {
        isHasAccess = false;
        userRoleName = "";
      }
      else {
        isHasAccess = true;
        userRoleName = userInService.getUserRole().getRoleName();
      }

      var requests = this.requestObtainUserRoleInServiceRepository.findByServiceAndUserCustomerAndIsActive(service, user, true);
      if(requests.size() > 1) throw new RuntimeException("requestObtainUserRoleInServiceRepository with active status > 1");
      String userRoleNameRequested = requests.size() == 1 ? requests.get(0).getRequestedRole().getRoleName() : "";

      String userRoleNameRequestedStatus = "";

      response.add(sunpp.its.demo.controllers.service.dto.user.ServiceResponseDTO.convertEntityToDTO(
          service, isHasAccess, userRoleName, userRoleNameRequested, userRoleNameRequestedStatus)
      );
    }

    return response;
  }


  /**
   * Check if the user has access to the service
   *
   * @param serviceId
   * @param userId
   * @return
   */
  public Boolean isHasAccessUserToService(Integer serviceId, Integer userId) {
    var service = this.serviceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);
    var userInService = this.serviceUserRepository.findByUserAndService(this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new), service);
    return userInService != null;
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
  @Transactional
  public ServiceResponseDTO updateService(UpdateServiceRequestDTO reqDTO) {
    this.serviceRepository.findById(reqDTO.getServiceId()).orElseThrow(EntityNotFoundException::new);

    ServiceEntity serviceEntity = new ServiceEntity();
    serviceEntity.setServiceId(reqDTO.getServiceId());
    serviceEntity.setServiceName(reqDTO.getServiceName());
    serviceEntity.setServiceDesc(reqDTO.getServiceDesc());
    serviceEntity.setUsers(new ArrayList<>());
    this.serviceRepository.save(serviceEntity);

    // delete all users from service
    this.serviceUserRepository.deleteByService(serviceEntity);

    // @NOTE why data not be deleted until data is not read ?
    this.serviceUserRepository.count();

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
   * Delete service
   *
   * @param id ID of entity
   */
  public void deleteService(Integer id) {
    this.serviceRepository.delete(this.serviceRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }


  /**
   * Get user role types in services
   *
   * @return
   */
  public List<UserRoleInServiceResponseDTO> getUserRoleTypes (){
    List<UserRoleInServiceResponseDTO> response = new LinkedList<>();

    for(TypeUserRoleInServiceEntity typeUserRoleInServiceEntity : this.userRoleInServiceRepository.findAll())
      response.add(UserRoleInServiceResponseDTO.convertEntityToDTO(typeUserRoleInServiceEntity));

    return response;
  }


  /**
   * Get services where user (user_id) has role (role_id)
   *
   * @param user_id ID of user
   * @param role_id id role of user
   * @return
   */
  public List<ServiceResponseDTO> getServicesListByUserAndUserRole(Integer user_id, Integer role_id) {
    List<ServiceResponseDTO> response = new LinkedList<>();
//    UserEntity user = this.userRepository.findById(user_id).orElseThrow(EntityNotFoundException::new);
//    TypeUserRoleInServiceEntity userRole = this.userRoleInServiceRepository.findById(role_id).orElseThrow(EntityNotFoundException::new);

    for (ServiceEntity service : this.serviceRepository.findByUsersAndUserRole(user_id, role_id))
      response.add(ServiceResponseDTO.convertEntityToDTO(service));
    return response;
  }


  /**
   * Create a request to obtain the user role in the service
   *
   * @param serviceId
   * @param userId
   * @param userRoleId
   */
  public void createRequestObtainUserRoleInService(Integer serviceId, Integer userId, Integer userRoleId) {
    var service = this.serviceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);
    var userCustomer = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    var requestedUserRole = this.userRoleInServiceRepository.findById(userRoleId).orElseThrow(EntityNotFoundException::new);

    // if the user makes a request in a service
    // where there is already an active request for this user,
    // then we prohibit the creation of a new request
    var oldRequests = this.requestObtainUserRoleInServiceRepository.findByServiceAndUserCustomer(service, userCustomer);
    for(var oldRequest : oldRequests)
      if(oldRequest.getIsActive())
        return;

    RequestObtainUserRoleInServiceEntity requestObtainUserRoleInServiceEntity = new RequestObtainUserRoleInServiceEntity();
    requestObtainUserRoleInServiceEntity.setService(service);
    requestObtainUserRoleInServiceEntity.setUserCustomer(userCustomer);
    requestObtainUserRoleInServiceEntity.setRequestedRole(requestedUserRole);
    requestObtainUserRoleInServiceEntity.setIsActive(true);

    this.requestObtainUserRoleInServiceRepository.save(requestObtainUserRoleInServiceEntity);
  }


  /**
   * Loading services which waiting approved to access.
   * First, the owner provides access and if he responds positively,
   * the further decision is up to the administrator.
   * (some user -> |send request role| -> owner -> |yes or no| -> admin -> |yse or no| -> access result)
   *
   * @param curUserId logged user which gives access to service (e.g. it has user role is 'owner', 'admin')
   * @return
   */
  public List<ServiceWaitingAccessResponseDTO> getServicesWaitingAccess(Integer curUserId) {
    List<ServiceWaitingAccessResponseDTO> responseDTO = new LinkedList<>();

    // current logged user
    var curUser = this.userRepository.findById(curUserId).orElseThrow(EntityNotFoundException::new);

    for (var request : this.requestObtainUserRoleInServiceRepository.findByIsActive(true)) {
//      if (!request.getIsActive()) continue;


      // find current logged user in service
      var serviceUsers = request.getService().getUsers().stream().filter(serviceUserEntity ->
          serviceUserEntity.getUser().equals(curUser)
      ).toList();

      // checked that the logged in user is not duplicated in the service
      if(serviceUsers.size() > 1) throw new RuntimeException("The same user cannot be duplicated in the service");
      else if(serviceUsers.isEmpty()) continue; // logged user absent in the service, then skip request

      // user role in the service for current logged user
      var curUserRole = serviceUsers.get(0).getUserRole();

      var responseRequestRoleInService = this.responseRequestRoleInServiceRepository.findByRequest(request);

      boolean isAddToView = false;

      switch (curUserRole.getRoleName()) {
        case "user":
          break;

        case "owner":
          if (responseRequestRoleInService.isEmpty())
            isAddToView = true;
          break;

        case "admin":
          if (responseRequestRoleInService.isEmpty())
            break;
//            throw new RuntimeException("The owner should give permission first, how did it get to the admin?");

          for (var item : responseRequestRoleInService) {
            // did the admin give an access ?
            if (
                this.serviceUserRepository.findByUserAndService(
                    item.getUser(),
                    request.getService()
                ).getUserRole().getRoleName().equals("admin")
            )
              break;
            else if (
                this.serviceUserRepository.findByUserAndService(
                    item.getUser(),
                    request.getService()
                ).getUserRole().getRoleName().equals("owner")
                    && item.getTypeResponse().getTypeResponseName().equals("approved")
            ) {
              isAddToView = true;
              break;
            }
          }
          break;

        default:
          throw new IllegalArgumentException("The user's role in the service is not defined");
      }

      // {userRoleName, TypeResponseName}
//      HashMap<String, String> isApproveByUsers = new HashMap<>();

      // find all decisions made by users for this request role
//      var responsesRequestRole = this.responseRequestRoleInServiceRepository.findByRequest(request);
//      for(var responseRequestRole : responsesRequestRole) {
//        isApproveByUsers.put(
//            curUserRole.getRoleName(),
//            responseRequestRole.getTypeResponse().getTypeResponseName()
//        );
//      }

      // add current request to responseDTO or not ?
//      boolean isAddToView = false;
//
//      // procedure for granting access rights
//      switch (curUserRole.getRoleName()) {
//        case "owner":
//          if (
//              !isApproveByUsers.containsKey("owner") ||
//                  (!isApproveByUsers.get("owner").equals("approved") &&
//                      !isApproveByUsers.get("owner").equals("rejected"))
//          )
//            isAddToView = true;
//          break;
//
//        case "admin":
//          if (
//              isApproveByUsers.containsKey("owner") &&
//                  isApproveByUsers.get("owner").equals("approved") &&
//                  (!isApproveByUsers.containsKey("admin") ||
//                      (!isApproveByUsers.get("admin").equals("approved") &&
//                          !isApproveByUsers.get("admin").equals("rejected"))
//                  )
//          )
//            isAddToView = true;
//          break;
//
//        default:
//          throw new IllegalArgumentException("The user's role in the service is not defined");
//      } // /switch (curUserRole.getRoleName())

      // need to add to DTO
      if(isAddToView)
        responseDTO.add(
            ServiceWaitingAccessResponseDTO.builder()
                .serviceId(request.getService().getServiceId())
                .serviceName(request.getService().getServiceName())
                .userId(request.getUserCustomer().getUserId())
                .userName(request.getUserCustomer().getUserName())
                .userRoleName(request.getRequestedRole().getRoleName())
                .build()
        );
    }

    return responseDTO;
  }


  /**
   * Client send response access grant to service.
   * (some user -> |send request role| -> owner -> |yes or no| -> admin -> |yse or no| -> access result)
   *
   * @param serviceId
   * @param fromUserId user who gives access
   * @param toUserId user who gets access
   * @param responseOfUser - possible values: "approved", "rejected"
   */
  public void sendResponseAccessGrantToService(
      Integer serviceId,
      Integer fromUserId,
      Integer toUserId,
      ResponseRequestObtainUserRoleInServiceRequestDTO responseOfUser
  ) {
    var service = this.serviceRepository.findById(serviceId).orElseThrow(EntityNotFoundException::new);
    var fromUser = this.userRepository.findById(fromUserId).orElseThrow(EntityNotFoundException::new);
    var toUser = this.userRepository.findById(toUserId).orElseThrow(EntityNotFoundException::new);

    for (var request : this.requestObtainUserRoleInServiceRepository.findByServiceAndUserCustomerAndIsActive(service, toUser, true)) {
//      if (!request.getIsActive()) continue;

//      request.setIsActive(false);
//      this.requestObtainUserRoleInServiceRepository.save(request);

      // find current logged user in service
      var serviceUsers = request.getService().getUsers().stream().filter(serviceUserEntity ->
          serviceUserEntity.getUser().equals(fromUser)
      ).toList();

      // checked that the logged in user is not duplicated in the service
      if(serviceUsers.size() > 1) throw new RuntimeException("The same user cannot be duplicated in the service");
      else if(serviceUsers.isEmpty()) continue; // logged user absent in the service, then skip request

      // user role in the service for current logged user
      var fromUserRole = serviceUsers.get(0).getUserRole();


      ResponseRequestRoleInServiceEntity responseRequestRoleInService = new ResponseRequestRoleInServiceEntity();
      responseRequestRoleInService.setRequest(request);
      responseRequestRoleInService.setUser(fromUser);
      responseRequestRoleInService.setTypeResponse(this.typeResponseRequestRoleInServiceRepository.findByTypeResponseName(responseOfUser.getTypeResponseName()));
      this.responseRequestRoleInServiceRepository.save(responseRequestRoleInService);


      switch (fromUserRole.getRoleName()) {
        case "owner":
          // if owner give access then send request to admin
          if(responseOfUser.getTypeResponseName().equals("approved")) {
            this.createRequestObtainUserRoleInService(
                request.getService().getServiceId(),
                request.getUserCustomer().getUserId(),
                request.getRequestedRole().getRoleId()
            );
          }
          if(responseOfUser.getTypeResponseName().equals("rejected")) {
            request.setIsActive(false);
            this.requestObtainUserRoleInServiceRepository.save(request);
          }
          break;

        case "admin":
          if(responseOfUser.getTypeResponseName().equals("approved")) {
            var userInServiceFinded = this.serviceUserRepository.findByUserAndService(toUser, service);
            if(userInServiceFinded == null) {
              // the user does not exists in the service, so just create new withrole
              ServiceUserEntity userInService = new ServiceUserEntity();
              userInService.setUser(toUser);
              userInService.setUserRole(request.getRequestedRole());
              userInService.setService(service);
              this.serviceUserRepository.save(userInService);
            }
            else {
              // the user exists in the service, so just update his role
              userInServiceFinded.setUserRole(request.getRequestedRole());
              this.serviceUserRepository.save(userInServiceFinded);
            }
          }

          request.setIsActive(false);
          this.requestObtainUserRoleInServiceRepository.save(request);
          break;

        default:
          throw new IllegalArgumentException("The user's role in service is not allowed");
      } // /switch (fromUserRole.getRoleName())
    }
  }


  /**
   * Load history of access to the service
   *
   * @param serviceId
   * @return
   */
  public List<HistoryRequestAccessResponseDTO> loadRequestsHistory(Integer serviceId) {
    List<HistoryRequestAccessResponseDTO> responseDTO = new LinkedList<>();

    for(var respReqRole : this.responseRequestRoleInServiceRepository.findAll()) {
      var request = respReqRole.getRequest();
      if(!Objects.equals(request.getService().getServiceId(), serviceId)) continue;

      responseDTO.add(
          HistoryRequestAccessResponseDTO.builder()
              .serviceName(request.getService().getServiceName())
              .requestedRole(request.getRequestedRole().getRoleName())
              .userNameCustomer(String.format("%s (%s)", request.getUserCustomer().getEmployee().getFullName(), request.getUserCustomer().getUserName()))
              .userNameGivesAccess(String.format("%s (%s)", respReqRole.getUser().getEmployee().getFullName(), respReqRole.getUser().getUserName()))
              .statusAccess(respReqRole.getTypeResponse().getTypeResponseName())
              .dateCreated(request.getCreatedOn())
              .build()
      );
    }
    return responseDTO;
  }
}
