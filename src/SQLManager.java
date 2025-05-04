import java.sql.*;
import java.util.*;

class SQLManager {
    private Connection conn;

    public SQLManager() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:students.db");
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, jmeno TEXT, prijmeni TEXT, datumNarozeni TEXT, typ TEXT)");
        }
    }

    public void ulozStudenty(Map<Integer, Student> studenti) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM students");
        }
        for (Student s : studenti.values()) {
            String typ = s instanceof StudentTelekomunikace ? "telekomunikace" : "kyberbezpecnost";
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?, ?)")) {
                ps.setInt(1, s.getId());
                ps.setString(2, s.getJmeno());
                ps.setString(3, s.getPrijmeni());
                ps.setString(4, s.getDatumNarozeni());
                ps.setString(5, typ);
                ps.executeUpdate();
            }
        }
    }

    public Map<Integer, Student> nactiStudenty() throws SQLException {
        Map<Integer, Student> studenti = new HashMap<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String jmeno = rs.getString("jmeno");
                String prijmeni = rs.getString("prijmeni");
                String datum = rs.getString("datumNarozeni");
                String typ = rs.getString("typ");
                if ("telekomunikace".equals(typ)) {
                    studenti.put(id, new StudentTelekomunikace(id, jmeno, prijmeni, datum));
                } else {
                    studenti.put(id, new StudentKyberbezpecnost(id, jmeno, prijmeni, datum));
                }
            }
        }
        return studenti;
    }

    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}
