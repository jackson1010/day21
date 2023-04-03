package TFIP.day21.Model;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Order {
    private int id;
    private String ShipName;
    private Customer customer;
    private double ShippingCost;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getShipName() {
        return ShipName;
    }
    public void setShipName(String shipName) {
        ShipName = shipName;
    }
    public double getShippingCost() {
        return ShippingCost;
    }
    public void setShippingCost(double shippingCost) {
        ShippingCost = shippingCost;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    


    public static Order create(SqlRowSet rs) {
        Order odr = new Order();
        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        odr.setCustomer(customer);
        odr.setId(rs.getInt("order_id"));
        odr.setShipName(rs.getString("ship_name"));
        odr.setShippingCost(rs.getDouble("shipping_fee"));

        return odr;
    }

    public JsonValue toJson(){
        return Json.createObjectBuilder()
        .add("customer_id",getCustomer().getId())
        .add("order_id", getId())
        .add("ship_name", getShipName())
        .add("shipping_fee", getShippingCost())
        .build();
    }
 

}
