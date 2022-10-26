
package mess.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BillManager {
    Connection conn;
    PreparedStatement psmt;
    ResultSet rs;
    String months[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    int days[] = new int[12];
    String givenMonth,givenYear;
    int index, HallId;
    DecimalFormat df = new DecimalFormat("####0.00");
    public int PROBLEM;
    
    public BillManager() {
        conn = null;
        psmt = null;
        rs = null;
        givenMonth = "";
        givenYear = "";
        
    }
    
    public BillManager(int hallId) {
        conn = null;
        psmt = null;
        rs = null;
        givenMonth = "";
        givenYear = "";
        this.HallId = hallId;
    }
    
    
    public BillManager(String month, String year, int hallId){
        conn = null;
        psmt = null;
        rs = null;
        givenMonth = month.trim();
        givenYear = year.trim();
        this.HallId = hallId;
        PROBLEM = 0;
        
        days[0] = 31;
        days[1] = 28 + leapYear(year);
        days[2] = 31;
        days[3] = 30;
        days[4] = 31;
        days[5] = 30;
        days[6] = 31;
        days[7] = 31;
        days[8] = 30;
        days[9] = 31;
        days[10] = 30;
        days[11] = 31;
        
        for(int i=0; i<12; i++){
            if(month.equals(months[i])){
                index = i;
                break;
            }
        }
        
        
        /*for(int i=0; i<days[index]; i++){
            System.out.println(dateMaker(i));
        }*/
        
    }
    
    
    private String dateMaker(int day,int flag, int change){
        int tempIndex = index - change;
        if(flag==-1)    tempIndex = 11;
        String date = "";
        date = date + (Integer.parseInt(givenYear) + flag) + "-";
        //if(index + 1 < 10)  date = date + "0" + (tempIndex+1) + "-";
        if(tempIndex + 1 < 10)  date = date + "0" + (tempIndex+1) + "-";
        else    date = date + (tempIndex+1)+ "-";
        if(day+1 < 10)  date = date + "0" + (day+1);
        else    date = date + (day+1);
        return date;
    }
    
    
    public int[] dateExtract(String date){
        int a[] = new int[3];
        char c[] = date.toCharArray();
        int l = date.length();
        String s = "";
        int index = 0;
        
        for(int i=0; i<l; i++){
            if(c[i]=='-'){
                a[index++] = Integer.parseInt(s);
                s = "";
                continue;
            }
            
            else if(i == l-1){
                s = s + c[i];
                a[index++] = Integer.parseInt(s);
                break;
            }
            
            s = s + c[i];
        }
        return a;
    }
    
    public String dateMaker(int[] a){
        String s = "";
        s = s + a[0] + "-";
        if(a[1] <10)    s = s + "0" + a[1] + "-";
        else    s = s + a[1] + "-";
        if(a[2] < 10)   s = s + "0" + a[2];
        else    s = s + a[2];
        
        return s;
    }
    
    public String nextDate(String currentDate){
        int a[] = dateExtract(currentDate);
        int leap = leapYear(a[0] + "");
        int day[] = new int[12];
        day[0] = 31;
        day[1] = 28 + leap;
        day[2] = 31;
        day[3] = 30;
        day[4] = 31;
        day[5] = 30;
        day[6] = 31;
        day[7] = 31;
        day[8] = 30;
        day[9] = 31;
        day[10] = 30;
        day[11] = 31;
        
        if(a[2] == day[a[1]-1]){
            a[2] = 1;
            if(a[1]==12){
                a[1]=1;
                a[0]++;
            }
            else    a[1]++;
        }
        
        else    a[2]++;
        
        return dateMaker(a);
    }
    
    
    private int leapYear(String year){
        int y = Integer.parseInt(year);
        if(  ((y % 4 == 0) && (y % 100 != 0)) ||    (y % 400 == 0)      )  return 1;
        return 0;
    }
    
    
    
    
    
    public ArrayList<MessBill> showBill(){
        conn = JConnection.ConnecrDb();
        ArrayList <MessBill> messBill = new ArrayList<>();
        
        int last = 0;
        if(index==0)    last = 31;
        else    last = days[index-1];
        int flag = 0;
        int change = 1;
        
        for(int i=25; ; i++){
            int totalBf =0, totalLunch =0, totalDinner = 0;
            int studentBf =0, studentLunch =0, studentDinner =0;
            Double bfOffer = 0.0, lunchOffer = 0.0, dinnerOffer = 0.0,totalOffer = 0.0; /////////////////////offer
            
            if(index == 0 && i>=25 && i<=30)  flag = -1;
            if(i==last){
                i = 0;
                flag = 0;
                change = 0;
            }
            String DATE = dateMaker(i,flag,change);
           
            //System.out.println(DATE);
            
            try{
                psmt = conn.prepareStatement("SELECT * FROM paradestate WHERE curdate = ?");
                psmt.setString(1,DATE);
                rs = psmt.executeQuery();
                int count = 0;
  
                while(rs.next()){
                    int hallId = rs.getInt(1);
                    if(hallId == this.HallId){
                        studentBf = rs.getInt(3);
                        studentLunch = rs.getInt(4);
                        studentDinner = rs.getInt(5);
                    }
                    totalBf += rs.getInt(3);
                    totalLunch += rs.getInt(4);
                    totalDinner += rs.getInt(5);
                    count++;
                }
                
                psmt.close();
                rs.close();
                
                psmt = conn.prepareStatement("SELECT bf,lunch,dinner FROM offerinfo WHERE offerdate = ?");///888
                psmt.setString(1, DATE);///88888
                rs = psmt.executeQuery();///8888
                while(rs.next()){///8888
                    bfOffer = rs.getDouble(1);///888
                    lunchOffer = rs.getDouble(2);////888
                    dinnerOffer = rs.getDouble(3);////888
                }///888
                psmt.close();////888
                rs.close();///8888
            }catch(Exception e){
                PROBLEM = 1;
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return messBill;
            }
            
            String state[] = {"Breakfast", "Lunch", "Dinner"};
            Double price[] = new Double[3];
           
            for(int k=0; k<3; k++){
                price[k] = 0.0;
                try{
                    psmt = conn.prepareStatement("SELECT amount,avgprice FROM storeoutput WHERE outdate = ? AND state = ?");
                    psmt.setString(1, DATE);
                    psmt.setString(2, state[k]);
                    rs = psmt.executeQuery();
                    
                    while(rs.next()){
                        //System.out.println("Hello");
                        Double quan = rs.getDouble(1);
                        Double avg = rs.getDouble(2);
                        quan = Double.valueOf(df.format(quan));
                        avg = Double.valueOf(df.format(avg));
                        price[k] = price[k] + quan * avg;
                        /////System.out.println(price[k] + "");//////////////////
                    }
                    
                    psmt.close();
                    rs.close();
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                    PROBLEM = 1;
                    return messBill;
                }
            }///////loop of k
            
            
            Double breakFastbill = 0.0, lunchBill = 0.0, dinnerBill =0.0;
            if(totalBf!=0)  breakFastbill = Double.valueOf(df.format((price[0] * studentBf)/totalBf));
            if(totalLunch!=0)   lunchBill = Double.valueOf(df.format((price[1] * studentLunch)/totalLunch));
            if(totalDinner!=0)  dinnerBill = Double.valueOf(df.format((price[2] * studentDinner)/totalDinner));
            
            MessBill m = new MessBill(HallId, DATE, breakFastbill, lunchBill, dinnerBill,studentBf, studentLunch, studentDinner);
            
            if(studentBf!=0)    totalOffer += bfOffer;  //88888
            if(studentLunch!=0) totalOffer += lunchOffer; //8888
            if(studentDinner!=0)    totalOffer += dinnerOffer; //8888
            m.offer = totalOffer; //888888
            messBill.add(m);
            
            if(i==24)   break;
            
        }///////////loop ends
        
        try{
            conn.close();
        }catch(Exception e){}
        return messBill;
    }
    
    
    
    
    
    
    
    
    
    
    public ArrayList<MessBill> showBill2(){
        conn = JConnection.ConnecrDb();
        ArrayList <MessBill> messBill = new ArrayList<>();
        
        
        for(int i=0; i<days[index]; i++){
            int totalBf =0, totalLunch =0, totalDinner = 0;
            int studentBf =0, studentLunch =0, studentDinner =0;
            String DATE = dateMaker(i,0,0);
            
            try{
                psmt = conn.prepareStatement("SELECT * FROM paradestate WHERE curdate = ?");
                psmt.setString(1,DATE);
                rs = psmt.executeQuery();
                int count = 0;
  
                while(rs.next()){
                    int hallId = rs.getInt(1);
                    if(hallId == this.HallId){
                        studentBf = rs.getInt(3);
                        studentLunch = rs.getInt(4);
                        studentDinner = rs.getInt(5);
                    }
                    totalBf += rs.getInt(3);
                    totalLunch += rs.getInt(4);
                    totalDinner += rs.getInt(5);
                    count++;
                }
                
                psmt.close();
                rs.close();
                
            }catch(Exception e){
                PROBLEM = 1;
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return messBill;
            }
            
            String state[] = {"Breakfast", "Lunch", "Dinner"};
            Double price[] = new Double[3];
           
            for(int k=0; k<3; k++){
                price[k] = 0.0;
                try{
                    psmt = conn.prepareStatement("SELECT amount,avgprice FROM storeoutput WHERE outdate = ? AND state = ?");
                    psmt.setString(1, DATE);
                    psmt.setString(2, state[k]);
                    rs = psmt.executeQuery();
                    
                    while(rs.next()){
                        //System.out.println("Hello");
                        Double quan = rs.getDouble(1);
                        Double avg = rs.getDouble(2);
                        quan = Double.valueOf(df.format(quan));
                        avg = Double.valueOf(df.format(avg));
                        price[k] = price[k] + quan * avg;
                        /////System.out.println(price[k] + "");//////////////////
                    }
                    
                    psmt.close();
                    rs.close();
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                    PROBLEM = 1;
                    return messBill;
                }
            }///////loop of k
            
            
            Double breakFastbill = 0.0, lunchBill = 0.0, dinnerBill =0.0;
            if(totalBf!=0)  breakFastbill = Double.valueOf(df.format((price[0] * studentBf)/totalBf));
            if(totalLunch!=0)   lunchBill = Double.valueOf(df.format((price[1] * studentLunch)/totalLunch));
            if(totalDinner!=0)  dinnerBill = Double.valueOf(df.format((price[2] * studentDinner)/totalDinner));
            
            MessBill m = new MessBill(HallId, DATE, breakFastbill, lunchBill, dinnerBill,studentBf, studentLunch, studentDinner);
            messBill.add(m);
        }///////////loop ends
        
        try{
            conn.close();
        }catch(Exception e){}
        return messBill;
    }
    
    
    
    
    
    
    
    public ArrayList<MessBill> showBill(int hId, String startDate, String endDate){
        conn = JConnection.ConnecrDb();
        this.HallId = hId;
        ArrayList <MessBill> messBill = new ArrayList<>();
        String curDate = startDate;
        
        
        for(;;){
            int totalBf =0, totalLunch =0, totalDinner = 0;
            int studentBf =0, studentLunch =0, studentDinner =0;
            Double bfOffer = 0.0, lunchOffer = 0.0, dinnerOffer = 0.0,totalOffer = 0.0; /////////////////////offer
            String DATE = curDate;           
            //System.out.println(DATE);
            
            try{
                psmt = conn.prepareStatement("SELECT * FROM paradestate WHERE curdate = ?");
                psmt.setString(1,DATE);
                rs = psmt.executeQuery();
                int count = 0;
  
                while(rs.next()){
                    int hallId = rs.getInt(1);
                    if(hallId == this.HallId){
                        studentBf = rs.getInt(3);
                        studentLunch = rs.getInt(4);
                        studentDinner = rs.getInt(5);
                    }
                    totalBf += rs.getInt(3);
                    totalLunch += rs.getInt(4);
                    totalDinner += rs.getInt(5);
                    count++;
                }
                
                psmt.close();
                rs.close();
                
                
                psmt = conn.prepareStatement("SELECT bf,lunch,dinner FROM offerinfo WHERE offerdate = ?");///888
                psmt.setString(1, DATE);///88888
                rs = psmt.executeQuery();///8888
                while(rs.next()){///8888
                    bfOffer = rs.getDouble(1);///888
                    lunchOffer = rs.getDouble(2);////888
                    dinnerOffer = rs.getDouble(3);////888
                }///888
                psmt.close();////888
                rs.close();///8888
            }catch(Exception e){
                PROBLEM = 1;
                JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                return messBill;
            }
            
            String state[] = {"Breakfast", "Lunch", "Dinner"};
            Double price[] = new Double[3];
           
            for(int k=0; k<3; k++){
                price[k] = 0.0;
                try{
                    psmt = conn.prepareStatement("SELECT amount,avgprice FROM storeoutput WHERE outdate = ? AND state = ?");
                    psmt.setString(1, DATE);
                    psmt.setString(2, state[k]);
                    rs = psmt.executeQuery();
                    
                    while(rs.next()){
                        //System.out.println("Hello");
                        Double quan = rs.getDouble(1);
                        Double avg = rs.getDouble(2);
                        quan = Double.valueOf(df.format(quan));
                        avg = Double.valueOf(df.format(avg));
                        price[k] = price[k] + quan * avg;
                        /////System.out.println(price[k] + "");//////////////////
                    }
                    
                    psmt.close();
                    rs.close();
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Oops! There are some problems!", "Unknown Error Occured!", JOptionPane.ERROR_MESSAGE);
                    PROBLEM = 1;
                    return messBill;
                }
            }///////loop of k
            
            
            Double breakFastbill = 0.0, lunchBill = 0.0, dinnerBill =0.0;
            if(totalBf!=0)  breakFastbill = Double.valueOf(df.format((price[0] * studentBf)/totalBf));
            if(totalLunch!=0)   lunchBill = Double.valueOf(df.format((price[1] * studentLunch)/totalLunch));
            if(totalDinner!=0)  dinnerBill = Double.valueOf(df.format((price[2] * studentDinner)/totalDinner));
            
            MessBill m = new MessBill(HallId, DATE, breakFastbill, lunchBill, dinnerBill,studentBf, studentLunch, studentDinner);
            
            if(studentBf!=0)    totalOffer += bfOffer;  //88888
            if(studentLunch!=0) totalOffer += lunchOffer; //8888
            if(studentDinner!=0)    totalOffer += dinnerOffer; //8888
            m.offer = totalOffer; //888888
            //System.out.println(totalOffer + "");
            messBill.add(m);
            System.out.println(totalOffer);
            
            if(curDate.equals(endDate)){
                //System.out.println("Break");
                //System.out.println(HallId + "");
                break;
            }
            else    curDate = nextDate(curDate);
            
        }///////////loop ends
        
        try{
            conn.close();
        }catch(Exception e){}
        return messBill;
    }
    
    
    
    
    
    
    
    
    
    
    
    /*public Object[] showMonthlyBill(int hallId, String month, String year){
        conn = JConnection.ConnecrDb();
        int totalBf = 0, totalLunch = 0, totalDinner = 0;
            
    }*/
}
