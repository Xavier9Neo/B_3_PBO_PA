import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Laporan implements LaporanDisplay {

    public static void tambahLaporanMasuk(Scanner input) {
        System.out.println("====================================");
        System.out.println("|------- BUAT LAPORAN MASUK -------|");
        System.out.println("====================================");
        System.out.println("| [ 1 ] MAKANAN                    |");
        System.out.println("| [ 2 ] MINUMAN                    |");
        System.out.println("| [ 3 ] PERABOTAN RUMAH TANGGA     |");
        System.out.println("====================================");
        String jenis = "";
        boolean validInput = false;
        List<String> namaBarangList = new ArrayList<>();

        while (!validInput) {
            System.out.print("Masukkan nomor jenis barang: ");
            if (input.hasNextInt()) {
                int jenisPilihan = input.nextInt();
                input.nextLine(); // Consume newline
                switch (jenisPilihan) {
                    case 1:
                        jenis = "Makanan";
                        namaBarangList = getNamaBarang("tbmakanan", "namaMakanan");
                        validInput = true;
                        break;
                    case 2:
                        jenis = "Minuman";
                        namaBarangList = getNamaBarang("tbminuman", "namaMinuman");
                        validInput = true;
                        break;
                    case 3:
                        jenis = "Perabotan";
                        namaBarangList = getNamaBarang("tbperabotan", "namaPerabotan");
                        validInput = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan nomor yang sesuai.");
                }
            } else {
                System.out.println("Harus berupa angka. Silakan masukkan nomor yang sesuai.");
                input.nextLine(); // pause    
            }
        }

        if (namaBarangList.isEmpty()) {
            System.out.println("Data masih kosong.");
            System.out.println("Tekan enter untuk melanjutkan...");
            input.nextLine(); // pause    
            return;
        }

        System.out.println("Daftar " + jenis + " yang tersedia:");
        for (int i = 0; i < namaBarangList.size(); i++) {
            System.out.println((i + 1) + ". " + namaBarangList.get(i));
        }

        System.out.print("Masukkan nomor nama barang: ");
        int namaBarangIndex = input.nextInt() - 1;
        input.nextLine(); // Consume newline

        if (namaBarangIndex >= 0 && namaBarangIndex < namaBarangList.size()) {
            String namaBarang = namaBarangList.get(namaBarangIndex);

            System.out.print("Masukkan jumlah barang: ");
            while (!input.hasNextInt()) {
                System.out.println("Jumlah harus berupa angka. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan jumlah barang: ");
            }
            int jumlah = input.nextInt();
            input.nextLine(); // Consume newline
            
            if (jumlah < 0) {
                System.out.println("Jumlah tidak boleh negatif.");
                return;
            }

            System.out.print("Masukkan tanggal masuk (Format: dd-MM-yyyy): ");
            String tanggalStr = input.nextLine();
            Date tanggal = parseDate(tanggalStr);

            if (tanggal != null) {
                System.out.print("Apakah barang diterima? (y/n): ");
                String status = input.nextLine();
                String statusFinal;
                String keterangan = "";

                if (status.equalsIgnoreCase("y")) {
                    statusFinal = "diterima";
                } else {
                    statusFinal = "ditolak";
                    System.out.print("Masukkan keterangan : ");
                    keterangan = input.nextLine();
                }

                insertLaporanMasuk(tanggal, namaBarang, jenis, jumlah, statusFinal, keterangan);
                System.out.println("Laporan masuk berhasil ditambahkan!");
            } else {
                System.out.println("Format tanggal tidak valid.");
            }
        } else {
            System.out.println("Nama barang tidak valid.");
        }
    }

    private static List<String> getNamaBarang(String tableName, String columnName) {
        List<String> namaBarangList = new ArrayList<>();
        Connection conn = DatabaseManager.getConnection();
        try {
            String query = "SELECT " + columnName + " FROM " + tableName;
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                namaBarangList.add(rs.getString(columnName));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data dari tabel " + tableName);
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }

        return namaBarangList;
    }

    public static void tambahLaporanKeluar(Scanner input) {
        System.out.println("====================================");
        System.out.println("|------- BUAT LAPORAN MASUK -------|");
        System.out.println("====================================");
        System.out.println("| [ 1 ] MAKANAN                    |");
        System.out.println("| [ 2 ] MINUMAN                    |");
        System.out.println("| [ 3 ] PERABOTAN RUMAH TANGGA     |");
        System.out.println("====================================");
        String jenis = "";
        boolean validInput = false;
        List<String> namaBarangList = new ArrayList<>();

        while (!validInput) {
            System.out.print("Masukkan nomor jenis barang: ");
            if (input.hasNextInt()) {
                int jenisPilihan = input.nextInt();
                input.nextLine(); // Consume newline
                switch (jenisPilihan) {
                    case 1:
                        jenis = "Makanan";
                        namaBarangList = getNamaBarang("tbmakanan", "namaMakanan");
                        validInput = true;
                        break;
                    case 2:
                        jenis = "Minuman";
                        namaBarangList = getNamaBarang("tbminuman", "namaMinuman");
                        validInput = true;
                        break;
                    case 3:
                        jenis = "Perabotan";
                        namaBarangList = getNamaBarang("tbperabotan", "namaPerabotan");
                        validInput = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan nomor yang sesuai.");
                }
            } else {
                System.out.println("Harus berupa angka. Silakan masukkan nomor yang sesuai.");
                input.next(); // Consume the invalid input
            }
        }

        if (namaBarangList.isEmpty()) {
            System.out.println("Data masih kosong.");
            return;
        }

        System.out.println("Daftar " + jenis + " yang tersedia:");
        for (int i = 0; i < namaBarangList.size(); i++) {
            System.out.println((i + 1) + ". " + namaBarangList.get(i));
        }

        System.out.print("Masukkan nomor nama barang: ");
        int namaBarangIndex = input.nextInt() - 1;
        input.nextLine(); // Consume newline

        if (namaBarangIndex >= 0 && namaBarangIndex < namaBarangList.size()) {
            String namaBarang = namaBarangList.get(namaBarangIndex);

            System.out.print("Masukkan jumlah barang: ");
            while (!input.hasNextInt()) {
                System.out.println("Jumlah harus berupa angka. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan jumlah barang: ");
            }
            int jumlah = input.nextInt();
            input.nextLine(); // Consume newline

            if (jumlah < 0) {
                System.out.println("Jumlah tidak boleh negatif.");
                return;
            }

            System.out.print("Masukkan tanggal keluar (Format: dd-MM-yyyy): ");
            String tanggalStr = input.nextLine();
            Date tanggal = parseDate(tanggalStr);

            if (tanggal != null) {
                System.out.print("Masukkan keterangan: ");
                String keterangan = input.nextLine();

                insertLaporanKeluar(tanggal, namaBarang, jenis, jumlah, keterangan);
                System.out.println("Laporan keluar berhasil ditambahkan!");
            } else {
                System.out.println("Format tanggal tidak valid.");
            }
        } else {
            System.out.println("Nama barang tidak valid.");
        }
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Disable lenient parsing

        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use the format dd-MM-yyyy.");
            return null;
        }
    }

    private static void insertLaporanMasuk(Date tanggal, String namaBarang, String jenis, int jumlah, String status, String keterangan) {
        Connection conn = DatabaseManager.getConnection();
        try {
            String query = "INSERT INTO tblaporanmasuk (jenisBarangMasuk, namaBarangMasuk, jumlahBarangMasuk, tanggalMasuk, statusBarangMasuk, keteranganBarangMasuk) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(query);
            pstmt.setString(1, jenis);
            pstmt.setString(2, namaBarang);
            pstmt.setInt(3, jumlah);
            pstmt.setDate(4, new java.sql.Date(tanggal.getTime()));
            pstmt.setString(5, status);
            pstmt.setString(6, keterangan);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan laporan masuk.");
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
    }

    private static void insertLaporanKeluar(Date tanggal, String namaBarang, String jenis, int jumlah, String keterangan) {
        Connection conn = DatabaseManager.getConnection();
        try {
            String query = "INSERT INTO tblaporankeluar (jenisBarangKeluar, namaBarangKeluar, jumlahBarangKeluar, tanggalKeluar, keteranganBarangKeluar) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(query);
            pstmt.setString(1, jenis);
            pstmt.setString(2, namaBarang);
            pstmt.setInt(3, jumlah);
            pstmt.setDate(4, new java.sql.Date(tanggal.getTime()));
            pstmt.setString(5, keterangan);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan laporan keluar.");
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
    }

    @Override
    public void lihatLaporanMasuk() {
        Connection conn = DatabaseManager.getConnection();
        try {
            String query = "SELECT * FROM tblaporanmasuk";
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedNow = now.format(formatter);

            System.out.println("LAPORAN MASUK");
            System.out.println("Tanggal: " + formattedNow);
            System.out.println("Pembuat Laporan: Admin");
            String format = "| %-10s | %-10s | %-20s | %-10s | %-10s | %-10s | %-20s |%n";
            System.out.format("+------------+------------+----------------------+------------+------------+------------+----------------------+%n");
            System.out.format("| ID         | Jenis      | Nama Barang          | Jumlah     | Tanggal    | Status     | Keterangan           |%n");
            System.out.format("+------------+------------+----------------------+------------+------------+------------+----------------------+%n");
            while (rs.next()) {
                String keterangan = rs.getString("keteranganBarangMasuk");
                System.out.format(format,
                        rs.getInt("idBarangMasuk"),
                        rs.getString("jenisBarangMasuk"),
                        rs.getString("namaBarangMasuk"),
                        rs.getInt("jumlahBarangMasuk"),
                        rs.getDate("tanggalMasuk"),
                        rs.getString("statusBarangMasuk"),
                        (keterangan != null && !keterangan.isEmpty()) ? keterangan : "-");
            }
            System.out.format("+------------+------------+----------------------+------------+------------+------------+----------------------+%n");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan laporan masuk.");
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
    }

    @Override
    public void lihatLaporanKeluar() {
        Connection conn = DatabaseManager.getConnection();
        try {
            String query = "SELECT * FROM tblaporankeluar";
            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedNow = now.format(formatter);

            System.out.println("LAPORAN KELUAR");
            System.out.println("Tanggal: " + formattedNow);
            System.out.println("Pembuat Laporan: Admin");
            String format = "| %-10s | %-10s | %-20s | %-10s | %-10s | %-20s |%n";
            System.out.format("+------------+------------+----------------------+------------+------------+----------------------+%n");
            System.out.format("| ID         | Jenis      | Nama Barang          | Jumlah     | Tanggal    | Keterangan           |%n");
            System.out.format("+------------+------------+----------------------+------------+------------+----------------------+%n");
            while (rs.next()) {
                String keterangan = rs.getString("keteranganBarangKeluar");
                System.out.format(format,
                        rs.getInt("idBarangKeluar"),
                        rs.getString("jenisBarangKeluar"),
                        rs.getString("namaBarangKeluar"),
                        rs.getInt("jumlahBarangKeluar"),
                        rs.getDate("tanggalKeluar"),
                        (keterangan != null && !keterangan.isEmpty()) ? keterangan : "-");
            }
            System.out.format("+------------+------------+----------------------+------------+------------+----------------------+%n");
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Gagal menampilkan laporan keluar.");
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }
    }
}
