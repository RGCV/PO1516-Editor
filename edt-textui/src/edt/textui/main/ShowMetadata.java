package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Editor;
import edt.Document;
import edt.Author;

/**
 * §2.1.2.
 */
public class ShowMetadata extends Command<Editor> {
	public ShowMetadata(Editor editor) {
		super(MenuEntry.SHOW_METADATA, editor);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		Document document = _receiver.getCurrentDocument();
		
		IO.println(Message.documentTitle(document.getTitle()));
		for (Author author : document.getAuthors())
			IO.println(Message.author(author.getName(), author.getEmail()));
		IO.println(Message.documentSections(document.countTopSections()));
		IO.println(Message.documentBytes(document.getSize()));
		IO.println(Message.documentIdentifiers(document.countUUID()));
	}

}
