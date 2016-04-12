package edt.textui.section;

import static ist.po.ui.Dialog.IO;

import edt.Visitor;
import edt.Section;
import edt.Paragraph;
import edt.Document;

/**
 * Visitor for showing content.
 * @see edt.Visitor
 */
public class ShowContentVisitor implements Visitor {
	/**
	 * Prints the document's title, its paragraphs and following sections
	 * recursively.
	 *
	 * @return The document's content as a String.
	 */
	@Override
	public String visitDocument(Document document) {
		String content = "{" + document.getTitle() + "}" + "\n";
		
		for (Paragraph p : document.getParagraphs()) {
			content += p.accept(this);
		}
		for (Section s : document.getSubSections()) {
			content += s.accept(this);
		}
		return content;
	}

	/**
	 * Prints the secton's details, its paragraphs and following sections
	 * recursively.
	 *
	 * @return The section's content as a String.
	 */
	@Override
	public String visitSection(Section section) {
		String content = Message.sectionIndexEntry(section.getUUID(),section.getTitle()) + "\n";
		
		for (Paragraph p : section.getParagraphs()) {
			content += p.accept(this);
		}
		for (Section s : section.getSubSections()) {
			content += s.accept(this);
		}
		return content;
	}

	/**
	 * Prints the paragraph as plain text.
	 *
	 * @return The paragraph's text.
	 */
	@Override
	public String visitParagraph(Paragraph paragraph) {
		return paragraph.getText() + "\n";
	}
}
