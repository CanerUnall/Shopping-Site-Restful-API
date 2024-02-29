package com.project;

import com.project.domain.concrets.user.User;
import com.project.domain.concrets.user.UserRole;
import com.project.domain.enums.Gender;
import com.project.domain.enums.RoleType;
import com.project.payload.request.user.UserRequest;
import com.project.service.user.UserRoleService;
import com.project.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@SpringBootApplication
public class ShoppingSiteApplication implements CommandLineRunner{

	static {
	//burada uygulama calistirdigimiz zaman run methodu calismandan once hazirlanmasi gereken kodlari vs yazabiliriz.
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSiteApplication.class, args);
	}
	private final UserRoleService userRoleService;
	private final UserService userService;


	@Override
	public void run(String... args) throws Exception {

		List<UserRole> allUserRole = userRoleService.getAllUserRole();
		if (allUserRole.isEmpty()) {

			UserRole admin = new UserRole();
			admin.setRoleType(RoleType.ADMIN);
			admin.setRoleName("Admin");
			userRoleService.saveUserRole(admin);

			UserRole manager = new UserRole();
			manager.setRoleType(RoleType.MANAGER);
			manager.setRoleName("Manager");
			userRoleService.saveUserRole(manager);

			UserRole assistantManager = new UserRole();
			assistantManager.setRoleType(RoleType.ASSISTANT_MANAGER);
			assistantManager.setRoleName("Assistant Manager");
			userRoleService.saveUserRole(assistantManager);

			UserRole customer = new UserRole();
			customer.setRoleType(RoleType.CUSTOMER);
			customer.setRoleName("Customer");
			userRoleService.saveUserRole(customer);

			UserRole seller = new UserRole();
			seller.setRoleType(RoleType.SELLER);
			seller.setRoleName("Seller");
			userRoleService.saveUserRole(seller);
		}

		List<User> allAdmins = userService.getAllAdmins();
		if (allAdmins.isEmpty()){
			UserRequest userRequest = UserRequest.builder().userName("Admin").name("Caner").surname("Unal").email("caner@ornekmail.com").ssn("333-22-4444")
					.password("0123456789").phoneNumber("333-444-5555").gender(Gender.MALE).birthDay(LocalDate.of(1990,5,10)).
					birthPlace("Turkey").built_in(true).build();

			userService.saveUser(userRequest,"ADMIN");
		}
	}
}
