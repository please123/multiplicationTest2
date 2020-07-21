package microservices.book.multiplication.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
final class RandomGeneratorServiceImpl implements RandomGeneratorService {

  static int MINIMUM_FACTOR = 0;
  static int MAXIMUM_FACTOR = 0 ;
  
  @Override
  public int generateRandomFactor() {
	  
	  MINIMUM_FACTOR = 11;
	  MAXIMUM_FACTOR = 99;
    return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
  }

	@Override
	public int esayGenerateRandomFactor() {
		MINIMUM_FACTOR = 1;
		MAXIMUM_FACTOR = 9;
	    return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
	}
  
  
}
