package sunpp.its.demo.controllers.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.service.TypeUserRoleInServiceEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleInServiceResponseDTO {
  private Integer roleId;
  private String roleName;


  /**
   * Convert entity to response DTO to clients
   *
   * @param typeUserRoleInServiceEntity Entity of user role types
   * @return DTO
   */
  public static UserRoleInServiceResponseDTO convertEntityToDTO(TypeUserRoleInServiceEntity typeUserRoleInServiceEntity) {
    UserRoleInServiceResponseDTO response = new UserRoleInServiceResponseDTO();
    response.setRoleId(typeUserRoleInServiceEntity.getRoleId());
    response.setRoleName(typeUserRoleInServiceEntity.getRoleName());

    return response;
  }
}
