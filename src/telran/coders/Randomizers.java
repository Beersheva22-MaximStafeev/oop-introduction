package telran.coders;

public class Randomizers {
	public static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (1+ max - min));
	}
}
