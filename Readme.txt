# ENSF 480 Group 05 Movie App Project


## How to Download and Run the Project


Follow the steps below to download, set up, and run the project:


### 1. Download Required Files
- Download the following files:
  - `Group05.jar`
  - `Group05.zip`
  - `Group05.sql`


### 2. Extract the Zip File
- Extract the contents of `Group05.zip` to any location on your computer.
- Open the extracted folder in VS Code.


### 3. Place the JAR File
- Move the `Group05.jar` file into the `bin` folder of the extracted project.


### 4. Configure Database Credentials
- Navigate to the `src/database` folder and open the `DatabaseConnection.java` file.
- Update the database credentials (`username`, `password`, etc.) in the file to match your local MySQL database setup.


### 5. Set Up the Database
- Open your MySQL client or command line tool.
- Import the database by running the `Group05.sql` file.


### 6. Compile the Files Using the Command:
- javac -d bin -cp "lib/*" src/Boundary/*.java src/Control/*.java src/Database/*.java src/Entity/*.java


### 7. Run the Program
- After successful compilation, run the program using the following command:


**For Windows:**
java -cp "bin;lib/*" Control.GUIController


**For Mac:**
java -cp "bin:lib/*" Control.GUIController