package dalmineks.common;

import dalmineks.MyPageApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { MyPageApplication.class })
public class CucumberSpingConfiguration {}
