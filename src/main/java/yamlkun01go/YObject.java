package yamlkun01go;

public interface YObject {
	public YObject parent();
	public YObject setParent(YObject yObject);
	
	public String label();
	public YObject setLabel(String label);
	
}
