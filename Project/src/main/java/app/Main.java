
package app;

import util.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexión exitosa a la base de datos");
            } else {
                System.out.println("❌ No se pudo establecer la conexión");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        }
    }
}
