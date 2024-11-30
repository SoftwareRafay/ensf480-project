/*
 * ENSF 480: Term Project - Movie App
 * 2024-11-09
 * Authors: Group 5-L01
 * Version: FINAL
 */

package Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import database.ReadDB;
import Boundary.*;
import Entity.*;

public class GUIController {
	private SetGUI frontEnd;

	public GUIController(SetGUI frontEnd) {
		this.setFrontEnd(frontEnd);
	}

	public static void main(String[] args) throws IOException{
		SetGUI frontEnd = null;
		Login backEnd = new Login();
		frontEnd = new SetGUI(backEnd);

		ReadDB dp = new ReadDB();
		dp.loadDatabase();

		GUIController controller = new GUIController(frontEnd);
		controller.runClient();
	}

	public SetGUI getFrontEnd() {
		return frontEnd;
	}

	public void setFrontEnd(SetGUI frontEnd) {
		this.frontEnd = frontEnd;
	}

	public void runClient() {
		getFrontEnd().displayGUI();
	}

}
