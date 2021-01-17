package yamlkun01go;

public class YValue implements YObject {

	public String label;
	public YObject parent;
	public Object value;

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
	public String toString() {
		return "" + value + "";
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
