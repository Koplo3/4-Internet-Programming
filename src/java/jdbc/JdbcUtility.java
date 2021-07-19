/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.*;

/**
 *
 * @author GATES
 */
public class JdbcUtility {

    Connection con = null;
    String driver;
    String url;
    String userName;
    String password;

    //user table
    PreparedStatement psInsertUser = null;
    PreparedStatement psSelectUserByLogin = null;
    PreparedStatement psUpdateUserPhoto = null;
    PreparedStatement psUpdateUserByID = null;

    //car table
    PreparedStatement psInsertCar = null;
    PreparedStatement psSelectAllCar = null;
    PreparedStatement psSelectCarByID = null;
    PreparedStatement psSelectCarByAvailability = null;
    PreparedStatement psUpdateCarByID = null;
    PreparedStatement psUpdateCarAvailability = null;
    PreparedStatement psDeleteCarById = null;
    
    PreparedStatement psInsertCurrentBooking = null;    
    PreparedStatement psSelectAllCurrentBooking = null;
    PreparedStatement psSelectCurrentBookingByID = null;
    PreparedStatement psUpdateCurrentBookingStatus = null;
    PreparedStatement psDeleteCurrentBookingByID = null;

    //member tables
//    PreparedStatement psSelectCarById = null;
    PreparedStatement psSelectAllCars = null;

