package IlhamJmartMH;
import java.util.ArrayList;

public class Filter
{
    public static ArrayList<PriceTag> filterPriceTag(PriceTag[]list, 
    double value, boolean less){
        int i;
        ArrayList<PriceTag> priceTags = new ArrayList<>();
        for (i=0; i<list.length; i++){
            if (less){
                if(list[i].getAdjustedPrice()<value){
                    priceTags.add(list[i]);
            }
        }
        }
        return priceTags;
    }
    
    public static void filterProductRating(ArrayList<ProductRating>list, 
    double value, boolean less){
        int i;
        for (i=0; i<list.size(); ++i){
            final ProductRating j = list.get(i);
            if (less && j.getAverage()<value || !less&&j.getAverage()>= value){
                list.remove(i);
            }
        }
    }
}
