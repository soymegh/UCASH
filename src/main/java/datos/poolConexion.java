package datos;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;


public class poolConexion {

    private static poolConexion INSTANCE = null;
    private static Connection con = null;
    private static BasicDataSource dataSource;
    private static String url = "jdbc:mysql://165.98.12.158:3306/dbucash?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private static String user = "root";
    private static String pass = "My$qlS3rv3rAPS*";

    private poolConexion() {
        this.inicializaDataSource();
    }

    private static synchronized void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new poolConexion();
        }

    }

    public static poolConexion getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }

        return INSTANCE;
    }

    public final void inicializaDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(user);
        basicDataSource.setPassword(pass);
        basicDataSource.setUrl(url);
        basicDataSource.setMaxActive(100);
        dataSource = basicDataSource;
    }

    public static boolean EstaConectado() {
        boolean resp = false;

        try {
            if (con != null && !con.isClosed()) {
                resp = true;
            } else {
                resp = false;
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return resp;
    }

    public static Connection getConnection() {
        if (!EstaConectado()) {
            try {
                con = dataSource.getConnection();
            } catch (SQLException var1) {
                var1.printStackTrace();
            }
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        if (EstaConectado()) {
            try {
                con.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws SQLException {
    }
}