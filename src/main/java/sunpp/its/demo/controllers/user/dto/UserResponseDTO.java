package sunpp.its.demo.controllers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Integer UserID;
    private String userName;
    private Integer EmployeeID;

    /**
     * Convert entity to response DTO to clients
     *
     * @param userEntity Entity of employee
     * @return DTO of users
     */
    public static UserResponseDTO convertEntityToDTO (UserEntity userEntity) {
        UserResponseDTO response = new UserResponseDTO();
        response.setUserID(userEntity.getUserId());
        response.setUserName(userEntity.getUserName());
        response.setEmployeeID(userEntity.getEmployee().getEmployeeId());
        return response;
    }
}