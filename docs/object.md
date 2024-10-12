> 도메인: 사용자가 프로그램을 사용하여 해결하고 싶은 문제들의 집합
> - 소프트웨어로 구현할 요구사항의 범위

## 객체지향 설계 순서

### 1. 다른 객체의 요청을 처리하기 위한 행도을 먼저 결정하고 행동에 적합한 객체를 나중에 선택한다.

calculateDiscount(할인 금액을 계산하라) 요청이 먼저 존재하고 이러한 요청을 처리할 적합한 객쳬(DiscountPolicy)를 나중에 선택해야한다.
- 이러한 방식의 설계를 통해 변경에 유연하고 팔요한 데이터만 포함하는 클래스를 얻을 수 있다.


### 2. 데이터를 보다 쉽게 변경하기 위해서는 외부 객체가 데이터가 아닌 행동에 의존하도록 만들어야 한다.

**as-is**

이전에는 DiscountPolicy의 데이터(amount, percent, ...) 에 getter를 통해 직접 접근하는 모습

```java
public class ReservationService {
    ...

    private Money calculateDiscount(DiscountPolicy policy, Movie movie) {
        if (policy.isAmountPolicy()) {
            return policy.getAmount();
        } else if (policy.isPercentPolicy()) {
            return movie.getFee().times(policy.getPercent());
        }

        return Money.ZERO;
    }
}
```

**to-be**

```java
public class ReservationService {
    ...
    public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
        ...
        if (found) {
            fee = movie.getFee().minus(policy.calculateDiscount(movie));
        } else {
            fee = movie.getFee();
        }
        ...
    }
}
public class DiscountPolicy {
    ...
    public Money calculateDiscount(Movie movie) {
        if (isAmountPolicy()) {
            return amount;
        } else if (isPercentPolicy()) {
            return movie.getFee().times(percent);
        }

        return Money.ZERO;
    }
}

```

외부 객체가 데이터에 의존하지 않도록 만드는 가장 좋은 방법은 데이터를 결정하기전에 행동을 먼저 결정하는것이다.
- 객체지향설계에서는 객체의 데이터에 관해서 고민하지 않고 외부에 어떤 행동(ex. 할인 금액 계산)을 제공할 지 먼저 결정한다.
- 이렇게 행동을 구현하는데 필요한 데이터를 객체의 데이터에 추가한다.

위와 같이 객체들의 협력관계를 기반으로 애플리케이션 설계하는 방법을 **책임 주도 설계**라고 부른다.

## 책임 주도 설계

객체지향에서는 협력에 참여하기 위해 객체가 수행하는 행동을 **책임**이라고 부른다.
- 할인 요금을 계산하라. -> DiscountPolicy의 책임

객체가 외부에 제공해야하는 책임에는 2가지 종류가 존재한다.
- 하는것(Doing)
  - 객체를 생성하거나 계산하는 등의 스스로 하는 것
  - 다른 객체(협력자)의 행동을 시작하는 것
  - 다른 객체(협력자)의 활동을 제어하고 조절하는 것
- 아는것(Knowing)
  - private로 캡슐된 상태(데이터)에 관해 아는 것
  - 관련된 객체(협력자)에 관하여 아는 것
  - 자신이 유도하거나 계산할 수 있는 것에 관하여 아는 것

여기서 아는것과 관련된 책임을 내부의 데이터를 저장하는 개념과는 다르다.
- 책임은 행동 관점이기 떄문에 어떤 것을 아는 책임을 할당받았다는 것은 해당 데이터를 저장해야하는것이 아닌 정보에 대해 대답할 수 있어야한다는 것이다.

**책임 주도 설계에서 가장 어려운 부분은 책임을 할당할 적합한 객체를 선택하는 것이다.**

## 협력을 설계하는데 필요한 문맥은 어디서?

**객체지향에서는 시스템이 외부에 제공해야하는 기능을 협력을 위한 문맥으로 사용한다.**
- 영화 예매 시스템이라면 **영화 예매 기능**이 문맥이 될 수 있다.
- 영화 예매 시스템을 객체지향적으로 설계한다는 것은 영화 예매 기능을 객체들 사이의 협력으로 구현하고, 협력에 필요한 행동을 수행하는데 필요한 객체들을 선택함을 의미한다.


## 도메인 모델

도메인에 중요한 개념과 관계의 집합을 의미한다.
- 객체지향 설계의 재료로 사용되는 도메인의 추상화

