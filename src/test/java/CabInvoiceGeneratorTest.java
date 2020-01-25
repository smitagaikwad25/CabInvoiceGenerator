import org.junit.Assert;
import org.junit.Test;

public class CabInvoiceGeneratorTest {
    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
        double dist = 2.0;
        int time = 5;
        double totalFare = invoiceGenerator.getTotalFare(dist, time);
        Assert.assertEquals(25.0 , totalFare,0);
    }
}
