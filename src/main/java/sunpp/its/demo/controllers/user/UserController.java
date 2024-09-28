package sunpp.its.demo.controllers.user;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.user.dto.CreateUserRequestDTO;
import sunpp.its.demo.controllers.user.dto.UpdateUserRequestDTO;
import sunpp.its.demo.shared.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin()
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Get list of users
     *
     * @return List of DTOs
     */
    @RequestMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(userService.getUsersList());
    }

    /**
     * Create new user
     *
     * @param reqDTO DTO of user from client
     * @return DTO of created user
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateUserRequestDTO reqDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(reqDTO));
    }

    /**
     * Update of exist user
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of user
     * @param reqDTO DTO of user from client
     * @return DTO of updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateUserRequestDTO reqDTO) throws BadRequestException {
        if(!reqDTO.getUserID().equals(id)) throw new BadRequestException();
        return ResponseEntity.ok().body(userService.updateUser(reqDTO));
    }

    /**
     * Delete of exist user
     *
     * @throws EntityNotFoundException if record not found
     * @param id ID of user
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
