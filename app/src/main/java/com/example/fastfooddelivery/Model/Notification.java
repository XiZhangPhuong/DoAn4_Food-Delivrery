package com.example.fastfooddelivery.Model;

import java.util.Date;

public class Notification {
    private String PhoneNumber;
    private String Noti;
    private String BookingDate;

    public Notification(String phoneNumber, String noti, String bookingDate) {
        PhoneNumber = phoneNumber;
        Noti = noti;
        BookingDate = bookingDate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNoti() {
        return Noti;
    }

    public void setNoti(String noti) {
        Noti = noti;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }
}
