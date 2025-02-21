package dev.alexguesser.payment.infra.gateway;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PJBankPaymentGateway implements PaymentGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Output process(Input input) {
        System.out.println("processing pjbank");

        Map<String, String> creditCard = getCreditCard(input);

        String url1 = "https://sandbox.pjbank.com.br/recebimentos/e0727263cc7a983f0aae5411ad86c5a144b8ed28/tokens";
        JsonNode output1 = restTemplate.postForObject(url1, creditCard, JsonNode.class);

        Map<String, Object> transaction = getTransaction(output1);

        String url2 = "https://sandbox.pjbank.com.br/recebimentos/e0727263cc7a983f0aae5411ad86c5a144b8ed28/transacoes";
        JsonNode output2 = restTemplate.postForObject(url2, transaction, JsonNode.class);

        String status = "rejected";
        if (output2.get("autorizada").asText().equals("1")) {
            status = "approved";
        }

        return new Output(output2.get("tid").asText(), output2.get("autorizacao").asText(), status);
    }

    private Map<String, Object> getTransaction(JsonNode output1) {
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("pedido_numero", "1");
        transaction.put("token_cartao", output1.get("token_cartao").asText());
        transaction.put("valor", 100000);
        transaction.put("parcelas", 1);
        transaction.put("descricao_pagamento", "");
        return transaction;
    }

    private Map<String, String> getCreditCard(Input input) {
        String[] expDateParts = input.expDate().split("/");
        String mes = expDateParts[0];
        String ano = expDateParts[1];

        Map<String, String> creditCard = new HashMap<>();
        creditCard.put("nome_cartao", input.cardHolder());
        creditCard.put("numero_cartao", input.creditCardNumber());
        creditCard.put("mes_vencimento", mes);
        creditCard.put("ano_vencimento", ano);
        creditCard.put("cpf_cartao", "64111456529");
        creditCard.put("codigo_cvv", input.cvv());
        creditCard.put("email_cartao", "api@pjbank.com.br");
        return creditCard;
    }

}