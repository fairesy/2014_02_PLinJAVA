import java.util.ArrayList;


public class ScoreBoard {
	ArrayList<Frame> frames = new ArrayList<Frame>();
	
	ScoreBoard(){
		for(int i=0; i<10; i++){
		frames.add(new Frame());
		}
	}
	
	void calculateScore() {
		
		calculateBonusScore();
		
		for(int frameId = 0 ; frameId < 10 ; frameId++){
			Frame currentFrame = frames.get(frameId);
			if(frameId == 0){
				currentFrame.totalUntilNow = currentFrame.first + currentFrame.second + currentFrame.bonus;
				continue;
			}
			Frame previousFrame = frames.get(frameId-1);
			currentFrame.totalUntilNow = previousFrame.totalUntilNow + currentFrame.first + currentFrame.second + currentFrame.bonus;
		}
	}
	
	private void calculateBonusScore() {
		for(int frameId = 1 ; frameId < 10 ; frameId++){
			Frame previousFrame = frames.get(frameId-1);
			Frame currentFrame = frames.get(frameId);
			
			if(previousFrame.isSpair){
				previousFrame.bonus = currentFrame.first;
			}
			
			if(previousFrame.isStrike){
				if(frameId > 1 && frameId<9 && frames.get(frameId-2).isStrike){
					frames.get(frameId-2).bonus = previousFrame.first + currentFrame.first;
				}
				
				if(frameId == 9 && frames.get(frameId-2).isStrike && currentFrame.isStrike){
					previousFrame.bonus += 20;
					return;
				}
				
				previousFrame.bonus = currentFrame.first + currentFrame.second;
			}
		}
	}

	void printScoreBoard(int currentFrameId) {
		//각 프레임의 첫번째, 두번째 점수를 프린트한다.
		System.out.print("  ");
		printFirstSecondScore(currentFrameId);
		System.out.println("");
		System.out.print("  ");
		
		//각 프레임의 점수 합을 프린트한다.
		if(frames.get(currentFrameId).isSpair){
			printFrameTotalScore(currentFrameId-1);
			System.out.println("");
			return;
		}
		if(frames.get(currentFrameId).isStrike){
			if(currentFrameId>0 && currentFrameId<8 && frames.get(currentFrameId-1).isStrike){
				printFrameTotalScore(currentFrameId-2);
			}
			else if(currentFrameId>=8 && frames.get(currentFrameId-1).isStrike){
				printFrameTotalScore(currentFrameId-1);
			}
			else{
				printFrameTotalScore(currentFrameId-1);
			}
			System.out.println("");
			return;
		}
		printFrameTotalScore(currentFrameId);
		System.out.println("");
	}
	
	void printFirstSecondScore(int currentFrameId){
		for(int i = 0 ; i < 10 ; i++){
			Frame frame = frames.get(i);
			
			if(frame.isStrike){
				System.out.print("   X    ");
			}
			else if(frame.isSpair){
				if(frame.first==0){
					System.out.print(" -  /   ");
				}
				System.out.print(" " + frame.first + "  /   ");
			}
			else{
				if(frame.first==0 && frame.second!=0){
					System.out.print(" -  " + frame.second + "   ");
				}
				else if(frame.second==0 && frame.first!=0){
					System.out.print(" " + frame.first + "  -   ");
				}
				else if(frame.first==0 && frame.second==0){
					System.out.print(" -  -   ");
				}
				else{
					System.out.print(" " + frame.first + "  " + frame.second + "   ");
				}
			}
		}
	}
	
	void printFrameTotalScore(int currentFrameId){
		
		for(int i=0 ; i<=currentFrameId ; i++){
			Frame frame = frames.get(i);
			System.out.print("   "+frame.totalUntilNow+ "	 ");
		}
	}

}
