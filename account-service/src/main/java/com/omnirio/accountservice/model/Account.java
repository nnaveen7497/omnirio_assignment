package com.omnirio.accountservice.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Account {

    private String accountId;

    private String accountType;

    private LocalDate openDate;

    @NotNull
    private String customerId;

    private String customerName;

    private String branch;

    private String minorIndicator;

    private User user;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMinorIndicator() {
        return minorIndicator;
    }

    public void setMinorIndicator(String minorIndicator) {
        this.minorIndicator = minorIndicator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
