package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;

/**
 * §2.2.4.
 */
public class SelectSection extends SectionCommand {
	public SelectSection(Section section, Document document) {
		super(MenuEntry.SELECT_SECTION, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException  {
	 	int index = IO.readInteger(Message.requestSectionId());

		try {
			Section section = _receiver.getSection(index);
			
			IO.println(Message.newActiveSection(index));
			edt.textui.section.MenuBuilder.menuFor(section, _receiver2);
		}
		catch (IndexOutOfBoundsException e) {
			IO.println(Message.noSuchSection(index));
		}
	}
}
