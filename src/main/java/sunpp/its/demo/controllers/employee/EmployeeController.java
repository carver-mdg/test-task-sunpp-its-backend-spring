package sunpp.its.demo.controllers.employee;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.employee.dto.CreateEmployeeRequestDTO;
import sunpp.its.demo.controllers.employee.dto.UpdateEmployeeRequestDTO;
import sunpp.its.demo.shared.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
  private final EmployeeService employeeService;


  /**
   * @param employeeService
   */
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }


  /**
   * Get list of employees
   *
   * @return List of DTOs of employees
   */
  @GetMapping("/")
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
   * @param employeeId ID of employee
   * @param eReqDTO    DTO of employee from client
   * @return DTO of updated employee
   * @throws BadRequestException if record not found
   */
  @PutMapping("/{employeeId}")
  public ResponseEntity<?> update(
      @PathVariable Integer employeeId,
      @RequestBody UpdateEmployeeRequestDTO eReqDTO
  ) throws BadRequestException {
    if (!eReqDTO.getEmployeeId().equals(employeeId)) throw new BadRequestException();
    return ResponseEntity.ok().body(employeeService.updateEmployee(eReqDTO));
  }


  /**
   * Delete of exist of employee
   *
   * @param employeeId ID of employee
   * @throws EntityNotFoundException if record not found
   */
  @DeleteMapping("/{employeeId}")
  public void delete(@PathVariable Integer employeeId) {
    employeeService.deleteEmployee(employeeId);
  }
}
