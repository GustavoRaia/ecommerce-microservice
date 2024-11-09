package br.edu.ibmec.cloud.ecommerce.service;

import br.edu.ibmec.cloud.ecommerce.entity.Purchase;
import br.edu.ibmec.cloud.ecommerce.repository.PurchaseRepository;
import com.azure.cosmos.models.PartitionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase findById(String accountId) {
        return this.purchaseRepository.findByAccountId(accountId);
    }

    public void save(Purchase purchase) {
        purchase.setPurchaseId(UUID.randomUUID().toString());
        this.purchaseRepository.save(purchase);
    }

    public void delete(String purchaseId) throws Exception {

        Optional<Purchase> optPurchase = this.purchaseRepository.findById(purchaseId);

        if (optPurchase.isPresent() == false)
            throw new Exception("Não encontrei o produto a ser excluido");

        this.purchaseRepository.deleteById(purchaseId, new PartitionKey(optPurchase.get().getAccountId()));
    }

}