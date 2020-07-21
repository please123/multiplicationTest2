package microservices.book.multiplication.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.event.SolvedEvent;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;

@Service
class MultiplicationServiceImpl implements MultiplicationService {
	
  private final Logger logger = LoggerFactory.getLogger(MultiplicationServiceImpl.class);

  private RandomGeneratorService randomGeneratorService;
  private MultiplicationResultAttemptRepository attemptRepository;
  private EventDispatcher eventDispatcher;

  @Autowired
  public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService, 
		  final MultiplicationResultAttemptRepository attemptRepository, final EventDispatcher eventDispatcher) {
    this.randomGeneratorService = randomGeneratorService;
    this.attemptRepository = attemptRepository;
    this.eventDispatcher = eventDispatcher;
  }

  @Override
  public Multiplication createRandomMultiplication() {
    int factorA = randomGeneratorService.generateRandomFactor();
    int factorB = randomGeneratorService.generateRandomFactor();
    return new Multiplication(factorA, factorB);
  }
  
  
  @Override
  public Multiplication createEasyRandomMultiplication() {
	int factorA = randomGeneratorService.esayGenerateRandomFactor();
	int factorB = randomGeneratorService.esayGenerateRandomFactor();
	return new Multiplication(factorA, factorB);	  
  }

/*  
  @Override
  public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
    return resultAttempt.getResultAttempt() ==
            resultAttempt.getMultiplication().getFactorA() *
            resultAttempt.getMultiplication().getFactorB();
  }
 */
 
 /* 
  @Override
  public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
	    return resultAttempt.getResultAttempt() ==
	            resultAttempt.getFactorA() *
	            resultAttempt.getFactorB();
  }
  */
  
  @Transactional           //트랜잭션  선언 (class가 final이면 안됨)
  @Override
  public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
	  
	  boolean isCorrect = resultAttempt.getResultAttempt() == 
			  resultAttempt.getFactorA() *
			  resultAttempt.getFactorB() ;
	  
	  //사용자가 답변한 결과를 객체에 저장
	  MultiplicationResultAttempt chreckedResultAttempt = new MultiplicationResultAttempt(
			  resultAttempt.getUserName(),
			  resultAttempt.getFactorA(),
			  resultAttempt.getFactorB(),
			  resultAttempt.getResultAttempt(),
			  isCorrect);
	  logger.info("chreckedResultAttempt 생성");
	  
	  //객체를 db에 저장
	  attemptRepository.save(chreckedResultAttempt);
	  logger.info("DB저장");
	  
	  //userName, isCorrect를 메시지로 전송
	  eventDispatcher.send(new SolvedEvent(chreckedResultAttempt.getUserName(),
			  chreckedResultAttempt.isCorrect()));
	  
	  return isCorrect;
  }
  
}
