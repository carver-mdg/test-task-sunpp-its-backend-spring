package sunpp.its.demo.controllers.adminSys;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sunpp.its.demo.shared.services.FirstInitDbService;

@RestController
@RequestMapping("/api/v1/admin-sys")
public class AdminSysController {
  private final FirstInitDbService firstInitDbService;


  /**
   * @param firstInitDbService
   */
  public AdminSysController(FirstInitDbService firstInitDbService) {
    this.firstInitDbService = firstInitDbService;
  }


  /**
   * Created mock data
   *
   * @return void
   */
  @PostMapping("/fill-mock-data")
  public ResponseEntity<?> fillMockData() {
    this.firstInitDbService.createDepartments();
    this.firstInitDbService.createStaffUnits();
    this.firstInitDbService.createEmployees();
    this.firstInitDbService.createUsers();
    this.firstInitDbService.createServices();
    return ResponseEntity.ok().body(null);
  }


  /**
   * Truncate database
   *
   * @return
   */
  @PostMapping("/truncate-db")
  public ResponseEntity<?> truncateDB() {
    this.firstInitDbService.truncateDB();
    return ResponseEntity.ok().body(null);
  }
}
