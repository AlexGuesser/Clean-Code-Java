package dev.alexguesser.payment.infra.gateway;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CieloPaymentGateway implements PaymentGateway {

    @Autowired
    private RestClient restClient;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CieloPaymentGateway.class);

    @Override
    public Output process(Input input) {
        LOGGER.info("processing cielo");
        Map<String, Object> transaction = createSampleTransaction();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("MerchantId", "10631719-a8b9-44fa-8053-1d856ca3cac7");
        headers.add("MerchantKey", "TWFYUFSEXRRPQGCUQLHFHGEXWEDNOPQTXGUFDSQH");

        String url = "https://apisandbox.cieloecommerce.cielo.com.br/1/sales/";
        String response = restClient.post()
                .uri(url)
                .headers(h -> h.addAll(headers))
                .body(transaction)
                .retrieve()
                .body(String.class);
        JsonNode output = null;
        try {
            output = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            LOGGER.error("error parsing response", e);
            throw new RuntimeException(e);
        }

        String status = "rejected";
        if ("4".equals(output.get("Payment").get("ReturnCode").toString())) {
            status = "approved";
        }

        return new Output(
                output.get("Payment").get("Tid").toString(),
                output.get("Payment").get("AuthorizationCode").toString(),
                status
        );
    }

    private static Map<String, Object> createSampleTransaction() {
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("MerchantOrderId", "2014111701");
        Map<String, Object> customer = new HashMap<>();
        customer.put("Name", "Comprador Teste");
        customer.put("Identity", "11225468954");
        customer.put("IdentityType", "CPF");
        customer.put("Email", "compradorteste@teste.com");
        customer.put("Birthdate", "1991-01-02");
        Map<String, Object> address = new HashMap<>();
        address.put("Street", "Rua Teste");
        address.put("Number", "123");
        address.put("Complement", "AP 123");
        address.put("ZipCode", "12345987");
        address.put("City", "Rio de Janeiro");
        address.put("State", "RJ");
        address.put("Country", "BRA");
        customer.put("Address", address);
        customer.put("DeliveryAddress", address);
        transaction.put("Customer", customer);
        Map<String, Object> payment = createSamplePayment();
        transaction.put("Payment", payment);
        return transaction;
    }

    private static Map<String, Object> createSamplePayment() {
        Map<String, Object> payment = new HashMap<>();
        payment.put("Type", "CreditCard");
        payment.put("Amount", 15700);
        payment.put("Currency", "BRL");
        payment.put("Country", "BRA");
        payment.put("Provider", "Simulado");
        payment.put("ServiceTaxAmount", 0);
        payment.put("Installments", 1);
        payment.put("Interest", "ByMerchant");
        payment.put("Capture", false);
        payment.put("Authenticate", false);
        payment.put("Recurrent", false);
        payment.put("SoftDescriptor", "123456789ABCD");
        Map<String, Object> creditCard = new HashMap<>();
        creditCard.put("CardNumber", "4024007197692931");
        creditCard.put("Holder", "Teste Holder");
        creditCard.put("ExpirationDate", "12/2021");
        creditCard.put("SecurityCode", "123");
        creditCard.put("SaveCard", "false");
        creditCard.put("Brand", "Visa");
        payment.put("CreditCard", creditCard);
        return payment;
    }
}