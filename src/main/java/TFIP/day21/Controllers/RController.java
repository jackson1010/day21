package TFIP.day21.Controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import TFIP.day21.Model.Customer;
import TFIP.day21.Model.Order;
import TFIP.day21.Repo.Repo;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path = "api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class RController {

    @Autowired
    private Repo repo;

    @GetMapping
    public ResponseEntity<String> getList(@RequestParam(required = false) String limit,
            @RequestParam(required = false) String offset) throws JsonProcessingException {

        if (Objects.isNull(limit)) {
            limit = "5";
        }

        if (Objects.isNull(offset)) {
            offset = "0";
        }
        List<Customer> cs = repo.getAllCustomers(Integer.parseInt(limit), Integer.parseInt(offset));
        ObjectMapper obM = new ObjectMapper();
        String json = obM.writeValueAsString(cs);
        JsonArrayBuilder ab = Json.createArrayBuilder();

        for (Customer c : cs) {
            ab.add(c.toJson());
        }
        JsonArray result = ab.build();

        // return
        // ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(json);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());

    }

    @GetMapping("{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId) throws JsonProcessingException {
        Optional<Customer> opt = repo.findById(customerId);
        // ObjectMapper obM = new ObjectMapper();
        // String json = obM.writeValueAsString(opt.get());
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        try {
            objectBuilder.add("customer", opt.get().toJson());
            JsonObject result = objectBuilder.build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"Cannot find customer Id: " + customerId + "\"}");
        }
        objectBuilder.add("customer", opt.get().toJson());
        JsonObject result = objectBuilder.build();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }

    @GetMapping("{customerId}/orders")
    public ResponseEntity<String> getAllOrders(@PathVariable Integer customerId) throws JsonProcessingException {
        List<Order> ord = repo.findAllOrders(customerId);
        JsonArray result = null;
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // ObjectMapper obM = new ObjectMapper();
        // String json = obM.writeValueAsString(ord);

        for (Order o : ord) {
            arrayBuilder.add(o.toJson());
        }
        result = arrayBuilder.build();
        if (ord.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"Cannot find " + customerId + "\"}");
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result.toString());
    }

}
