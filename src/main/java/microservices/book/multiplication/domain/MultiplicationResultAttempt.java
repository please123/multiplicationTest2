package microservices.book.multiplication.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class MultiplicationResultAttempt {

/*	
	private final User user;
	private final Multiplication multiplication;
	private final int resultAttempt;

  // JSON (역)직렬화를 위한 빈 생성자
  MultiplicationResultAttempt() {
    this.user = null;
    this.multiplication = null;
    this.resultAttempt = -1;
    
  }
*/
	@Id
	@GeneratedValue
	private Long id;	
	
	private final String userName;
	private final int factorA;
	private final int factorB;
	private final int resultAttempt;
	
	private final boolean correct;
	
	MultiplicationResultAttempt(){
		userName = null;
		factorA = -1;
		factorB = -1;
		resultAttempt = -1;
		correct = false;
	}
}
