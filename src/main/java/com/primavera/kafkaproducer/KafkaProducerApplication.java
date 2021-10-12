package com.primavera.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import com.primavera.kafkaadapter.KafkaProducerConfig;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.primavera.kafkaadapter")
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaProducerConfig kafkaProducerConfig;

	@Value("${billing.kafka.output.topic}")
	private String topicName;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter your Kafka Message ");
			String msg = scanner.next();
			System.out.println("producing message: " + msg + "for topic:" +topicName);
			kafkaProducerConfig.kafkaTemplate().send(topicName, msg);
		}

	}

}
