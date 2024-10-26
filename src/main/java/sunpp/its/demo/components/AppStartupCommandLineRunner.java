package sunpp.its.demo.components;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sunpp.its.demo.shared.services.FirstInitDbService;

@Component
public class AppStartupCommandLineRunner implements CommandLineRunner {
  private final FirstInitDbService firstInitDbService;


  /**
   * @param firstInitDbService
   */
  public AppStartupCommandLineRunner(FirstInitDbService firstInitDbService) {
    this.firstInitDbService = firstInitDbService;
  }


  /**
   * @param args
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    firstInitDbService.create();
  }
}
