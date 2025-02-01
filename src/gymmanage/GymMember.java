package gymmanage;

public abstract class GymMember {
    // Attributes
    private int id;
    private String name;
    private String location;
    private String phone;
    private String email;
    private String gender;
    private String dob;
    private String membershipStartDate;
    private int attendance;
    private double loyaltyPoints;
    private boolean activeStatus;

    // Constructor
    public GymMember(int id, String name, String location, String phone, String email, String gender, String dob,
            String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0;
        this.loyaltyPoints = 0;
        this.activeStatus = false;
    }

    // Accessor methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public int getAttendance() {
        return attendance;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    // Abstract method for attendance tracking
    public abstract void markAttendance();

    // Membership activation and deactivation methods
    public void activateMembership() {
        this.activeStatus = true;
    }

    public void deactivateMembership() {
        if (this.activeStatus) {
            this.activeStatus = false;
        }
    }

    // Reset member method
    public void resetMember() {
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }

    // Display method
    public void display() {
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + activeStatus);
    }

    // Set loyalty points method
    protected void setLoyaltyPoints(double points) {
        this.loyaltyPoints = points;
    }
}