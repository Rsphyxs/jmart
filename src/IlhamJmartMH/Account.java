package IlhamJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    public int id = 0;
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9&*_~]+(\\.[a-zA-Z0-9&*_~]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?!.* ).{8,}$";

    public Account(int id, String name, String email, String password)
    {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
     public boolean read(String content)
    {
        return false;
    }
    
    public Object write()
    {
        return null;
    }
    
    public static Object newInstance(String content)
    {
        return null;
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
