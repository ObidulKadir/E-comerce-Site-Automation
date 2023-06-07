
import org.openqa.selenium.By;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class Home {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "D://personal//Web Driver//Chrome//chromedriver_win32/chromedriver.exe");
//		System.setProperty("webdriver.edge.driver", "D://personal//Web Driver//edge//edgedriver_win64/msedgedriver.exe");

		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		
		 // Navigate to the website
		driver.get("https://automationexercise.com/");
		

		
		WebElement card = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[1]/div[1]"));
		Thread.sleep(2000);
		
		//Instantiating Actions class
		Actions actions = new Actions(driver);
		
		//Hovering on main menu
		actions.moveToElement(card);
		
		// Locating the element from Sub Menu
		WebElement subMenu = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/a"));
		//To mouseover on sub menu
		actions.moveToElement(subMenu);

		//compile all the actions into a single step 
		actions.click().build().perform();
		
		// add to cart
		driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[1]/div[2]/div/a")).click();
		
		Thread.sleep(5000);
		
		// click on the view cart from the modal.
		driver.findElement(By.xpath("//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a")).click();
		
		// proceed to Checkout
		driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
		
		Thread.sleep(1000);
		
		// register /login from checkout modal
		driver.findElement(By.xpath("//*[@id=\"checkoutModal\"]/div/div/div[2]/p[2]/a")).click();
		
		// New User signup! form
		
		// SignUp Name.
		String userName = generateRandomName();
		driver.findElement(By.xpath("//input[@data-qa=\"signup-name\"]")).sendKeys(userName);
		
		// signUp email address
		String randomEmail = randomEmailGenarator(userName);
		driver.findElement(By.xpath("//input[@data-qa=\"signup-email\"]")).sendKeys(randomEmail);
		
		
		driver.findElement(By.xpath("//button[@data-qa=\"signup-button\"]")).click();
		
		// Account Information form.
		
		driver.findElement(By.xpath("//*[@id=\"uniform-id_gender1\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Abc@2023");
		
		Select selectDate = new Select (driver.findElement(By.xpath("//*[@id=\"days\"]")));
		selectDate.selectByVisibleText("15");
		
		Select selectMonth = new Select (driver.findElement(By.xpath("//*[@id=\"months\"]")));
		selectMonth.selectByVisibleText("April");
		
		Select selectYear = new Select (driver.findElement(By.xpath("//*[@id=\"years\"]")));
		selectYear.selectByVisibleText("2021");
		
		driver.findElement(By.xpath("//*[@id=\"newsletter\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"optin\"]")).click();
		
		
		driver.findElement(By.xpath("//*[@id=\"first_name\"]")).sendKeys(userName);
		
		String lName = generateRandomLastName();
		driver.findElement(By.xpath("//*[@id=\"last_name\"]")).sendKeys(lName);
		driver.findElement(By.id("company")).sendKeys("Product Company");
		driver.findElement(By.name("address1")).sendKeys("Gio street,P.O. Box 756, Product Company");
		driver.findElement(By.id("state")).sendKeys("Delhi");
	    driver.findElement(By.id("city")).sendKeys("ChokBazar");
	    driver.findElement(By.id("zipcode")).sendKeys("12345");
	    driver.findElement(By.id("mobile_number")).sendKeys("987123366589");
		driver.findElement(By.xpath("//button[@data-qa=\"create-account\"]")).click();
		
//		Thread.sleep(1000);
		//Verify account created text!!!
		String verifyText = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).getText();
		if(verifyText.equals("ACCOUNT CREATED!")) {
			System.out.println("The "+verifyText + " sucessfully");
		}
		
		
		// continue button.
		driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
		
		
		// logged user verified
//		Thread.sleep(3000);
		WebElement logUser = driver.findElement(By.xpath("//*[@id='header']/div/div/div/div[2]/div/ul/li[10]/a/b"));
		logUser.getText();
		
