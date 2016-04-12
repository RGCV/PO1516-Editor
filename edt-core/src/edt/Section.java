package edt;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;

/**
 * This class represents a <code>section</code>, a <code>TextElement</code>
 * which can hold paragraphs or other sections.
 * Could be empty or in turn have more sections (subsections) and/or
 * paragraphs.
 *
 * @see edt.TextElement
 */
public class Section extends TextElement {

	/** Serial number. */
	private static final long serialVersionUID = 201511292012L;

	/** The section's title. */
	private String _title;

	/** The section's subsections. */
	private List<Section> _subSections;

	/** The section's paragraphs. */
	private List<Paragraph> _paragraphs;

	/**
	 * Creates a new section with the given title, empty ("") uuid.
	 *
	 * @param title
	 *     The section's title.
	 * @see #Section(java.lang.String, lava.lang.String)
	 */
	public Section(String title) {
		this("", title);
	}

	/**
	 * Creates a new section with the given title and UUID.
	 *
	 * @param uuid
	 *     The section's UUID.
	 * @param title
	 *     The section's title.
	 */
	public Section(String uuid, String title) {
		super(uuid);
		_title = title;
		_subSections = new LinkedList<Section>();
		_paragraphs = new LinkedList<Paragraph>();
	}

	/**
	 * @return The section's title.
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * @param title
	 *     The section's new title.
	 */
	public void setTitle(String title) {
		_title = title;
	}

	/**
	 * @return The section's subsections as an unmodifiableList.
	 */
	public List<Section> getSubSections() {
		return Collections.unmodifiableList(_subSections);
	}

	/**
	 * @return The section's last subsection.
	 */
	public Section getLastSection() {
		int size = _subSections.size();
		return size > 0 ? _subSections.get(size - 1) : this;
	}

	/**
	 * @param index
	 *     Index of the section being added.
	 * @param section
	 *     The section being added.
	 * @see #addSection(edt.Section)
	 */
	public void addSection(int index, Section section) {
		if (index >= 0 && index < _subSections.size())
			_subSections.add(index, section);
		else
			addSection(section);
	}

	/**
	 * @param section
	 *     The section being added at the end of the list.
	 * @return true, if the section was added successfully (List changed);
	 *     false, otherwise.
	 */
	public boolean addSection(Section section) {
		return _subSections.add(section);
	}

	/**
	 * @param index
	 *     The index of the section to return.
	 */
	public Section getSection(int index) {
		return _subSections.get(index);
	}

	/**
	 * @param index
	 *     The index of the section being removed, if present.
	 * @return The section previously at the specified position.
	 */
	public Section removeSection(int index) {
		return _subSections.remove(index);
	}

	/**
	 * @return The section's paragraphs as an unmodifiableList.
	 */
	public List<Paragraph> getParagraphs() {
		return Collections.unmodifiableList(_paragraphs);
	}
	
	/**
	 * @param index
	 *     Index of the paragraph being added.
	 * @param paragraph
	 *     The paragraph being added.
	 * @see #addParagraph(edt.Paragraph)
	 */
	public void addParagraph(int index, Paragraph paragraph) {
		if (index >= 0 && index < _paragraphs.size())
			_paragraphs.add(index, paragraph);
		else
			addParagraph(paragraph);
	}

	/**
	 * @param paragraph
	 *     The paragraph being added at the end of the list.
	 * @return true, if the paragraph was added successfully (List changed);
	 *     false, otherwise.
	 */
	public boolean addParagraph(Paragraph p) {
		return _paragraphs.add(p);
	}

	/**
	 * @param index
	 *     The index of the paragraph to return.
	 */
	public Paragraph getParagraph(int index) {
		return _paragraphs.get(index);
	}

	/**
	 * @param index
	 *     The index of the paragraph being removed, if present.
	 * @return The paragraph previously at the specified position.
	 */
	public Paragraph removeParagraph(int index) {
		return _paragraphs.remove(index);
	}
	
	/**
	 * @return The section's title.
	 * @see #getTitle()
	 */
	@Override
	public String getContent() {
		return getTitle();
	}

	/**
	 * @param v
	 *     The visitor.
	 * @return The visitor's string representation of the Section.
	 */
	@Override
	public String accept(Visitor v) {
		return v.visitSection(this);
	}

	/**
	 * @param section
	 *     The section candidate.
	 * @return true, if they have the same text; false, otherwise.
	 */
	public boolean equals(Section section) {
		return super.equals(section) && _title == section.getTitle();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Section && equals((Section)obj);
	}

}
