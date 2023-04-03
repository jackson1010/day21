package TFIP.day21.Repo;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

//to import all from DBQ
import static TFIP.day21.Repo.DBQueries.*;

import TFIP.day21.Model.Customer;
import TFIP.day21.Model.Order;

@Repository
public class Repo {
    
    @Autowired
    private JdbcTemplate template;

    public List<Customer> getAllCustomers(Integer limit, Integer offset){

        final List<Customer> allCustomers = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SELECT_ALL_CUSTOMERS, offset, limit);
        
        while(rs.next()){
            allCustomers.add(Customer.create(rs));
        }
        return (Collections.unmodifiableList(allCustomers));
    }

    public Optional<Customer> findById(Integer customerId) {
        final SqlRowSet rs= template.queryForRowSet(SELECT_CUSTOMER, customerId);
       if(rs.first()){
        return Optional.of(Customer.create(rs));
       }
        return Optional.empty();  
    }

    public List<Order> findAllOrders(Integer customerId) {
        final SqlRowSet rs =template.queryForRowSet(SELECT_ALL_ORDERS, customerId);
        final List<Order> orders = new LinkedList<>();
        while(rs.next()){
            orders.add(Order.create(rs));
        }

        return (Collections.unmodifiableList(orders));
    }
}
