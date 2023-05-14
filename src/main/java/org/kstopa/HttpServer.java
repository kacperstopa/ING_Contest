package org.kstopa;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.kstopa.atm.AtmRequest;
import org.kstopa.atm.AtmResponse;
import org.kstopa.atm.AtmService;
import org.kstopa.onlinegame.CalculateRequest;
import org.kstopa.onlinegame.Clan;
import org.kstopa.onlinegame.OnlinegameService;
import org.kstopa.transactions.Account;
import org.kstopa.transactions.Transaction;
import org.kstopa.transactions.TransactionsService;

import java.util.Arrays;
import java.util.List;

public class HttpServer {

    private final TransactionsService transactionsService = new TransactionsService();
    private final AtmService atmService = new AtmService();
    private final OnlinegameService onlinegameService = new OnlinegameService();

    Javalin server() {
        return Javalin.create(javalinConfig -> {
                    javalinConfig.http.maxRequestSize = 1073741824; // 1GB
                })
                .post("/atms/calculateOrder", this::handleAtmService)
                .post("/onlinegame/calculate", this::handleOnlineGame)
                .post("/transactions/report", this::handleTransactionsReport);
    }

    private Context handleAtmService(Context context) {
        final List<AtmRequest> atmRequestList = Arrays.asList(context.bodyValidator(AtmRequest[].class)
                .check(atmRequests -> Arrays.stream(atmRequests).allMatch(AtmRequest::isValid), "Atm request not valid")
                .get());
        final List<AtmResponse> result = atmService.calculate(atmRequestList);
        return context.json(result);
    }

    private Context handleOnlineGame(Context context) {
        final CalculateRequest calculateRequest = context.bodyValidator(CalculateRequest.class)
                .check(CalculateRequest::isValid, "Onlinegame request not valid")
                .get();
        final List<List<Clan>> result = onlinegameService.calculate(calculateRequest.groupCount(), calculateRequest.clans());
        return context.json(result);
    }

    private Context handleTransactionsReport(Context context) {
        final List<Transaction> transactionsRequestList = Arrays.asList(context.bodyValidator(Transaction[].class)
                .check(transactions -> Arrays.stream(transactions).allMatch(Transaction::isValid), "Transactions not valid")
                .get());
        final List<Account> result = transactionsService.calculate(transactionsRequestList);
        return context.json(result);
    }
}
