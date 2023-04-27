package Sistem_Pasar_Elektronik;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static Sistem_Pasar_Elektronik.Main.ListProduk;
import static Sistem_Pasar_Elektronik.Main.ListPembeli;
import static Sistem_Pasar_Elektronik.Main.ListPenjual;
import static Sistem_Pasar_Elektronik.Main.ListKeranjang;
import static Sistem_Pasar_Elektronik.Main.ListTransaksi;

public class Pembeli extends Pengguna implements InterfaceCRUD {
  private String nama_pembeli;
  private String alamat;

  public Pembeli(String username, String password, String role, String nama_pembeli, String alamat) {
    super(username, password, role);
    this.nama_pembeli = nama_pembeli;
    this.alamat = alamat;
  }

  public String getNama_pembeli() {
    return this.nama_pembeli;
  }

  public void setNama_pembeli(String nama_pembeli) {
    this.nama_pembeli = nama_pembeli;
  }

  public String getAlamat() {
    return this.alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  // untuk pembeli daftar ke sistem
  static void daftar() throws IOException {
    System.out.println("");
    System.out.print("\tUsername : ");
    String username = input.readLine();
    System.out.print("\tPassword : ");
    String password = input.readLine();
    System.out.print("\tNama     : ");
    String nama_pembeli = input.readLine();
    System.out.print("\tAlamat   : ");
    String alamat = input.readLine();
    for (Pembeli data : ListPembeli) {
      if (data.getUsername().equals(username)) {
        lanjutkan("gagal", "Username telah digunakan");
        return;
      }
    }

    Pembeli pembeli_baru = new Pembeli(username, password, "pembeli", nama_pembeli, alamat);
    ListPembeli.add(pembeli_baru);
    lanjutkan("berhasil", "Anda Berhasil Mendaftar sebagai Pembeli");
  }

  // untuk pembeli lihat produk
  public void lihat_produk() throws IOException {
    try {
      clearScreen();
      if (ListPenjual.size() == 0) {
        System.out.println("\tTidak Ada Toko");
        return;
      }

      System.out.println("\tDaftar Toko");
      for (int i=0; i<ListPenjual.size(); i++) {
        System.out.println("\t[" + (i+1) + "] " + ListPenjual.get(i).getNama_toko());
      }

      System.out.println("");
      System.out.print("\tSilahkan Pilih : ");
      int pilihan =  Integer.parseInt(input.readLine());
  
      if (pilihan <= 0 || pilihan > ListPenjual.size()) {
        System.out.println("");
        System.out.println("\t" + RED + "Pilihan tidak tersedia" + NORMAL);
        return;
      }
  
      String username_penjual = ListPenjual.get(pilihan-1).getUsername();
      boolean produk_kosong = true;
  
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual)) {
          produk_kosong = false;
        }
      }
  
      if (produk_kosong) {
        System.out.println("");
        System.out.println("\tTidak Ada Produk");
        return;
      }

