package javaee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;


public class JmsConsumer {

    private static final Logger logger
            = LoggerFactory.getLogger(JmsConsumer.class);

    public static void main(String... args) throws NamingException, JMSException {

        Properties props = new Properties();
        props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.put("java.naming.provider.url", "tcp://localhost:61616");
        props.put("queue.js-queue", "timetable");
        props.put("connectionFactoryNames", "queueCF");
        Context context = new InitialContext(props);

        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("queueCF");
        Queue queue = (Queue) context.lookup("js-queue");

        QueueConnection connection = connectionFactory.createQueueConnection();
        connection.start();

        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

        QueueReceiver receiver = session.createReceiver(queue);
        TextMessage message = (TextMessage) receiver.receive();

        System.out.println(message.getIntProperty("Inegerrrr"));
        System.out.println("message received: " + message.getText());

        try {
            System.out.println("Press any key to exit");
            System.in.read();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        receiver.close();
        session.close();
        connection.close();
    }

}
