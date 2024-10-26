package sunpp.its.demo.shared.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import sunpp.its.demo.controllers.user.dto.CreateUserRequestDTO;
import sunpp.its.demo.controllers.user.dto.UpdateUserRequestDTO;
import sunpp.its.demo.controllers.user.dto.UserResponseDTO;
import sunpp.its.demo.shared.entities.UserEntity;
import sunpp.its.demo.shared.repositories.EmployeeRepository;
import sunpp.its.demo.shared.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final EmployeeRepository employeeRepository;


  /**
   * @param userRepository
   * @param employeeRepository
   */
  public UserService(
      UserRepository userRepository,
      EmployeeRepository employeeRepository
  ) {
    this.userRepository = userRepository;
    this.employeeRepository = employeeRepository;
  }


  /**
   * Get list of all users
   *
   * @return list of DTOs
   */
  public List<UserResponseDTO> getUsersList() {
    List<UserResponseDTO> response = new LinkedList<>();
    for (UserEntity user : this.userRepository.findAll())
      response.add(UserResponseDTO.convertEntityToDTO(user));
    return response;
  }


  /**
   * Create new user from createDTO
   *
   * @param reqDTO DTO for create entity
   * @return DTO
   */
  public UserResponseDTO createUser(CreateUserRequestDTO reqDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(reqDTO.getUserName());
    userEntity.setPassword(reqDTO.getPassword());
    userEntity.setEmployee(this.employeeRepository.findById(reqDTO.getEmployeeId()).orElseThrow());
    this.userRepository.save(userEntity);

    UserResponseDTO responseDTO = new UserResponseDTO();
    responseDTO.setUserId(userEntity.getUserId());
    responseDTO.setUserName(userEntity.getUserName());
    responseDTO.setEmployeeId(userEntity.getEmployee().getEmployeeId());

    return responseDTO;
  }


  /**
   * Update user from updateDTO
   *
   * @param reqDTO DTO for update entities
   * @return DTO
   */
  public UserResponseDTO updateUser(UpdateUserRequestDTO reqDTO) {
    this.userRepository.findById(reqDTO.getUserId()).orElseThrow(EntityNotFoundException::new);

    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(reqDTO.getUserId());
    userEntity.setUserName(reqDTO.getUserName());
    userEntity.setPassword(reqDTO.getPassword());
    userEntity.setEmployee(this.employeeRepository.findById(reqDTO.getEmployeeId()).orElseThrow());

    this.userRepository.save(userEntity);
    return UserResponseDTO.convertEntityToDTO(userEntity);
  }


  /**
   * Delete user
   *
   * @param userId ID of entity
   */
  public void deleteUser(Integer userId) {
    this.userRepository.delete(this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
  }
}
