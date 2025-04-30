import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Comparativa {
    private static final String URL="jdbc:postgresql://localhost:5432/jardineria";
    private static final String USUARIO= "jardinero";
    private static final String CLAVE="jardinero";
    private static final int NUMCONEX = 300;

    /**
     * Comparativa
     * Conexiones       DM          Pool (Hikari)
     *      1           800             316
     *     10         1.653             220
     *    100         7.909             159
     *    300        22.221             162
     */

    public static void main(String[] args) throws SQLException {
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < NUMCONEX; i++){
            Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE);

            con.close();
        }
        System.out.println("Tiempo Total DM: "+(System.currentTimeMillis()-inicio));

        // Probamos con un pool de conexiones
        // Configuramos el pool
        HikariConfig conf = new HikariConfig();
        conf.setJdbcUrl(URL);
        conf.setUsername(USUARIO);
        conf.setPassword(CLAVE);
        conf.setMaximumPoolSize(NUMCONEX);

        inicio = System.currentTimeMillis();
        HikariDataSource hkds = new HikariDataSource(conf);
        for(int i = 0; i < NUMCONEX; i++){
            Connection con = hkds.getConnection();

            con.close();
        }
        hkds.close();
        System.out.println("Tiempo Total Pool: "+(System.currentTimeMillis()-inicio));

    }
}
