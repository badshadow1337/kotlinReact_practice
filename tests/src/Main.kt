import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import org.w3c.dom.html.HTMLInputElement
import java.time.Duration
import kotlin.concurrent.timer

fun main(args: Array<String>) {
    val driver = ChromeDriver()
    driver.get("http://127.0.0.1:8080/")
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1))

    driver.findElement(By.id("LinkToCourseMath")).click() // Переход на курс математики
    Thread.sleep(3_000) // Задержка перед дальнейшими действиями
    val sheldon = driver.findElement(By.id("StudentItemSheldon Cooper"))
    val inputRadioSheldon5 = driver.findElement(By.id("MathSheldon Cooper05"))
    val unmarkedFont = sheldon.getCssValue("font-weight") //Изначально не маркирован
    inputRadioSheldon5.click()
    val gradeSheldonOnStudentList = driver.findElement(By.id("MathSheldon Cooper0Grade"))
        .getAttribute("textContent") //Оценка шелдона в списке студентов на математике
    val markedFont =
        sheldon.getCssValue("font-weight") //После выставления оценки произошел клик и студент теперь маркирован
    Thread.sleep(3_000)
    driver.findElement(By.id("LinkToStudentSheldon Cooper")).click()
    val gradeSheldonOnCourseList =
        driver.findElement(By.id("Sheldon CooperMath0Grade"))
            .getAttribute("textContent") //Оценка шелдона в списке курсов
    check(driver.findElement(By.id("CourseItemMath")).getCssValue("font-weight") == markedFont)
    check(driver.findElement(By.id("CourseItemPhys")).getCssValue("font-weight") == unmarkedFont)
    check(gradeSheldonOnStudentList == gradeSheldonOnCourseList) //проверка сохраности оценки у студента при переходе по ссылкам

    /**Тест компонента цвет**/
    Thread.sleep(3_000)
    val inputChoice = driver.findElement(By.id("InputChoiceGrade"))
    val color = driver.findElement(By.id("InputChoiceColor"))
    inputChoice.sendKeys("5")
    color.click()
    color.sendKeys("#00FF00")
    inputChoice.click()
    check(
        driver.findElement(By.id("Sheldon CooperMath0Grade")).getCssValue("color") == "rgba(0, 255, 0, 1)"
    ) //Проверка цвета оценки

    /**Тест компонента мод full short**/
    Thread.sleep(3_000)
    val modeShort = driver.findElement(By.id("Short"))
    driver.findElement(By.id("LinkToCourseMath")).click()
    modeShort.click()
    check(driver.findElement(By.id("StudentItemSheldon Cooper")).text == "S. Cooper")


    /****/
    Thread.sleep(1_000)
    driver.findElement(By.id("LinkToStudentSheldon Cooper")).click()

    Thread.sleep(1_000)
    driver.findElement(By.id("DetailsDeleteCourses")).click()
    driver.findElement(By.id("MathCheckBox0")).click()
    Thread.sleep(1_000)
    driver.findElement(By.id("PhysCheckBox1")).click()
    Thread.sleep(1_000)
    driver.findElement(By.id("ButtonDeleteCourses")).click()

}