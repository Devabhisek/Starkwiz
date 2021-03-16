package com.starkwiz.starkwiz.ModelClass;

public class InterestModelClass {

    String Interest;

    public InterestModelClass(String interest) {
        Interest = interest;
    }

    public String getInterest() {
        return Interest;
    }

    public void setInterest(String interest) {
        Interest = interest;
    }

    @Override
    public String toString() {
        return "{" +
                "Interest='" + Interest + '\'' +
                '}';
    }
}
