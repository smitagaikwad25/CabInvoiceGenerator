import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class CabInvoiceGeneratorTest {
    InvoiceSevice invoiceSevice = null;

    @Mock
    RideRepository rideRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {

        invoiceSevice = new InvoiceSevice(rideRepository);
    }

    @Test
    public void givenDistanceAndTime_shouldReturnTotalFare() {
        double dist = 2.0;
        int time = 5;
        double totalFare = invoiceSevice.getTotalFare(dist, time);
        Assert.assertEquals(25.0, totalFare, 0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinFare() {
        double dist = 0.1;
        int time = 1;
        double totalFare = invoiceSevice.getTotalFare(dist, time);
        Assert.assertEquals(5.0, totalFare, 0);
    }

    @Test
    public void givenMultipleRideData_shouldReturnInvoiceSummary() {
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary summary = invoiceSevice.calculateFare(ride1);
        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        Assert.assertEquals(exceptedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "sms@gmail.com";
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        invoiceSevice.addRides(userId, ride1);
        InvoiceSummary summary = invoiceSevice.getInvoiceSummary(userId);
        System.out.println(summary);
        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        Assert.assertEquals(exceptedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceMockito() {
        String userId = "sms@gmail.com";
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSevice invoiceSevice = new InvoiceSevice(rideRepository);
        when(rideRepository.getRides(userId)).thenReturn(ride1);

        InvoiceSummary exceptedInvoiceSummary = new InvoiceSummary(2, 30.0, 15.0);
        InvoiceSummary summary = invoiceSevice.getInvoiceSummary(userId);
        Assert.assertEquals(exceptedInvoiceSummary, summary);
    }

    @Test
    public void givenUserId_whenNotCorrect_shouldReturnExcption() {
        String userId = null;
        try {
            when(rideRepository.getRides(userId)).thenThrow(new RuntimeException("Invalid Input"));
            invoiceSevice.getInvoiceSummary(userId);
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(), "Invalid Input");
            e.printStackTrace();
        }
    }
}