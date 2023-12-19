package com.project.payload.messages;

public class ErrorMessages12lg {
    private ErrorMessages12lg() {
    }//lg120
    public static final String NOT_PERMITTED_METHOD_MESSAGE ="You do not have any permission to do this operation"; //lg121
    public static final String PASSWORD_NOT_MATCHED = "Your passwords are not matched";//lg124

    public static final String ALREADY_REGISTER_MESSAGE_USERNAME= "Error : User with username %s is already registered";//uc70

    public static final String ALREADY_REGISTER_MESSAGE_SSN= "Error : User with ssn %s is already registered";//uc73

    public static final String ALREADY_REGISTER_MESSAGE_PHONE= "Error : User with phone number %s is already registered";//uc77

    public static final String ALREADY_REGISTER_MESSAGE_EMAIL= "Error : User with email %s is already registered";//uc81

    public static final String ROLE_NOT_FOUND= "There is no role like that, check the database";//uc107


    public static final String NOT_FOUND_USER_USERROLE_MESSAGE= "Error: User not fount with user-role %s";//uc111
}//Lg119

/*lg120 icin Burada gnreate--Cunstructer yapiyoruz  public ErrorMessages11lg() {
    } ancak publik olan yapiyi private yapiyoruz*/

/* lg121 --buraya bir degisken olustururuz ve ilgili mesaji buraya yazariz
* public static final String NOT_PERMITTED_METHOD_MESSAGE ="You do not have any permission to do this operation";
* bu degisken ifade update ve delete metodlarindada kullanilacak sekilde yapilandirildi servis tarafina gidip bu mesaji
* oraya cekecegiz */
