package nkg5.serverswither;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nkg5.serverswither.config.Config;
import nkg5.serverswither.config.ConfigDialog;
import nkg5.serverswither.config.ConfigManager;

public class ServerSwitcherController implements Initializable{

	@FXML
	private ComboBox<String> serverCombo;
	@FXML
	private AnchorPane rootPane;
		
	private Config conf;
	private String realmlist;
	
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
			// In case folder path don't end with "\"
			String path = conf.getProp("wow_folder");
			if(!(path.endsWith("\\")
					||path.endsWith("/")))
				path += "\\";
			
			// Backup current cache
			Path realmlistFile = Paths.get(conf.getProp("realmlist"));
			CacheManager.getInstance().backupCache(path, realmlistFile);
			
			// Replace realmlist
			Files.deleteIfExists(realmlistFile);
			LinkedList<String> lines = new LinkedList<String>();
			String realm = conf.getServer(serverCombo.getValue());
			lines.add("set realmlist " + realm);
			Files.write(realmlistFile, lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			
			// Restore cache for current realmlist
			CacheManager.getInstance().restoreCache(path, realmlistFile);

			// Start WoW client
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(path + "Wow.exe");
			
			// Close this program
			((Stage) serverCombo.getScene().getWindow()).close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	private void buttonEdit(ActionEvent e){
		ConfigDialog dialog = new ConfigDialog();
		Optional<Config> result = dialog.showAndWait();
		if(result.isPresent()){
			conf = result.get();
			serverCombo.setItems(FXCollections.observableArrayList(conf.getServers().keySet()));
			realmlist = conf.getProp("wow_folder");
			if(Files.exists(Paths.get(realmlist, "realmlist.wtf"))){
				realmlist += "\\realmlist.wtf";
				conf.setProp("realmlist", realmlist.toString());
			}else{
				try {
					Stream<Path> local = Files.list(Paths.get(realmlist, "Data"));
					local.forEach(t->{
						if(Files.isDirectory(t))
							realmlist = t.toString();
					});
					local.close();
					realmlist += "\\realmlist.wtf";
					conf.setProp("realmlist", realmlist.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			ConfigManager.saveConfig(conf);
		}
	}
}
