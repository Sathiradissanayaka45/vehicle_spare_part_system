package Model;

public class Payment {
    private int paymentId;
    private int customerId;
    private String paymentSlip;
    private String fullName;
    private String mobileNumber;
    private String address;
    private String postalCode;
    private String status;

    // Constructor
    public Payment(int paymentId, int customerId, String paymentSlip, String fullName, String mobileNumber, String address, String postalCode, String status) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.paymentSlip = paymentSlip;
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.postalCode = postalCode;
        this.status = status;
    }

    // Getters and setters
    public int getPaymentId() {
        return paymentId;
    }


    public int getCustomerId() {
        return customerId;
    }


    public String getPaymentSlip() {
        return paymentSlip;
    }

    public String getFullName() {
        return fullName;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }


    public String getAddress() {
        return address;
    }


    public String getPostalCode() {
        return postalCode;
    }
    public String getStatus() {
        return status;
    }
}
