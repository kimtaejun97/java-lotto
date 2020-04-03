## 기능 요구사항
- 빈 문자열 또는 null 값을 입력 할 경우 0을 반환
- 숫자 하나를 문자열로 입력 할 경우 해당 숫자를 반환
-> 1 반환
- 쉼표(,) 또는 콜론(:) 구분자로 가지는 문자열을 전달 하는 경우 구분자를 기준으로 분리

- 분리한 숫자의 합을 반환 
  - ex)  "" => 0
  - ex) "1,2" =>3
  - ex) "1,2,3" => 6
  - ex) “1,2:3” => 6
  
- "//" 와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용
  - ex) “//;\n1;2;3”   구분자는 세미콜론(;)
      - 결과 값은 6

- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw 한다
 - 음수를 전달할 경우

## 프로그래밍 요구사항
- indent(들여쓰기) depth를 2단계에서 1단계로 줄여라.
  - depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. 
  - if문 안에 while문을 사용한다면 depth가 2단계가 된다.
  
- 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
  - method가 한 가지 일만 하도록 최대한 작게 만들어라.
  
- else를 사용하지 마라.