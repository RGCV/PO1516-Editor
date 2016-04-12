package edt;

/**
 * Visitor interface, for visiting the core class elements.
 */
public interface Visitor {
	/**
	 * @return The string representation of a Document.
	 */
	public String visitDocument(Document document);

	/**
	 * @return The string representation of a section.
	 */
	public String visitSection(Section section);

	/**
	 * @return The string representation of a paragraph.
	 */
	public String visitParagraph(Paragraph paragraph);
}
