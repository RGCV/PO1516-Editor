package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;

/**
 * §2.2.7.
 */
public class RemoveSection extends SectionCommand {
	public RemoveSection(Section section, Document document) {
		super(MenuEntry.REMOVE_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int index = IO.readInteger(Message.requestSectionId());

		try {
			Section section = _receiver.removeSection(index);
			
			_receiver2.removeTextElement(section);
			_receiver2.changed();
		}
		catch (IndexOutOfBoundsException e) {
			IO.println(Message.noSuchSection(index));
		}
	}
  
}
