package IlhamJmartMH;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store extends Recognizable implements FileParser
{
    public String name;
    public String address;
    public String phoneNumber;
    static final String REGEX_NAME = "^\\d{9,12}$";
    static final String REGEX_PHONE = "^[A-Z](?!.*(\\s)\1).{4,20}$";
    
    public Store(int accountId, String name, String address, String phoneNumber){
        super(accountId);
        this.name = name;
        this.address= address;
        this.phoneNumber = phoneNumber;
    }
    
    public Store(Account account, String name, String address, String phoneNumber){
        super(account.id);
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
    
    public String toString() {
        return  "name: " + this.name + 
                "\naddress: " + this.address + 
                "\nahoneNumber: " + this.phoneNumber;
    }
    
    public boolean validate(){
        Pattern REGEX_NAME = Pattern.compile(this.REGEX_NAME);
        Matcher NameValidate = REGEX_NAME.matcher(this.name);
        boolean nameValidate = NameValidate.find();
        Pattern REGEX_PHONE = Pattern.compile(this.REGEX_PHONE);
        Matcher NumberValidate = REGEX_PHONE.matcher(this.name);
        boolean numberValidate = NumberValidate.find();
        
        if(nameValidate == true && numberValidate == true){
            return true;
        }
        else{
            return false;
        }
    }
}
