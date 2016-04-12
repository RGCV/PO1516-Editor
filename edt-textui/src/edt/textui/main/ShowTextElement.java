package edt.textui.main;

import static ist.po.ui.Dialog.IO;
import ist.po.ui.Command;
import ist.po.ui.DialogException;

import java.io.IOException;

import edt.Editor;
import edt.Document;
import edt.TextElement;
import edt.NoSuchTextElementException;
import edt.textui.section.ShowContentVisitor;

/**
 * §2.1.5.
 */
public class ShowTextElement extends Command<Editor> {
	public ShowTextElement(Editor editor) {
		super(MenuEntry.SHOW_TEXT_ELEMENT, editor);
	}

	@Override
	public final void execute() throws DialogException, IOException {
		Document document = _receiver.getCurrentDocument();
		String uuid = IO.readString(Message.requestElementId());
		
		try {
			TextElement element = document.getTextElement(uuid);
			String content = element.accept(new ShowContentVisitor());
			
			IO.println(content.substring(0,content.length()-1));
		}
		catch (NoSuchTextElementException e) {
			IO.println(Message.noSuchTextElement(uuid));
		}
	}
}
