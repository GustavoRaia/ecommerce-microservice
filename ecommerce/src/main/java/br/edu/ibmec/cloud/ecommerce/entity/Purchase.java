package br.edu.ibmec.cloud.ecommerce.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Container(containerName = "purchases")
public class Purchase {

    @Data
    public static class Extract {
        private String transactionId;

        private String date;

        private String description;

        private double amount;

        private double balance;
    }

    @Id
    private String purchaseId;

    @PartitionKey
    private String accountId;

    private double totalSpent;

    private LocalDate lastPurchase;

    private List<Extract> extractList;

}
