package com.example.akka.restaurant.ARI2_Project;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@SpringBootApplication
public class Ari2ProjectApplication {

	public class Main {
		public static void main(String[] args) {
			final ActorSystem system = ActorSystem.create("restaurant-system");
	
			// Création des acteurs
			ActorRef chef = system.actorOf(Chef.props(), "chef");
			ActorRef server = system.actorOf(Server.props(chef), "server");
	
			// Simuler des clients
			for (int i = 1; i <= 2; i++) {
				ActorRef customer = system.actorOf(Customer.props(server, i), "customer-" + i);
				customer.tell("start", ActorRef.noSender());
			}
		}
	}
}
