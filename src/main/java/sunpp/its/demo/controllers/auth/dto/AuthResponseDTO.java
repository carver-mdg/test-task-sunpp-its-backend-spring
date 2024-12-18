package sunpp.its.demo.controllers.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.auth.TypeUserRoleInSystemEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
  private Integer userId;
  private String userName;
  private Integer userRoleId;
  private String userRoleName;


  /**
   * @param userEntity
   * @param userRoleInSystemEntity
   * @return
   */
  public static AuthResponseDTO convertEntitiesToDTO(
      UserEntity userEntity,
      TypeUserRoleInSystemEntity userRoleInSystemEntity
  ) {
    AuthResponseDTO responseDTO = new AuthResponseDTO();
    responseDTO.setUserId(userEntity.getUserId());
    responseDTO.setUserName(userEntity.getUserName());
    responseDTO.setUserRoleId(userRoleInSystemEntity.getRoleId());
    responseDTO.setUserRoleName(userRoleInSystemEntity.getRoleName());
    return responseDTO;
  }
}