//		System.out.println("logged user: "+logUser.getText().getClass().getSimpleName());
//		System.out.println("username :"+userName.getClass().getSimpleName());
		
		if (userName.equals(logUser.getText())) {
			System.out.println("Loged user is verified.,The name is : "+logUser.getText());
		}
		else {
			System.out.println("The user does not exists.");
		}
		
		//cart
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
		
		// proceed to checkout
		driver.findElement(By.xpath("//*[@id=\"do_action\"]/div[1]/div/div/a")).click();
	

        // Verify Address Details and Review Your Order
        WebElement deliveryAddress = driver.findElement(By.xpath("//*[@id=\"address_delivery\"]"));

        // Find all list items within the unordered list
        List<WebElement> liElements = deliveryAddress.findElements(By.tagName("li"));

        // Iterate through the list items and print their text
        String deliveryAddressvalue = null;
        for (WebElement liElement : liElements) {
            deliveryAddressvalue = liElement.getText();
            System.out.println(deliveryAddressvalue);
        }

        WebElement ulElement = driver.findElement(By.xpath("//*[@id=\"address_invoice\"]"));

        // Find all list items within the unordered list
        List<WebElement> liElementslist = ulElement.findElements(By.tagName("li"));
//        String randomName = generateRandomName();
        String randomCardNumber = generateCardNumber();
        String randomCVC = generateRandomCVC();
        String randomExpiration = generateRandomMonth();
        String randomExpirationYear = generateRandomYear();

//        System.out.println("Card Name: " + randomName);
//        System.out.println("Card Number: " + randomCardNumber);
//        System.out.println("CVC: " + randomCVC);
//        System.out.println("Expiration Month: " + randomExpiration);
//        System.out.println("Expiration Year: " + randomExpiration);

        // Iterate through the list items and print their text
        System.out.println(" ");

        String addressInvoice = null;
        for (WebElement liElement : liElementslist) {
            addressInvoice = liElement.getText();
            System.out.println(addressInvoice);
        }
        System.out.println("Congratulations!!, The address are matched");
        driver.findElement(By.xpath("//*[@id=\"ordermsg\"]/textarea")).sendKeys(" Please send me the product ASAP, Thanks");
        driver.findElement(By.className("check_out")).click();
        
        // Payment
        Thread.sleep(1000);
        driver.findElement(By.name("name_on_card")).sendKeys(userName +" "+lName);
        driver.findElement(By.name("card_number")).sendKeys(randomCardNumber);
        driver.findElement(By.name("cvc")).sendKeys(randomCVC);
        driver.findElement(By.name("expiry_month")).sendKeys(randomExpiration);
        driver.findElement(By.name("expiry_year")).sendKeys(randomExpirationYear);
        
        WebElement submitBtn = driver.findElement(By.id("submit"));
		submitBtn.click();
		
//		Thread.sleep(3000);
//		WebElement text = driver.findElement(By.cssSelector("div[id='success_message'] div[class='alert-success alert']"));
//		String ball = text.getText();
//        System.out.println("Information:"+text.getText());
		
   
        Thread.sleep(1000);
	
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();	}
	
	public static String generateRandomName() {
        String[] userNameList = {"Johnson", "Brown", "Taylor", "Wilson", "Davis", "Michael", "James", "Sophia", "Mark", "Sundor", "Gold"};

        Random random = new Random();
        String userName = userNameList[random.nextInt(userNameList.length)];
        return userName;
    }
	
	public static String generateRandomFirstName() {
        String[] userNameList = {"Taylor", "Wilson", "Davis", "Michael", "James", "Sophia", "Mark", "Sundor", "Gold"};

        Random random = new Random();
        String fName = userNameList[random.nextInt(userNameList.length)];
        return fName;
    }
	
	public static String generateRandomLastName() {
        String[] userNameList = {"Tylon","AB","Lan","Yan","Cameron","white","jickson","Muller"};
        
        Random random = new Random();
        String lName = userNameList[random.nextInt(userNameList.length)];
        return lName;
    }

	public static String randomEmailGenarator(String userName) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator. nextInt(1000);
		
		return userName + randomInt + "@gmail.com";
	}
	
    public static String generateCardNumber() {
        Random random = new Random();
        long cardNumber = 3000000000000000L + random.nextInt(900000000);
        return String.valueOf(cardNumber);
    }

    public static String generateRandomCVC() {
        Random random = new Random();
        int cvc = 100 + random.nextInt(900);
        return String.valueOf(cvc);
    }

    public static String generateRandomMonth() {
        Random random = new Random();
        int month = 1 + random.nextInt(12);
        return String.valueOf(month);
    }
    public  static String generateRandomYear(){
        Random random = new Random();
        int year = 2023 + random.nextInt(5);
        return String.valueOf(year);
    }
	
}



