package Sistem_Pasar_Elektronik;

public class Produk {
  protected String username_penjual;
  protected String nama_produk;
  protected int harga_produk;
  protected int stok_produk;
  protected int jumlah_terjual;

  public Produk(String username_penjual, String nama_produk, int harga_produk, int stok_produk, int jumlah_terjual) {
    this.username_penjual = username_penjual;
    this.nama_produk = nama_produk;
    this.harga_produk = harga_produk;
    this.stok_produk = stok_produk;
    this.jumlah_terjual = jumlah_terjual;
  }

  public String getUsername_penjual() {
    return this.username_penjual;
  }

  public void setUsername_penjual(String username_penjual) {
    this.username_penjual = username_penjual;
  }

  public String getNama_produk() {
    return this.nama_produk;
  }

  public void setNama_produk(String nama_produk) {
    this.nama_produk = nama_produk;
  }

  public int getHarga_produk() {
    return this.harga_produk;
  }

  public void setHarga_produk(int harga_produk) {
    this.harga_produk = harga_produk;
  }

  public int getStok_produk() {
    return this.stok_produk;
  }

  public void setStok_produk(int stok_produk) {
    this.stok_produk = stok_produk;
  }

  public int getJumlah_terjual() {
    return this.jumlah_terjual;
  }

  public void setJumlah_terjual(int jumlah_terjual) {
    this.jumlah_terjual = jumlah_terjual;
  }
}
