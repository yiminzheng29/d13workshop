package sg.edu.nus.iss.day13workshop.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
// import java.util.Scanner;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day13workshop.models.Contact;

@Service
public class DatabaseService {
    
    private File dataDir = new File("some directory"); // initiating a place to store files in the directory


    public File getDataDir() {
        return dataDir;
    }

    public void setDataDir(File dataDir) {
        this.dataDir = dataDir;
    }

    // for mac: /Users/<username>/data
    public boolean isDataDirValid() {
        return dataDir.exists() && dataDir.isDirectory() && dataDir.canWrite();
    }

    public boolean save(final Contact contact) {
        File f = new File(this.dataDir, contact.getID());
        try {
            OutputStream out = new FileOutputStream(f);
            PrintWriter pw = new PrintWriter(out);
            pw.println(contact.getID()); // returns ID in the file, if it is inside the file
            pw.println(contact.getName()); // returns name in the file, if it is inside the file
            pw.println(contact.getEmail()); // returns email in the file, if it is inside the file
            pw.println(contact.getPhone()); // returns phone in the file, if it is inside the file
            pw.flush();

            return true;

        } catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            // ex.printStackTrace();
            return false;
        }
		
	}

	public Contact read(String fileID) {

        try {
            // File f = new File(this.dataDir, fileID);
            // Scanner myReader = new Scanner(f);
            // while (myReader.hasNextLine()) {
            //     System.out.println(myReader.nextLine());
            // }
            // myReader.close();

            Contact contact = new Contact();
            Path filePath = new File(this.dataDir, fileID).toPath();
            Charset charset = Charset.forName("utf-8");
            List<String> stringVal = Files.readAllLines(filePath, charset);

            contact.setName(stringVal.get(1));
            contact.setEmail(stringVal.get(2));
            contact.setPhone(stringVal.get(3));

            return contact;
        } catch (IOException ex) {
            System.err.printf("Error: %s", ex.getMessage());
            ex.printStackTrace();
            return null;
        }
	}
}
