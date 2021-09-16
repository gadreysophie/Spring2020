package sample.simple.store.Store;

import sample.simple.bank.Bank.IBank;
import sample.simple.client.Client.IFastLane;
import sample.simple.client.Client.IJustHaveALook;
import sample.simple.client.Client.ILane;
import sample.simple.provider.Provider.IProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Store implements IJustHaveALook, IFastLane, ILane {

    @Autowired
    IBank iBank;
    @Autowired
    IProvider iProvider;



    @Override
    public void getProductInfo(String s) {
        System.out.println(s);
        this.iBank.transferMoney();
    }

    @Override
    public void getPrice() {

    }

    @Override
    public  Boolean isAvailable() {
        return null;
    }

}
