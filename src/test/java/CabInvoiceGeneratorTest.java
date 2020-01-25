import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceGeneratorTest {
    CabInvoiceGenerator cabInvoiceGenerator=null;
    @Before
    public void setUp(){
        cabInvoiceGenerator = new CabInvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double dist = 2.0;
        int time = 5;
        double totalFare = cabInvoiceGenerator.getTotalFare(dist, time);
        Assert.assertEquals(25.0 , totalFare,0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinFare() {
        double dist = 0.1;
        int time = 1;
        double totalFare = cabInvoiceGenerator.getTotalFare(dist, time);
        Assert.assertEquals(5.0 , totalFare,0);
    }

    @Test
    public void givenMultipleRideData_shouldReturnInvoiceSummary() {
        Ride[] ride1 = {new Ride(2.0, 5),
                        new Ride(0.1, 1)
        };
        InvoiceSummary summary = cabInvoiceGenerator.calculateFare(ride1);
        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        Assert.assertEquals(exceptedInvoiceSummary,summary);
    }
}