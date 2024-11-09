package br.edu.ibmec.cloud.ecommerce.controller;

import br.edu.ibmec.cloud.ecommerce.entity.Order;
import br.edu.ibmec.cloud.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody Order order) {
        this.orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Order> getOrderById(@RequestParam String orderId) {
        Order order = this.orderService.findByOrderId(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("orderId") String orderId) throws Exception {
        this.orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
