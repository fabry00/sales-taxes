package com.id.salestaxesapi.obj.persistent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Very very Very simple and dummy Persistent engien
 *
 * @author Fabrizio Faustinoni
 */
public class FilePersistentEngine implements IPersistentEngine {

    private static final String SIMPLE_DB = "simple_db.salesdb";
    private static final String ROW_SEPARATOR = "############################";

    private File db;

    public FilePersistentEngine() throws IOException {
        initDb();
    }

    @Override
    public boolean execQuery(Object obj) throws IOException {
        FileWriter fw = new FileWriter(db, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(obj.toString());
        out.println(ROW_SEPARATOR);
        out.close();

        return true;
    }

    @Override
    public String execQuery(String query) throws IOException {
        return new String(Files.readAllBytes(Paths.get(db.getAbsolutePath())));
    }

    private void initDb() throws IOException {
        this.db = new File(SIMPLE_DB);
        if (this.db.exists()) {
            this.db.delete();
            this.db.createNewFile();
        }
        execQuery(" SalesTaxes DB \n");
    }

}
