package microservices.book.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.service.MultiplicationService;

/**
 * 곱셈 애플리케이션의 REST API 를 구현한 클래스
 */
@RestController
@RequestMapping("/multiplications")
final class MultiplicationController {

  private final MultiplicationService multiplicationService;

  @Autowired
  public MultiplicationController(final MultiplicationService multiplicationService) {
    this.multiplicationService = multiplicationService;
  }

  @CrossOrigin
  @GetMapping("/random")
  Multiplication getRandomMultiplication() {
    return multiplicationService.createRandomMultiplication();
  }
  
  @CrossOrigin
  @GetMapping("/randomeasy")
  Multiplication getEasyRanmdomMultiplication() {
	  return multiplicationService.createEasyRandomMultiplication();
  }

}
