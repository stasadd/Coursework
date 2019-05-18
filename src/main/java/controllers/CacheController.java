package controllers;

import config.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public abstract class CacheController {

    public static boolean isCacheExist(String fileName) {
        return new File(Settings.getInstance().getCacheDirectory() + "\\" + fileName).exists();
    }

    public static void clearCache() {
        Path directory = Paths.get(Settings.getInstance().getCacheDirectory());

        if (Files.exists(directory))
        {
            try {
                Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path directory, IOException ioException) throws IOException {
                        Files.delete(directory);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
