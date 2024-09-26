package sunpp.its.demo.controllers.employee;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.employee.dto.CreateEmployeeRequestDTO;
import sunpp.its.demo.controllers.employee.dto.UpdateEmployeeRequestDTO;
import sunpp.its.demo.shared.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin()
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * Get list of employees
     *
     * @return List of DTOs of employees
     */
    @RequestMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(employeeService.getEmployeesList());
    }

    /**
     * Create new employee
     *
     * @param eReqDTO DTO of employee from client
     * @return DTO of created employee
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateEmployeeRequestDTO eReqDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(eReqDTO));
    }

    /**
     * Update of exist of employee
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of employee
     * @param eReqDTO DTO of employee from client
     * @return DTO of updated employee
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateEmployeeRequestDTO eReqDTO) throws BadRequestException {
        if(!eReqDTO.getEmployeeID().equals(id)) throw new BadRequestException();
        return ResponseEntity.ok().body(employeeService.updateEmployee(eReqDTO));
    }

    /**
     * Delete of exist of employee
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of employee
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
