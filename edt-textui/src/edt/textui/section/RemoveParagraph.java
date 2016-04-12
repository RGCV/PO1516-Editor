package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;
import edt.Paragraph;

/**
 * §2.2.11.
 */
public class RemoveParagraph extends SectionCommand {
	public RemoveParagraph(Section section, Document document) {
		super(MenuEntry.REMOVE_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int index = IO.readInteger(Message.requestParagraphId());
		
		try {
			Paragraph paragraph = _receiver.removeParagraph(index);
			
			_receiver2.removeTextElement(paragraph);
			_receiver2.changed();
		}
		catch (IndexOutOfBoundsException e) {
			IO.println(Message.noSuchParagraph(index));
		}
	}

}
