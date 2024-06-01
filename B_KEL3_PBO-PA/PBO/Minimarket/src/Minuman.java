import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



abstract class JenisMinuman {
    protected String jenis;

    public JenisMinuman(String jenis) {
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    abstract void tampilkanInfo();
}

class MinumanBotol extends JenisMinuman {
    public MinumanBotol() {
        super("Minuman Botol");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah minuman Botol.");
    }
}

class MinumanKaleng extends JenisMinuman {
    public MinumanKaleng() {
        super("Minuman Kaleng");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah minuman Kaleng.");
    }
}

class MinumanGelas extends JenisMinuman {
    public MinumanGelas() {
        super("Minuman Gelas");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah minuman Gelas.");
    }
}



public class Minuman {
    // Variabel input untuk membaca dari pengguna
    private static final InputStreamReader sr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(sr);
    // ==========

    // Variabel-variabel untuk menyimpan data minuman
    private int minumanId;
    private String minumanNama;
    private int minumanStok;
    private double minumanHarga;
    private Date minumanKadaluarsa;
    private JenisMinuman minumanJenis;

    // Variabel untuk koneksi database
    static final String Database = "jdbc:mysql://localhost:3306/dbminimarket";
    static Connection CONN;
    static Statement statement;
    static PreparedStatement Pstatement;
    static ResultSet result;

    // Constructor untuk membuat objek minuman baru
    public Minuman(int minumanId, String minumanNama, int minumanStok, double minumanHarga, Date minumanKadaluarsa, JenisMinuman minumanJenis) {
        this.minumanId = minumanId;
        this.minumanNama = minumanNama;
        this.minumanStok = minumanStok;
        this.minumanHarga = minumanHarga;
        this.minumanKadaluarsa = minumanKadaluarsa;
        this.minumanJenis = minumanJenis;
    }

    // Getter dan Setter untuk mendapatkan dan mengubah nilai atribut
    public int getMinumanId() {
        return minumanId;
    }

    public void setMinumanId(int minumanId) {
        this.minumanId = minumanId;
    }

    public String getMinumanNama() {
        return minumanNama;
    }

    public void setMinumanNama(String minumanNama) {
        this.minumanNama = minumanNama;
    }

    public int getMinumanStok() {
        return minumanStok;
    }

    public void setMinumanStok(int minumanStok) {
        this.minumanStok = minumanStok;
    }

    public double getMinumanHarga() {
        return minumanHarga;
    }

    public void setMinumanHarga(double minumanHarga) {
        this.minumanHarga = minumanHarga;
    }

    public Date getMinumanKadaluarsa() {
        return minumanKadaluarsa;
    }

    public void setMinumanKadaluarsa(Date minumanKadaluarsa) {
        this.minumanKadaluarsa = minumanKadaluarsa;
    }

    public JenisMinuman getJenisMinuman() {
        return minumanJenis;
    }

    public void setJenisMinuman(JenisMinuman jenisMinuman) {
        this.minumanJenis = jenisMinuman;
    }

