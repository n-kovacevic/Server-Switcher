package nkg5.serverswither;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ServerSwitcherController implements Initializable{

	@FXML
	private ComboBox<String> serverCombo;
	@FXML
	private AnchorPane rootPane;
	
	private Config conf;
	
	public ServerSwitcherController(){
		conf = ConfigManager.loadConfig();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serverCombo.setItems(FXCollections.observableArrayList(conf.getServers().keySet()));
		if(!serverCombo.getItems().isEmpty())
			serverCombo.setValue(serverCombo.getItems().get(0));
	}
	
	@FXML
	private void buttonPlay(ActionEvent e){
		try {
			Runtime runtime = Runtime.getRuntime();
			String path = conf.getProp("wow_folder");
			if(!(path.endsWith("\\")
					||path.endsWith("/")))
				path += "\\";
			LinkedList<String> lines = new LinkedList<String>();
			String realm = conf.getServer(serverCombo.getValue());
			lines.add("set realmlist " + realm);
			
			System.out.println(serverCombo.getValue());
			Path realmlistPath = Paths.get(path+"Data\\enUS\\realmlist.wtf");
			Files.deleteIfExists(realmlistPath);
			Files.write(realmlistPath, lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			
			if(realm != conf.getProp("previous"))
				copyCache(path);
			
			runtime.exec(path + "Wow.exe");
			((Stage) serverCombo.getScene().getWindow()).close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void copyCache(String folder){
		try{
			Path path = Paths.get(folder+"Cache/");
			Path dest = Paths.get("./Cache/"+conf.getProp("previous"));
			if(Files.exists(dest, LinkOption.NOFOLLOW_LINKS))
					deleteFolder(dest);
			copyFolder(path, dest);
			deleteFolder(path);
			dest = Paths.get("./Cache/"+conf.getServer(serverCombo.getValue()));
			
			copyFolder(dest, path);
			conf.setProp("previous", conf.getServer(serverCombo.getValue()));
			ConfigManager.saveConfig(conf);
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	private void copyFolder(Path sourcePath, Path targetPath) throws IOException{
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
	
	private void deleteFolder(Path path) throws IOException {
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
	
	@FXML
	private void buttonEdit(ActionEvent e){
		ConfigDialog dialog = new ConfigDialog();
		Optional<Config> result = dialog.showAndWait();
		if(result.isPresent()){
			conf = result.get();
			serverCombo.setItems(FXCollections.observableArrayList(conf.getServers().keySet()));
			ConfigManager.saveConfig(conf);
		}
	}
}
