package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import services.MarketService;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "controller")
public class MainMarket 
{
	static MarketService servico = new MarketService();
	
    public static void main( String[] args )
    {
    	SpringApplication.run(MainMarket.class, args);
    	
    	//Thread para processar as planilhas enviadas.
    	new Thread(threadProcessamento).start();
    }
    
    private static Runnable threadProcessamento = new Runnable() {
        public synchronized  void run() {
        	while (true) {
    			try {
    				servico.processarPlanilha();
    				this.wait(5000);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
       }
    };
}
