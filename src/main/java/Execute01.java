import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
   //1. Adim : Driver'a kayit olalim ===>JDBC 4 sonrasi gerek kalmamistir sadece genel kultur olarak ogrendik
   Class.forName("org.postgresql.Driver");

   //2. Adim: Database'e baglan
       Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","208518913");

   //3.Adim: Statement olustur
        Statement statement=connection.createStatement();
   //4. Adim:Query calistir
        //1. ornek create table workers.
   boolean sql1=statement.execute("CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT);");
         /*
        1) Eğer execute() methodu DDL (create, drop, alter table) ile kullanılırsa her zaman 'false' döner.
        2) Eğer execute() methodu DQL (select) ile kullanılırsa data dönerse 'true', data dönmezse 'false' döner.
         */
       //execute() methodu parametre olarak girilen String SQL komutunu bagli oldugu database uzerinde uygular

        System.out.println("sql1= "+sql1);

        //2. Ornek: workers table'ina adres column'u ekleyin
   String sqlQuery1="ALTER TABLE workers ADD worker_address VARCHAR(100); ";
   boolean sql2=statement.execute(sqlQuery1);
        System.out.println("sql2= "+sql2);


        //3. Ornek: worker table'i siliniz
        String sqlQuery2="DROP TABLE workers";
        boolean sql3=statement.execute(sqlQuery2);
        System.out.println("sql3= "+sql3);

        //5. Adim: Baglantiyi Kapat
        connection.close();
        statement.close();


    }//main


}//class
