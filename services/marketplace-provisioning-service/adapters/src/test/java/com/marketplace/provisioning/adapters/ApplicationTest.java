package com.marketplace.provisioning.adapters;

import com.marketplace.provisioning.adapters.config.AbstractMongoIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ApplicationTest extends AbstractMongoIT {

    @Test
    void contextLoads() {
    }
}
