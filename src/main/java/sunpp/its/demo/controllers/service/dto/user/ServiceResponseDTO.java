package sunpp.its.demo.controllers.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.service.ServiceEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO {
  private Integer serviceId;
  private String serviceName;
  private String serviceDesc;
  private Boolean isHasAccess;  // user has access to this service ?
  private String userRoleName;
  private String userRoleNameRequested;
  private String userRoleNameRequestedStatus; // the status of the role promotion request


  /**
   * Convert entity to response DTO to clients
   *
   * @param serviceEntity Entity of service system
   * @return DTO of service system
   */
  public static ServiceResponseDTO convertEntityToDTO(
      ServiceEntity serviceEntity,
      Boolean isHasAccess,
      String userRoleName,
      String userRoleNameRequested,
      String userRoleNameRequestedStatus
  ) {
    ServiceResponseDTO response = new ServiceResponseDTO();
    response.setServiceId(serviceEntity.getServiceId());
    response.setServiceName(serviceEntity.getServiceName());
    response.setServiceDesc(serviceEntity.getServiceDesc());

    response.setIsHasAccess(isHasAccess);
    response.setUserRoleName(userRoleName);
    response.setUserRoleNameRequested(userRoleNameRequested);
    response.setUserRoleNameRequestedStatus(userRoleNameRequestedStatus);

    return response;
  }
}
