package piyano1;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:piano_stats.db";

    public static void initialize() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS GeneralStats (" +
                    "id INTEGER PRIMARY KEY CHECK (id = 1)," +
                    "wins INTEGER DEFAULT 0," +
                    "losses INTEGER DEFAULT 0," +
                    "longNoteErrors INTEGER DEFAULT 0)");

            stmt.execute("CREATE TABLE IF NOT EXISTS NoteStats (" +
                    "note CHAR(1) PRIMARY KEY," +
                    "missCount INTEGER DEFAULT 0)");

            stmt.execute("INSERT OR IGNORE INTO GeneralStats(id, wins, losses) VALUES(1, 0, 0)");
            char[] keys = {'a', 's', 'd', 'f', 'g', 'h', 'j'};
            for (char k : keys) {
                stmt.execute("INSERT OR IGNORE INTO NoteStats(note, missCount) VALUES('" + k + "', 0)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateWinLoss(boolean won) {
        String column = won ? "wins" : "losses";
        executeSQL("UPDATE GeneralStats SET " + column + " = " + column + " + 1 WHERE id = 1");
    }

    public static void incrementNoteMiss(char note, boolean isLong) {
        executeSQL("UPDATE NoteStats SET missCount = missCount + 1 WHERE note = '" + note + "'");
        if (isLong) {
            executeSQL("UPDATE GeneralStats SET longNoteErrors = longNoteErrors + 1 WHERE id = 1");
        }
    }

    public static String getGeneralStats() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT wins, losses, longNoteErrors FROM GeneralStats WHERE id = 1")) {
            if (rs.next()) {
                return String.format(" Kazanma: %d\n Kaybetme: %d\n Uzun Nota Hataları: %d", 
                        rs.getInt("wins"), rs.getInt("losses"), rs.getInt("longNoteErrors"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return "Veri yok.";
    }

    public static String getNoteStats() {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT note, missCount FROM NoteStats ORDER BY missCount DESC")) {
            while (rs.next()) {
                sb.append(" Nota: ").append(rs.getString("note").toUpperCase())
                  .append(" -> Hata: ").append(rs.getInt("missCount")).append("\n");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return sb.toString();
    }
    
  public static void resetStats() {
    try (Connection conn = DriverManager.getConnection(URL)) {
        Statement stmt = conn.createStatement();
        stmt.execute("UPDATE GeneralStats SET wins = 0, losses = 0, longNoteErrors = 0 WHERE id = 1");
        stmt.execute("UPDATE NoteStats SET missCount = 0");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    private static void executeSQL(String sql) {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) { e.printStackTrace(); }
    }
}