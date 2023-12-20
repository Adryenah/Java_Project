package Testing;

//import org.testng.annotations.Test;

import org.junit.Test;

public class ServiceTesting {
    public ServiceTesting(){}

    @Test
    public void runTestsService() throws Exception{
        System.out.println("Starting tests for service............");
        this.runTestsCakeService();
        this.runTestsOrderService();
        System.out.println("Exiting runTestsService successfully.....");
    }

    @Test
    public void runTestsCakeService() throws Exception{}

    @Test
    public void runTestsOrderService() throws Exception{}

}
