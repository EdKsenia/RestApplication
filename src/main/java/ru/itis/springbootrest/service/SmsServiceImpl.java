package ru.itis.springbootrest.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.springbootrest.models.SmsRequest;
import ru.itis.springbootrest.models.SmsResponse;

@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public String sendMessage(String phone) {
        String message = "Your letter received, expect a response from our experts" ;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("ksushka.e@mail.ru", "itOvDD2h60tnXcPZWJTsPjat0Ud7");

        HttpEntity<SmsRequest> request = new HttpEntity<>(new SmsRequest(phone, message,"SMS Aero", "DIRECT"), httpHeaders);
        String resourceUrl = "https://gate.smsaero.ru/v2/sms/send";
        ResponseEntity<SmsResponse> response = restTemplate.postForEntity(resourceUrl, request, SmsResponse.class);

        return "" + phone;
    }
}
