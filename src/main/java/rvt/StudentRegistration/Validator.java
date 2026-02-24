package rvt.StudentRegistration;

import java.util.regex.*;

public class Validator {
    public static boolean isNameValid(String vards) {
        return vards != null && vards.matches("^[a-zA-Zā-žĀ-Ž]+$") && vards.length() >= 3;
    }

    public static boolean isSurnameValid(String uzvards) {
        return uzvards != null && uzvards.matches("^[a-zA-Zā-žĀ-Ž]+$") && uzvards.length() >= 1;
    }

    public static boolean isEmailValid(String epasts) {
        return epasts != null && epasts.contains("@") && epasts.contains(".");
    }

    public static boolean isCodeValid(String personasKods) {
        return personasKods != null && personasKods.matches("\\d{6}-\\d{5}");
    }
}
