package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;

/**
 * §2.2.5.
 */
public class InsertSection extends SectionCommand {
	public InsertSection(Section section, Document document) {
		super(MenuEntry.INSERT_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int index = IO.readInteger(Message.requestSectionId());
		String title = IO.readString(Message.requestSectionTitle());
		Section paragraph = new Section(title);
		
		_receiver.addSection(index, paragraph);
		_receiver2.addTextElement(paragraph);
		_receiver2.changed();
	}
}
