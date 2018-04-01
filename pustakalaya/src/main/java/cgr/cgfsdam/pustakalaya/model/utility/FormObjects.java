package cgr.cgfsdam.pustakalaya.model.utility;

import cgr.cgfsdam.pustakalaya.controller.BaseController;
import javafx.scene.Parent;

public class FormObjects {
	Parent parent;
	BaseController controller;
	/**
	 * @return the parent
	 */
	public Parent getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	/**
	 * @return the controller
	 */
	public BaseController getController() {
		return controller;
	}
	/**
	 * @param controller the controller to set
	 */
	public void setController(BaseController controller) {
		this.controller = controller;
	}
}
