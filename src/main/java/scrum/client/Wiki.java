package scrum.client;

import ilarkesto.gwt.client.BetterTextArea;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.Initializer;
import ilarkesto.gwt.client.RichtextFormater;
import ilarkesto.gwt.client.ToolbarWidget;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import scrum.client.collaboration.Wikipage;
import scrum.client.common.AScrumComponent;
import scrum.client.img.Img;
import scrum.client.wiki.ScrumHtmlContext;
import scrum.client.wiki.WikiModel;
import scrum.client.wiki.WikiParser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

public class Wiki extends AScrumComponent implements RichtextFormater {

	@Override
	protected void onInitialization() {
		super.onInitialization();
		Gwt.setDefaultRichtextFormater(this);
		Gwt.setRichtextEditorEditInitializer(new RichtextEditorEditInitializer());
		Gwt.setDefaultRichtextSyntaxInfo(WikiParser.SYNTAX_INFO_HTML);
	}

	public String getTemplate(String name) {
		Wikipage page = getCurrentProject().getWikipage("template:" + name);
		return page == null ? null : page.getText();
	}

	public String richtextToHtml(String text) {
		if (Gwt.isEmpty(text)) return text;
		return toHtml(text);
	}

	public static String toHtml(String wiki) {
		WikiParser parser = new WikiParser(wiki);
		WikiModel model = parser.parse();
		return model.toHtml(new ScrumHtmlContext());
	}

	public SuggestOracle createPagesSuggestOracle() {
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		for (Wikipage page : getCurrentProject().getWikipages()) {
			oracle.add(page.getName());
		}
		return oracle;
	}

	class RichtextEditorEditInitializer implements Initializer<RichtextEditorWidget> {

		public void initialize(final RichtextEditorWidget editor) {
			final ToolbarWidget toolbar = editor.getEditorToolbar();

			toolbar.add(createToolbarButton(Img.bundle.formatTextItalic().createImage(), "Italic", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("''", "''");
					textArea.setFocus(true);
				}
			}));

			toolbar.add(createToolbarButton(Img.bundle.formatTextBold().createImage(), "Bold", new ClickHandler() {

				public void onClick(ClickEvent event) {
					BetterTextArea textArea = editor.getEditor();
					textArea.wrapSelection("'''", "'''");
					textArea.setFocus(true);
				}
			}));
		}
	}

	protected final Widget createToolbarButton(Image icon, String tooltip, ClickHandler clickHandler) {
		PushButton button = new PushButton(icon, clickHandler);
		button.setTitle(tooltip);
		return button;
	}

}
