package br.edu.ibmec.cloud.ecommerce.request;

import lombok.Data;

@Data
public class CheckoutRequest {
    private String accountId;
    private String productId;
    private String numeroCartao;
}