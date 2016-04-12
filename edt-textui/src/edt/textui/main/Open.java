package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;
import java.io.FileNotFoundException;

import edt.Editor;

/**
 * Open existing document.
 */
public class Open extends Command<Editor> {
	public Open(Editor editor) {
		super(MenuEntry.OPEN, editor);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		String fileName = IO.readString(Message.openFile());
		
		try {
			_receiver.openDocument(fileName);
		} catch(FileNotFoundException e) {
			IO.println(Message.fileNotFound());
		}
	}

}
