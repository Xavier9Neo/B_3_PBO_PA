import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int pilihan; 
        
        do {
            clearScreen();
            System.out.println("================================");
            System.out.println("|--------- MENU LOGIN ---------|");
            System.out.println("================================");
            System.out.println("| [ 1 ] LOGIN SEBAGAI ADMIN    |");
            System.out.println("| [ 2 ] LOGIN SEBAGAI MANAJER  |");
            System.out.println("| [ 3 ] KELUAR DARI PROGRAM    |");
            System.out.println("================================");
            System.out.print("Pilihan Anda: ");
            
            if (input.hasNextInt()) {
                pilihan = input.nextInt();
                
                switch (pilihan) {
                    case 1:
                        loginAdmin(input);
                        break;
                    case 2:
                        loginManajer(input);
                        break;
                    case 3:     
                        System.out.println("Kembali ke menu awal...");
                        break;
                    default:
                        System.out.println("================================");
                        System.out.println("|   INPUTAN ANDA TIDAK VALID   |");
                        System.out.println("================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("================================");
                System.out.println("|   INPUTAN ANDA TIDAK VALID   |");
                System.out.println("================================");
                pause(1000);
                pilihan = 0;
            }
        } while (pilihan != 3);
            
        input.close();        
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }    
    
    private static void loginAdmin(Scanner input) throws IOException {
        final String ADMIN_USERNAME = "admin";
        final String ADMIN_PASSWORD = "123";
        int failedLoginAttempts = 0;

        while (failedLoginAttempts < 3) {
            clearScreen();
            System.out.println("=================================");
            System.out.println("|          LOGIN ADMIN          |");
            System.out.println("=================================");    
            System.out.print("Masukkan username: ");
            String username = input.next();
            System.out.print("Masukkan password: ");
            String password = input.next();
    
            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                System.out.println("=================================");
                System.out.println("|         LOGIN BERHASIL        |");
                System.out.println("=================================");
                pause(1000);
                menuAdmin(input);
                return;
            } else {
                failedLoginAttempts++;
                int remainingAttempts = 3 - failedLoginAttempts;
                if (remainingAttempts > 0) { 
                    System.out.println("=================================");
                    System.out.println("|  USERNAME / PASSWORD SALAH !  |");
                    System.out.println("|    PERCOBAAN TERSISA : " + remainingAttempts + "/3    |");
                    System.out.println("=================================");
                    pause(1000);
                } else {
                    System.out.println("=================================");
                    System.out.println("|  MAAF ANDA TIDAK DAPAT LOGIN  |");
                    System.out.println("=================================");
                    pause(1000);
                    return;
                }
            }
        }
    }

    private static void loginManajer(Scanner input) throws IOException {
        final String MANAJER_USERNAME = "manajer";
        final String MANAJER_PASSWORD = "123";
        int failedLoginAttempts = 0;

        while (failedLoginAttempts < 3) {
            clearScreen();
            System.out.println("=================================");
            System.out.println("|         LOGIN MANAJER         |");
            System.out.println("=================================");    
            System.out.print("Masukkan username: ");
            String username = input.next();
            System.out.print("Masukkan password: ");
            String password = input.next();

            if (username.equals(MANAJER_USERNAME) && password.equals(MANAJER_PASSWORD)) {
                System.out.println("=================================");
                System.out.println("|         LOGIN BERHASIL        |");
                System.out.println("=================================");
                pause(1000);
                menuManajer(input);
                return;
            } else {
                failedLoginAttempts++;
                int remainingAttempts = 3 - failedLoginAttempts;
                if (remainingAttempts > 0) { 
                    System.out.println("=================================");
                    System.out.println("|  USERNAME / PASSWORD SALAH !  |");
                    System.out.println("|    PERCOBAAN TERSISA : " + remainingAttempts + "/3    |");
                    System.out.println("=================================");
                    pause(1000);
                } else {
                    System.out.println("=================================");
                    System.out.println("|  MAAF ANDA TIDAK DAPAT LOGIN  |");
                    System.out.println("=================================");
                    pause(1000);
                    return;
                }
            }
        }
    }

    private static void menuAdmin(Scanner input) throws IOException {
        int pilihan;
        do {
            clearScreen();
            System.out.println("=======================================");
            System.out.println("|------------- MENU ADMIN ------------|");
            System.out.println("=======================================");
            System.out.println("| [ 1 ] MELIHAT DATA BARANG           |");
            System.out.println("| [ 2 ] MENAMBAH DATA BARANG          |");
            System.out.println("| [ 3 ] MENGUBAH DATA BARANG          |");
            System.out.println("| [ 4 ] MENGHAPUS DATA BARANG         |");
            System.out.println("| [ 5 ] LAPORAN DATA BARANG           |");
            System.out.println("| [ 6 ] KEMBALI KE MENU AWAL          |");
            System.out.println("=======================================");
            System.out.print("Pilihan Anda: ");
            
            if (input.hasNextInt()) {
                pilihan = input.nextInt();
                
                switch (pilihan) {
                    case 1:
                        lihatDataBarang(input);
                        break;
                    case 2:
                        tambahDataBarang(input);
                        break;
                    case 3:
                        ubahDataBarang(input);
                        break;
                    case 4:
                        hapusDataBarang(input);
                        break;
                    case 5:
                        buatLaporan(input);
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("=======================================");
                        System.out.println("|       INPUTAN ANDA TIDAK VALID      |");
                        System.out.println("=======================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("=======================================");
                System.out.println("|       INPUTAN ANDA TIDAK VALID      |");
                System.out.println("=======================================");
                pause(1000);
                pilihan = 0;
            }
        } while (pilihan != 6); 
    }

    private static void menuManajer(Scanner input) throws IOException {
        int pilihan;
        do {
            clearScreen();
            System.out.println("=======================================");
            System.out.println("|------------ MENU MANAJER -----------|");
            System.out.println("=======================================");
            System.out.println("| [ 1 ] MELIHAT LAPORAN DATA BARANG   |");
            System.out.println("| [ 2 ] KEMBALI KE MENU AWAL          |");
            System.out.println("=======================================");
            System.out.print("Pilihan Anda: ");
            
            if (input.hasNextInt()) {
                pilihan = input.nextInt();
                
                switch (pilihan) {
                    case 1:
                        lihatLaporan(input);
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("=======================================");
                        System.out.println("|       INPUTAN ANDA TIDAK VALID      |");
                        System.out.println("=======================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("=======================================");
                System.out.println("|       INPUTAN ANDA TIDAK VALID      |");
                System.out.println("=======================================");
                pause(1000);
                pilihan = 0;
            }
        } while (pilihan != 2);
    }

    private static void lihatDataBarang(Scanner input) throws IOException {
        // Tambahkan logika untuk melihat data barang
        int pilih;
        do {
            clearScreen();
            System.out.println("====================================");
            System.out.println("|---------- LIHAT BARANG ----------|");
            System.out.println("====================================");
            System.out.println("| [ 1 ] MAKANAN                    |");
            System.out.println("| [ 2 ] MINUMAN                    |");
            System.out.println("| [ 3 ] PERABOTAN RUMAH TANGGA     |");
            System.out.println("| [ 4 ] KEMBALI KE MENU ADMIN      |");
            System.out.println("====================================");
            System.out.print("Pilihan Anda: ");
            if (input.hasNextInt()) {
                pilih = input.nextInt();
                switch (pilih) {
                    case 1:
                        clearScreen();
                        Makanan.lihatMakanan2(input);
                        menuAdmin(input);
                        break;
                    case 2:
                        clearScreen();
                        Minuman.lihatMinuman2(input);
                        menuAdmin(input);
                        break;
                    case 3:
                        clearScreen();
                        Perabotan.lihatPerabotan2(input);
                        menuAdmin(input);
                        break;
                    case 4:
                        clearScreen();
                        menuAdmin(input);
                        break;
                    default:
                        System.out.println("====================================");
                        System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                        System.out.println("====================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("====================================");
                System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                System.out.println("====================================");
                pause(1000);
                pilih = 0;
            }
        } while (pilih != 4);
    }

    private static void tambahDataBarang(Scanner input) throws IOException {
        int pilih;
        do {
            clearScreen();
            System.out.println("====================================");
            System.out.println("|---------- TAMBAH BARANG ---------|");
            System.out.println("====================================");
            System.out.println("| [ 1 ] MAKANAN                    |");
            System.out.println("| [ 2 ] MINUMAN                    |");
            System.out.println("| [ 3 ] PERABOTAN RUMAH TANGGA     |");
            System.out.println("| [ 4 ] KEMBALI KE MENU ADMIN      |");
            System.out.println("====================================");
            System.out.print("Pilihan Anda: ");
            if (input.hasNextInt()) {
                pilih = input.nextInt();
                switch (pilih) {
                    case 1:
                        clearScreen();
                        Makanan.tambahMakanan(input);
                        menuAdmin(input);
                        break;
                    case 2:
                        clearScreen();
                        Minuman.tambahMinuman(input);
                        menuAdmin(input);
                        break;
                    case 3:
                        clearScreen();
                        Perabotan.tambahPerabotan(input);
                        menuAdmin(input);
                        break;
                    case 4:
                        clearScreen();
                        menuAdmin(input);
                        break;
                    default:
                        System.out.println("====================================");
                        System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                        System.out.println("====================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("====================================");
                System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                System.out.println("====================================");
                pause(1000);
                pilih = 0;
            }
        } while (pilih != 4);
    }

    private static void ubahDataBarang(Scanner input) throws IOException {
        int pilih;
        do {
            clearScreen();
            System.out.println("====================================");
            System.out.println("|----------- UBAH BARANG ----------|");
            System.out.println("====================================");
            System.out.println("| [ 1 ] MAKANAN                    |");
            System.out.println("| [ 2 ] MINUMAN                    |");
            System.out.println("| [ 3 ] PERABOTAN RUMAH TANGGA     |");
            System.out.println("| [ 4 ] KEMBALI KE MENU ADMIN      |");
            System.out.println("====================================");
            System.out.print("Pilihan Anda: ");
            if (input.hasNextInt()) {
                pilih = input.nextInt();
                switch (pilih) {
                    case 1:
                        clearScreen();
                        Makanan.ubahMakanan(input);
                        menuAdmin(input);
                        break;
                    case 2:
                        clearScreen();
                        Minuman.ubahMinuman(input);
                        menuAdmin(input);
                        break;
                    case 3:
                        clearScreen();
                        Perabotan.ubahPerabotan(input);
                        menuAdmin(input);
                        break;
                    case 4:
                        clearScreen();
                        menuAdmin(input);
                        break;
                    default:
                        System.out.println("====================================");
                        System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                        System.out.println("====================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("====================================");
                System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                System.out.println("====================================");
                pause(1000);
                pilih = 0;
            }
        } while (pilih != 4);

    }

    private static void hapusDataBarang(Scanner input) throws IOException {
        int pilih;
        do {
            clearScreen();
            System.out.println("====================================");
            System.out.println("|---------- HAPUS BARANG ----------|");
            System.out.println("====================================");
            System.out.println("| [ 1 ] MAKANAN                    |");
            System.out.println("| [ 2 ] MINUMAN                    |");
            System.out.println("| [ 3 ] PERABOTAN RUMAH TANGGA     |");
            System.out.println("| [ 4 ] KEMBALI KE MENU ADMIN      |");
            System.out.println("====================================");
            System.out.print("Pilihan Anda: ");
            if (input.hasNextInt()) {
                pilih = input.nextInt();
                switch (pilih) {
                    case 1:
                        clearScreen();
                        Makanan.hapusMakanan(input);
                        menuAdmin(input);
                        break;
                    case 2:
                        clearScreen();
                        Minuman.hapusMinuman(input);
                        menuAdmin(input);
                        break;
                    case 3:
                        clearScreen();
                        Perabotan.hapusPerabotan(input);
                        menuAdmin(input);
                        break;
                    case 4:
                        clearScreen();
                        menuAdmin(input);
                        break;
                    default:
                        System.out.println("====================================");
                        System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                        System.out.println("====================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("====================================");
                System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                System.out.println("====================================");
                pause(1000);
                pilih = 0;
            }
        } while (pilih != 4);
    }

    private static void buatLaporan(Scanner input) throws IOException {
        int pilih;
        do {
            clearScreen();
            System.out.println("====================================");
            System.out.println("|--------- LAPORAN BARANG ---------|");
            System.out.println("====================================");
            System.out.println("| [ 1 ] BUAT LAPORAN MASUK         |");
            System.out.println("| [ 2 ] BUAT LAPORAN KELUAR        |");
            System.out.println("| [ 3 ] LIHAT LAPORAN MASUK        |");
            System.out.println("| [ 4 ] LIHAT LAPORAN KELUAR       |");
            System.out.println("| [ 5 ] KEMBALI KE MENU ADMIN      |");
            System.out.println("====================================");
            System.out.print("Pilihan Anda: ");
            if (input.hasNextInt()) {
                pilih = input.nextInt();
                Laporan laporan = null; 
                switch (pilih) {
                    case 1:
                        clearScreen();
                        Laporan.tambahLaporanMasuk(input);
                        break;
                    case 2:
                        clearScreen();
                        Laporan.tambahLaporanKeluar(input);
                        break;
                    case 3:
                        clearScreen();
                        laporan = new Laporan(); // inisialisasi di setiap case
                        laporan.lihatLaporanMasuk();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        input.nextLine(); // Membersihkan karakter newline
                        input.nextLine(); // Menunggu pengguna menekan enter
                        break;
                    case 4:
                        clearScreen();
                        laporan = new Laporan(); // inisialisasi di setiap case
                        laporan.lihatLaporanKeluar();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        input.nextLine(); // Membersihkan karakter newline
                        input.nextLine(); // Menunggu pengguna menekan enter
                        break;
                    case 5:
                        menuAdmin(input);
                        break;
                    default:
                        System.out.println("====================================");
                        System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                        System.out.println("====================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("====================================");
                System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                System.out.println("====================================");
                pause(1000);
                pilih = 0;
            }
        } while (pilih != 5);
    }

    private static void lihatLaporan(Scanner input) throws IOException {
        int pilih;
        do {
            clearScreen();
            System.out.println("====================================");
            System.out.println("|----------- MINI MARKET ----------|");
            System.out.println("====================================");
            System.out.println("| [ 1 ] LIHAT LAPORAN MASUK        |");
            System.out.println("| [ 2 ] LIHAT LAPORAN KELUAR       |");
            System.out.println("| [ 3 ] KEMBALI KE MENU MANAJER    |");
            System.out.println("====================================");
            System.out.print("Pilihan Anda: ");
            if (input.hasNextInt()) {
                pilih = input.nextInt();
                Laporan laporan = null;  
                switch (pilih) {
                    case 1:
                        clearScreen();
                        laporan = new Laporan(); // inisialisasi di setiap case
                        laporan.lihatLaporanMasuk();
                        break;
                    case 2:
                        clearScreen();
                        laporan = new Laporan(); // inisialisasi di setiap case
                        laporan.lihatLaporanKeluar();
                        break;
                    case 3:
                        menuManajer(input);
                        break;
                    default:
                        System.out.println("====================================");
                        System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                        System.out.println("====================================");
                        pause(1000);
                }
            } else {
                input.next();
                System.out.println("====================================");
                System.out.println("|     INPUTAN ANDA TIDAK VALID     |");
                System.out.println("====================================");
                pause(1000);
                pilih = 0;
            }
            if (pilih != 3) { // Menunggu Enter hanya jika pengguna tidak memilih kembali ke menu admin
                System.out.println("Tekan Enter untuk melanjutkan...");
                input.nextLine(); // Membersihkan karakter newline
                input.nextLine(); // Menunggu pengguna menekan enter
            }
        } while (pilih != 3);
    }
}