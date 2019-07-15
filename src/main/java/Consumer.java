import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare("hello-world", false, false, false, null);

            channel.basicConsume("hello-world", true, (consumerTag, delivery) -> {
                String m = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("i just received a message = " + m);
            }, consumerTag -> {});


    }
}
