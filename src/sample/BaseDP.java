package sample;

import java.sql.*;

public class BaseDP {
    private Connection conn;
    private static boolean hasData = false;
    public BaseDP(){
        conn = SdzConnection.getInstance();
        initialze();
    }

    public ResultSet searchUser(String pseudo ,String password) throws SQLException {
        String query = "SELECT pseudo,password,nom,prenom,type FROM user WHERE pseudo = '" + pseudo +"' AND  password = '"+password+"';";
        Statement stat = conn.createStatement();
        ResultSet res  = stat.executeQuery(query);
        return res;

    }

    public void insert(String peseudo , String nom , String prenom, String passWord,String type) throws SQLException {
        PreparedStatement prep = conn.prepareStatement("insert into user values(?,?,?,?,?);");
        prep.setString(1, peseudo);
        prep.setString(2, nom);
        prep.setString(3, prenom);
        prep.setString(4, passWord);
        prep.setString(5, type);
        prep.execute();
        prep.close();
    }

    private void initialze(){
        // check for database table
        if( !hasData ){
            try{
                Statement state = conn.createStatement();
                ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='user'");
                if( !res.next()) {
                    System.out.println("Building the User table with prepopulated values.");
                    // need to build the table
                    Statement state2 = conn.createStatement();
                    state2.executeUpdate("create table user(pseudo varchar(60),"
                            + "nom varchar(60)," + "prenom varchar(60),"+"password varchar(60)," +"type varchar(10),"+ "primary key (pseudo));");

                    // inserting some sample data
                    PreparedStatement prep = conn.prepareStatement("insert into user values(?,?,?,?,?);");
                    prep.setString(1, "JohnMc");
                    prep.setString(2, "John");
                    prep.setString(3, "McNeil");
                    prep.setString(4, "lazizi97+");
                    prep.setString(5, "non");
                    prep.execute();
                    state2.close();
                    prep.close();
                }
                state.close();
                res.close();


            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
