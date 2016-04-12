package edt;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * This class represents a <code>Document</code>, which is in turn a special
 * <code>Section</code>.
 * It has authors, sections, and paragraphs. It also has collections of all the
 * text elements in the document.
 *
 * @see edt.Author
 * @see edt.Paragraph
 * @see edt.Section
 * @see edt.TextElement
 */
public class Document extends Section {

	/** Serial number. */
	private static final long serialVersionUID = 201511292012L;
	
	/** The modification status of the document. */
	private boolean _changed;

	/** A Map with the document's authors. */
	private Map<String, Author> _authors;

	/** A List with all the document's elements. */
	private Map<String, TextElement> _identifiedTextElements;
	private List<TextElement> _unidentifiedTextElements;

	/**
	 * Creates a new untitled document.
	 *
	 * @see #Document(java.lang.String)
	 */
	public Document() {
		this("");
	}

	/**
	 * Creates a new document with a given title.
	 *
	 * @param title
	 *     The document's title.
	 */
	public Document(String title) {
		super(title);
		_changed = true;
		_authors = new TreeMap<String, Author>();
		_identifiedTextElements = new LinkedHashMap<String, TextElement>();
		_unidentifiedTextElements = new ArrayList<TextElement>();
	}

	/**
	 * The size of a document is obtained through counting the number of
	 * characters in it, taking into account every paragraph and the title
	 * of every sections it has.
	 *
	 * @return the document's size, in bytes.
	 */
	public int getSize() {
		int size = getTitle().length();
		
		for (TextElement e : _identifiedTextElements.values()) {
			size += e.getContent().length();
		}
		for(TextElement e : _unidentifiedTextElements) {
			size += e.getContent().length();
		}

		return size;
	}

	/**
	 * Adds a new text element to the collections that maintain every text
	 * element in the document.
	 *
	 * @param element
	 *     The element being added at the end of the list.
	 */
	public void addTextElement(TextElement element) {
		if (!element.getUUID().equals(""))
			_identifiedTextElements.put(element.getUUID(), element);
		else
			_unidentifiedTextElements.add(element);
	}

	/**
	 * Gets a text element using the uuid given.
	 *
	 * @param uuid
	 *     The uuid of the text element to return, if present.
	 * @return The text element with the given uuid, if found.
	 * @throws edt.NoSuchTextElementException
	 */
	public TextElement getTextElement(String uuid) throws NoSuchTextElementException {
		TextElement element = _identifiedTextElements.get(uuid);
		if (element != null)
			return element;
		else
			throw new NoSuchTextElementException();
	}

	/**
	 * Names/Identifies a text element with the given uuid, performing the
	 * changes necessary if the uuid exists already or if the element being
	 * renamed is unidentified.
	 *
	 * @param uuid
	 *     The new uuid of the element.
	 * @param element
	 *     The element being renamed.
	 * @throws edt.UUIDChangedException
	 */
	public void nameTextElement (String uuid, TextElement element) throws UUIDChangedException {
		boolean hasID = !element.getUUID().equals("");
		
		if(hasID) {
			_identifiedTextElements.remove(element.getUUID());
		} else {
			_unidentifiedTextElements.remove(element);
		}

		element.setUUID(uuid);
		if(_identifiedTextElements.containsKey(uuid)) {
		    TextElement e = _identifiedTextElements.remove(uuid);
		    e.setUUID("");
		    _unidentifiedTextElements.add(e);
		}
		_identifiedTextElements.put(uuid, element);

		if(hasID) {
			throw new UUIDChangedException();
		}
	}

	/**
	 * @param section
	 *     The section being removed.
	 */
	public void removeTextElement(Section section) {
		
		for (Paragraph p : section.getParagraphs()) {
			removeTextElement(p);
		}
		for (Section s : section.getSubSections()) {
			removeTextElement(s);
		}
		
		if (!section.getUUID().equals("")) {
			_identifiedTextElements.remove(section.getUUID());
		}
		else {
			_unidentifiedTextElements.remove(section);
		}
	}
	/**
	 * @param paragraph
	 *     The paragraph being removed.
	 */
	public void removeTextElement(Paragraph paragraph) {
		if (!paragraph.getUUID().equals("")) {
			_identifiedTextElements.remove(paragraph.getUUID());
		}
		else {
			_unidentifiedTextElements.remove(paragraph);
		}
	}

	/**
	 * @param author
	 *     The author being added to the Map.
	 * @see #addAuthor(java.lang.String, java.lang.String)
	 */
	public void addAuthor(Author author) {
		_authors.put(author.getName(), author);
	}

	/**
	 * @param name
	 *     The name of the author being added.
	 * @param email
	 *     The author's email.
	 * @throws DuplicateAuthorException
	 */
	public void addAuthor (String name, String email) throws DuplicateAuthorException {
		if (_authors.containsKey(name))
			throw new DuplicateAuthorException();
		else
			addAuthor(new Author(name, email));
	}

	/**
	 * @return The authors of the document as an unmodifiableCollection.
	 */
	public Collection<Author> getAuthors() {
		return Collections.unmodifiableCollection(_authors.values());
	}

	/**
	 * @return The number of subsections the document has.
	 */
	public int countTopSections() {
		return getSubSections().size();
	}

	/**
	 * @return The number of text elements with a UUID.
	 */
	public int countUUID() {
		return _identifiedTextElements.size();
	}

	/**
	 * @return true, if document was changed; false, otherwise.
	 */
	public boolean hasChanges() {
		return _changed;
	}
	
	/**
	 * Sets the current document's status to changed.
	 */
	public void changed() {
		_changed = true;
	}

	/**
	 * Sets the current document's status to saved.
	 */
	public void saved() {
		_changed = false;
	}
	
	/**
	 * @param v
	 *     The visitor.
	 * @return The visitor's string representation of the document.
	 */
	@Override
	public String accept(Visitor v) {
		return v.visitDocument(this);
	}

}
