package sunpp.its.demo.dto.department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.entities.DepartmentEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentRequestDTO {
    private String DepartmentName;

    public static DepartmentEntity convertDTOToEntity (CreateDepartmentRequestDTO dto) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDepartmentName(dto.getDepartmentName());
        return entity;
    }
}
