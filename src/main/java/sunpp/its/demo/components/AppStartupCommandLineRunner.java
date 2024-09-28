package sunpp.its.demo.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sunpp.its.demo.shared.services.FirstInitDbService;

@Component
public class AppStartupCommandLineRunner implements CommandLineRunner {
    @Autowired
    FirstInitDbService firstInitDbService;

    @Override
    public void run(String ...args) throws Exception {
        firstInitDbService.create();
    }
}
