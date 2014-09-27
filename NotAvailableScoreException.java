
@SuppressWarnings("serial")
public class NotAvailableScoreException extends Exception {
	
	public NotAvailableScoreException() {
		System.out.println("<<한 프레임에서 얻을 수 있는 점수는 0 ~ 10점입니다!>>");
	}
}
