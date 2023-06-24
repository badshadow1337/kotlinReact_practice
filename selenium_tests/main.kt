import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.ExpectedConditions.*
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FirstTest {
    private lateinit var driver: WebDriver

    @BeforeAll
    fun setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\QWERTY\\yniver\\chromedriver.exe")
        val options = ChromeOptions()
        options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe")
        driver = ChromeDriver(options)
    }

    @AfterEach
    fun teardown() {
        driver.quit()
    }

    @Test
    fun main() {
        driver.get("https://crossbrowsertesting.github.io/drag-and-drop")
        val actions = Actions(driver)
        val e1 = driver.findElement(By.id("draggable"))
        val e2 = driver.findElement(By.id("droppable"))
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3))
        actions.dragAndDrop(e1,e2).build().perform()


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3))
        val wait = WebDriverWait(driver,Duration.ofSeconds(5))
        driver.get("https:\\pagination.js.org")
        Thread.sleep(3000)
        var elements:List<WebElement> = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"))
        var txt = elements[2].text
        println(txt)
        var pages:List<WebElement> = driver.findElements(By.xpath("//div[@class='paginationjs-pages']/ul/li"))
        pages[2].click()
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='data-container']/ul/li[2]"),"12"))
        elements = driver.findElements(By.xpath("//div[@class='data-container']/ul/li"))
        txt = elements[2].text
        println(txt)


        val wait = WebDriverWait(driver,Duration.ofSeconds(2))
        driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")
        driver.findElement(By.xpath("//*[@id=\"alertid\"]")).click()
        val alert = wait.until(alertIsPresent())
        alert.accept()


        driver.get("https://crossbrowsertesting.github.io/selenium_example_page.html")
        Thread.sleep(2000)
        val w1 = driver.windowHandle

        driver.findElement(By.xpath("//*[@id=\"pop-up-page\"]/button[1]")).click()
        Thread.sleep(2000)

        val ws:Set<String> = driver.windowHandles
        var w2:String = ""
        for (i in ws){
            if (i!=w1)
                w2=i
        }
        driver.switchTo().window(w2)
        Thread.sleep(2000)
//        assertEquals("This is inside the popup window", driver.findElement(By.tagName("h4")).text) //don't work
        driver.close()

        Thread.sleep(2000)
        driver.switchTo().window(w1)


        driver.get("https://humanbenchmark.com/tests/reactiontime")
        Thread.sleep(500)
        val clickarea =
            driver.findElement(By.cssSelector("#root > div > div:nth-child(4) > div.view-splash.e18o0sx0.css-saet2v.e19owgy77"))
        clickarea.click()

        val textdiv = driver.findElement(By.xpath("(//div)[15]"))
//        val green = driver.findElement(By.xpath("(//div)[contains(text(), \"Click!\")]"))

        val waitTime = WebDriverWait(driver, Duration.ofSeconds(10))
        val greenwait = waitTime.until(ExpectedConditions.textToBe(By.xpath("(//div)[15]"),"Click!"))
        clickarea.click()

        Thread.sleep(2000)
        driver.quit()
    }
}