/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

 package Boundary;

import Entity.*;
import database.ReadDB;
import database.UpdateDB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.MatteBorder;

public class MainView extends JPanel {

    private JLabel logoutButton;
    private JLabel logo;

    private JLabel cartButton;

    private JLabel annualFeeButton;

    private JLabel cancelTicketButton;

    // Seat-related components
    private JTextField rowTextField;
    private JTextField colTextField;
    private JLabel selectRowLabel;
    private JLabel selectColLabel;
    private JLabel invalidSeatErrorLabel;
    private JLabel takenSeatErrorLabel;
    private JLabel selectedSeatErrorLabel;
    private JLabel addedToCartLabel;
    private JTextArea seatGraphicLabel;
    private JLabel AddToCartButton;
    private JLabel selectSeatLabel;
    private JLabel SeatLabel;

    private JLabel selectShowtimeLabel;
    private JTextArea showtimeDetailsLabel;
    private JComboBox showtimeSelectComboBox;

    private JLabel selectTheatreLabel;
    private JComboBox theatreSelectComboBox;

    private JTextField voucherTextField;
    private JLabel voucherButton;
    private JLabel voucherLabel;

    private JLabel selectMovieLabel;
    private JLabel MovieLabel;

    private JTextArea movieDetailsLabel;

    private JTextArea movieSynopsisLabel;

    private JLabel posterCard;

    private JComboBox movieSelectComboBox;

    private JLabel userLabel;

    private JLabel homeBackground;

    private JPanel seatPanel;

    private Movie currentMovie;

    private Theatre currentTheatre;

    private Showtime currentShowtime;

