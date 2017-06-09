package com.example;

import com.example.controller.OrganizationRestControllerTest;
import com.example.controller.PersonRestControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by AjmalCholassery on 4/4/17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                DemoApplicationTests.class,
                PersonRestControllerTest.class,
                OrganizationRestControllerTest.class
        })
public class JunitTestSuite {
}
