package sunpp.its.demo.controllers.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateServiceRequestDTO {
    private Integer serviceId;
    private String serviceName;
    private String serviceDesc;
    private List<Integer> usersIdsAsRoleUser;
    private List<Integer> usersIdsAsRoleOwner;
    private List<Integer> usersIdsAsRoleAdmin;
}
