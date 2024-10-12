package sunpp.its.demo.controllers.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.service.dto.CreateServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.UpdateServiceRequestDTO;
import sunpp.its.demo.shared.services.ServiceService;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    /**
     * Get list of services
     *
     * @return List of DTOs of services
     */
    @RequestMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(serviceService.getServicesList());
    }

    /**
     * Create new service
     *
     * @param reqDTO DTO of service from client
     * @return DTO of created service
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateServiceRequestDTO reqDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.createService(reqDTO));
    }

    /**
     * Update of exist of service
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of service
     * @param reqDTO DTO of service from client
     * @return DTO of updated service
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateServiceRequestDTO reqDTO) throws BadRequestException {
        if(!reqDTO.getServiceId().equals(id)) throw new BadRequestException();
        return ResponseEntity.ok().body(serviceService.updateService(reqDTO));
    }

    /**
     * Delete of exist of service
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of service
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        serviceService.deleteService(id);
    }
}
