package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;

/**
 * §2.2.3.
 */
public class ShowContent extends SectionCommand {
	public ShowContent(Section section, Document document) {
		super(MenuEntry.SHOW_CONTENT, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
	  String content = _receiver.accept(new ShowContentVisitor());

	  if(content.length() > 0)
	  	IO.println(content.substring(0,content.length() - 1));
	}
}
