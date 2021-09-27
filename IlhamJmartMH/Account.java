package IlhamJmartMH;

public class Account extends Recognizable implements FileParser
{
    public String name;
    public String email;
    public String password;
    public int id = 0;

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
}
