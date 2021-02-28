package download;

import com.google.gson.stream.JsonReader;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.netty.http.client.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RestController
@SpringBootApplication
public class DownloadApplication {

    private static void gsonConsume() {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Request...");
        restTemplate.execute("http://localhost:8082/gson", HttpMethod.GET, (ClientHttpRequest requestCallback) -> {},
                responseExtractor -> {
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseExtractor.getBody()));
//                    String line;
//
//                    while((line = readLine(bufferedReader))!= null){
//                        System.out.println(line);
//                    }
                    JsonReader reader = new JsonReader(new InputStreamReader(responseExtractor.getBody(), StandardCharsets.UTF_8));
                    reader.beginArray();
                    while (reader.hasNext()) {
                        reader.beginArray();
                        while(reader.hasNext()){
                            String s = reader.nextString();
                            System.out.println(s);
                        }
                        reader.endArray();
                    }
                    reader.endArray();
                    reader.close();
                    return null;
                });
    }

    public static void main(String[] args) {
        SpringApplication.run(DownloadApplication.class, args);
        gsonConsume();
//        HttpClient.create() // THIS
//                .baseUrl("http://localhost:8081/employees")
//                .get()
//                .responseContent()
//                .asString()
//                .doOnError(t -> {
//                    System.out.println("on error!!!!");
//                    System.out.println(t.toString());
//                })
//                .subscribe(line -> {
//                    System.out.println(line);
//                });

//        HttpClient.create()
//                .baseUrl("http://localhost:8081/employees")
//                .get()
//                .responseContent()
//                .asInputStream()
//                .subscribe(inputStream -> {
//                    String line;
//                    InputStreamReader bufferedInputStream = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(bufferedInputStream);
//
//                    while ((line = readLine(bufferedReader)) != null){
//                        System.out.println(line);
//                    }
//                });
    }

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
//}
    @SneakyThrows
    public static String readLine(BufferedReader br) {
        return br.readLine();
    }
}
