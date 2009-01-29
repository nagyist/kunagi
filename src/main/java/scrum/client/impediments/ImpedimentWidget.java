package scrum.client.impediments;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.ABlockWidget;
import scrum.client.common.ItemFieldsWidget;
import scrum.client.common.ToolbarWidget;
import scrum.client.common.editable.AEditableTextWidget;
import scrum.client.common.editable.AEditableTextareaWidget;
import scrum.client.dnd.BlockListDropController;
import scrum.client.img.Img;
import scrum.client.workspace.WorkspaceWidget;

import com.allen_sauer.gwt.dnd.client.drop.DropController;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentWidget extends ABlockWidget {

	/**
	 * The impediment, this widget is managing.
	 */
	private Impediment impediment;

	public ImpedimentWidget(Impediment impediment) {
		this.impediment = impediment;
	}

	@Override
	protected String getBlockTitle() {
		return impediment.getLabel();
	}

	@Override
	protected AbstractImagePrototype getIcon16() {
		// return different icon depending on solved-status
		if (impediment.isSolved()) return Img.bundle.impedimentSolvedIcon16();
		return Img.bundle.impedimentIcon16();
	}

	@Override
	protected AbstractImagePrototype getIcon32() {
		// return different icon depending on solved-status
		if (impediment.isSolved()) return Img.bundle.impedimentSolvedIcon32();
		return Img.bundle.impedimentIcon32();
	}

	@Override
	protected Widget buildContent() {
		if (!isExtended()) // block is not extended -> return only a label with the summary
			return new Label(impediment.getSummary());

		// block is extended -> create an ItemFieldsWidget
		ItemFieldsWidget fieldsWidget = new ItemFieldsWidget();
		fieldsWidget.addField("Label", new AEditableTextWidget() {

			@Override
			protected String getText() {
				return impediment.getLabel();
			}

			@Override
			protected void setText(String text) {
				impediment.setLabel(text);
				rebuild();
			}

		});
		fieldsWidget.addField("Description", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return impediment.getDescription();
			}

			@Override
			protected void setText(String text) {
				impediment.setDescription(text);
			}
		});
		fieldsWidget.addField("Solution", new AEditableTextareaWidget() {

			@Override
			protected String getText() {
				return impediment.getSolution();
			}

			@Override
			protected void setText(String text) {
				impediment.setSolution(text);
			}
		});
		return fieldsWidget;
	}

	@Override
	protected Widget buildToolbar() {
		if (!isExtended()) // block is not extended -> no toolbar
			return null;

		// block is extended -> create toolbar with buttons
		ToolbarWidget toolbar = new ToolbarWidget();

		toolbar.addButton(Img.bundle.delete16().createImage(), "Delete").addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				ScrumGwtApplication.get().getProject().deleteImpediment(impediment);
				WorkspaceWidget.impediments.list.removeSelectedRow();
			}
		});

		if (!impediment.isSolved()) {
			// impediment not solved -> add [Solve] button
			toolbar.addButton(Img.bundle.done16().createImage(), "Solve").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					impediment.setSolved(true);
					rebuild();
				}
			});
		} else {
			// impediment not solved -> add [Unsolve] button
			toolbar.addButton("Unsolve").addClickListener(new ClickListener() {

				public void onClick(Widget sender) {
					impediment.setSolved(false);
					rebuild();
				}
			});
		}

		return toolbar;
	}

	@Override
	protected DropController createDropController() {
		return new BlockListDropController(this, WorkspaceWidget.impediments.list);
	}

	@Override
	public void delete() {
		ScrumGwtApplication.get().getProject().deleteImpediment(impediment);
		WorkspaceWidget.impediments.list.remove(this);
	}
}
