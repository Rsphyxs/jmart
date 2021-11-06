package IlhamJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public double balance;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";
    public Store store;

    public Account(String name, String email, String password, double balance)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }
    
    public String toString() {
        return  "name : " + this.name + 
                "\nemail : " + this.name + 
                "\npassword : " + this.password;
    }
    
    public boolean validate(){
        Pattern REGEX_EMAIL = Pattern.compile(this.REGEX_EMAIL);
        Matcher EmailValidate = REGEX_EMAIL.matcher(this.email);
        boolean emailValidate = EmailValidate.find();
        Pattern REGEX_PASSWORD = Pattern.compile(this.REGEX_PASSWORD);
        Matcher PasswordValidate = REGEX_PASSWORD.matcher(this.password);
        boolean passwordValidate = PasswordValidate.find();
        
        if(emailValidate == true && passwordValidate == true){
            return true;
        }
        else{
            return false;
        }
    }
}