    public MainView(JFrame frame, Login login) {
        setLayout(null);

        logo = new JLabel("");
        logo.setIcon(new ImageIcon(LoginView.class.getResource("/logo.png")));
        logo.setBounds(400, 5, 480, 90);
        add(logo);

        // Initialize seatPanel
        seatPanel = new JPanel();
        seatPanel.setLayout(null);
        seatPanel.setBounds(600, 250, 600, 500);
        //Added this for seat visibility
        seatPanel.setBackground(new Color(20, 20, 20)); // Very dark gray color
        seatPanel.setVisible(false); // Initially hidden
        add(seatPanel);

        // Row Text Field
        rowTextField = new JTextField();
        rowTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        rowTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        rowTextField.setForeground(Color.WHITE);
        rowTextField.setBackground(Color.GRAY);
        rowTextField.setBounds(400, 110, 50, 28);
        rowTextField.setOpaque(true);
        rowTextField.setColumns(10);
        rowTextField.setVisible(false);
        seatPanel.add(rowTextField);

        selectRowLabel = new JLabel("Enter Row (A-Z): ");
        selectRowLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectRowLabel.setForeground(Color.WHITE);
        selectRowLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        selectRowLabel.setBounds(400, 70, 200, 45);
        selectRowLabel.setVisible(false);
        seatPanel.add(selectRowLabel);

        // Column Text Field
        colTextField = new JTextField();
        colTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        colTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        colTextField.setForeground(Color.WHITE);
        colTextField.setBackground(Color.GRAY);
        colTextField.setBounds(400, 190, 50, 28);
        colTextField.setOpaque(true);
        colTextField.setColumns(10);
        colTextField.setVisible(false);
        seatPanel.add(colTextField);

        selectColLabel = new JLabel("Enter Seat (1-N): ");
        selectColLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectColLabel.setForeground(Color.WHITE);
        selectColLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        selectColLabel.setBounds(400, 150, 200, 45);
        selectColLabel.setVisible(false);
        seatPanel.add(selectColLabel);

        invalidSeatErrorLabel = new JLabel("<html>Invalid Row or Seat Number was Selected</html>");
        invalidSeatErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        invalidSeatErrorLabel.setForeground(Color.RED);
        invalidSeatErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        invalidSeatErrorLabel.setBounds(400, 290, 250, 45);
        invalidSeatErrorLabel.setVisible(false);
        seatPanel.add(invalidSeatErrorLabel);

        selectedSeatErrorLabel = new JLabel("<html>The Seat Selected is already Selected</html>");
        selectedSeatErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectedSeatErrorLabel.setForeground(Color.RED);
        selectedSeatErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        selectedSeatErrorLabel.setBounds(400, 340, 250, 45);
        selectedSeatErrorLabel.setVisible(false);
        seatPanel.add(selectedSeatErrorLabel);

        takenSeatErrorLabel = new JLabel("<html>The Seat Selected is already Booked</html>");
        takenSeatErrorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        takenSeatErrorLabel.setForeground(Color.RED);
        takenSeatErrorLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        takenSeatErrorLabel.setBounds(400, 390, 250, 45);
        takenSeatErrorLabel.setVisible(false);
        seatPanel.add(takenSeatErrorLabel);

        addedToCartLabel = new JLabel("<html>Added To Cart</html>");
        addedToCartLabel.setHorizontalAlignment(SwingConstants.LEFT);
        addedToCartLabel.setForeground(Color.GREEN);
        addedToCartLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        addedToCartLabel.setBounds(400, 440, 192, 45);
        addedToCartLabel.setVisible(false);
        seatPanel.add(addedToCartLabel);

        seatGraphicLabel = new JTextArea("");
        seatGraphicLabel.setForeground(Color.WHITE);
        seatGraphicLabel.setFont(new Font("Courier New", Font.PLAIN, 15));
        seatGraphicLabel.setBounds(50, 70, 300, 400);
        seatGraphicLabel.setVisible(false);
        seatGraphicLabel.setLineWrap(true);
        seatGraphicLabel.setWrapStyleWord(true);
        seatGraphicLabel.setOpaque(false);
        seatGraphicLabel.setEditable(false);
        seatPanel.add(seatGraphicLabel);

        AddToCartButton = new JLabel("Add To Cart");
        AddToCartButton.setForeground(Color.WHITE);
        AddToCartButton.setBackground(Color.GRAY);
        AddToCartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        AddToCartButton.setBackground(new Color(255, 140, 0));
        AddToCartButton.setOpaque(true);
        AddToCartButton.setHorizontalAlignment(SwingConstants.CENTER);
        AddToCartButton.setBounds(400, 230, 100, 50);
        AddToCartButton.setVisible(false);
        seatPanel.add(AddToCartButton);

        AddToCartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((login.getCurrentUser().getreg_or_unreg_user().compareTo("Registered") == 0
                        && login.getCurrentRegisteredUser().isFeePayed())
                        || login.getCurrentUser().getreg_or_unreg_user().compareTo("Guest") == 0) {
                    try {
                        // Parse the row input as a letter and convert it to an index
                        String rowInput = rowTextField.getText().toUpperCase();
                        if (rowInput.length() != 1 || rowInput.charAt(0) < 'A' || rowInput.charAt(0) > 'Z') {
                            invalidSeatErrorLabel.setVisible(true);
                            return;
                        }
                        int userRow = rowInput.charAt(0) - 'A';
        
                        // Parse the column input and adjust for 1-based indexing
                        int userCol = Integer.parseInt(colTextField.getText()) - 1;
        
                        invalidSeatErrorLabel.setVisible(false);
                        takenSeatErrorLabel.setVisible(false);
                        addedToCartLabel.setVisible(false);
                        selectedSeatErrorLabel.setVisible(false);
        
                        // Validate userRow and userCol
                        if (userRow >= 0 && userRow < currentShowtime.getnumber_of_rows()
                                && userCol >= 0 && userCol < currentShowtime.getnumber_of_columns()) {
                            boolean seatBooked = currentShowtime.getSeatAvaliability(userRow, userCol);
                            if (!seatBooked) {
                                // Seat is available
                                Seat seat = currentShowtime.getSeats()[userRow][userCol];
                                if (seat.isReservedForRegisteredUsers() && login.getCurrentUser().getreg_or_unreg_user().compareTo("Guest") == 0) {
                                    JOptionPane.showMessageDialog(frame, "This seat is reserved for registered users.",
                                            "Seat Selection Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
        
                                boolean selectFlag = false;
                                for (int k = 0; k < login.getCurrentUser().getCart().getitems().size(); k++) {
                                    if (login.getCurrentUser().getCart().getitems().get(k).getseat_to_book()
                                            .getSelected_row() == userRow
                                            && login.getCurrentUser().getCart().getitems().get(k)
                                                    .getseat_to_book().getSelected_column() == userCol
                                            && login.getCurrentUser().getCart().getitems().get(k)
                                                    .getshowing_time()
                                                    .getID_for_showtime() == currentShowtime.getID_for_showtime()) {
                                        selectedSeatErrorLabel.setVisible(true);
                                        selectFlag = true;
                                        break;
                                    }
                                }
                                if (!selectFlag) {
                                    login.getCurrentUser().getCart().addToCart(new Reservation(currentMovie,
                                            currentShowtime, seat));
                                    addedToCartLabel.setVisible(true);
                                    createSeatGraphic(frame, login);
                                }
                            } else {
                                // Seat is already booked
                                takenSeatErrorLabel.setVisible(true);
                            }
                        } else {
                            invalidSeatErrorLabel.setVisible(true);
                        }
                        frame.revalidate();
                    } catch (NumberFormatException f) {
                        invalidSeatErrorLabel.setVisible(true);
                        System.out.println(f);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You must pay the $20 Annual Fee for Registered Users.",
                            "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        

        SeatLabel = new JLabel("________________________________");
        SeatLabel.setHorizontalAlignment(SwingConstants.LEFT);
        SeatLabel.setForeground(Color.WHITE);
        SeatLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        SeatLabel.setBounds(150, 25, 300, 45);
        SeatLabel.setVisible(false);
        seatPanel.add(SeatLabel);

        selectSeatLabel = new JLabel("Screen");
        selectSeatLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectSeatLabel.setForeground(Color.WHITE);
        selectSeatLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        selectSeatLabel.setBounds(250, 10, 254, 45);
        selectSeatLabel.setVisible(false);
        seatPanel.add(selectSeatLabel);

        // Voucher components from the first code
        voucherLabel = new JLabel("Use a voucher");
        voucherLabel.setHorizontalAlignment(SwingConstants.LEFT);
        voucherLabel.setForeground(Color.WHITE);
        voucherLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        voucherLabel.setBounds(450, 140, 254, 45);
        add(voucherLabel);

        voucherButton = new JLabel("Redeem");
        voucherButton.setForeground(Color.WHITE);
        voucherButton.setBackground(Color.GRAY);
        voucherButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        voucherButton.setBackground(new Color(255, 140, 0));
        voucherButton.setOpaque(true);
        voucherButton.setHorizontalAlignment(SwingConstants.CENTER);
        voucherButton.setBounds(500, 220, 80, 20);
        add(voucherButton);

        voucherTextField = new JTextField();
        voucherTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        voucherTextField.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        voucherTextField.setForeground(Color.WHITE);
        voucherTextField.setBackground(Color.GRAY);
        voucherTextField.setBounds(450, 180, 200, 28);
        voucherTextField.setOpaque(true);
        voucherTextField.setColumns(10);
        add(voucherTextField);

        voucherButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String voucherCode = voucherTextField.getText();

                if (voucherCode == null || voucherCode.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame,
                            "Please enter a valid voucher code.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                voucherCode = voucherCode.trim();
                ReadDB dbReader = new ReadDB();
                UpdateDB dbUpdater = new UpdateDB();

                // Get the voucher value from the database
                Double voucherValue = dbReader.getVoucherValue(voucherCode);

                if (voucherValue != null) {
                    // Set the voucher value in the user's cart
                    login.getCurrentUser().getCart().setVoucherValue(voucherValue);

                    // Remove the voucher from the database
                    try {
                        dbUpdater.removeVoucher(voucherCode);

                        JOptionPane.showMessageDialog(frame,
                                "Voucher redeemed successfully! $" + String.format("%.2f", voucherValue)
                                + " will be applied to your total.",
                                "Voucher Redeemed",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame,
                                "Voucher redeemed, but there was an issue removing it from the database.",
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "The voucher code entered does not exist or is invalid.",
                            "Invalid Voucher",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        selectShowtimeLabel = new JLabel("Show Times");
        selectShowtimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectShowtimeLabel.setForeground(Color.white);
        selectShowtimeLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        selectShowtimeLabel.setBounds(1000, 140, 254, 45);
        selectShowtimeLabel.setVisible(false);
        add(selectShowtimeLabel);

        showtimeDetailsLabel = new JTextArea("");
        showtimeDetailsLabel.setForeground(Color.white);
        showtimeDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        showtimeDetailsLabel.setBounds(1000, 215, 254, 100);
        showtimeDetailsLabel.setVisible(false);
        showtimeDetailsLabel.setLineWrap(true);
        showtimeDetailsLabel.setWrapStyleWord(true);
        showtimeDetailsLabel.setOpaque(false);
        showtimeDetailsLabel.setEditable(false);
        add(showtimeDetailsLabel);

        showtimeSelectComboBox = new JComboBox(new String[0]);
        showtimeSelectComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        showtimeSelectComboBox.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        showtimeSelectComboBox.setForeground(Color.white);
        showtimeSelectComboBox.setBackground(Color.gray);
        showtimeSelectComboBox.setBounds(1000, 180, 200, 28);
        showtimeSelectComboBox.setOpaque(true);
        showtimeSelectComboBox.setVisible(false);

        showtimeSelectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempString = (String) showtimeSelectComboBox.getSelectedItem();
                invalidSeatErrorLabel.setVisible(false);
                takenSeatErrorLabel.setVisible(false);
                addedToCartLabel.setVisible(false);
                selectedSeatErrorLabel.setVisible(false);
                if (tempString != null) {
                    String[] tempStringArray = tempString.split("/");
                    Date tempDate = new Date(Integer.parseInt(tempStringArray[0]), Integer.parseInt(tempStringArray[1]),
                            Integer.parseInt(tempStringArray[2]));
                    currentShowtime = login.getDataController().findShowtime(currentMovie, currentTheatre, tempDate);
                    if (currentShowtime != null) {
                        showtimeDetailsLabel
                                .setText("Time: " + currentShowtime.gettime_in_hours() + ":" + currentShowtime.gettime_in_minutes());

                        showtimeDetailsLabel.setVisible(true);

                        // Show seatPanel and its components
                        seatPanel.setVisible(true);
                        selectSeatLabel.setVisible(true);
                        SeatLabel.setVisible(true);
                        seatGraphicLabel.setVisible(true);
                        selectRowLabel.setVisible(true);
                        rowTextField.setVisible(true);
                        selectColLabel.setVisible(true);
                        colTextField.setVisible(true);
                        AddToCartButton.setVisible(true);

                        createSeatGraphic(frame, login);
                    } else {
                        showtimeDetailsLabel.setVisible(false);
                        seatPanel.setVisible(false);
                    }
                } else {
                    showtimeDetailsLabel.setVisible(false);
                    seatPanel.setVisible(false);
                }
            }
        });
        add(showtimeSelectComboBox);

        selectTheatreLabel = new JLabel("Theatres");
        selectTheatreLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectTheatreLabel.setForeground(Color.white);
        selectTheatreLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        selectTheatreLabel.setBounds(700, 140, 254, 45);
        selectTheatreLabel.setVisible(false);
        add(selectTheatreLabel);

        theatreSelectComboBox = new JComboBox(new String[0]);
        theatreSelectComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        theatreSelectComboBox.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        theatreSelectComboBox.setForeground(Color.white);
        theatreSelectComboBox.setBackground(Color.gray);
        theatreSelectComboBox.setBounds(700, 180, 200, 28);
        theatreSelectComboBox.setOpaque(true);
        theatreSelectComboBox.setVisible(false);

        theatreSelectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTheatre = login.getDataController()
                        .findTheatre((String) theatreSelectComboBox.getSelectedItem());

                if (currentTheatre != null) {

                    currentTheatre = login.getDataController()
                            .findTheatre((String) theatreSelectComboBox.getSelectedItem());
                    currentShowtime = null;
                    if (currentTheatre != null) {

                        DefaultComboBoxModel model2 = (DefaultComboBoxModel) showtimeSelectComboBox.getModel();
                        model2.removeAllElements();
                        ArrayList<Showtime> showtimeList = login.getDataController().findAllShowtime(currentMovie,
                                currentTheatre);
                        for (int i = 0; i < showtimeList.size(); i++) {
                            model2.addElement(showtimeList.get(i).getDate_of_show().toString());
                        }
                        showtimeSelectComboBox.setModel(model2);
                        showtimeSelectComboBox.setVisible(true);
                        // noShowTimeSelectedLabel.setVisible(true);
                        selectShowtimeLabel.setVisible(true);
                    }
                }
            }
        });
        add(theatreSelectComboBox);

        movieDetailsLabel = new JTextArea("");
        movieDetailsLabel.setForeground(Color.WHITE);
        movieDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        movieDetailsLabel.setBounds(273, 233, 123, 268);
        movieDetailsLabel.setVisible(false);
        movieDetailsLabel.setLineWrap(true);
        movieDetailsLabel.setWrapStyleWord(true);
        movieDetailsLabel.setOpaque(false);
        movieDetailsLabel.setEditable(false);
        add(movieDetailsLabel);

        movieSynopsisLabel = new JTextArea("");
        movieSynopsisLabel.setForeground(Color.WHITE);
        movieSynopsisLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        movieSynopsisLabel.setBounds(85, 525, 311, 134);
        movieSynopsisLabel.setVisible(false);
        movieSynopsisLabel.setLineWrap(true);
        movieSynopsisLabel.setWrapStyleWord(true);
        movieSynopsisLabel.setOpaque(false);
        movieSynopsisLabel.setEditable(false);
        add(movieSynopsisLabel);

        posterCard = new JLabel("");
        posterCard.setBounds(85, 235, 182, 268);
        posterCard.setVisible(true);
        add(posterCard);

        Vector<String> list_of_movies = new Vector<String>();
        for (int i = 0; i < login.getDataController().getlist_of_movies().size(); i++) {
            if (login.getCurrentRegisteredUser() != null) {
                if (!login.getDataController().getlist_of_movies().get(i).getMovieAnnouncement().getPrAnnounceDate()
                        .is_before_CurrentDate())
                    list_of_movies.add(login.getDataController().getlist_of_movies().get(i).gettitle_of_movie());
            } else {
                if (!login.getDataController().getlist_of_movies().get(i).getMovieAnnouncement().getPuAnnounceDate()
                        .is_before_CurrentDate())
                    list_of_movies.add(login.getDataController().getlist_of_movies().get(i).gettitle_of_movie());
            }
        }

        selectMovieLabel = new JLabel("Search Movies");
        selectMovieLabel.setHorizontalAlignment(SwingConstants.LEFT);
        selectMovieLabel.setForeground(Color.WHITE);
        selectMovieLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        selectMovieLabel.setBounds(85, 80, 150, 32);
        add(selectMovieLabel);

        JTextField searchBar = new JTextField();
        searchBar.setFont(new Font("Arial", Font.PLAIN, 15));
        searchBar.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        searchBar.setForeground(Color.WHITE);
        searchBar.setBackground(Color.GRAY);
        searchBar.setBounds(85, 110, 311, 28);
        searchBar.setOpaque(true);
        add(searchBar);

        MovieLabel = new JLabel("Or Select Movies");
        MovieLabel.setHorizontalAlignment(SwingConstants.LEFT);
        MovieLabel.setForeground(Color.WHITE);
        MovieLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        MovieLabel.setBounds(85, 140, 200, 32);
        add(MovieLabel);

        // Create a JList for showing recommendations
        DefaultListModel<String> movieListModel = new DefaultListModel<>();
        JList<String> movieSuggestionsList = new JList<>(movieListModel);
        movieSuggestionsList.setFont(new Font("Arial", Font.PLAIN, 15));
        movieSuggestionsList.setForeground(Color.WHITE);
        movieSuggestionsList.setBackground(Color.DARK_GRAY);
        movieSuggestionsList.setBounds(85, 170, 311, 100);
        movieSuggestionsList.setVisible(false);
        add(movieSuggestionsList);

        // Add action listener for search bar to filter movies
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchBar.getText().toLowerCase();
                movieListModel.clear(); // Clear previous results

                if (!query.isEmpty()) {
                    for (String movie : list_of_movies) {
                        if (movie.toLowerCase().contains(query)) {
                            movieListModel.addElement(movie);
                        }
                    }
                    movieSuggestionsList.setVisible(!movieListModel.isEmpty());
                } else {
                    movieSuggestionsList.setVisible(false);
                }
            }
        });

