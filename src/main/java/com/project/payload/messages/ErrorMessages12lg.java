package com.project.payload.messages;

public class ErrorMessages12lg {
    private ErrorMessages12lg() {
    }//lg120
    public static final String NOT_PERMITTED_METHOD_MESSAGE ="You do not have any permission to do this operation"; //lg121
    public static final String PASSWORD_NOT_MATCHED = "Your passwords are not matched";//lg124
}//Lg119

/*lg120 icin Burada gnreate--Cunstructer yapiyoruz  public ErrorMessages11lg() {
    } ancak publik olan yapiyi private yapiyoruz*/

/* lg121 --buraya bir degisken olustururuz ve ilgili mesaji buraya yazariz
* public static final String NOT_PERMITTED_METHOD_MESSAGE ="You do not have any permission to do this operation";
* bu degisken ifade update ve delete metodlarindada kullanilacak sekilde yapilandirildi servis tarafina gidip bu mesaji
* oraya cekecegiz */
