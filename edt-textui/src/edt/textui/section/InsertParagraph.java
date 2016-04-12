package edt.textui.section;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Document;
import edt.Section;
import edt.Paragraph;

/**
 * §2.2.8.
 */
public class InsertParagraph extends SectionCommand {
	public InsertParagraph(Section section, Document document) {
		super(MenuEntry.INSERT_PARAGRAPH, section, document);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		int index = IO.readInteger(Message.requestParagraphId());
		String content = IO.readString(Message.requestParagraphContent());
		Paragraph paragraph = new Paragraph(content);
		
		_receiver.addParagraph(index, paragraph);
		_receiver2.addTextElement(paragraph);
		_receiver2.changed();
	}

}
