package com.BankSystem_Project;
import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> , Serializable{

    private static int next = 1;
    int trsNo;
    Account acc;
    LocalDate date;
    char operation;
    double amount;

    public Transaction(Account acc, LocalDate date, char operation, double amount) {
        this.acc = acc;
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        trsNo = next++;
    }

    @Override
    public int compareTo(Transaction o) {
        return this.trsNo - o.trsNo;
    }

    @Override
    public String toString() {
        return "Transaction [trsNo=" + trsNo + ", acc=" + acc + ", date=" + date + ", operation=" + operation
                + ", amount=" + amount + "]";
    }

    public int getTrsNo() {
        return trsNo;
    }

    public void setTrsNo(int trsNo) {
        this.trsNo = trsNo;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public char getOperation() {
        return operation;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
