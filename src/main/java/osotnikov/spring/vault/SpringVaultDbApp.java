package osotnikov.spring.vault;

import osotnikov.spring.vault.config.StaticVaultProps;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(StaticVaultProps.class)
public class SpringVaultDbApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringVaultDbApp.class, args);
    }

}
