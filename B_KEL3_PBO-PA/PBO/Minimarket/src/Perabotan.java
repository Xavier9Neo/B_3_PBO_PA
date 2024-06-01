import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader;



abstract class JenisPerabotan {
    protected String jenis;

    public JenisPerabotan(String jenis) {
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    abstract void tampilkanInfo();
}

class PerabotanDapur extends JenisPerabotan {
    public PerabotanDapur() {
        super("Peralatan Dapur");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah Peralatan Dapur.");
    }
}

class PerabotanRumahTangga extends JenisPerabotan {
    public PerabotanRumahTangga() {
        super("Peralatan Rumah Tangga");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah Peralatan Rumah Tangga.");
    }
}

class PerabotanMandi extends JenisPerabotan {
    public PerabotanMandi() {
        super("Perlengkapan Mandi");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah Perlengkapan Mandi.");
    }
}

class PerabotanTulis extends JenisPerabotan {
    public PerabotanTulis() {
        super("Perlengkapan Tulis");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah Perlengkapan Tulis.");
    }
}

public class Perabotan {
    // Variabel input untuk membaca dari pengguna
    private static final InputStreamReader sr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(sr);

    // Variabel untuk menyimpan data perabotan
    private int perabotanId;
    private String perabotanNama;
    private int perabotanStok;
    private double perabotanHarga;
    private JenisPerabotan perabotanJenis;
    
    // Variabel untuk koneksi database
    static final String Database = "jdbc:mysql://localhost:3306/dbminimarket";
    static Connection CONN;
    static Statement statement;
    static PreparedStatement Pstatement;
    static ResultSet result;

    // Constructor untuk membuat objek perabotan baru
    public Perabotan(int perabotanId, String perabotanNama, int perabotanStok, double perabotanHarga, JenisPerabotan perabotanJenis) {
        this.perabotanId = perabotanId;
        this.perabotanNama = perabotanNama;
        this.perabotanStok = perabotanStok;
        this.perabotanHarga = perabotanHarga;
        this.perabotanJenis = perabotanJenis;
    }

    // Getter dan Setter agar bisa mendapatkan dan mengubah nilai atribut
    public int getPerabotanId() {
        return perabotanId;
    }

    public void setPerabotanId(int perabotanId) {
        this.perabotanId = perabotanId;
    }

    public String getPerabotanNama() {
        return perabotanNama;
    }

    public void setPerabotanNama(String perabotanNama) {
        this.perabotanNama = perabotanNama;
    }

    
    public int getPerabotanStok() {
        return perabotanStok;
    }
    
    public void setPerabotanStok(int perabotanStok) {
        this.perabotanStok = perabotanStok;
    }
    
    public double getPerabotanHarga() {
        return perabotanHarga;
    }
    
    public void setPerabotanHarga(double perabotanHarga) {
        this.perabotanHarga = perabotanHarga;
    }
    
    public JenisPerabotan getJenisPerabotan() {
        return perabotanJenis;
    }

