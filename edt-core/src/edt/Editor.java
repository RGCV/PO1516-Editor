package edt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.regex.Pattern;

/**
 * The <code>Editor</code> is responsible for managing a <code>Document</code>.
 * It has an instance of the current document being edited, tracks its status
 * (if it was modified or not), and keeps the document's file name.
 *
 * @see edt.Document
 */
public class Editor {

	/** The current document being edited. */
	private Document _currentDocument;

	/** The document's file name. */
	private String _fileName;

	/**
	 * Creates a new editor with a fresh unchanged document. The document's
	 * file name defaults to empty (""), untitled.
	 */
	public Editor() {
		_currentDocument = new Document();
		_fileName = "";
	}

	/**
	 * A 'simple factory' used to read a file and generate a document.
	 *
	 * @param name
	 *     The name of the import file.
	 * @throws java.io.IOException
	 * @see java.io.FileReader(java.lang.String)
	 */
	public void readImportFile(String name) throws IOException {
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(name));

		if((line = reader.readLine()) != null)
			_currentDocument.setTitle(line);
		if((line = reader.readLine()) != null)
			createAuthors(line.split("\\|"));
		
		while ((line = reader.readLine()) != null) {
			String fields[] = line.split("\\|");
			try {
				createFromFields(fields);
			} catch (UnknownDataException e) {
				newDocument();
				e.printStackTrace();
			}
		}

		reader.close();
	}

	/**
	 * Takes the fields from every data line it reads from the import file
	 * and attempts to create text elements, if possible.
	 *
	 * @param fields
	 *     The data types extracted from reading the import file.
	 * @see #readImportFile(java.lang.String)
	 */
	public void createFromFields(String... fields) throws UnknownDataException {
		Pattern patTextElement = Pattern.compile("^(SECTION|PARAGRAPH)");
		
		if (patTextElement.matcher(fields[0]).matches()) {
			createTextElement(fields);
		} else {
			throw new UnknownDataException();
		}
	}

	/**
	 * Creates and adds authors from the fields after reading them from the
	 * import file.
	 *
	 * @param fields
	 *     The authors read from the import file.
	 * @see #readImportFile(java.lang.String)
	 */
	public void createAuthors(String... fields) {
		for (String author : fields) {
			String[] authorInfo = author.split("/");
			_currentDocument.addAuthor(new Author(authorInfo[0], authorInfo[1]));
			changed();
		}
	}

	/**
	 * Creates and adds text elements from the fields after reading them from
	 * the import file.
	 *
	 * @param fields
	 *     The text elements read from the import file.
	 * @see #readImportFile(java.lang.String)
	 */
	public void createTextElement(String... fields) {
		//String uuid = fields[0].equals("SECTION") ? fields[1] : "";
		if(fields[0].equals("SECTION")) {
			Section section = new Section(fields[1], fields[2]);
			_currentDocument.addTextElement(section);
			_currentDocument.addSection(section);
			changed();
		} else {
			Paragraph paragraph = new Paragraph(fields[1]);
			_currentDocument.addTextElement(paragraph);
			_currentDocument.getLastSection().addParagraph(paragraph);
			changed();
		}
	}

	/**
	 * Opens a document the given file name, if it exists.
	 *
	 * @param fileName
	 *     The file name of the document being opened.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void openDocument(String fileName) throws FileNotFoundException,	IOException {
		ObjectInputStream ois = new ObjectInputStream(
			new BufferedInputStream(new FileInputStream(fileName + ".edt")));	
		try {
			Document document = (Document)ois.readObject();
			if(document == null) {
				throw new FileNotFoundException();
			} else {
				setCurrentDocument(document);
				setFileName(fileName);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		ois.close();
	}

	/**
	 * Saves the current document with the given fileName.
	 *
	 * @param fileName
	 *     The file name of the document being saved.
	 */
	public void saveDocument(String fileName) throws IOException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(fileName+".edt")));
			oos.writeObject(_currentDocument);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		saved();
	}
	/**
	 * @return true, if document was changed; false, otherwise.
	 */
	public boolean hasChanges() {
		return _currentDocument.hasChanges();
	}
	
	/**
	 * Sets the current document's status to changed.
	 */
	public void changed() {
		_currentDocument.changed();
	}

	/**
	 * Sets the current document's status to saved.
	 */
	public void saved() {
		_currentDocument.saved();
	}

	/**
	 * @return The document being currently edited.
	 */
	public Document getCurrentDocument() {
		return _currentDocument;
	}

	/**
	 * Initializes a new document with an empty title.
	 */
	public void newDocument() {
		_currentDocument = new Document();
		_fileName = "";
		changed();
	}

	/**
	 * @param document
	 *     The document to be set as the current document.
	 */
	public void setCurrentDocument(Document document) {
		_currentDocument = document;
	}

	/**
	 * @return The current document's file name.
	 */
	public String getFileName() {
		return _fileName;
	}

	/**
	 * @param fileName
	 *     The current document's new file name.
	 */
	public void setFileName(String fileName) {
		_fileName = fileName;
	}

}
