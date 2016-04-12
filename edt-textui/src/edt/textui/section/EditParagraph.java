package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;
import edt.Paragraph;

/**
 * §2.2.10.
 */
public class EditParagraph extends SectionCommand {
	public EditParagraph(Section section, Document document) {
		super(MenuEntry.EDIT_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
	  int index = IO.readInteger(Message.requestParagraphId());
		String content = IO.readString(Message.requestParagraphContent());
		
		try {
			Paragraph paragraph = _receiver.getParagraph(index);
			paragraph.setText(content);
			_receiver2.changed();
		}
		catch (IndexOutOfBoundsException e) {
			IO.println(Message.noSuchParagraph(index)); 
		}
	}

}
