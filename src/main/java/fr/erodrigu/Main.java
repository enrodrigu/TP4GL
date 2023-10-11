package fr.erodrigu;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;

        try
        {
            // create a database connection by specifying an absolute or relative path for SQLite, or the server address otherwise
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/banque.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            System.out.println("OUT 1");

            ResultSet rs = statement.executeQuery("select count(*) as nb from person");
            while(rs.next())
            {
                // read the result set
                System.out.println("nb personne = " + rs.getInt("nb"));
            }

            System.out.println("OUT 2");

            rs = statement.executeQuery("select count(*) as nb from compte");
            while(rs.next())
            {
                // read the result set
                System.out.println("nb comptes = " + rs.getInt("nb"));
            }

            System.out.println("OUT 3");

            rs = statement.executeQuery("SELECT personne_nom,personne_prenom,sum(compte_solde) " +
                    "FROM personne JOIN compte ON compte_titulaire=idpersonne\n" +
                    "GROUP BY idpersonne\n" +
                    "LIMIT 10;");
            while(rs.next())
            {
                // read the result set
                System.out.println(rs.getString("personne_nom") + "\t" + rs.getString("personne_prenom") + "\t" + rs.getInt("sum(compte_solde)"));
            }

            System.out.println("OUT 4");

            rs = statement.executeQuery("SELECT personne_nom,personne_prenom,sum(compte_solde)\n" +
                    "FROM personne JOIN compte ON compte_titulaire=idpersonne\n" +
                    "GROUP BY idpersonne\n" +
                    "ORDER BY sum(compte_solde) DESC\n" +
                    "LIMIT 3;");
            while(rs.next())
            {
                // read the result set
                System.out.println(rs.getString("personne_nom") + "\t" + rs.getString("personne_prenom") + "\t" + rs.getInt("sum(compte_solde)"));
            }

            System.out.println("OUT 5");

            rs = statement.executeQuery("SELECT personne_nom,sum(compte_solde)\n" +
                    "FROM personne JOIN compte ON compte_titulaire=idpersonne\n" +
                    "WHERE idpersonne=1\n" +
                    "GROUP BY idpersonne");
            while(rs.next())
            {
                // read the result set
                System.out.println(rs.getString("personne_nom") + "\t" + "\t" + rs.getInt("sum(compte_solde)"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}