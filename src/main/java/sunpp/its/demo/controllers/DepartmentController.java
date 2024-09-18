package sunpp.its.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.dto.department.CreateDepartmentRequestDTO;
import sunpp.its.demo.dto.department.DepartmentResponseDTO;
import sunpp.its.demo.dto.department.UpdateDepartmentRequestDTO;
import sunpp.its.demo.entities.DepartmentEntity;
import sunpp.its.demo.services.DepartmentService;

import javax.swing.text.html.parser.Entity;
import java.util.*;

@RestController
@RequestMapping("/api/v1/departments")
@CrossOrigin()
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
        DepartmentEntity departmentEntity = departmentService.addDepartment(CreateDepartmentRequestDTO.convertDTOToEntity(department));
        return ResponseEntity.ok().body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UpdateDepartmentRequestDTO department, @PathVariable Integer id) {
        DepartmentEntity departmentEntity = departmentService.updateDepartment(UpdateDepartmentRequestDTO.convertDTOToEntity(department));
        return ResponseEntity.ok().body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        DepartmentEntity departmentEntity = departmentService.deleteDepartment(id);
        return ResponseEntity.ok().body(DepartmentResponseDTO.convertEntityToDTO(departmentEntity));
    }
}
