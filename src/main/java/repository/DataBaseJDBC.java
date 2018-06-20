package repository;

import java.sql.*;

public class DataBaseJDBC extends DataBase {
    private static String JDBCDriver = "com.mysql.jdbc.Driver";
    private static String DBurl = "jdbc:mysql://atypowa.cba.pl/atypowa_cba_pl?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String DBUser = "atypowa";
    private static String DBPassword = "Atyp123@";

    private Connection conn = null;
    private PreparedStatement stmnt = null;
    public DataBaseJDBC(){

    }
    private void openConnection(String SQL) throws SQLException {
        //Class.forName(this.JDBCDriver);
        //łączenie z bazą
        this.conn = DriverManager.getConnection(DBurl, DBUser, DBPassword);
        this.stmnt = this.conn.prepareStatement(SQL);
    }

    private void closeConnection() throws SQLException {
        this.stmnt.close();
        this.conn.close();
    }

    public String getUserName(int idUser){
        /**
         * @return: (String) username if success, or null if fail
         */
        String username = null;
        int amount = 0;
        try{
            String sql;
            sql = "SELECT username FROM java_users WHERE id = ?";
            this.openConnection(sql);
            this.stmnt.setInt(1, idUser);

            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                username = result.getString("username");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(amount == 1)
            return username;
        else
            return null;
    }

    public int checkUser(String username, String password){
        /**
         * @return: (int) id user >= 0 success; < 0 fail
         */
        int userId = -1, amount = 0;

        try{
            String sql;
            sql = "SELECT id FROM java_users WHERE LOWER(username) = ? AND password = ?";
            this.openConnection(sql);
            this.stmnt.setString(1, username.toLowerCase());
            this.stmnt.setString(2, password);
            ResultSet result = this.stmnt.executeQuery();

            while(result.next()){
                userId = result.getInt("id");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(amount == 1)
            return userId;
        else
            return -1;
    }
    public int createUser(String username, String password){
        /**
         * @return: (int) id user >= 0 success; < 0 fail
         */
        int userId = -1, amount = 0;

        try{
            String sql;
            sql = "SELECT id FROM java_users WHERE LOWER(username) = ?";
            this.openConnection(sql);
            this.stmnt.setString(1, username.toLowerCase());
            this.stmnt.setString(2, password);
            ResultSet result = this.stmnt.executeQuery(sql);

            while(result.next()){
                userId = result.getInt("id");
                amount++;
            }

            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(amount == 1)
            return userId;
        else
            return -1;
    }
    public double getUserBitcoins(int userId){
        //double
        double bitcoins = 1;
        return bitcoins;
    }
    public double getUserDollars(int userId){
        //double
        double dolars = 1;
        return dolars;
    }
    public double[] getUserBitcoinsAndDolars(int userId){
        //tablicy double[2]
        double bitcoins = 1;
        double dolars = 1;
        double[] ret = {bitcoins, dolars};
        return ret;
    }
    public double addUserDolars(int userId, double amount){
        //double
        double dolars = 1;
        return dolars;
    }
    public double[] userBuyBitcoins(int userId, double amountBitcoins, double amountDolars){
        //tablicy double[2]
        double bitcoins = 1;
        double dolars = 1;
        double[] ret = {bitcoins, dolars};
        return ret;
    }
    public double[] userSellBitcoins(int userId, double amountBitcoins, double amountDolars){
        //tablicy double[2]
        //bleble ble
        double bitcoins = 1;
        double dolars = 1;
        double ret[] = {bitcoins, dolars};
        return ret;
    }
}