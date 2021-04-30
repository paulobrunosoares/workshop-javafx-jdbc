package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	// Button Action
	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {
		});
	}
	// End Button Action

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub

	}

	private synchronized <T> void loadView(String absuluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absuluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mianVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mianVBox.getChildren().get(0);
			mianVBox.getChildren().clear();
			mianVBox.getChildren().add(mainMenu);
			mianVBox.getChildren().addAll(newVBox.getChildren());

			T controlller = loader.getController();
			initializingAction.accept(controlller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	/*
	 * private synchronized void loadView2(String absuluteName) { try { FXMLLoader
	 * loader = new FXMLLoader(getClass().getResource(absuluteName)); VBox newVBox =
	 * loader.load();
	 * 
	 * Scene mainScene = Main.getMainScene(); VBox mianVBox = (VBox)((ScrollPane)
	 * mainScene.getRoot()).getContent();
	 * 
	 * 
	 * Node mainMenu = mianVBox.getChildren().get(0);
	 * mianVBox.getChildren().clear(); mianVBox.getChildren().add(mainMenu);
	 * mianVBox.getChildren().addAll(newVBox.getChildren());
	 * 
	 * DepartmentListController controller = loader.getController();
	 * controller.setDepartmentService(new DepartmentService());
	 * controller.updateTableView();
	 * 
	 * }catch(IOException e) { Alerts.showAlert("IO Exception",
	 * "Error loading view", e.getMessage(), AlertType.ERROR); } }
	 */

}
