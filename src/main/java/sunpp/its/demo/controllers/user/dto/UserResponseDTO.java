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
    private String Login;
    private Integer EmployeeID;

    /**
     * Convert entity to response DTO to clients
     *
     * @param userEntity Entity of employee
     * @return DTO of users
     */
    public static UserResponseDTO convertEntityToDTO (UserEntity userEntity) {
        UserResponseDTO response = new UserResponseDTO();
        response.setUserID(userEntity.getUserID());
        response.setLogin(userEntity.getLogin());
        response.setEmployeeID(userEntity.getEmployee().getEmployeeID());
        return response;
    }
}