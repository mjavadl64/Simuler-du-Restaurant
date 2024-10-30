package com.example.akka.restaurant.ARI2_Project;

// Cook.java
import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Cook extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    public static Props props() {
        return Props.create(Cook.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Messages.PrepareOrder.class, this::onPrepareOrder)
            .build();
    }

    private void onPrepareOrder(Messages.PrepareOrder msg) {
        log.info("Cook preparing dish: {} for order {}", msg.dish, msg.orderId);
        // Simuler la préparation
        try {
            Thread.sleep(1000); // Simuler le temps de préparation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        getSender().tell(new Messages.OrderReady(msg.orderId), getSelf());
    }
}

