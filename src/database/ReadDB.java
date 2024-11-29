package database;

import Entity.*;

import java.sql.*;
import java.util.List;

public class ReadDB {
    ControlDB database_control;

    public ReadDB() {
        database_control = ControlDB.getobject();
    }

    public void loadDatabase() {
        try {
            gettingMovies();
            gettingTheatres();
            gettingBankName();
            gettingAnnouncements();
            gettingBankInfo();
            gettingUsers();
        } catch (SQLException e) {
            
            System.err.println("Error loading database: " + e.getMessage());
        }
    }
    
    // Getting Users from the database
    public void gettingUsers() throws SQLException {
        String query = "SELECT * FROM RegisteredUsers";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                int bankId = rs.getInt("bank_info_id");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");

                UserBankInfo bankInfo = database_control.searchBankingInfo(bankId);
                Entity.Date foundDate = new Entity.Date(day, month, year);
                RegisteredUser user = new RegisteredUser(id, username, password, firstName, lastName, email, bankInfo, foundDate);
                database_control.addUser(user);
            }
        }
    }

    // Getting Movies from the database
    public void gettingMovies() throws SQLException {
        String query = "SELECT * FROM Movies";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String code = rs.getString("code");
                double rating = rs.getDouble("rating");
                String description = rs.getString("description");

                Movie movie = new Movie(id, title, genre, code, rating, description);
                database_control.addMovie(movie);
            }
        }
    }

    // Getting Showtimes from the database
    public void gettingShowtimes() throws SQLException {
        String query = "SELECT * FROM Showtimes";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("ShowtimeID");
                int movieId = rs.getInt("MovieID");
                int auditoriumId = rs.getInt("RoomID");
                int hour = rs.getInt("Hour");
                int min = rs.getInt("Minute");
                int day = rs.getInt("Day");
                int month = rs.getInt("Month");
                int year = rs.getInt("Year");

                Movie movie = database_control.searchMovie(movieId);
                ScreeningRoom auditorium = database_control.searchAuditorium(auditoriumId);
                Entity.Date foundDate = new Entity.Date(day, month, year);
                Showtime showtime = new Showtime(id, movie, auditorium, foundDate, hour, min);
                database_control.addShowtime(showtime);
            }
        }
    }

    // Getting Theatres from the database
    public void gettingTheatres() throws SQLException {
        String query = "SELECT * FROM Theatres";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Theatre theatre = new Theatre(id, name);
                database_control.addTheatre(theatre);
            }
        }
        gettingAuditoriums();
    }

    // Getting Bank Info from the database
    public void gettingBankInfo() throws SQLException {
        String query = "SELECT * FROM BankInfo";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String Name = rs.getString("holder_name");
                String accountNum = rs.getString("account_number");

                UserBankInfo bankInfo = new UserBankInfo(id, Name, accountNum);
                database_control.addBankingInfo(bankInfo);
            }
        }
    }

    // Getting Bank Name from the database
    public void gettingBankName() throws SQLException {
        String query = "SELECT * FROM BankName";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            if (rs.next()) {
                String bankName = rs.getString("name");
                database_control.setInst(new Bank(bankName));
            }
        }
    }

    // Getting Auditoriums from the database
    public void gettingAuditoriums() throws SQLException {
        String query = "SELECT * FROM ScreeningRooms";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int rows = rs.getInt("rows");
                int columns = rs.getInt("columns");
                int theatreId = rs.getInt("theatre_id");

                Theatre theatre = database_control.searchTheatre(theatreId);
                ScreeningRoom auditorium = new ScreeningRoom(id, rows, columns, theatre);
                database_control.addAuditorium(auditorium);
            }
        }
        gettingShowtimes();
    }

    // Getting Announcements from the database
    public void gettingAnnouncements() throws SQLException {
        String query = "SELECT * FROM Announcements";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int startDay = rs.getInt("Start_Day");
                int startMonth = rs.getInt("StartMonth");
                int startYear = rs.getInt("StartYear");

                int endDay = rs.getInt("EndDay");
                int endDMonth = rs.getInt("EndMonth");
                int endYear = rs.getInt("EndYear");
                int movieId = rs.getInt("MovieID");

                Entity.Date startDate = new Entity.Date(startDay, startMonth, startYear);
                Entity.Date endDate = new Entity.Date(endDay, endDMonth, endYear);

                Movie movie = database_control.searchMovie(movieId);
                Announcement announcement = new Announcement(id, startDate, endDate, movie);
                database_control.addAnnouncement(announcement);
                movie.setMovieAnnouncement(announcement);
            }
        }
    }
}
