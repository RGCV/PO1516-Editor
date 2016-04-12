package edt.textui.section;

import static ist.po.ui.Dialog.IO;

import edt.Visitor;
import edt.Section;
import edt.Paragraph;
import edt.Document;

/**
 * Visitor for listing sections.
 * @see edt.Visitor
 */
public class ListSectionsVisitor implements Visitor {
	/**
	 * Prints the document and its subsections, calling <code>visitSection</code>
	 * (they're the same in this context).
	 *
	 * @return The document and its subsections as a String.
	 * @see #visitSection(edt.Section)
	 */
	@Override
	public String visitDocument(Document document) {
		return visitSection(document);
	}

	/**
	 * Prints the section and its subsections recursively.
	 *
	 * @return The section and its subsections as a String.
	 */
	@Override
	public String visitSection(Section section) {
		String content = "";
		
		for(Section s : section.getSubSections()) {
			content += Message.sectionIndexEntry(s.getUUID(),s.getTitle()) + "\n";
			content += s.accept(this);
		}
		return content;
	}

	/**
	 * Doesn't print anything, as paragraphs aren't shown when the ListSections
	 * command is issued.
	 *
	 * @return Empty string.
	 */
	@Override
	public String visitParagraph(Paragraph paragraph) {
		return "";
	}
}
