package Sistem_Pasar_Elektronik;

import java.io.IOException;

import static Sistem_Pasar_Elektronik.Main.ListProduk;
import static Sistem_Pasar_Elektronik.Main.ListPembeli;
import static Sistem_Pasar_Elektronik.Main.ListPenjual;
import static Sistem_Pasar_Elektronik.Main.ListTransaksi;

public class Admin extends Pengguna {
  String nama_admin;

  public Admin(String username, String password, String role, String nama_admin) {
    super(username, password, role);
    this.nama_admin = nama_admin;
  }

  public String getNama_admin() {
    return this.nama_admin;
  }

  public void setNama_admin(String nama_admin) {
    this.nama_admin = nama_admin;
  }

  // untuk admin lihat daftar pembeli
  public void lihat_pembeli() throws IOException {
    clearScreen();
    if (ListPembeli.size() == 0) {
      System.out.println("");
      System.out.println("\tTidak Ada Pembeli");
      lanjutkan();
      return;
    }

    System.out.println("\t|=====================================================================|");
    System.out.println("\t|                          Daftar Pembeli                             |");
    System.out.println("\t|=====================================================================|");
    System.out.printf("\t| %-3s| %-10s |   %-14s |          %-15s |\n", "No", "Username Pembeli", "Nama Pembeli", "Alamat");
    System.out.println("\t|=====================================================================|");
    int nomor = 1;
    for (int i = 0; i < ListPembeli.size(); i++) {
      if (nomor > 1) {
        System.out.println("\t|---------------------------------------------------------------------|");
      }
      System.out.printf("\t| %-3d| %-16s | %-17s| %-24s |\n", nomor, ListPembeli.get(i).getUsername(), ListPembeli.get(i).getNama_pembeli(), ListPembeli.get(i).getAlamat());
      nomor++;
    }
    System.out.println("\t|=====================================================================|");
    lanjutkan();
  }

  // untuk admin lihat daftar penjual
  public void lihat_penjual() throws IOException {
    clearScreen();
    if (ListPenjual.size() == 0) {
      System.out.println("");
      System.out.println("\tTidak Ada Penjual");
      lanjutkan();
      return;
    }

    System.out.println("\t|===========================================================================================|");
    System.out.println("\t|                                      Daftar Penjual                                       |");
    System.out.println("\t|===========================================================================================|");
    System.out.printf("\t| %-3s| %-10s |     %-13s |   %-14s | %5s |  %-9s |\n", "No", "Username Penjual", "Nama Toko", "Nama Penjual", "Jumlah Produk", "Kategori");
    System.out.println("\t|===========================================================================================|");
    int nomor = 1;
    for (int i = 0; i < ListPenjual.size(); i++) {
      if (nomor > 1) {
        System.out.println("\t|-------------------------------------------------------------------------------------------|");
      }
      System.out.printf("\t| %-3d| %-16s | %-18s| %-17s|       %-7d | %-10s |\n", nomor, ListPenjual.get(i).getUsername(), ListPenjual.get(i).getNama_toko(), ListPenjual.get(i).getNama_penjual(), ListPenjual.get(i).jumlah_produk(), ListPenjual.get(i).getKategori());
      nomor++;
    }
    System.out.println("\t|===========================================================================================|");
    lanjutkan();
  }

  // untuk admin lihat daftar produk
  public void lihat_produk() throws IOException {
    clearScreen();
    System.out.println("");
    if (ListProduk.size() == 0) {
      System.out.println("");
      System.out.println("\tTidak Ada Produk");
      lanjutkan();
      return;
    }

    System.out.println("\t|=======================================================================|");
    System.out.println("\t|                             Daftar Produk                             |");
    System.out.println("\t|=======================================================================|");
    System.out.printf("\t| %-3s| %-10s |     %-16s| %-6s| %5s | %7s |\n", "No", "Username Penjual", "Nama Produk", "Harga", "Stock", "Terjual");
    System.out.println("\t|=======================================================================|");
    int nomor = 1;
    for (int i = 0; i < ListProduk.size(); i++) {
      if (ListProduk.get(i).getStok_produk() >= 0) {
        if (nomor > 1) {
          System.out.println("\t|-----------------------------------------------------------------------|");
        }
        System.out.printf("\t| %-3d| %-16s | %-20s| %-6d|  %-4d | %4d    |\n", nomor, ListProduk.get(i).getUsername_penjual(), ListProduk.get(i).getNama_produk(), ListProduk.get(i).getHarga_produk(), ListProduk.get(i).getStok_produk(), ListProduk.get(i).getJumlah_terjual());
        nomor++;
      }
    }
    System.out.println("\t|=======================================================================|");
    lanjutkan();
  }

  // untuk admin lihat daftar transaksi
  @Override
  public void riwayat_transaksi() throws IOException {
    clearScreen();
    if (ListTransaksi.size() == 0) {
      System.out.println("");
      System.out.println("\tTidak Ada Riwayat Transaksi");
      lanjutkan();
      return;
    }

    System.out.println("\t|======================================================================================================================|");
    System.out.println("\t|                                                  Riwayat Transaksi                                                   |");
    System.out.println("\t|======================================================================================================================|");
    System.out.printf("\t| %-3s| %-12s | %-10s |     %-16s|  %-6s | %6s |  %-6s |        %-16s|\n", "No", "Username Pembeli", "Username Penjual", "Nama Produk", "Harga", "Jumlah", "Total", "Tanggal");
    System.out.println("\t|======================================================================================================================|");
    int nomor = 1;
    for (Transaksi data : ListTransaksi) {
      if (nomor > 1) {
        System.out.println("\t|----------------------------------------------------------------------------------------------------------------------|");
      }
      System.out.printf("\t| %-3s| %-16s | %-16s | %-20s|  %-7d|   %-4d | %-7d | %-23s|\n", nomor, data.getUsername_pembeli(), data.getUsername_penjual(), data.getNama_produk(), data.getHarga_produk(), data.getJumlah_produk(), data.getTotal_harga(), data.getTanggal());
      nomor++;
    }
    System.out.println("\t|======================================================================================================================|");
    lanjutkan();
  }
}
