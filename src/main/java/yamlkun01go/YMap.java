package yamlkun01go;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class YMap extends LinkedHashMap implements YObject {
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
