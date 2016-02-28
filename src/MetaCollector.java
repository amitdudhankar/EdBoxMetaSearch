import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.print.Doc;

import org.apache.bcel.generic.Select;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.sun.jna.platform.win32.OaIdl.DATE;
import com.thoughtworks.selenium.webdriven.Timer;

public class MetaCollector {
	public static void Collecotr(String input) throws Exception {
		String url = "https://www.edx.org/course?search_query=" + URLEncoder.encode(input);
		String url_courera = "https://www.udacity.com/courses/all";
		// System.out.println(url);
		Document doc;

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en");
		System.setProperty("webdriver.chrome.driver", "D:\\JAVAworkspace\\EdBoxMetaSearch\\chromedriver.exe");
		// capabilities.setBrowserName("metachrome");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		// WebDriver driver = new ChromeDriver(capabilities);
		List<WebDriver> driverPool = new ArrayList<WebDriver>();
		for (int i = 0; i < 1; i++) {
			WebDriver driver = new ChromeDriver(capabilities);
			Runtime.getRuntime().exec("D:\\JAVAworkspace\\EdBoxMetaSearch\\HideSearchBrowser.exe");
			driverPool.add(driver);
		}

		driverPool.get(0).get(url_courera);
		WebDriverWait wait = new WebDriverWait(driverPool.get(0), 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div[1]/div[1]/div[2]/div/div/div/input")));
		WebElement element_enter = driverPool.get(0).findElement(By.xpath("//*[@id=\"page-content\"]/div[1]/div[1]/div[2]/div/div/div/input"));
		element_enter.findElement(By.xpath("//*[@id=\"page-content\"]/div[1]/div[1]/div[2]/div/div/div/input")).sendKeys("Java");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-content\"]/div[1]/div[3]")));
		// scroll to bottom
		scrollToBottom(driverPool.get(0));

		doc = Jsoup.parse(driverPool.get(0).getPageSource());
		System.out.println(doc);
		String cssQuery = "#page-content > div.container > div.row.row-gap-medium > div.col-xs-9.course-list > div > div> div.col-xs-3 > a";

		Elements newsHeadlines = doc.select(cssQuery);
		for (Element e : newsHeadlines) {
			System.out.println(e.attr("href"));
		}

		// driverPool.get(0).get("https://www.coursera.org/course/intropoojava");
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"rendered-content\"]/div/div/div[2]/div/div/div/div[3]/div[1]/div/div[6]/div")));
		// scrollToBottom(driverPool.get(0));
		// doc = Jsoup.parse(driverPool.get(0).getPageSource());
		// // System.out.println(doc);
		// String lengthQury =
		// "#rendered-content > div > div > div.rc-SparkCourseDescriptionPage > div > div > div > div.bt3-row > div.bt3-col-sm-4.bt3-col-sm-offset-1.bt3-col-sm-push-7 > div > div.c-cs-sessions > select > option:nth-child(1)";
		// Elements length = doc.select(lengthQury);
		//
		// System.out.println("length:"+ length.get(0).text());
		driverPoolQuit(driverPool);
	}

	private static void driverPoolQuit(List<WebDriver> driverPool) {
		for (int i = 0; i < driverPool.size(); i++) {
			driverPool.get(i).quit();
		}
	}

	private static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < 10; i++) {
			js.executeScript("window.scrollBy(0,3000)", "");
		}
	}
}
