package yamlkun01go;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Bunkaikun {

	public interface Handler {
		@SuppressWarnings("rawtypes")
		public Stack stack = new Stack();
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void map(Handler handler,  Map map) {

		map.forEach((k, v) -> {

			handler.stack.push(k);
			out(handler, v);
			handler.stack.pop();
		});
	}

	@SuppressWarnings("rawtypes")
	public void out(Handler handler,  Object o) {
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
	public void list(Handler handler,  List list) {

		list.forEach(o -> {
			out(handler, o);
		});

	}

	@SuppressWarnings("unchecked")
	public void value(Handler handler,  Object o) {

		System.out.println(handler.stack + ":" + o);
	}

}
