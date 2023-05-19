package dalmineks.common;

import dalmineks.FrontApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { FrontApplication.class })
public class CucumberSpingConfiguration {}
