package Sistem_Pasar_Elektronik;

import java.io.IOException;

public interface InterfaceCRUD {
  public void lihat_produk() throws IOException;
  public void tambah_produk() throws IOException;
  public void ubah_produk() throws IOException;
  public void hapus_produk() throws IOException;
}