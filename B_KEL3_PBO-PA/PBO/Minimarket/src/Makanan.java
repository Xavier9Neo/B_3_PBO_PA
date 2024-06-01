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



abstract class JenisMakanan {
    protected String jenis;

    public JenisMakanan(String jenis) {
        this.jenis = jenis;
    }

    public String getJenis() {
        return jenis;
    }

    abstract void tampilkanInfo();
}

class MakananSegar extends JenisMakanan {
    public MakananSegar() {
        super("Makanan Segar");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah makanan segar.");
    }
}

class MakananOlahan extends JenisMakanan {
    public MakananOlahan() {
        super("Makanan Olahan");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah makanan olahan.");
    }
}

class MakananRingan extends JenisMakanan {
    public MakananRingan() {
        super("Makanan Ringan");
    }

    @Override
    void tampilkanInfo() {
        System.out.println("Ini adalah makanan ringan.");
    }
}



public class Makanan {
    // Variabel input untuk membaca dari pengguna
    private static final InputStreamReader sr = new InputStreamReader(System.in);
    private static final BufferedReader br = new BufferedReader(sr);
    // ==========

    // Variabel-variabel untuk menyimpan data makanan
    private int makananId;
    private String makananNama;
    private int makananStok;
    private double makananHarga;
    private Date makananKadaluarsa;
    private JenisMakanan makananJenis;

    // Variabel untuk koneksi database
    static final String Database = "jdbc:mysql://localhost:3306/dbminimarket";
    static Connection CONN;
    static Statement statement;
    static PreparedStatement Pstatement;
    static ResultSet result;

    // Constructor untuk membuat objek Makanan baru
    public Makanan(int makananId, String makananNama, int makananStok, double makananHarga, Date makananKadaluarsa, JenisMakanan makananJenis) {
        this.makananId = makananId;
        this.makananNama = makananNama;
        this.makananStok = makananStok;
        this.makananHarga = makananHarga;
        this.makananKadaluarsa = makananKadaluarsa;
        this.makananJenis = makananJenis;
    }

    // Getter dan Setter untuk mendapatkan dan mengubah nilai atribut
    public int getMakananId() {
        return makananId;
    }

    public void setMakananId(int makananId) {
        this.makananId = makananId;
    }

    public String getMakananNama() {
        return makananNama;
    }

    public void setMakananNama(String makananNama) {
        this.makananNama = makananNama;
    }

    public int getMakananStok() {
        return makananStok;
    }

    public void setMakananStok(int makananStok) {
        this.makananStok = makananStok;
    }

    public double getMakananHarga() {
        return makananHarga;
    }

    public void setMakananHarga(double makananHarga) {
        this.makananHarga = makananHarga;
    }

    public Date getMakananKadaluarsa() {
        return makananKadaluarsa;
    }

    public void setMakananKadaluarsa(Date makananKadaluarsa) {
        this.makananKadaluarsa = makananKadaluarsa;
    }

    public JenisMakanan getJenisMakanan() {
        return makananJenis;
    }

