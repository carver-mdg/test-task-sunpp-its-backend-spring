package sunpp.its.demo.controllers.department.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sunpp.its.demo.shared.entities.DepartmentEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequestDTO {
    private Integer DepartmentID;
    private String DepartmentName;

//    public static DepartmentEntity convertDTOToEntity (UpdateDepartmentRequestDTO dto) {
//        DepartmentEntity entity = new DepartmentEntity();
//        entity.setDepartmentID(dto.getDepartmentID());
//        entity.setDepartmentName(dto.getDepartmentName());
//        return entity;
//    }

    public DepartmentEntity convertDTOToEntity () {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setDepartmentID(this.getDepartmentID());
        entity.setDepartmentName(this.getDepartmentName());
        return entity;
    }
}
