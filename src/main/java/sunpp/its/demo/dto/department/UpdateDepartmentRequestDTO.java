package sunpp.its.demo.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.entities.DepartmentEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequestDTO {
    private Integer DepartmentID;
    private String DepartmentName;

    public static DepartmentEntity convertDTOToEntity (UpdateDepartmentRequestDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDepartmentID(dto.getDepartmentID());
        entity.setDepartmentName(dto.getDepartmentName());
        return entity;
    }
}
