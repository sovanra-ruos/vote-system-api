package istad.co.votingsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		servers = {
				@Server(url = "https://vote-system-api.psa-khmer.world", description = "Deploy API Server"),
		},
        info = @Info(
                title = "Vote system",
                version = "1.0",
                description = "Vote system"
        )
)

public class VotingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplication.class, args);
    }

}
