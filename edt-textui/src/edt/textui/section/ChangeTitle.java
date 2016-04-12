package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;

/**
 * §2.2.1.
 */
public class ChangeTitle extends SectionCommand {
	public ChangeTitle(Section section, Document document) {
		super(MenuEntry.CHANGE_TITLE, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		_receiver.setTitle(IO.readString(Message.requestSectionTitle()));
		_receiver2.changed();
	}
}
