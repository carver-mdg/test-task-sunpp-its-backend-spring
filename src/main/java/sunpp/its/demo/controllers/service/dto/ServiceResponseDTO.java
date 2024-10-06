package sunpp.its.demo.controllers.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.service.ServiceEntity;

import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseDTO {
    private Integer serviceId;
    private String serviceName;
    private String serviceDesc;
    private List<Integer> usersIdsAsRoleUser;
    private List<Integer> usersIdsAsRoleOwner;
    private List<Integer> usersIdsAsRoleAdmin;


    /**
     * Convert entity to response DTO to clients
     *
     * @param serviceEntity Entity of service system
     * @return DTO of service system
     */
    public static ServiceResponseDTO convertEntityToDTO (ServiceEntity serviceEntity) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        response.setServiceId(serviceEntity.getServiceId());
        response.setServiceName(serviceEntity.getServiceName());
        response.setServiceDesc(serviceEntity.getServiceDesc());

        response.setUsersIdsAsRoleUser(getUsersByRole(serviceEntity,"user"));
        response.setUsersIdsAsRoleOwner(getUsersByRole(serviceEntity,"owner"));
        response.setUsersIdsAsRoleAdmin(getUsersByRole(serviceEntity,"admin"));

        return response;
    }


    /**
     * Get list user in service by it role name in service
     *
     * @param serviceEntity entity of service system
     * @param userRole user role in service
     * @return List of IDs of users by it roles
     */
    private static List<Integer> getUsersByRole (ServiceEntity serviceEntity, String userRole) {
        List<Integer> usersIds = new LinkedList<>();
        for(var user: serviceEntity.getUsers()) {
            if(user.getUserRole().getRoleName().equals(userRole))
                usersIds.add(user.getUser().getUserId());
        }

        return usersIds;
    }
}