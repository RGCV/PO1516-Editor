package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Editor;
import edt.Document;
import edt.Section;

/**
 * §2.1.4.
 */
public class ShowIndex extends Command<Editor> {
	public ShowIndex(Editor editor) {
		super(MenuEntry.SHOW_INDEX, editor);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		Document document = _receiver.getCurrentDocument();
		
		IO.println("{" + document.getTitle() + "}");
		for (Section section : document.getSubSections())
			IO.println(Message.sectionIndexEntry(section.getUUID(), section.getTitle()));
	}
}
