package com.university.books.store.controller;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.math.BigDecimal;

/**
 * Created by Aleksandr on 5/30/2017.
 */
@RestController
public class PaymentController {

    @Autowired
    BraintreeGateway gateway;

    @PostMapping("/clientToken")
    public String getClientToken(Model model){
        String id=gateway.clientToken().generate();
        System.out.println(id);
        return id;
    }

    @PostMapping("/checkout")
    public Object handleCheckout(@RequestBody String method){
        System.out.println(method);
        String nonceFromTheClient=method.substring(0,method.length()-1);
        System.out.println(nonceFromTheClient);
        TransactionRequest request = new TransactionRequest()
                .amount(new BigDecimal("1000.00"))
                .paymentMethodNonce(nonceFromTheClient)
                .options()
                .submitForSettlement(true)
                .done();
        /*
        Result<Transaction> result = gateway.transaction().sale(request);

        System.out.println(result.getTransaction());
        System.out.println(result.isSuccess());
        System.out.println(result.getTarget());
*/
        return method;
    }
}
