package yamlkun01go;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.undo.UndoManager;

import yamlkun01go.Bunkaikun.Handler;
import yamlkun01go.Bunkaikun.HandlerStack;

public class YMaker {

	public interface Handler extends HandlerStack {
		@SuppressWarnings("rawtypes")
		public default void before(Handler handler, Map map) {
		}

		@SuppressWarnings("rawtypes")
		public default void before(Handler handler, List list) {
		}

		@SuppressWarnings("rawtypes")
		public default void after(Handler handler, Map map) {
		}

		@SuppressWarnings("rawtypes")
		public default void after(Handler handler, List list) {
		}
	}

	@SuppressWarnings("rawtypes")
	public interface HandlerStack {

		public void push(Object o);

		public Object pop();

		public Stack stack();

		public void terminal(Object o);
	}

	@SuppressWarnings("rawtypes")
	public static abstract class AbstractHandler implements Handler {

		public Stack stack = new Stack();

		@SuppressWarnings("unchecked")
		@Override
		public void push(Object o) {
			this.stack.push(o);
		}

		@Override
		public Object pop() {
			return this.stack.pop();
		}

		@Override
		public Stack stack() {
			return stack;
		}
	}

	public String reverseTraverse(YObject yObject) {

		final YObject parent = yObject.parent();
		if (parent == null) {
			return "";
		}

		if (parent instanceof YMap) {
			final YObject parentOfParent = parent.parent();
			if (parentOfParent == null) {
				return null;
			}

			if (parentOfParent instanceof YList) {
				YMap yMap = (YMap) parent;
				return "*";
			}

		}
		return "";
	}

	public String reverseTraverseOutput(YObject yObject, String separator) {

		if (yObject == null) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		if (yObject.label() != null) {
			builder.insert(0, yObject.label() + separator);
		}

		YObject parent = yObject.parent();
		if (yObject instanceof YMap) {
			if (parent instanceof YList) {
				builder.append("*").append(separator);
			} else {
				builder.append(separator);
			}
		} else {
			builder.append(separator);
		}
		
		

		return builder.insert(0, reverseTraverseOutput(yObject.parent(), separator)).toString();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public YMap map(Handler handler, Map map) {

		final YMap yMap = new YMap();
		map.forEach((k, v) -> {
			yMap.put(k, out(handler, yMap, v).setLabel(k.toString()));
		});

		return yMap;
	}

	public YObject out(Handler handler, Object o) {
		return out(handler, null, o);
	}

	@SuppressWarnings("rawtypes")
	public YObject out(Handler handler, YObject me, Object o) {
		if (o instanceof Map) {

			return map(handler, (Map) o).setParent(me);
		}
		if (o instanceof List) {

			return list(handler, (List) o).setParent(me);
		}

		return value(handler, o).setParent(me);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public YList list(Handler handler, List list) {

		YList yList = new YList();

		list.forEach(o -> {
			yList.add(out(handler, yList, o));
		});

		return yList;
	}

	public YValue value(Handler handler, Object o) {

		YValue yValue = new YValue();
		yValue.value = o;

		return yValue;
	}
}