    public void setJenisMakanan(JenisMakanan jenisMakanan) {
        this.makananJenis = jenisMakanan;
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
    
    public static void lihatMakanan2(Scanner input) {
        List<Makanan> daftarMakanan = readMakanan();
        if (daftarMakanan.isEmpty()) {
            System.out.println("Tidak ada data makanan.");
        } else {
            String format = "| %-3s | %-30s | %-15s | %-10s | %-15s | %-20s |%n";
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            System.out.format("| ID  | Nama                           | Jenis           | Stok       | Harga           | Kadaluarsa           |%n");
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (Makanan makanan : daftarMakanan) {
                System.out.printf(format,
                        makanan.getMakananId(), 
                        makanan.getMakananNama(), 
                        makanan.getJenisMakanan().getJenis(),
                        makanan.getMakananStok(), 
                        "Rp " + String.format("%,.2f", makanan.getMakananHarga()),
                        sdf.format(makanan.getMakananKadaluarsa()));
            }
            
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
        }
        System.out.println("Tekan enter untuk melanjutkan...");
        input.nextLine(); // pause
        input.nextLine(); // pause
    }
    
    public static void lihatMakanan(Scanner input) {
        List<Makanan> daftarMakanan = readMakanan();
        if (daftarMakanan.isEmpty()) {
            System.out.println("Tidak ada data makanan.");
        } else {
            String format = "| %-3s | %-30s | %-15s | %-10s | %-15s | %-20s |%n";
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            System.out.format("| ID  | Nama                           | Jenis           | Stok       | Harga           | Kadaluarsa           |%n");
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            for (Makanan makanan : daftarMakanan) {
                System.out.printf(format,
                        makanan.getMakananId(), 
                        makanan.getMakananNama(), 
                        makanan.getJenisMakanan().getJenis(),
                        makanan.getMakananStok(), 
                        "Rp " + String.format("%,.2f", makanan.getMakananHarga()),
                        sdf.format(makanan.getMakananKadaluarsa()));
            }
            
            System.out.format("+-----+--------------------------------+-----------------+------------+-----------------+----------------------+%n");
        }
    }
    
    public static void tambahMakanan(Scanner input) throws IOException {
        // Placeholder untuk method lihatMakanan
        lihatMakanan(input);
        System.out.print("Masukkan nama makanan: ");
        String nama = br.readLine();
    
        // Menampilkan menu jenis makanan
        System.out.println("Jenis Makanan:");
        System.out.println("1. Makanan Segar");
        System.out.println("2. Makanan Olahan");
        System.out.println("3. Makanan Ringan");
    
        JenisMakanan jenisMakanan = null;
        boolean validInput = false;
    
        while (!validInput) {
            System.out.print("Masukkan nomor jenis makanan: ");
            String inputJenis = br.readLine();
            if (isNumeric(inputJenis)) {
                int jenisPilihan = Integer.parseInt(inputJenis);
                switch (jenisPilihan) {
                    case 1:
                        jenisMakanan = new MakananSegar();
                        validInput = true;
                        break;
                    case 2:
                        jenisMakanan = new MakananOlahan();
                        validInput = true;
                        break;
                    case 3:
                        jenisMakanan = new MakananRingan();
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
            System.out.print("Masukkan stok makanan: ");
            while (!input.hasNextInt()) {
                System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan stok makanan: ");
            }
            stok = input.nextInt();
            input.nextLine(); // Consume newline
            if (stok <= 0) {
                System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
            }
        }
    
        double harga = -1;
        while (harga <= 0) {
            System.out.print("Masukkan harga makanan: ");
            while (!input.hasNextDouble()) {
                System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                input.next(); // Consume the invalid input
                System.out.print("Masukkan harga makanan: ");
            }
            harga = input.nextDouble();
            input.nextLine(); // Consume newline
            if (harga <= 0) {
                System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
            }
        }
    
        System.out.print("Masukkan tanggal kadaluarsa makanan (Format: dd-MM-yyyy): ");
        String kadaluarsaStr = br.readLine();
        Date kadaluarsa = parseDate(kadaluarsaStr);
        System.out.println(kadaluarsa);
    
        if (kadaluarsa != null) {
            if (jenisMakanan != null) {
                jenisMakanan.tampilkanInfo(); // Menampilkan pesan sesuai jenis makanan
                insertMakanan(nama, jenisMakanan.getJenis(), stok, harga, kadaluarsa);
                System.out.println("Makanan berhasil ditambahkan!");
            } else {
                System.out.println("Gagal menambahkan makanan karena jenis tidak valid.");
            }
        } else {
            System.out.println("Gagal menambahkan makanan karena format tanggal tidak valid.");
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

    public static void ubahMakanan(Scanner input) {
        List<Makanan> daftarMakanan = readMakanan(); // Memastikan daftarMakanan diisi dari database
        if (daftarMakanan.isEmpty()) {
            System.out.println("Tidak ada makanan dalam sistem.");
            return;
        }
    
        lihatMakanan(input); // Menampilkan daftar makanan
    
        System.out.print("Pilih ID makanan yang ingin diubah: ");
        while (!input.hasNextInt()) {
            System.out.println("ID harus berupa angka. Silakan masukkan lagi.");
            input.next(); // Consume the invalid input
            System.out.print("Pilih ID makanan yang ingin diubah: ");
        }
        int id = input.nextInt();
        input.nextLine(); // Consume newline
    
        Makanan makananToUpdate = null;
        for (Makanan makanan : daftarMakanan) {
            if (makanan.getMakananId() == id) {
                makananToUpdate = makanan;
                break;
            }
        }
    
        if (makananToUpdate != null) {
            System.out.println("\nPilih data yang ingin diubah:");
            System.out.println("1. Nama Makanan");
            System.out.println("2. Jenis Makanan");
            System.out.println("3. Stok Makanan");
            System.out.println("4. Harga Makanan");
            System.out.println("5. Tanggal Kadaluarsa Makanan");
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
                    System.out.print("Masukkan nama makanan baru: ");
                    String namaBaru = input.nextLine();
                    makananToUpdate.setMakananNama(namaBaru);
                    updateMakanan(makananToUpdate, id);
                    System.out.println("Nama makanan berhasil diubah!");
                    break;
                case 2:
                    // Menampilkan menu jenis makanan
                    System.out.println("Jenis Makanan:");
                    System.out.println("1. Makanan Segar");
                    System.out.println("2. Makanan Olahan");
                    System.out.println("3. Makanan Ringan");
    
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("Masukkan nomor jenis makanan baru: ");
                        if (input.hasNextInt()) {
                            int jenisBaruPilihan = input.nextInt();
                            input.nextLine(); // Consume newline
    
                            switch (jenisBaruPilihan) {
                                case 1:
                                    makananToUpdate.setJenisMakanan(new MakananSegar());
                                    validInput = true;
                                    break;
                                case 2:
                                    makananToUpdate.setJenisMakanan(new MakananOlahan());
                                    validInput = true;
                                    break;
                                case 3:
                                    makananToUpdate.setJenisMakanan(new MakananRingan());
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
                    updateMakanan(makananToUpdate, id);
                    System.out.println("Jenis makanan berhasil diubah!");
                    break;
    
                case 3:
                    int stokBaru = -1;
                    while (stokBaru <= 0) {
                        System.out.print("Masukkan stok makanan baru: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan stok makanan baru: ");
                        }
                        stokBaru = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (stokBaru <= 0) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                        }
                    }
                    makananToUpdate.setMakananStok(stokBaru);
                    updateMakanan(makananToUpdate, id);
                    System.out.println("Stok makanan berhasil diubah!");
                    break;
                case 4:
                    double hargaBaru = -1;
                    while (hargaBaru <= 0) {
                        System.out.print("Masukkan harga makanan baru: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan harga makanan baru: ");
                        }
                        hargaBaru = input.nextDouble();
                        input.nextLine(); // Consume newline
                        if (hargaBaru <= 0) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                        }
                    }
                    makananToUpdate.setMakananHarga(hargaBaru);
                    updateMakanan(makananToUpdate, id);
                    System.out.println("Harga makanan berhasil diubah!");
                    break;
                case 5:
                    System.out.print("Masukkan tanggal kadaluarsa makanan baru (Format: dd-MM-yyyy): ");
                    String kadaluarsaBaruStr = input.nextLine();
                    Date kadaluarsaBaru = parseDate(kadaluarsaBaruStr);
                    if (kadaluarsaBaru != null) {
                        makananToUpdate.setMakananKadaluarsa(kadaluarsaBaru);
                        updateMakanan(makananToUpdate, id);
                        System.out.println("Tanggal Kadaluarsa makanan berhasil diubah!");
                    } else {
                        System.out.println("Gagal mengubah tanggalkadaluarsa makanan karena format tanggal tidak valid.");
                    }
                    break;
                case 6:
                    System.out.print("Masukkan nama makanan baru: ");
                    String namaBaruAll = input.nextLine();
    
                    // Menampilkan menu jenis makanan
                    System.out.println("Jenis Makanan:");
                    System.out.println("1. Makanan Segar");
                    System.out.println("2. Makanan Olahan");
                    System.out.println("3. Makanan Ringan");
    
                    String jenisBaruAll = "";
                    boolean validInput1 = false;
                    while (!validInput1) {
                        System.out.print("Masukkan nomor jenis makanan baru: ");
                        if (input.hasNextInt()) {
                            int jenisBaruAllPilihan = input.nextInt();
                            input.nextLine(); // Consume newline
    
                            switch (jenisBaruAllPilihan) {
                                case 1:
                                    jenisBaruAll = "Makanan Segar";
                                    validInput1 = true;
                                    break;
                                case 2:
                                    jenisBaruAll = "Makanan Olahan";
                                    validInput1 = true;
                                    break;
                                case 3:
                                    jenisBaruAll = "Makanan Ringan";
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
                        System.out.print("Masukkan stok makanan baru: ");
                        while (!input.hasNextInt()) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan stok makanan baru: ");
                        }
                        stokBaruAll = input.nextInt();
                        input.nextLine(); // Consume newline
                        if (stokBaruAll <= 0) {
                            System.out.println("Stok harus berupa bilangan bulat positif. Silakan masukkan lagi.");
                        }
                    }

                    double hargaBaruAll = -1;
                    while (hargaBaruAll <= 0) {
                        System.out.print("Masukkan harga makanan baru: ");
                        while (!input.hasNextDouble()) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                            input.next(); // Consume the invalid input
                            System.out.print("Masukkan harga makanan baru: ");
                        }
                        hargaBaruAll = input.nextDouble();
                        input.nextLine(); // Consume newline
                        if (hargaBaruAll <= 0) {
                            System.out.println("Harga harus berupa bilangan positif. Silakan masukkan lagi.");
                        }
                    }
    
                    System.out.print("Masukkan tanggal kadaluarsa makanan baru (Format: dd-MM-yyyy): ");
                    String kadaluarsaBaruStrAll = input.nextLine();
                    Date kadaluarsaBaruAll = parseDate(kadaluarsaBaruStrAll);
                    if (kadaluarsaBaruAll != null) {
                        // Inisialisasi objek JenisMakanan berdasarkan pilihan jenis baru
                        JenisMakanan jenisMakananBaru = null;
                        switch (jenisBaruAll) {
                            case "Makanan Segar":
                                jenisMakananBaru = new MakananSegar();
                                break;
                            case "Makanan Olahan":
                                jenisMakananBaru = new MakananOlahan();
                                break;
                            case "Makanan Ringan":
                                jenisMakananBaru = new MakananRingan();
                                break;
                        }
                        
                        // Set semua data baru ke makananToUpdate
                        makananToUpdate.setMakananNama(namaBaruAll);
                        makananToUpdate.setJenisMakanan(jenisMakananBaru);
                        makananToUpdate.setMakananStok(stokBaruAll);
                        makananToUpdate.setMakananHarga(hargaBaruAll);
                        makananToUpdate.setMakananKadaluarsa(kadaluarsaBaruAll);
                        updateMakanan(makananToUpdate, id);
                        System.out.println("Data makanan berhasil diubah!");
                    } else {
                        System.out.println("Gagal mengubah data makanan karena format tanggal tidak valid.");
                    }
                    break;
                case 7:
                    System.out.println("Kembali ke menu utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } else {
            System.out.println("ID makanan tidak valid.");
        }
        pause(1000);
    }
    
    public static void hapusMakanan(Scanner input) {
        List<Makanan> daftarMakanan = readMakanan();
        if (daftarMakanan.isEmpty()) {
            System.out.println("Tidak ada data makanan.");
            return;
        }

        // Tampilkan daftar makanan
        lihatMakanan(input);

        System.out.print("Pilih ID makanan yang ingin dihapus: ");
        while (!input.hasNextInt()) {
            System.out.println("ID harus berupa angka. Silakan masukkan lagi.");
            input.next(); // Consume the invalid input
            System.out.print("Pilih ID makanan yang ingin dihapus: ");
        }
        int id = input.nextInt();
        input.nextLine(); // Consume newline

        boolean idValid = false;
        Makanan makananToRemove = null;

        for (Makanan makanan : daftarMakanan) {
            if (makanan.getMakananId() == id) {
                idValid = true;
                makananToRemove = makanan;
                break;
            }
        }

        if (idValid && makananToRemove != null) {
            deleteMakanan(id);
            System.out.println("Makanan dengan ID" + makananToRemove.getMakananId() + " berhasil dihapus!");
        } else {
            System.out.println("ID makanan tidak valid.");
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
    public static ArrayList<Makanan> readMakanan() {
        connection();
        ArrayList<Makanan> makananList = new ArrayList<>();
        try {
            statement = (Statement) CONN.createStatement();
            result = statement.executeQuery("SELECT * FROM tbmakanan");
            while (result.next()) {
                int id = result.getInt("idMakanan"); // Mengambil ID dari database
                String nama = result.getString("namaMakanan");
                String jenis = result.getString("jenisMakanan");
                int stok = result.getInt("stokMakanan");
                double harga = result.getDouble("hargaMakanan");
                Date kadaluarsa = result.getDate("kadaluarsaMakanan");

                // Inisialisasi objek JenisMakanan berdasarkan jenis makanan dari database
                JenisMakanan jenisMakanan = null;
                switch (jenis) {
                    case "Makanan Segar":
                        jenisMakanan = new MakananSegar();
                        break;
                    case "Makanan Olahan":
                        jenisMakanan = new MakananOlahan();
                        break;
                    case "Makanan Ringan":
                        jenisMakanan = new MakananRingan();
                        break;
                    default:
                        // Handle jenis makanan yang tidak dikenal jika diperlukan
                        break;
                }

                // Buat objek Makanan dengan menggunakan jenisMakanan yang telah diinisialisasi
                Makanan makanan = new Makanan(id, nama, stok, harga, kadaluarsa, jenisMakanan);
                makananList.add(makanan);
            }
            statement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data makanan.");
            e.printStackTrace();
        }
        return makananList;
    }
    
    public static void insertMakanan(String nama, String jenis, int stok, double harga, Date kadaluarsa) {
        connection();
        try {
            String query = "INSERT INTO tbmakanan (namaMakanan, jenisMakanan, stokMakanan, hargaMakanan, kadaluarsaMakanan) VALUES (?, ?, ?, ?, ?)";
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
            System.out.println("Gagal menambahkan makanan.");
            e.printStackTrace();
        }
    }

    public static void updateMakanan(Makanan makanan, int id) {
        connection();
        try {
            String query = "UPDATE tbmakanan SET namaMakanan = ?, jenisMakanan = ?, stokMakanan = ?, hargaMakanan = ?, kadaluarsaMakanan = ? WHERE idMakanan = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setString(1, makanan.getMakananNama());
            Pstatement.setString(2, makanan.getJenisMakanan().getJenis()); // Mengambil jenis makanan dari objek JenisMakanan
            Pstatement.setInt(3, makanan.getMakananStok());
            Pstatement.setDouble(4, makanan.getMakananHarga());
            Pstatement.setDate(5, new java.sql.Date(makanan.getMakananKadaluarsa().getTime()));
            Pstatement.setInt(6, id);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengubah makanan.");
            e.printStackTrace();
        }
    }

    // Method untuk menghapus makanan dari database
    public static void deleteMakanan(int id) {
        connection();
        try {
            String query = "DELETE FROM tbmakanan WHERE idMakanan = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, id);
            Pstatement.executeUpdate();
            Pstatement.close();
            CONN.close();
            
            // Update IDs after deletion
            updateIdsAfterDeletion(id); // Panggil fungsi untuk memperbarui ID setelah penghapusan
        } catch (SQLException e) {
            System.out.println("Gagal menghapus makanan.");
            e.printStackTrace();
        }
    }
    
    private static void updateIdsAfterDeletion(int deletedId) {
        connection();
        try {
            // Ambil ID makanan yang lebih besar dari ID yang dihapus
            String query = "SELECT idMakanan FROM tbmakanan WHERE idMakanan > ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, deletedId);
            ResultSet resultSet = Pstatement.executeQuery();
            
            // Simpan ID yang perlu diperbarui dalam daftar
            List<Integer> idsToUpdate = new ArrayList<>();
            while (resultSet.next()) {
                idsToUpdate.add(resultSet.getInt("idMakanan"));
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
            // Perbarui ID makanan secara individu
            String query = "UPDATE tbmakanan SET idMakanan = ? WHERE idMakanan = ?";
            Pstatement = (PreparedStatement) CONN.prepareStatement(query);
            Pstatement.setInt(1, newId);
            Pstatement.setInt(2, oldId);
            Pstatement.executeUpdate();
            Pstatement.close();
        } catch (SQLException e) {
            System.out.println("Gagal mengupdate ID makanan.");
            e.printStackTrace();
        }
    }
}


