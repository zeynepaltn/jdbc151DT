public class Runner {
    public static void main(String[] args) {
        //database'e baglan

        JdbcUtils.connectToDataBase();

        //Statement olustur
        JdbcUtils.createStatement();

        //Query calistir
        String sql="CREATE TABLE workers(worker_id VARCHAR(20), worker_name VARCHAR (20), worker_salary INT)";
        JdbcUtils.execute(sql);
    }
}