![image](https://github.com/user-attachments/assets/6402e139-354d-4b1f-adaf-3403ff690e37)

이러한 도메인 모델은 책임을 할당할 후보 객체를 찾을 때 가장 먼저 참고하는 개념과 관계의 집합이 된다.

## GRASP 

### Information Expert

애플리케이션의 기능이 객체협력을 설계하는데 필요한 문맥을 제공한다.

`상영을 예매하라` 라는 기능 예시
- 기능이 실행된 이후 결과를 결정
  - 사용자가 새롭게 예매 결과 정보
- 기능이 실행된 이후 변경된 도메인의 개념의 상태나 구조 결정
  - 상영을 예매하는데 성공하면 시스템 내에 새로운 예매를 생성
  - 이렇게 생성된 예매를 상영과 연결

![image](https://github.com/user-attachments/assets/c7bba358-7b99-4095-be19-932ae512f3db)

애플리케이션의 기능을 시스템의 책임으로 간주하고 그리고 이러한 시스템의 책임을 객체의 책임으로 변환한다.
기능을 시작하는 책임을 어떤 객체에게 할당할지 결정하는 문제로 전환할 수 있다.

즉, `상영을 예매한다` 라는 기능은 새로운 예매를 반환하는 결과를 가져오는데, 이러한 기능에 대한 책임을 할당받은 객체는 `상영을 예매한다` 라는 기능을 가지며 결과로 새롭게 새로운 예매를 반환해야한다.
- 어떤 객체에게 이를 할당해야할까?

책임을 할당할때 GRASP 패턴을 활용해볼 수 있으며 가장 기본이 되는 패턴은 정보 전문가 패턴이다.

**정보 전문가 패턴**
- 문제: 책임을 객체에게 할당하는 일반적인 원칙은 무엇인가?
- 해결방법: 책임을 수행하는데 필요한 정보를 가장 많이 알고 있는 객체에게 할당하라

단 여기서 말하는 정보는 데이터가 아닌 행동을 의미한다. 즉, 정보 전문가란 어떤 정보가 필요할때 이 정보에 대한 질문에 답을 해줄 수 있는 객체를 의미한다.

예를 들어, `저 학생은 몇 살인가요?`라는 질문에 대해 가장 많은 저오를 알고 있는 학생이 대답하는 것이 자연스럽다.
이러한 방법으로 이와 같은 책임을 할당할 객체를 학생으로 할당할 수 있다.

이렇게 책임을 할당할 객체를 결정했다면 학생 클래스 안에 행동(메서드)을 구현하면 된다.
정보를 책임질 객체는 결정했지만 이 단계까지도 아직 데이터는 결정하지 않았다. 이러한 책임을 원할하게만 수행할 수 있다면 데이터는 어떤 방식으로 구현해도 무방하다.

```kotlin
class Student(
    private val age: Int
) {
    fun answerAge() = age
}
```

```kotlin
class Student(
    private val birthdate: LocalDate,
) {
    fun answerAge(): Int {
        return LocalDate.now().year - birthdate.year
    }
}
```

위와 같이 정보 전문가가 정보를 직접 저장하는게 아닌 책임을 실행하는 도중에 계산, 생성하여 반환할 수도 있다.
혹은 아래와 같이 나이와 관련된 데이터를 전혀 포함하지 않는 구현방식도 고민해볼 수 있다.

```kotlin
class Student(
    private val schoolRecord: SchoolRecord,
) {
    fun answerAge(): Int {
        return schoolRecord.search(this).age
    }
}
```

여기서 위 3개의 Student 클래스는 인터페이스는 동일하지만 구현은 다르다. 

나이를 제공하는 책임만 동일하다면 내부를 구현하는 방식은 자유롭게 선택가능하다는 것을 보여준다.
이는 다시 말해 객체의 책임만 동일하게 유지한다면 내부 구현 방식을 변경하더라도 외부에 영향을 끼치지 않음을 의미힌다.

행동을 먼저 결정해서 외부 객체가 인터페이스에 의존하도록 만들면 객체 내부 구현이 변경되더라도 외부의 영향을 미치지 않을 수 있다.

다시 돌아와서 `예매를 생성하라` 라는 책임을 어떤 객체에게 할당하는것이 좋을까? (공통된 멘탈 모델 기반 - 여러 사람이 동의할 수 있어야함)

![image](https://github.com/user-attachments/assets/4280cc10-72de-4891-ab79-f801e09cfa17)

예매 객체에는 제목, 상영 시간, 인원, 정가, 결제 금액이 포함되어 있다.
이러한 예매를 생성하기 위한 재료를 가장 많이 가지고 있는 정보 전문가에게 책임을 할당하면 된다. 
- 영화? 제목, 정가 정보를 대답해줄 수 있는 정보 전문가 
- 상영? 상영 시간 정보를 대답해줄 수 있는 정보 전문가

두 후보중에 어떤 객체에게 예매 생성 책임을 할당하는 것이 좋을까?
- 상영 시간이 핵심 정보이고 제목, 정가 정보는 상영과 연결된 영화를 통해 획득 가능하기 때문에 상영 객체에게 책임을 할당하도록 결정
- 제목, 정가 정보는 상영 객체와 연결된 영화 객체의 협력을 통해 얻을 수 있다.

애매하다면? **목적어에 책임을 할당한다.**

만약 애매하다면 현재 책임을 문장으로 작성해본다음 목적어에 책임을 할당해볼 수도 있다.

![image](https://github.com/user-attachments/assets/06a4f8f7-2429-4807-857b-3421f52c8655)

- 객체는 책임을 스스로 수행하는 자율적인 존재이므로 목적어를 주어로 변경하면 능동적으로 그 행동을 수행하도록 만들 수 있다. 

## Creator, Low Coupling

객체지향 설계를 하다보면 다른 객체를 생성하는 책임을 할당해야하는 경우가 빈번하게 발생하는데, 이럴때 사용할 수 있는 패턴이 창조자 패턴이다.

**창조자 패턴**
- 문제: 새로운 인스턴스를 생성하는 책임을 어떤 객체에게 할당할 것인가?
- 해결방법: 다음 중 한 가지라도 만족할 경우 A의 인스턴스를 생성할 책임을 B에게 할당하라
  - B가 A를 포함하거나 참조한다
  - B가 A를 기록한다
  - B가 A를 긴밀하게 사용한다
  - B가 A를 초기화하는 데 필요한 정보를 알고 있다

정보 전문가 패턴 관점에서 예매를 생성하는 책임을 상영 객체에게 할당하였는데, 이번에는 창조자 패턴 관점에서 바라본다.
- 영화?
  - 영화는 예매를 초기화하는데 필요한 정보인 제목, 정가, 할인 금액을 알고 있다. (`B가 A를 초기화하는 데 필요한 정보를 알고 있다`)
- 상영? 
  - 상영은 예매를 초기화하는데 필요한 정보인 상영 정보를 알고 있다. (`B가 A를 초기화하는 데 필요한 정보를 알고 있다`)
  - 상영은 예매를 참조하고 있다. (`B가 A를 포함하거나 참조한다`)

이러한 관점에서 상영 객체가 창조자 패턴의 요건을 더 많이 충족하고 있다.

### 결합도

> 결합도: 객체들이 서로 연관되어 있는 정도

책임을 할당할때는 전체적으로 결합도가 낮아지는 방향으로 할당해야한다.

**낮은 결합도 패턴**
- 문제: 어떻게 낮은 의존성을 유지하고, 변경에 따른 영향을 줄이면서, 재사용성을 높일 수 있을까?
- 해결방법: 설계의 전체적인 결합도를 낮게 유지할 수 있도록 책임을 할당하라

창조자 패턴의 요건은 낮은 결합도 패턴과 정보 전문가 패턴이 조합되어 있음을 알 수 있다.

![image](https://github.com/user-attachments/assets/685992ed-eff9-48f4-a5ae-02eb528e5c51)

결합도 관점에서 예매 생성 책임 확인
- 상영과 예매 사이에는 이미 관계가 존재하기 때문에 결합도에 변화가 없다. (새로운 관계를 추가할 필요가 없다.)
- 영화에 책임을 할당한다면 새로운 관계가 추가되어 결합도가 상승하게 된다.
- 즉, 전체적으로 낮은 결합도를 유지하기 위해 예매 생성 책임을 상영에 할당한다.

## High Cohesion

**높은 응집도 패턴**
- 문제: 어떻게 낮은 결합도를 유지하고, 변경에 따른 영향을 줄이면서, 재사용성을 높일 수 있을까?
- 해결방법: 높은 응집도를 유지하도록 책임을 할당하라

> 높은 응집도?
> 
> 한 요소의 책임들이 얼마나 강력하게 관련되고 집중되어 있는가를 의미힌다. 연관성이 높은 책임들을 가지면서 너무 많은 일을 하지 않는 객체에 책임을 할당 

설계를 하다보면 객체에 하나 이상의 책임을 할당하는 것이 일반적이다.
높은 응집도의 취지는 여러 책임을 할당할 경우 하나의 객체에 함께 할당할 수 있는 책임들의 특성을 제한하자는 것을 의미힌다.

객체지향에서는 유사한 책임들이 함께 모여있는 경우 응집도가 높다고 표현하며, 이질적인 책임들이 함께 모여있는 경우 응집도가 낮다고 표현한다.
연관성이 높은 책임을 하나의 객체에 할당하고 연관성이 낮은 책임을 다른 객체로 이동시키면 하나의 객체가 너무 많은 일을 하는것을 방지할 수 있다.

### 영화 가격 계산할 책임을 담당할 객체는?

상영을 예매하는 책임을 완수하기 위해서는 예매를 생성해야하고, 예매를 생성하기 위해서는 영화 가격에 대한 정보가 필요하다.
예매를 생성하는일을 담당하는 상영에 영화 가격을 계산하는 책임까지 할당하면 상영에 너무 많은 책임이 할당되며, 이질적인 책임을 할당하게 된다.
즉, 상영의 부담을 낮추기 위해서는 외부의 다른 객체에게 영화 가격을 계산하는 책임을 할당하는것이 좋다.

우선 정보 전문가 관점에서 후보를 선정한다.
- 가격 계산하기 위해 필요한 핵심 정보는 영화 정가 정보이며, 이러한 정보를 대답할 수 있는 객체는 영화이다. 

낮은 결합도 관점에서 후보를 고민한다.
- 이러한 가격을 계산하기 위해서는 할인 정보를 알고 있어야하는데, 영화는 이미 할인 정책, 할인 조건과 관계를 맺고 있기 때문에 가격을 계산하는 책임을 할당하더라도 낮은 결합도를 유지할 수 있다.

## Polymorphism

할인 금액을 계산하기 위해 영화와 협력해야하는 객체를 설계해본다.

영화에 할인 금액을 계산하는 책임을 할당하면 응집도가 낮아질 수 밖에 없다.
영화가 할인 금액을 계산하라는 요청을 전송하여 다른 객체가 이러한 요청을 처리하도록 만든다.

금액, 비율으로 할인하는데 필요한 정보를 알고 있는 할인 정책(DiscountPolicy)에 할당한다.

이러한 설계에서 2가지 협력이 존재한다.

![image](https://github.com/user-attachments/assets/92a68d4f-89c1-4ecd-9767-77eed94926f0)

여기서 문제는 영화가 서로 다른 종류의 2가지 요청을 보내야한다는 것이다.

```kotlin
class Movie {
    private val discountPolicy: DiscountPolicy
    
    fun calculateFee(...): Money {
        if (금액할인조건이라면) {
            return discountPolicy.calculateAmountDiscount(...)
        }
      
      return discountPolicy.calculatePercentDiscount(...)
    }
}
```

```kotlin
class DiscountPolicy {
    
    fun calculateAmountDiscount(...): Money { ... }
  
    fun calculatePercentDiscount(...): Money { ... }
}
```

위 코드에서 DiscountPolicy에는 낮은 응집도에 대한 문제를 가지고 있다. 
금액 할인 정책 계산 로직, 비율 할인 정책 계산 로직은 할인 정책에 한 종류라는 공통점은 존재하지만 계산하는데 필요한 데이터도 다를 뿐만 아니라 계산하는 필요한 로직도 공유하지 않는다.
즉, DiscountPolicy는 서로 상관없는 두 로직을 함께 포함하고 있기 때문에 응집도가 낮다.

이러한 경우에 새로운 중복 할인 정책이 추가된다면 DiscountPolicy 내부를 수정하게 된다.
이러한 수정이 발생하는 원인은 DiscountPolicy의 응집도가 낮기 때문이다.
또한, 이러한 낮은 응집도의 객체를 사용하는 클래스도 해당 객체의 변경에 영향을 받기 쉽다.

```kotlin
class Movie {
    private val discountPolicy: DiscountPolicy
    
    fun calculateFee(...): Money {
        if (금액할인조건이라면) {
            return discountPolicy.calculateAmountDiscount(...)
        }
        if (비율할인조건이라면) {
          discountPolicy.calculatePercentDiscount(...)
        }
      
      return discountPolicy.calculateOverlappedDiscount(...)
    }
}
```

```kotlin
class DiscountPolicy {
    
    fun calculateAmountDiscount(...): Money { ... }
  
    fun calculatePercentDiscount(...): Money { ... }

    fun calculateOverlappedDiscount(...): Money { ... }
}
```

GRASP 패턴에서는 이렇게 유사하지만 다른 방식의 행동을 추가하고 싶을때 다른 코드에 영향을 주지 않고도 쉽게 확장가능한 패턴을 제공한다.

**다형성 패턴**
- 문제: 타입을 기반으로 유사하지만 서로 다르게 행동할 때 조건문을 사용하지 않고 변하는 행동을 어떻게 처리할 것인가?
  - 금액 할인 정책과 비율 할인 정책은 서로 다르게 동작하는 서로 다른 타입
  - 할인 요금을 계산한다는 유사함을 서로 다르게 계산하고 있다.
- 해결방법: 다형적인 메시지를 이용해서 행동이 변하는 타입들에게 각 행동을 다루기 위한 책임을 할당하라
  - 할인 정책안에는 서로 다른 방식으로 동작하는 두 가지 행동이 포함되어 있다. 
  - 영화의 입장에서는 할인 정책의 행동은 시간에 따라 변하는 것처럼 보인다. (특정 시점에 금액 할인 정책 or 비율 할인 정책 하나만 필요)
  - 이렇게 클라이언트 입장에서 행동이 변하는 것처럼 보인다면 행동에 따라 타입을 분리하는것이 좋다. (즉, 유사해보이지만 서로 다르게 동작하는 책임이 하나의 후보에 뭉쳐있다면 타입을 불리)
  - 금액 할인 정책, 비율 할인 정책을 각각의 객체로 분리한다.

## 결합도 낮추기 - 변경 보호

금액 할인 정책, 비율 할인 정책을 각각의 객체로 분리까지 진행했으나 영화 객체에는 각각의 할인 정책을 모두 알고 있어야하기 때문에
새로운 할인 정책이 추가되는 경우 변경을 피할 수 없는데 이러한 문제를 **다형적인 메시지**를 통해 해결해본다.

![image](https://github.com/user-attachments/assets/6b06e82c-ce82-4550-8a38-79275c045878)

할인 금액을 계산해서 반환할 수 있다면 협력이라는 문맥 안에서 두 객체는 동일하다고 볼 수 있다.
할인 금액을 계산할 수만 있다면 계산하는 방식이나 타입이 무엇인지는 신경 쓸 필요가 없다.

영화는 협력하는 대상이 할인 금액을 반환할 수만 있다면 어떠한 객체에게 메시지를 전송할지 고민할 필요가 없어야한다.

![image](https://github.com/user-attachments/assets/b64ba6f0-540d-4c11-b74c-3c37992b71d5)

영화가 할인은 계산하라는 메시지를 전송하면 메시지를 수신한 객체는 자신의 타입에 따라 적절한 방법을 스스로 선택할 수 있다.
이렇게 동일한 메시지를 전송했을때 메시지를 수신한 객체의 타입에 따라 서로 다른 방식에 따라 동작하는 것을 다형성이라고 부른다.
또한, 이렇게 타입에 따라 다르게 동작하도록 요청을 전송하는 메시지를 다형적인 메시지라고 부른다.

![image](https://github.com/user-attachments/assets/0ec03a4d-e684-4d03-bf80-287816e91e34)

메시지를 통합한 뒤에 결합도를 낮추기 위해 할인 정책의 타입을 숨겨한다. 
이럴때 사용할 수 있는 패턴이 변경 보호 패턴이다.

**변경 보호 패턴**
- 문제: 요소들의 변화나 불안정한 요소가 다른 요소에 해로운 영향을 미치지 않도록 할 수 있을까?
- 해결방법: 변화가 예상되거나 불안정한 지점을 식별하고, 그 주위에 안정적인 인터페이스 또는 추상화를 형성하도록 책임을 할당하라

위 예제에서 변화하는 대상은 타입의 변화이다. 이를 위해 할인 정책이라는 추상화를 추가한다. 

![image](https://github.com/user-attachments/assets/5c4c7df2-018c-48d7-9f05-20696f0e5c6c)

이렇게 구체적인 타입(금액 할인 정책, 비율 할인 정책) 대체 가능한 추상화를 역할이라고 부른다.

## 객체 구현하기

상영을 예매한다는것은 내부적으로 예매 객체를 생성함을 의미한다. 
이때 예매 객체를 생성하기 위해서는 가격 정보가 필요한데, 이러한 가격 계산을 상영(Screening) 객체가 직접 가격을 계산하는것은 응집도 관점에서 좋지않다.
따라서 이를 위해 상영 객체는 영화 객체와의 협력이 필요하다.

```kotlin
fun reserve(customer: Customer, audienceCount: Int): Reservation {
    val fee = movie.calculateFee(this).times(audienceCount)
    return Reservation(customer, this, audienceCount, fee)
}
```

영화(Movie) 객체는 금액을 계산하기 위해 할인 금액을 알아야하며 이 과정에서 할인 정책(DiscountPolicy) 객체와 협력이 필요하다.

```kotlin
fun calculateFee(screening: Screening): Money {
  return fee.minus(discountPolicy.calculateDiscount(screening))
}
```

### 다형성 패턴 적용하기

영화와 할인 정책 사이의 협력을 설계할때 코드 변경이 쉽도록 하기 위해 다형성 패턴을 적용했다.
영화는 `할인을 계산하라`는 다형적인 메시지를 전송하고, 실제로 메시지를 수신하는 객체 타입이 비율 할인 정책, 금액 할인 정책인지에 따라 적절한 메서드가 실행되도록 협력을 설계하였다.

이때 변하는 할인 정책 타입을 캡슐화하기 위해 변경 보호 패턴을 적용하여 추상화인 할인 정책을 추가하였다.
이로 인해 영화는 추상화된 할인 정책만을 의존하고, 비율, 금액 할인 정책은 의존하지 않을 수 있다.

그리고 할인 정책처럼 여러 객체로 대체될 수 있는 추상화를 `역할`이라고 부른다. 
여기서 역할을 구현하는 방법은 다양하다.
- 역할이 오직 하나의 객체만을 표한한다면 구체 클래스를 통해 구현할 수 있다.
- 역할이 여러 객체에 의해 대체될 수 있고 코드를 공유할 필요가 있다면 추상 클래스를 통해 구현할 수 있다.
- 코드를 공유할 필요도 없고 결합도를 낮추기 위한 메시지 수신자의 역할만을 명시하기를 원한다면 인터페이스를 통해 구현할 수도 있다.

![image](https://github.com/user-attachments/assets/19d7316b-9501-487f-aa96-f70866ac0944)

할인 정책의 경우에 비율, 금액 할인 정책 사이에 데이터와 로직 공유가 필요하기 위해 추상 클래스를 통해 구현한다.

```kotlin
abstract class DiscountPolicy(
    val conditions: List<DiscountCondition>
) {
    protected abstract fun getDiscountAmount(screening: Screening): Money

    fun calculateDiscount(screening: Screening): Money {
        for (condition in conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening)
            }
        }
        return Money.ZERO
    }
}
```
- protected abstract fun getDiscountAmount(screening: Screening): Money
  - getDiscountAmount 메서드가 추상 메서드인데, 자식 클래스는 이를 override하여 자신만의 할인 금액으로 계산하면 된다.

```kotlin
class AmountDiscountPolicy(
    private val discountAmount: Money,
    conditions: List<DiscountCondition>
) : DiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        return discountAmount
    }
}
```

```kotlin
class PercentDiscountPolicy(
    private val percent: Double,
    conditions: List<DiscountCondition>,
) : DiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        return screening.getFixedFee().times(percent)
    }
}
```

## 메시지와 메서드의 분리

- 메시지: 객체 사이의 의사소통 수단
  - 객체가 다른 객체에게 협력을 요청하기 위해서는 메시지를 전달해야한다.
- 메서드: 메시지를 수신한 객체가 메시지를 처리하는 방법

**메시지와 메서드를 분리하는 이유**

의존성을 제어해서 내부 데이터가 변경되거나 타입이 바뀌는 경우 파생효과를 줄이기 위함이다.
변경하기 쉬운 구조를 설계하기 위해서는 메시지를 먼저 결정하고 메시지를 수신할 객체를 나중에 선택하는것이 좋다.

메시지만 단독으로 선언할 수 없고 메시지를 포함한 명확한 타입을 선언해야 한다.

```kotlin
interface DiscountPolicy {
    fun isSatisfiedBy(screening: Screening): Boolean
}
```
- isSatisfiedBy 메시지를 정의하기 위해 DiscountPolicy 라는 인터페이스를 정의
- DiscountPolicy 인터페이스를 통해 협력에 참여할 수 있는 일종의 슬롯(플러그인)을 제공한다.
- 이러한 구조에 의해 새로운 할인 조건이 추가되더라도 Client인 DiscountPolicy를 수정할 필요가 없어진다.
  - 왜냐하면 DiscountPolicy는 isSatisfiedBy라는 메시지에만 의존하기 때문이다.

## 유연하고 일관적인 협력

메시지와 메서드를 분리함으로써 Client는 동일한 메시지를 전송하더라도 메시지를 수신하는 객체에 따라 서로 다른 메서드를 실행할 수 있게된다.

메시지와 메서드를 분리하고 동적 바인딩을 통해 런타임에 실행될 메서드를 결정하는 객체지향의 이점을 누리기 위해서는 한가지 문제를 해결해야한다.

DiscountPolicy는 다양한 구체 DiscountCondition과 협력 가능해야하기 때문에 오직 DiscountPolicy 인터페이스에만 의존해야한다.
하지만 런타임에는 실제 구체 클래스들에게 메시지를 전송할 수 있어야 한다.

여기서 문제는 컴파일 타임 의존성과 런타임 의존성이 다르다는것이다.
따라서 DiscountPolicy 인스턴스를 생성할 때 컴파일 타임에 의존하는 대상인 DiscountCondition 인터페이스를 실제 구현 인스턴스로 대체해야한다.

이를 위해서는 외부의 객체가 실제 구현 인스턴스를 생성한 후에 DiscountPolicy 객체에게 전달해서 DiscountCondition의 자리를 전달한 객체로 대체할 수 있어야 한다.

![image](https://github.com/user-attachments/assets/20da874b-e218-48a1-b686-3606e7045d2f)

이렇게 컴파일 타임 의존성과 런타임 의존성 차이를 해결하기 위해 외부의 객체가 의존하는 객체를 전달하는 방식을 **의존성 주입**이라고 부른다.
- 생성자를 통해 외부의 객체로 부터 주입받을 수 있다.

### 메시지와 메서드 분리의 또 다른 장점

기능을 확장할 때 따라야하는 표준적인 구조를 유지할 수 있기 때문에 지속적으로 기능을 추가하면서도 설계를 유지할 수 있다.
이를 통해 확장하기 쉽고 예측하기 쉬운 설계를 얻게 된다.

### 비할인 정책 추가

![image](https://github.com/user-attachments/assets/d1daed82-0d4b-4682-93b1-43be695d9c21)

영화와 할인 정책 사이의 다중성은 0..1 임을 확인할 수 있다.
이때 다중성이 0인 영화는 사용자에게 할인을 제공하지 않는 경우를 의미한다. (비할인 정책)

이를 구현하는 가장 간단한 방법은 discountPolicy를 nullable하도록 수정하는것이다.

```kotlin
data class Movie(
    val fee: Money,
    val discountPolicy: DiscountPolicy?,
) {
    fun calculateFee(screening: Screening): Money {
        if (discountPolicy == null) return fee

        return fee.minus(discountPolicy.calculateDiscount(screening))
    }
}
```

하지만 이러한 설계는 두가지 문제점이 있다. 
- 첫번째는 DiscountPolicy가 Null인 Movie를 생성할 수 있다는 점이다.
  - 이것은 Movie 안에서 DiscountPolicy를 접근하는 모든 로직에 Null 체크가 필요해짐을 의미한다.
  - 또한 이는 두가지 의미라고 해석될 수 있는데, Movie가 비할인 정책이거나 아직 할인 정책이 정해지지 않음으로도 해석될 수 있다.
- 두번째는 calculateFee 로직에 예외 플로우가 추가됨이다.
  - 변경전에는 항상 메시지를 DiscountPolicy에게 전달했지만 변경 후에는 전달할 수도 있고 전달하지 않을 수도 있어졌다.
  - 따라서 코드의 실행 흐름을 이해하기 어려워진다.

이러한 문제는 Nullable하기 때문에 발생하므로 다중성을 0..1에서 1로 수정하면 해결가능하다.

```kotlin
class NoneDiscountPolicy(
    conditions: List<DiscountCondition>,
) : DiscountPolicy(conditions) {
    override fun getDiscountAmount(screening: Screening): Money {
        return Money.ZERO
    }
}
```

## 애플리케이션 객체 추가하기

나머지 GRASP 패턴 
- 간접화(Indirection)
- 컨트롤러(Controller)
- 순수한 가공물(Pure Fabrication)

애플리케이션을 레이어드 아키텍처 형태로 구현한다고 가정하자. 
현재까지 구현한 로직은 모두 도메인 레이어에 속하며 도메인 로직은 컴퓨터와 관련된 로직(DB, HTTP, ...)은 모두 배제하고 순수하게 도메인과 관련된 측면만을 나타내야한다.

![image](https://github.com/user-attachments/assets/7b459bbb-ed88-4b75-86f2-7dbd572576bb)

도메인 로직 자체는 컴퓨터와 무관하지만 컴퓨터 안에서 도메인 로직을 실행하기 위헤 컴퓨터와 관련된 기술적인 요소들을 활용해야한다.
대표적인 예시가 데이터베이스에 도메인 객체를 저장, 조회하는 로직이 존재한다.

애플리케이션의 기능을 완전히 구현하기 위해서는 트랜잭션, DB과 같은 기술적인 매커니즘과 도메인 로직을 조율하기위한 플로우를 구현해야한다.

**만약 사용자 인터페이스가 도메인 레이어에 직접 접근한다면?**

![image](https://github.com/user-attachments/assets/2f5fd68a-1901-42ed-9db1-67be931fa606)
 
도메인 로직 처리 복잡성이 사용자 인터페이스 클래스가 직접 처리하므로 코드가 굉자히 복잡해진다.

![image](https://github.com/user-attachments/assets/cca3771f-f650-4936-87cf-f1df6405726b)

콘솔 뿐만아니라 HTTP 프로토콜을 통해서도 처리하게 된다면 중복 로직이 생기게된다.

즉, 만약 사용자 인터페이스가 도메인 레이어에 직접 접근하게된다면 코드의 복잡성이 증가하고, 중복 코드 문제가 발생하게된다.

**해결방법은 도메인 레이어를 보호하는 새로운 레이어를 추가하는것이다.**

![image](https://github.com/user-attachments/assets/f8466aa5-7aac-4343-9a1d-61c6f830b0aa)

해당 객체에게 트랜잭션을 관리하고 DB에 접근하고 도메인 레이어를 실행하는 플로우 관련된 책임을 할당한다.
이를 통해 사용자 인터페이스는 중간 객체에게 책임을 위임하면 되기 때문에 로직이 간단해지며 중복 로직을 제거할 수 있다.

이렇게 직접적인 의존성으로 발생하는 문제를 피하기 위해 중간 객체를 추가한 후 책임을 할당하는 패턴을 **간접화** 패턴이라고 부른다.

**간접화 패턴**
- 문제: 직접적인 의존을 피하기 위해 어디에 책임을 할당해야 하는가?
- 해결방법: 다른 컴포넌트나 서비스가 직접 의존하지 않도록 중재하는 중간 객체에 책임을 할당하라

중간 객체는 도메인 모델에서 찾을 수 없다.
도메인 모델안에서 적합한 후보를 찾기 어렵다면 도메인 개념과 상관없는 인위적인 대상에게 책임을 할당할 수 밖에 없다.
이렇게 인위적인 대상에게 책임을 할당하는 패턴을 **순수한 가공물** 패턴이라고 부른다.

**순수한 가공물 패턴**
- 문제: 적당한 책임을 가진 클래스를 찾지 못하는 상황이거나 높은 응집도와 낮은 결합도를 위반하고 싶지 않은 경우에 누구에게 책임을 할당해야 하는가?
- 해결방법: 도메인 개념을 표현하지 않는 인위적으로 만든 클래스에 책임을 할당하라. 이런 클래스는 높은 응집도, 낮은 결합도, 재사용을 지원하기 위해 만들어진다.

이러한 순수한 가공물 중에서 사용자 인터페이스에게 기능을 제공하기 위한 책임을 할당함으로써 결합도를 낮추는 패턴을 **컨트롤러** 패턴이라고 한다.

**컨트롤러 패턴**
- 문제: UI 계층을 통해 전달되는 시스템의 오퍼레이션을 전달받고 조정(제어)할 최초의 객체는 무엇인가?
- 해결방법: 워크플로우를 표현한 객체에게 책임을 할당하라

![image](https://github.com/user-attachments/assets/9d2d1396-724a-47ca-9ba7-1adaeea13aee)

이러한 워크플로우를 다른말로 유스케이스 플로우 or 애플리케이션 로직이라고 부르며, 이러한 로직을 처리하는 서비스를 **애플리케이션 서비스**라고도 부른다.
애플리케이션 서비스는 도메인 레이어와 프레젠테이션 레이어 사에이 존재하는 이를 서비스 레이어라고 부른다.

![image](https://github.com/user-attachments/assets/a4db3dd8-55b1-443d-853e-905d4291835a)

데이터 접근 객체 역시 간접화 패턴을 적용한 예시이다.

![image](https://github.com/user-attachments/assets/7143de1f-0cd0-4963-8ed0-1305a50b6edc)

도메인 객체에게 자기 자신을 직접 데이터베이스에 저정하는 책임을 직접 할당할 수 있겠지만 이렇게 하면 도메인 객체가 데이터베이스에 의존하게 된다.
데이터 접근 객체를 추가하여 데이터베이스에 대한 책임을 처리하면 도메인 객체가 데이터베이스에 의존하지 않도록 만들 수 있다. 