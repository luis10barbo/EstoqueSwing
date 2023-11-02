package estoqueswing.utils;

import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilsSQLITE {

    @Nullable
    public static Integer ultimoIDInserido(Statement statement) {
        try {
            ResultSet rs = statement.executeQuery("SELECT last_insert_rowid()");
            if (rs.next()) {
                return rs.getInt(1);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
