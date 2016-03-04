package Master.Working.account.logic;

import Master.Working.Common.database;
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
            System.out.println("added");
        }
    }
    
    public void deleteAccount()
    {
        
    }
     
    public void subscribe()
    {
        
    }
    public boolean duplicateCheck(String search,String column,String table)
    {
        return data.dupCheck( search, column, table);
    }
    public int premCheck(int iD)
    {
        ResultSet rsPremCheck=data.makeQuery("SELECT * FROM SUBSCRIPTION WHERE USERID='"+iD+"'");
        try {
            if(rsPremCheck.getInt(2)==1)
            {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                try {
                    String dateDue = rsPremCheck.getString(4);
                    //System.out.println(dateDue);
                    Date dateB=dateFormat.parse(dateDue);
                    //System.out.println(date.after(dateB));
                    if(date.after(dateB))//if current date is past due date(so not paid)
                    {
                        data.conn.close();
                        return -1;//expired premium
                    }
                    else{
                        data.conn.close();
                        return 2;}//premium
                } catch (SQLException ex) {
                    Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{data.conn.close();
                return 1;}//free
        } catch (SQLException ex) {
            Logger.getLogger(logic.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            data.conn.close();
        } catch (SQLException ex) {
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
    public ResultSet resultGet(String query)
    {
        ResultSet setResult=null;
        setResult=data.makeQuery(query);
        return setResult;
                
    }
    
    public int daysBeforeNow(String date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
        ResultSet deviceList=resultGet("SELECT DEVICEID,DEVICENAME,DEVICETYPE,DATE FROM DEVICE WHERE DEVICEOWNER='"+ID+"'");
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
}
