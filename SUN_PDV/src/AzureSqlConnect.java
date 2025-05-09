import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AzureSqlConnect {
    // Configurações de conexão (em produção, use variáveis de ambiente ou arquivo de configuração)
    private static final String JDBC_URL = "jdbc:sqlserver://serverpdv.database.windows.net:1433;"
            + "database=SUN_PDVcloud;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "hostNameInCertificate=*.database.windows.net;"
            + "loginTimeout=30;";
    
    private static final String USERNAME = "adminuser@serverpdv";
    private static final String PASSWORD = "Jp081007!";

    public static void main(String[] args) {
        testConnection();
    }

    public static void testConnection() {
        String testQuery = "SELECT 1 AS test_value";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(testQuery);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                System.out.println("✅ Conexão bem-sucedida! Valor de teste: " + rs.getInt("test_value"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro na conexão com o banco de dados:");
            e.printStackTrace();
            
            // Mensagens mais amigáveis para erros comuns
            if (e.getMessage().contains("login failed")) {
                System.err.println("Verifique o usuário e senha");
            } else if (e.getMessage().contains("cannot open server")) {
                System.err.println("Verifique o nome do servidor e firewall");
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver (opcional para JDBC 4.0+)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado", e);
        }
        
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}