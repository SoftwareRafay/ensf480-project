DROP DATABASE IF EXISTS Group05;
CREATE DATABASE IF NOT EXISTS Group05;
USE Group05;

CREATE TABLE BankName (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE BankInfo (
    id INT PRIMARY KEY,
    holder_name VARCHAR(255),
    account_number VARCHAR(20)
);

CREATE TABLE Theatres (
    id INT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE ScreeningRooms (
    id INT PRIMARY KEY,
    `rows` INT,
    `columns` INT,
    theatre_id INT,
    FOREIGN KEY (theatre_id) REFERENCES Theatres(id)
);


CREATE TABLE Movies (
    id INT PRIMARY KEY,
    title VARCHAR(255),
    genre VARCHAR(255),
    year INT,
    director VARCHAR(255),
    duration DECIMAL(5,2),
    rating DECIMAL(3,1),
    code VARCHAR(10),
    price DECIMAL(5,2),
    description TEXT
);

CREATE TABLE Showtimes (
    ShowtimeID INT PRIMARY KEY,
    MovieID INT,
    RoomID INT,
    Day INT,
    Month INT,
    Year INT,
    Hour INT,
    Minute INT,
    FOREIGN KEY (MovieID) REFERENCES Movies(id),
    FOREIGN KEY (RoomID) REFERENCES ScreeningRooms(id)
);

CREATE TABLE Announcements (
    id INT PRIMARY KEY,
    Start_Day INT,
    StartMonth INT,
    StartYear INT,
    EndDay INT,
    EndMonth INT,
    EndYear INT,
    MovieID INT,
    FOREIGN KEY (MovieID) REFERENCES Movies(id)
);

CREATE TABLE RegisteredUsers (
    id INT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    email VARCHAR(255),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    bank_info_id INT,
    Day INT,
    Month INT,
    Year INT,
    FOREIGN KEY (bank_info_id) REFERENCES BankInfo(id)
);

CREATE TABLE Tickets (
    ticket_id INT PRIMARY KEY, 
    movie_id int,        
    seat_row INT NOT NULL,                   
    seat_column INT NOT NULL,                
    show_time int,       
    booking_date DATE NOT NULL,             
	FOREIGN KEY (show_time) REFERENCES Showtimes (ShowtimeID),
	FOREIGN KEY (movie_id) REFERENCES Movies(id)
);

CREATE TABLE Vouchers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    voucher_code VARCHAR(255) NOT NULL UNIQUE,
    value DECIMAL(10, 2) NOT NULL,
    user_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



-- Inserting into BankName table
INSERT INTO BankName (name) VALUES ('Fake Bank');

-- Inserting into BankInfo table
INSERT INTO BankInfo (id, holder_name, account_number)
VALUES 
(100, 'Jon Smith', '12345'),
(101, 'Chris Lee', '67891'),
(102, 'Michael James', '999999'),
(103, 'William Richard', '1011121314'),
(104, 'Hi Rez', '123456');
-- Inserting into Theatres table
INSERT INTO Theatres (id, name)
VALUES 
(400, 'Cineplex Crowfoot'),
(401, 'Cineplex University District');

-- Inserting into ScreeningRooms table
INSERT INTO ScreeningRooms (id, `rows`, `columns`, theatre_id )
VALUES 
(300, 8, 7, 400),
(301, 6, 6, 400),
(302, 7, 8, 401),
(303, 6, 8, 401);

-- Inserting into Movies table
INSERT INTO Movies (id, Title, Genre, Year, Director, Duration, Rating, code, Price, Description)
VALUES 
(200, 'Venom: The Last Dance', 'Action', 2024, 'Kelly Marcel', 109, 6.2, 've', 25, 'Eddie Brock and Venom must make a devastating decision as they\'re pursued by a mysterious military man and alien monsters from Venom\'s home world.'),
(201, 'Moana 2', 'Animation', 2024, 'Dave Jr. Derrick', 100, 7.2, 'mo', 25, 'Moana journeys to the far seas of Oceania after receiving an unexpected call from her wayfinding ancestors.'),
(202, 'Wicked', 'Musical', 2024, 'John M. Chu', 107.0, 8.2, 'wi', 25, 'Misunderstood because of her green skin, a young woman named Elphaba forges an unlikely but profound friendship with Glinda, a student with an unflinching desire for popularity.'),
(203, 'Red One', 'Action, Adventure, Comedy, Family', 2024, 'Jake Kasdan', 123.0, 6.9, 'ro', 25, 'When a villain kidnaps Santa Claus from the North Pole, an E.L.F. (Extremely Large and Formidable) operative joins forces with the world\'s most accomplished tracker to find him and save Christmas.');

-- Inserting into Announcements table
INSERT INTO Announcements (id, Start_Day, StartMonth, StartYear, EndDay, EndMonth, EndYear, MovieID)
VALUES 
(800, 29, 11, 2023, 11, 11, 2024, 200),
(801, 11, 18, 2023, 18, 11, 2024, 201),
(802, 20, 11, 2023, 7, 10, 2024, 202),
(803, 15, 11, 2023, 3, 10, 2024, 203);

-- Inserting into Showtimes table
INSERT INTO Showtimes (ShowtimeID, MovieID, RoomID, Day, Month, Year, Hour, Minute)
VALUES 
(700, 200, 300, 15, 12, 2024, 7, 15),
(701, 201, 301, 17, 12, 2024, 8, 30),
(702, 202, 302, 16, 12, 2024, 6, 45),
(703, 203, 303, 18, 12, 2024, 14, 15),
(704, 203, 300, 23, 12, 2024, 12, 30),
(705, 202, 301, 25, 12, 2024, 17, 15),
(706, 201, 302, 27, 12, 2024, 20, 15),
(707, 200, 303, 29, 12, 2024, 20, 45),
(708, 200, 300, 31, 12, 2024, 14, 15),
(709, 201, 301, 11, 12, 2024, 16, 35),
(710, 201, 302, 31, 12, 2024, 9, 05),
(711, 200, 303, 28, 12, 2024, 9, 45);

-- Inserting data into RegisteredUsers table
INSERT INTO RegisteredUsers (id, username, password, email, first_name, last_name, bank_info_id, Day, Month, Year)
VALUES 
(0, 'J.smith', 'pwd', 'a@gmail.com', 'Jon', 'Smith', 100, 3, 12, 2024),
(1, 'C.Lee', 'pwd', 'f@gmail.com', 'Chris', 'Lee', 101, 2, 12, 2024),
(2, 'M.James', 'pwd', 'r@gmail.com', 'Michael', 'James', 102, 3, 12, 2024),
(3, 'W.Richard', 'pwd', 'h@gmail.com', 'William', 'Richard', 103, 4, 7, 2024),
(4, 'Hi.Rez', '123', 'hirez2277@gmail.com', 'Hi', 'Rez', 104, 4, 7, 2024);



