import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DB_URL = "jdbc:mysql://localhost:3306/toko";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("=== INPUT DATA BARANG ===");
            System.out.print("Kode: ");
            String kode = input.nextLine();
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Harga: ");
            int harga = input.nextInt();
            System.out.print("Stok: ");
            int stok = input.nextInt();

            // Panggil stored procedure
            CallableStatement cs = conn.prepareCall("{ call insert_barang(?, ?, ?, ?) }");
            cs.setString(1, kode);
            cs.setString(2, nama);
            cs.setInt(3, harga);
            cs.setInt(4, stok);

            cs.execute();
            System.out.println("Data berhasil diinsert.\n");

            // Tampilkan isi view_barang
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM view_barang");

            System.out.println("=== DATA BARANG DALAM VIEW ===");
            System.out.printf("%-6s %-20s %-7s %-5s %-12s\n", "Kode", "Nama", "Harga", "Stok", "Total Nilai");
            while (rs.next()) {
                System.out.printf("%-6s %-20s %-7d %-5d %-12d\n",
                    rs.getString("kode"),
                    rs.getString("nama"),
                    rs.getInt("harga"),
                    rs.getInt("stok"),
                    rs.getInt("total_nilai"));
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Kode barang sudah digunakan.");
        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
