package download;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;
import reactor.rabbitmq.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

@RestController
@SpringBootApplication
public class DownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownloadApplication.class, args);

        HttpClient.create()
                .baseUrl("http://localhost:8081/employees")
                .get()
                .response((httpClientResponse, byteBufFlux) -> byteBufFlux.)
//        WebClient client = WebClient.create("http://localhost:8081");
//        client.get()
//                .uri("employees")
//                .retrieve()
//                .bodyToFlux(SegmentLine.class)
//                .log()
//                .map(SegmentLine::getLine)
//                .subscribe(s -> {
//                    System.out.println(s);
//                    System.out.println();
//                });
    }

}
