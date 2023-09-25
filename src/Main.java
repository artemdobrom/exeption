import exeption.WrongLoginException;
import exeption.WrongPasswordException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static final String VALIDATE_PATTERN = "^[a-zA-Z0-9-_]+$";

    public static void main(String[] args) {
        check("login", "pass", "pass");
        check("loginLoginLoginLoginLoginLogin", "pass", "pass");
        check("login^^&*#", "pass", "pass");
        check("login", "pass", "passPassPass");
        check("login", "pass#$%%", "pass#$%%");
    }


    private static boolean check(String login, String password, String confirmPassword) {

        boolean isValid = true;

        try {
            checkLogin(login);
            checkPassword(password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println(" Не верный логин. " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println(" Не верный пароль. " + e.getMessage());
            isValid = false;
        }
        if (isValid) {
            System.out.println(" Логин и пароль корректные. ");
        }

        return isValid;
    }



    private static void checkLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Login может содержат в себе только латинские буквы, цифры и знак подчеркивания. ");
        } else if (login.length() > 20) {
            throw new WrongLoginException("У параметра login есть ограничение по длине – он должен быть равен или меньше 20 символов. ");
        }
    }

    private static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {
        if (!confirmPassword.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Пароль может содержат в себе только латинские буквы, цифры и знак подчеркивания. ");
        } else if (confirmPassword.length() > 20) {
            throw new WrongPasswordException("У пароля есть ограничение по длине – он должен быть равен или меньше 20 символов. ");
        } else if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }

    }
}