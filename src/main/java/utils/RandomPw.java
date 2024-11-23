package utils;

import java.util.Random;

public class RandomPw {

	public String pw() {
		Random random = new Random();
		int randompw = 100000 + random.nextInt(900000);
		
		return String.valueOf(randompw);
	}
}
