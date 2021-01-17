package yamlkun01go;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 
 * 分解君
 * 
 * @author nakawakashigeto
 *
 */
public class Bunkaikun {

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void map(Handler handler, Map map) {

		map.forEach((k, v) -> {
			handler.push(k);
			out(handler, v);
			handler.pop();
		});
	}

	@SuppressWarnings("rawtypes")
	public void out(Handler handler, Object o) {
		if (o instanceof Map) {
			map(handler, (Map) o);
			return;
		}
		if (o instanceof List) {
			list(handler, (List) o);
			return;
		}

		value(handler, o);
		return;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void list(Handler handler, List list) {
		list.forEach(o -> {
			out(handler, o);
		});
	}

	public void value(Handler handler, Object o) {
		handler.terminal(o);
	}

}
