package sunpp.its.demo.controllers.user;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.controllers.user.dto.CreateUserRequestDTO;
import sunpp.its.demo.controllers.user.dto.UpdateUserRequestDTO;
import sunpp.its.demo.shared.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private final UserService userService;


  /**
   * @param userService
   */
  public UserController(UserService userService) {
    this.userService = userService;
  }


  /**
   * Get list of users
   *
   * @return List of DTOs
   */
  @GetMapping("/")
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
   * @param userId ID of user
   * @param reqDTO DTO of user from client
   * @return DTO of updated user
   * @throws EntityNotFoundException if record not found
   */
  @PutMapping("/{userId}")
  public ResponseEntity<?> update(
      @PathVariable Integer userId,
      @RequestBody UpdateUserRequestDTO reqDTO
  ) throws BadRequestException {
    if (!reqDTO.getUserId().equals(userId)) throw new BadRequestException();
    return ResponseEntity.ok().body(userService.updateUser(reqDTO));
  }


  /**
   * Delete of exist user
   *
   * @param userId ID of user
   * @throws EntityNotFoundException if record not found
   */
  @DeleteMapping("/{userId}")
  public void delete(@PathVariable Integer userId) {
    userService.deleteUser(userId);
  }
}
