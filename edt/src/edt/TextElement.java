package edt;

import java.io.Serializable;

/**
 * Representation of a text element (declared abstract to prevent direct
 * initialization).
 * Every text element has a Universally Unique Identifier (UUID).
 * Two text elements are the same (in general) if they share a UUID.
 */
public abstract class TextElement implements Serializable {

	/** Serial number. */
	private static final long serialVersionUID = 201511292012L;

	/** The text element's uuid. */
	private String _uuid;

	/**
	 * Creates a text element with an empty ("") uuid.
	 *
	 * @see #TextElement(java.lang.String)
	 */
	public TextElement() {
		this("");
	}

	/**
	 * Creates a text element with the given uuid. (For subclasses to call
	 * only, through super())
	 *
	 * @param uuid
	 *     The uuid of the text element.
	 */
	protected TextElement(String uuid) {
		_uuid = uuid;
	}

	/**
	 * @return The text element's uuid.
	 */
	public final String getUUID() {
		return _uuid;
	}

	/**
	 * @param uuid
	 *     The text element's new uuid.
	 */
	public void setUUID(String uuid) {
		_uuid = uuid;
	}
	
	/**
	 * @return The text element's content.
	 */
	public abstract String getContent();

	/**
	 * @param v
	 *     The visitor.
	 * @return The visitor's string representation of the text element.
	 */
	public abstract String accept(Visitor v);

	/**
	 * @param element
	 *     The text element candidate.
	 * @return true, if they have the same uuid; false, otherwise.
	 */
	public boolean equals(TextElement element) {
		return _uuid == element.getUUID();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof TextElement && equals((TextElement)obj);
	}

}
