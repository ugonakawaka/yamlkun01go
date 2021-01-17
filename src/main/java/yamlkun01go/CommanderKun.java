package yamlkun01go;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommanderKun {

	public interface Handler {
		
		default public void action() {}
	}
	
	
	public interface HandlerCommand extends Handler {
		public void handleCommand(Scanner scanner) ;
	}

	public static final String PROMPT = ">"; 
	
	final Map<String, Handler> map = new HashMap<String, CommanderKun.Handler>();
	
	final Scanner scanner = new Scanner(System.in);
	
	public boolean ready = false;
	public void start() {
		
		do {
			print();
			String line = scanner.next();
			System.out.println(line);
			Handler handler = map.get(line);
			if (handler != null) {
				handler.action();
			}
			
			
		} while(ready);
	}
	
	void print() {
		System.out.print(PROMPT);
	}
	
}
