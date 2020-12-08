
package com.example.messageboard;

import com.example.messageboard.javabean.Message;
import com.example.messageboard.javabean.User;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DB {
    private Connection conn = null;
    private Statement stat=null;
    private ResultSet rs=null;
    private PreparedStatement pstat = null;

    private static final String dbURL = "jdbc:mysql://localhost:3306/messageboard?useUnicode=true&characterEncoding=utf8";
    private static final String dbDriver = "com.mysql.jdbc.Driver";
    private static final String dbUser = "root";
    private static final String dbPassword = "mysql123";

    public User queryUser(String userid, String password){
        User user = null;
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from USERINFO where userid='"+userid
                    +"' and password='" + password + "'");
            if(rs.next()){
                user = new User(userid, "");
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(rs!=null)
                    rs.close();
                if(stat!=null)
                    stat.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public User queryUser(String userid){
        User user = null;
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from USERINFO where userid='"+userid
                    +"'");
            if(rs.next()){
                user = new User(userid, "");
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(rs!=null)
                    rs.close();
                if(stat!=null)
                    stat.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public boolean insertUser(String userid, String password){
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            String sql = "insert into USERINFO values(?,?)";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, userid);
            pstat.setString(2, password);
            int result = pstat.executeUpdate();
            if(result != 0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(rs!=null)
                    rs.close();
                if(stat!=null)
                    pstat.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean insertMessage(String title, String content, String userid){
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            String sql = "insert into MESSAGE values(?,?,?,?)";
            pstat = conn.prepareStatement(sql);
            long mid = System.currentTimeMillis();
            pstat.setLong(1, mid);
            pstat.setString(2, title);
            pstat.setString(3, content);
            pstat.setString(4, userid);
            int result = pstat.executeUpdate();
            if(result != 0)
                return true;
            else
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(rs!=null)
                    rs.close();
                if(stat!=null)
                    pstat.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ArrayList<Message> queryMessage(){
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            stat = conn.createStatement();
            rs = stat.executeQuery("select * from MESSAGE");
            while(rs.next()){
                long mid = rs.getLong("mid");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String userid = rs.getString("userid");
                Message m = new Message(mid, title, content, userid);
                messageList.add(m);
            }
            return messageList;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(rs!=null)
                    rs.close();
                if(stat!=null)
                    stat.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messageList;
    }
}