        // Add mouse listener for selecting a movie from suggestions
        movieSuggestionsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single click to select
                    String selectedMovie = movieSuggestionsList.getSelectedValue();
                    if (selectedMovie != null) {
                        searchBar.setText(selectedMovie);
                        movieSuggestionsList.setVisible(false);

                        // Update current movie selection
                        currentMovie = login.getDataController().findMovie(selectedMovie);
                        currentTheatre = null;
                        currentShowtime = null;
                        movieDetailsLabel.setText(currentMovie.gettitle_of_movie() + "\n\n" + currentMovie.getgenre_of_movie());
                        movieSynopsisLabel.setText(currentMovie.getSynopsis());

                        posterCard.setIcon(new ImageIcon(MainView.class.getResource("/" + currentMovie.getPoster() + ".jpg")));

                        movieDetailsLabel.setVisible(true);
                        selectTheatreLabel.setVisible(true);
                        posterCard.setVisible(true);
                        movieSynopsisLabel.setVisible(true);

                        DefaultComboBoxModel model = (DefaultComboBoxModel) theatreSelectComboBox.getModel();
                        model.removeAllElements();
                        ArrayList<Theatre> theatreList = login.getDataController().findTheatresPlayingMovie(currentMovie);
                        for (int i = 0; i < theatreList.size(); i++) {
                            model.addElement(theatreList.get(i).getname_of_theatre());
                        }
                        theatreSelectComboBox.setModel(model);
                        theatreSelectComboBox.setVisible(true);
                        frame.revalidate();
                    }
                }
            }
        });

        movieSelectComboBox = new JComboBox(list_of_movies);
        movieSelectComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        movieSelectComboBox.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.LIGHT_GRAY));
        movieSelectComboBox.setForeground(Color.WHITE);
        movieSelectComboBox.setBackground(Color.GRAY);
        movieSelectComboBox.setBounds(85, 180, 311, 28);
        movieSelectComboBox.setOpaque(true);

        movieSelectComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp = (String) movieSelectComboBox.getSelectedItem();

                currentMovie = login.getDataController().findMovie(temp);
                currentTheatre = null;
                currentShowtime = null;

                movieDetailsLabel.setText(currentMovie.gettitle_of_movie() + "\n\n" + currentMovie.getgenre_of_movie());
                movieSynopsisLabel.setText(currentMovie.getSynopsis());

                posterCard.setIcon(new ImageIcon(MainView.class.getResource("/" + currentMovie.getPoster() + ".jpg")));

                movieDetailsLabel.setVisible(true);
                selectTheatreLabel.setVisible(true);
                posterCard.setVisible(true);
                movieSynopsisLabel.setVisible(true);

                DefaultComboBoxModel model = (DefaultComboBoxModel) theatreSelectComboBox.getModel();
                model.removeAllElements();
                ArrayList<Theatre> theatreList = login.getDataController().findTheatresPlayingMovie(currentMovie);
                for (int i = 0; i < theatreList.size(); i++) {
                    model.addElement(theatreList.get(i).getname_of_theatre());
                }
                theatreSelectComboBox.setModel(model);
                theatreSelectComboBox.setVisible(true);
                frame.revalidate();
            }
        });
        add(movieSelectComboBox);

        cancelTicketButton = new JLabel("Cancel Ticket");
        cancelTicketButton.setForeground(Color.WHITE);
        cancelTicketButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelTicketButton.setFont(new Font("Arial", Font.BOLD, 15));
        cancelTicketButton.setBackground(Color.DARK_GRAY);
        cancelTicketButton.setOpaque(true);
        cancelTicketButton.setHorizontalAlignment(SwingConstants.CENTER);
        cancelTicketButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CancelView cancelTicketPage = new CancelView(frame, login);
                frame.setContentPane(cancelTicketPage);
                frame.revalidate();

            }
        });
        cancelTicketButton.setBounds(1100, 14, 120, 32);
        cancelTicketButton.setVisible(true);

        add(cancelTicketButton);

        logoutButton = new JLabel("Logout");
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.DARK_GRAY);
        logoutButton.setOpaque(true);
        logoutButton.setHorizontalAlignment(SwingConstants.CENTER);

        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login.logout_user();
                LoginView loginPanel = new LoginView(frame, login);
                frame.setContentPane(loginPanel);
                frame.revalidate();
            }
        });
        logoutButton.setBounds(30, 5, 70, 50);

        add(logoutButton);

        if (login.getCurrentUser().getreg_or_unreg_user().compareTo("Registered") == 0) {
            annualFeeButton = new JLabel("Pay Annual Fee");
            annualFeeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            annualFeeButton.setFont(new Font("Arial", Font.PLAIN, 15));
            annualFeeButton.setForeground(Color.WHITE);

            annualFeeButton.setHorizontalAlignment(SwingConstants.CENTER);
            annualFeeButton.setBackground(Color.DARK_GRAY);
            annualFeeButton.setOpaque(true);
            add(annualFeeButton);

            annualFeeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (!login.getCurrentRegisteredUser().getdate_of_feeDeposit().is_before_CurrentDate()) {

                        String name = login.getCurrentRegisteredUser().getfirst_name() + " "
                                + login.getCurrentRegisteredUser().getlast_name();

                        String cn = login.getCurrentRegisteredUser().getBankInfo().getpayment_card_number();

                        if (login.getDataController().getInst().CardInfo_verification(name, cn)) {
                            JOptionPane.showMessageDialog(null,
                                    "Annual Fee has been paid.",
                                    (String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
                            LocalDate today = LocalDate.now();
                            Date date = new Date(today.getDayOfMonth(), today.getMonthValue(), today.getYear() + 1);
                            login.getCurrentRegisteredUser().setdate_of_feeDeposit(date);
                        } else {
                            JOptionPane.showMessageDialog(null, "Annual Fee payment failed.",
                                    (String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Annual Fee is already paid.",
                                (String) "Annual Fee Payment", JOptionPane.INFORMATION_MESSAGE);
                    }

                    frame.revalidate();
                }
            });
            annualFeeButton.setBounds(915, 14, 160, 32);
            add(annualFeeButton);

        }

        cartButton = new JLabel("View Cart");
        cartButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartButton.setFont(new Font("Arial", Font.BOLD, 15));
        cartButton.setForeground(Color.WHITE);
        cartButton.setBackground(Color.DARK_GRAY);
        cartButton.setOpaque(true);
        cartButton.setHorizontalAlignment(SwingConstants.CENTER);

        cartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CartView cartPanel = new CartView(frame, login);
                frame.setContentPane(cartPanel);
                frame.revalidate();
            }
        });
        cartButton.setBounds(1250, 14, 90, 32);
        add(cartButton);

        // Set bg image
        homeBackground = new JLabel("");
        homeBackground.setBounds(-2, -1, 1366, 768);
        homeBackground.setIcon(new ImageIcon(LoginView.class.getResource("/bg2.jpg")));
        homeBackground.setHorizontalAlignment(SwingConstants.CENTER);
        add(homeBackground);
    }

    public void createSeatGraphic(JFrame frame, Login login) {
        StringBuilder tempGraphic = new StringBuilder();
        for (int i = 0; i < currentShowtime.getnumber_of_rows(); i++) {
            if (i == 0) {
                tempGraphic.append("     ");
                for (int j = 0; j < currentShowtime.getnumber_of_columns(); j++) {
                    tempGraphic.append(j + 1).append("  "); // Columns start from 1
                }
                tempGraphic.append("\n   __");
                for (int j = 0; j < currentShowtime.getnumber_of_columns(); j++) {
                    tempGraphic.append("___");
                }
                tempGraphic.append("\n");
            }
            for (int j = 0; j < currentShowtime.getnumber_of_columns(); j++) {
                if (j == 0) {
                    char rowLetter = (char) ('A' + i); // Convert row index to letter
                    tempGraphic.append(rowLetter).append("  | ");
                }
                Seat seat = currentShowtime.getSeats()[i][j];
    
                if (seat.isSeat_booked_or_not()) {
                    tempGraphic.append("X  ");
                } else {
                    boolean isSeatInCart = false;
                    for (Reservation res : login.getCurrentUser().getCart().getitems()) {
                        if (res.getseat_to_book().getSelected_row() == i
                                && res.getseat_to_book().getSelected_column() == j
                                && res.getshowing_time().getID_for_showtime() == currentShowtime.getID_for_showtime()) {
                            isSeatInCart = true;
                            break;
                        }
                    }
                    if (isSeatInCart) {
                        tempGraphic.append("/  ");
                    } else if (seat.isReservedForRegisteredUsers()) {
                        if (login.getCurrentUser().getreg_or_unreg_user().equals("Registered")) {
                            tempGraphic.append("o  ");
                        } else {
                            tempGraphic.append("R  ");
                        }
                    } else {
                        tempGraphic.append("o  ");
                    }
                }
            }
            tempGraphic.append("\n");
        }
        tempGraphic.append("o = Free, / = Selected, X = Taken, R = Reserved for Registered Users");
        seatGraphicLabel.setText(tempGraphic.toString());
        seatGraphicLabel.setVisible(true);
    }

    private static final long serialVersionUID = 1L;
}