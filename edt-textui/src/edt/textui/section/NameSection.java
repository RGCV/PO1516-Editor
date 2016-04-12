package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;
import edt.UUIDChangedException;

/**
 * §2.2.6.
 */
public class NameSection extends SectionCommand {
	public NameSection(Section section, Document document) {
		super(MenuEntry.NAME_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int index = IO.readInteger(Message.requestSectionId());
		String uuid = IO.readString(Message.requestUniqueId());
		
		try {
			Section section = _receiver.getSection(index);
			
			try {
				_receiver2.nameTextElement(uuid, section);
				_receiver2.changed();
			}
			catch (UUIDChangedException e) {
				IO.println(Message.sectionNameChanged());
			}
		}
		catch (IndexOutOfBoundsException e) {
			IO.println(Message.noSuchSection(index));
		}
		
	}
}
