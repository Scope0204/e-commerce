# 🛒 e-commerce Project
## 📘 Docs
- ### [API 요구 사항 & Sequence Diagram](https://github.com/Scope0204/e-commerce/tree/master/docs/sequence)
- ### [ERD](https://github.com/Scope0204/e-commerce/tree/master/docs/erd)
- ### [API 명세서](https://github.com/Scope0204/e-commerce/tree/master/docs/api)

## 🔧 Project Specification

| 항목 | 내용 |
|:---|:---|
| Language | Kotlin 1.9.25 |
| JVM Version | Java 17 |
| Framework | Spring Boot 3.4.4 |
| Build Tool | Gradle (Kotlin DSL) |
| ORM | Spring Data JPA |
| Database | MySQL |
| Mapper | MapStruct 1.5.5.Final |
| API Documentation | Springdoc OpenAPI 2.1.0 (Swagger UI) |
| Testing | JUnit5, Kotlin Test (kotlin-test-junit5) |

## 📦 Project Structure
아래는 `scope.commerce` 패키지의 디렉토리 구조로, `order` 도메인을 예시로 보여줍니다. 

```
📦 scope.commerce
├── common
│   ├── exception/        # 공통 예외 클래스 (예: NotFoundException.kt)
│   ├── util/             # 공통 유틸리티 (예: DateUtils.kt)
│   └── config/           # 공통 설정 (예: JacksonConfig.kt)
├── order
│   ├── api                    # 🌐 API 계층 (프레젠테이션)
│   │   ├── controller/        # REST 컨트롤러 (예: OrderController.kt)
│   │   └── dto/              # 요청/응답 DTO
│   │       ├── request/      # API 요청 DTO (예: OrderRequest.Create)
│   │       └── response/     # API 응답 DTO (예: OrderResponse.Summary)
│   │
│   ├── application           # 🧠 애플리케이션 계층 (유스케이스)
│   │   ├── usecase/          # 유스케이스 구현체 (예: CreateOrderUseCase.kt)
│   │   ├── dto/             # 명령/응답 DTO
│   │   │   ├── command/     # 유스케이스 입력 DTO (예: CreateOrderCommand.kt)
│   │   │   └── response/    # 유스케이스 출력 DTO (예: CreateOrderResponse.kt)
│   │   └── mapper/          # API ↔ 애플리케이션 DTO 변환 매퍼
│   │
│   ├── domain               # 🧩 도메인 계층 (비즈니스 로직)
│   │   ├── model/           # 도메인 모델 (예: Order, OrderProduct)
│   │   ├── service/         # 도메인 서비스 (예: OrderService.kt)
│   │   └── repository/      # 리포지토리 인터페이스 (예: OrderRepository.kt)
│   │
│   └── infra                # 🏗️ 인프라 계층 (영속성)
│       ├── entity/          # JPA 엔티티 (예: OrderEntity.kt, OrderProductEntity.kt)
│       ├── mapper/          # 도메인 ↔ 엔티티 변환 매퍼
│       └── repository/      # JPA, QueryDSL 구현체 (예: OrderRepositoryImpl.kt)
│
├── user                     # 동일한 구조 반복
├── product                  # 동일한 구조 반복
├── coupon                   # 동일한 구조 반복

```
<details>
    <summary> 프로젝트 아키텍처에 대한 자세한 설명은 다음과 같습니다.</summary>

## 프로젝트 아키텍처
이 프로젝트는 **도메인 주도 설계(DDD)** 와 **클린 아키텍처** 원칙을 기반으로 한 **4계층 아키텍처**를 따릅니다. 코드는 `scope.commerce` 패키지 아래 비즈니스 도메인(`order`, `user`, `product`, `coupon`)별로 구분하여, 관심사의 분리와 확장성을 보장합니다.

## 아키텍처 개요

프로젝트는 주요 네 개의 계층으로 구성되며, 각 계층은 고유한 책임을 가집니다

1. **API (프레젠테이션 계층)**: HTTP 요청과 응답을 처리하며, 클라이언트에게 RESTful 엔드포인트를 제공합니다. 컨트롤러와 요청/응답 DTO를 포함합니다.
2. **애플리케이션 계층**: 비즈니스 유스케이스를 조정하며, API와 도메인 계층 간의 중개를 담당합니다. 유스케이스와 명령/응답 DTO를 포함합니다.
3. **도메인 계층**: 핵심 비즈니스 로직을 캡슐화하며, 도메인 모델, 서비스, 리포지토리 인터페이스를 포함합니다. 애플리케이션의 중심입니다.
4. **인프라 계층**: 영속성 로직(예: JPA, QueryDSL)과 외부 연동을 구현합니다. 엔티티, 매퍼, 리포지토리 구현체를 포함합니다.

## 계층별 역할

- **API 계층**: 클라이언트 요청을 받아 유효성을 검증하고, 애플리케이션 계층의 유스케이스를 호출합니다. 응답 데이터를 클라이언트에 맞게 변환해 반환합니다.
- **애플리케이션 계층**: 비즈니스 유스케이스를 정의하고, 도메인 계층의 서비스를 호출해 비즈니스 로직을 실행합니다. API와 도메인 간 DTO 변환을 처리합니다.
- **도메인 계층**: 비즈니스 규칙과 로직을 캡슐화하며, 도메인 모델과 서비스를 통해 핵심 기능을 구현합니다. 리포지토리 인터페이스를 정의해 인프라 계층과 분리됩니다.
- **인프라 계층**: 데이터베이스 연동(JPA, QueryDSL)과 외부 시스템과의 통신을 처리합니다. 도메인 계층의 리포지토리 인터페이스를 구현합니다.

## 설계 원칙
- **DDD**: 도메인 중심 설계로, 비즈니스 로직을 도메인 계층에 집중시키고, 도메인 모델의 무결성을 보장합니다.
- **클린 아키텍처**: 계층 간 단방향 의존성을 유지하며, 도메인 계층이 외부 계층(API, 인프라)에 독립적이도록 설계했습니다.
</details>
