import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum ProductType {
    MIE,
    BUAH_BUAHAN,
    FROZEN_FOOD
}

interface Discountable {
    double calculateDiscount(int amount);
}

interface Expireable {
    String getExpiry();
}

abstract class Product {

    private String id;
    private String nama;
    private double harga;
    private int stok;

    public Product(String id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public abstract String getProductDetail();

    public abstract double calculateSubTotal(int amount);
}

class Mie extends Product implements Discountable {

    private String rasa;
    private double berat;

    public Mie(String id, String nama, double harga, int stok,
               String rasa, double berat) {

        super(id, nama, harga, stok);
        this.rasa = rasa;
        this.berat = berat;
    }

    public String getRasa() {
        return rasa;
    }

    public double getBerat() {
        return berat;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    @Override
    public double calculateDiscount(int amount) {

        double total = getHarga() * amount;

        if (amount >= 20) {
            return total * 0.10;
        } else if (amount >= 10) {
            return total * 0.05;
        } else {
            return 0;
        }
    }

    @Override
    public double calculateSubTotal(int amount) {

        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getProductDetail() {

        return "Mie [" + getNama() + "]"
                + " (ID: " + getId() + ")"
                + " - Harga: Rp" + getHarga()
                + " | Stok: " + getStok()
                + " | Rasa: " + rasa
                + " | Berat: " + berat + "g";
    }
}

class BuahBuahan extends Product
        implements Discountable, Expireable {

    private String jenis;
    private double berat;
    private String tanggalKadaluarsa;

    public BuahBuahan(String id, String nama, double harga, int stok,
                       String jenis, double berat,
                       String tanggalKadaluarsa) {

        super(id, nama, harga, stok);
        this.jenis = jenis;
        this.berat = berat;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getJenis() {
        return jenis;
    }

    public double getBerat() {
        return berat;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }

    public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    @Override
    public double calculateDiscount(int amount) {

        double total = getHarga() * amount;

        if (amount >= 15) {
            return total * 0.05;
        } else if (amount >= 10) {
            return total * 0.03;
        } else if (amount >= 5) {
            return total * 0.02;
        } else {
            return 0;
        }
    }

    @Override
    public double calculateSubTotal(int amount) {

        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getExpiry() {
        return tanggalKadaluarsa;
    }

    @Override
    public String getProductDetail() {

        return "Buah-buahan [" + getNama() + "]"
                + " (ID: " + getId() + ")"
                + " - Harga: Rp" + getHarga()
                + " | Stok: " + getStok()
                + " | Jenis: " + jenis
                + " | Berat: " + berat + "kg"
                + " | Kadaluarsa: " + tanggalKadaluarsa;
    }
}

class FrozenFood extends Product
        implements Discountable, Expireable {

    private double suhuPenyimpanan;
    private String tanggalKadaluarsa;

    public FrozenFood(String id, String nama, double harga, int stok,
                      double suhuPenyimpanan,
                      String tanggalKadaluarsa) {

        super(id, nama, harga, stok);
        this.suhuPenyimpanan = suhuPenyimpanan;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public double getSuhuPenyimpanan() {
        return suhuPenyimpanan;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setSuhuPenyimpanan(double suhuPenyimpanan) {
        this.suhuPenyimpanan = suhuPenyimpanan;
    }

    public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    @Override
    public double calculateDiscount(int amount) {

        double total = getHarga() * amount;

        if (amount >= 8) {
            return total * 0.08;
        } else if (amount >= 4) {
            return total * 0.04;
        } else {
            return 0;
        }
    }

    @Override
    public double calculateSubTotal(int amount) {

        return (getHarga() * amount) - calculateDiscount(amount);
    }

    @Override
    public String getExpiry() {
        return tanggalKadaluarsa;
    }

    @Override
    public String getProductDetail() {

        return "Frozen Food [" + getNama() + "]"
                + " (ID: " + getId() + ")"
                + " - Harga: Rp" + getHarga()
                + " | Stok: " + getStok()
                + " | Suhu: " + suhuPenyimpanan + "°C"
                + " | Kadaluarsa: " + tanggalKadaluarsa;
    }
}

class InputUtil {

    private static Scanner input = new Scanner(System.in);

    public static String inputString(String pesan) {
        System.out.print(pesan);
        return input.nextLine();
    }

    public static int inputInt(String pesan) {
        System.out.print(pesan);
        return Integer.parseInt(input.nextLine());
    }

    public static double inputDouble(String pesan) {
        System.out.print(pesan);
        return Double.parseDouble(input.nextLine());
    }
}

public class Main {

    private static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        int pilihan;

        do {

            System.out.println();
            System.out.println("===============================");
            System.out.println("        MINI MARKET");
            System.out.println("===============================");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Print Semua Produk");
            System.out.println("0. Keluar");
            System.out.println("===============================");

            pilihan = InputUtil.inputInt("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tambahProduk();
                    break;

                case 2:
                    printSemuaProduk();
                    break;

                case 0:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }

        } while (pilihan != 0);
    }

    public static void tambahProduk() {

        System.out.println();
        System.out.println("TAMBAH PRODUK");
        System.out.println("1. Mie");
        System.out.println("2. Buah-buahan");
        System.out.println("3. Frozen Food");

        int pilihan = InputUtil.inputInt("Pilih kategori: ");

        if (pilihan == 1) {
            tambahMie();

        } else if (pilihan == 2) {
            tambahBuah();

        } else if (pilihan == 3) {
            tambahFrozenFood();

        } else {
            System.out.println("Kategori tidak tersedia.");
        }
    }


    public static void tambahMie() {

        String id = InputUtil.inputString("ID: ");
        String nama = InputUtil.inputString("Nama: ");
        double harga = InputUtil.inputDouble("Harga: ");
        int stok = InputUtil.inputInt("Stok: ");
        String rasa = InputUtil.inputString("Rasa: ");
        double berat = InputUtil.inputDouble("Berat (gram): ");

        Product mie = new Mie(
                id,
                nama,
                harga,
                stok,
                rasa,
                berat
        );

        products.add(mie);

        System.out.println("Produk Mie berhasil ditambahkan.");
    }

    public static void tambahBuah() {

        String id = InputUtil.inputString("ID: ");
        String nama = InputUtil.inputString("Nama: ");
        double harga = InputUtil.inputDouble("Harga: ");
        int stok = InputUtil.inputInt("Stok: ");
        String jenis = InputUtil.inputString("Jenis (Lokal/Import): ");
        double berat = InputUtil.inputDouble("Berat (kg): ");
        String tanggal = InputUtil.inputString(
                "Tanggal Kadaluarsa (YYYY-MM-DD): "
        );

        Product buah = new BuahBuahan(
                id,
                nama,
                harga,
                stok,
                jenis,
                berat,
                tanggal
        );

        products.add(buah);

        System.out.println("Produk Buah-buahan berhasil ditambahkan.");
    }

    public static void tambahFrozenFood() {

        String id = InputUtil.inputString("ID: ");
        String nama = InputUtil.inputString("Nama: ");
        double harga = InputUtil.inputDouble("Harga: ");
        int stok = InputUtil.inputInt("Stok: ");
        double suhu = InputUtil.inputDouble("Suhu Penyimpanan: ");
        String tanggal = InputUtil.inputString(
                "Tanggal Kadaluarsa (YYYY-MM-DD): "
        );

        Product frozen = new FrozenFood(
                id,
                nama,
                harga,
                stok,
                suhu,
                tanggal
        );

        products.add(frozen);

        System.out.println("Produk Frozen Food berhasil ditambahkan.");
    }


    public static void printSemuaProduk() {

        System.out.println();
        System.out.println("SEMUA PRODUK");

        if (products.isEmpty()) {

            System.out.println("Belum ada produk.");

        } else {

            for (Product product : products) {

                System.out.println(
                        "• " + product.getProductDetail()
                );
            }
        }
    }
}






