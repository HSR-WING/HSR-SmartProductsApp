package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

public interface IFileSystem {
    void store(String path, String filename, byte[] content);
    byte[] load(String path, String filename);
}