    public JdbcUtility(String driver,
            String url,
            String userName,
            String password) {
        this.driver = driver;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public void jdbcConnect() {

        try {

            Class.forName(driver);
            con = DriverManager.getConnection(url, userName, password);
            DatabaseMetaData dma = con.getMetaData();
            System.out.println("\nConnected to " + dma.getURL());
            System.out.println("Driver       " + dma.getDriverName());
            System.out.println("Version      " + dma.getDriverVersion());
            System.out.println("");

        } catch (SQLException ex) {

            while (ex != null) {
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("Message:  " + ex.getMessage());
                System.out.println("Vendor:   " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }

            System.out.println("Connection to the database error");
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection jdbcGetConnection() {
        return con;
    }

    public void jdbcConClose() {

        try {
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //User Table
    public void prepareSQLStatementInsertUser() {

        try {

            //create SQL statement
            String sqlInsertUser = "INSERT INTO user(fullname, login, password, salt, usertype)"
                    + " VALUES(?, ?, ?, ?, ?)";

            //prepare statement
            psInsertUser = con.prepareStatement(sqlInsertUser);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public PreparedStatement getPsInsertUser() {
        return psInsertUser;
    }
    
    public void prepareSQLStatementInsertCurrentBooking() {

        try {

            //create SQL statement
            String sqlInsertCurrentBooking = "INSERT INTO currentBooking(userId, username, carId, days)"
                    + " VALUES(?, ?, ?, ?)";

            //prepare statement
            psInsertCurrentBooking = con.prepareStatement(sqlInsertCurrentBooking);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public PreparedStatement getPsInsertCurrentBooking() {
        return psInsertCurrentBooking;
    }

    

    //psSelectUserByLogin
    public void prepareSQLStatementSelectUserByLogin() {

        try {

            //create SQL statement
            String sqlSelectUserByLogin = "SELECT *"
                    + " FROM user"
                    + " WHERE login = ?";

            //prepare statement
            psSelectUserByLogin = con.prepareStatement(sqlSelectUserByLogin);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectUserByLogin() {
        return psSelectUserByLogin;
    }

    public void prepareSQLStatementUpdateUserPhoto() {

        try {

            //create SQL statement
            String sqlUpdateUserPhoto = "UPDATE user"
                    + " set photo = ?"
                    + " WHERE id = ?";

            //prepare statement
            psUpdateUserPhoto = con.prepareStatement(sqlUpdateUserPhoto);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsUpdateUserPhoto() {
        return psUpdateUserPhoto;
    }
    
    public void prepareSQLStatementUpdateUserByID() {

        try {

            //create SQL statement
            String sqlUpdateUserByID = "UPDATE user"
                    + " set fullname = ?,"
                    + " login = ?"
                    + " WHERE id = ?";

            //prepare statement
            psUpdateUserByID = con.prepareStatement(sqlUpdateUserByID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsUpdateUserByID() {
        return psUpdateUserByID;
    }

    public void prepareSQLStatementInsertCar() {

        try {

            //create SQL statement
            String sqlInsertCar = "INSERT INTO car(name, model, color, year, price)"
                    + " VALUES(?, ?, ?, ?, ?)";

            //prepare statement
            psInsertCar = con.prepareStatement(sqlInsertCar);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsInsertCar() {
        return psInsertCar;
    }

    public void prepareSQLStatementSelectAllCar() {
        try {

            //create SQL statement
            String sqlSelectAllCar = "SELECT * FROM car";

            //prepare statement
            psSelectAllCar = con.prepareStatement(sqlSelectAllCar);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectAllCar() {
        return psSelectAllCar;
    }
    
    public void prepareSQLStatementSelectAllCurrentBooking() {
        try {

            //create SQL statement
            String sqlSelectAllCurrentBooking = "SELECT * FROM currentBooking";

            //prepare statement
            psSelectAllCurrentBooking = con.prepareStatement(sqlSelectAllCurrentBooking);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectAllCurrentBooking() {
        return psSelectAllCurrentBooking;
    }
    
    public void prepareSQLStatementSelectCarByID() {

        try {

            //create SQL statement
            String sqlSelectCarByID = "SELECT *"
                    + " FROM car"
                    + " WHERE id = ?";

            //prepare statement
            psSelectCarByID = con.prepareStatement(sqlSelectCarByID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectCarByID() {
        return psSelectCarByID;
    }
    
    public void prepareSQLStatementSelectCarByAvailability() {

        try {

            //create SQL statement
            String sqlSelectCarByAvailability = "SELECT *"
                    + " FROM car"
                    + " WHERE availability=?";

            //prepare statement
            psSelectCarByAvailability = con.prepareStatement(sqlSelectCarByAvailability);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectCarByAvailability() {
        return psSelectCarByAvailability;
    }

    public void prepareSQLStatementSelectCurrentBookingByID() {

        try {

            //create SQL statement
            String sqlSelectCurrentBookingByID = "SELECT *"
                    + " FROM currentBooking"
                    + " WHERE userID = ?";

            //prepare statement
            psSelectCurrentBookingByID = con.prepareStatement(sqlSelectCurrentBookingByID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectCurrentBookingByID() {
        return psSelectCurrentBookingByID;
    }
    
    public void prepareSQLStatementUpdateCurrentBookingStatus() {

        try {
            //create SQL statement
            String sqlUpdateCurrentBookingStatus = "UPDATE currentBooking"
                    + " set status = ?"
                    + " WHERE ID = ?";

            //prepare statement
            psUpdateCurrentBookingStatus = con.prepareStatement(sqlUpdateCurrentBookingStatus);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsUpdateCurrentBookingStatus() {
        return psUpdateCurrentBookingStatus;
    }

    public void prepareSQLStatementUpdateCarByID() {

        try {

            //create SQL statement
            String sqlUpdateCarByID = "UPDATE car"
                    + " set name = ?,"
                    + " model = ?,"
                    + " color = ?,"
                    + " year = ?,"
                    + " price = ?,"
                    + " availability = ?"
                    + " WHERE id = ?";

            //prepare statement
            psUpdateCarByID = con.prepareStatement(sqlUpdateCarByID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsUpdateCarByID() {
        return psUpdateCarByID;
    }

    public void prepareSQLStatementUpdateCarAvailability() {

        try {

            //create SQL statement
            String sqlUpdateCarAvailability = "UPDATE car"
                    + " set availability = ?"
                    + " WHERE id = ?";

            //prepare statement
            psUpdateCarAvailability = con.prepareStatement(sqlUpdateCarAvailability);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsUpdateCarAvailability() {
        return psUpdateCarAvailability;
    }
    
    public void prepareSQLStatementDeleteCarById() {

        try {

            //create SQL statement
            String sqlDeleteCarById = "DELETE FROM car"
                    + " WHERE id = ?";

            //prepare statement
            psDeleteCarById = con.prepareStatement(sqlDeleteCarById);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsDeleteCarById() {
        return psDeleteCarById;
    }
    
    public void prepareSQLStatementDeleteCurrentBookingByID() {

        try {

            //create SQL statement
            String sqlDeleteCurrentBookingByID = "DELETE FROM currentBooking"
                    + " WHERE carId = ?";

            //prepare statement
            psDeleteCurrentBookingByID = con.prepareStatement(sqlDeleteCurrentBookingByID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsDeleteCurrentBookingById() {
        return psDeleteCurrentBookingByID;
    }

    //psSelectAllCars
    public void prepareSQLStatementSelectAllCars() {

        try {

            //create SQL statement
            String sqlSelectAllCars = "SELECT * FROM car";

            //prepare statement
            psSelectAllCars = con.prepareStatement(sqlSelectAllCars);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public PreparedStatement getPsSelectAllCars() {
        return psSelectAllCars;
    }

}
