package microservices.book.multiplication.event;
import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class SolvedEvent implements Serializable {
	
	private final String userName;
	private final boolean correct;

}
