package sunpp.its.demo.controllers.staffunit;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.staffunit.dto.CreateStaffUnitRequestDTO;
import sunpp.its.demo.controllers.staffunit.dto.UpdateStaffUnitRequestDTO;
import sunpp.its.demo.shared.services.StaffUnitService;

@RestController
@RequestMapping("/api/v1/staff-units")
public class StaffUnitController {
    @Autowired
    private StaffUnitService staffUnitService;

    /**
     * Get list of staff units
     *
     * @return List of DTOs of staff units
     */
    @RequestMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(staffUnitService.getStaffUnitsList());
    }

    /**
     * Create new staff unit
     *
     * @param suReqDTO DTO of staff unit from client
     * @return DTO of created staff unit
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateStaffUnitRequestDTO suReqDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffUnitService.createStaffUnit(suReqDTO));
    }

    /**
     * Update of exist of staff unit
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of staff unit
     * @param suReqDTO DTO of staff unit from client
     * @return DTO of updated staff unit
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateStaffUnitRequestDTO suReqDTO) throws BadRequestException {
        if(!suReqDTO.getStaffUnitID().equals(id)) throw new BadRequestException();
        return ResponseEntity.ok().body(staffUnitService.updateStaffUnit(suReqDTO));
    }

    /**
     * Delete of exist of staff unit
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of staff unit
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        staffUnitService.deleteStaffUnit(id);
    }
}
