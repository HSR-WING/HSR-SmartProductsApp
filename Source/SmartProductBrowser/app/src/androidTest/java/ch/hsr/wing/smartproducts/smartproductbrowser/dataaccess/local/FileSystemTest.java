package ch.hsr.wing.smartproducts.smartproductbrowser.dataaccess.local;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ch.hsr.wing.smartproducts.smartproductbrowser.IApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileSystemTest {

    private IApp createApp(){
        return new IApp() {
            @Override
            public Context getAppContext() {
                return ApplicationProvider.getApplicationContext();
            }

            @Override
            public SharedPreferences getSettings() {
                return null;
            }
        };
    }

    @Test
    public void test_Store_File(){
        IApp app = this.createApp();

        FileSystem fileSystem = new FileSystem(app);
        fileSystem.store("", "test.txt", "Hello World".getBytes());

        File file = new File(app.getAppContext().getFilesDir(), "test.txt");

        assertTrue(file.exists());
    }

    @Test
    public void test_Store_File_InSubDirectory(){
        IApp app = this.createApp();

        FileSystem fileSystem = new FileSystem(app);
        fileSystem.store("TestFiles", "test.txt", "Hello World".getBytes());

        File file = new File(new File(app.getAppContext().getFilesDir(), "TestFiles"), "test.txt");

        assertTrue(file.exists());
    }

    @Test
    public void test_Load_File() throws FileNotFoundException, IOException {
        IApp app = this.createApp();

        try(FileOutputStream stream = app.getAppContext().openFileOutput("text.txt", Context.MODE_PRIVATE)){
            stream.write("Hello World".getBytes());
        }

        FileSystem fileSystem = new FileSystem(app);
        byte[] content = fileSystem.load("", "test.txt");

        String text = new String(content);

        assertEquals("Hello World", text);
    }

    @Test
    public void test_StoreLoad_File_InSubDirectory(){
        IApp app = this.createApp();

        FileSystem fileSystem = new FileSystem(app);
        fileSystem.store("TestFiles", "test.txt", "Hello World".getBytes());

        File file = new File(new File(app.getAppContext().getFilesDir(), "TestFiles"), "test.txt");

        assertTrue(file.exists());

        byte[] content = fileSystem.load("TestFiles","test.txt");
        String text = new String(content);

        assertEquals("Hello World", text);
    }
}
