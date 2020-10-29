package de.fabiankrueger.scc.stubrunnerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;

@SpringBootApplication
@EnableStubRunnerServer
public class StubRunnerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StubRunnerServerApplication.class, args);
    }

}