    // Metode koneksi database
    public static void connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            CONN = DriverManager.getConnection(Database, "root", ""); // Ganti dengan password MySQL Anda
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Gagal Connect");
            e.printStackTrace();
        }
    }
    
    public static void lihatMinuman2(Scanner input) {
        List<Minuman> daftarMinuman = readMinuman();
        if (daftarMinuman.isEmpty()) {
            System.out.println("Tidak ada data minuman.");
        } else {
            String format = "| %-3s | %-30s | %-15s | %-10s | %-15s | %-20s |%n";
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            System.out.format("| ID  | Nama                           | Jenis           | Stok       | Harga           | Kadaluarsa           |%n");
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (Minuman minuman : daftarMinuman) {
                System.out.printf(format,
                        minuman.getMinumanId(), 
                        minuman.getMinumanNama(), 
                        minuman.getJenisMinuman().getJenis(),
                        minuman.getMinumanStok(), 
                        "Rp " + String.format("%,.2f", minuman.getMinumanHarga()),
                        sdf.format(minuman.getMinumanKadaluarsa()));
            }
            
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
        }
        System.out.println("Tekan enter untuk melanjutkan...");
        input.nextLine(); // pause
        input.nextLine(); // pause
    }
    
    public static void lihatMinuman(Scanner input) {
        List<Minuman> daftarMinuman = readMinuman();
        if (daftarMinuman.isEmpty()) {
            System.out.println("Tidak ada data minuman.");
        } else {
            String format = "| %-3s | %-30s | %-15s | %-10s | %-15s | %-20s |%n";
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            System.out.format("| ID  | Nama                           | Jenis           | Stok       | Harga           | Kadaluarsa           |%n");
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (Minuman minuman : daftarMinuman) {
                System.out.printf(format,
                        minuman.getMinumanId(), 
                        minuman.getMinumanNama(), 
                        minuman.getJenisMinuman().getJenis(),
                        minuman.getMinumanStok(), 
                        "Rp " + String.format("%,.2f", minuman.getMinumanHarga()),
                        sdf.format(minuman.getMinumanKadaluarsa()));
            }
            
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
        }
    }
    
    public static void tambahMinuman(Scanner input) throws IOException {
        // Placeholder untuk method lihatMinuman
        lihatMinuman(input);
        System.out.print("Masukkan nama minuman: ");
        String nama = br.readLine();
    
        // Menampilkan menu jenis minuman
        System.out.println("Jenis Minuman:");
        System.out.println("1. Minuman Botol");
        System.out.println("2. Minuman Kaleng");
        System.out.println("3. Minuman Gelas");
    
        JenisMinuman jenisMinuman = null;
        boolean validInput = false;
    
        while (!validInput) {
            System.out.print("Masukkan nomor jenis minuman: ");
            String inputJenis = br.readLine();
            if (isNumeric(inputJenis)) {
                int jenisPilihan = Integer.parseInt(inputJenis);
                switch (jenisPilihan) {
                    case 1:
                        jenisMinuman = new MinumanBotol();
                        validInput = true;
                        break;
                    case 2:
                        jenisMinuman = new MinumanKaleng();
                        validInput = true;
                        break;
                    case 3:
                        jenisMinuman = new MinumanGelas();
                        validInput = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan nomor yang sesuai.");
                }
            } else {
                System.out.println("Harus berupa angka. Silakan masukkan nomor yang sesuai.");
            }
        }
    
        int stok = -1;
        while (stok <= 0) {
            System.out.print("Masukkan stok minuman: ");
            while (!input.hasNextInt()) {
                System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan stok minuman: ");
            }
            stok = input.nextInt();
            input.nextLine(); // Consume newline
            if (stok <= 0) {
                System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
            }
        }
    
        double harga = -1;
        while (harga <= 0) {
            System.out.print("Masukkan harga minuman: ");
            while (!input.hasNextDouble()) {
                System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan harga minuman: ");
            }
            harga = input.nextDouble();
            input.nextLine(); // Consume newline
            if (harga <= 0) {
                System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
            }
        }
    
        System.out.print("Masukkan tanggal kadaluarsa minuman (Format: dd-MM-yyyy): ");
        String kadaluarsaStr = br.readLine();
        Date kadaluarsa = parseDate(kadaluarsaStr);
        System.out.println(kadaluarsa);
    
        if (kadaluarsa != null) {
            if (jenisMinuman != null) {
                jenisMinuman.tampilkanInfo(); // Menampilkan pesan sesuai jenis minuman
                insertMinuman(nama, jenisMinuman.getJenis(), stok, harga, kadaluarsa);
                System.out.println("Minuman berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan minuman karena jenis tidak valid.");
            }
        } else {
            System.out.println("Gagal menambahkan minuman karena format tanggal tidak valid.");
        }
        pause(1000);
    }
    

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void ubahMinuman(Scanner input) {
        List<Minuman> daftarMinuman = readMinuman(); // Memastikan daftarMinuman diisi dari database
        if (daftarMinuman.isEmpty()) {
            System.out.println("Tidak ada minuman dalam sistem.");
            return;
        }
    
        lihatMinuman(input); // Menampilkan daftar minuman
    
        System.out.print("Pilih ID minuman yang ingin diubah: ");
        while (!input.hasNextInt()) {
            System.out.println("ID harus berupa angka. Silakan masukkan lagi.");
            input.next(); // Consume the invalid input
            System.out.print("Pilih ID minuman yang ingin diubah: ");
        }
        int id = input.nextInt();
        input.nextLine(); // Consume newline
    
        Minuman minumanToUpdate = null;
        for (Minuman minuman : daftarMinuman) {
            if (minuman.getMinumanId() == id) {
                minumanToUpdate = minuman;
                break;
            }
        }
    
        if (minumanToUpdate != null) {
            System.out.println("\nPilih data yang ingin diubah:");
            System.out.println("1. Nama Minuman");
            System.out.println("2. Jenis Minuman");
            System.out.println("3. Stok Minuman");
            System.out.println("4. Harga Minuman");
            System.out.println("5. Tanggal Kadaluarsa Minuman");
            System.out.println("6. Semua Data");
            System.out.println("7. Kembali");
            System.out.print("Pilih menu: ");
            while (!input.hasNextInt()) {
                System.out.println("Pilihan harus berupa angka. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Pilih menu: ");
            }
            int pilihanUbah = input.nextInt();
            input.nextLine(); // Consume newline
    
            switch (pilihanUbah) {
                case 1:
                    System.out.print("Masukkan nama minuman baru: ");
                    String namaBaru = input.nextLine();
                    minumanToUpdate.setMinumanNama(namaBaru);
                    updateMinuman(minumanToUpdate, id);
                    System.out.println("Nama minuman berhasil diubah!");
                    break;
                case 2:
                    // Menampilkan menu jenis minuman
                    System.out.println("Jenis Minuman:");
                    System.out.println("1. Minuman Botol");
                    System.out.println("2. Minuman Kaleng");
                    System.out.println("3. Minuman Gelas");
    
                    
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("Masukkan nomor jenis minuman baru: ");
                        if (input.hasNextInt()) {
                            int jenisBaruPilihan = input.nextInt();
                            input.nextLine(); // Consume newline
    
                            switch (jenisBaruPilihan) {
                                case 1:
                                    minumanToUpdate.setJenisMinuman(new MinumanBotol());
                                    validInput = true;
                                    break;
                                case 2:
                                    minumanToUpdate.setJenisMinuman(new MinumanKaleng());
                                    validInput = true;
                                    break;
                                case 3:
                                    minumanToUpdate.setJenisMinuman(new MinumanGelas());
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
                    updateMinuman(minumanToUpdate, id);
                    System.out.println("Jenis minuman berhasil diubah!");
                    break;
    
                case 3:
                    int stokBaru = -1;
                    while (stokBaru <= 0) {
                        System.out.print("Masukkan stok minuman baru: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan stok minuman baru: ");
                        }
                        stokBaru = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (stokBaru <= 0) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                        }
                    }
                    minumanToUpdate.setMinumanStok(stokBaru);
                    updateMinuman(minumanToUpdate, id);
                    System.out.println("Stok minuman berhasil diubah!");
                    break;
                case 4:
                    double hargaBaru = -1;
                    while (hargaBaru <= 0) {
                        System.out.print("Masukkan harga minuman baru: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan harga minuman baru: ");
                        }
                        hargaBaru = input.nextDouble();
                        input.nextLine(); // Consume newline
                        if (hargaBaru <= 0) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                        }
                    }
                    minumanToUpdate.setMinumanHarga(hargaBaru);
                    updateMinuman(minumanToUpdate, id);
                    System.out.println("Harga minuman berhasil diubah!");
                    break;
                case 5:
                    System.out.print("Masukkan tanggal kadaluarsa minuman baru (Format: dd-MM-yyyy): ");
                    String kadaluarsaBaruStr = input.nextLine();
                    Date kadaluarsaBaru = parseDate(kadaluarsaBaruStr);
                    if (kadaluarsaBaru != null) {
                        minumanToUpdate.setMinumanKadaluarsa(kadaluarsaBaru);
                        updateMinuman(minumanToUpdate, id);
                        System.out.println("Tanggal Kadaluarsa minuman berhasil diubah!");
                    } else {
                        System.out.println("Gagal mengubah tanggalkadaluarsa minuman karena format tanggal tidak valid.");
                    }
                    break;
                case 6:
                    System.out.print("Masukkan nama minuman baru: ");
                    String namaBaruAll = input.nextLine();
    
                    // Menampilkan menu jenis minuman
                    System.out.println("Jenis Minuman:");
                    System.out.println("1. Minuman Botol");
                    System.out.println("2. Minuman Kaleng");
                    System.out.println("3. Minuman Gelas");
    
                    String jenisBaruAll = "";
                    boolean validInput1 = false;
                    while (!validInput1) {
                        System.out.print("Masukkan nomor jenis minuman baru: ");
                        if (input.hasNextInt()) {
                            int jenisBaruAllPilihan = input.nextInt();
                            input.nextLine(); // Consume newline
    
                            switch (jenisBaruAllPilihan) {
                                case 1:
                                    jenisBaruAll = "Minuman Botol";
                                    validInput1 = true;
                                    break;
                                case 2:
                                    jenisBaruAll = "Minuman Kaleng";
                                    validInput1 = true;
                                    break;
                                case 3:
                                    jenisBaruAll = "Minuman Gelas";
                                    validInput1 = true;
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid. Silakan masukkan nomor yang sesuai.");
                            }
                        } else {
                            System.out.println("Harus berupa angka. Silakan masukkan nomor yang sesuai.");
                            input.next(); // Consume the invalid input
                        }
                    }
    
                    int stokBaruAll = -1;
                    while (stokBaruAll <= 0) {
                        System.out.print("Masukkan stok minuman baru: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan stok minuman baru: ");
                        }
                        stokBaruAll = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (stokBaruAll <= 0) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                        }
                    }

                    double hargaBaruAll = -1;
                    while (hargaBaruAll <= 0) {
                        System.out.print("Masukkan harga minuman baru: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan harga minuman baru: ");
                        }
                        hargaBaruAll = input.nextDouble();
                        input.nextLine(); // Consume newline
                        if (hargaBaruAll <= 0) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                        }
                    }
    
                    System.out.print("Masukkan tanggal kadaluarsa minuman baru (Format: dd-MM-yyyy): ");
                    String kadaluarsaBaruStrAll = input.nextLine();
                    Date kadaluarsaBaruAll = parseDate(kadaluarsaBaruStrAll);
                    if (kadaluarsaBaruAll != null) {
                        // Inisialisasi objek JenisMinuman berdasarkan pilihan jenis baru
                        JenisMinuman jenisMinumanBaru = null;
                        switch (jenisBaruAll) {
                            case "Minuman Botol":
                                jenisMinumanBaru = new MinumanBotol();
                                break;
                            case "Minuman Kaleng":
                                jenisMinumanBaru = new MinumanKaleng();
                                break;
                            case "Minuman Gelas":
                                jenisMinumanBaru = new MinumanGelas();
                                break;
                        }
                        
                        // Set semua data baru ke minumanToUpdate
                        minumanToUpdate.setMinumanNama(namaBaruAll);
                        minumanToUpdate.setJenisMinuman(jenisMinumanBaru);
                        minumanToUpdate.setMinumanStok(stokBaruAll);
                        minumanToUpdate.setMinumanHarga(hargaBaruAll);
                        minumanToUpdate.setMinumanKadaluarsa(kadaluarsaBaruAll);
                        updateMinuman(minumanToUpdate, id);
                        System.out.println("Data minuman berhasil diubah!");
                    } else {
                        System.out.println("Gagal mengubah data minuman karena format tanggal tidak valid.");
                    }
                    break;
                case 7:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } else {
            System.out.println("ID minuman tidak valid.");
        }
        pause(1000);
    }
    
    public static void hapusMinuman(Scanner input) {
        List<Minuman> daftarMinuman = readMinuman();
        if (daftarMinuman.isEmpty()) {
            System.out.println("Tidak ada data minuman.");
            return;
        }

        // Tampilkan daftar minuman
        lihatMinuman(input);

        System.out.print("Pilih ID minuman yang ingin dihapus: ");
        while (!input.hasNextInt()) {
            System.out.println("ID harus berupa angka. Silakan masukkan lagi.");
            input.next(); // Consume the invalid input
            System.out.print("Pilih ID minuman yang ingin dihapus: ");
        }
        int id = input.nextInt();
        input.nextLine(); // Consume newline

        boolean idValid = false;
        Minuman minumanToRemove = null;

        for (Minuman minuman : daftarMinuman) {
            if (minuman.getMinumanId() == id) {
                idValid = true;
                minumanToRemove = minuman;
                break;
            }
        }

        if (idValid && minumanToRemove != null) {
            deleteMinuman(id);
            System.out.println("Minuman dengan ID" + minumanToRemove.getMinumanId() + " berhasil dihapus!");
        } else {
            System.out.println("ID minuman tidak valid.");
        }
        pause(1000);
    }

    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Format tanggal tidak valid. Gunakan format dd-MM-yyyy.");
            return null;
        }
    }

    // CRUD operations
    public static ArrayList<Minuman> readMinuman() {
        connection();
        ArrayList<Minuman> minumanList = new ArrayList<>();
        try {
            statement = (Statement) CONN.createStatement();
            result = statement.executeQuery("SELECT * FROM tbminuman");
            while (result.next()) {
                int id = result.getInt("idMinuman"); // Mengambil ID dari database
                String nama = result.getString("namaMinuman");
                String jenis = result.getString("jenisMinuman");
                int stok = result.getInt("stokMinuman");
                double harga = result.getDouble("hargaMinuman");
                Date kadaluarsa = result.getDate("kadaluarsaMinuman");

                // Inisialisasi objek JenisMinuman berdasarkan jenis minuman dari database
                JenisMinuman jenisMinuman = null;
                switch (jenis) {
                    case "Minuman Botol":
                        jenisMinuman = new MinumanBotol();
                        break;
                    case "Minuman Kaleng":
                        jenisMinuman = new MinumanKaleng();
                        break;
                    case "Minuman Gelas":
                        jenisMinuman = new MinumanGelas();
                        break;
                    default:
                        // Handle jenis minuman yang tidak dikenal jika diperlukan
                        break;
                }

                // Buat objek Minuman dengan menggunakan jenisMinuman yang telah diinisialisasi
                Minuman minuman = new Minuman(id, nama, stok, harga, kadaluarsa, jenisMinuman);
                minumanList.add(minuman);
            }
            statement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data minuman.");
            e.printStackTrace();
        }
        return minumanList;
    }
    
    public static void insertMinuman(String nama, String jenis, int stok, double harga, Date kadaluarsa) {
        connection();
        try {
            String query = "INSERT INTO tbminuman (namaMinuman, jenisMinuman, stokMinuman, hargaMinuman, kadaluarsaMinuman) VALUES (?, ?, ?, ?, ?)";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setString(1, nama);
            Pstatement.setString(2, jenis);
            Pstatement.setInt(3, stok);
            Pstatement.setDouble(4, harga);
            Pstatement.setDate(5, new java.sql.Date(kadaluarsa.getTime()));
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan minuman.");
            e.printStackTrace();
        }
    }

    public static void updateMinuman(Minuman minuman, int id) {
        connection();
        try {
            String query = "UPDATE tbminuman SET namaMinuman = ?, jenisMinuman = ?, stokMinuman = ?, hargaMinuman = ?, kadaluarsaMinuman = ? WHERE idMinuman = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setString(1, minuman.getMinumanNama());
            Pstatement.setString(2, minuman.getJenisMinuman().getJenis()); // Mengambil jenis minuman dari objek JenisMinuman
            Pstatement.setInt(3, minuman.getMinumanStok());
            Pstatement.setDouble(4, minuman.getMinumanHarga());
            Pstatement.setDate(5, new java.sql.Date(minuman.getMinumanKadaluarsa().getTime()));
            Pstatement.setInt(6, id);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengubah minuman.");
            e.printStackTrace();
        }
    }

    // Method untuk menghapus minuman dari database
    public static void deleteMinuman(int id) {
        connection();
        try {
            String query = "DELETE FROM tbminuman WHERE idMinuman = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, id);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
            
            // Update IDs after deletion
            updateIdsAfterDeletion(id); // Panggil fungsi untuk memperbarui ID setelah penghapusan
        } catch (SQLException e) {
            System.out.println("Gagal menghapus minuman.");
            e.printStackTrace();
        }
    }
    
    private static void updateIdsAfterDeletion(int deletedId) {
        connection();
        try {
            // Ambil ID minuman yang lebih besar dari ID yang dihapus
            String query = "SELECT idMinuman FROM tbminuman WHERE idMinuman > ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, deletedId);
            ResultSet resultSet = Pstatement.executeQuery();
            
            // Simpan ID yang perlu diperbarui dalam daftar
            List<Integer> idsToUpdate = new ArrayList<>();
            while (resultSet.next()) {
                idsToUpdate.add(resultSet.getInt("idMinuman"));
            }
            
            // Perbarui ID
            for (Integer id : idsToUpdate) {
                int newId = id - 1; // Geser ID satu per satu setelah penghapusan
                updateId(id, newId);
            }
            
            Pstatement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate IDs setelah penghapusan.");
            e.printStackTrace();
        }
    }
    
    private static void updateId(int oldId, int newId) {
        try {
            // Perbarui ID minuman secara individu
            String query = "UPDATE tbminuman SET idMinuman = ? WHERE idMinuman = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, newId);
            Pstatement.setInt(2, oldId);
            Pstatement.executeUpdate();
            Pstatement.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate ID minuman.");
            e.printStackTrace();
        }
    }
}