      System.out.println("");
      System.out.println("\t|====================================================|");
      System.out.println("\t|                   Daftar Produk                    |");
      System.out.println("\t|====================================================|");
      System.out.printf("\t| %-3s|     %-16s| %-6s| %5s | %7s |\n", "No", "Nama Produk", "Harga", "Stock", "Terjual");
      System.out.println("\t|====================================================|");
      int nomor = 1;
      for (int i = 0; i < ListProduk.size(); i++) {
        if (ListProduk.get(i).getStok_produk() >= 0 && ListProduk.get(i).getUsername_penjual().equals(username_penjual)) {
          if (nomor > 1) {
            System.out.println("\t|----------------------------------------------------|");
          }
          System.out.printf("\t| %-3d| %-20s| %-6d|  %-4d | %4d    |\n", nomor, ListProduk.get(i).getNama_produk(), ListProduk.get(i).getHarga_produk(), ListProduk.get(i).getStok_produk(), ListProduk.get(i).getJumlah_terjual());
          nomor++;
        }
      }
      System.out.println("\t|====================================================|");
    }
    catch (NumberFormatException nfe) {
      System.out.println("");
      System.out.println("\t" + RED + "Pilihan tidak tersedia" + NORMAL);
    }
  }

  // untuk pembeli tambah produk ke keranjang
  public void tambah_produk() throws IOException {
    try {
      clearScreen();
      if (ListPenjual.size() == 0) {
        System.out.println("\tTidak Ada Penjual");
        lanjutkan();
        return;
      }

      System.out.println("\tDaftar Toko");
      for (int i=0; i<ListPenjual.size(); i++) {
        System.out.println("\t[" + (i+1) + "] " + ListPenjual.get(i).getNama_toko());
      }

      System.out.println("");
      System.out.print("\tSilahkan Pilih : ");
      int pilihan =  Integer.parseInt(input.readLine());
  
      if (pilihan <= 0 || pilihan > ListPenjual.size()) {
        lanjutkan("gagal", "Pilihan tidak tersedia");
        return;
      }
  
      String username_penjual = ListPenjual.get(pilihan-1).getUsername();
      ArrayList<String> daftar_produk = new ArrayList<>();
      boolean produk_kosong = true;
  
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual)) {
          daftar_produk.add(data.getNama_produk());
          produk_kosong = false;
        }
      }
  
      if (produk_kosong) {
        System.out.println("");
        System.out.println("\tTidak Ada Produk");
        lanjutkan();
        return;
      }

      System.out.println("");
      System.out.println("\t|====================================================|");
      System.out.println("\t|                   Daftar Produk                    |");
      System.out.println("\t|====================================================|");
      System.out.printf("\t| %-3s|     %-16s| %-6s| %5s | %7s |\n", "No", "Nama Produk", "Harga", "Stock", "Terjual");
      System.out.println("\t|====================================================|");
      int nomor = 1;
      for (int i = 0; i < ListProduk.size(); i++) {
        if (ListProduk.get(i).getStok_produk() >= 0 && ListProduk.get(i).getUsername_penjual().equals(username_penjual)) {
          if (nomor > 1) {
            System.out.println("\t|----------------------------------------------------|");
          }
          System.out.printf("\t| %-3d| %-20s| %-6d|  %-4d | %4d    |\n", nomor, ListProduk.get(i).getNama_produk(), ListProduk.get(i).getHarga_produk(), ListProduk.get(i).getStok_produk(), ListProduk.get(i).getJumlah_terjual());
          nomor++;
        }
      }
      System.out.println("\t|====================================================|");
      System.out.println("");
      System.out.print("\tSilahkan Pilih Nomor Produk : ");
      int nomor_produk = Integer.parseInt(input.readLine());
      if (nomor_produk <= 0 || nomor_produk > daftar_produk.size()) {
        lanjutkan("gagal", "Pilihan tidak tersedia");
        return;
      }

      String nama_produk = "";
      for (int i=0; i<daftar_produk.size(); i++) {
        if (i == nomor_produk-1) {
          nama_produk = daftar_produk.get(i);
        }
      }

      Produk produk_terpilih = null;
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual) && data.getNama_produk().equals(nama_produk)) {
          produk_terpilih = data;
        }
      }

      System.out.print("\tMasukan Jumlah Produk : ");
      int jumlah_produk = Integer.parseInt(input.readLine());

      if (produk_terpilih.getStok_produk() == 0) {
        lanjutkan("gagal", "Stok Produk sedang Kosong");
        return;
      }

      if (jumlah_produk < 0 || jumlah_produk>produk_terpilih.getStok_produk()) {
        lanjutkan("gagal", "Jumlah Produk tidak valid (Minimal pesanan adalah 1 dan Maksimal pesanan adalah stok produk)");
        return;
      }

      String username_pembeli = this.getUsername();
      int harga_produk = produk_terpilih.getHarga_produk();
      int total_harga = jumlah_produk * harga_produk;
      int jumlah_terjual = produk_terpilih.getJumlah_terjual();

      int stok_lama = produk_terpilih.getStok_produk();
      int stok_baru = stok_lama - jumlah_produk;
      produk_terpilih.setStok_produk(stok_baru);

      ProdukKeranjang produk = null;
      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_pembeli().equals(username_pembeli) && data.getUsername_penjual().equals(username_penjual) && data.getNama_produk().equals(nama_produk)) {
          int jumlah_lama = data.getJumlah_produk();
          int total_lama = data.getTotal_harga();
          data.setJumlah_produk(jumlah_lama + jumlah_produk);
          data.setTotal_harga(total_lama + total_harga);
          data.setStok_produk(stok_baru);
          produk = data;
        }
      }

      if (produk == null) {
        produk = new ProdukKeranjang(username_penjual, nama_produk, harga_produk, stok_baru, jumlah_terjual, username_pembeli, jumlah_produk, total_harga);
        ListKeranjang.add(produk);
      }
      lanjutkan("berhasil", "Produk Berhasil Dipesan");
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Pilihan tidak tersedia");
    }
  }

  // untuk pembeli lihat daftar produk di keranjangnya
  public void lihat_keranjang() throws IOException {
    clearScreen();
    int jumlahProdukKeranjang = 0;
    System.out.println("");
    for (int i = 0; i < ListKeranjang.size(); i++) {
      if (ListKeranjang.get(i).getUsername_pembeli().equals(this.getUsername())) {
        jumlahProdukKeranjang++;
      }
    }

    if (jumlahProdukKeranjang == 0) {
      System.out.println("\tKeranjang masih Kosong");
      return;
    }

    System.out.println("\t|=============================================|");
    System.out.println("\t|              Keranjang Belanja              |");
    System.out.println("\t|=============================================|");
    System.out.printf("\t| %-3s|     %-16s| %6s |  %-6s |\n", "No", "Nama Produk", "Jumlah", "Harga");
    System.out.println("\t|=============================================|");
    int total = 0;
    int nomor = 1;
    for (int i = 0; i < ListKeranjang.size(); i++) {
      if (ListKeranjang.get(i).getUsername_pembeli().equals(this.getUsername())) {
        String nama_produk = ListKeranjang.get(i).getNama_produk();
        int harga_produk = ListKeranjang.get(i).getHarga_produk();
        int jumlah_produk = ListKeranjang.get(i).getJumlah_produk();
        int total_harga = ListKeranjang.get(i).getTotal_harga();

        if (nomor > 1) {
          System.out.println("\t|---------------------------------------------|");
        }
        System.out.printf("\t| %-3d| %-20s| %4d   |  %-7d|\n", nomor, nama_produk, jumlah_produk, harga_produk);
        nomor++;
        total += total_harga;
      }
    }
    System.out.println("\t|=============================================|");
    System.out.printf("\t| %33s | %-7d |\n", "Total Harga", total);
    System.out.println("\t|=============================================|");
  }

  // untuk pembeli ubah jumlah produk di keranjang
  public void ubah_produk() throws IOException {
    try {
      String username_pembeli = this.getUsername();
      int jumlah_produk_keranjang = 0;
      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_pembeli().equals(username_pembeli)) {
          jumlah_produk_keranjang++;
        }
      }

      if (jumlah_produk_keranjang == 0) {
        clearScreen();
        System.out.println("\tKeranjang masih Kosong");
        lanjutkan();
        return;
      }

      lihat_keranjang();
      System.out.println("");
      System.out.print("\tSilahkan Pilih Nomor Produk : ");
      int pilihan = Integer.parseInt(input.readLine());

      if (pilihan <= 0 || pilihan > jumlah_produk_keranjang) {
        lanjutkan("gagal", "Pilihan tidak tersedia");
        return;
      }

      int index = 0;
      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_pembeli().equals(username_pembeli)) {
          if (index == pilihan - 1) {
            System.out.print("\tMasukan Jumlah Baru : ");
            int jumlah_produk_baru = Integer.parseInt(input.readLine());

            int jumlah_produk_lama = data.getJumlah_produk();
            int stok_lama = data.getStok_produk();
            int selisih;

            if (jumlah_produk_baru <= 0 || jumlah_produk_baru > (jumlah_produk_lama + stok_lama)) {
              lanjutkan("gagal", "Jumlah Produk tidak valid (Minimal pesanan adalah 1 dan Maksimal pesanan adalah stok produk)");
              return;
            }

            for (Produk produk : ListProduk) {
              if (produk.getNama_produk().equals(data.getNama_produk())) {
                if (jumlah_produk_baru > jumlah_produk_lama) {
                  selisih = jumlah_produk_baru - jumlah_produk_lama;
                  int stok_baru = stok_lama - selisih;
                  produk.setStok_produk(stok_baru);
                  data.setStok_produk(stok_baru);
                }
                else if (jumlah_produk_baru < jumlah_produk_lama) {
                  selisih = jumlah_produk_lama - jumlah_produk_baru;
                  int stok_baru = stok_lama + selisih;
                  produk.setStok_produk(stok_baru);
                  data.setStok_produk(stok_baru);
                }
              }
            }

            int harga_produk = data.getHarga_produk();
            int total_harga = harga_produk * jumlah_produk_baru;
            data.setJumlah_produk(jumlah_produk_baru);
            data.setTotal_harga(total_harga);

            lanjutkan("berhasil", "Pesanan Berhasil Diperbarui");
          }
          index++;
        }
      }
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Pilihan harus Angka");
    }
  }

  // untuk pembeli hapus produk dari keranjang
  public void hapus_produk() throws IOException {
    try {
      String username_pembeli = this.getUsername();
      int jumlah_produk_keranjang = 0;
      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_pembeli().equals(username_pembeli)) {
          jumlah_produk_keranjang++;
        }
      }

      if (jumlah_produk_keranjang == 0) {
        clearScreen();
        System.out.println("\tKeranjang masih Kosong");
        lanjutkan();
        return;
      }

      lihat_keranjang();
      System.out.println("");
      System.out.print("\tSilahkan Pilih Nomor Produk : ");
      int pilihan = Integer.parseInt(input.readLine());

      if (pilihan <= 0 || pilihan > jumlah_produk_keranjang) {
        lanjutkan("gagal", "Pilihan tidak tersedia");
        return;
      }

      int index = 0;
      for (int i=0; i<ListKeranjang.size(); i++) {
        if (ListKeranjang.get(i).getUsername_pembeli().equals(username_pembeli)) {
          if (index == pilihan - 1) {
            String nama_produk = ListKeranjang.get(i).getNama_produk();
            int jumlah_produk = ListKeranjang.get(i).getJumlah_produk();
            for (Produk data : ListProduk) {
              if (data.getNama_produk().equals(nama_produk)) {
                int stok_lama = data.getStok_produk();
                int stok_baru = stok_lama + jumlah_produk;
                data.setStok_produk(stok_baru);
              }
            }
            ListKeranjang.remove(i);
            lanjutkan("berhasil", "Pesanan Berhasil Dihapus");
          }
          index++;
        }
      }
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Pilihan harus Angka");
    }
  }

  // untuk pembeli melakukan pembayaran
  public void pembayaran() throws IOException {
    try {
      int total = 0;
      int jumlah_produk_keranjang = 0;
      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_pembeli().equals(this.getUsername())) {
          total += data.getTotal_harga();
          jumlah_produk_keranjang++;
        }
      }

      if (jumlah_produk_keranjang == 0) {
        clearScreen();
        System.out.println("\tKeranjang masih Kosong");
        lanjutkan();
        return;
      }

      lihat_keranjang();
      System.out.println("");
      System.out.print("\tJumlah Uang Tunai : Rp ");
      int jumlah_uang = Integer.parseInt(input.readLine());

      if (jumlah_uang < total) {
        lanjutkan("gagal", "Jumlah Uang Tunai tidak mencukupi");
        return;
      }

      int index = 0;
      while (jumlah_produk_keranjang > 0) {
        if (ListKeranjang.get(index).getUsername_pembeli().equals(this.getUsername())) {
          String username_pembeli = ListKeranjang.get(index).getUsername_pembeli();
          String username_penjual = ListKeranjang.get(index).getUsername_penjual();
          String nama_produk = ListKeranjang.get(index).getNama_produk();
          int harga_produk = ListKeranjang.get(index).getHarga_produk();
          int jumlah_produk = ListKeranjang.get(index).getJumlah_produk();
          int total_harga = ListKeranjang.get(index).getTotal_harga();

          Date tanggal = new Date();
          SimpleDateFormat formatTanggal = new SimpleDateFormat("dd MMM yyyy HH:mm");

          Transaksi transaksi_sukses = new Transaksi(username_pembeli, username_penjual, nama_produk, harga_produk, jumlah_produk, total_harga, formatTanggal.format(tanggal) + " WITA");
          ListTransaksi.add(transaksi_sukses);

          for (Produk data : ListProduk) {
            if (data.getNama_produk().equals(nama_produk)) {
              int jumlah_terjual_lama = data.getJumlah_terjual();
              int jumlah_terjual_baru = jumlah_terjual_lama + jumlah_produk;
              data.setJumlah_terjual(jumlah_terjual_baru);
            }
          }
          ListKeranjang.remove(index);
          jumlah_produk_keranjang--;
          continue;
        }
        index++;
      }

      int kembalian = jumlah_uang - total;
      System.out.println("\tKembalian : Rp " + kembalian);
      lanjutkan("berhasil", "Terima Kasih Telah Berbelanja");
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Jumlah Uang harus Angka");
    }
  }

  // untuk pembeli lihat riwayat transaksi
  @Override
  public void riwayat_transaksi() throws IOException {
    clearScreen();
    int jumlah = 0;
    for (Transaksi data : ListTransaksi) {
      if (data.getUsername_pembeli().equals(this.getUsername())) {
        jumlah++;
      }
    }

    if (jumlah == 0) {
      System.out.println("\tTidak Ada Riwayat Transaksi");
      lanjutkan();
      return;
    }

    System.out.println("\t|===================================================================================================================|");
    System.out.println("\t|                                                Riwayat Transaksi                                                  |");
    System.out.println("\t|===================================================================================================================|");
    System.out.printf("\t| %-3s|   %-11s |   %-14s |     %-16s|  %-6s | %6s |  %-6s |        %-16s|\n", "No", "Nama Toko", "Nama Penjual", "Nama Produk", "Harga", "Jumlah", "Total", "Tanggal");
    System.out.println("\t|===================================================================================================================|");
    int nomor = 1;
    for (Transaksi data : ListTransaksi) {
      if (data.getUsername_pembeli().equals(this.getUsername())) {
        if (nomor > 1) {
          System.out.println("\t|-------------------------------------------------------------------------------------------------------------------|");
        }
        Penjual penjual_terpilih = null;
        for (Penjual penjual : ListPenjual) {
          if (penjual.getUsername().equals(data.getUsername_penjual())) {
            penjual_terpilih = penjual;
          }
        }
        System.out.printf("\t| %-3s| %-13s | %-16s | %-20s|  %-7d|   %-4d | %-7d | %-23s|\n", nomor, penjual_terpilih.getNama_toko(), penjual_terpilih.getNama_penjual(), data.getNama_produk(), data.getHarga_produk(), data.getJumlah_produk(), data.getTotal_harga(), data.getTanggal());
        nomor++;
      }
    }
    System.out.println("\t|===================================================================================================================|");
    lanjutkan();
  }
}
