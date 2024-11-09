package br.edu.ibmec.cloud.ecommerce.controller;

import br.edu.ibmec.cloud.ecommerce.entity.Purchase;
import br.edu.ibmec.cloud.ecommerce.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @PostMapping
    public ResponseEntity<Purchase> create(@Valid @RequestBody Purchase purchase) {
        this.service.save(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<Purchase>> getPurchaseById(@RequestParam String accountId) {
//        List<Purchase> purchases = this.service.findById(accountId);
//        return new ResponseEntity<>(purchases, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<Purchase> getPurchaseById(@RequestParam String accountId) {
        Purchase purchase = this.service.findById(accountId);
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") String id) throws Exception {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
