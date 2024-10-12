package sunpp.its.demo.controllers.adminSys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sunpp.its.demo.shared.services.FirstInitDbService;

@RestController
@RequestMapping("/api/v1/admin-sys")
public class AdminSysController {
    @Autowired
    FirstInitDbService firstInitDbService;

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

    @PostMapping("/truncate-db")
    public ResponseEntity<?> truncateDB() {
        this.firstInitDbService.truncateDB();
        return ResponseEntity.ok().body(null);
    }
}
