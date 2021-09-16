package sample.simple.client.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Client implements IRun {

    @Autowired
    IFastLane iFastLane;
    @Autowired
    ILane iLane;
    @Autowired
    IJustHaveALook iJustHaveALook;

    @Override
    public void run() {

        System.out.println("c'est parti");

    }
}
