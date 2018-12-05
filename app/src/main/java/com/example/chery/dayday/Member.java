package com.example.chery.dayday;

//Member POJO
public class Member {
    private String Uid;
    private String memberName;
    private int memberBirthDay, memberBirthMonth, memberBirthYear;
    private long memberIC, memberPhoneNumber;
    private String memberEmail, memberAddress;

    public Member() {
        // Default constructor required for calls to DataSnapshot.getValue(Member.class)
    }

    public Member(String memberName, int memberBirthDay, int memberBirthMonth,
                  int memberBirthYear, long memberIC, String memberEmail,
                  String memberAddress, int memberPhoneNumber) {
        this.memberName = memberName;
        this.memberBirthDay = memberBirthDay;
        this.memberBirthMonth = memberBirthMonth;
        this.memberBirthYear = memberBirthYear;
        this.memberIC = memberIC;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public String getUid() {
        return Uid;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberBirthDay() {
        return memberBirthDay;
    }

    public void setMemberBirthDay(int memberBirthDay) {
        this.memberBirthDay = memberBirthDay;
    }

    public int getMemberBirthMonth() {
        return memberBirthMonth;
    }

    public void setMemberBirthMonth(int memberBirthMonth) {
        this.memberBirthMonth = memberBirthMonth;
    }

    public int getMemberBirthYear() {
        return memberBirthYear;
    }

    public void setMemberBirthYear(int memberBirthYear) {
        this.memberBirthYear = memberBirthYear;
    }

    public long getMemberIC() {
        return memberIC;
    }

    public void setMemberIC(long memberIC) {
        this.memberIC = memberIC;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public long getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(long memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }
}
