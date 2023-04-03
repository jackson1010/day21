package TFIP.day21.Repo;

public class DBQueries {
    public static final String SELECT_ALL_CUSTOMERS="select id, company, last_name, first_name from customers limit ?, ?";
    public static final String SELECT_CUSTOMER="select id,company,last_name, first_name from customers where id=?";
    public static final String SELECT_ALL_ORDERS="select c.id as customer_id, c.company, o.id as order_id,o.ship_name, o.shipping_fee from customers c, orders o where c.id = o.customer_id and customer_id =?";
}
