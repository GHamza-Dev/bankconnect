package dev.dashboard.bankconnect.dto;

import jakarta.validation.constraints.NotNull;

public class TransactionRequest {
    private String senderNumber;
    private String receiverNumber;

    @NotNull
    private Double amount;

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
