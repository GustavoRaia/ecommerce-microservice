package br.edu.ibmec.cloud.ecommerce.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Container(containerName = "purchases")
public class Purchase {

    @Id
    private String purchaseId;

    @PartitionKey
    private String accountId;

    private double totalSpent;

    private LocalDate lastPurchase;

}
