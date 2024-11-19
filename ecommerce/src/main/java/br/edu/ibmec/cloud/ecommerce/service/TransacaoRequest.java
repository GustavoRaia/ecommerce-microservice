package br.edu.ibmec.cloud.ecommerce.service;

import lombok.Data;

@Data
public class TransacaoRequest {
    private String accountId;
    private String numeroCartao;
    private String comerciante;
    private Double valor;

}
