package br.com.market.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import services.MarketService;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "controller")
public class MainMaket 
{
	static MarketService servico = new MarketService();
	
    public static void main( String[] args )
    {
    	SpringApplication.run(MainMaket.class, args);
    	
    	new Thread(threadProcessamento).start();
    }
    
    private static Runnable threadProcessamento = new Runnable() {
        public synchronized  void run() {
        	while (true) {
    			try {
    				servico.processarPlanilha();
    				this.wait(10000);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
       }
    };
}
