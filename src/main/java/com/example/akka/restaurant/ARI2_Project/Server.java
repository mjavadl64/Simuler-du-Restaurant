package com.example.akka.restaurant.ARI2_Project;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Server extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private final ActorRef chef;

    public Server(ActorRef chef) {
        this.chef = chef;
    }

    public static Props props(ActorRef chef) {
        return Props.create(Server.class, () -> new Server(chef));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Messages.PlaceOrder.class, this::onPlaceOrder)
            .match(Messages.OrderReady.class, this::onOrderReady)
            .build();
    }

    private void onPlaceOrder(Messages.PlaceOrder msg) {
        log.info("Server received order from customer {}", msg.customerId);
        chef.tell(msg, getSelf());
    }

    private void onOrderReady(Messages.OrderReady msg) {
        log.info("Server serving order {}", msg.orderId);
        // Notifier le client
    }
}

