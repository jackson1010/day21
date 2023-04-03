package TFIP.day21.Model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Customer {
    private int id;
    private String company;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String stateProvince;
    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getBusinessPhone() {
        return businessPhone;
    }
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getStateProvince() {
        return stateProvince;
    }
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public static Customer create(SqlRowSet rs) {
        Customer cs = new Customer();
        cs.setId(rs.getInt("id"));
        cs.setCompany(rs.getString("company"));
        cs.setLastName(rs.getString("last_name"));
        cs.setFirstName(rs.getString("first_name"));
        // cs.setEmailAddress(rs.getString("email_address"));
        // cs.setJobTitle(rs.getString("job_title"));
        // cs.setBusinessPhone(rs.getString("business_phone"));
        // cs.setHomePhone(rs.getString(("home_phone")));
        // cs.setMobilePhone(rs.getString("mobile_phone"));
        // cs.setAddress(rs.getString("address"));
        // cs.setStateProvince(rs.getString("state_province"));
        return cs;
    }

    public JsonValue toJson(){
        return Json.createObjectBuilder()
        .add("id", getId())
        .add("company", getCompany())
        .add("last_name",getLastName())
        .add("first_name", getFirstName())
        .build();
    }
    

    

     

    
}
