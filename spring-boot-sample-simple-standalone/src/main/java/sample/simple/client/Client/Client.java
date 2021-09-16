package sample.simple.client.Client;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
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
        iJustHaveALook.getProductInfo("Product 1");


    }

    @AfterReturning("execution(* sample..*Service.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("Completed: " + joinPoint);
    }
}
