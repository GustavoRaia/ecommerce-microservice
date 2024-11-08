package br.edu.ibmec.cloud.ecommerce.repository;

import br.edu.ibmec.cloud.ecommerce.entity.Purchase;
import com.azure.spring.data.cosmos.repository.CosmosRepository;

import java.util.List;

public interface PurchaseRepository extends CosmosRepository<Purchase, String> {

//    List<Purchase> findByPurchaseId(String purchaseId);
    Purchase findByAccountId(String accountId);
}
