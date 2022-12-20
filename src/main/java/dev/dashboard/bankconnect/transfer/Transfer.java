package dev.dashboard.bankconnect.transfer;

import dev.dashboard.bankconnect.account.Account;
import jakarta.persistence.*;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "sender_account_id")
    private long senderAccountId;

    @Column(name = "receiver_account_id")
    private long receiverAccountId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", insertable = false, updatable = false)
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", insertable = false, updatable = false)
    private Account receiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
}
