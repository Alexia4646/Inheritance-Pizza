public class Pizza {
   String[] toppings;
    double price;
    String size;
//Constructor for Pizza Class
    public Pizza(String[] toppings, int numToppings, String size) {
        this.toppings = new String[numToppings];
        for (int i = 0; i < numToppings; i++) {
            this.toppings[i] = toppings[i];
        }
        //I am setting the pizza size and calculating based on toppings
        this.size = size;
        this.price = 14 + (2 * numToppings);
    }

    public String toString() {
        StringBuilder description = new StringBuilder("Pizza with ");
        for (int i = 0; i < toppings.length; i++) {
            description.append(toppings[i]);
            if (i < toppings.length - 1) {
                description.append(", ");
            }
        }
        return "Pizza Toppings:" + String.join(",",toppings) + "\nSize:" + size + "\nPrice: $" + price;
    }
}
