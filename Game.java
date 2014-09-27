import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	ArrayList<ScoreBoard> boards = new ArrayList<ScoreBoard>();
	
	public void addScoreBoard() {
		boards.add(new ScoreBoard());
	}

	public void playFrame(int playerId, int frameId) throws NotAvailableScoreException {
		ScoreBoard board = boards.get(playerId);
		Frame currentFrame = board.frames.get(frameId);
		
		System.out.println((playerId+1) + "번째 플레이어의 "+(frameId+1) + "번째 프레임 1번째 공 : ");
		Scanner input = new Scanner(System.in);
		currentFrame.first = input.nextInt();
		exceptionCheck(currentFrame);

		//스트라이크일 경우.
		if(currentFrame.first == 10){
			System.out.println("스트라이크입니다!!");
			currentFrame.isStrike = true;
			return;
		}
		
		System.out.println((playerId+1) + "번째 플레이어의 "+(frameId+1) + "번째 프레임 2번째 공 : ");
		currentFrame.second = input.nextInt();
		exceptionCheck(currentFrame);
		
		//스페어일 경우.
		if(currentFrame.first + currentFrame.second == 10){
			currentFrame.isSpair = true;
		}
	}

	private void exceptionCheck(Frame frame) throws NotAvailableScoreException {
		//입력받은 값이 음수이거나 10보다 크면 exception.
		if(frame.first<0 || frame.first>10){
			throw new NotAvailableScoreException();
		}		
		//점수의 합이 10보다 크면 Exception.
		if(frame.first + frame.second >10){
			throw new NotAvailableScoreException();
		}
	}

	public void calculateScore(int playerId, int frameId) {
		ScoreBoard board = boards.get(playerId);
		board.calculateScore();
	}
	
	public void printScoreBoard(int playerNum, int frameId) {
		System.out.println("|  1st	|  2nd	|  3rd	|  4th	|  5th	|  6th	|  7th	|  8th	|  9th	|  10th	|");
		System.out.println("----------------------------------------------------------------------------------");
		
		for(int playerId=0; playerId<playerNum ; playerId++){
			ScoreBoard board = boards.get(playerId);
			board.printScoreBoard(frameId);
			System.out.println("----------------------------------------------------------------------------------");
		}
	}

	public void checkTenthFrame(int playerId) {
		ScoreBoard board = boards.get(playerId);
		Frame tenth = board.frames.get(9);
		Frame nineth = board.frames.get(8);
		
		if(tenth.isStrike){
			int firstBonus = tenthIsStrike(tenth);
			if(nineth.isStrike){
				nineth.bonus += firstBonus;
			}
		}
		if(tenth.isSpair){
			tenthIsSpair(tenth);
		}
	}
	
	private int tenthIsStrike(Frame tenth){
		Scanner input = new Scanner(System.in);
		System.out.println("1st 보너스 공 : ");
		int firstBonus = input.nextInt();
		System.out.println("2nd 보너스 공 : ");
		int secondBonus = input.nextInt();
		tenth.bonus = firstBonus + secondBonus;
		return firstBonus;
	}
	
	private void tenthIsSpair(Frame tenth){
		Scanner input = new Scanner(System.in);
		System.out.println("보너스 공 : ");
		tenth.bonus += input.nextInt();
	}

	public void printFinalScore(int playerNum) {
		System.out.println("|  1st	|  2nd	|  3rd	|  4th	|  5th	|  6th	|  7th	|  8th	|  9th	|  10th	|");
		System.out.println("----------------------------------------------------------------------------------");
		
		for(int playerId=0; playerId<playerNum ; playerId++){
			ScoreBoard board = boards.get(playerId);
			System.out.print("  ");
			board.printFirstSecondScore(9);
			System.out.println("");
			System.out.print("  ");
			board.printFrameTotalScore(9);
			System.out.println("");
			System.out.println("----------------------------------------------------------------------------------");
		}		
	}

}