    public void setJenisPerabotan(JenisPerabotan jenisPerabotan) {
        this.perabotanJenis = jenisPerabotan;
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

    public static void lihatPerabotan2(Scanner input) {
        List<Perabotan> daftarPerabotan = readPerabotan();
        if (daftarPerabotan.isEmpty()) {
            System.out.println("Tidak ada data Perabotan.");
        } else {
            String format = "| %-3s | %-30s | %-23s | %-10s | %-15s |%n";
            System.out.format("+-----+--------------------------------+-------------------------+------------+-----------------+%n");
            System.out.format("| ID  | Nama                           | Jenis                   | Stok       | Harga           |%n");
            System.out.format("+-----+--------------------------------+-------------------------+------------+-----------------+%n");
    
            for (Perabotan perabotan : daftarPerabotan) {
                System.out.printf(format,
                        perabotan.getPerabotanId(), 
                        perabotan.getPerabotanNama(), 
                        perabotan.getJenisPerabotan().getJenis(),
                        perabotan.getPerabotanStok(), 
                        "Rp " + String.format("%,.2f", perabotan.getPerabotanHarga()));
            }
    
            System.out.format("+-----+--------------------------------+-------------------------+------------+-----------------+%n");
        }
        System.out.println("Tekan enter untuk melanjutkan...");
        input.nextLine(); // pause
        input.nextLine(); // pause
    }
    
    
    public static void lihatPerabotan(Scanner input) {
        List<Perabotan> daftarPerabotan = readPerabotan();
        if (daftarPerabotan.isEmpty()) {
            System.out.println("Tidak ada data Perabotan.");
        } else {
            String format = "| %-3s | %-30s | %-23s | %-10s | %-15s |%n";
            System.out.format("+-----+--------------------------------+-------------------------+------------+-----------------+%n");
            System.out.format("| ID  | Nama                           | Jenis                   | Stok       | Harga           |%n");
            System.out.format("+-----+--------------------------------+-------------------------+------------+-----------------+%n");
    
            for (Perabotan perabotan : daftarPerabotan) {
                System.out.printf(format,
                        perabotan.getPerabotanId(), 
                        perabotan.getPerabotanNama(), 
                        perabotan.getJenisPerabotan().getJenis(),
                        perabotan.getPerabotanStok(), 
                        "Rp " + String.format("%,.2f", perabotan.getPerabotanHarga()));
            }
    
            System.out.format("+-----+--------------------------------+-------------------------+------------+-----------------+%n");
        }
    }
    

    public static void tambahPerabotan(Scanner input) throws IOException {
        lihatPerabotan(input);
        System.out.print("Masukkan nama Perabotan: ");
        String nama = br.readLine();
        
        System.out.println("Jenis Perabotan:");
        System.out.println("1. Peralatan Dapur");
        System.out.println("2. Peralatan Rumah Tangga");
        System.out.println("3. Perlengkapan Mandi");
        System.out.println("4. Perlengkapan Tulis");

        JenisPerabotan jenisPerabotan = null;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Masukkan nomor jenis Perabotan: ");
            String inputJenis = br.readLine();
            if (isNumeric(inputJenis)) {
                int jenisPilihan = Integer.parseInt(inputJenis);
                // input.nextLine(); // Consume newline
                    switch (jenisPilihan) {
                        case 1:
                        jenisPerabotan = new PerabotanDapur();
                        validInput = true;
                        break;
                    case 2:
                        jenisPerabotan = new PerabotanRumahTangga();
                        validInput = true;
                        break;
                    case 3:
                        jenisPerabotan = new PerabotanMandi();
                        validInput = true;
                        break;
                    case 4:
                        jenisPerabotan = new PerabotanTulis();
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
            System.out.print("Masukkan stok perabotan: ");
            while (!input.hasNextInt()) {
                System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan stok perabotan: ");
            }
            stok = input.nextInt();
            input.nextLine(); // Consume newline
            if (stok <= 0) {
                System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
            }
        }

        double harga = -1;
        while (harga <= 0) {
            System.out.print("Masukkan harga perabotan: ");
            while (!input.hasNextDouble()) {
                System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan harga perabotan: ");
            }
            harga = input.nextDouble();
            input.nextLine(); // Consume newline
            if (harga <= 0) {
                System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
            }
        }

        // Menampilkan pesan sesuai dengan jenis perabotan yang dipilih
        if (jenisPerabotan != null) {
            System.out.println(jenisPerabotan.getJenis());
        }

        insertPerabotan(nama, jenisPerabotan.getJenis(), stok, harga);
        System.out.println("Perabotan berhasil ditambahkan!");
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

    public static void ubahPerabotan(Scanner input) {
        List<Perabotan> daftarPerabotan = readPerabotan(); // Memastikan daftarPerabotan diisi dari database
        if (daftarPerabotan.isEmpty()) {
            System.out.println("Tidak ada perabotan dalam sistem.");
            return;
        }
    
        lihatPerabotan(input); // Menampilkan daftar perabotan
    
        System.out.print("Pilih ID perabotan yang ingin diubah: ");
        while (!input.hasNextInt()) {
            System.out.println("ID harus berupa angka. Silakan masukkan lagi.");
            input.next(); // Consume the invalid input
            System.out.print("Pilih ID perabotan yang ingin diubah: ");
        }
        int id = input.nextInt();
        input.nextLine(); // Consume newline
    
        Perabotan perabotanToUpdate = null;
        for (Perabotan perabotan : daftarPerabotan) {
            if (perabotan.getPerabotanId() == id) {
                perabotanToUpdate = perabotan;
                break;
            }
        }
    
        if (perabotanToUpdate != null) {
            System.out.println("\nPilih data yang ingin diubah:");
            System.out.println("1. Nama Perabotan");
            System.out.println("2. Jenis Perabotan");
            System.out.println("3. Stok Perabotan");
            System.out.println("4. Harga Perabotan");
            System.out.println("5. Semua Data");
            System.out.println("6. Kembali");
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
                    System.out.print("Masukkan nama perabotan baru: ");
                    String namaBaru = input.nextLine();
                    perabotanToUpdate.setPerabotanNama(namaBaru);
                    updatePerabotan(perabotanToUpdate, id);
                    System.out.println("Nama perabotan berhasil diubah!");
                    break;
                case 2:
                    // Menampilkan menu jenis perabotan
                    System.out.println("Jenis Perabotan:");
                    System.out.println("1. Peralatan Dapur");
                    System.out.println("2. Peralatan Rumah Tangga");
                    System.out.println("3. Perlengkapan Mandi");
                    System.out.println("4. Perlengkapan Tulis");
    
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("Masukkan nomor jenis perabotan baru: ");
                        if (input.hasNextInt()) {
                            int jenisBaruPilihan = input.nextInt();
                            input.nextLine(); // Consume newline
                            
                            switch (jenisBaruPilihan) {
                                case 1:
                                    perabotanToUpdate.setJenisPerabotan(new PerabotanDapur());
                                    validInput = true;
                                    break;
                                case 2:
                                    perabotanToUpdate.setJenisPerabotan(new PerabotanRumahTangga()); 
                                    validInput = true;
                                    break;
                                case 3:
                                    perabotanToUpdate.setJenisPerabotan(new PerabotanMandi()); 
                                    validInput = true;
                                    break;
                                case 4:
                                    perabotanToUpdate.setJenisPerabotan(new PerabotanTulis());
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
                    updatePerabotan(perabotanToUpdate, id);
                    System.out.println("Jenis perabotan berhasil diubah!");
                    break;

                case 3:
                    int stokBaru = -1;
                    while (stokBaru <= 0) {
                        System.out.print("Masukkan stok perabotan baru: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan stok perabotan baru: ");
                        }
                        stokBaru = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (stokBaru <= 0) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                        }
                    }
                    perabotanToUpdate.setPerabotanStok(stokBaru);
                    updatePerabotan(perabotanToUpdate, id);
                    System.out.println("Stok perabotan berhasil diubah!");
                    break;
                case 4:
                    double hargaBaru = -1;
                    while (hargaBaru <= 0) {
                        System.out.print("Masukkan harga perabotan baru: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan harga perabotan baru: ");
                        }
                        hargaBaru = input.nextDouble();
                        input.nextLine(); // Consume newline
                        if (hargaBaru <= 0) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                        }
                    }
                    perabotanToUpdate.setPerabotanHarga(hargaBaru);
                    updatePerabotan(perabotanToUpdate, id);
                    System.out.println("Harga perabotan berhasil diubah!");
                    break;
                case 5:
                    System.out.print("Masukkan nama Perabotan baru: ");
                    String namaBaruAll = input.nextLine();

                    // Menampilkan menu jenis perabotan
                    System.out.println("Jenis Perabotan:");
                    System.out.println("1. Peralatan Dapur");
                    System.out.println("2. Peralatan Rumah Tangga");
                    System.out.println("3. Perlengkapan Mandi");
                    System.out.println("4. Perlengkapan Tulis");

                    String jenisBaruAll = "";
                    boolean validInput1 = false;
                    while (!validInput1) {
                        System.out.print("Masukkan nomor jenis perabotan baru: ");
                        if (input.hasNextInt()) {
                            int jenisBaruAllPilihan = input.nextInt();
                            input.nextLine(); // Consume newline
                            
                            switch (jenisBaruAllPilihan) {
                                case 1:
                                    jenisBaruAll = "Peralatan Dapur";
                                    validInput1 = true;
                                    break;
                                case 2:
                                    jenisBaruAll = "Peralatan Rumah Tangga";
                                    validInput1 = true;
                                    break;
                                case 3:
                                    jenisBaruAll = "Perlengkapan Mandi";
                                    validInput1 = true;
                                    break;
                                case 4:
                                    jenisBaruAll = "Perlengkapan Tulis";
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
                        System.out.print("Masukkan stok perabotan baru: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan stok perabotan baru: ");
                        }
                        stokBaruAll = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (stokBaruAll <= 0) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                        }
                    }

                    double hargaBaruAll = -1;
                    while (hargaBaruAll <= 0) {
                        System.out.print("Masukkan harga perabotan baru: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan harga perabotan baru: ");
                        }
                        hargaBaruAll = input.nextDouble();
                        input.nextLine(); // Consume newline
                        if (hargaBaruAll <= 0) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                        }
                    }
                    // Inisialisasi objek JenisPerabotan berdasarkan pilihan jenis baru
                    JenisPerabotan jenisPerabotanBaru = null;
                    switch (jenisBaruAll) {
                        case "Peralatan Dapur":
                            jenisPerabotanBaru = new PerabotanDapur();
                            break;
                        case "Peralatan Rumah Tangga":
                            jenisPerabotanBaru = new PerabotanRumahTangga();
                            break;
                        case "Perlengkapan Mandi":
                            jenisPerabotanBaru = new PerabotanMandi();
                            break;
                        case "Perlengkapan Tulis":
                            jenisPerabotanBaru = new PerabotanTulis();
                            break;
                    }
                    // Mengatur data baru pada objek perabotan
                    perabotanToUpdate.setPerabotanNama(namaBaruAll);
                    perabotanToUpdate.setJenisPerabotan(jenisPerabotanBaru);
                    perabotanToUpdate.setPerabotanStok(stokBaruAll);
                    perabotanToUpdate.setPerabotanHarga(hargaBaruAll);
                    // Memanggil metode updatePerabotan untuk memperbarui data
                    updatePerabotan(perabotanToUpdate, id);
                    System.out.println("Data Perabotan berhasil diubah!");
                    break;
                case 6:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } else {
            System.out.println("ID Perabotan tidak valid.");
        }
        pause(1000);
    }
    

