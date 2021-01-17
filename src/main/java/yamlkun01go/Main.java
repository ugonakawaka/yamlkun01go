package yamlkun01go;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.yaml.snakeyaml.Yaml;

import yamlkun01go.Bunkaikun.AbstractHandler;
import yamlkun01go.Bunkaikun.Handler;

public class Main {

	public static void main(String[] args) throws IOException {

		// CommanderKun commanderKun = new CommanderKun();
		// commanderKun.start();

		final Yaml yaml = new Yaml();
		try (final InputStream inputStream = Files.newInputStream(Paths.get("data/a.yaml"))) {
			Object object = yaml.load(inputStream);
			System.out.println(object);

			Bunkaikun bunkaikun = new Bunkaikun();

			final Handler handler = new AbstractHandler() {

				@Override
				public void push(Object o) {
					String[] oo = { "*", o.toString() };
					super.push(o);
				}

				@Override
				public void terminal(Object o) {
					System.out.println(stack + ":" + o);
					// System.out.println(o.getClass());
				}
			};

			bunkaikun.out(handler, object);

			System.out.println(yaml.dump(object));

			final YMaker yMaker = new YMaker();
			YObject yObject = yMaker.out(null, object);
			System.out.println(yObject);

			bunkaikun.out(new AbstractHandler() {

				@Override
				public void push(Object o) {
					super.push(o);
				}

				@Override
				public void terminal(Object o) {
					// if (o instanceof YObject) {
					final YObject yObject1 = (YObject) o;

					final StringBuilder builder = new StringBuilder();
					builder.append(new YMaker().reverseTraverseOutput(yObject1, "\t"));
					System.out.println(builder.toString() + o);
					return;
					// }
					// System.out.println(stack + ":" + o);

				}
			}, yObject);
		}
	}

}
