package ques10;

/*
10. Design classes having attributes and method(only skeleton) for a coffee shop. There are three different actors in our scenario and i have listed the different actions they do also below

* Customer
  - Pays the cash to the cashier and places his order, get a token number back
  - Waits for the intimation that order for his token is ready
  - Upon intimation/notification he collects the coffee and enjoys his drink
  ( Assumption:  Customer waits till the coffee is done, he wont timeout and cancel the order. Customer always likes the drink served. Exceptions like he not liking his coffee, he getting wrong coffee are not considered to keep the design simple.)

* Cashier
  - Takes an order and payment from the customer
  - Upon payment, creates an order and places it into the order queue
  - Intimates the customer that he has to wait for his token and gives him his token
  ( Assumption: Token returned to the customer is the order id. Order queue is unlimited. With a simple modification, we can design for a limited queue size)

* Barista
 - Gets the next order from the queue
 - Prepares the coffee
 - Places the coffee in the completed order queue
 - Places a notification that order for token is ready
 */

public class ques10 {
}
    class Customer{
        String name;
        long contact;
        public void wait_for_coffee(){}
        public void collects_coffee(){}

    }

    class Cashier{
        String name;
        String Empid;
        public int take_order(double cash)
        {
            return 0;
        }
        public void placeOrderToQueue(Order o){}
    }

    class Order{
        int order_id;
        String coffee_type;
    }

    class Barista{
        public void Prepare_coffee(Order o) {}
        public void addCompleteOrder(Order o) {}
        public void notifyCompleteOrder(Order o) {}
    }

    class PendingOrderQueue
    {
        public void addOrder(Order o){}
        public void getNextOrder(Order o){}
    }

    class CompleteOrders{
        public void getcompleteOrders(){}
    }
