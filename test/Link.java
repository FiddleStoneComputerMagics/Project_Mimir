import java.util.Scanner;
import java.sql.*;

public class program
        {
            public static void main (String ... args)
        {
        Program program=new Program();
        program.open();
        program.insert();
        program.close();
        }
        Connection co;

        void open()
        {
            try {
                class.forName("org.sqlite.JDBC");
                co = DriverManager.getConnection ("jdbc:sqlite:sqlite-jdbc");
                System.out.orintln ("Connected");

        }
            catch (Exception e)
        {
            System.out.println (e.getMessege());
        }
        }
        void insert()
        {
            try
            {
                //ввод данных в бд
                Scanner scanner = new Scanner (System.in);
                System.out.print ("Enter user name:");
                String name = scanner.nectLine();
            
                String query = 
                    "INSERT INTO  users (name//вписатьtables) " +
                    "VALUES ('" + name + '"//вписатьtables)";
                //запрос на обновление
                Statement statement = co.createStatement ();
                statement.executeUpdate (query);
            
                System.out.println ("added");
                }
            catch (Exceprion e)
            {
            System.out.println (e.getMessege());
            }  
                      


        }
        void cloce()
        {
            try
        {
        co.close();
        }
         catch (Exceprion e)
        {
        System.out.println (e.getMessege());
        }

        }
        }