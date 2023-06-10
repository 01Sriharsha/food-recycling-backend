package com.frms.foodrecycling;

import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.repository.DonationRepository;
import com.frms.foodrecycling.repository.MemberRepository;
import com.frms.foodrecycling.repository.RequestFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.UUID;

@SpringBootApplication
public class FoodRecyclingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FoodRecyclingApplication.class, args);
	}

	@Bean
	@Primary
	public WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("*")
						.allowedHeaders("*")
						.allowedMethods("*")
						.allowedOriginPatterns("http://localhost:3000");
			}
		};
	}

	@Autowired
	private RequestFoodRepository requestFoodRepository;

	@Autowired
	private MemberRepository memberRepository;


	@Override
	public void run(String... args) throws Exception {
//		Member member = memberRepository.findById(3)
//				.orElseThrow(() -> new RuntimeException("Member Not Found"));
//		System.out.println(requestFoodRepository.getExistingStatus(member));
		System.out.println(UUID.randomUUID());
	}
}