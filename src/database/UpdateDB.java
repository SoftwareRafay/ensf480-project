package database;

import Entity.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            saveTicket();
        } catch (SQLException e) {
            System.err.println("Error updating database: " + e.getMessage());
        }
    }

    public void writeBankInfo() throws SQLException {
        String insertQuery = """
            INSERT INTO BankInfo (id, holder_name, account_number)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE
                holder_name = VALUES(holder_name),
                account_number = VALUES(account_number)
        """;
    
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
    
            for (RegisteredUser r : databaseController.getlist_of_users()) {
                UserBankInfo bankInfo = r.getBankInfo();
                preparedStatement.setInt(1, bankInfo.getID_for_bank());
                preparedStatement.setString(2, bankInfo.getName_of_the_customer());
                preparedStatement.setString(3, bankInfo.getpayment_card_number());
    
                preparedStatement.addBatch(); // Add the current BankInfo to the batch
            }
    
            preparedStatement.executeBatch(); // Execute all batch updates
        }
    }
    

    public void writeRegisteredUser() throws SQLException {
        writeBankInfo();
        String insertQuery = """
            INSERT INTO RegisteredUsers (id, username, password, email, first_name, last_name, bank_info_id, Day, Month, Year)
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
                preparedStatement.setString(5, r.getfirst_name());
                preparedStatement.setString(6, r.getlast_name());
                preparedStatement.setString(4, r.getgmail_of_user());
                preparedStatement.setInt(7, r.getBankInfo().getID_for_bank());
                preparedStatement.setInt(8, r.getdate_of_feeDeposit().getDay());
                preparedStatement.setInt(9, r.getdate_of_feeDeposit().getMonth());
                preparedStatement.setInt(10, r.getdate_of_feeDeposit().getYear());

                preparedStatement.addBatch(); // Add the current user to the batch
            }

            preparedStatement.executeBatch(); // Execute all batch updates
        }
    }

    public void saveTicket() throws SQLException {
    String sql = "INSERT INTO Tickets (ticket_id, movie_id, seat_row, seat_column, show_time, booking_date) VALUES ( ?, ?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (Ticket r : databaseController.getlist_of_tickets()) {
                preparedStatement.setInt(1, r.getID_of_ticket());
                preparedStatement.setInt(2, r.getmovie().getID_of_movie());
                preparedStatement.setInt(3, r.getSeat_select().getSelected_row());
                preparedStatement.setInt(4, r.getSeat_select().getSelected_column());
                preparedStatement.setInt(5, r.getShowtime().getID_for_showtime());
                preparedStatement.setDate(6, java.sql.Date.valueOf(LocalDate.now()));
                

                preparedStatement.addBatch(); 
            }

            preparedStatement.executeBatch(); 
        }
}

public void removeTicketFromDatabase(int ticketId) {
    String fetchQuery = """
        SELECT movie_id, seat_row, seat_column, show_time 
        FROM Tickets 
        WHERE ticket_id = ?
    """;
    String deleteQuery = "DELETE FROM Tickets WHERE ticket_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement fetchStmt = connection.prepareStatement(fetchQuery);
         PreparedStatement deleteStmt = connection.prepareStatement(deleteQuery)) {

        // Fetch ticket details
        fetchStmt.setInt(1, ticketId);
        ResultSet rs = fetchStmt.executeQuery();

        if (rs.next()) {
            int movieId = rs.getInt("movie_id");
            int row = rs.getInt("seat_row");
            int col = rs.getInt("seat_column");
            int showTime = rs.getInt("show_time");

            // Fetch the Showtime object
            Showtime show = databaseController.searchShowtimeInfo(showTime);

            if (show != null) {
                // Mark the seat as available
                Seat seat = show.getSeats()[row][col];
                seat.setSeat_booked_or_not(false);
            }
        }

        // Delete the ticket from the database
        deleteStmt.setInt(1, ticketId);
        deleteStmt.executeUpdate();

    } catch (SQLException e) {
        System.err.println("Error removing ticket: " + e.getMessage());
    }
}


public void addVoucherToDatabase(String voucherCode, double value, String userType) {
    String query = "INSERT INTO Vouchers (voucher_code, value, user_type) VALUES (?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {
        ps.setString(1, voucherCode);
        ps.setDouble(2, value);
        ps.setString(3, userType);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Error adding voucher to database: " + e.getMessage());
    }
}

public void removeVoucher(String voucherCode) {
        String query = "DELETE FROM vouchers WHERE voucher_code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, voucherCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

