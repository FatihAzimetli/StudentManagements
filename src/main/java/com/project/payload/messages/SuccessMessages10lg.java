package com.project.payload.messages;

public class SuccessMessages10lg {

    private SuccessMessages10lg() {
    }//l106

    public static final String PASSWORD_CHANGED_RESPONSE_MESSAGE = "Password Successfully Changed"; //lg105

    public static final String USER_CREATE = "User is saved Successfully";//uc116


    public static final String USER_FOUND = "User is found Successfully";//uc171 <--UserService02lg

    public static final String USER_DELETE = "User is delete Successfully";//uc200 <--UserService02lg
}//lg104


/*bu class statik olacak istince olustutmamak icinve final olacak baska bir yerde mesaj degistirmek istersek bizi durdursun*/

/*l106 newlwmemek icin  generat costructer yapariz  public SuccessMessages10lg() {
    }//l106 ancak newlnmemesi icin puublic silip private yapariz*/