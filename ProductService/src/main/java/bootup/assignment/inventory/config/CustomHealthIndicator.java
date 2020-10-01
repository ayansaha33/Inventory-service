package bootup.assignment.inventory.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/*
 * ----------- Requirement 3 -----------
 *  custom actuator health indicator
 * 
 */


@Component
public class CustomHealthIndicator implements HealthIndicator {
        private final String message_key = "Service A";
    @Override
    public Health health() {
        if (!isRunningServiceA()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }
    private Boolean isRunningServiceA() {
        Boolean isRunning = true;
        // Logic Skipped
        return isRunning;
    }
}