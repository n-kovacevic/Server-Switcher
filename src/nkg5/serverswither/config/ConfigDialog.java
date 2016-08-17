package nkg5.serverswither.config;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import javafx.util.Pair;

public class ConfigDialog extends Dialog<Config> {
	private final Config conf;
	private final ButtonType saveType;
	
	private ListView<String> serverList;
	public ConfigDialog(){
		super();
		conf = ConfigManager.loadConfig();
		saveType = new ButtonType("Save",ButtonData.OK_DONE);
		BorderPane pane = new BorderPane();
		serverList = new ListView<>(FXCollections.observableArrayList(conf.getServers().keySet()));
		
		serverList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ScrollPane scroll = new ScrollPane(serverList);
		scroll.setPrefHeight(250);
		
		// Top Pane
		HBox topPane = new HBox(5);
		Label folderLabel = new Label(conf.getProp("wow_folder"));
		Button selectButton = new Button("Select");
		topPane.getChildren().addAll(folderLabel, selectButton);
		topPane.setPadding(new Insets(5));
		topPane.setAlignment(Pos.CENTER_RIGHT);
		
		selectButton.setOnAction(e->{
			DirectoryChooser chooser = new DirectoryChooser();
			File folder = new File(conf.getProp("wow_folder"));
			if(folder.exists())
				chooser.setInitialDirectory(folder);
			folder = chooser.showDialog(getDialogPane().getScene().getWindow());
			if(folder != null){
				conf.setProp("wow_folder", folder.toString());
				folderLabel.setText(folder.toString());
			}
		});
		
		// Bottom Pane
		HBox bottomPane = new HBox(5);
		Button addButton = new Button("Add");
		Button removeButton = new Button("Remove");
		bottomPane.getChildren().addAll(addButton, removeButton);
		bottomPane.setPadding(new Insets(5));
		bottomPane.setAlignment(Pos.CENTER_RIGHT);
		
		addButton.setOnAction(e->{
			Dialog<Pair<String, String>> dia = new Dialog<>();
			dia.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
			dia.getDialogPane().getButtonTypes().add(ButtonType.OK);
			
			GridPane gridPane = new GridPane();
			TextField nameField = new TextField();
			TextField urlField = new TextField();
			Label nameLabel = new Label("Name: ");
			Label urlLabel = new Label("Location: ");
			GridPane.setConstraints(nameLabel, 1, 1);
			GridPane.setConstraints(nameField, 2, 1);
			GridPane.setConstraints(urlLabel, 1, 2);
			GridPane.setConstraints(urlField, 2, 2);
			gridPane.getChildren().addAll(nameLabel, nameField, urlLabel, urlField);
			dia.getDialogPane().setContent(gridPane);
			dia.setResultConverter(new Callback<ButtonType, Pair<String,String>>() {
				@Override
				public Pair<String, String> call(ButtonType param) {
					if(param==ButtonType.OK){
						Pair<String, String> res = new Pair<>(nameField.getText(), urlField.getText());
						return res;
					}
					return null;
				}
			});
			Optional<Pair<String, String>> result = dia.showAndWait();
			if(result.isPresent()){
				conf.setServer(result.get().getKey(), result.get().getValue());
				updateServerList();
			}
		});
		removeButton.setOnAction(e->{
			HashMap<String, String> servers = conf.getServers();
			servers.remove(serverList.getSelectionModel().getSelectedItem());
			updateServerList();
		});
		
		// Dialog Pane
		pane.setTop(topPane);
		pane.setCenter(scroll);
		pane.setBottom(bottomPane);
		getDialogPane().setContent(pane);
				
		getDialogPane().getButtonTypes().add(saveType);
		getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
		
		setResultConverter(new Callback<ButtonType, Config>() {
			@Override
			public Config call(ButtonType type) {
				if(type==saveType)
					return conf;
				return null;
			}
		});
		
	}
	
	private void updateServerList(){
		serverList.setItems(FXCollections.observableArrayList(conf.getServers().keySet()));
		serverList.getSelectionModel().clearAndSelect(0);
	}
}
