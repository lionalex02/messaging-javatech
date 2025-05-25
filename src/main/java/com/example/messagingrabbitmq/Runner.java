package com.example.messagingrabbitmq;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	private final RabbitTemplate rabbitTemplate;
	private final FanoutExchange exchange;



	public Runner(FanoutExchange exchange, RabbitTemplate rabbitTemplate) {
		this.exchange = exchange;
		this.rabbitTemplate = rabbitTemplate;

	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the message to send: ");
		String message;
		do {
			message = scanner.nextLine();
			if (!message.isEmpty()) {
				rabbitTemplate.convertAndSend(exchange.getName(), "", message);
			}
		}while (!message.equals("exit"));
		scanner.close();
	}


}
