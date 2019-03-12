package sk.itsovy.rodcverifier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.DropMode.INSERT;

public class Database {

    private final String username = "adam";
    private final String password = "adam";
    private final String host =  "jdbc:mysql://localhost:3306/db";

    private Connection getConnection()
    {
        Connection connection;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(host, username, password);
            return connection;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewPerson(Person person)
    {
        Connection con = getConnection();
        try
        {
            PreparedStatement stat = con.prepareStatement("INSERT INTO person(FirstName, LastName, BirthDate, rc) values(?,?,?,?)");
            stat.setString(1, person.getFname());
            stat.setString(2, person.getLname());
            stat.setString(3, new Date(person.getDob().getTime()).toString());
            stat.setString(4, person.getPin());

            int result = stat.executeUpdate();
            closeConnection(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void selectPerson(Person person) {
        Connection con = getConnection();
        try
        {
            String query = "SELECT * FROM person Where LastName like '"+person.getLname()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date date = rs.getDate("BirthDate");
                String rc = rs.getString("rc");


                // print the results
                System.out.format("%s, %s, %s, %s\n", firstName, lastName, date, rc);
            }

            closeConnection(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void selectPersonByPin(String pin) {
        Connection con = getConnection();
        try
        {
            String query = "SELECT * FROM person Where rc like '"+pin+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date date = rs.getDate("BirthDate");
                String rc = rs.getString("rc");


                // print the results
                System.out.format("%s, %s, %s, %s\n", firstName, lastName, date, rc);
            }

            closeConnection(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void selectCountWomen() {
        Connection con = getConnection();
        try
        {
            String query = "SELECT Count(*) AS pocet FROM person WHERE rc LIKE '__5%'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);


            System.out.println(rs.getInt("pocet"));

            closeConnection(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<Person> selectAllMen() {
        Connection con = getConnection();
        String query = "SELECT * FROM person WHERE rc LIKE '__0%' OR rc LIKE '__1%'";
        List<Person> persons = new ArrayList<>();
        ResultSet rs;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                Date date = rs.getDate("BirthDate");
                String rc = rs.getString("rc");
                Person p = new Person(lastName,firstName,date,rc);
                persons.add(p);
                closeConnection(con);
            }else {
                closeConnection(con);
                return  null;
            }
        }catch(SQLException e) {
             e.printStackTrace();
        }

        return persons;
    }

    private void closeConnection(Connection conn){
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
