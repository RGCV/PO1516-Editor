package edt;

/**
 * This class represents a <code>Paragraph</code>, a <code>TextElement</code>
 * that has plain text.
 * Two paragraphs are the same if their text is the same.
 *
 * @see edt.TextElement
 */
public class Paragraph extends TextElement {

	/** Serial number. */
	private static final long serialVersionUID = 201511292012L;

	/** The paragraph's text. */
	private String _text;

	/**
	 * Creates a paragraph with the given text and empty ("") uuid.
	 *
	 * @param text
	 *     The paragraph's text.
	 * @see #Paragraph(java.lang.String, java.lang.String)
	 */
	public Paragraph(String text) {
		this(text, "");
	}

	/**
	 * Creates a paragraph with the given text and uuid.
	 *
	 * @param text
	 *     The paragraph's text.
	 * @param uuid
	 *     The paragraph's uuid.
	 */
	public Paragraph(String text, String uuid) {
		super(uuid);
		_text = text;
	}

	/**
	 * @return The paragraph's text.
	 */
	public String getText() {
		return _text;
	}
	
	/**
	 * @param text
	 *     The paragraph's new text.
	 */
	public void setText(String text) {
		_text = text;
	}

	/**
	 * @return The paragraph's text.
	 * @see #getText()
	 */
	@Override
	public String getContent() {
		return getText();
	}
	
	/**
	 * @param v
	 *     The visitor.
	 * @return The visitor's string representation of the document.
	 */
	@Override
	public String accept(Visitor v) {
		return v.visitParagraph(this);
	}

	/**
	 * @param paragraph
	 *     The paragraph candidate.
	 * @return true, if they have the same text; false, otherwise.
	 */
	public boolean equals(Paragraph paragraph) {
		return super.equals(paragraph) && _text == paragraph.getText();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Paragraph && equals((Paragraph)obj);
	}

}
