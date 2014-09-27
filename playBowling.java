import java.util.Scanner;

public class playBowling {

	public static void main(String[] args) {
		Game bowlingGame = new Game();
		
		System.out.println("볼링을 하는 사람 수를 입력해주세요 : ");
		Scanner howManyPlayers = new Scanner(System.in);
		int playerNum = howManyPlayers.nextInt();
		
		//사람 수만큼 스코어보드 만들기.
		for(int i=0; i < playerNum ; i++){
			bowlingGame.addScoreBoard();
		}
		
		//각 프레임마다 플레이어들이 순서대로 공을 던진다. 
		for(int frameId=0; frameId<10; frameId++){
			for(int playerId = 0 ; playerId < playerNum ; playerId++){
				
				try{
					bowlingGame.playFrame(playerId, frameId);
				}catch (NotAvailableScoreException e) {
					playerId--;
					continue;
				}
				
				//10번째 프레임에 대한 별도의 추가처리를 한다. 
				if(frameId == 9){
					bowlingGame.checkTenthFrame(playerId);
				}
				bowlingGame.calculateScore(playerId, frameId);
				bowlingGame.printScoreBoard(playerNum, frameId);
			}
		}
		//게임이 끝난 뒤에 최종 스코어보드를 보여준다.
		System.out.println("<<최종 스코어>>");
		bowlingGame.printFinalScore(playerNum);
	}

}
