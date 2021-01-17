package yamlkun01go;

import java.util.ArrayList;


@SuppressWarnings("rawtypes")
public class YList extends ArrayList implements YObject {
	public String label;
	public YObject parent;

	@Override
	public YObject parent() {
		return parent;
	}

	@Override
	public YObject setParent(YObject yObject) {
		this.parent = yObject;
		return this;
	}

	@Override
	public String label() {
		return label;
	}

	@Override
	public YObject setLabel(String label) {
		this.label = label;
		return this;
	}

}
