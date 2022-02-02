package application;

import java.sql.Connection;
import db.DB;
import db.DbException;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Connection conn = null;
        Statement st = null;
        String comand = null;

        System.out.println("\n* Press 'f' to exit.\n");

        System.out.print("Enter DB properties file path: ");
        String properties = sc.nextLine();

        DB.loadProperties(properties);

        System.out.println("DB properties loaded!\n");
        
        do{
            comand = sc.nextLine();
            
            if(comand.equals("f go;")){
                
                DB.closeConnection();
                
                System.exit(0);
            }
            
            int comandLength = comand.length();

            String equalString = comand.substring(comandLength - 4, comandLength);

            if (equalString.equals(" go;")) {
                try {
                    conn = DB.getConnection(properties);

                    st = conn.createStatement();

                    st.execute(comand.substring(0, comandLength - 4));

                    System.out.println("Comando realizado com sucesso!");
                    
                } catch (SQLException e) {
                    throw new DbException("Error executing command:" + e.getMessage());
                }
            } else {
                System.out.println("Error executing command: missing 'go;'");
            }
            
        }while(true);

    }
}

