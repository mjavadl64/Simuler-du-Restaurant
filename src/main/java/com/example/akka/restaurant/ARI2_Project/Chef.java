package com.example.akka.restaurant.ARI2_Project;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Chef extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(Chef.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Messages.PlaceOrder.class, this::onPlaceOrder)
            .match(Messages.OrderReady.class, this::onOrderReady)
            .build();
    }

    private void onPlaceOrder(Messages.PlaceOrder msg) {
        log.info("Chef received order for {} from customer {}", msg.dish, msg.customerId);
        // Simuler l'affectation de la commande à un cuisinier
        ActorRef cook = getContext().actorOf(Cook.props(), "Cook-" + msg.customerId);
        cook.tell(new Messages.PrepareOrder(msg.dish, msg.customerId), getSelf());
    }

    private void onOrderReady(Messages.OrderReady msg) {
        log.info("Order {} is ready", msg.orderId);
        // Notifier le serveur pour qu'il serve le client
    }
}

