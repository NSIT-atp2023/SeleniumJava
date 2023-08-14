package WebBrowser;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Carla {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://vaclmweb1.brevardclerk.us/AcclaimWeb/");
		driver.findElement(By.xpath("//img[@alt='Name']")).click();
		driver.findElement(By.xpath("//input[@id='btnButton']")).click();
		driver.findElement(By.id("SearchOnName")).sendKeys("Stokes, Carla");
		driver.findElement(By.id("btnSearch")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Done']")).click();
		String parentWH = driver.getWindowHandle();
		Thread.sleep(1000);
		driver.findElement(By.id("toggleForm")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='t-dropdown t-header']/div[@class='t-dropdown-wrap t-state-default']/ span[@class='t-select']  /span[@class='t-icon t-arrow-down']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[text()='500']")).click();
		Thread.sleep(1000);
		List<WebElement> allRows = driver.findElements(By.xpath("//div[@class='rowNumClass']"));
		for(int i=0;i<allRows.size();i++) {
			Thread.sleep(2000);
            allRows.get(i).click();
			Thread.sleep(1500);
			Set<String> allWH = driver.getWindowHandles();
			for (String handle : allWH) {
	            if (!handle.equals(parentWH)) {
	                driver.switchTo().window(handle);
	                Thread.sleep(3000);
	        		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='imgFrame1']")));
	        		String value=driver.findElement(By.xpath("//iframe[@id='ImageInPdf']")).getAttribute("src");
	        		System.out.println("Link of PDF "+(i+1)+" :>> "+value);
	        		driver.close();
	        		driver.switchTo().window(parentWH);
	        		break;
	            }
	        }
		}
		driver.quit();
	}
}
