package ch.ost.wing.smartproducts.smartproductbrowser.dataaccess.local;

public interface IFileSystem {
    boolean exists(String path, String filename);
    void store(String path, String filename, byte[] content);
    byte[] load(String path, String filename);
}
