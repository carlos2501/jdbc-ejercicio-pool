import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Comparativa {
    private static final String URL="jdbc:postgresql://localhost:5432/jardineria";
    private static final String USUARIO= "jardinero";
    private static final String CLAVE="jardinero";
    private static final int NUMCONEX = 100;

    /**
     * Comparativa
     * Conexiones       DM          Pool (Hikari)
     *      1           884
     *     10         1.890
     *    100         8.747
     */

    public static void main(String[] args) throws SQLException {
        long inicio = System.currentTimeMillis();
        for(int i = 0; i < NUMCONEX; i++){
            Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE);

            con.close();
        }
        System.out.println("Tiempo Total DM: "+(System.currentTimeMillis()-inicio));
    }
}
