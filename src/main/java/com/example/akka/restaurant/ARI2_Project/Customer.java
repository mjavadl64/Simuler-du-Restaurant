package com.example.akka.restaurant.ARI2_Project;

// Customer.java

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Customer extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final ActorRef server;
    private final int customerId;

    public Customer(ActorRef server, int customerId) {
        this.server = server;
        this.customerId = customerId;
    }

    public static Props props(ActorRef server, int customerId) {
        return Props.create(Customer.class, () -> new Customer(server, customerId));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .matchEquals("start", m -> placeOrder())
            .match(Messages.OrderReceived.class, this::onOrderReceived)
            .build();
    }

    private void placeOrder() {
        log.info("Customer {} placing an order.", customerId);
        server.tell(new Messages.PlaceOrder("Burger", customerId), getSelf());
    }

    private void onOrderReceived(Messages.OrderReceived msg) {
        log.info("Customer {} received their order.", msg.customerId);
    }
}
