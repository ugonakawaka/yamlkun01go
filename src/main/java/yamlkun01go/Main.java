package yamlkun01go;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

import yamlkun01go.Bunkaikun.Handler;

public class Main {

	public static void main(String[] args) throws IOException {
		
		
		// CommanderKun commanderKun = new CommanderKun();
		// commanderKun.start();
		
		
		final Yaml yaml = new Yaml();
		try (final InputStream inputStream = Files.newInputStream(Paths.get("data/a.yaml"))) {
	        Object object = yaml.load(inputStream);
			System.out.println(object);
			
			Bunkaikun  bunkaikun = new Bunkaikun();
			
			final Handler handler = new Handler() {
			};
			
			bunkaikun.out(handler, object);
			
	    }
	}

}
