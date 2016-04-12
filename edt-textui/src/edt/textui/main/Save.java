package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Editor;

/**
 * Save to file under current name (if unnamed, query for name).
 */
public class Save extends Command<Editor> {
	public Save(Editor editor) {
		super(MenuEntry.SAVE, editor);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		if(_receiver.hasChanges()) {
			String fileName;
			
			if ((fileName = _receiver.getFileName()).equals("")) {
				fileName = IO.readString(Message.newSaveAs());
			}
			_receiver.saveDocument(fileName);
		}
	}
}
