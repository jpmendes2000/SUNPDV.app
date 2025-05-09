import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AzureSqlConnect {
    public static void main(String[] args) {
        String url = "jdbc:sqlserver://serverpdv.database.windows.net:1433;"
                   + "database=SUN_PDVcloud;"
                   + "user=adminuser@serverpdv;"
                   + "password=Jp081007!;"
                   + "encrypt=true;"
                   + "trustServerCertificate=false;"
                   + "hostNameInCertificate=*.database.windows.net;"
                   + "loginTimeout=30;";

        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url);

            Scanner sc = new Scanner(System.in);
            System.out.print("Digite o nome do produto: ");
            String Nome = sc.nextLine();

            System.out.print("Digite o Código de barras do produto: ");
            String Cod_Barras = sc.nextLine();

            System.out.print("Digite o preço do produto: ");
            double Preco = sc.nextDouble();

            String sql = "INSERT INTO produtos (Nome, Cod_Barras, Preco) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, Nome);
            stmt.setString(2, Cod_Barras);
            stmt.setDouble(3, Preco);

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso! Linhas afetadas: " + linhasAfetadas);

            stmt.close();
            conn.close();
            sc.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}