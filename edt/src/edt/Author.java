package edt;

import java.io.Serializable;

/**
 * A <code>Document</code>'s <code>Author</code> is represented both by
 * his/her name and email address.
 * Two authors are the same if their names and emails are the same.
 *
 * @see edt.Document
 */
public class Author implements Serializable {

	/** Serial number. */
	private static final long serialVersionUID = 201511151647L;

	/** The author's name. */
	private String _name;

	/** The author's email address. */
	private String _email;

	/**
	 * @param name
	 *     The author's name.
	 * @param email
	 *     The author's email address.
	 */
	public Author(String name, String email) {
		_name = name;
		_email = email;
	}

	/**
	 * @return The author's name.
	 */
	public String getName() {
		return _name;
	}
  
	/**
	 * @param name
	 *     The author's new name
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * @return The author's email address.
	 */
	public String getEmail() {
		return _email;
	}

	/**
	 * @param email
	 *     The author's new email address.
	 */
	public void setEmail(String email) {
		_email = email;
	}

	/**
	 * @param author
	 *     The candidate author.
	 * @return true, if both authors have the same name and email;
	 *     false, otherwise.
	 */
	public boolean equals(Author author) {
		return _name.equals(author.getName()) && _email.equals(author.getEmail());
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Author && equals((Author)obj);
	}

}
