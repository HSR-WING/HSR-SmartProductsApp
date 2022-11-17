package ch.ost.wing.smartproducts.shoppingcartapp.dataaccess.local;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.inject.Inject;

import ch.ost.wing.smartproducts.shoppingcartapp.IApp;

public class FileSystem implements IFileSystem {

    private final IApp _app;

    @Inject
    public FileSystem(IApp app){
        this._app = app;
    }

    private Context getContext(){
        return this._app.getAppContext();
    }


    @Override
    public boolean exists(String path, String filename) {
        File dir = this.ensurePath(path);
        return new File(dir, filename).exists();
    }

    @Override
    public void store(String path, String filename, byte[] content) {
        File dir = this.ensurePath(path);
        try {
            try(FileOutputStream stream = new FileOutputStream(new File(dir, filename))){
                stream.write(content);
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    private File ensurePath(String path){
        if(path == null || path.trim().equals("")){
            return this.getContext().getFilesDir();
        }
        File file = new File(this.getContext().getFilesDir(), path);
        file.mkdirs();
        return file;
    }

    @Override
    public byte[] load(String path, String filename) {
        File dir = this.ensurePath(path);
        try{
            File file = new File(dir, filename);
            if(!file.exists()){
                return new byte[0];
            }
            try(FileInputStream stream = new FileInputStream(file)){
                byte[] buffer = new byte[(int)stream.getChannel().size()];
                stream.read(buffer);
                return buffer;
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }
}
