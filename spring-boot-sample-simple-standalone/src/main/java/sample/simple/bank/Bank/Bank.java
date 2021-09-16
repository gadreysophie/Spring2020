package sample.simple.bank.Bank;

import org.springframework.stereotype.Component;

@Component
public class Bank implements IBank {

    @Override
    public void getMoney() {
    }

    @Override
    public void transferMoney() {
        System.out.print("Argent Transferé");
    }
}
