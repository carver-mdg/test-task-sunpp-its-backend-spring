package sunpp.its.demo.controllers.service;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.service.dto.adminsys.CreateServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.adminsys.UpdateServiceRequestDTO;
import sunpp.its.demo.controllers.service.dto.user.ResponseRequestObtainUserRoleInServiceRequestDTO;
import sunpp.its.demo.shared.services.ServiceService;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    /**
     * Get list of services for user by admin of system
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


    /**
     * Get list of services for usage by users
     *
     * @return List of DTOs of services
     */
    @RequestMapping("/viewer/{user_id}")
    public ResponseEntity<?> getServicesViewer(@PathVariable Integer user_id) {
        return ResponseEntity.ok().body(serviceService.getServicesViewerList(user_id));
    }

    /**
     * Get service item
     *
     * @return List of DTOs of services
     */
    @RequestMapping("/{service_id}/users/{user_id}")
    public ResponseEntity<?> getServiceItem(@PathVariable Integer service_id, @PathVariable Integer user_id) {
        if(!serviceService.isHasAccessUserToService(service_id, user_id))
            throw new AccessDeniedException("user don't have access to this service");

        return ResponseEntity.ok().body( String.format("service: %d, random value: %d", service_id, (int)(Math.random() * 100)) );
    }


    /**
     * Get possible user role types in services
     *
     * @return
     */
    @GetMapping("/users/types/roles")
    public ResponseEntity<?> getUserRoleTypes () {
        return ResponseEntity.ok().body(serviceService.getUserRoleTypes());
    }


    /**
     * Get services where user (user_id) has role (role_id)
     *
     * @param userId
     * @param roleId
     * @return
     */
    @GetMapping("/users/{userId}/role/{roleId}")
    public ResponseEntity<?> getServicesByUserAndUserRole (
        @PathVariable Integer userId,
        @PathVariable Integer roleId
    ) {
        return ResponseEntity.ok().body(serviceService.getServicesListByUserAndUserRole(userId, roleId));
    }


    /**
     * A request to obtain the user role in the service
     *
     * @param serviceId
     * @param userId
     * @param userRoleId
     * @return
     */
    @PostMapping("/{serviceId}/request/obtain/users/{userId}/role/{userRoleId}")
    public ResponseEntity<?> requestObtainUserRoleInService(
        @PathVariable Integer serviceId,
        @PathVariable Integer userId,
        @PathVariable Integer userRoleId
    ) {
        this.serviceService.createRequestObtainUserRoleInService(serviceId, userId, userRoleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    /**
     * Loading services which waiting approved to access
     *
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}/access/response/waiting")
    public ResponseEntity<?> getServicesWaitingAccess(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(serviceService.getServicesWaitingAccess(userId));
    }


    /**
     * Client send response access grant to service
     *
     * @param serviceId
     * @param fromUserId
     * @param toUserId
     * @param responseOfUser
     * @return
     */
    @PostMapping("/{serviceId}/request/obtain/role/users/from/{fromUserId}/to/{toUserId}")
    public ResponseEntity<?> sendResponseAccessGrantToService(
        @PathVariable Integer serviceId,
        @PathVariable Integer fromUserId,
        @PathVariable Integer toUserId,
        @RequestBody ResponseRequestObtainUserRoleInServiceRequestDTO responseOfUser
    ) {
        this.serviceService.sendResponseAccessGrantToService(serviceId, fromUserId, toUserId, responseOfUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    /**
     *
     * @param serviceId
     * @return
     */
    @GetMapping("/{serviceId}/history")
    public ResponseEntity<?> getRequestsHistory(@PathVariable Integer serviceId) {
        return ResponseEntity.ok().body(serviceService.loadRequestsHistory(serviceId));
    }

}
