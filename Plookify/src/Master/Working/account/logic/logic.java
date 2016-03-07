package Master.Working.account.logic;

import Master.Working.Common.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author jlleow
 */
public class logic
{
    public database data=new database();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public logic()
    {
        
    }
    public void addAccount(String uname,String fname,String lname,String pass,String[] address)//checks if username exists then inserts record
    {
        if(data.dupCheck(uname,"USERNAME","ACCOUNT"))
        {System.out.println(true);}
        else
        {
            System.out.println(false);
            String update="INSERT INTO ACCOUNT (USERNAME,PASSWORD,FIRSTNAME,LASTNAME,DOORNO,STREET,CITY,COUNTY,POSTCODE,CONTACTNO) VALUES('"+uname+"','"+pass+"','"+fname+"','"+lname+"','"+address[0]+"','"+address[1]+"','"+address[2]+"','"+address[3]+"','"+address[4]+"','"+address[5]+"')";
            //System.out.println(update);
            
            data.makeUpdate(update);
            System.out.println("added acc");
            int newID=data.authCheckD(uname,pass);
            String update2="INSERT INTO SUBSCRIPTION (USERID) VALUES('"+newID+"')";
            data.makeUpdate(update2);
            System.out.println("added blank sub");
            
        }
    }
    
    public void deleteAccount()
    {
        
    }
    public void deleteDevice()
    {
        
    }     
    public void newSubscribe(int ID,int months)
    {
        try {
            String endDate=getDueDateString(months);
            int premSet=0;
            int monthSet=0;
            if(daysBeforeNow(endDate)<-29)
            {
                premSet=1;
                monthSet=months;
            }
            Connection conn2= DriverManager.getConnection("jdbc:sqlite:data.db");
            PreparedStatement pStat1=conn2.prepareStatement("UPDATE SUBSCRIPTION SET PREMIUM=?, SUBSCRIPTIONTYPE=?, DUEDATE=? WHERE USERID=?");
            pStat1.setInt(1,premSet);
            pStat1.setInt(2,monthSet);
            pStat1.setString(3,endDate);
            pStat1.setInt(4,ID);
            pStat1.execute();
            System.out.println("update Made");
            conn2.close();
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }
    public boolean duplicateCheck(String search,String column,String table)
    {
        return data.dupCheck( search, column, table);
    }
    public int premCheck(int iD)
    {
        ResultSet rsPremCheck;
        try {
            int premValue=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'").getInt(2);
            String dateDue=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'").getString(4);
            if(premValue==1)
            {
                DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                 //System.out.println(dateDue);
                Date dateB=dFormat.parse(dateDue);
                //System.out.println(date.after(dateB));
                if(date.after(dateB))//if current date is past due date(so not paid)
                {
                    
                    return -1;//expired premium
                }
                else{
                    
                    return 2;
                    }//premium 
            }
            else{
                
                return 1;
                }//free
            } catch (SQLException | ParseException ex)
            {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
            }
        return 0;//if error in checking
    }
    public String stringGet(int ident,String identColumn,String table,String column)
    {
        ResultSet result1;
        String statementA="SELECT "+column+" from "+table+" where "+identColumn+"='"+ident+"'";
        String name=null;
        result1=data.makeQuery(statementA);
        try {
             name=result1.getString(1);
             data.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("null");
        return name;
    }
    
    public int daysBeforeNow(String date)
    {
        try {
            Date now = dateFormat.parse(dateFormat.format(new Date()));
            Date date2= dateFormat.parse(date);
            long nowStamp =now.getTime();
            //System.out.println(nowStamp);
            long date2Stamp=date2.getTime();
            //System.out.println(date2Stamp);
            long result=(nowStamp-date2Stamp);
            result=result/86400000;
            return (int)result;
        } catch (ParseException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public void changeRecord(String stat)
    {
        data.makeUpdate(stat);
        try {
            data.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ObservableList<deviceInfo> makeTableInfo(int ID)
    {
        ArrayList<String[]> tableStuff=new ArrayList<>();
        ResultSet deviceList=data.makeQuery("SELECT DEVICEID,DEVICENAME,DEVICETYPE,DATE FROM DEVICE WHERE DEVICEOWNER='"+ID+"'");
        ObservableList<deviceInfo> deviceData = FXCollections.observableArrayList();
        int count=5;
        try {
            while(deviceList.next() && count>=0)
            {
                int timeDif=daysBeforeNow(deviceList.getString(4));
                String[] dData={deviceList.getString(1),deviceList.getString(2),deviceList.getString(3),Integer.toString(timeDif)};
                tableStuff.add(dData);
                count=count-1;
            }
            data.conn.close();
            deviceList.close();
            for(int i=0;i<tableStuff.size();i++)
            {
                int days=Integer.parseInt(tableStuff.get(i)[3]);
                int dId=Integer.parseInt(tableStuff.get(i)[0]);
                deviceInfo info=new deviceInfo(dId,tableStuff.get(i)[1],tableStuff.get(i)[2],days);
                deviceData.add(info);
            }
            while(count>0)
            {
                deviceInfo blankInfo=new deviceInfo(999,"---","---",0);
                deviceData.add(blankInfo);
                count=count-1;
            }
            } catch (SQLException ex) {
                Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(deviceList==null)
        {
            System.out.println("deviceList RS closed");
        }
        return deviceData;
    }
    public String getDueDateString(int months)
    {
        try {
            Date now = dateFormat.parse(dateFormat.format(new Date()));
            long stamp=now.getTime();
            long adding=2592000*months;
            stamp=stamp/1000;
            stamp=stamp+adding;
            stamp=stamp*1000;
            Date dueDate=dateFormat.parse(dateFormat.format(stamp));
            System.out.println(dateFormat.format(dueDate));
            return dateFormat.format(dueDate);
            } catch (ParseException ex) 
            {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return null;
    }
}
