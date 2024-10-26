package sunpp.its.demo.controllers.department;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.department.dto.CreateDepartmentRequestDTO;
import sunpp.its.demo.controllers.department.dto.DepartmentResponseDTO;
import sunpp.its.demo.controllers.department.dto.UpdateDepartmentRequestDTO;
import sunpp.its.demo.shared.entities.DepartmentEntity;
import sunpp.its.demo.shared.services.DepartmentService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {
  private final DepartmentService departmentService;


  /**
   * @param departmentService
   */
  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }


  /**
   * Get list of departments
   *
   * @return
   */
  @GetMapping("/")
  public ResponseEntity<?> getAll() {
    List<DepartmentResponseDTO> response = new LinkedList<DepartmentResponseDTO>();
    for (DepartmentEntity department : departmentService.getDepartmentsList())
      response.add(DepartmentResponseDTO.convertEntityToDTO(department));

    return ResponseEntity.ok().body(response);
  }


  /**
   * Create new department
   *
   * @param department
   * @return
   */
  @PostMapping("/")
  public ResponseEntity<?> create(@RequestBody CreateDepartmentRequestDTO department) {
    DepartmentEntity departmentEntity = departmentService.addDepartment(department.convertDTOToEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
  }


  /**
   * Update of exist of department
   *
   * @param departmentId
   * @param department
   * @return
   */
  @PutMapping("/{departmentId}")
  public ResponseEntity<?> update(
      @PathVariable Integer departmentId,
      @RequestBody UpdateDepartmentRequestDTO department
  ) {
    DepartmentEntity departmentEntity = departmentService.updateDepartment(department.convertDTOToEntity());
    return ResponseEntity.ok().body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
  }


  /**
   * Delete of exist of department
   *
   * @param departmentId
   */
  @DeleteMapping("/{departmentId}")
  public void delete(@PathVariable Integer departmentId) {
    DepartmentEntity departmentEntity = departmentService.deleteDepartment(departmentId);
  }
}
