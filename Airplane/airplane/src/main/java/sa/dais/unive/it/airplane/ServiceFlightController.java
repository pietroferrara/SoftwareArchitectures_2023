package sa.dais.unive.it.airplane;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class ServiceFlightController {
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirportRepository airportRepository;


    @GetMapping("rest/depart/{airplane}/{airport}")
    public String depart(@PathVariable String airplane, @PathVariable String airport) throws Exception {
        System.out.println("Departed!");

        ConnectionFactory f = new ConnectionFactory();
        f.setHost("localhost");
        try(Connection conn=f.newConnection();
            Channel ch = conn.createChannel()) {
            String QUEUE_NAME = "departure";
            ch.queueDeclare(QUEUE_NAME,false,false,false,null);
            String message = airplane+"\n"+airport;
            ch.basicPublish("", QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Message sent\n"+message);
        }
        return "message sent";
    }

    @GetMapping("rest/process/depart")
    public String processDepart() throws Exception {
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("localhost");
        Connection conn=f.newConnection();
        Channel ch = conn.createChannel();
        String QUEUE_NAME = "departure";
        ch.queueDeclare(QUEUE_NAME,false,false,false,null);

        DeliverCallback callback = (tag, del) -> {
            String message = new String(del.getBody(), StandardCharsets.UTF_8);
            String[] split = message.split("\n");
            try {
                new FlightRunner(
                        airplaneRepository.findById(split[0]).get(),
                        airportRepository.findById(split[1]).get(), airplaneRepository).start();
            }
            catch(Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Message received:\n"+message);
        };

        ch.basicConsume(QUEUE_NAME,true,callback, tag->{});
        return "departed";
    }
}
