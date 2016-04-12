package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;

/**
 * §2.2.2.
 */
public class ListSections extends SectionCommand {
	public ListSections(Section section, Document document) {
		super(MenuEntry.LIST_SECTIONS, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		String content = _receiver.accept(new ListSectionsVisitor());
		
		if(content.length() > 0)
			IO.println(content.substring(0,content.length() - 1));
	}
}
