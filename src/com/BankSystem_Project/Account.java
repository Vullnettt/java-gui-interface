package com.BankSystem_Project;
import java.util.Date;

public class Account implements Comparable{

    static int nextAccNo = 10;
    int accNo;
    String owner;
    City city;
    char gender;
    double balance;
    Date openDate;

    public Account(){

    }

    public Account(String owner, City city, char gender) {
        accNo = nextAccNo;
        nextAccNo += 10;
        this.owner = owner;
        this.city = city;
        this.gender = gender;

        balance = 0.0;
        openDate = null;
    }

    public Account(int accNo, String owner, City city, char gender, double balance) {
        super();
        this.accNo = accNo;
        this.owner = owner;
        this.city = city;
        this.gender = gender;
        this.balance = balance;
    }
    public void setBalance(double b){
        balance = b> 0.0 ? b: 0.0;
    }

    @Override
    public String toString() {
        return accNo + " " + owner + " " + city + " " + gender + " "
                + balance;
    }

    @Override
    public int compareTo(Object o) {
        return this.owner.compareTo(((Account) o) .owner);
    }

    public void deposit(double amount){
        if(amount > 0){
            setBalance(balance + amount);
        }
    }
    public double withdraw(double amount){
        if(amount > 0){
            if(amount < balance){
                setBalance(balance - amount);
            }else{
                amount = balance;
                setBalance(0.0);
            }
            return amount;
        }
        return 0.0;

    }
}
