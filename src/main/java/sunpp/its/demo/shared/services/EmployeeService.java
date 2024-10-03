package sunpp.its.demo.shared.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sunpp.its.demo.controllers.employee.dto.CreateEmployeeRequestDTO;
import sunpp.its.demo.controllers.employee.dto.EmployeeResponseDTO;
import sunpp.its.demo.controllers.employee.dto.UpdateEmployeeRequestDTO;
import sunpp.its.demo.shared.entities.EmployeeEntity;
import sunpp.its.demo.shared.repositories.EmployeeRepository;
import sunpp.its.demo.shared.repositories.StaffUnitRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private StaffUnitRepository staffUnitRepository;


    /**
     * Get list of all employees
     *
     * @return list of DTOs
     */
    public List<EmployeeResponseDTO> getEmployeesList() {
        List<EmployeeResponseDTO> response = new LinkedList<>();
        for (EmployeeEntity employee : this.employeeRepository.findAll())
            response.add(EmployeeResponseDTO.convertEntityToDTO(employee));
        return response;
    }

    /**
     * Create new employee from createDTO
     *
     * @param eReqDTO DTO for create entity
     * @return DTO
     */
    public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO eReqDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFullName(eReqDTO.getFullName());
        employeeEntity.setStaffUnit(this.staffUnitRepository.findById(eReqDTO.getStaffUnitID()).orElseThrow());
        this.employeeRepository.save(employeeEntity);

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setEmployeeID(employeeEntity.getEmployeeId());
        responseDTO.setFullName(employeeEntity.getFullName());
        responseDTO.setStaffUnitID(employeeEntity.getStaffUnit().getStaffUnitId());

        return responseDTO;
    }

    /**
     * Update employee from updateDTO
     *
     * @param eReqDTO DTO for update entities
     * @return DTO
     */
    public EmployeeResponseDTO updateEmployee(UpdateEmployeeRequestDTO eReqDTO) {
        this.employeeRepository.findById(eReqDTO.getEmployeeID()).orElseThrow(EntityNotFoundException::new);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(eReqDTO.getEmployeeID());
        employeeEntity.setFullName(eReqDTO.getFullName());
        employeeEntity.setStaffUnit(this.staffUnitRepository.findById(eReqDTO.getStaffUnitID()).orElseThrow());

        this.employeeRepository.save(employeeEntity);
        return EmployeeResponseDTO.convertEntityToDTO(employeeEntity);
    }

    /**
     * Delete employee
     *
     * @param id ID of entity
     */
    public void deleteEmployee(Integer id) {
        this.employeeRepository.delete(this.employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
