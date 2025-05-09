import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    public static Connection conectar() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/SunPDV";
        String user = "seunome";    
        String pass = "suasenha";   

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) {
        try (Connection conexao = conectar()) {
            System.out.println("Conexão bem-sucedida!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
