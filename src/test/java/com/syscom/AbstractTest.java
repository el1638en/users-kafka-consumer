package com.syscom;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.syscom.config.TestConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
@TestPropertySource(locations = "classpath:application-test.yml")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@ActiveProfiles("test")
@Transactional
public abstract class AbstractTest {

}
