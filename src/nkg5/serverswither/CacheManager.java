package nkg5.serverswither;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class CacheManager {
	private static CacheManager instance;
	public static synchronized CacheManager getInstance(){
		if(instance == null)
			instance = new CacheManager();
		return instance;
	}
	
	// Makes local backup of game cache
	public void backupCache(String wowFolder, Path realmlistFile) throws IOException{
		String realmlist = readRealmlist(realmlistFile);
		if (realmlist != null){
			String localCache = "Cache\\"+realmlist;
			String gameCache = wowFolder+"Cache";
			deleteFolder(localCache);
			Files.createDirectories(Paths.get(localCache));
			copyFolder(gameCache, localCache);
		}
	}
	
	// Restores local cache backup
	public void restoreCache(String wowFolder, Path realmlistFile) throws IOException{
		String realmlist = readRealmlist(realmlistFile);
		String localCache = "Cache\\"+realmlist;
		String gameCache = wowFolder+"Cache";
		deleteFolder(gameCache);
		Files.createDirectories(Paths.get(gameCache));
		copyFolder(localCache, gameCache);
	}
	
	// Read realmlist url from realmlist file.
	private String readRealmlist(Path realmlist) throws IOException{
		List<String> lines = Files.readAllLines(realmlist);
		String realmlistURL = null;
		for(String line: lines){
			if(line.startsWith("set realmlist ")){
				realmlistURL = line.replaceFirst("set realmlist ", "");
				return realmlistURL;
			}
		}
		return null;
	}
	
	// Folder managment methods
	private void copyFolder(String sourceFolder, String targetFolder) throws IOException{
		Path sourcePath = Paths.get(sourceFolder);
		Path targetPath = Paths.get(targetFolder);
		if (Files.exists(sourcePath, LinkOption.NOFOLLOW_LINKS)){
			Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
			    private Path sourcePath = null;
			    @Override
				public FileVisitResult preVisitDirectory(final Path dir,
			    final BasicFileAttributes attrs) throws IOException {
			        if (sourcePath == null) {
			            sourcePath = dir;
			        } else {
			        Files.createDirectories(targetPath.resolve(sourcePath
			                    .relativize(dir)));
			        }
			        return FileVisitResult.CONTINUE;
			    }

			    @Override
			    public FileVisitResult visitFile(final Path file,
			    final BasicFileAttributes attrs) throws IOException {
			    Files.copy(file,
			        targetPath.resolve(sourcePath.relativize(file)));
			    return FileVisitResult.CONTINUE;
			    }
			});
		}
	}
	
	private void deleteFolder(String folder) throws IOException {
		Path path = Paths.get(folder);
		if(Files.exists(path, LinkOption.NOFOLLOW_LINKS))
			Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
	
				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
	
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}
			
		});
	}
	
}
