package sunpp.its.demo.shared.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunpp.its.demo.controllers.staffunit.dto.CreateStaffUnitRequestDTO;
import sunpp.its.demo.controllers.staffunit.dto.StaffUnitResponseDTO;
import sunpp.its.demo.controllers.staffunit.dto.UpdateStaffUnitRequestDTO;
import sunpp.its.demo.shared.entities.DepartmentEntity;
import sunpp.its.demo.shared.entities.StaffUnitEntity;
import sunpp.its.demo.shared.repositories.DepartmentRepository;
import sunpp.its.demo.shared.repositories.StaffUnitRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class StaffUnitService {
    @Autowired
    private StaffUnitRepository staffUnitRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    /**
     * Get list of all staff units
     *
     * @return list of DTOs
     */
    public List<StaffUnitResponseDTO> getStaffUnitsList() {
        List<StaffUnitResponseDTO> response = new LinkedList<>();
        for (StaffUnitEntity staffUnit : this.staffUnitRepository.findAll())
            response.add(StaffUnitResponseDTO.convertEntityToDTO(staffUnit));
        return response;
    }

    /**
     * Create new staff unit from createDTO
     *
     * @param suReqDTO DTO for create entity
     * @return DTO
     */
    public StaffUnitResponseDTO createStaffUnit(CreateStaffUnitRequestDTO suReqDTO) {
        DepartmentEntity department = this.departmentRepository.findById(suReqDTO.getDepartmentID()).orElseThrow();

        StaffUnitEntity staffUnit = new StaffUnitEntity();
        staffUnit.setStaffUnitName(suReqDTO.getStaffUnitName());
        staffUnit.setDepartment(department);
        staffUnit = this.staffUnitRepository.save(staffUnit);

        StaffUnitResponseDTO responseDTO = new StaffUnitResponseDTO();
        responseDTO.setDepartmentID(department.getDepartmentID());
        responseDTO.setStaffUnitID(staffUnit.getStaffUnitID());
        responseDTO.setStaffUnitName(staffUnit.getStaffUnitName());

        return responseDTO;
    }

    /**
     * Update exist staff unit from updateDTO
     *
     * @param suReqDTO DTO for update entities
     * @return DTO
     */
    public StaffUnitResponseDTO updateStaffUnit(UpdateStaffUnitRequestDTO suReqDTO) {
        StaffUnitEntity entity = new StaffUnitEntity();
        entity.setStaffUnitID(suReqDTO.getStaffUnitID());
        entity.setStaffUnitName(suReqDTO.getStaffUnitName());
        entity.setDepartment(this.departmentRepository.findById(suReqDTO.getDepartmentID()).orElseThrow());

        this.staffUnitRepository.save(entity);
        return StaffUnitResponseDTO.convertEntityToDTO(entity);
    }

    /**
     * Delete staff unit
     *
     * @param id ID of entity
     */
    public void deleteStaffUnit(Integer id) {
        StaffUnitEntity staffUnit = this.staffUnitRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        this.staffUnitRepository.delete(staffUnit);
    }
}
