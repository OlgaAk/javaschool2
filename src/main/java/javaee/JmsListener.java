package javaee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.*;

import org.jboss.ejb3.annotation.ResourceAdapter;

@MessageDriven(name = "JmsListener", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "timetable"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
@ResourceAdapter("activemq-ra2.rar")
public class JmsListener implements MessageListener {

    private static final Logger logger
            = LoggerFactory.getLogger(JmsListener.class);

    public JmsListener() {
    }

    @Resource(mappedName = "java:/activemq/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    private Connection connection;

    public void init() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
    }

    public void destroy() throws JMSException {
        connection.close();
    }

    @Override
    public void onMessage(Message message) {
        try {
            init();
            String text = ((TextMessage) message).getText();
            logger.info("message received: " + text);
        } catch (JMSException e) {
            logger.error(e.getMessage());
            throw new EJBException("Error in JMS operation", e);
        } finally {
            try {
                destroy();
            } catch (JMSException e) {
                logger.error(e.getMessage());
                throw new EJBException("Error in closing connection", e);
            }
        }
    }

}

