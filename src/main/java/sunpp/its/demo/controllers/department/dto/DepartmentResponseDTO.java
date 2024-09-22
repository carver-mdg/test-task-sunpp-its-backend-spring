package sunpp.its.demo.controllers.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.DepartmentEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    private Integer DepartmentID;
    private String DepartmentName;

    public static DepartmentResponseDTO convertEntityToDTO (DepartmentEntity departmentEntity) {
        DepartmentResponseDTO response = new DepartmentResponseDTO();
        response.setDepartmentID(departmentEntity.getDepartmentID());
        response.setDepartmentName(departmentEntity.getDepartmentName());
        return response;
    }
}