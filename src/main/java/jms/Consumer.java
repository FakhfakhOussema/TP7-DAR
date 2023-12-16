package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer
{
    public static void main(String[] args)
    {
        try
        {
            //creation au broker
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = ConnectionFactory.createConnenction;
            connection.start();

            //Creation d'un session
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("myTopic.topic");

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage)
                    {
                        TextMessage textMessage = (TextMessage) message;
                        try
                        {
                            System.out.println("Message recu : " +textMessage.getText());
                        }
                        catch (Exception e)
                        {
                           e.printStackTrace();
                        }
                    }
                }
            });

        }
        catch(Exception e )
        {
            e.printStackTrace();
        }
    }
}
