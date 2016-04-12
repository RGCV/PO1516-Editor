package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Editor;
import edt.Document;
import edt.DuplicateAuthorException;

/**
 * §2.1.3.
 */
public class AddAuthor extends Command<Editor> {
	public AddAuthor(Editor editor) {
		super(MenuEntry.ADD_AUTHOR, editor);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		String name = IO.readString(Message.requestAuthorName());
		String email = IO.readString(Message.requestEmail());
		Document document = _receiver.getCurrentDocument();
		
		try {
			document.addAuthor(name, email);
			document.changed();
		} catch (DuplicateAuthorException e) {
			IO.println(Message.duplicateAuthor(name));
		}
	}
}
