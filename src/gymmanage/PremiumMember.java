package gymmanage;

public class PremiumMember extends GymMember {
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;

    // Constructor
    public PremiumMember(int id, String name, String location, String phone, String email, String gender, String dob,
            String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, dob, membershipStartDate);
        this.premiumCharge = 50000;
        this.paidAmount = 0;
        this.isFullPayment = false;
        this.discountAmount = 0;
        this.personalTrainer = personalTrainer;
    }

    // Accessor methods
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean getIsFullPayment() {
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    // Pay due amount and update payment status
    public String payDueAmount(double paidAmount) {
        if (this.isFullPayment) {
            return "Payment is already complete!";
        }

        if (this.paidAmount + paidAmount > premiumCharge) {
            return "Payment amount exceeds the premium charge!";
        }

        this.paidAmount += paidAmount;
        double remainingAmount = premiumCharge - this.paidAmount;

        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;
        }

        return "Payment successful! Remaining amount to be paid: " + remainingAmount;
    }

    // Calculate discount if full payment is made
    public String calculateDiscount() {
        if (isFullPayment) {
            this.discountAmount = premiumCharge * 0.10;
            return "Discount calculated successfully!";
        }
        return "No discount available. Complete the payment first!";
    }

    // Revert premium member to default state
    public void revertPremiumMember() {
        super.resetMember();
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    // Display member details
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount to be Paid: " + remainingAmount);
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }

    // Set loyalty points (implementation of abstract method)
    @Override
    protected void setLoyaltyPoints(double points) {
        super.setLoyaltyPoints(points);
    }
}