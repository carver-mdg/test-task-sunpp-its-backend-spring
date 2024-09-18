package sunpp.its.demo.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.entities.DepartmentEntity;

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