package com.project.contactMessage.messages;

public class Messages {
    public static final String NOT_FOUND_MESSAGE = "Message Not Found";//135

    public static final String WRONG_DATE_FORMAT = "Wrong Date Format";//165

    public static final String WRONG_TIME_FORMAT = "Wrong Time Format";//179
    public static final String CONTACT_MESSAGE_DELETED_SUCCESSFULLY = "Contact Message Deleted Successfully";//137 to 138
}//134
//138 to ContactMessageService
//164- 165 ContactMessageService klasindan geldim
// 165 WRONG_DATE_FORMAT bunu copyaladik
//bu klasa new lemeden nasil ulasiyoruz Message msg = new Message(); msg.NOT_FOUND_MESSAGE bu new olmamasi icin
// degiskenlerin skopunu static yaptik. bu degisken artik class bagli oldugu icin static oldugundan new ihtiyaci yok
// degiskenler nesnelere bagi degil direk class bagli