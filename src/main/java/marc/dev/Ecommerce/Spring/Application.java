package marc.dev.Ecommerce.Spring;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.http.HttpServletRequest;
import marc.dev.Ecommerce.Spring.utils.VerifyToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public FilterRegistrationBean<VerifyToken> filterRegistrationBean(){
		FilterRegistrationBean<VerifyToken> registrationBean = new FilterRegistrationBean<>();
		VerifyToken verifyToken = new VerifyToken();
		registrationBean.setFilter(verifyToken);
		registrationBean.addUrlPatterns("/api/v1/cart");
		registrationBean.addUrlPatterns("/api/v1/order");

		return registrationBean;
	}
//	@GetMapping("/api/v1/cart")
//	public String apiroot(HttpServletRequest request){
//		String userId = (String) request.getAttribute("userId");
//		System.out.println(userId);
//		return "hello world";
//	}
}
