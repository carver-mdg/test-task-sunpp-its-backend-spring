package sunpp.its.demo.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sunpp.its.demo.controllers.auth.dto.AuthRequestDTO;
import sunpp.its.demo.controllers.auth.dto.AuthResponseDTO;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.entities.auth.TypeUserRoleInSystemEntity;
import sunpp.its.demo.shared.repositories.UserRepository;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final UserRepository userRepository;


  /**
   * @param userRepository
   */
  public AuthController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  /**
   * @param reqDTO
   * @return
   * @FIXME this is emulated auth
   */
  @PostMapping("/signin")
  public ResponseEntity<?> signin(@RequestBody AuthRequestDTO reqDTO) {
    UserEntity userEntity = new UserEntity();
    TypeUserRoleInSystemEntity userRoleInSystemEntity = new TypeUserRoleInSystemEntity();

    if (reqDTO.getUserName().equals("admin")) {
      userEntity.setUserId(0);
      userEntity.setUserName("admin");
      userRoleInSystemEntity.setRoleId(0);
      userRoleInSystemEntity.setRoleName("admin");
    } else {
      userEntity = this.userRepository.findByUserName(reqDTO.getUserName());
      if (userEntity == null) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
      userRoleInSystemEntity.setRoleId(10);
      userRoleInSystemEntity.setRoleName("user");
    }

    return ResponseEntity.status(HttpStatus.OK).body(AuthResponseDTO.convertEntitiesToDTO(userEntity, userRoleInSystemEntity));
  }


  /**
   * Check user autorized
   *
   * @param reqDTO
   * @return
   */
  @PostMapping("/check")
  public ResponseEntity<?> check(@RequestBody AuthRequestDTO reqDTO) {
    if (reqDTO.getUserName().equals("admin") || this.userRepository.findByUserName(reqDTO.getUserName()) != null)
      return ResponseEntity.status(HttpStatus.OK).body(true);
    return ResponseEntity.status(HttpStatus.OK).body(false);
  }
}
