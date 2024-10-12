package sunpp.its.demo.controllers.department;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DepartmentService departmentService;

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @RequestMapping("/entity-manager")
//    public String pEntityManager() {
//        List<Object[]> departments = entityManager.createNativeQuery("SELECT * FROM Department").getResultList();
//
//        StringBuilder sb = new StringBuilder();
//        for(Object[] department : departments) {
//            sb.append(String.format("{DepartmentID: %d, DepartmentName: %s} <br>", (Integer)department[0], (String)department[1]));
//        }
//        return sb.toString();
//    }

//    @RequestMapping("native/{id}")
//    public ResponseEntity<DepartmentEntity> dep_native(@PathVariable(value = "id") Integer id) {
//        return ResponseEntity.ok().body(departmentService.getDepartmentByID_native(id));
//    }
//
//    @RequestMapping("new")
//    public ResponseEntity<DepartmentEntity> depNew() {
//        return ResponseEntity.ok().body(departmentService.createRandomDepartment());
//    }
//
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "some default value") String name){
//        return String.format("Hello '%s'!", name);
//    }

//    @RequestMapping("/")
//    public ResponseEntity<List<DepartmentEntity>> getAll() {
//        return ResponseEntity.ok().body(departmentService.getDepartmentList());
//    }

    @RequestMapping("/")
    public ResponseEntity<?> getAll() {
        List<DepartmentResponseDTO> response = new LinkedList<DepartmentResponseDTO>();
        for(DepartmentEntity department: departmentService.getDepartmentList())
            response.add(DepartmentResponseDTO.convertEntityToDTO(department));

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateDepartmentRequestDTO department) {
        DepartmentEntity departmentEntity = departmentService.addDepartment(department.convertDTOToEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateDepartmentRequestDTO department) {
        DepartmentEntity departmentEntity = departmentService.updateDepartment(department.convertDTOToEntity());
        return ResponseEntity.ok().body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        DepartmentEntity departmentEntity = departmentService.deleteDepartment(id);
    }
}
