package scrum.client.img;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

public interface ScrumImageBundle extends ImageBundle {

	@Resource(value = "story32.png")
	public AbstractImagePrototype storyIcon16(); // TODO find corresponding icon with size 16

	@Resource(value = "story32.png")
	public AbstractImagePrototype storyIcon32();

	@Resource(value = "storyDone32.png")
	public AbstractImagePrototype storyDoneIcon16(); // TODO find corresponding icon with size 16

	@Resource(value = "storyDone32.png")
	public AbstractImagePrototype storyDoneIcon32();

	@Resource(value = "impediment16.png")
	public AbstractImagePrototype impedimentIcon16();

	@Resource(value = "impediment32.png")
	public AbstractImagePrototype impedimentIcon32();

	@Resource(value = "impedimentSolved32.png")
	public AbstractImagePrototype impedimentSolvedIcon16(); // TODO find corresponding icon with size 16

	@Resource(value = "impedimentSolved32.png")
	public AbstractImagePrototype impedimentSolvedIcon32();

	@Resource(value = "sprint32.png")
	public AbstractImagePrototype sprintIcon32();

	@Resource(value = "sprint16.png")
	public AbstractImagePrototype sprintIcon16();

	@Resource(value = "task32.png")
	public AbstractImagePrototype taskIcon16(); // TODO find corresponding icon with size 16

	@Resource(value = "task32.png")
	public AbstractImagePrototype taskIcon32();

	@Resource(value = "taskDone32.png")
	public AbstractImagePrototype taskDoneIcon16(); // TODO find corresponding icon with size 16

	@Resource(value = "taskDone32.png")
	public AbstractImagePrototype taskDoneIcon32();

	@Resource(value = "trash32.png")
	public AbstractImagePrototype trashIcon32();

	@Resource(value = "project32.png")
	public AbstractImagePrototype projectIcon16(); // TODO find corresponding icon with size 16

	@Resource(value = "project32.png")
	public AbstractImagePrototype projectIcon32();

	@Resource(value = "plus16.png")
	public AbstractImagePrototype plusIcon16();

	@Resource(value = "minus16.png")
	public AbstractImagePrototype minusIcon16();

	@Resource(value = "action16.png")
	public AbstractImagePrototype action16();

	@Resource(value = "delete16.png")
	public AbstractImagePrototype delete16();

	@Resource(value = "done16.png")
	public AbstractImagePrototype done16();

}
