package com.primavera.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;

	@Value("${billing.kafka.output.topic}")
	private String topicName;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Enter your Kafka Message Key: ");
			String key = scanner.next();
			System.out.print("Enter your Kafka Message Value: ");
			String value = scanner.next();
			System.out.println("producing message for topic:" + topicName);
			kafkaTemplate.send(topicName,key, value);
		}

	}

}
