package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.jms.ConnectionFactory;

public class Producer {
    public static void main(String[] args) {
        try {
            // connexion au broker
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            //Creation d'un session
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //tue bech mayeb3ath el msg ken man3melo commit
            //AUTO_ACKNOWLEDGE kol ma yousolo msg yraja3 delevery


            Destination destination = session.createTopic("myTopic.topic");


            // il ya deux  type de file d'attendte -queue et topic
            //queue msg yousel lel nes lkol selon el ordre
            //topic yousel l tout les consomateur


            MessageProducer producer = session.createProducer(destination);


            //producer bech yconecty al session w ya3rf el destination

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);


            //DeliveryMode.NON_PERSISTENT el msg eli yet9ra mel fille d'attente yetfsakh

            //Creation d'un message
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("Hello World");
            producer.send(textMessage);
            //commit the transaction
            session.commit();
            System.out.println("Envoi du Message ...");


            session.close();
            connection.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
