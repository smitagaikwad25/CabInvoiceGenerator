public class CabInvoiceGenerator {
    
    private static final int COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;

    public double getTotalFare(double dist, int time) {
        return (COST_PER_KILOMETER * dist) + (COST_PER_MINUTE * time);
    }
}
