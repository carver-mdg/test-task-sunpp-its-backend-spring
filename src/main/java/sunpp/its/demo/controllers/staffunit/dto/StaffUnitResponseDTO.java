package sunpp.its.demo.controllers.staffunit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.StaffUnitEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffUnitResponseDTO {
    private Integer StaffUnitID;
    private String StaffUnitName;
    private Integer DepartmentID;

    /**
     * Convert entity to response DTO to clients
     *
     * @param staffUnitEntity Entity of staff unit
     * @return DTO of staff unit
     */
    public static StaffUnitResponseDTO convertEntityToDTO (StaffUnitEntity staffUnitEntity) {
        StaffUnitResponseDTO response = new StaffUnitResponseDTO();
        response.setStaffUnitID(staffUnitEntity.getStaffUnitID());
        response.setStaffUnitName(staffUnitEntity.getStaffUnitName());
        response.setDepartmentID(staffUnitEntity.getDepartment().getDepartmentID());
        return response;
    }
}