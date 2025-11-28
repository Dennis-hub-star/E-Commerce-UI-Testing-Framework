package ui.utils;
import java.io.*;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * ZipUtils class provides utility methods for compressing folders and files into a zip archive.
 * It supports recursive zipping of directories and their contents.
 */
public class ZipUtils {

    /**
     * Compresses a folder into a zip file.
     * 
     * @param sourceFolderPath Path to the folder to be compressed.
     * @param zipFilePath Path where the zip file will be created.
     * @throws IOException If there is an error during the zipping process.
     */
    public static void zipFolder(String sourceFolderPath, String zipFilePath) throws IOException {
        // Create a FileOutputStream that will write bytes into the final zip file
        FileOutputStream fos = new FileOutputStream(zipFilePath); // creates Reports.zip file (or overwrites)
        
        // ZipOutputStream wraps the FileOutputStream and provides methods to add ZipEntries
        ZipOutputStream zos = new ZipOutputStream(fos);

        // Create a File object for the source folder we want to compress
        File folderToZip = new File(sourceFolderPath);
        
        // Start the recursive zipping process - this will add files and folders into the zip
        zipFile(folderToZip, folderToZip.getName(), zos);

        // Close the ZipOutputStream (which also flushes/finishes the zip format)
        zos.close();
        
        // Close the underlying FileOutputStream
        fos.close();
    }

    /**
     * Recursively adds files and directories to the zip file.
     * 
     * @param fileToZip File or directory to be added to the zip.
     * @param fileName Name of the file or directory in the zip.
     * @param zos ZipOutputStream to write the zip entries.
     * @throws IOException If there is an error during the zipping process.
     */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
        // Skip hidden files (optional safeguard)
        if (fileToZip.isHidden()) {
            return;
        }

        // If the file is a directory, we must create a directory entry and recurse into children
        if (fileToZip.isDirectory()) {
            // Add a directory entry into the zip (entry names for directories should end with '/')
            if (fileName.endsWith("/")) {
                zos.putNextEntry(new ZipEntry(fileName));
                zos.closeEntry();
            } else {
                zos.putNextEntry(new ZipEntry(fileName + "/"));
                zos.closeEntry();
            }
            // List children of the directory and call zipFile recursively
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zos);
            }
            return;
        }

        // If the file is not a directory, it's a file — read bytes and write to the zip entry
        FileInputStream fis = new FileInputStream(fileToZip); // read file bytes
        ZipEntry zipEntry = new ZipEntry(fileName);           // create a zip entry with relative path
        zos.putNextEntry(zipEntry);                            // start entry in zip

        byte[] bytes = new byte[1024]; // buffer to read file in chunks
        int length;
        // Read from file and write to zip output stream until EOF
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        // Close the file input stream and the zip entry
        fis.close();
    }

}