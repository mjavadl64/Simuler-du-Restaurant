package com.example.akka.restaurant.ARI2_Project;

public interface Messages {

    //un client passe une commande
    class PlaceOrder {
        public String dish;
        public int customerId;

        public PlaceOrder(String dish, int customerId) {
            this.dish = dish;
            this.customerId = customerId;
        }
    }

    //
    class OrderReceived {
        public int customerId;

        public OrderReceived(int customerId) {
            this.customerId = customerId;
        }
    }

    class PrepareOrder {
        public String dish;
        public int orderId;

        public PrepareOrder(String dish, int orderId) {
            this.dish = dish;
            this.orderId = orderId;
        }
    }

    class OrderReady {
        public int orderId;

        public OrderReady(int orderId) {
            this.orderId = orderId;
        }
    }

    class ServeOrder {
        public int customerId;

        public ServeOrder(int customerId) {
            this.customerId = customerId;
        }
    }

}
