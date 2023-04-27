package Sistem_Pasar_Elektronik;

import java.io.IOException;

import static Sistem_Pasar_Elektronik.Main.ListProduk;
import static Sistem_Pasar_Elektronik.Main.ListPembeli;
import static Sistem_Pasar_Elektronik.Main.ListPenjual;
import static Sistem_Pasar_Elektronik.Main.ListKeranjang;
import static Sistem_Pasar_Elektronik.Main.ListTransaksi;

public class Penjual extends Pengguna implements InterfaceCRUD {
  private String nama_toko;
  private String nama_penjual;
  private String kategori;

  public Penjual(String username, String password, String role, String nama_toko, String nama_penjual, String kategori) {
    super(username, password, role);
    this.nama_toko = nama_toko;
    this.nama_penjual = nama_penjual;
    this.kategori = kategori;
  }

  public String getNama_toko() {
    return this.nama_toko;
  }

  public void setNama_toko(String nama_toko) {
    this.nama_toko = nama_toko;
  }

  public String getNama_penjual() {
    return this.nama_penjual;
  }

  public void setNama_penjual(String nama_penjual) {
    this.nama_penjual = nama_penjual;
  }

  public String getKategori() {
    return this.kategori;
  }

  public void setKategori(String kategori) {
    this.kategori = kategori;
  }

  public int jumlah_produk() {
    int jml_produk = 0;
    for (Produk data : ListProduk) {
      if (data.getUsername_penjual().equals(this.getUsername())) {
        jml_produk++;
      }
    }
    return jml_produk;
  }

  // untuk penjual daftar ke sistem
  static void daftar() throws IOException {
    System.out.println("");
    System.out.print("\tUsername  : ");
    String username = input.readLine();
    System.out.print("\tPassword  : ");
    String password = input.readLine();
    System.out.print("\tNama Toko : ");
    String nama_toko = input.readLine();
    System.out.print("\tNama      : ");
    String nama_penjual = input.readLine();
    System.out.print("\tKategori  : ");
    String kategori = input.readLine();
    for (Penjual data : ListPenjual) {
      if (data.getUsername().equals(username)) {
        lanjutkan("gagal", "Username telah digunakan");
        return;
      }
    }

    Penjual penjual_baru = new Penjual(username, password, "penjual", nama_toko, nama_penjual, kategori);
    ListPenjual.add(penjual_baru);
    lanjutkan("berhasil", "Anda Berhasil Mendaftar sebagai Penjual");
  }

  // untuk penjual lihat produk
  public void lihat_produk() throws IOException {
    clearScreen();
    String username_penjual = getUsername();
    int jumlah_produk_penjual = 0;
    for (Produk data : ListProduk) {
      if (data.getUsername_penjual().equals(username_penjual)){
        jumlah_produk_penjual++;
      }
    }

    if (jumlah_produk_penjual == 0) {
      System.out.println("\tTidak Ada Produk");
      return;
    }
    
    System.out.println("");
    System.out.println("\t|====================================================|");
    System.out.println("\t|                    Daftar Produk                   |");
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

  // untuk penjual tambah produknya
  public void tambah_produk() throws IOException {
    clearScreen();
    try {
      lihat_produk();
      String username_penjual = this.getUsername();
      int produk_terjual = 0;
      System.out.println("");
      System.out.println("\tTambah Produk Baru");
      System.out.println("");
      System.out.print("\tNama Produk  : ");
      String nama_produk = input.readLine();
      System.out.print("\tHarga Produk : ");
      int harga_produk = Integer.parseInt(input.readLine());
      System.out.print("\tStok Produk  : ");
      int stok_produk = Integer.parseInt(input.readLine());
      
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual) && data.getNama_produk().equals(nama_produk)) {
          lanjutkan("gagal", "Produk sudah tersedia");
          return;
        }
      }

      if (harga_produk == 0 && stok_produk == 0) {
        lanjutkan("gagal", "Harga Minimal dan Stok Minimal Produk adalah 1");
        return;
      }
      else if (harga_produk == 0) {
        lanjutkan("gagal", "Harga Minimal Produk adalah 1");
        return;
      }
      else if (stok_produk == 0) {
        lanjutkan("gagal", "Stok Minimal Produk adalah 1");
        return;
      }
      
