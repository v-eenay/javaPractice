package gymmanage;

public class RegularMember extends GymMember {
    private int attendance;
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    // Constructor
    public RegularMember(int id, String name, String location, String phone, String email, 
            String gender, String dob, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, dob, membershipStartDate);
        this.attendanceLimit = 30;
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = "basic";
        this.price = 6500.0;
    }

    // Accessor methods
    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    public boolean isEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }

    public int getAttendance() {
        return attendance;
    }

    // Set attendance and check eligibility for upgrade
    public void setAttendance(int attendance) {
        this.attendance = attendance;
        if (this.attendance >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
        }
    }

    // Mark attendance and update loyalty points
    @Override
    public void markAttendance() {
        setAttendance(getAttendance() + 1);
        setLoyaltyPoints(getLoyaltyPoints() + 5);
    }

    // Get plan price based on the plan type
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic":
                return 6500;
            case "standard":
                return 12500;
            case "deluxe":
                return 18500;
            default:
                return -1;
        }
    }

    // Upgrade plan if eligible
    public String upgradePlan(String newPlan) {
        if (!isEligibleForUpgrade) {
            return "Not eligible for upgrade";
        }

        if (newPlan.equalsIgnoreCase(this.plan)) {
            return "Already subscribed to this plan";
        }

        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) {
            return "Invalid plan selected";
        }

        this.plan = newPlan.toLowerCase();
        this.price = newPrice;
        return "Plan upgraded successfully";
    }

    // Revert regular member to default state
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    // Display member details
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }

    // Set loyalty points (implementation of abstract method)
    @Override
    protected void setLoyaltyPoints(double points) {
        super.setLoyaltyPoints(points);
    }
}