    public static void hapusPerabotan(Scanner input) {
        List<Perabotan> daftarPerabotan = readPerabotan();
        if (daftarPerabotan.isEmpty()) {
            System.out.println("Tidak ada data perabotan.");
            return;
        }

        // Tampilkan daftar perabotan
        lihatPerabotan(input);

        System.out.print("Pilih ID perabotan yang ingin dihapus: ");
        while (!input.hasNextInt()) {
            System.out.println("ID harus berupa angka. Silakan masukkan lagi.");
            input.next(); // Consume the invalid input
            System.out.print("Pilih ID perabotan yang ingin dihapus: ");
        }
        int id = input.nextInt();
        input.nextLine(); // Consume newline

        boolean idValid = false;
        Perabotan perabotanToRemove = null;

        for (Perabotan perabotan : daftarPerabotan) {
            if (perabotan.getPerabotanId() == id) {
                idValid = true;
                perabotanToRemove = perabotan;
                break;
            }
        }

        if (idValid && perabotanToRemove != null) {
            deletePerabotan(id);
            System.out.println("Perabotan dengan ID" + perabotanToRemove.getPerabotanId() + " berhasil dihapus!");
        } else {
            System.out.println("ID perabotan tidak valid.");
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

    // CRUD operations
    public static ArrayList<Perabotan> readPerabotan() {
        connection();
        ArrayList<Perabotan> perabotanList = new ArrayList<>();
        try {
            statement = (Statement) CONN.createStatement();
            result = statement.executeQuery("SELECT * FROM tbperabotan");
            while (result.next()) {
                int id = result.getInt("idPerabotan"); // Mengambil ID dari database
                String nama = result.getString("namaPerabotan");
                String jenis = result.getString("jenisPerabotan");
                int stok = result.getInt("stokPerabotan");
                double harga = result.getDouble("hargaPerabotan");

                 // Inisialisasi objek JenisPerabotan berdasarkan jenis perabotan dari database
                JenisPerabotan jenisPerabotan = null;
                switch (jenis) {
                    case "Peralatan Dapur":
                        jenisPerabotan = new PerabotanDapur();
                        break;
                    case "Peralatan Rumah Tangga":
                        jenisPerabotan = new PerabotanRumahTangga();
                        break;
                    case "Perlengkapan Mandi":
                        jenisPerabotan = new PerabotanMandi();
                        break;
                    case "Perlengkapan Tulis":
                        jenisPerabotan = new PerabotanTulis();
                        break;
                    default:
                        // Handle jenis perabotan yang tidak dikenal jika diperlukan
                        break;
                }
                Perabotan perabotan = new Perabotan(id, nama, stok, harga, jenisPerabotan);
                perabotanList.add(perabotan);
            }
            statement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data perabotan.");
            e.printStackTrace();
        }
        return perabotanList;
    }

    public static void insertPerabotan(String nama, String jenis, int stok, double harga) {
        connection();
        try {
            String query = "INSERT INTO tbperabotan (namaPerabotan, jenisPerabotan, stokPerabotan, hargaPerabotan) VALUES (?, ?, ?, ?)";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setString(1, nama);
            Pstatement.setString(2, jenis);
            Pstatement.setInt(3, stok);
            Pstatement.setDouble(4, harga);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan Perabotan.");
            e.printStackTrace();
        }
    }

    public static void updatePerabotan(Perabotan perabotan, int id) {
        connection();
        try {
            String query = "UPDATE tbperabotan SET namaPerabotan = ?, jenisPerabotan = ?, stokPerabotan = ?, hargaPerabotan = ? WHERE idPerabotan = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setString(1, perabotan.getPerabotanNama());
            Pstatement.setString(2, perabotan.getJenisPerabotan().getJenis());
            Pstatement.setInt(3, perabotan.getPerabotanStok());
            Pstatement.setDouble(4, perabotan.getPerabotanHarga());
            Pstatement.setInt(5, id);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengubah Perabotan.");
            e.printStackTrace();
        }
    }

    // Method untuk menghapus perabotan dari database
    public static void deletePerabotan(int id) {
        connection();
        try {
            String query = "DELETE FROM tbperabotan WHERE idPerabotan = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, id);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
            
            // Update IDs after deletion
            updateIdsAfterDeletion(id); // Panggil fungsi untuk memperbarui ID setelah penghapusan
        } catch (SQLException e) {
            System.out.println("Gagal menghapus perabotan.");
            e.printStackTrace();
        }
    }
    
    private static void updateIdsAfterDeletion(int deletedId) {
        connection();
        try {
            // Ambil ID perabotan yang lebih besar dari ID yang dihapus
            String query = "SELECT idPerabotan FROM tbperabotan WHERE idPerabotan > ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, deletedId);
            ResultSet resultSet = Pstatement.executeQuery();
            
            // Simpan ID yang perlu diperbarui dalam daftar
            List<Integer> idsToUpdate = new ArrayList<>();
            while (resultSet.next()) {
                idsToUpdate.add(resultSet.getInt("idPerabotan"));
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
            // Perbarui ID perabotan secara individu
            String query = "UPDATE tbperabotan SET idPerabotan = ? WHERE idPerabotan = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, newId);
            Pstatement.setInt(2, oldId);
            Pstatement.executeUpdate();
            Pstatement.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate ID perabotan.");
            e.printStackTrace();
        }
    }
}
