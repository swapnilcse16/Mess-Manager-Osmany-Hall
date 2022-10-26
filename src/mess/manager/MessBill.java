package mess.manager;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessBill {
    public int hallId,bf,lunch,dinner;
    public String DATE;
    public Double breakFastBill, lunchBill, dinnerBill, mealCharge;
    public DecimalFormat df = new DecimalFormat("####0.00");
    public double offer;

    public MessBill(int hallId, String dte, Double breakFastBill, Double lunchBill, Double dinnerBill, int bf,int lunch, int dinner) {
        this.hallId = hallId;
        this.breakFastBill = breakFastBill;
        this.lunchBill = lunchBill;
        this.dinnerBill = dinnerBill;
        this.mealCharge = breakFastBill + dinnerBill + lunchBill;
        this.bf = bf;
        this.dinner = dinner;
        this.lunch = lunch;
        this.DATE = dte;
        offer = 0.0;
        
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); ////////String ta je format e ache,sei format ta hobe
        try{
            Date date = dt.parse(this.DATE); 
            Format formatter = new SimpleDateFormat("MMM d,yyyy");   ////////je format e convert korte chai
            this.DATE = formatter.format(date);
        }catch(Exception e){}
        
    }
    
    
    
    public Object[] copyShowBill(){
        Object o[] = {DATE,bf + "", lunch + "", dinner + "",breakFastBill+"", lunchBill+"",dinnerBill+"",Double.valueOf(df.format(mealCharge))+""};
        return o;
    }

    
}