      Produk produk_baru = new Produk(username_penjual, nama_produk, harga_produk, stok_produk, produk_terjual);
      ListProduk.add(produk_baru);
      lanjutkan("berhasil", "Produk Berhasil Ditambahkan");
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Harga dan Stok Produk harus Angka");
    }
  }

  // untuk penjual ubah produknya
  public void ubah_produk() throws IOException {
    try {
      String username_penjual = this.getUsername();
      int jumlah_produk_penjual = 0;
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual)){
          jumlah_produk_penjual++;
        }
      }

      if (jumlah_produk_penjual == 0) {
        clearScreen();
        System.out.println("\tTidak Ada Produk");
        lanjutkan();
        return;
      }

      lihat_produk();
      System.out.println("");
      System.out.println("\tMemperbarui Produk");
      System.out.println("");
      System.out.print("\tSilahkan Pilih Nomor Produk : ");
      int pilihan = Integer.parseInt(input.readLine());

      if (pilihan <= 0 || pilihan > jumlah_produk_penjual) {
        lanjutkan("gagal", "Pilihan tidak tersedia");
        return;
      }

      int index = 0;
      Produk target_ubah_produk = null;
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual)) {
          if (index == pilihan - 1) {
            target_ubah_produk = data;
            break;
          }
          index++;
        }
      }

      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_penjual().equals(username_penjual) && data.getNama_produk().equals(target_ubah_produk.getNama_produk())) {
          lanjutkan("gagal", "Gagal, Produk sedang dipesan oleh Pembeli");
          return;
        }
      }

      System.out.println("");
      System.out.print("\tNama Produk  : ");
      String nama_produk = input.readLine();
      System.out.print("\tHarga Produk : ");
      int harga_produk = Integer.parseInt(input.readLine());
      System.out.print("\tStok Produk  : ");
      int stok_produk = Integer.parseInt(input.readLine());

      if (harga_produk == 0 && stok_produk == 0) {
        lanjutkan("gagal", "Harga Minimal dan Stok Minimal Produk adalah 1");
        return;
      }
      else if (harga_produk == 0) {
        lanjutkan("gagal", "Harga Minimal Produk adalah 1");
        return;
      }
      else if (stok_produk == 0) {
        lanjutkan("gagal", "Stok Minimal Produk adalah 1");
        return;
      }

      target_ubah_produk.setUsername_penjual(username_penjual);
      target_ubah_produk.setNama_produk(nama_produk);
      target_ubah_produk.setHarga_produk(harga_produk);
      target_ubah_produk.setStok_produk(stok_produk);
      lanjutkan("berhasil", "Produk Berhasil Diperbarui");
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Harga dan Stok Produk harus Angka");
    }
  }

  // untuk penjual hapus produknya
  public void hapus_produk() throws IOException {
    try {
      String username_penjual = this.getUsername();
      int jumlah_produk_penjual = 0;
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual)){
          jumlah_produk_penjual++;
        }
      }

      if (jumlah_produk_penjual == 0) {
        clearScreen();
        System.out.println("\tTidak Ada Produk");
        lanjutkan();
        return;
      }

      lihat_produk();
      System.out.println("");
      System.out.println("\tMenghapus Produk");
      System.out.println("");
      System.out.print("\tSilahkan Pilih Nomor Produk : ");
      int pilihan = Integer.parseInt(input.readLine());

      if (pilihan <= 0 || pilihan > jumlah_produk_penjual) {
        lanjutkan("gagal", "Pilihan tidak tersedia");
        return;
      }

      int index = 0;
      Produk target_hapus_produk = null;
      for (Produk data : ListProduk) {
        if (data.getUsername_penjual().equals(username_penjual)) {
          if (index == pilihan - 1) {
            target_hapus_produk = data;
            break;
          }
          index++;
        }
      }

      for (ProdukKeranjang data : ListKeranjang) {
        if (data.getUsername_penjual().equals(username_penjual) && data.getNama_produk().equals(target_hapus_produk.getNama_produk())) {
          lanjutkan("gagal", "Gagal, Produk sedang dipesan oleh Pembeli");
          return;
        }
      }

      for (int i=0; i<ListProduk.size(); i++) {
        if (ListProduk.get(i).getUsername_penjual().equals(username_penjual) && ListProduk.get(i).getNama_produk().equals(target_hapus_produk.getNama_produk())) {
          ListProduk.remove(i);
          break;
        }
      }
      lanjutkan("berhasil", "Produk Berhasil Dihapus");
    }
    catch (NumberFormatException nfe) {
      lanjutkan("gagal", "Pilihan harus Angka");
    }
  }

  // untuk penjual lihat penjualan produknya
  @Override
  public void riwayat_transaksi() throws IOException {
    clearScreen();
    int jumlah = 0;
    for (Transaksi data : ListTransaksi) {
      if (data.getUsername_penjual().equals(this.getUsername())) {
        jumlah++;
      }
    }

    if (jumlah == 0) {
      System.out.println("\tTidak Ada Riwayat Penjualan");
      lanjutkan();
      return;
    }

    System.out.println("\t|===================================================================================================|");
    System.out.println("\t|                                        Riwayat Penjualan                                          |");
    System.out.println("\t|===================================================================================================|");
    System.out.printf("\t| %-3s|   %-14s |     %-16s|  %-6s | %6s |  %-6s |        %-16s|\n", "No", "Nama Pembeli", "Nama Produk", "Harga", "Jumlah", "Total", "Tanggal");
    System.out.println("\t|===================================================================================================|");
    int nomor = 1;
    for (Transaksi data : ListTransaksi) {
      if (data.getUsername_penjual().equals(this.getUsername())) {
        if (nomor > 1) {
          System.out.println("\t|---------------------------------------------------------------------------------------------------|");
        }
        Pembeli pembeli_terpilih = null;
        for (Pembeli pembeli : ListPembeli) {
          if (pembeli.getUsername().equals(data.getUsername_pembeli())) {
            pembeli_terpilih = pembeli;
          }
        }
        System.out.printf("\t| %-3s| %-16s | %-20s|  %-7d|   %-4d | %-7d | %-23s|\n", nomor, pembeli_terpilih.getNama_pembeli(), data.getNama_produk(), data.getHarga_produk(), data.getJumlah_produk(), data.getTotal_harga(), data.getTanggal());
        nomor++;
      }
    }
    System.out.println("\t|===================================================================================================|");
    lanjutkan();
  }
}
