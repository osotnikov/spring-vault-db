package osotnikov.spring.vault.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("static-props")
public class StaticVaultProps {

    private String propA;
    private String propB;
}
