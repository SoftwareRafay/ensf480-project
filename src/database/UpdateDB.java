package database;

import Entity.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateDB {
    ControlDB databaseController;

    public UpdateDB() {
        databaseController = ControlDB.getobject();
    }

    public void write_to_Database() {
        try {
            writeRegisteredUser();
        } catch (SQLException e) {
            System.err.println("Error updating database: " + e.getMessage());
        }
    }

    public void writeRegisteredUser() throws SQLException {
        String insertQuery = """
            INSERT INTO RegisteredUsers (id, username, password, first_name, last_name, email, bank_info_id, Day, Month, Year)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                username = VALUES(username),
                password = VALUES(password),
                first_name = VALUES(first_name),
                last_name = VALUES(last_name),
                email = VALUES(email),
                bank_info_id = VALUES(bank_info_id),
                Day = VALUES(Day),
                Month = VALUES(Month),
                Year = VALUES(Year)
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (RegisteredUser r : databaseController.getlist_of_users()) {
                preparedStatement.setInt(1, r.getID_of_User());
                preparedStatement.setString(2, r.getUsername());
                preparedStatement.setString(3, r.getPassword());
                preparedStatement.setString(4, r.getfirst_name());
                preparedStatement.setString(5, r.getlast_name());
                preparedStatement.setString(6, r.getgmail_of_user());
                preparedStatement.setInt(7, r.getBankInfo().getID_for_bank());
                preparedStatement.setInt(8, r.getdate_of_feeDeposit().getDay());
                preparedStatement.setInt(9, r.getdate_of_feeDeposit().getMonth());
                preparedStatement.setInt(10, r.getdate_of_feeDeposit().getYear());

                preparedStatement.addBatch(); // Add the current user to the batch
            }

            preparedStatement.executeBatch(); // Execute all batch updates
        }
    }

    public void saveTicket(Ticket ticket, ScreeningRoom screenRoom) throws SQLException {
    String sql = "INSERT INTO Tickets (movie_name, seat_row, seat_column, screen_room, booking_date) VALUES ( ?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
       
        pstmt.setString(1, ticket.getmovie().gettitle_of_movie());
        pstmt.setInt(2, ticket.getSeat_select().getSelected_row());
        pstmt.setInt(3, ticket.getSeat_select().getSelected_column());
        pstmt.setInt(4, ticket.getShowtime().getaudi().getAuditoriumID()); 
        pstmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
