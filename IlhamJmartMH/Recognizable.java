package IlhamJmartMH;

public class Recognizable
{
    public final int id;

    protected Recognizable(int id)
    {
        this.id = id;
    }
    
    public boolean equals(Object Obj){
        if(Obj instanceof Recognizable){
            Recognizable  UcObj = (Recognizable) Obj;
            if(this.id == UcObj.id){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    public boolean equals(Recognizable Recog){
        if(this.id == Recog.id){
            return true;
        }
        else{
            return false;
        }
    }
}
