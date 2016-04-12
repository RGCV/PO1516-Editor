package edt.textui.section;

import ist.po.ui.Command;

import edt.Document;
import edt.Section;

/**
 * Superclass of all section-context commands.
 */
public abstract class SectionCommand extends Command<Section> {
  
  protected Document _receiver2 = null;
  
	public SectionCommand(String title, Section section, Document document) {
		super(title, section);
		_receiver2 = document;
	}

}
