package br.edu.ibmec.cloud.ecommerce.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.edu.ibmec.cloud.ecommerce.entity.Order;
import br.edu.ibmec.cloud.ecommerce.entity.Product;
import br.edu.ibmec.cloud.ecommerce.repository.OrderRepository;

@Service
public class CheckoutService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "https://jhanus-gzhqcpheekdahpc6.eastus-01.azurewebsites.net/";
    private final String merchant = "BOT-COMMERCE";

    @Autowired
    private OrderRepository orderRepository;

    public Order checkout(Product product, String accountId, String numeroCartao) throws Exception {
        try {
            TransacaoResponse response = this.autorizar(product, numeroCartao);

            if (response == null || response.equals(""))  {
                throw new Exception("Não consegui realizar a compra");
            }

            Order order = new Order();
            order.setOrderId(UUID.randomUUID().toString());
            order.setDataOrder(LocalDateTime.now());
            order.setProductId(product.getProductId());
            order.setProductName(product.getProductName());
            order.setAccountId(accountId);
            order.setStatus("Produto Comprado");
            this.orderRepository.save(order);
            return order;
        }
        catch (Exception e) {
            //Gera um erro
            throw new Exception("Não consegui realizar a compra");
        }
    }

    private TransacaoResponse autorizar(Product product, String numeroCartao) {
        String url = baseUrl + "transacao/autorizar";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("numero", numeroCartao);
        body.add("valor", String.valueOf(product.getPrice()));
        body.add("comerciante", merchant);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<TransacaoResponse> response = restTemplate.postForEntity(url, request, TransacaoResponse.class);

        return response.getBody();
    }

}