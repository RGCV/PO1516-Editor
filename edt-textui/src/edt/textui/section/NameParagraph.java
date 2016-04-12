package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;
import edt.Paragraph;
import edt.UUIDChangedException;

/**
 * §2.2.9.
 */
public class NameParagraph extends SectionCommand {
	public NameParagraph(Section section, Document document) {
		super(MenuEntry.NAME_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int index = IO.readInteger(Message.requestParagraphId());
		String uuid = IO.readString(Message.requestUniqueId());
		
		try {
			Paragraph paragraph = _receiver.getParagraph(index);
			
			try {
				_receiver2.nameTextElement(uuid, paragraph);
				_receiver2.changed();
			}
			catch(UUIDChangedException e) {
				IO.println(Message.paragraphNameChanged());
			}
		}
		catch(IndexOutOfBoundsException e) {
			IO.println(Message.noSuchParagraph(index));
		}
	}
}
