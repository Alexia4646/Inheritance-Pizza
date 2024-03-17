class DeliveryPizza extends Pizza {
    private double delivery_Fee;
    private String delivery_Address;

    //constructor for DeliveryPizza class
    public DeliveryPizza(String[] toppings, String delivery_Address, int numToppings, String size) {
        super(toppings, numToppings, size);
        //I am initializing the delivery address
        this.delivery_Address = delivery_Address;
        //I am calculating the delivery fee based on the total price
        if (this.price > 18) {
            this.delivery_Fee = 3;
        } else {
            this.delivery_Fee = 5;
        }
    }

    public String toString() {
        return super.toString() + ". Delivery fee: $" + delivery_Fee + ". Delivery address: " + delivery_Address;
    }
}

