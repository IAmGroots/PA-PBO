package Sistem_Pasar_Elektronik;

public final class Transaksi {
  private final String username_pembeli;
  private final String username_penjual;
  private final String nama_produk;
  private final int harga_produk;
  private final int jumlah_produk;
  private final int total_harga;
  private final String tanggal;

  public Transaksi(String username_pembeli, String username_penjual, String nama_produk, int harga_produk, int jumlah_produk, int total_harga, String tanggal) {
    this.username_pembeli = username_pembeli;
    this.username_penjual = username_penjual;
    this.nama_produk = nama_produk;
    this.harga_produk = harga_produk;
    this.jumlah_produk = jumlah_produk;
    this.total_harga = total_harga;
    this.tanggal = tanggal;
  }

  public final String getUsername_pembeli() {
    return this.username_pembeli;
  }

  public final String getUsername_penjual() {
    return this.username_penjual;
  }

  public final String getNama_produk() {
    return this.nama_produk;
  }

  public final int getHarga_produk() {
    return this.harga_produk;
  }

  public final int getJumlah_produk() {
    return this.jumlah_produk;
  }

  public final int getTotal_harga() {
    return this.total_harga;
  }

  public final String getTanggal() {
    return this.tanggal;
  }
}
