package org.kstopa.transactions;

import java.math.BigDecimal;
import java.util.*;

public class TransactionsService {

    public List<Account> calculate(List<Transaction> transactions) {
        final Map<String, Account> accounts = new HashMap<>();
        transactions.forEach(transaction -> applyTransaction(accounts, transaction));
        return accounts.values().stream().sorted(Comparator.comparing(account -> new BigDecimal(account.account()))).toList();
    }

    private void applyTransaction(Map<String, Account> accounts, Transaction transaction) {
        final Account creditedAccount = accounts.getOrDefault(transaction.creditAccount(), Account.empty(transaction.creditAccount()));
        final Account debitedAccount = accounts.getOrDefault(transaction.debitAccount(), Account.empty(transaction.debitAccount()));
        accounts.put(transaction.creditAccount(), creditedAccount.credit(transaction.amount()));
        accounts.put(transaction.debitAccount(), debitedAccount.debit(transaction.amount()));
    }

}
