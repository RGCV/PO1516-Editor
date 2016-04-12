package edt.textui;

import static ist.po.ui.Dialog.IO;

import java.io.IOException;

import edt.Editor;

/**
 * Class that starts the application's textual interface.
 */
public class TextEditor {
	public static void main(String[] args) {
		Editor editor = new Editor();
		String datafile = System.getProperty("import"); //$NON-NLS-1$
		
		if (datafile != null) {
			try {
				editor.readImportFile(datafile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		edt.textui.main.MenuBuilder.menuFor(editor);
		
		IO.closeDown();
	}
}
